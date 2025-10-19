/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.dtos.AdministrationHistoryObject;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jblue.model.dtos.ProcessDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class ProcessDAO {

    private JDBConnection connection;
    private OEmployee current_employee;
    private AdministrationHistoryObject current_admini;
    private String current_db_user;

    public ProcessDAO(JDBConnection connection) {
        this.connection = connection;
        current_employee = SystemSession.getInstancia().getCurrentEmployee();
        current_admini = SystemSession.getInstancia().getCurrentAdministration();
        current_db_user = "";
    }

    public String insertStartProcess(String process_type, String user) throws SQLException {
        String query = "INSERT INTO()";
        return null;
    }

    public String insertValidProcess() {
        return null;
    }

    public List<ProcessDTO> getStartProcedures() {
        ArrayList<ProcessDTO> list = new ArrayList<>(50);
        return list;
    }

    public List<ProcessDTO> getValidProcedures() {
        ArrayList<ProcessDTO> list = new ArrayList<>(50);
        return list;
    }

    public List<ProcessDTO> getPaymentProcedures() {
        ArrayList<ProcessDTO> list = new ArrayList<>(50);
        return list;
    }

    public List<ProcessDTO> getEndProcedures() {
        ArrayList<ProcessDTO> list = new ArrayList<>(50);
        return list;
    }

    public List<ProcessDTO> getPrintProcedures() {
        ArrayList<ProcessDTO> list = new ArrayList<>(50);
        try (PreparedStatement ps = connection.getNewPreparedStatement(getProcedure(5));) {
            ResultSet rs = ps.executeQuery();
            String[] aux = new String[_Const.PRO_PROCESS_TABLE.getFields().length];
            int i;
            while (rs.next()) {
                i = 0;
                for (String j : _Const.PRO_PROCESS_TABLE.getFields()) {
                    aux[i] = j;
                }
            }

        } catch (SQLException ex) {
            System.getLogger(ProcessDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return list;
    }

    public String getProcedure(int status) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("SELECT * FROM pro_process WHERE status != 15");
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
