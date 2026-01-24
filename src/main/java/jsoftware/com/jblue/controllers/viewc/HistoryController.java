/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import jsoftware.com.jblue.controllers.AbstractViewController;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.HistoryView;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public class HistoryController extends AbstractViewController {

    private static final long serialVersionUID = 1L;

    private final HistoryView view;

    public HistoryController(HistoryView view) {
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "load_history" ->
                load();
            default ->
                throw new AssertionError();
        }
    }

    private void load() {
        try (JDBConnection connection = ConnectionFactory.getIntance().getHistoryConnection()) {
            load(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load(JDBConnection connection) {
        connection.setAutoCommit(false);
        EmployeeDTO dto = SystemSession.getInstancia().getCurrentEmployee();
        try {
            boolean res = HysHistoryDAO.getINSTANCE().select(
                    Const.INDEX_HYS_PROGRAM_HISTORY,
                    "EL EMPLEADO: %s %s %s CONSULTO EL HISTORIAL".formatted(
                            dto.getFirstName(),
                            dto.getLastName1(),
                            dto.getLastName2()
                    )
            );
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        String query = "SELECT * FROM HISTORIAL_DE_MOVIMIENTOS ORDER BY id DESC";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query); ResultSet rs = ps.executeQuery();) {
            ResultSetMetaData md = rs.getMetaData();
            String[] col = new String[md.getColumnCount()];
            for (int i = 1; i <= md.getColumnCount(); i++) {
                col[i - 1] = md.getColumnLabel(i);
            }
            JTableModel tm = new JTableModel(col, 0);
            String[] row;
            int i;
            while (rs.next()) {
                row = new String[col.length];
                i = 0;
                for (String j : col) {
                    row[i] = rs.getString(j);
                    i++;
                }
                tm.addRow(row);
            }
            view.getObjectsTable().setModel(tm);
        } catch (Exception e) {
        }
    }

}
