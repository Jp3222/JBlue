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
package com.jblue.controlador.viewc;

import com.jblue.controlador.DBController;
import static com.jblue.controlador.DBController.DELETE_COMMAND;
import static com.jblue.controlador.DBController.SAVE_COMMAND;
import static com.jblue.controlador.DBController.UPDATE_COMMAND;
import com.jblue.modelo.dbconexion.DBConnection;
import com.jblue.modelo.fabricas.FactoryCache;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.vista.components.CVisorUsuario;
import com.jblue.vista.views.UserView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 */
public class UserController extends DBViewController<OUsuarios> implements DBController {

    private final UserView view;

    public UserController(UserView view) {
        super(FactoryCache.USUARIOS);
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
        if (isOK()) {
            return;
        }
        DBConnection<OUsuarios> connection = (DBConnection<OUsuarios>) memo_cache.getConnection();
        String field = "nombre, ap, am, calle, ncasa, toma, estado, tipo";
        boolean insert = connection.insert(field, view.getDbValues());
        rmessage(insert);
    }

    @Override
    public void delete() {
        if (isOK()) {
            return;
        }
        DBConnection<OUsuarios> connection = (DBConnection<OUsuarios>) memo_cache.getConnection();
        boolean delete = connection.delete("id = %s".formatted(view.getObjectSearch().getId()));
        rmessage(delete);
    }

    @Override
    public void update() {
        if (isOK()) {
            return;
        }
        DBConnection<OUsuarios> connection = (DBConnection<OUsuarios>) memo_cache.getConnection();
        String[] field = {"nombre", " ap", "am", "calle", "ncasa", "toma", "estado", "tipo"};
        boolean update = connection.update(field,
                view.getDbValues(),
                "id = %s".formatted(view.getObjectSearch().getId())
        );
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
        CVisorUsuario.showVisor(view.getObjectSearch());
    }

    public boolean isOK() {
        boolean ok = view.isValuesOk();
        String status = ok ? "Exitoso" : "Erroneo";
        JOptionPane.showMessageDialog(view,
                "Operacion %s".formatted(status),
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);
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
