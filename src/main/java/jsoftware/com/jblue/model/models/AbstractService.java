package jsoftware.com.jblue.model.models;

import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 * Base abstracta para la capa de servicios del framework JBlue. Centraliza la
 * gestión del ciclo de vida transaccional (Commit / Rollback) y provee el
 * estado operativo contextual a las implementaciones concretas.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-07
 * @version 1.1
 */
public abstract class AbstractService implements ServiceModel {

    private static final long serialVersionUID = 1L;

    /**
     * Mensaje de negocio sanitizado para la interfaz gráfica.
     */
    protected String user_message;

    /**
     * Código identificador del resultado o fallo técnico.
     */
    protected int error_code;

    /**
     * Sesión única y centralizada del entorno de ejecución actual.
     */
    protected final SystemSession session;

    /**
     * Bandera de control para activar trazas de depuración en consola.
     */
    private final boolean dev_flag;

    /**
     * Identificador del módulo o caso de uso para el rastro de logs.
     */
    private final String process_name;

    public AbstractService(boolean dev_flag, String process_name) {
        this.user_message = "";
        this.error_code = SERVICE_EXECUTE_OK;
        this.process_name = process_name;
        this.dev_flag = dev_flag;
        // Recuperación segura del Singleton de sesión de JBlue
        this.session = SystemSession.getInstancia();
    }

    /**
     * Revierte de forma segura los cambios realizados en el bloque
     * transaccional activo y restaura el estado operativo de la conexión.
     *
     * * @param connection Wrapper de conexión activa de JBlue.
     */
    public void rollback(JDBConnection connection) {
        if (Func.isNull(connection) || connection.isClose()) {
            returnMessageError("La conexión se cerró inesperadamente antes de revertir los cambios.");
            return;
        }
        connection.rollBack();
    }

    /**
     * Consolida de forma atómica las operaciones pendientes en la base de datos
     * y libera el bloqueo transaccional.
     *
     * * @param connection Wrapper de conexión activa de JBlue.
     */
    public void commit(JDBConnection connection) {
        if (Func.isNull(connection) || connection.isClose()) {
            returnMessageError("No se pudo consolidar la transacción. Conexión nula o cerrada.");
            return;
        }
        connection.commit();
    }

    /**
     * Helper defensivo interno para inyectar estados de error provocados por la
     * red o la infraestructura del servidor local del pozo.
     */
    protected void returnMessageError(String msg) {
        returnMessageError(SERVICE_EXECUTE_ERROR, msg);
    }

    /**
     * Helper defensivo interno para inyectar estados de error provocados por la
     * red o la infraestructura del servidor local del pozo.
     */
    protected void returnMessageError(int error, String msg) {
        this.error_code = error;
        this.user_message = msg;
    }

    /**
     * Restablece el estado del servicio a un estado inicial exitoso. Es crucial
     * llamarlo antes de iniciar una nueva transacción si la instancia del
     * servicio se reutiliza en la misma vista de Swing.
     */
    public void resetServiceState() {
        this.user_message = "";
        this.error_code = SERVICE_EXECUTE_OK;
    }

    @Override
    public String getUserMessage() {
        return user_message;
    }

    @Override
    public int getErrorCode() {
        return error_code;
    }

    public boolean isDev_flag() {
        return dev_flag;
    }

    public String getProcess_name() {
        return process_name;
    }

    @Override
    public boolean isError() {
        // Mapeo directo: Si el código muta del 0 estándar al rango de error, se levanta el semáforo
        return (error_code >= SERVICE_EXECUTE_ERROR) || (error_code > 0);
    }

    protected void log(Exception e, String method_name) {
        FuncLogs.logError(
                AppFiles.DIR_PROG_LOG_TODAY,
                getClass(),
                e,
                process_name,
                method_name,
                e.getMessage()
        );
    }
}
