package jsoftware.com.jblue.model.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.SequenceDAO;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.dto.DocumentRecordDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.UserDocumentationDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.ProcessException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jpaymentlib.model.dto.PaymentDetailDTO;
import jsoftware.com.jpaymentlib.model.dto.PaymentHeaderDTO;
import jsoftware.com.jpaymentlib.model.dto.wrp.PaymentWrapper;
import jsoftware.com.jpaymentlib.model.service.PaymentHeaderService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Servicio encargado de orquestar el flujo secuencial de los trámites de
 * contratación e instalación del servicio de agua potable.
 *
 * @author juanp
 * @since 2026-07-11
 */
public class ProcessService extends AbstractService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ProcessDAO dao;
    private final SequenceDAO sequence_dao;
    private final UserService user_service;
    private final AddressService address_service;
    private final UserDocumentationService documentation_service;
    private final DocumentRecordService document_record_service;
    private final WaterIntakeUserService water_intake_service;
    private final PaymentHeaderService header_service;
    private final PaymentService payment_service;
    private final HistoryDAO history_dao;

    public ProcessService(boolean flag_dev, String process_name) {
        super(flag_dev, process_name);
        // CORREGIDO: Inicialización de los DAOs para evitar NullPointerException
        this.dao = new ProcessDAO(flag_dev, process_name);
        this.sequence_dao = new SequenceDAO(flag_dev, process_name);
        user_service = new UserService(flag_dev, process_name);
        address_service = new AddressService(flag_dev, process_name);
        documentation_service = new UserDocumentationService(flag_dev, process_name);
        document_record_service = new DocumentRecordService(flag_dev, process_name);
        water_intake_service = new WaterIntakeUserService(flag_dev, process_name);
        header_service = new PaymentHeaderService(flag_dev, process_name);
        payment_service = new PaymentService(flag_dev, process_name);
        history_dao = HistoryDAO.getInstance();
    }

    /**
     * PASO 1: GENERACIÓN DE SECUENCIA Y ESTADO INICIAL Registra el inicio del
     * trámite y genera el folio único.
     */
    public boolean start(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);

            // Se obtiene el folio consecutivo desde la base de datos
            String sequence = sequence_dao.getNextValString(connection, "TRAMITES");
            dto.put("sequence", sequence);

            res = dao.startProcess(connection, dto.getProcess()) > 0;
            if (!res) {
                returnMessageError("REGISTRO EN BITACORA CORRUPTO - TRAMITE");
            }
            res = userRegister(connection, ss, dto);
            if (!res && user_service.isError()) {
                returnMessageError(user_service.getErrorCode(), user_service.getUserMessage());
            }
            if (!res && address_service.isError()) {
                returnMessageError(address_service.getErrorCode(), address_service.getUserMessage());
            }
            commit(connection);
            connection.setAutoCommit(true); // Solo cambia si el commit fue exitoso
        } catch (SQLException | DataAccesObjectException ex) {
            returnMessageError(ex.getMessage());
            rollback(connection);
            log(ex, "valid");
            res = false;
        }
        return res;
    }

    /**
     * PASO 1.1: REGISTRO DEL CONTRIBUYENTE Asocia los datos demográficos
     * evaluando que no existan incidencias previas.
     */
    public boolean userRegister(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        String final_office = ss.getCurrent_instance().getOfficeId();
        String final_employee = ss.getCurrentEmployee().getId();
        UserDTO user = dto.getUser();
        //DATOS POR DEFECTO PARA BUSQUEDA Y VALIDACION
        user.put("office_id", final_office);
        user.put("last_employee_update", final_employee);
        user.put("status", "0");//SI EL METODO EXIST NO ENCUENTRA NADA SE DEBE VALIDAR EL STATUS
        //SI EL USUARIO EXISTE, SE NOTIFICARA LAS OPERACIONES ALTERNATIVAS
        if (user_service.exist(connection, user)) {
            return false;
        }
        //SI EL PAGO FUE REALIZADO PRESENCIALMENTE TODOS LOS REGISTROS SERAN ACTIVOS,
        //SI NO, SE MARCAN COMO INACTIVOS
        String final_status = dto.isPayment_header_valid() ? "1" : "2";
        user.put("status", final_status);
        int user_id = user_service.save(connection, user);
        if (user_service.isError()) {
            return false;
        }
        AddressDTO address = dto.getAddress();
        address.put("user_id", String.valueOf(user_id));
        address.put("employee_id", final_employee);
        address.put("office_id", final_office);
        address.put("status", "1");
        res = address_service.insert(connection, address);
        if (!res) {
            return false;
        }
        return res;
    }

    /**
     * PASO 2: VALIDACIÓN DE DOCUMENTOS Modifica el estado del trámite tras la
     * revisión física/digital de requisitos.
     */
    public boolean valid(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        try {
            String final_office = ss.getCurrent_instance().getOfficeId();
            String final_employee = ss.getCurrentEmployee().getId();
            res = dao.startProcess(connection, dto.getProcess()) > 0;
            if (!res) {
                returnMessageError("REGISTRO EN BITACORA CORRUPTO - TRAMITE");
            }
            List<UserDocumentationDTO> list = dto.getDocument_list();
            res = documentation_service.insert(connection, list);
            if (!res) {
                rollback(connection);
                returnMessageError(documentation_service.getErrorCode(), documentation_service.getUserMessage());
                return false;
            }
            DocumentRecordDTO document_record = dto.getDocument_record();
            document_record.put("employee_register_id", final_employee);
            document_record.put("status", "2");
            if (dto.isDocument_record_valid()) {
                document_record.put("employee_valid_id", final_employee);
                document_record.put("status", "1");
            }
            document_record.put("document_start", list.getFirst().getId());
            document_record.put("document_end", list.getLast().getId());
            res = document_record_service.save(connection, document_record) > 0;
            if (!res) {
                rollback(connection);
                returnMessageError(document_record_service.getErrorCode(), document_record_service.getUserMessage());
                return false;
            }
        } catch (SQLException ex) {
            returnMessageError(ex.getMessage());
            rollback(connection);
            log(ex, "valid");
            res = false;
        }
        return res;
    }

    /**
     * PASO 2.1: REGISTRO DE TOMA Si los documentos son válidos, se asignan las
     * coordenadas y características de la toma de agua.
     */
    public boolean waterIntakeRegister(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        String final_office = ss.getCurrent_instance().getOfficeId();
        String final_employee = ss.getCurrentEmployee().getId();
        WaterIntakeDTO wki = dto.getWater_intake();
        wki.put("status", "2");
        wki.put("employee_register", final_employee);
        wki.put("last_employee_update", final_employee);
        if (dto.isWater_intake_valid()) {
            wki.put("status", "1");
            wki.put("last_employee_update", final_employee);
        }
        res = water_intake_service.saveProcess(connection, null);
        if (!res) {
            rollback(connection);
            returnMessageError(water_intake_service.getErrorCode(), water_intake_service.getUserMessage());
            return false;
        }
        return res;
    }

    /**
     * PASO 3: VALIDACIÓN DEL PAGO CASO 1: Pago inmediato (Efectivo/Terminal) ->
     * Pasa directo a Activo. CASO 2: Pago posterior/Transferencia -> Genera
     * línea de captura con vigencia limitada.
     */
    public boolean payment(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        Optional<PaymentWrapper> opt = header_service.saveProcess(connection, null);

        if (opt.isEmpty()) {
            returnMessageError(0, "ERROR AL CALCULAR MONTOS E IMPORTES");
            return false;
        }

        PaymentWrapper get = opt.get();
        PaymentHeaderDTO header = get.getHeader();
        header.put("process_id", dto.getProcess().getId());
        header.put("sequence", dto.getProcess().getSequenceProcess());
        header.put("uuid", UUID.randomUUID().toString());
        header.put("user_id", dto.getUser().getId());
        header.put("wki_user_id", dto.getWki_user().getId());
        header.put("office_id", ss.getCurrent_instance().getOfficeId());
        header.put("cash_box_turn_id", null);
        header.put("authorized_by", null);
        header.put("payment_method_id", "1");
        header.put("payment_type_id", "4");
        header.put("is_surcharge_paid", !header.getTotalSurcharge().equals("0.00"));
        header.put("print_count", "0");
        header.put("status", "1");
        header.put("employee_id", ss.getCurrentEmployee().getId());
        dto.setPayment_header(header);
        //
        List<PaymentDetailDTO> detail = get.getDetail();
        dto.setPayment_details(detail);
        return true;
    }

    /**
     * PASO 4: FINALIZACIÓN Cierre formal del trámite en el sistema.
     */
    public boolean finalized(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        try {
            int status = Integer.parseInt(dto.getPayment_header().getStatus());
            if (status != 1) {
                return res;
            }
            res = dao.endProcess(connection, status, user_message);
            if (!res) {
                returnMessageError("EL TRAMITE NO PUDO SER FINALIZADO");
            }
        } catch (SQLException ex) {
            returnMessageError(ex.getMessage());
            rollback(connection);
            log(ex, "valid");
            res = false;
        } catch (ProcessException ex) {
            returnMessageError(ex.getErrorCode(), ex.getMessage());
            rollback(connection);
            log(ex, "valid");
            res = false;
        }

        return res;
    }

    /**
     * PASO 5: IMPRESIÓN DE CONSTANCIA Entrega del documento oficial. Si pasan
     * 30 días, se requiere trámite de reimpresión.
     */
    public boolean print(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        return true;
    }
}
