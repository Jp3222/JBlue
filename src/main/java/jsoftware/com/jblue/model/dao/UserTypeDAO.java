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
import jsoftware.com.jblue.model.dto.UserTypeDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class UserTypeDAO extends AbstractDAO implements ListComponentDAO<UserTypeDTO> {

    private static final long serialVersionUID = 1L;

    public UserTypeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    @Override
    public List<UserTypeDTO> getList(JDBConnection connection) throws SQLException, Exception {
        List<UserTypeDTO> list = new ArrayList<>(0);
        String query = "SELECT * FROM usr_user_type WHERE status = 1";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query); ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();
            String[] fields = new String[md.getColumnCount()];
            for (int i = 0; i < fields.length; i++) {
                fields[i] = md.getColumnLabel(i + 1);
            }
            UserTypeDTO dto;
            list = new ArrayList<>(10);
            while (rs.next()) {
                dto = new UserTypeDTO();
                for (String i : fields) {
                    dto.getMap().put(i, rs.getString(i));
                }
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
