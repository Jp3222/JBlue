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
import com.jblue.controlador.DBController;
import com.jblue.controlador.compc.ComponentController;
import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.fabricas.FactoryCache;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.util.cache.MemoListCache;
import com.jblue.vista.views.WaterIntakesView;
import java.awt.event.ActionEvent;

/**
 *
 * @author juan-campos
 */
public class WaterIntakesController extends Controller implements DBController {

    private WaterIntakesView view;
    private final MemoListCache<OTipoTomas> memo_cache;
    public WaterIntakesController(WaterIntakesView view) {
        this.view = view;
        memo_cache = FactoryCache.TIPO_DE_TOMAS;
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
        if (view.isValuesOk()) {
            String[] values = view.getDbValues();
            FuncionesBD<OTipoTomas> conexion = (FuncionesBD<OTipoTomas>) memo_cache.getConnection();
            conexion.insert(values);
        }
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

}
