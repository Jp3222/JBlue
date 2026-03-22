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
import java.util.Optional;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
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

    public int insert(JDBConnection connection, EmployeeUserDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        boolean res;
        int employee_id = INSERT_DEFAULT_RETURN;
        String query = """
                            INSERT INTO emp_user
                            (employee_id, office_id, user, password, description, 
                            email, phone_number, employee_type, last_employee_update)
                            VALUES(?,?,?,?,?,?,?,?,?,?)
                            """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, dto.getEmployeeId());
            ps.setString(2, dto.getOfficeId());
            ps.setString(3, dto.getUser());
            ps.setString(4, dto.getPassword());
            ps.setString(5, dto.getDescription());
            ps.setString(6, dto.getEmail());
            ps.setString(7, dto.getPhoneNumber());
            ps.setString(8, dto.getEmployeeType());
            ps.setString(9, dto.getLastEmployeeUpdate());
            res = ps.executeUpdate() == 1;
            if (!res) {
                throw new CorruptInsertionException();
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                employee_id = rs.getInt(1);
                dto.put("id", employee_id);
                dto.getMap().put("date_update", Formats.getLocalDateTime(LocalDateTime.now()));
                dto.getMap().put("date_resgiter", Formats.getLocalDateTime(LocalDateTime.now()));
            }
        } catch (SQLException | CorruptInsertionException | KeyNotGenerateException ex) {
            throw ex;
        }
        return employee_id;
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
