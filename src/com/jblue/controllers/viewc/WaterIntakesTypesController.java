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
package com.jblue.controllers.viewc;

import com.jblue.model.factories.CacheFactory;
import com.jblue.model.dtos.OWaterIntakeTypes;
import com.jblue.views.WaterIntakesTypesView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import com.jblue.controllers.DBControllerModel;
import com.jblue.controllers.AbstractDBViewController;
import com.jblue.model.constants._Const;
import com.jblue.model.dtos.OUser;
import com.jblue.util.Formats;
import com.jutil.dbcon.connection.JDBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public class WaterIntakesTypesController extends AbstractDBViewController<OWaterIntakeTypes> implements DBControllerModel {

    private final WaterIntakesTypesView view;

    public WaterIntakesTypesController(WaterIntakesTypesView view) {
        super(CacheFactory.WATER_INTAKES_TYPES);
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
            default ->
                defaultCase(ae.getActionCommand(), null, -1);
        }
    }

    @Override
    public void save() {
        if (!view.isValuesOk()) {
            return;
        }
        connection.setAutoCommit(false);
        Map<String, String> values = view.getValues(false);
        String[] arr = Formats.getInsertFormats(values);
        String query = JDBConnection.INSERT_VAL.formatted(_Const.INDEX_INSERT, arr[0], arr[1]);
        try (Statement st = connection.getConnection().createStatement();) {
            boolean res = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) > 0;
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (!rs.next()) {
                    return;
                }
//                rmessage(view, res,
//                        _Const.INSERT_TO_TYPE_WATER_INTAKES,
//                        "SE CREO EL TIPO DE TOMA: %s - %s".formatted(rs.getString(0), values.get("type_name")));
            }
        } catch (SQLException ex) {
            connection.rollBack();
            System.getLogger(WaterIntakesTypesController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void delete() {
        if (!view.isValuesOk()) {
            return;
        }
        try {
            List<OUser> list = CacheFactory.USERS.getList((t) -> t.getWaterIntakeType().equals(view.getObjectSearch().getId()));
            if (!list.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hay %s usando este registro".formatted(list.size()));
                return;
            }
            connection.setAutoCommit(false);
            boolean delete = connection.update("status", "3", "id = %s".formatted(view.getObjectSearch().getId()));
//            rmessage(view, delete,
//                    _Const.LOGIC_DELETE_TO_TYPE_WATER_INTAKES,
//                    "SE ELIMINO EL TIPO DE TOMA: %s".formatted(view.getObjectSearch().getTypeName())
//            );
            connection.setAutoCommit(true);
            connection.commit();
        } catch (NullPointerException e) {
            connection.rollBack();
        }
    }

    @Override
    public void update() {
        if (!view.isValuesOk()) {
            return;
        }
        connection.setAutoCommit(false);
        Map<String, String> values = view.getValues(false);
        String arr = Formats.getUpdateFormats(values);
        String query = JDBConnection.UPDATE_COL.formatted(_Const.WKI_WATER_INTAKE_TYPE_NAME, arr,
                "id = %s".formatted(view.getObjectSearch().getId()));
        try (Statement st = connection.getConnection().createStatement();) {
            boolean res = st.executeUpdate(query) > 0;
//            rmessage(view, res,
//                    _Const.INSERT_TO_TYPE_WATER_INTAKES,
//                    "SE ACTUALIZO EL TIPO DE TOMA: %s - %s".formatted(
//                            view.getObjectSearch().getId(),
//                            view.getObjectSearch().getTypeName())
//            );
        } catch (SQLException ex) {
            connection.rollBack();
            System.getLogger(WaterIntakesTypesController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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

}
