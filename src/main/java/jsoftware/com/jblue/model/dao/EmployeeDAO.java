/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Data Access Object (DAO) para la gestión de la tabla emp_employee.
 */
public class EmployeeDAO extends AbstractDAO {

    public EmployeeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public int insert(JDBConnection connection, EmployeeDTO dto) throws SQLException {
        int res = INSERT_DEFAULT_RETURN;
        String query = """
                       INSERT INTO emp_employee
                       (curp, first_name, last_name1, last_name2, gender, birthdate, 
                       personal_email, personal_phone, 
                       street1, street2, inside_number, outside_number, 
                       employee_type, status, 
                       user, password
                       )
                       VALUES
                       ()
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

        }
        return res;
    }

    public boolean update(JDBConnection connection, EmployeeDTO dto) {
        boolean res = UPDATE_DEFAULT_RETURN;

        return res;
    }

    public EmployeeDTO get(JDBConnection connection, String user, String password) {
        return null;
    }

}
