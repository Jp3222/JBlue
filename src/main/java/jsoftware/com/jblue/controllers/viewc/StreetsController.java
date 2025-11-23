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
package jsoftware.com.jblue.controllers.viewc;

import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.views.StreetsView;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.jexception.JExcp;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author juan-campos
 */
public class StreetsController extends AbstractDBViewController<StreetDTO> implements DBControllerModel {

    private final StreetsView view;

    public StreetsController(StreetsView view) {
        super(CacheFactory.STREETS);
        this.view = view;

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
        if (!view.isValuesOk()) {
            return;
        }
        String[] values = view.getDbValues(false);
        String query = JDBConnection.INSERT_VAL.formatted(Const.CAT_STREET_TABLE.getTableName(),
                "street_name",
                values[0]
        );
        System.out.println(query);
        connection.setAutoCommit(false);
        try (Statement st = this.connection.getJDBConnection().getNewStament()) {
            boolean insert = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) > 0;
            if (!insert) {
                throw new SQLException("REGISTRO CORRUPTO");
            }
            ResultSet rs = st.getGeneratedKeys();
            insert = rs.next();
            if (!insert) {
                throw new SQLException("ERROR AL GENERAL LAS LLAVES");
            }
            insert = HysHistoryDAO.getINSTANCE().insert(Const.INDEX_CAT_STREET,
                    "SE INSERTO LA CALLE: %s - %s".formatted(rs.getString(1), values[0])
            );
            if (!insert) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
            returnMessage(view, true);
        } catch (SQLException e) {
            connection.rollBack();
            JExcp.getInstance(false, true).print(e, getClass(), SAVE_COMMAND);
            returnMessage(view, false);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void delete() {
        if (view.isValuesOk()) {
            return;
        }
        try {
            connection.setAutoCommit(false);
            String query = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            boolean delete = connection.update(
                    "status = '3', date_end = '%s'".formatted(query),
                    "WHERE id = '%s'".formatted(view.getObjectSearch().getId())
            );
            if (!delete) {
                throw new SQLException("BORRADO LOGICO CORRUPTO");
            }
            delete = HysHistoryDAO.getINSTANCE().delete(Const.INDEX_CAT_STREET,
                    "SE ELIMINO LA CALLE: %s - %s".formatted(
                            view.getObjectSearch().getId(),
                            view.getObjectSearch().getNombre()
                    ));
            if (!delete) {
                throw new SQLException("ERROR AL REGISTRAR EN BITACORA");
            }
            returnMessage(view, true);
        } catch (SQLException e) {
            connection.rollBack();
            returnMessage(view, false);
            JExcp.getInstance(false, true).print(e, getClass(), SAVE_COMMAND);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void update() {
        if (!view.isValuesOk()) {
            return;
        }
        try {
            connection.setAutoCommit(false);
            String field = "name";
            boolean update = connection.update(field.replace(" ", "").split(","),
                    view.getDbValues(true),
                    "id = %s".formatted(view.getObjectSearch().getId())
            );
            if (!update) {
                throw new SQLException("ERROR AL ACTUALIZAR");
            }
            update = HysHistoryDAO.getINSTANCE().update(Const.INDEX_CAT_STREET, "SE ACTUALIZO LA CALLE");
            if (!update) {
                throw new SQLException("ERROR AL REGISTRAR EL BITACORA");
            }
            connection.commit();
            returnMessage(view, update);
        } catch (SQLException ex) {
            connection.rollBack();
            System.getLogger(StreetsController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            connection.setAutoCommit(true);
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
