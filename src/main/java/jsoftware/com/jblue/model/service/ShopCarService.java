/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.model.dao.ViewsDAO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.vw.WaterIntakeUserPaymentDTO;
import jsoftware.com.jblue.model.dto.wrp.ShopCartWrapperDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.SystemException;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.utils.platf.FuncLogs;
import jsoftware.com.jpaymentlib.model.dao.WaterIntakePaymentUserDAO;
import jsoftware.com.jpaymentlib.model.dto.PaymentInputBuilder;
import jsoftware.com.jpaymentlib.model.dto.PaymentInputDTO;
import jsoftware.com.jpaymentlib.model.dto.WaterIntakePaymentUserDTO;
import jsoftware.com.jpaymentlib.model.dto.wrp.PaymentWrapper;
import jsoftware.com.jpaymentlib.model.exp.PaymentException;
import jsoftware.com.jpaymentlib.model.service.PaymentService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class ShopCarService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final WaterIntakeUserService wki_service;
    private final SystemSessionService ss_service;
    private final PaymentService payment_service;
    private final ViewsDAO dao;
    private final WaterIntakePaymentUserDAO pym_dao;

    public ShopCarService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        wki_service = new WaterIntakeUserService(dev_flag, process_name);
        dao = new ViewsDAO(dev_flag, process_name);
        payment_service = new PaymentService(dev_flag, process_name);
        ss_service = new SystemSessionService(dev_flag, process_name);
        pym_dao = new WaterIntakePaymentUserDAO();
    }

    public boolean calculation(JDBConnection connection, SystemSession ss, ShopCartWrapperDTO dto) {
        boolean res = false;
        try {
            ss_service.valid(connection, ss);
            int final_employee = Integer.parseInt(ss.getCurrentEmployee().getId());
            int final_office = Integer.parseInt(ss.getCurrent_instance().getOfficeId());
            int final_committee = Integer.parseInt(ss.getCurrent_instance().getCommitteeId());
            int final_administration = Integer.parseInt(ss.getCurrentAdministration().getId());
            System.out.println(final_administration);
            PaymentInputDTO input = new PaymentInputBuilder()
                    .setEmployee_id(final_employee)
                    .setOffice_id(final_office)
                    .setCommittee_id(final_committee)
                    .setAdministration_id(final_administration)
                    .setUser_id(dto.getWki_usr_pym().getId_usuario())
                    .setWater_intake_user_id(dto.getWki_usr_pym().getId_toma())
                    .setWater_intake_number(dto.getWki_usr_pym().getNumero_toma())
                    .setUser_name(dto.getWki_usr_pym().getNombre_usuario())
                    .setMonth_list(dto.getMonths())
                    .buildCalculateMonthlyPayment();
            PaymentWrapper calculate = payment_service.calculate(connection, input);
            dto.setPym_wrp(calculate);
            res = true;
        } catch (SystemException | SQLException | PaymentException | DataAccesObjectException ex) {
            FuncLogs.log(getClass(), ex, process_name, "calculation", ex.getMessage());
            res = returnMessageError(ex.getMessage());
        }
        return res;
    }

    public boolean payment(JDBConnection connection, SystemSession ss, ShopCartWrapperDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            ss_service.valid(connection, ss);

            // 1. Guardar la cabecera e importes del pago
            res = payment_service.save(connection, ss, dto.getPym_wrp());
            if (!res) {
                rollback(connection);
                return returnMessageError(payment_service.getUserMessage());
            }

            // 2. Actualizar la planilla de pagos
            WaterIntakeUserPaymentDTO wki_user_pym = dto.getWki_usr_pym();
            if (wki_user_pym == null) {
                rollback(connection);
                return returnMessageError("DATOS DE PAGO DE TOMA NO ENCONTRADOS");
            }

            // Mapeo correcto del DTO de meses y años fiscales
            WaterIntakePaymentUserDTO months_dto = new WaterIntakePaymentUserDTO();
            months_dto.setWki_id(wki_user_pym.getId_toma());
            months_dto.setFiscal_year(wki_user_pym.getUlt_año_pago());
            months_dto.setEne(wki_user_pym.isEne());
            months_dto.setFeb(wki_user_pym.isFeb());
            months_dto.setMar(wki_user_pym.isMar());
            months_dto.setAbr(wki_user_pym.isAbr());
            months_dto.setMay(wki_user_pym.isMay());
            months_dto.setJun(wki_user_pym.isJun());
            months_dto.setJul(wki_user_pym.isJul());
            months_dto.setAgo(wki_user_pym.isAgo());
            months_dto.setSep(wki_user_pym.isSep());
            months_dto.setOct(wki_user_pym.isOct());
            months_dto.setNov(wki_user_pym.isNov());
            months_dto.setDic(wki_user_pym.isDic());

            // 3. Inserción o actualización de los meses pagados
            Optional<WaterIntakePaymentUserDTO> existingRecord = pym_dao.selectByWkiIdAndFiscalYear(connection, months_dto.getWki_id(), months_dto.getFiscal_year());
            if (existingRecord.isEmpty()) {
                res = pym_dao.insert(connection, months_dto);
            } else {
                res = pym_dao.updateMonths(connection, months_dto);
            }

            if (!res) {
                rollback(connection);
                return returnMessageError("NO SE PUDIERON ACTUALIZAR LOS MESES DE PAGO");
            }

            // 4. Verificación de los 12 meses pagados en el año fiscal
            Optional<WaterIntakePaymentUserDTO> op = pym_dao.selectByWkiIdAndFiscalYear(connection, months_dto.getWki_id(), months_dto.getFiscal_year());
            if (op.isEmpty()) {
                rollback(connection);
                return returnMessageError("OPERACION COMPROMETIDA, FAVOR DE VALIDAR LOS MODULOS DE AUDITORIA");
            }

            WaterIntakePaymentUserDTO dto_before = op.get();
            List<Boolean> months_paid = Arrays.asList(
                    dto_before.isEne(), dto_before.isFeb(), dto_before.isMar(),
                    dto_before.isAbr(), dto_before.isMay(), dto_before.isJun(),
                    dto_before.isJul(), dto_before.isAgo(), dto_before.isSep(),
                    dto_before.isOct(), dto_before.isNov(), dto_before.isDic()
            );

            int totalPaidMonths = 0;
            int month = 0;
            int j = 0;
            for (Boolean isPaid : months_paid) {
                j++;
                if (isPaid != null && isPaid) {
                    totalPaidMonths++;
                    month = j;
                }
            }
            // Si ya cubrió los 12 meses, actualizamos el estatus a 7 (Completado / Pagado anual)
            if (totalPaidMonths == 12 || month == 12) {
                pym_dao.updateStatusByWkiId(connection, months_dto.getWki_id(), months_dto.getFiscal_year(), 7);
            }

            // 5. Actualización del último pago en la entidad de la toma
            WaterIntakeUserDTO wki_user = dto.getWki_user();
            if (wki_user != null) {
                wki_user.put("id", wki_user_pym.getId_toma());
                wki_user.put("user_id", wki_user_pym.getId_usuario());

                // Corrección del string format con comodines %s
                String observation = "PAGO REALIZADO CON EL FOLIO: %s Y NO DE OPERACION: %s"
                        .formatted(dto.getPym_wrp().getPayment().getId(), dto.getPym_wrp().getOperation().getId());
                wki_user.put("description", observation);

                wki_user.put("fiscal_year", months_dto.getFiscal_year());
                wki_user.put("last_month_paid", String.valueOf(month));
                wki_user.put("last_amount_paid", dto.getPym_wrp().getHeader().getTotal_amount());
                wki_user.put("last_employee_update", ss.getCurrentEmployee().getId());

                res = wki_service.updateLastPayment(connection, ss, wki_user);
                if (!res) {
                    rollback(connection);
                    return returnMessageError(wki_service.getUserMessage());
                }
            }

            commit(connection);
            return returnMessageError(SERVICE_EXECUTE_OK, "OPERACION EXITOSA");
        } catch (SystemException | SQLException ex) {
            rollback(connection);
            FuncLogs.log(getClass(), ex, process_name, "payment", ex.getMessage());
            return returnMessageError(ex.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<WaterIntakeUserDTO> search(JDBConnection connection, SystemSession ss, ShopCartWrapperDTO dto) {
        List<WaterIntakeUserDTO> res = wki_service.search(connection, ss, dto);
        return res;
    }

    public boolean getUser(JDBConnection connection, SystemSession ss, ShopCartWrapperDTO wrp) {
        try {
            ss_service.valid(connection, ss);
            int id_toma = Integer.parseInt(wrp.getWki_user().getId() == null ? "-1" : wrp.getWki_user().getId());
            if (id_toma == -1) {
                return returnMessageError("ID DE LA TOMA NO VALIDA");
            }
            Optional<WaterIntakeUserPaymentDTO> o = dao.get(connection, id_toma);
            if (o.isEmpty()) {
                return returnMessageError("TOMA NO ENCONTRADA");
            }
            WaterIntakeUserPaymentDTO wki_pym = o.get();
            wrp.setWki_usr_pym(wki_pym);
            return true;
        } catch (SQLException | SystemException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
}
