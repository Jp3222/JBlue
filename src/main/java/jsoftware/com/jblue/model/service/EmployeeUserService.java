/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.cryp.BCryptCrypto;
import jsoftware.com.jblue.model.crypto.DeterministicCrypto;
import jsoftware.com.jblue.model.dao.EmployeeUserDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO.EmployeeUserHistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Servicio encargado de gestionar el ciclo de vida y persistencia de los
 * usuarios. Mapea las credenciales a sus respectivos entornos criptográficos
 * seguros.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-11
 * @version 2.0
 */
public class EmployeeUserService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private EmployeeUserDAO dao;
    private EmployeeUserHistoryDAO hys;

    // Frase semilla del sistema para el cifrado determinista del usuario
    private static final String SYSTEM_PEPPER = "JBl_u3#Pozo$2026_MasterKey";

    public EmployeeUserService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        // Se asume que adaptaste el constructor de tu DAO o pasas las banderas correspondientes
        dao = new EmployeeUserDAO(dev_flag, user_message);
        hys = EmployeeUserHistoryDAO.getInstance();
    }

    public int insert(JDBConnection connection, EmployeeUserDTO dto) {
        int pk = 0;
        boolean res = false;
        try {

            // 2. Extraemos los valores en texto plano del DTO (vienen de la vista Swing)
            String rawUser = dto.getUser();       // Suponiendo que tienes el getter mapeado al Map
            String rawPassword = dto.getPassword();

            if (rawUser == null || rawPassword == null) {
                throw new ServiceException(400, "EL USUARIO Y LA CONTRASEÑA SON REQUERIDOS EN EL DTO");
            }

            // 3. Procesamos criptográficamente las credenciales
            // El usuario se cifra de forma determinista (AES); la contraseña se destruye de forma segura (BCrypt)
            String secureUser = DeterministicCrypto.encryp(rawUser, SYSTEM_PEPPER);
            String securePassword = BCryptCrypto.encryp(rawPassword);

            // 4. Asignación de credenciales seguras al DTO para el transporte hacia el DAO
            dto.put("user", secureUser);
            dto.put("password", securePassword);

            // 5. REGISTRO DE EMPLEADO EN MYSQL
            pk = dao.insert(connection, dto);
            res = pk > 0;
            if (!res) {
                throw new ServiceException(1, "LOS DATOS DEL USUARIO NO SE HAN REGISTRADO CORRECTAMENTE");
            }

            // 6. REGISTRO EN BITACORA MACRO (Formateando el log de auditoría con los datos limpios)
            String logDescription = "SE REGISTRO EN EL PADRON DE EMPLEADOS: %s - ID: %d"
                    .formatted(rawUser, pk); // Guardamos el rastro con el usuario legible para auditoría humana interna

            res = hys.insert(connection, logDescription);
            if (!res) {
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO");
            }

            if (isDev_flag()) {
                System.out.println("[%s - SUCCESS]: Usuario registrado con éxito. Generó PK: %d"
                        .formatted(getProcess_name(), pk));
            }
        } catch (SQLException ex) {
            returnMessageError(ex.getErrorCode(), ex.getMessage());
        } catch (ServiceException | CorruptInsertionException | KeyNotGenerateException ex) {
            // Unificamos el manejo de errores gracias a las excepciones controladas de JBlue
            returnMessageError(ex.getErrorCode(), ex.getUserMessage());
        }
        return pk;
    }
}
