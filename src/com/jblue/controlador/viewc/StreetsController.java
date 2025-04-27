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

import com.jblue.controlador.Controller;
import com.jblue.modelo.fabricas.CacheFactory;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.util.cache.MemoListCache;
import com.jblue.vista.views.StreetsView;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.jblue.controlador.DBController;
import com.jblue.controlador.compc.ComponentController;
import com.jblue.modelo.ConstBD;
import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.objetos.OUser;
import java.util.ArrayList;

/**
 *
 * @author juan-campos
 */
public class StreetsController extends DBViewController<OCalles> implements DBController {

    private final JDBConnection<OCalles> connection;
    private final StreetsView view;
    private final ArrayList<ComponentController> components_controllers;

    public StreetsController(StreetsView view) {
        super(CacheFactory.CALLES);
        this.connection = (JDBConnection<OCalles>) memo_cache.getConnection();
        this.view = view;
        this.components_controllers = new ArrayList(5);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            case UPDATE_COMMAND ->
                update();
            case DELETE_COMMAND ->
                delete();
            case "google-maps" -> {
                try {
                    String uri = "https://www.google.com.mx/maps/place/Cuauhtemoc,+62757+Cuautla,+Mor./@18.8677895,-98.930224,16z/data=!3m1!4b1!4m6!3m5!1s0x85ce6ead484a42d1:0xe9451cff404f4b4c!8m2!3d18.8678174!4d-98.9259142!16s%2Fg%2F1tj9tnz6?entry=ttu&g_ep=EgoyMDI0MTIxMS4wIKXMDSoASAFQAw%3D%3D";
                    Desktop.getDesktop().browse(URI.create((uri)));

                } catch (IOException ex) {
                    Logger.getLogger(StreetsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            default ->
                defaultCase("El comando %s no existe".formatted(e.getActionCommand()), StreetsController.this.getClass().getName(), -1);
        }
    }

    @Override
    public void save() {
        if (isOK()) {
            return;
        }
        String field = "name";
        boolean insert = connection.insert(field, view.getDbValues());
        rmessage(insert);
    }

    @Override
    public void delete() {
        if (isOK()) {
            return;
        }
        //boolean delete = connection.delete("id = %s".formatted(view.getObjectSearch().getId()));
        boolean delete = connection.update("status", "3", "id = %s".formatted(view.getObjectSearch().getId()));
        rmessage(delete);
    }

    @Override
    public void update() {
        if (isOK()) {
            return;
        }
        String field = "name";
        boolean update = connection.update(field.replace(" ", "").split(","),
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
