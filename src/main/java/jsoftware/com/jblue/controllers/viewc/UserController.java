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
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.UserService;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.UserView;
import jsoftware.com.jblue.views.components.ComponentFactory;
import jsoftware.com.jblue.views.components.UserViewComponent;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juan pablo campos casasanero
 */
public class UserController extends AbstractDBViewController<UserDTO> implements DBControllerModel {

    private static final long serialVersionUID = 1L;

    private final UserView view;

    /**
     * Movimiento, id, nombre, a paterno, a materno
     */
    private String DESCRIPTION_FORMAT = "SE %s EL USUARIO: %s - %s %s %s";
    private final UserService user_service;

    public UserController(UserView view) {
        this.view = view;
        user_service = new UserService(AppConfig.isDevMessages(), view.getProcessTypeName());
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
        boolean res = false;
        res = view.isValuesOK();
        if (!res) {
            return;
        }

        UserDTO user = view.getValues(false);

        res = user != null;
        if (!res) {
            returnMessage(view, "HA OCURRIDO UN ERROR INTERNO");
            return;
        }
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection()) {

            int pro = user_service.save(connection, view.getProcessId(), user);
            res = pro > 0;
            returnMessage(view, res);
        } catch (Exception e) {
            log(e, "save");
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

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(
                    AppFiles.DIR_PROG_LOG_TODAY,
                    getClass(), e,
                    getClass().getName(),
                    method_name,
                    e.getMessage()
            );
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
