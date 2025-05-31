/*
 * Copyright (C) 2024 juan-campos
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
package com.jblue.controlador.viewc.dbviews;

import static com.jblue.controlador.DBControllerModel.DELETE_COMMAND;
import static com.jblue.controlador.DBControllerModel.SAVE_COMMAND;
import static com.jblue.controlador.DBControllerModel.UPDATE_COMMAND;
import com.jblue.modelo.fabricas.CacheFactory;
import com.jblue.modelo.objetos.OUser;
import com.jblue.sistema.DevFlags;
import com.jblue.vista.components.UserViewComponent;
import com.jblue.vista.views.UserView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import com.jblue.controlador.DBControllerModel;
import com.jblue.controlador.AbstractDBViewController;

/**
 *
 * @author juan-campos
 */
public class UserController extends AbstractDBViewController<OUser> implements DBControllerModel {

    private final UserView view;

    public UserController(UserView view) {
        super(CacheFactory.USUARIOS);
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
        if (!view.isValuesOk()) {
            return;
        }
        String field = "first_name, last_name1, last_name2, street, house_number, water_intakes, user_type, status";
        boolean insert = connection.insert(field, view.getDbValues(false));
        rmessage(insert);
    }

    @Override
    public void delete() {
        if (!view.isValuesOk()) {
            return;
        }
        String id = view.getObjectSearch().getId();
        boolean delete = connection.update("status", "3", "id = %s".formatted(id));
        rmessage(delete);
        //FUNCION EN DESARROLLO - Ocultar los resgitros de pago de un usuario
        if (DevFlags.TST_EXE_FUNCION) {
            int hidden_payments = JOptionPane.showConfirmDialog(view, "¿Desea eliminar los pagos hechos por esta persona?");
            if (hidden_payments == JOptionPane.YES_OPTION) {
                boolean update = connection.update(
                        "status=3",
                        "id = %s".formatted(view.getObjectSearch().getId())
                );
                rmessage(update);
            }
        }
    }

    @Override
    public void update() {
        if (!view.isValuesOk()) {
            return;
        }
        String field = "first_name, last_name1, last_name2, "
                + "street, house_number, water_intakes, "
                + "user_type, status";

        boolean update = connection.update(
                field.split(","),
                view.getDbValues(true),
                "id = %s".formatted(view.getObjectSearch().getId()));
        rmessage(update);
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
        UserViewComponent.showVisor(view.getObjectSearch());
    }

    public boolean isOK() {
        boolean ok = view.isValuesOk();
        String status = ok ? "Exitoso" : "Erroneo";
        JOptionPane.showMessageDialog(view,
                "Operacion %s".formatted(status),
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);
        System.out.println(ok);
        return ok;
    }

    public void rmessage(boolean op) {
        String status = op ? "Exitoso" : "Erroneo";
        JOptionPane.showMessageDialog(view,
                "Operacion %s".formatted(status),
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);
        if (op) {
            memo_cache.reLoadData();
            view.initialState();
        }
    }
}
