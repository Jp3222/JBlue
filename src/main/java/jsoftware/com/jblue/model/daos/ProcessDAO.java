/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.dtos.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jblue.model.dtos.ProcessDTO;
import jsoftware.com.jblue.model.exp.ProcessException;
import jsoftware.com.jblue.model.querys.ProcessQuery;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.util.Filters;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class ProcessDAO {

    private final JDBConnection connection;
    private final OEmployee current_employee;
    private final HysAdministrationHistoryDTO current_admin;
    private final String current_db_user;

    public ProcessDAO(JDBConnection connection) {
        this.connection = connection;
        current_employee = SystemSession.getInstancia().getCurrentEmployee();
        current_admin = SystemSession.getInstancia().getCurrentAdministration();
        current_db_user = "";
    }

    public boolean startProcess(String process_type, String user) throws SQLException {
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

    public boolean validProcess(String id) {
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

    public boolean payProcess(String id) {
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

    public boolean endProcess(String id) {
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

    public boolean printProcess(String id) {
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

    public List<ProcessDTO> getStartProcedures() {
        return readProcess(1);
    }

    public List<ProcessDTO> getValidProcedures() {
        return readProcess(2);
    }

    public List<ProcessDTO> getPaymentProcedures() {
        return readProcess(3);
    }

    public List<ProcessDTO> getEndProcedures() {
        return readProcess(4);
    }

    public List<ProcessDTO> getPrintProcedures() {
        return readProcess(5);
    }

    public List<ProcessDTO> readProcess(int status) {
        ArrayList<ProcessDTO> list = new ArrayList<>(50);
        try (PreparedStatement ps = connection.getNewPreparedStatement(queryProcess(status));) {
            ResultSet rs = ps.executeQuery();
            String[] aux = new String[_Const.PRO_PROCESS_TABLE.getFields().length];
            int i;
            ProcessDTO o;
            while (rs.next()) {
                i = 0;
                for (String j : _Const.PRO_PROCESS_TABLE.getFields()) {
                    aux[i] = j;
                }
                o = new ProcessDTO(aux);
                list.add(o);
            }
        } catch (SQLException ex) {
            System.getLogger(ProcessDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return list;
    }

    public String queryProcess(int status) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("SELECT * FROM pro_process WHERE status != 15 ");
        switch (status) {
            case 1 ->
                sb.append("AND employee_start != NULL AND date_start != NULL");
            case 2 ->
                sb.append("AND employee_valid != NULL AND date_valid != NULL");
            case 3 ->
                sb.append("AND employee_payment != NULL AND date_payment != NULL");
            case 4 ->
                sb.append("AND employee_ends != NULL AND date_end != NULL");
            case 5 ->
                sb.append("AND employee_print != NULL AND date_print != NULL");
            default ->
                throw new AssertionError();
        }
        return sb.toString();
    }
}
