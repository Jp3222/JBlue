/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.List;
import jsoftware.com.jblue.model.dto.StatusDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public final class StatusDAO extends AbstractDAO implements TableComponentDAO<StatusDTO> {

    private static final long serialVersionUID = 1L;

    public StatusDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    @Override
    public List<StatusDTO> getList(JDBConnection connection, JTableModel model) {
        String query = "SELECT * FROM cat_status";
        try (PreparedStatement ps = connection.getNewCallableStatement(query); ResultSet rs = ps.executeQuery();) {
            ResultSetMetaData md = rs.getMetaData();
            String[] fields = new String[md.getColumnCount()];
            for (int i = 0; i < fields.length; i++) {
                fields[i] = md.getColumnLabel(i + 1);
            }
            model = new JTableModel(fields, 0);
            String[] arr_aux;
            int i;
            while (rs.next()) {
                i = 0;
                arr_aux = new String[fields.length];
                for (String j : fields) {
                    arr_aux[i] = rs.getString(j);
                    i++;
                }
                System.out.println(Arrays.toString(arr_aux));
                model.addRow(arr_aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
