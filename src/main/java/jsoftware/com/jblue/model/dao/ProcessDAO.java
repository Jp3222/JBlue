/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.ProcessDTO;
import jsoftware.com.jblue.model.exp.ProcessException;
import jsoftware.com.jblue.model.querys.ProcessQuery;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.util.Filters;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.jexception.JExcp;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class ProcessDAO extends AbstractDAO {

    private final EmployeeDTO current_employee;
    private final HysAdministrationHistoryDTO current_admin;
    private final String current_db_user;

    public ProcessDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
        current_employee = SystemSession.getInstancia().getCurrentEmployee();
        current_admin = SystemSession.getInstancia().getCurrentAdministration();
        current_db_user = null;
    }

    public boolean startProcess(JDBConnection connection, String process_type, String user) throws SQLException {
        boolean rt = false;
        if (connection == null) {
            throw new SQLException("TRAMITE NO INICIADO");
        }
        if (Filters.isNullOrBlank(process_type, user)) {
            throw new IllegalArgumentException("Paramentros erroneos: process_type: " + process_type + ", user: " + user);
        }
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.INSERT_START_PROCESS);) {
            connection.setAutoCommit(false);
            ps.setString(1, process_type);
            ps.setString(2, current_employee.getId());
            ps.setString(3, current_admin.getId());
            ps.setString(4, current_employee.getId());
            ps.setString(5, current_db_user);
            ps.setString(6, user);
            ps.setString(7, "9");
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            connection.commit();
            return rt;
        } catch (ProcessException ex) {
            System.getLogger(ProcessDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (Exception ex) {
            System.getLogger(ProcessDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            connection.setAutoCommit(true);
        }
        return rt;
    }

    public boolean validProcess(JDBConnection connection, String id) {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_VALID);) {
            LocalDateTime ld = LocalDateTime.now();
            connection.setAutoCommit(false);
            ps.setString(1, current_employee.getId());
            ps.setString(2, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            ps.setString(3, current_employee.getId());
            ps.setString(4, current_db_user);
            ps.setString(5, "10");
            ps.setString(6, id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            connection.commit();
            return rt;
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return rt;
    }

    public boolean payProcess(JDBConnection connection, String id) {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_PAYMENT);) {
            LocalDateTime ld = LocalDateTime.now();
            connection.setAutoCommit(false);
            ps.setString(1, current_employee.getId());
            ps.setString(2, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            ps.setString(3, current_employee.getId());
            ps.setString(4, current_db_user);
            ps.setString(5, "10");
            ps.setString(6, id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            connection.commit();
            return rt;
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return rt;
    }

    public boolean endProcess(JDBConnection connection, String id) {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_END);) {
            LocalDateTime ld = LocalDateTime.now();
            connection.setAutoCommit(false);
            ps.setString(1, current_employee.getId());
            ps.setString(2, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            ps.setString(3, current_employee.getId());
            ps.setString(4, current_db_user);
            ps.setString(5, "10");
            ps.setString(6, id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            connection.commit();
            return rt;
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return rt;
    }

    public boolean printProcess(JDBConnection connection, String id) {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(ProcessQuery.UPDATE_PROCESS_PRINT);) {
            LocalDateTime ld = LocalDateTime.now();
            connection.setAutoCommit(false);
            ps.setString(1, current_employee.getId());
            ps.setString(2, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            ps.setString(3, current_employee.getId());
            ps.setString(4, current_db_user);
            ps.setString(5, "10");
            ps.setString(6, id);
            rt = ps.executeUpdate() > 0;
            if (!rt) {
                throw new ProcessException(1, "TRAMITE NO INICIADO");
            }
            connection.commit();
            return rt;
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return rt;
    }

    public List<ProcessDTO> getStartProcedures(JDBConnection connection) {
        return getProcess(connection, 10);
    }

    public List<ProcessDTO> getValidProcedures(JDBConnection connection) {
        return getProcess(connection, 11);
    }

    public List<ProcessDTO> getPaymentProcedures(JDBConnection connection) {
        return getProcess(connection, 12);
    }

    public List<ProcessDTO> getEndProcedures(JDBConnection connection) {
        return getProcess(connection, 13);
    }

    public List<ProcessDTO> getPrintProcedures(JDBConnection connection) {
        return getProcess(connection, 14);
    }

    public List<ProcessDTO> getProcess(JDBConnection connection, int status) {
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
        } catch (SQLException ex) {
            JExcp.getInstance(false, true).print(ex, getClass(), "getProcess");
        }
        return list;
    }

}
