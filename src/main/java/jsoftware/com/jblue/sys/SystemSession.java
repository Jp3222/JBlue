package jsoftware.com.jblue.sys;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.SessionDTO;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.sys.LocalSession;

/**
 * Esta clase define al personal de sesión actual, gestionando quién utiliza el
 * programa y el contexto administrativo activo. Implementa el patrón Singleton
 * para asegurar una única instancia de sesión a nivel de aplicación (o hilo).
 *
 * @author jp
 * @version 1.0
 * @since 2025-11-22
 */
public class SystemSession implements LocalSession<EmployeeUserDTO>, Serializable {

    private static SystemSession instancia;
    private static final long serialVersionUID = 1L;

    /**
     * Retorna una única instancia de la clase Sesión (Patrón Singleton). El
     * método está sincronizado para garantizar la seguridad en entornos
     * multihilo.
     *
     * @return La instancia única de SystemSession.
     */
    public static synchronized SystemSession getInstancia() {
        if (instancia == null) {
            instancia = new SystemSession();
        }
        return instancia;
    }

    private Map<String, Object> var_session;
    /**
     * Empleado DTO que ha iniciado sesión actualmente.
     */
    private EmployeeUserDTO current_employee;

    /**
     * Registro de la administración activa actual (del año en curso).
     */
    private HysAdministrationHistoryDTO current_administration;

    /**
     * Informacion de sesion actual
     */
    private SessionDTO current_session;

    /**
     * usuario principal de base de datos
     */
    private String current_db_user;

    /**
     * Constructor privado que obtiene la conexión a la base de datos al
     * inicializar la única instancia de la sesión.
     */
    private SystemSession() {
        init();
    }

    void init() {
        this.current_administration = null;
        this.current_db_user = null;
        this.current_employee = null;
        this.current_session = null;
        this.var_session = new HashMap<>(30);
    }

    /**
     * Muestra advertencias del sistema, realizando verificaciones importantes
     * que podrian afectar a las funcionalidades del aplicativo
     */
    public void getWarnings() {
        StringBuilder sb = new StringBuilder(255);
        //Permite el paso y ejecucion del funciones
        if (Func.isNullEmptyBlank(current_db_user)) {
            sb.append("El usuario de base de datos no se ha registrado correctamente. No podrá realizar algunos registros.\n");
        }

        //Permite el paso
        if (Func.isNull(current_employee)) {
            sb.append("El usuario no se ha registrado correctamente. No podrá realizar algunos registros.\n");
        }

        //permite el paso
        if (Func.isNull(current_administration)) {
            sb.append("La administración actual no ha sido registrada. No podrá realizar ningún registro administrativo.\n");
        }

        //no permite el paso
        if (Func.isNull(current_session)) {
            sb.append("El objeto de session actual no ha sido registrado correctamente. No podrá realizar ningún registro administrativo.\n");
        }

        if (AppConfig.isAutoPay()) {
            sb.append("Advertencia: El sistema tiene el modo de recargo automático activado.\n");
        }

        if (!sb.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    sb.toString(),
                    "Advertencias del Sistema",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    /**
     * Metodo que verifica una sesion abierta
     *
     * @return true solo si existe un user_db, empleado y objeto de session en
     * la session actual
     */
    @Override
    public boolean isOpen() {
        return current_db_user != null && current_employee != null && current_session != null;
    }

    /**
     * Método placeholder de la interfaz {@code LocalSession}. Actualmente no
     * realiza ninguna acción.
     */
    @Override
    public void writer() {
    }

    public void close() {
        var_session.clear();
        init();
    }

    @Override
    public void setUser(EmployeeUserDTO user) {
        this.current_employee = user;
    }

    public EmployeeUserDTO getCurrentEmployee() {
        return current_employee;
    }

    public void setCurrentEmployee(EmployeeUserDTO current_employee) {
        this.current_employee = current_employee;
    }

    public HysAdministrationHistoryDTO getCurrentAdministration() {
        return current_administration;
    }

    public void setCurrentAdministration(HysAdministrationHistoryDTO current_administration) {
        this.current_administration = current_administration;
    }

    public SessionDTO getCurrentSession() {
        return current_session;
    }

    public void setCurrentSession(SessionDTO current_session) {
        this.current_session = current_session;
    }

    public String getCurrentDbUser() {
        return current_db_user;
    }

    public void setCurrentDbUser(String current_db_user) {
        this.current_db_user = current_db_user;
    }
    
    public void put(String key, Object value){
        var_session.put(key, value);
    }
    
    public Object get(String key){
        return var_session.get(key);
    }

    @Override
    public String toString() {
        return current_db_user;
    }

}
