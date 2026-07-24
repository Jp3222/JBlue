package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.ProcessDTO;
import jsoftware.com.jblue.model.exp.ProcessException;
import jsoftware.com.jblue.model.querys.ProcessQuery;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Data Access Object (DAO) para el control evolutivo de trámites operativos.
 * <br>
 * Administra de forma secuencial las fases y estados del Workflow de JBlue.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 * @version 1.1
 */
public class ProcessDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    // Constantes de Estado del Workflow mapeadas en cat_status
    public static final int STATUS_INICIADO = 10;
    public static final int STATUS_VALIDADO = 11;
    public static final int STATUS_PAGADO = 12;
    public static final int STATUS_FINALIZADO = 13;
    public static final int STATUS_CADUCADO = 14;
    public static final int STATUS_CANCELADO = 5;

    private final EmployeeUserDTO current_employee;
    private final AdministrationHistoryDTO current_admin;

    public ProcessDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
        this.current_employee = SystemSession.getInstancia().getCurrentEmployee();
        this.current_admin = SystemSession.getInstancia().getCurrentAdministration();
    }

    /**
     * Fase [1]: Captura de Datos. Registra el inicio de un trámite en
     * ventanilla.
     */
    public int startProcess(JDBConnection connection, ProcessDTO dto) throws SQLException {
        int generatedId = 0;
        String query = """
                       INSERT INTO pro_process
                       (process_type, sequence_process, employee_start, administration_start, current_db_user, status, last_employee_update) 
                       VALUES
                       (?, ?, ?, ?, ?, CURRENT_USER, 10, ?)
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, Integer.parseInt(dto.getProcessType()));
            ps.setInt(2, Integer.parseInt(dto.getSequenceProcess()));
            ps.setInt(3, Integer.parseInt(dto.getEmployeeStart()));
            ps.setInt(4, Integer.parseInt(dto.getAdministrationStart()));
            ps.setInt(5, Integer.parseInt(dto.getDateStart()));
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            } else {
                throw new SQLException("ERROR TRANSACCIONAL: Trámite no iniciado en base de datos.");
            }
        }
        return generatedId;
    }

    public boolean userRegister(JDBConnection connection, ProcessDTO dto) throws SQLException, ProcessException {
        String query = """
                        UPDATE pro_process SET
                            current_db_user = ?,
                            last_employee_update = ?
                        WHERE id = ?
                       
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, dto.getCurrentDbUser());
            ps.setString(2, dto.getLastEmployeeUpdate());
            ps.setString(3, dto.getId());
            boolean rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "ERROR EN VALIDACIÓN: El trámite ID " + dto.getId() + " no existe o no pudo mutar.");
            }
            return rt;
        }
    }

    /**
     * Fase [2]: Validación Documental.
     */
    public boolean validProcess(JDBConnection connection, int id, String document_id) throws SQLException, ProcessException {
        String query = """
                        UPDATE pro_process SET
                            user_id = ?, 
                            current_db_user = ?,
                            last_employee_update = ?}
                        WHERE id = ?
                       
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_VALID)) {
            ps.setInt(1, Integer.parseInt(current_employee.getId()));
            ps.setInt(2, Integer.parseInt(document_id));
            ps.setInt(3, Integer.parseInt(current_employee.getId()));
            ps.setInt(4, STATUS_VALIDADO);
            ps.setInt(5, id);

            boolean rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "ERROR EN VALIDACIÓN: El trámite ID " + id + " no existe o no pudo mutar.");
            }
            return rt;
        }
    }

    /**
     * Fase [2]: Validación Documental.
     */
    public boolean waterIntakeRegister(JDBConnection connection, int id, String document_id) throws SQLException, ProcessException {
        String query = """
                        UPDATE pro_process SET
                            user_id = ?, 
                            current_db_user = ?,
                            last_employee_update = ?}
                        WHERE id = ?
                       
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, Integer.parseInt(current_employee.getId()));
            ps.setInt(2, Integer.parseInt(document_id));
            ps.setInt(3, Integer.parseInt(current_employee.getId()));
            ps.setInt(4, STATUS_VALIDADO);
            ps.setInt(5, id);

            boolean rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "ERROR EN VALIDACIÓN: El trámite ID " + id + " no existe o no pudo mutar.");
            }
            return rt;
        }
    }

    /**
     * Fase [4]: Registro de Pago en Cajas.
     */
    public boolean payProcess(JDBConnection connection, String payment_id) throws SQLException, ProcessException {
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_PAYMENT)) {
            ps.setInt(1, Integer.parseInt(current_employee.getId()));
            ps.setInt(2, Integer.parseInt(payment_id));
            ps.setInt(3, Integer.parseInt(current_employee.getId()));
            ps.setInt(4, STATUS_PAGADO);
            //ps.setInt(5, id);

            boolean rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(2, "ERROR EN CAJAS: No se pudo registrar el pago para el trámite ID ");
            }
            return rt;
        }
    }

    /**
     * Fase [5]: Impresión de Comprobante / Título de Concesión.
     */
    public boolean printProcess(JDBConnection connection, int id) throws SQLException, ProcessException {
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_PRINT)) {
            ps.setInt(1, Integer.parseInt(current_employee.getId()));
            ps.setInt(2, Integer.parseInt(current_employee.getId()));
            ps.setInt(3, id);

            boolean rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(3, "ERROR DE EMISIÓN: No se pudo firmar la impresión del trámite ID " + id);
            }
            return rt;
        }
    }

    /**
     * Fase [6]: Finalización e incorporación definitiva al Padrón Activo.
     */
    public boolean endProcess(JDBConnection connection, int id, String wki_user_id) throws SQLException, ProcessException {
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_END)) {
            ps.setInt(1, Integer.parseInt(current_employee.getId()));
            ps.setInt(2, Integer.parseInt(wki_user_id));
            ps.setInt(3, Integer.parseInt(current_admin.getId()));
            ps.setInt(4, Integer.parseInt(current_employee.getId()));
            ps.setInt(5, STATUS_FINALIZADO);
            ps.setInt(6, id);

            boolean rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(4, "ERROR DE CIERRE: No se pudo consolidar el alta en el padrón para el trámite ID " + id);
            }
            return rt;
        }
    }

    /**
     * Cancelación explícita del trámite por dictamen o desistimiento del
     * ciudadano.
     */
    public boolean cancelProcess(JDBConnection connection, int id) throws SQLException, ProcessException {
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_CANCEL)) {
            ps.setInt(1, Integer.parseInt(current_employee.getId()));
            ps.setInt(2, STATUS_CANCELADO);
            ps.setInt(3, id);

            boolean rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(5, "ERROR EN ABORTO: No se pudo cancelar el trámite ID " + id);
            }
            return rt;
        }
    }

    /**
     * Cierre automático por caducidad (Expiración del plazo de 30 días).
     */
    public boolean caducateProcess(JDBConnection connection, int id) throws SQLException, ProcessException {
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_CADUCATE)) {
            ps.setInt(1, Integer.parseInt(current_employee.getId()));
            ps.setInt(2, STATUS_CADUCADO);
            ps.setInt(3, id);

            boolean rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(6, "ERROR EN VENCIMIENTO: No se pudo caducar el trámite ID " + id);
            }
            return rt;
        }
    }

    // --- MÉTODOS DE FILTRADO Y CONSULTAS ---
    public List<ProcessDTO> getStartProcedures(JDBConnection connection) throws SQLException {
        return getProcess(connection, STATUS_INICIADO);
    }

    public List<ProcessDTO> getValidProcedures(JDBConnection connection) throws SQLException {
        return getProcess(connection, STATUS_VALIDADO);
    }

    public List<ProcessDTO> getPaymentProcedures(JDBConnection connection) throws SQLException {
        return getProcess(connection, STATUS_PAGADO);
    }

    public List<ProcessDTO> getEndProcedures(JDBConnection connection) throws SQLException {
        return getProcess(connection, STATUS_FINALIZADO);
    }

    public List<ProcessDTO> getCaducateProcedures(JDBConnection connection) throws SQLException {
        return getProcess(connection, STATUS_CADUCADO);
    }

    public List<ProcessDTO> getCanceledProcedures(JDBConnection connection) throws SQLException {
        return getProcess(connection, STATUS_CANCELADO);
    }

    /**
     * Recupera y mapea de manera dinámica la lista de trámites filtrados por su
     * estatus actual.
     */
    public List<ProcessDTO> getProcess(JDBConnection connection, int status) throws SQLException {
        List<ProcessDTO> list = new ArrayList<>(50);
        String query = "SELECT * FROM pro_process WHERE status = ?";

        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();

                while (rs.next()) {
                    // CORRECCIÓN: Se remueve el parámetro 'size' del constructor. 
                    // El DTO administra su propio mapa interno de capacidad fija 40.
                    ProcessDTO process = new ProcessDTO();
                    for (int i = 1; i <= size; i++) {
                        String label = md.getColumnLabel(i);
                        process.put(label, rs.getString(label));
                    }
                    list.add(process);
                }
            }
        }
        return list;
    }
}
