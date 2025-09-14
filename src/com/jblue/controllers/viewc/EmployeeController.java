/*
 * Copyright (C) 2025 juanp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.controllers.viewc;

import com.jblue.controllers.AbstractDBViewController;
import com.jblue.model.constants._Const;
import com.jblue.model.daos.HysHistoryDAO;
import com.jblue.model.factories.CacheFactory;
import com.jblue.model.dtos.OEmployee;
import com.jblue.util.Formats;
import com.jblue.views.EmployeesView;
import com.jutil.dbcon.connection.JDBConnection;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author juanp
 */
public class EmployeeController extends AbstractDBViewController<OEmployee> {

    private final EmployeesView view;

    public EmployeeController(EmployeesView view) {
        super(CacheFactory.EMPLOYEES);
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            case DELETE_COMMAND ->
                delete();
            case UPDATE_COMMAND ->
                update();
            case CANCEL_COMMAND ->
                cancel();
            case "search_object" ->
                searchObject();
            default ->
                defaultCase(ae.getActionCommand(), null, -1);
        }

    }

    @Override
    public void save() {
        System.out.println("Entro al metodo");
        if (!view.isValuesOk()) {
            return;
        }
        System.out.println("Valido");
        Map<String, String> values = view.getValues(false);
        String[] info = Formats.getInsertFormats(values);
        String query = JDBConnection.INSERT_VAL.formatted(
                _Const.USR_USERS_NAME,// NOMBRE DE LA TABLA
                info[0],// CAMPOS
                info[1]// DATOS
        );
        System.out.println("se armaron los valores");
        try (Statement st = connection.getConnection().createStatement();) {
            connection.setAutoCommit(false);
            boolean execute = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) > 0;
            if (!execute) {
                throw new SQLException("UPDATE FALLIDO");
            }
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs == null || !rs.next()) {
                    throw new SQLException("UPDATE FALLIDO");
                }
                String employee_id = rs.getString(1);
                if (employee_id != null) {
                    // Lógica para el historial
                }
                boolean hys = HysHistoryDAO.getINSTANCE().insert(_Const.INDEX_EMP_EMPLOYEES,
                        "SE INSERTO AL EMPLEADO: %s - %s %s %s".formatted(
                                employee_id,
                                values.get("first_name"),
                                values.get("last_name1"),
                                values.get("last_name2")
                        ));
                if (!hys) {
                    throw new SQLException("UPDATE FALLIDO");
                }
            }
            connection.commit();
            returnMessage(view, true);
        } catch (SQLException ex) {
            connection.rollBack();
            returnMessage(view, false);
            ex.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void delete() {
        OEmployee employee = view.getObjectSearch();
        if (employee == null) {
            return;
        }
        connection.setAutoCommit(false);
        boolean update = connection.update("status", "3", "id = %s".formatted(employee.getId()));
        if (!update) {
            connection.rollBack();
            connection.setAutoCommit(true);
            returnMessage(view, false);
            return;
        }
        boolean hys = HysHistoryDAO.getINSTANCE().deleteToUsers(
                "SE OCULTO AL EMPLEADO: %s - %s %s %s".formatted(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName1(),
                        employee.getLastName2()
                )
        );
        if (!hys) {
            connection.rollBack();
            connection.setAutoCommit(true);
            returnMessage(view, false);
            return;
        }
        connection.commit();
    }

    @Override
    public void update() {
        try {
            OEmployee employee = view.getObjectSearch();
            Map<String, String> values = view.getValues(true);
            String updateFormats = Formats.getUpdateFormats(values);
            connection.setAutoCommit(false);
            boolean update = connection.update(updateFormats, "id = %s".formatted(employee.getId()));
            if (!update) {
                throw new SQLException("UPDATE FALLIDO");
            }
            boolean hys = HysHistoryDAO.getINSTANCE().updateToUsers(
                    "SE ACTUALIZO EL USUARIO: %s - %s %s %s".formatted(
                            employee.getId(),
                            employee.getFirstName(),
                            employee.getLastName1(),
                            employee.getLastName2()
                    ));
            if (!hys) {
                throw new SQLException("INSETR EN EL HISTORIAL FALLIDO");
            }
            connection.commit();
            returnMessage(view, true);
        } catch (SQLException e) {
            connection.rollBack();
            returnMessage(view, false);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void cancel() {
        int in = JOptionPane.showConfirmDialog(view,
                "¿Estas seguro de cancelar esta operacion?",
                "Cancelar Operacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (in == JOptionPane.YES_OPTION) {
            view.initialState();
        }
    }

    private void searchObject() {
        OEmployee objectSearch = view.getObjectSearch();
    }

}
