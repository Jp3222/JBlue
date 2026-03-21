/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Optional;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class EmployeeUserDAO extends AbstractDAO {

    public EmployeeUserDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public Optional<EmployeeUserDTO> get(JDBConnection connection, String password, String user) throws SQLException {
        Optional<EmployeeUserDTO> p = Optional.empty();
        String query = "SELECT * FROM emp_user WHERE user = ? AND password = ? AND status = 1";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, user);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery();) {
                if (!rs.next()) {
                    return p;
                }
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                String[] fields = new String[size];
                for (int i = 0; i < size; i++) {
                    fields[i] = md.getColumnLabel(i + 1);
                }

                EmployeeUserDTO dto = new EmployeeUserDTO();
                for (String i : fields) {
                    dto.put(i.toLowerCase(), rs.getString(i));
                }
                p = Optional.of(dto);
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return p;
    }
}
