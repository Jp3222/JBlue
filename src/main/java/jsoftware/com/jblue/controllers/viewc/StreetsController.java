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

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dao.StreetDAO;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.views.StreetsView;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juan pablo campos casasanero
 */
public class StreetsController extends AbstractDBViewController<StreetDTO> implements DBControllerModel {

    private final StreetsView view;
    private final StreetDAO dao;

    public StreetsController(StreetsView view) {
        this.view = view;
        this.dao = new StreetDAO(true, "calles");

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
            case CANCEL_COMMAND ->
                cancel();
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
        if (!view.isValuesOK()) {
            return;
        }
        StreetDTO o = view.getValues(false);
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
        StreetDTO o = view.getObjectSearch();
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
        StreetDTO new_dto = view.getValues(false);
        StreetDTO old_dto = view.getObjectSearch();
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

    private boolean save(JDBConnection connection, StreetDTO o) {
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
                    Const.INDEX_CAT_STREET,
                    Const.INDEX_INSERT,
                    "SE REGISTRO LA CALLE: %s - %s".formatted(key, o.getStreetName())
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

    public boolean delete(JDBConnection connection, StreetDTO o) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);

            res = dao.delete(connection, o);
            if (!res) {
                throw new SQLException("REGISTRO DE CALLE CORRUPTO");
            }
            res = HysHistoryDAO.getINSTANCE().save(
                    Const.INDEX_CAT_STREET,
                    Const.INDEX_INSERT,
                    "SE REGISTRO LA CALLE: %s - %s".formatted(o.getId(), o.getStreetName())
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

    public boolean update(JDBConnection connection, StreetDTO old_dto, StreetDTO new_dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);

            res = dao.update(connection, old_dto, new_dto);
            if (!res) {
                throw new SQLException("REGISTRO DE CALLE CORRUPTO");
            }
            res = HysHistoryDAO.getINSTANCE().save(
                    Const.INDEX_CAT_STREET,
                    Const.INDEX_INSERT,
                    "SE ACTUALIZO LA CALLE: %s - %s".formatted(new_dto.getId(), new_dto.getStreetName())
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
