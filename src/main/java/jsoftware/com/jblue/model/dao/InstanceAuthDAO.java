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
import jsoftware.com.jblue.model.dto.InstanceAuthDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class InstanceAuthDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public InstanceAuthDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public Optional<InstanceAuthDTO> getInstance(JDBConnection connection, String uuid) throws SQLException {
        Optional<InstanceAuthDTO> res = Optional.empty();
        String query = """
                    SELECT 
                        *
                    FROM 
                        sys_instance_auth 
                    WHERE 
                        status = 1 
                        AND uuid = ?
                        AND INSTR(db_user, SUBSTRING_INDEX(CURRENT_USER(), '@', 1)) > 0;
                   """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query);) {
            ps.setString(1, uuid);
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                String[] fields = new String[size];

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = md.getColumnLabel(i + 1);
                }

                while (rs.next()) {
                    InstanceAuthDTO o = new InstanceAuthDTO();
                    for (String field_name : fields) {
                        o.getMap().put(field_name, rs.getString(field_name));
                    }
                    res = Optional.of(o);

                }
            }
        }
        return res;
    }
}
