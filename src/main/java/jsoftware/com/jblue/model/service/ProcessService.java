package jsoftware.com.jblue.model.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.ProcessWaterIntakeUserDAO;
import jsoftware.com.jblue.model.dao.SequenceDAO;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.dto.DocumentRecordDTO;
import jsoftware.com.jblue.model.dto.ProcessDTO;
import jsoftware.com.jblue.model.dto.ProcessWaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.UserDocumentationDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.ProcessException;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jpaymentlib.model.dto.PaymentDTO;
import jsoftware.com.jpaymentlib.model.dto.wrp.PaymentWrapper;
import jsoftware.com.jpaymentlib.model.exp.PaymentException;
import jsoftware.com.jpaymentlib.model.service.PaymentService;
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
    private final ProcessWaterIntakeUserDAO pro_dao;
    private final SequenceDAO sequence_dao;
    private final UserService user_service;
    private final AddressService address_service;
    private final UserDocumentationService documentation_service;
    private final DocumentRecordService document_record_service;
    private final WaterIntakeUserService water_intake_service;
    private final PaymentService payment_service;

    private final HistoryDAO history_dao;

    public ProcessService(boolean flag_dev, String process_name) {
        super(flag_dev, process_name);
        // CORREGIDO: Inicialización de los DAOs para evitar NullPointerException
        this.dao = new ProcessDAO(flag_dev, process_name);
        pro_dao = new ProcessWaterIntakeUserDAO(flag_dev, process_name);
        this.sequence_dao = new SequenceDAO(flag_dev, process_name);
        user_service = new UserService(flag_dev, process_name);
        address_service = new AddressService(flag_dev, process_name);
        documentation_service = new UserDocumentationService(flag_dev, process_name);
        document_record_service = new DocumentRecordService(flag_dev, process_name);
        water_intake_service = new WaterIntakeUserService(flag_dev, process_name);
        payment_service = new PaymentService(flag_dev, user_message);
        history_dao = HistoryDAO.getInstance();
    }

    /**
     * PASO 1: GENERACIÓN DE SECUENCIA Y ESTADO INICIAL Registra el inicio del
     * trámite y genera el folio único.
     */
    public boolean start(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        try {
            ProcessDTO process = dto.getProcess();
            if (process.getStatus().equals("10")) {
                return true;
            }
            String final_office = ss.getCurrent_instance().getOfficeId();
            String final_employee = ss.getCurrentEmployee().getId();
            String final_admin = ss.getCurrentAdministration().getId();
            //[1] OBTENEMOS EL FOLIO DEL TRAMITE
            String sequence = sequence_dao.getNextValString(connection, "TRAMITES");
            if (Func.isNullEmptyBlank(sequence)) {
                return returnMessageError("NO SE PUDO GENERAR EL FOLIO DEL TRAMITE");
            }
            process.put("sequence_process", sequence);
            process.put("employee_start", final_employee);
            process.put("administration_start", final_admin);
            process.put("status", "10");
            process.put("last_employee_update", final_employee);
            //[2] SE REGISTRA EL INICIO DEL TRAMITE
            res = dao.startProcess(connection, dto.getProcess());
            if (!res) {
                return returnMessageError("REGISTRO EN BITACORA CORRUPTO - TRAMITE");
            }
            ProcessWaterIntakeUserDTO pwki = dto.getProcess_water_intake_user();
            pwki.put("process_id", process.getId());
            pwki.put("sequence", sequence);
            pwki.put("process_type", process.getProcessType());
            pwki.put("status", process.getStatus());
            pwki.put("last_employee_update", final_employee);
            res = pro_dao.insert(connection, pwki);
            if (!res) {
                return returnMessageError("REGISTRO EN BITACORA CORRUPTO - TRAMITE");
            }
            //[3]REGISTRAMOS AL CONTRIBUYENTE
            UserDTO user = dto.getUser();
            //DATOS POR DEFECTO PARA BUSQUEDA Y VALIDACION
            user.put("office_id", final_office);
            user.put("last_employee_update", final_employee);
            user.put("status", "0");//SI EL METODO EXIST NO ENCUENTRA NADA SE DEBE VALIDAR EL STATUS
            //SI EL USUARIO EXISTE, SE NOTIFICARA LAS OPERACIONES ALTERNATIVAS
            if (user_service.exist(connection, user)) {
                if (user_service.isError()) {
                    returnMessageError(user_service.getErrorCode(), user_service.getUserMessage());
                    return false;
                }
            }
            String final_status = "1";
            user.put("status", final_status);
            //SI ES UN ALTA DE TITULAR
            if (dto.getProcess().getProcessType().equals("1")) {
                user.put("user_type", "1");// SE ASIGNA ROL DE TITULAR
            }
            int user_id = user_service.save(connection, user);
            if (user_service.isError()) {
                returnMessageError(user_service.getErrorCode(), user_service.getUserMessage());
                return false;
            }
            //[4]REGISTRAMOS EL DOMICILIO
            AddressDTO address = dto.getAddress();
            address.put("user_id", String.valueOf(user_id));
            address.put("employee_id", final_employee);
            address.put("office_id", final_office);
            address.put("status", "1");
            res = address_service.insert(connection, address);
            if (!res) {
                return returnMessageError(address_service.getErrorCode(), address_service.getUserMessage());
            }
            //[4]REGISTRAMOS LOS DATOS GENERADOS
            pwki.put("user_id", user.getId());
            res = pro_dao.userRegister(connection, pwki);
            if (!res) {
                return returnMessageError("REGISTRO DE USUARIO INCORRECTO");
            }
            return returnMessageError(SERVICE_EXECUTE_OK, "OPERACION EXITOSA");
        } catch (SQLException | DataAccesObjectException ex) {
            returnMessageError(ex.getMessage());
            log(ex, "valid");
            res = false;
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
            if (dto.getProcess().getStatus().equals("11")) {
                return true;
            }
            String final_office = ss.getCurrent_instance().getOfficeId();
            String final_employee = ss.getCurrentEmployee().getId();
            String final_admin = ss.getCurrentAdministration().getId();
            dto.getProcess().put("employee_valid", final_employee);
            dto.getProcess().put("last_employee_update", final_employee);
            dto.getProcess().put("administration_end", null);
            dto.getProcess().put("status", "11");
            res = dao.validProcess(connection, dto.getProcess());
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
        } catch (SQLException | ProcessException ex) {
            returnMessageError(ex.getMessage());
            log(ex, "valid");
            res = false;
        }
        return res;
    }

    /**
     * PASO 2: VALIDACIÓN DE DOCUMENTOS Modifica el estado del trámite tras la
     * revisión física/digital de requisitos.
     */
    public boolean invalid(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        try {
            if (dto.getProcess().getStatus().equals("11")) {
                return true;
            }
            String final_employee = ss.getCurrentEmployee().getId();
            String final_admin = ss.getCurrentAdministration().getId();
            dto.getProcess().put("employee_valid", final_employee);
            dto.getProcess().put("last_employee_update", final_employee);
            dto.getProcess().put("administration_end", final_admin);
            dto.getProcess().put("status", "11");
            res = dao.cancelProcess(connection, dto.getProcess());
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
            document_record.put("status", "33");
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
        } catch (ProcessException ex) {
            System.getLogger(ProcessService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
    }

    /**
     * PASO 3: VALIDACIÓN DEL PAGO CASO 1: Pago inmediato (Efectivo/Terminal) ->
     * Pasa directo a Activo. CASO 2: Pago posterior/Transferencia -> Genera
     * línea de captura con vigencia limitada.
     */
    public boolean payment(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        try {
            String final_office = ss.getCurrent_instance().getOfficeId();
            String final_employee = ss.getCurrentEmployee().getId();
            boolean res = dao.payProcess(connection, dto.getProcess());
            if (!res) {
                throw new ProcessException(1, "NO SE PUDO ACTUALIZAR EL STATUS DEL TRAMITE");
            }
            PaymentDTO payment = new PaymentDTO();
            payment.put("payment_type_id", "1");//PAGO DE TRAMITES
            //SI EL PAGO ES GENERADO
            // SI EL PAGO FUE VALIDADO, FUE EN EFECTIVO
            payment.put("payment_method_id", dto.isPayment_valid() ? "1" : "6");
            payment.put("observation", dto.isPayment_valid() ? "PAGO VALIDADO PRESENCIALMENTE" : "PAGO CON LINEA GENERADA");
            payment.put("status", dto.isPayment_valid() ? "7" : "8");
            payment.put("office_id", final_office);
            payment.put("last_employee_update", final_employee);

            PaymentWrapper wrp = payment_service.paymentProcess(connection, payment, null);
            if (Func.isNull(wrp)) {
                throw new ProcessException(1, "PAGO NO GENERADO");
            }

            res = payment_service.save(connection, wrp);
            if (!res) {
                throw new ProcessException(2, "PAGO NO GENERADO");
            }
        } catch (SQLException | PaymentException | ProcessException ex) {
            System.getLogger(ProcessService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
//        payment.saveProcess(connection, dto);
//        if (dto.getProcess().getStatus().equals("11")) {
//            return true;
//        }
//        if (opt.isEmpty()) {
//            returnMessageError(0, "ERROR AL CALCULAR MONTOS E IMPORTES");
//            return false;
//        }
//
//        PaymentWrapper get = opt.get();
//        PaymentHeaderDTO header = get.getHeader();
//        header.put("process_id", dto.getProcess().getId());
//        header.put("sequence", dto.getProcess().getSequenceProcess());
//        header.put("uuid", UUID.randomUUID().toString());
//        header.put("user_id", dto.getUser().getId());
//        header.put("wki_user_id", dto.getWki_user().getId());
//        header.put("office_id", ss.getCurrent_instance().getOfficeId());
//        header.put("cash_box_turn_id", null);
//        header.put("authorized_by", null);
//        header.put("payment_method_id", "1");
//        header.put("payment_type_id", "4");
//        header.put("is_surcharge_paid", !header.getTotalSurcharge().equals("0.00"));
//        header.put("print_count", "0");
//        header.put("status", "1");
//        header.put("employee_id", ss.getCurrentEmployee().getId());
//        dto.setPayment_header(header);
//        //
//        List<PaymentDetailDTO> detail = get.getDetail();
//        dto.setPayment_details(detail);
        return true;
    }

    /**
     * PASO 4: FINALIZACIÓN Cierre formal del trámite en el sistema.
     */
    public boolean finalized(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        try {
            String status = dto.getPayment_wrp().getPayment().getStatus();
            if (!status.equals("1")) {
                return res;
            }
            res = dao.endProcess(connection, dto.getProcess());
            if (!res) {
                returnMessageError("EL TRAMITE NO PUDO SER FINALIZADO, POR FALTA DE PAGO");
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

    public boolean mov(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        try {
            String final_office = ss.getCurrent_instance().getOfficeId();
            String final_employee = ss.getCurrentEmployee().getId();
            String final_admin = ss.getCurrentAdministration().getId();
            ProcessDTO process = dto.getProcess();
            process.put("employee_mov", final_employee);
            process.put("last_employee_update", final_employee);
            res = dao.movProcess(connection, dto.getProcess());
            if (!res) {
                return returnMessageError("EL TRAMITE NO PUDO SER FINALIZADO");
            }
            WaterIntakeUserDTO wki_user = dto.getWki_user();
            wki_user.put("process_id", process.getId());
            wki_user.put("employee", final_employee);
            wki_user.put("user_id", dto.getUser().getId());
            wki_user.put("address_id", dto.getAddress().getId());
            wki_user.put("water_intake_id", dto.getWater_intake().getId());
            wki_user.put("water_intake_type_id", dto.getWater_intake_type().getId());
            wki_user.put("user_type_id", dto.getUser().getUserType());
            wki_user.put("is_consumer", process.getProcessType().equals("1") ? "0" : "1");
            wki_user.put("office_id", final_office);
            wki_user.put("user_name", dto.getUser().toString());
            wki_user.put("description", dto.getTransaction().getObservation());
            wki_user.put("observation", dto.getProcess().getObservation());
            wki_user.put("current_fiscal_year", "-1");
            wki_user.put("last_month_paid", "-1");
            wki_user.put("last_amount_paid", "0.00");
            wki_user.put("last_process_id", process.getProcessType());
            wki_user.put("last_employee_update", final_employee);
            wki_user.put("status", "1");
            res = water_intake_service.saveProcess(connection, dto.getWki_user());
            if (!res) {
                return returnMessageError("EL TRAMITE NO PUDO SER FINALIZADO");
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

    public boolean exists(JDBConnection c, UserDTO user) {
        boolean exist = user_service.exist(c, user);
        if (user_service.isError()) {
            returnMessageError(user_service.getErrorCode(), user_service.getUserMessage());
            return false;
        }
        if (exist) {
            user_service.set(c, user);
        }
        return exist;
    }

    public boolean search(JDBConnection c, ProcessDTO process, String current_status_method) {
        boolean exist = false;
        try {
            exist = dao.exist(c, process);
            if (!exist) {
                return false;
            }
            boolean valid_status = Func.isNotNull(process.getStatus());
            if (!valid_status) {
                return returnMessageError("EL TRAMITE NO TIENE UN STATUS VALIDO");
            }

        } catch (SQLException ex) {
            return returnMessageError(ex.getErrorCode(), ex.getMessage());
        }
        return exist;
    }
}
