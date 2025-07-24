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
package com.jblue.controlador.viewc.dbviews;

import com.jblue.modelo.fabricas.CacheFactory;
import com.jblue.modelo.objetos.OUser;
import com.jblue.vista.components.UserViewComponent;
import com.jblue.vista.views.UserView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import com.jblue.controlador.DBControllerModel;
import com.jblue.controlador.AbstractDBViewController;
import com.jblue.modelo.constdb.Const;
import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.objetos.OServicePayments;
import com.jblue.sistema.Sesion;
import com.jblue.sistema.app.AppConfig;
import com.jblue.sistema.app.AppFiles;
import com.jblue.vista.components.ComponentFactory;
import com.jblue.vista.marco.vistas.SimpleView;
import java.io.File;

/**
 *
 * @author juan-campos
 */
public class UserController extends AbstractDBViewController<OUser> implements DBControllerModel {

    private final UserView view;

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
        String[] arr = view.getDbValues(false);
        String field = "first_name, last_name1, last_name2, street, house_number, water_intakes, user_type, status";
        boolean insert = connection.insert(field, arr);
        
        if (insert) {
            Sesion.getInstancia().register(Const.INSERT_TO_USER, DESCRIPTION_FORMAT.formatted(
                    memo_cache.count() + 1,
                    arr[0], arr[1], arr[2]
            ));
        }

        rmessage(view, insert);
    }

    @Override
    public void delete() {
        if (!view.isValuesOk()) {
            return;
        }
        String id = view.getObjectSearch().getId();
        boolean delete = connection.update("status", "3", "id = %s".formatted(id));
        if (delete) {
            Sesion.getInstancia().register(Const.LOGIC_DELETE_TO_USER, DESCRIPTION_FORMAT.formatted(
                    view.getObjectSearch().getId(),
                    view.getObjectSearch().getName(),
                    view.getObjectSearch().getLastName1(),
                    view.getObjectSearch().getLastName2()
            ));
        }
        rmessage(view, delete);
        //FUNCION EN DESARROLLO - Ocultar los resgitros de pago de un usuario
        if (AppConfig.isDevFunction()) {
            int hidden_payments = JOptionPane.showConfirmDialog(view, "¿Desea eliminar los pagos hechos por esta persona?");
            if (hidden_payments == JOptionPane.YES_OPTION) {
                JDBConnection<OServicePayments> payments = CacheFactory.SERVICE_PAYMENTS.getConnection();
                boolean update = payments.update("status", "3", "user = %s".formatted(id));
                rmessage(view, update);
            }
        }
    }

    @Override
    public void update() {
        if (!view.isValuesOk()) {
            return;
        }
        String field = "first_name, last_name1, last_name2, "
                + "street, house_number, water_intakes, "
                + "user_type, status";
        String values[] = view.getDbValues(true);
        boolean update = connection.update(
                field.split(","),
                values,
                "id = %s".formatted(view.getObjectSearch().getId()));
        if (update) {
            Sesion.getInstancia().register(Const.UPDATE_TO_USER, DESCRIPTION_FORMAT.formatted(
                    view.getObjectSearch().getId(),
                    view.getObjectSearch().getName(),
                    view.getObjectSearch().getLastName1(),
                    view.getObjectSearch().getLastName2()
            ));
        }
        rmessage(view, update);
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

    @Override
    protected void rmessage(SimpleView view, boolean ok) {
        super.rmessage(view, ok); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if (ok) {
            CacheFactory.SERVICE_PAYMENTS.reLoadData();
        }
    }

    String DESCRIPTION_FORMAT = "ID:%s, NOMBRE:%s %s %s";
}
