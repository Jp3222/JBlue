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

import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dao.PaymentsDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.UserDao;
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
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

        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection();) {
            save(connection, user);
        } catch (SQLException ex) {
            System.getLogger(UserController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private void save(JDBConnection connection, UserDTO user) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

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
