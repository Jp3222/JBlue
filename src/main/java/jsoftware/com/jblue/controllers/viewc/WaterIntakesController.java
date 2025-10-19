/*
 * Copyright (C) 2025 juanp
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

import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.dtos.OWaterIntakes;
import jsoftware.com.jblue.views.WaterIntakesView;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.daos.HysHistoryDAO;
import jsoftware.com.jblue.model.dtos.OStreet;
import jsoftware.com.jblue.model.dtos.OUser;
import jsoftware.com.jblue.model.dtos.OWaterIntakeTypes;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.views.components.ObjectSearchComponent;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class WaterIntakesController extends AbstractDBViewController<OWaterIntakes> {

    private WaterIntakesView view;

    public WaterIntakesController(WaterIntakesView view) {
        super(CacheFactory.WATER_INTAKES);
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
            case "search_user" ->
                search_user();
            case "search_water_intake_type" ->
                search_street1();
            case "search_street1" ->
                search_street2();
            case "search_street2" ->
                search_water_inatake_type();
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public void save() {
        Map<String, String> values = view.getValues(false);
        if (values.isEmpty()) {
            returnMessage(view, false);
            return;
        }
        //obtienen los datos
        String[] insertFormats = Formats.getInsertFormats(values);
        // se construye el query
        String query = JDBConnection.INSERT_VAL.formatted(_Const.WKI_WATER_INTAKES_TABLE.getTableName(), insertFormats[0], insertFormats[1]);
        connection.setAutoCommit(false);
        try (Statement st = connection.getJDBConnection().getNewStament()) {
            boolean register = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) > 0;
            if (!register) {
                throw new SQLException("REGISTRO CORRUPTO");
            }
            ResultSet rs = st.getGeneratedKeys();
            if (!rs.next()) {
                throw new SQLException("LLAVE CORRUPTA");
            }
            register = HysHistoryDAO.getINSTANCE().insert(_Const.INDEX_WKI_WATER_INTAKES,
                    "SE INSERTO LA TOMA DE AGUA DEL USUARIO: %s".formatted(
                            values.get("user_name")
                    )
            );
            if (!register) {
                throw new SQLException("REGISTRO CORRUPTO");
            }
            returnMessage(view, true);
        } catch (SQLException e) {
            connection.rollBack();
            returnMessage(view, false);
        } finally {
            connection.setAutoCommit(true);
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

    private void search_user() {
        OUser user = ObjectSearchComponent.getUser(null);
        view.setUserSearch(user);
    }

    private void search_street1() {
        OStreet street = ObjectSearchComponent.getStreet(null);
        view.setStreet1_search(street);
    }

    private void search_street2() {
        OStreet street = ObjectSearchComponent.getStreet(null);
        view.setStreet2_search(street);
    }

    private void search_water_inatake_type() {
        OWaterIntakeTypes water_inatke_type = ObjectSearchComponent.getWaterIntakeType(null);
        view.setWater_intake_types_search(water_inatke_type);
    }
}
