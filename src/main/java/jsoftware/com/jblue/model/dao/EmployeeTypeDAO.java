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
import jsoftware.com.jblue.model.dto.EmployeeTypesDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class EmployeeTypeDAO extends AbstractDAO implements ListComponentDAO<EmployeeTypesDTO> {

    private static final long serialVersionUID = 1L;

    public EmployeeTypeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    @Override
    public List<EmployeeTypesDTO> getList(JDBConnection connection) throws SQLException, Exception {
        List<EmployeeTypesDTO> list = List.of();
        String query = "SELECT * FROM emp_employee_type WHERE status = 1";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query); ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();
            String[] fields = new String[md.getColumnCount()];
            for (int i = 0; i < fields.length; i++) {
                fields[i] = md.getColumnLabel(i + 1);
            }
            EmployeeTypesDTO dto;
            list = new ArrayList<>(10);
            while (rs.next()) {
                dto = new EmployeeTypesDTO();
                for (String i : fields) {
                    dto.getMap().put(i, rs.getString(i));
                }
                list.add(dto);
            }
        }
        return list;
    }

}
