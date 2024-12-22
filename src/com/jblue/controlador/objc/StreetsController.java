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
package com.jblue.controlador.objc;

import com.jblue.controlador.Controller;
import com.jblue.controlador.ControllerBD;
import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.fabricas.FabricaCache;
import com.jblue.modelo.fabricas.FabricaFuncionesBD;
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

/**
 *
 * @author juan-campos
 */
public class StreetsController extends Controller implements ControllerBD {

    private final MemoListCache<OCalles> memo_cache;
    private final StreetsView view;

    public StreetsController(StreetsView view) {
        this.view = view;
        memo_cache = FabricaCache.CALLES;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            case UPDATE ->
                update();
            case DELETE ->
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
                defaultCase("El comando %s no existe".formatted(e.getActionCommand()), null, -1);
        }
    }

    @Override
    public void save() {
        FuncionesBD<OCalles> fn = FabricaFuncionesBD.getCalles();
        boolean op = fn.insertByData(view.getDbValues());
        messages(op);
    }

    @Override
    public void delete() {
    }

    @Override
    public void update() {
    }

    @Override
    public void cancel() {
    }

    private void messages(boolean o) {
        if (o) {
            JOptionPane.showMessageDialog(view, "Operacion exitosa", "Estado de la operacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Operacion erronea", "Estado de la operacion", JOptionPane.ERROR_MESSAGE);
        }
    }
}
