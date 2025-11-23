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
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dao.PaymentsDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.UserDao;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.UserView;
import jsoftware.com.jblue.views.components.ComponentFactory;
import jsoftware.com.jblue.views.components.UserViewComponent;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juan-campos
 */
public class UserController extends AbstractDBViewController<UserDTO> implements DBControllerModel {

    private final UserView view;

    /**
     * Movimiento, id, nombre, a paterno, a materno
     */
    private String DESCRIPTION_FORMAT = "SE %s EL USUARIO: %s - %s %s %s";
    private UserDao user_dao;
    private ProcessDAO process_dao;
    private HysHistoryDAO history_dao;
    private PaymentsDAO payment;

    public UserController(UserView view) {
        super(CacheFactory.USERS);
        this.view = view;
        user_dao = new UserDao(AppConfig.isLogsDev(), "user");
        process_dao = new ProcessDAO(AppConfig.isLogsDev(), "user");
        history_dao = new HysHistoryDAO(AppConfig.isLogsDev(), "user");
        payment = new PaymentsDAO(AppConfig.isDevMessages(), "user");
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
        UserDTO user = new UserDTO();
        Map<String, Object> values = view.getValues(false);
        if (values.isEmpty()) {
            returnMessage(view, false);
            return;
        }
        user.setMap(values);
        JDBConnection connection = ConnectionFactory.getIntance().getMain_connection();
        boolean res;
        connection.setAutoCommit(false);
        try {
            int user_key = user_dao.insertUser(connection, user);
            res = user_key > 0;
            if (!res) {
                throw new SQLException("REGISTRO DE USUARIO CORRUPTO");
            }
            res = history_dao.getHysUsersMovs().insertToUsers(DESCRIPTION_FORMAT.formatted(
                    "INSERTO",
                    user_key,
                    user.getFirstName(),
                    user.getLastName1(),
                    user.getLastName2()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            if (view.isProcess()) {
                res = process_dao.startProcess(connection, view.getProcessId(), String.valueOf(user_key));
                if (!res) {
                    throw new SQLException("REGISTRO DEL TRAMITE CORRUPTO");
                }
                res = history_dao.insert(Const.INDEX_PRO_CONTRACT_PROCEDURE,
                        "SE REGISTRO EL TRAMITE DEL USUARIO: %s - %s %s %s".formatted(user_key,
                                user.getFirstName(),
                                user.getLastName1(),
                                user.getLastName2()
                        ));
                //si no se registra en el historial lanzamos una excepcion para hacer un rollback
                if (!res) {
                    throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
                }
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void delete() {
        if (!view.isValuesOk()) {
            return;
        }
        boolean res = false;
        UserDTO user = view.getObjectSearch();
        EmployeeDTO employee = SystemSession.getInstancia().getCurrentEmployee();
        JDBConnection connection = ConnectionFactory.getIntance().getMain_connection();
        try {
            connection.setAutoCommit(false);
            res = user_dao.logicalDeleteUser(connection,
                    Integer.parseInt(user.getId()),
                    Integer.parseInt(employee.getId())
            );
            if (!res) {
                throw new SQLException("BORRADO LOGICO CORRUPTO");
            }
            res = HysHistoryDAO.getINSTANCE().getHysUsersMovs().insertToUsers(
                    DESCRIPTION_FORMAT.formatted(
                            "BORRO LOGICAMENTE",
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName1(),
                            user.getLastName2()
                    ));
            //si no se registra en el historial lanzamos una excepcion para hacer un rollback
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            if (view.isProcess()) {
                res = process_dao.startProcess(connection, view.getProcessId(), user.getId());
                if (!res) {
                    throw new SQLException("REGISTRO DEL TRAMITE CORRUPTO");
                }
                res = history_dao.insert(Const.INDEX_PRO_CONTRACT_PROCEDURE,
                        "SE REGISTRO EL TRAMITE DEL USUARIO: %s - %s %s %s".formatted(
                                user.getId(),
                                user.getFirstName(),
                                user.getLastName1(),
                                user.getLastName2()
                        ));
                //si no se registra en el historial lanzamos una excepcion para hacer un rollback
                if (!res) {
                    throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
                }
            }
            boolean devFunction = AppConfig.isDevFunction();
            if (devFunction) {
                int hidden_payments = JOptionPane.showConfirmDialog(view,
                        "¿Desea eliminar los pagos hechos por esta persona?",
                        "Eliminar - Pagos",
                        JOptionPane.YES_NO_OPTION
                );
                if (hidden_payments == JOptionPane.YES_OPTION) {
                    res = payment.deleteWC(connection, Integer.parseInt(user.getId()));
                    if (!res) {
                        throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
                    }
                    res = HysHistoryDAO.getINSTANCE().delete(Const.INDEX_PYM_SERVICE_PAYMENTS,
                            "SE BORRARON LOGICAMENTE LOS PAGOS DEL USUARIO: %s - %s %s %s".formatted(
                                    user.getId(),
                                    user.getFirstName(),
                                    user.getLastName1(),
                                    user.getLastName2()
                            )
                    );
                    //si no se registra en el historial lanzamos una excepcion para hacer un rollback
                    if (!res) {
                        throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
                    }
                }
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
        boolean res = false;
        JDBConnection connection = ConnectionFactory.getIntance().getMain_connection();
        try {
            UserDTO old_user = view.getObjectSearch();
            UserDTO new_user = new UserDTO();
            Map<String, Object> values = view.getValues(true);
            new_user.setMap(values);
            res = user_dao.updateUserDynamic(connection, old_user, new_user);
            if (!res) {
                throw new SQLException("ACTUALIZACION DE USUARIO CORRUPTA");
            }
            res = history_dao.getHysUsersMovs().updateToUsers(DESCRIPTION_FORMAT.formatted(
                    "ACTUALIZO",
                    new_user.getId(),
                    new_user.getFirstName(),
                    new_user.getLastName1(),
                    new_user.getLastName2()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            if (view.isProcess()) {
                res = process_dao.startProcess(connection, view.getProcessId(), new_user.getId());
                if (!res) {
                    throw new SQLException("REGISTRO DEL TRAMITE CORRUPTO");
                }
                res = history_dao.insert(Const.INDEX_PRO_CONTRACT_PROCEDURE,
                        "SE REGISTRO EL TRAMITE DEL USUARIO: %s - %s %s %s".formatted(
                                new_user.getId(),
                                new_user.getFirstName(),
                                new_user.getLastName1(),
                                new_user.getLastName2()
                        ));
                //si no se registra en el historial lanzamos una excepcion para hacer un rollback
                if (!res) {
                    throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            connection.rollBack();
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

}
