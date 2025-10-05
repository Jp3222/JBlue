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
import com.jblue.model.dtos.OUser;
import com.jblue.views.components.UserViewComponent;
import com.jblue.views.UserView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import com.jblue.controllers.DBControllerModel;
import com.jblue.controllers.AbstractDBViewController;
import com.jblue.model.DBConnection;
import com.jblue.model.constants._Const;
import com.jblue.model.daos.HysHistoryDAO;
import com.jblue.model.dtos.AdministrationHistoryObject;
import com.jblue.model.dtos.OEmployee;
import com.jblue.model.dtos.OServicePayments;
import com.jblue.sys.SystemSession;
import com.jblue.sys.app.AppConfig;
import com.jblue.sys.app.AppFiles;
import com.jblue.util.Formats;
import com.jblue.views.components.ComponentFactory;
import com.jutil.dbcon.connection.JDBConnection;
import com.jutil.jexception.JExcp;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public class UserController extends AbstractDBViewController<OUser> implements DBControllerModel {

    private final UserView view;

    /**
     * Movimiento, id, nombre, a paterno, a materno
     */
    private String DESCRIPTION_FORMAT = "SE %s EL USUARIO: %s - %s %s %s";

    public UserController(UserView view) {
        super(CacheFactory.USERS);
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
            case "search_object" ->
                searchObject();
            case "add_file" ->
                saveFile();
            default ->
                defaultCase(ae.getActionCommand(), null, -1);
        }

    }

    @Override
    public void save() {
        if (!view.isValuesOk()) {
            return;
        }
        Map<String, String> values = view.getValues(false);
        if (values.isEmpty()) {
            returnMessage(view, false);
            return;
        }
        //obtienen los datos
        String[] insertFormats = Formats.getInsertFormats(values);
        // se construye el query
        String query = JDBConnection.INSERT_VAL.formatted(_Const.USR_USERS_TABLE.getTableName(), insertFormats[0], insertFormats[1]);
        System.out.println(query);
        // se deshabilita el autocomit
        connection.setAutoCommit(false);
        try (Statement st = connection.getConnection().createStatement()) {
            String user_key = userRegister(st, query, values);
            //si se registra en el historial, validamos si es un tramite y si es un alta de titular o de consumidor
            if (view.isProcess() || values.get("user_type").contains("1")) {
                processOwnerRegister(st, user_key, values, "1");
            } else {
                processOwnerRegister(st, user_key, values, "2");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
            JExcp.getInstance(false, true).print(e, getClass(), "save");
            returnMessage(view, false);
        } catch (NullPointerException e) {
            JExcp.getInstance(false, true).print(e, getClass(), "save");
            returnMessage(view, false);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void delete() {
        try {
            if (!view.isValuesOk()) {
                return;
            }
            connection.setAutoCommit(false);
            String id = view.getObjectSearch().getId();
            boolean delete = connection.update("status", "3", "id = %s".formatted(id));
//            rmessage(view, delete, _Const.INDEX_DELETE, LogBookFormats.USERS.formatted(
//                    view.getObjectSearch().getId(),
//                    view.getObjectSearch().getName(),
//                    view.getObjectSearch().getLastName1(),
//                    view.getObjectSearch().getLastName2()
//            ));
            //FUNCION EN DESARROLLO - Ocultar los resgitros de pago de un usuario
            if (!AppConfig.isDevFunction()) {
                return;
            }
            int hidden_payments = JOptionPane.showConfirmDialog(view,
                    "¿Desea eliminar los pagos hechos por esta persona?",
                    "Eliminar - Pagos",
                    JOptionPane.YES_NO_OPTION
            );
            if (hidden_payments != JOptionPane.YES_OPTION) {
                return;
            }
            DBConnection<OServicePayments> payments = CacheFactory.SERVICE_PAYMENTS.getConnection();
            boolean update = payments.update("status", "3", "user = %s".formatted(id));
//            rmessage(view, update, _Const.LOGIC_DELETE_TO_SERVICE_PAYMENTS, LogBookFormats.USERS.formatted(
//                    view.getObjectSearch().getId(),
//                    view.getObjectSearch().getName(),
//                    view.getObjectSearch().getLastName1(),
//                    view.getObjectSearch().getLastName2()
//            ));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (NullPointerException e) {
            connection.rollBack();
        }
    }

    @Override
    public void update() {
        if (!view.isValuesOk()) {
            return;
        }
        OUser user = view.getObjectSearch();
        Map<String, String> values = view.getValues(true);
        if (values.isEmpty()) {
            returnMessage(view, false);
            return;
        }
        String update_format = Formats.getUpdateFormats(values);
        String query = JDBConnection.UPDATE_COL.formatted(
                _Const.USR_USERS_TABLE.getTableName(),
                update_format,
                "id = ".formatted(user.getId())
        );

        JDBConnection jconnection = connection.getJDBConnection();
        jconnection.setAutoCommit(false);
        try (Statement st = jconnection.getNewStament();) {
            boolean register = st.executeUpdate(query) > 0;
            if (!register) {
                throw new SQLException("ACTUALIZACION CORRUPTA");
            }
            register = HysHistoryDAO.getINSTANCE().getHysUsersMovs().updateToUsers(DESCRIPTION_FORMAT.formatted(
                    "ACTUALIZO",
                    user.getId(),
                    user.getName(),
                    user.getLastName1(),
                    user.getLastName2()
            ));
            if (!register) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            jconnection.commit();
            returnMessage(view, true);
        } catch (SQLException ex) {
            returnMessage(view, false);
            System.getLogger(UserController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            jconnection.setAutoCommit(true);
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

    private void searchObject() {
        view.getObjectSearch();
        UserViewComponent.showVisor(view.getObjectSearch());
    }

    private void saveFile() {
        File file = ComponentFactory.getFileChooser(view, "Aceptar");
        File out = new File(AppFiles.DIR_USER, view.getObjectSearch().toString());
        if (!out.exists()) {
            out.mkdir();
        }
        //Files.copy(file.toPath(), new BufferedOutputStream(new FileOutputStream(out)));
    }

    private String userRegister(Statement st, String query, Map<String, String> user_data) throws SQLException {
        //ejecutamos el quer
        boolean registro = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) > 0;
        //si no se registra lanzamos una excepcion para hacer un rollback
        if (!registro) {
            throw new SQLException("REGISTRO CORRUPTO");
        }
        //si se registra obtenemos la primary key generada
        String user_key = null;
        try (ResultSet rs = st.getGeneratedKeys()) {
            registro = rs.next();
            //si no se obtuvo la llave lanzamos una excepcion para hacer un rollback
            if (!registro) {
                throw new SQLException("ERROR AL GENERAL LA LLAVE");
            }
            //si se obtuvo se registra en el historial de movimientos
            user_key = rs.getString(1);
            registro = HysHistoryDAO.getINSTANCE().getHysUsersMovs().insertToUsers(
                    DESCRIPTION_FORMAT.formatted(
                            "INSERTO",
                            user_key,
                            user_data.get("first_name"),
                            user_data.get("last_name1"),
                            user_data.get("last_name2")
                    ));
            //si no se registra en el historial lanzamos una excepcion para hacer un rollback
            if (!registro) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
        }
        return user_key;
    }

    private void processOwnerRegister(Statement st, String user_key, Map<String, String> user_data, String process_type) throws SQLException, NullPointerException {
        boolean registro;
        //si es un titular se inicial el proceso de alta de toma
        String fields = "process_type,employee_start,president,user,status";

        OEmployee current_employee = SystemSession.getInstancia().getCurrentEmployee();
        AdministrationHistoryObject current_admin = SystemSession.getInstancia().getCurrentAdministration();
        if (current_employee == null) {
            throw new NullPointerException("El empleado actual esta corrupto");
        }
        if (current_admin == null) {
            throw new NullPointerException("La administracion actual esta corrupta");
        }
        String v2 = "'%s','%s','%s','%s'".formatted(
                process_type,
                current_employee.getId(),
                current_admin.getId(),
                user_key
        );
        //se construye el query
        String query = JDBConnection.INSERT_VAL.formatted(_Const.PRO_PROCESS_TABLE.getTableName(), fields, v2);
        registro = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) > 0;
        // si no se hizo el registro del tramite lanzamos una excepcion para hacer un rollback
        if (!registro) {
            throw new SQLException("REGISTRO DEL TRAMITE CORRUPTO");
        }
        // registramos el tramite en el historial
        registro = HysHistoryDAO.getINSTANCE().insert(_Const.INDEX_PRO_CONTRACT_PROCEDURE,
                "SE REGISTRO EL TRAMITE DEL USUARIO: %s - %s %s %s".formatted(user_key,
                        user_data.get("first_name"),
                        user_data.get("last_name1"),
                        user_data.get("last_name2")
                )
        );
        //si no se registra en el historial lanzamos una excepcion para hacer un rollback
        if (!registro) {
            throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
        }
    }

}
