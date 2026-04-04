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
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypeDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.WaterIntakeTypeService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.WaterIntakesTypesView;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juan pablo campos casasanero
 */
public class WaterIntakesTypesController extends AbstractDBViewController<WaterIntakeTypeDTO> implements DBControllerModel {

    private static final long serialVersionUID = 1L;

    private final WaterIntakesTypesView view;

    private WaterIntakeTypeService service;

    public WaterIntakesTypesController(WaterIntakesTypesView view) {
        this.view = view;
        this.service = new WaterIntakeTypeService(AppConfig.isDevMessages(), "TIPO DE TOMAS DE AGUA POTABLE");
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

    @Override
    public void save() {
        boolean res = false;
        String mensaje = "OPERACION EXITOSA";
        try (JDBConnection connection = ConnectionFactory.getIntance().getWaterIntakeConnection()) {
            EmployeeUserDTO currentEmployee = SystemSession.getInstancia().getCurrentEmployee();
            res = service.insert(connection, view.getValues(false), currentEmployee);
        } catch (SQLException ex) {
            log(ex, ex.getMessage());
        }
        if (!res) {
            mensaje = service.getUserMessage();
        }
        returnMessage(view, res, mensaje);
    }

    @Override
    public void delete() {
    }

    @Override
    public void update() {
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
