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

import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.fabricas.CacheFactory;
import com.jblue.modelo.objetos.OWaterIntake;
import com.jblue.vista.views.WaterIntakesView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import com.jblue.controlador.DBControllerModel;
import com.jblue.controlador.AbstractDBViewController;

/**
 *
 * @author juan-campos
 */
public class WaterIntakesController extends AbstractDBViewController<OWaterIntake> implements DBControllerModel {

    private WaterIntakesView view;
    private final JDBConnection<OWaterIntake> connection;

    public WaterIntakesController(WaterIntakesView view) {
        super(CacheFactory.WATER_INTAKES_TYPES);
        this.view = view;
        connection = (JDBConnection<OWaterIntake>) memo_cache.getConnection();

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
            default ->
                defaultCase(ae.getActionCommand(), null, -1);
        }
    }

    @Override
    public void save() {
        if (!view.isValuesOk()) {
            return;
        }
        String field = "type, price, surcharge";
        boolean insert = connection.insert(field, view.getDbValues(false));
        rmessage(view, insert);
    }

    @Override
    public void delete() {
        if (!view.isValuesOk()) {
            return;
        }
        //boolean delete = connection.delete("id = %s".formatted(view.getObjectSearch().getId()));
        boolean delete = connection.update("status", "3", "id = %s".formatted(view.getObjectSearch().getId()));
        rmessage(view, delete);
    }

    @Override
    public void update() {

        if (!view.isValuesOk()) {
            return;
        }
        String field = "type, previus_price, price, surcharge, date_update";
        boolean update = connection.update(field.replace(" ", "").split(","),
                view.getDbValues(true),
                "id = %s".formatted(view.getObjectSearch().getId())
        );
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

}
