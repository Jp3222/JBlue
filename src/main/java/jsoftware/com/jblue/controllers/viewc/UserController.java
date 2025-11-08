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

import java.awt.HeadlessException;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.dtos.OUser;
import jsoftware.com.jblue.views.components.UserViewComponent;
import jsoftware.com.jblue.views.UserView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.DBConnection;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.daos.HysHistoryDAO;
import jsoftware.com.jblue.model.dtos.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jblue.model.dtos.OServicePayments;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.views.components.ComponentFactory;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.jexception.JExcp;
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
        HysAdministrationHistoryDTO currentAdministration = SystemSession.getInstancia().getCurrentAdministration();
        if (currentAdministration == null) {
            returnMessage(view, false, "[1] La administracion actual no ha sido registrada");
            return;
        }
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
            } else if (view.isProcess() || values.get("user_type").contains("2")) {
                processOwnerRegister(st, user_key, values, "2");
            } else {
                throw new UnknownError("Tramite Desconocido");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
            JExcp.getInstance(false, true).print(e, getClass(), "save");
            returnMessage(view, false);
        } catch (Exception e) {
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
            OUser user = view.getObjectSearch();
            boolean registro = connection.update("status", "3", "id = %s".formatted(user.getId()));
            if (!registro) {
                throw new SQLException("BORRADO LOGICO CORRUPTO");
            }
            registro = HysHistoryDAO.getINSTANCE().getHysUsersMovs().insertToUsers(
                    DESCRIPTION_FORMAT.formatted(
                            "BORRO LOGICAMENTE",
                            user.getId(),
                            user.getName(),
                            user.getLastName1(),
                            user.getLastName2()
                    ));
            //si no se registra en el historial lanzamos una excepcion para hacer un rollback
            if (!registro) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            boolean devFunction = AppConfig.isDevFunction();
            if (!devFunction) {
                connection.commit();
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
            registro = payments.update("status", "3", "user = %s".formatted(user.getId()));
            if (!registro) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            registro = HysHistoryDAO.getINSTANCE().delete(_Const.INDEX_PYM_SERVICE_PAYMENTS,
                    "SE BORRARON LOGICAMENTE LOS PAGOS DEL USUARIO: %s - %s %s %s".formatted(
                            user.getId(),
                            user.getName(),
                            user.getLastName1(),
                            user.getLastName2()
                    )
            );
            //si no se registra en el historial lanzamos una excepcion para hacer un rollback
            if (!registro) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException | HeadlessException ex) {
            connection.rollBack();
            System.getLogger(UserController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            connection.setAutoCommit(true);
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

    private String processOwnerRegister(Statement st, String user_key, Map<String, String> user_data, String process_type) throws SQLException, NullPointerException {
        boolean registro;
        //si es un titular se inicial el proceso de alta de toma
        String fields = "process_type,employee_start,president,user,status";

        OEmployee current_employee = SystemSession.getInstancia().getCurrentEmployee();
        HysAdministrationHistoryDTO current_admin = SystemSession.getInstancia().getCurrentAdministration();
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
        ResultSet rs = st.getGeneratedKeys();
        if (!rs.next()) {
            throw new SQLException("LLAVE CORRUPTA");
        }
        String process_key = rs.getString(1);
        return process_key;
    }

}
