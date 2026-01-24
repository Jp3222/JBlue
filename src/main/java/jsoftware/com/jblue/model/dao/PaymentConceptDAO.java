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
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class PaymentConceptDAO extends AbstractDAO {

    public PaymentConceptDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public List<String[]> getPaymentConcep(JDBConnection connection, String id_process) throws SQLException {
        List<String[]> res;
        String query = "SELECT id, description, current_price, mandatory_payment FROM pym_payment_concept WHERE module = ? AND status = 1";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, id_process);
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int size_field = md.getColumnCount();
                String[] fields = new String[size_field];
                for (int i = 0; i < size_field; i++) {
                    fields[i] = md.getColumnLabel(i + 1);
                }
                res = new ArrayList<>();
                String[] row;
                int i;
                while (rs.next()) {
                    i = 0;
                    row = new String[size_field];
                    for (String j : fields) {
                        row[i] = rs.getString(j);
                        i++;
                    }
                    res.add(row);
                }
            }
        }
        return res;
    }
}
