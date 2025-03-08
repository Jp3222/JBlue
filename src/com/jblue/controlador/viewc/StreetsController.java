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
import com.jblue.modelo.fabricas.FactoryCache;
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
import java.util.ArrayList;

/**
 *
 * @author juan-campos
 */
public class StreetsController extends Controller implements DBController {

    private final MemoListCache<OCalles> memo_cache;
    private final StreetsView view;
    private final ArrayList<ComponentController> components_controllers;

    public StreetsController(StreetsView view) {
        this.view = view;
        this.components_controllers = new ArrayList(5);
        memo_cache = FactoryCache.CALLES;

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
        if (!view.isValuesOk()) {
            return;
        }
        String[] arr = view.getDbValues();
        boolean insert = memo_cache.getConnection().insert(arr);
        memo_cache.reLoadData();
        messages(view, insert);
    }

    @Override
    public void delete() {
        if (view.getObjectSearch() != null) {
            return;
        }
        boolean delete = memo_cache.getConnection().delete("id = %s".formatted(view.getObjectSearch().getId()));
        messages(view, delete);
    }

    @Override
    public void update() {
        if (view.getObjectSearch() != null && !view.isValuesOk()) {
            return;
        }
        JDBConnection connection = (JDBConnection) memo_cache.getConnection();
        boolean update = connection.update(
                ConstBD.TABLA_CALLES,
                view.getDbValues(),
                "id = %s".formatted(view.getObjectSearch().getId())
        );
        messages(view, update);
    }

    @Override
    public void cancel() {
        int input = JOptionPane.showConfirmDialog(view, "¿Seguro que desea cancelar esta operacion?", "Cancelar operacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        boolean option = input == JOptionPane.YES_OPTION;
        if (option) {
            view.initialState();
        }

    }
    
}
