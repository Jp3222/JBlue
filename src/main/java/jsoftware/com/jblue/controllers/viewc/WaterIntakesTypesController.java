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
import jsoftware.com.jblue.model.dtos.OWaterIntakeTypes;
import jsoftware.com.jblue.views.WaterIntakesTypesView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.daos.HysHistoryDAO;
import jsoftware.com.jblue.model.dtos.OUser;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import jsoftware.com.jutil.jexception.JExcp;

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
        //validar los camapos obligatorios
        if (!view.isValuesOk()) {
            return;
        }
        connection.setAutoCommit(false);//desactivar el autocommit
        Map<String, String> values = view.getValues(false);//leer datos
        String[] arr = Formats.getInsertFormats(values);//dar formato de entrada
        //Estructuramos el query
        String query = JDBConnection.INSERT_VAL.formatted(
                _Const.WKI_WATER_INTAKE_TYPE_TABLE.getTableName(),
                arr[0],
                arr[1]
        );
        //Creamos el stament
        try (Statement st = connection.getJDBConnection().getNewStament()) {
            System.out.println(query);
            //ejecuta el query
            boolean registro = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) > 0;
            //validar si fue exitoso
            if (!registro) {
                throw new SQLException("[1] REGISTRO CORRUPTO");
            }
            //obtener las llaves generadas
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("[2] Primary Key no generada");
                }
                //registro en bitacora
                registro = HysHistoryDAO.getINSTANCE().insert(_Const.INDEX_WKI_WATER_INTAKE_TYPE,
                        "SE INSERTO LA TIPO DE TOMA: %s - %s".formatted(
                                rs.getString(1),
                                values.get("type_name")
                        )
                );
                if (!registro) {
                    throw new SQLException("[3] REGISTRO EN BITACORA INCOMPLETO");
                }
            }
            //confirmar los cambios
            connection.commit();
            returnMessage(view, true);
        } catch (SQLException ex) {
            connection.rollBack();
            returnMessage(view, false);
            JExcp.getInstance(false, true).show(ex, getClass());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void delete() {
        if (!view.isValuesOk()) {
            return;
        }
        connection.setAutoCommit(false);
        try {
            //comprobamos si hay usuarios usando este tipo de toma
            List<OUser> list = CacheFactory.USERS.getList((t) -> t.getWaterIntakeType().equals(view.getObjectSearch().getId()));
            if (!list.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hay %s usando este registro".formatted(list.size()));
                return;
            }
            //sino hay usuarios, hacemos un borrado logico
            String current_date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            if (connection == null) {
                throw new SQLException("conexion null");
            }

            boolean registro = connection.update("status = '3', date_end = '%s'".formatted(current_date_time),
                    "id = %s".formatted(view.getObjectSearch().getId())
            );

            // si no se hizo el borrado, mandamos una excepcion
            if (!registro) {
                throw new SQLException("BORRADO LOGICO CORRUPTO");
            }
            registro = HysHistoryDAO.getINSTANCE().insert(_Const.INDEX_WKI_WATER_INTAKE_TYPE,
                    "SE ELIMINO LOGICAMENTE EL TIPO DE TOMA: %s - %s".formatted(
                            view.getObjectSearch().getId(),
                            view.getObjectSearch().getTypeName()
                    ));
            if (!registro) {
                throw new SQLException("NO SE PUDO REGISTRAR EN BITACORA");
            }
            //si se hizo hacemos la confirmacion
            connection.commit();
            returnMessage(view, true);
        } catch (SQLException | NullPointerException ex) {
            connection.rollBack();
            returnMessage(view, false);
            System.getLogger(WaterIntakesTypesController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            connection.setAutoCommit(true);
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
        String query = JDBConnection.UPDATE_COL.formatted(_Const.WKI_WATER_INTAKE_TYPE_TABLE.getTableName(), arr,
                "id = %s".formatted(view.getObjectSearch().getId()));

        try (Statement st = connection.getConnection().createStatement();) {
            boolean registro = st.executeUpdate(query) > 0;
            if (!registro) {
                throw new SQLException("NO SE PUDO ELIMINAR LOGICAMENTE EL REGISTRO");
            }
            registro = HysHistoryDAO.getINSTANCE().insert(_Const.INDEX_WKI_WATER_INTAKE_TYPE,
                    "SE ACTUALIZO EL TIPO DE TOMA: %s - %s".formatted(
                            view.getObjectSearch().getId(),
                            view.getObjectSearch().getTypeName()
                    ));
            if (!registro) {
                throw new SQLException("NO SE PUDO REGISTRAR EN BITACORA");
            }
            connection.commit();
            returnMessage(view, true);
        } catch (SQLException ex) {
            connection.rollBack();
            returnMessage(view, false);
            System.getLogger(WaterIntakesTypesController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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
