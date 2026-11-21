/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.abst.AbstractService;
import static jsoftware.com.jblue.model.abst.ServiceModel.SERVICE_EXECUTE_OK;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.ProcessWaterIntakeUserDAO;
import jsoftware.com.jblue.model.dao.SequenceDAO;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.dto.ProcessDTO;
import jsoftware.com.jblue.model.dto.ProcessWaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.wrp.UserRegisterWrapperDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class UserRegisterService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private UserService user_service;
    private AddressService address_service;
    private ProcessDAO process_dao;
    private ProcessWaterIntakeUserDAO pro_dao;
    private SequenceDAO sequence_dao;

    public UserRegisterService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
    }

    public boolean save(JDBConnection connection, SystemSession ss, UserRegisterWrapperDTO dto) {
        boolean res = false;
        try {
            ProcessDTO process = dto.getProcess();

            String final_office = ss.getCurrent_instance().getOfficeId();
            int final_employee = ss.getCurrentEmployee().getId();
            String final_admin = ss.getCurrentAdministration().getId();
            //[1] OBTENEMOS EL FOLIO DEL TRAMITE
            String sequence = sequence_dao.getNextValString(connection, "TRAMITES");
            if (Func.isNullEmptyBlank(sequence)) {
                return returnMessageError("NO SE PUDO GENERAR EL FOLIO DEL TRAMITE");
            }
            //[2] SE REGISTRA EL INICIO DEL TRAMITE
            res = process_dao.startProcess(connection, dto.getProcess());
            if (!res) {
                return returnMessageError("REGISTRO EN BITACORA CORRUPTO - TRAMITE");
            }
            ProcessWaterIntakeUserDTO pwki = dto.getProcess_water_intake_user();
            pwki.put("process_id", process.getId());
            pwki.put("sequence", sequence);
            pwki.put("process_type", process.getProcess_type());
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
            if (dto.getProcess().getProcess_type() == 1) {
                user.put("user_type", "1");// SE ASIGNA ROL DE TITULAR
            }
            res = user_service.save(connection, ss, user);
            if (!res || user_service.isError()) {
                returnMessageError(user_service.getErrorCode(), user_service.getUserMessage());
                return false;
            }
            //[4]REGISTRAMOS EL DOMICILIO
            AddressDTO address = dto.getAddress();
            address.put("user_id", String.valueOf(user.getId()));
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
            commit(connection);
            return returnMessageError(SERVICE_EXECUTE_OK, "OPERACION EXITOSA");
        } catch (SQLException ex) {
            res = false;
            returnMessageError(-1, ex.getMessage());
            rollback(connection);
        } catch (KeyNotGenerateException | CorruptInsertionException ex) {
            res = false;
            returnMessageError(SERVICE_EXECUTE_ERROR, ex.getUserMessage());
            rollback(connection);
        } catch (DataAccesObjectException ex) {
            System.getLogger(UserRegisterService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
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
}
