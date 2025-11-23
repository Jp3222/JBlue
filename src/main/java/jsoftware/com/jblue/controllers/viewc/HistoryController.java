/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsoftware.com.jblue.controllers.AbstractViewController;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.HistoryView;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.jexception.JExcp;

/**
 *
 * @author juanp
 */
public class HistoryController extends AbstractViewController {

    private HistoryView view;
    private JDBConnection connection;

    public HistoryController(HistoryView view) {
        this.view = view;
        this.connection = JDBConnection.getInstance();
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
        connection.setAutoCommit(false);
        try {
            EmployeeDTO currentEmployee = SystemSession.getInstancia().getCurrentEmployee();
            boolean insert = HysHistoryDAO.getINSTANCE().SELECT(Const.INDEX_HYS_PROGRAM_HISTORY,
                    "EL USUARIO %s - %s %s %s CONSULTO EL HISTORIAL".formatted(
                            currentEmployee.getId(),
                            currentEmployee.getFirstName(),
                            currentEmployee.getLastName1(),
                            currentEmployee.getLastName2()
                    )
            );
            if (!insert) {
                throw new SQLException("Registro en bitacora corrupto");
            }
            connection.commit();
            //
            String query = """
                        SELECT
                            TRIM(CONCAT(
                                    em.first_name, ' ',
                                    COALESCE(em.last_name1, ''), ' ',
                                    COALESCE(em.last_name2, '')
                            )) AS EMPLOYEE_NAME, 
                            ty.description AS Movement_Type_Description,
                            his.description AS History_Description,
                            his.date_register
                        FROM hys_program_history his
                        INNER JOIN cat_history_type_mov ty ON ty.id = his.type_mov
                        INNER JOIN emp_employees em ON em.id = his.employee
                        WHERE his.id BETWEEN 1 AND 1000
                        ORDER BY his.date_register DESC;
                        """;
            ResultSet rs = connection.query(query);
            if (rs == null) {
                throw new SQLException("Error al consultar");
            }
            while (rs.next()) {
                view.getModel().addRow(new Object[]{
                    rs.getString(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                });
            }
        } catch (SQLException e) {
            connection.rollBack();
            JExcp.getInstance(false, true).print(e, getClass(), "load");
        } finally {
            connection.setAutoCommit(true);
        }

    }

}
