/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import jsoftware.com.jblue.util.Filters;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class ProcessDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public static int STATUS_INICIADO = 10;
    public static int STATUS_VALIDADO = 11;
    public static int STATUS_PAGADO = 12;
    public static int STATUS_FINALIZADO = 13;
    public static int STATUS_CADUCADO = 14;
    public static int STATUS_CANCELADO = 5;

    private final EmployeeUserDTO current_employee;
    private final AdministrationHistoryDTO current_admin;

    public ProcessDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
        current_employee = SystemSession.getInstancia().getCurrentEmployee();
        current_admin = SystemSession.getInstancia().getCurrentAdministration();
    }

    public int startProcess(JDBConnection connection, String process_type, String user_id) throws SQLException {
        int generatedId = 0; // Cambiamos el retorno a int para devolver el ID
        if (connection == null) {
            throw new SQLException("CONEXIÓN NO DISPONIBLE");
        }
        if (Filters.isNullOrBlank(process_type, user_id)) {
            throw new IllegalArgumentException("Parámetros erróneos: process_type: " + process_type + ", user: " + user_id);
        }

        // 1. IMPORTANTE: Solicitar que se retornen las llaves generadas
        try (PreparedStatement ps = connection.getNewPreparedStatement(
                ProcessQuery.INSERT_START_PROCESS,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, process_type);
            ps.setString(2, current_employee.getId());
            ps.setString(3, current_admin.getId());
            ps.setString(4, current_employee.getId());
            ps.setString(5, user_id);
            ps.setInt(6, STATUS_INICIADO);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                // 2. Recuperar el conjunto de llaves generadas
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1); // Obtenemos el ID generado
                    }
                }
            } else {
                throw new SQLException("TRAMITE NO INICIADO: No se insertó ningún registro.");
            }
        }
        return generatedId; // Retorna el ID (o 0 si falló)
    }

    public boolean validProcess(JDBConnection connection, int process_id) throws SQLException, ProcessException {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_VALID);) {
            ps.setString(1, current_employee.getId());
            ps.setString(2, current_employee.getId());
            ps.setInt(3, STATUS_VALIDADO);
            ps.setInt(4, process_id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            return rt;
        }
    }

    public boolean payProcess(JDBConnection connection, String id) throws SQLException, ProcessException {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_PAYMENT);) {
            ps.setString(1, current_employee.getId());
            ps.setString(2, current_admin.getId());
            ps.setString(3, current_employee.getId());
            ps.setInt(4, STATUS_PAGADO);
            ps.setString(5, id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            return rt;
        }
    }

    public boolean endProcess(JDBConnection connection, String user_id, String water_inatke_id) throws SQLException, ProcessException {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_END);) {
            ps.setString(1, current_employee.getId());
            ps.setString(2, current_admin.getId());
            ps.setString(3, current_employee.getId());
            ps.setInt(4, STATUS_FINALIZADO);
            ps.setString(5, user_id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            return rt;
        }
    }

    /**
     * se lanza si el usuario pride su tarjeta fisica
     *
     * @param connection
     * @param id
     * @return
     */
    public boolean printProcess(JDBConnection connection, String id) throws SQLException, ProcessException {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_PRINT);) {
            ps.setString(1, current_employee.getId());
            ps.setString(2, current_employee.getId());
            ps.setString(3, id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            return rt;
        }
    }

    /**
     * se lanza si el empleado decide cancelar el tramite, no se ha validado o
     * no se ha realizado un pago dentro de los proximos 30 dias
     *
     * @param connection
     * @param payment_id
     * @return
     */
    public boolean cancelProcess(JDBConnection connection, String payment_id) throws SQLException, ProcessException {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_CANCEL);) {
            ps.setString(1, current_employee.getId());
            ps.setInt(2, STATUS_CANCELADO);
            ps.setString(3, payment_id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            return rt;
        }
    }

    /**
     * Se lanza cuando han transcurrido 30 dias despues de inicial el tramite
     *
     * @param connection
     * @param payment_id
     * @return
     */
    public boolean caducateProcess(JDBConnection connection, String payment_id) throws SQLException, ProcessException {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_CADUCATE);) {
            ps.setString(1, current_employee.getId());
            ps.setInt(2, STATUS_CADUCADO);
            ps.setString(3, payment_id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            return rt;
        }
    }

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

    public List<ProcessDTO> getProcess(JDBConnection connection, int status) throws SQLException {
        ArrayList<ProcessDTO> list = new ArrayList<>(50);
        String query = "SELECT * FROM pro_process WHERE status = ?";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query);) {
            ps.setInt(1, status);
            try (ResultSet rs = ps.executeQuery();) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                while (rs.next()) {
                    ProcessDTO process = new ProcessDTO(size);
                    for (int i = 1; i <= size; i++) {
                        process.getMap().put(
                                md.getColumnLabel(i),
                                rs.getString(md.getColumnLabel(i))
                        );
                    }
                    list.add(process);
                }
            }
        }
        return list;
    }

}
