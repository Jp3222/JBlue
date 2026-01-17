/*
 * Copyright (C) 2024 juan pablo campos casasanero
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
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dao.WaterIntakeTypeDAO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypesDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.views.WaterIntakesTypesView;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juan pablo campos casasanero
 */
public class WaterIntakesTypesController extends AbstractDBViewController<WaterIntakeTypesDTO> implements DBControllerModel {

    private static final long serialVersionUID = 1L;

    private final WaterIntakesTypesView view;
    private final WaterIntakeTypeDAO dao;

    public WaterIntakesTypesController(WaterIntakesTypesView view) {
        this.view = view;
        this.dao = new WaterIntakeTypeDAO(true, "TIPO DE TOMAS");
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
        if (!view.isValuesOK()) {
            return;
        }
        WaterIntakeTypesDTO o = view.getValues(false);
        if (o == null || o.getMap().isEmpty()) {
            returnMessage(view, "HA OCURRIDO UN ERROR INTERNO");
            return;
        }
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            boolean save = save(c, o);
            returnMessage(view, save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        if (!view.isValuesOK()) {
            return;
        }
        WaterIntakeTypesDTO o = view.getObjectSearch();
        if (o == null || o.getMap().isEmpty()) {
            returnMessage(view, "HA OCURRIDO UN ERROR INTERNO");
            return;
        }
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            boolean save = delete(c, o);
            returnMessage(view, save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if (!view.isValuesOK()) {
            return;
        }
        WaterIntakeTypesDTO new_dto = view.getValues(false);
        WaterIntakeTypesDTO old_dto = view.getObjectSearch();
        if (new_dto == null || new_dto.getMap().isEmpty()) {
            returnMessage(view, "HA OCURRIDO UN ERROR INTERNO");
            return;
        }
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            boolean save = update(c, old_dto, new_dto);
            returnMessage(view, save);
        } catch (Exception e) {
            e.printStackTrace();
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

    private boolean save(JDBConnection connection, WaterIntakeTypesDTO o) {
        boolean res = false;
        int key = -1;
        try {
            connection.setAutoCommit(false);

            key = dao.insert(connection, o);
            res = key > 0;
            if (!res) {
                throw new SQLException("REGISTRO DE CALLE CORRUPTO");
            }
            res = HysHistoryDAO.getINSTANCE().save(
                    Const.INDEX_WKI_WATER_INTAKE_TYPE,
                    Const.INDEX_INSERT,
                    "SE REGISTRO EL TIPO DE TOMA: %s - %s".formatted(key, o.getTypeName())
            );
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollBack();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    public boolean delete(JDBConnection connection, WaterIntakeTypesDTO o) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);

            res = dao.delete(connection, o);
            if (!res) {
                throw new SQLException("REGISTRO DE CALLE CORRUPTO");
            }
            res = HysHistoryDAO.getINSTANCE().save(
                    Const.INDEX_WKI_WATER_INTAKE_TYPE,
                    Const.INDEX_INSERT,
                    "SE ELIMINO EL TIPO DE TOMA: %s - %s".formatted(o.getId(), o.getTypeName())
            );
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    public boolean update(JDBConnection connection, WaterIntakeTypesDTO old_dto, WaterIntakeTypesDTO new_dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);

            res = dao.update(connection, old_dto, new_dto);
            if (!res) {
                throw new SQLException("REGISTRO DE CALLE CORRUPTO");
            }
            res = HysHistoryDAO.getINSTANCE().save(
                    Const.INDEX_WKI_WATER_INTAKE_TYPE,
                    Const.INDEX_INSERT,
                    "SE ACTUALIZO EL TIPO DE TOMA: %s - %s".formatted(new_dto.getId(), new_dto.getTypeName())
            );
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
