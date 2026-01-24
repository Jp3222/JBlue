package jsoftware.com.jblue.sys;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.model.dao.AdministrationHistoryDAO;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.jexception.JExcp;
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
public class SystemSession implements LocalSession<EmployeeDTO> {

    private static SystemSession instancia;

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

    /**
     * Empleado DTO que ha iniciado sesión actualmente.
     */
    private EmployeeDTO current_employee;
    /**
     * Registro de la administración activa actual (del año en curso).
     */
    private HysAdministrationHistoryDTO current_administration;

    private String current_db_user;

    /**
     * Constructor privado que obtiene la conexión a la base de datos al
     * inicializar la única instancia de la sesión.
     */
    private SystemSession() {
    }

    /**
     * Obtiene el DTO del empleado actualmente autenticado.
     *
     * @return El DTO del empleado en sesión, o {@code null} si no hay sesión
     * abierta.
     */
    public EmployeeDTO getCurrentEmployee() {
        return current_employee;
    }

    /**
     * Obtiene el registro de la administración activa (presidente, tesorero,
     * etc.) para el contexto actual.
     *
     * @return El DTO de la historia de administración.
     */
    public HysAdministrationHistoryDTO getCurrentAdministration() {
        return current_administration;
    }

    public String getCurrentDbUser() {
        return current_db_user;
    }

    /**
     * Muestra advertencias críticas del sistema mediante un cuadro de diálogo
     * (JOptionPane). Verifica el estado del empleado, la administración y la
     * configuración de pagos automáticos.
     */
    public void getWarnings() {
        StringBuilder sb = new StringBuilder(255);

        if (getCurrentEmployee() == null) {
            sb.append("El usuario no se ha registrado correctamente. No podrá realizar algunos registros.\n");
        }

        if (getCurrentAdministration() == null) {
            sb.append("La administración actual no ha sido registrada. No podrá realizar ningún registro administrativo.\n");
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
     * {@inheritDoc} Verifica si la sesión está abierta (si existe un empleado
     * autenticado).
     *
     * @return {@code true} si {@code current_employee} no es nulo.
     */
    @Override
    public boolean isOpen() {
        return current_employee != null;
    }

    /**
     * Método placeholder de la interfaz {@code LocalSession}. Actualmente no
     * realiza ninguna acción.
     */
    @Override
    public void writer() {
        // Método sin implementar/funcionalidad
    }

    /**
     * Inicia o cierra una sesión de empleado, registrando la acción en la
     * bitácora de movimientos del empleado de forma transaccional.
     *
     * @param user El DTO del empleado que inicia sesión, o {@code null} para
     * cerrar sesión.
     * @throws RuntimeException Si el registro en la bitácora o la actualización
     * de estado falla.
     */
    @Override
    public void setUser(EmployeeDTO user) {
        // Deshabilita el auto-commit para manejar la transacción manualmente.
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection()) {
            saveSession(connection, user);
        } catch (SQLException e) {

        }
    }

    private void saveSession(JDBConnection connection, EmployeeDTO user) {
        boolean res;
        try {
            connection.setAutoCommit(false);
            String description = user == null ? "FIN DE SESION" : "INICIO DE SESIÓN";
            if (user == null) {
                // Registro de salida
                res = HysHistoryDAO.getINSTANCE().getHysEmployeeMovs().saveLogOut(current_employee, description);
                //las variables de sesion se quitan
                current_employee = null;
                current_administration = null;
                current_db_user = null;
            } else {
                // Registro de entrada
                res = HysHistoryDAO.getINSTANCE().getHysEmployeeMovs().saveLogin(user, description);
                current_employee = user;
                //Obtiene el contexto administrativo tras un login exitoso.
                current_administration = new AdministrationHistoryDAO().getCurrentAdministration(connection);
                //Usuario de base de datos actual
                current_db_user = HysHistoryDAO.getINSTANCE().currentUser(connection);
            }
            if (!res) {
                throw new SQLException("El registro de auditoría en bitácora ha fallado o fue corrupto.");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
            JExcp.getInstance(false, true).print(e, getClass(), "setUser");

        } finally {
            // Siempre restaurar el modo AutoCommit.
            connection.setAutoCommit(true);
        }
    }

    /**
     * Método placeholder para la lógica de carga de la sesión. Actualmente no
     * implementado.
     */
    public void load() {
        // Lógica de precarga de sesión si es necesario
    }

}
