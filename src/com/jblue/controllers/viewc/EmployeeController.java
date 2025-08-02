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
import com.jblue.model.constants.Const;
import com.jblue.model.JDBConnection;
import com.jblue.model.factories.CacheFactory;
import com.jblue.model.dtos.OEmployee;
import com.jblue.sys.Session;
import com.jblue.views.EmployeeView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author juanp
 */
public class EmployeeController extends AbstractDBViewController<OEmployee> {

    private final EmployeeView view;
    private final JDBConnection<OEmployee> connection;

    public EmployeeController(EmployeeView view) {
        super(CacheFactory.EMPLOYEES);
        this.view = view;
        this.connection = (JDBConnection<OEmployee>) memo_cache.getConnection();
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
        String fields = "first_name, last_names, employee_type, user, password";
        String values[] = view.getDbValues(false);
        boolean res = connection.insert(fields, values);
        if (res) {
            Session.getInstancia().register(Const.INSERT_TO_EMPLOYEES, "id:%s, employee:%s_%s".formatted(
                    memo_cache.count() + 1,
                    values[0], values[1]
            ));
        }
        rmessage(view, res);
    }

    @Override
    public void delete() {
        if (!view.isValuesOk()) {
            return;
        }
        //boolean delete = connection.delete("id = %s".formatted(view.getObjectSearch().getId()));
        String id = view.getObjectSearch().getId();
        boolean delete = connection.update("status", "3", "id = %s".formatted(id));
        if (delete) {
            Session.getInstancia().register(Const.LOGIC_DELETE_TO_EMPLOYEES, "id:%s".formatted(id));
        }
//        if (DevFlags.TST_EXE_FUNCION) {
//            int hidden_payments = JOptionPane.showConfirmDialog(view, "¿Desea eliminar los pagos hechos por esta persona?");
//            if (hidden_payments == JOptionPane.YES_OPTION) {
//                try {
//                    connection.getConnection().update("service_payments", "status=3", "id = %s".formatted(id));
//                } catch (SQLException ex) {
//                    Excp.imp(ex, getClass(), true, true);
//                }
//            }
//        }
        rmessage(view, delete);
    }

    @Override
    public void update() {
        if (!view.isValuesOk()) {
            return;
        }
        String id = view.getObjectSearch().getId();
        String field = "first_name, last_names, employee_type, user, password";
        boolean update = connection.update(field.replace(" ", "").split(","),
                view.getDbValues(true),
                "id = %s".formatted(id)
        );
        if (update) {
            Session.getInstancia().register(Const.UPDATE_TO_EMPLOYEES, "id:%s".formatted(id));
        }
        rmessage(view, update);
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
        view.getObjectSearch();
    }

}
