/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class OwnerRegisterProcessService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final UserService user;
    private final PaymentService payment;
    private final WaterIntakeService water_intake;

    public OwnerRegisterProcessService(boolean flag_dev, String process_name) {
        super(flag_dev, process_name);
        user = new UserService(flag_dev, process_name);
        payment = new PaymentService(flag_dev, process_name);
        water_intake = new WaterIntakeService(flag_dev, process_name);
    }
    
    /**
     * 
     * @param connection
     * @param process_type
     * @param dto
     * @return 
     */
    public boolean save(JDBConnection connection, String process_type, ProcessWrapperDTO dto) {
        boolean res = false;
        connection.setAutoCommit(false);
        try {
            //SE VALIDA LA CONEXION
            if (Func.isNull(connection) || Func.isNull(connection.getConnection()) || connection.isClose()) {
                error_code = 1;
                user_message = "ERROR DE CONEXION";
                connection.rollBack();
                connection.setAutoCommit(true);
                return false;
            }
            //SE VALIDA EL PROCESO
            if (Func.isNull(process_type) || Func.isNullEmptyBlank(process_type)) {
                error_code = 2;
                user_message = "TRAMITE NO DEFINIDO";
                connection.rollBack();
                connection.setAutoCommit(true);
                return false;
            }
            //SE VALIDA EL OBJETO DE TRAMITE
            if (Func.isNull(dto)) {
                error_code = 3;
                user_message = "DATOS CAPTURADOS ERRONEOS INTERNAMENETE";
                connection.rollBack();
                connection.setAutoCommit(true);
                return false;
            }
            //SE VALIDA EL REGISTRO DE ADMINISTRACION
            res = session.isAdministrationValid();
            if (!res) {
                error_code = 3;
                user_message = "LA ADMINISTRACION NO HA SIDO REGISTRADA, MOVIMIENTO NO PERMITIDO";
                connection.rollBack();
                connection.setAutoCommit(true);
                return false;
            }
            //SE VALIDA EL QUE SE GUARDE EL USUARIO
            int user_id = user.saveProcess(connection, process_type, dto);
            res = user.isError();
            if (res) {
                error_code = user.getErrorCode();
                user_message = user.getUserMessage();
                connection.rollBack();
                connection.setAutoCommit(true);
                return false;
            }
            //SE VALIDA QUE SE GUARDE LA TOMA DE AGUA POTABLE
            boolean water_intake_id = water_intake.saveProcess(connection, process_type, dto);
            if (water_intake.isError()) {
                error_code = water_intake.getErrorCode();
                user_message = water_intake.getUserMessage();
                connection.rollBack();
                connection.setAutoCommit(true);
                return false;
            }
            //SE VALIDA EL PAGO
            boolean payment_id = payment.saveProcess(connection, process_type, dto);
            if (payment.isError()) {
                error_code = payment.getErrorCode();
                user_message = payment.getUserMessage();
                connection.rollBack();
                connection.setAutoCommit(true);
                return false;
            }
            // SI NO HAY ERRORES SE CONFIRMAN LOS CAMBIOS
            connection.commit();
        } catch (ServiceException | CorruptInsertionException | KeyNotGenerateException ex) {
            error_code = ex.getErrorCode();
            user_message = ex.getUserMessage();
            connection.rollBack();
        } catch (SQLException ex) {
            error_code = ex.getErrorCode();
            user_message = "ERROR INTERNO";
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    public PaymentService getPayment() {
        return payment;
    }

    public UserService getUser() {
        return user;
    }

    public WaterIntakeService getWater_intake() {
        return water_intake;
    }

}
