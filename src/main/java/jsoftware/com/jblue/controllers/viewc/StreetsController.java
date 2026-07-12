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
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.dto.wrp.StreetWrapperDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.StreetService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.mod.StreetProcess;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juan pablo campos casasanero
 */
public class StreetsController extends AbstractDBViewController<StreetDTO> implements DBControllerModel {

    private StreetProcess view;
    private StreetService service;

    public StreetsController() {
        this.service = new StreetService(true, StreetsController.class.getName());
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
            default ->
                defaultCase("El comando %s no existe".formatted(e.getActionCommand()), StreetsController.this.getClass().getName(), -1);
        }
    }

    @Override
    public void save() {
        boolean res = false;
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            if (!ss.isAdministrationValid()) {
                returnMessage(view, "LA ADMINISTRACION ACTUAL NO ES VALIDA");
                return;
            }
            if (!ss.isLock()) {
                returnMessage(view, "LA ADMINISTRACION ACTUAL NO ES VALIDA");
                return;
            }
            if (!view.isValuesOK()) {
                return;
            }
            StreetWrapperDTO dto = view.getDtoWrapper();
            res = service.save(c, ss, dto);
            if (service.isError()) {
                returnMessage(view, false, service.getUserMessage());
                return;
            }
        } catch (Exception e) {
            returnMessage(view, false, e.getMessage());
        }
        if (res) {
            returnMessage(view, true);
        }
    }

    @Override
    public void update() {
        boolean res = false;
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            if (!ss.isAdministrationValid()) {
                returnMessage(view, "LA ADMINISTRACION ACTUAL NO ES VALIDA");
                return;
            }
            if (!ss.isLock()) {
                returnMessage(view, "LA ADMINISTRACION ACTUAL NO ES VALIDA");
                return;
            }
            if (!view.isValuesOK()) {
                return;
            }
            StreetWrapperDTO dto = view.getDtoWrapper();
            res = service.update(c, ss, dto);
            if (service.isError()) {
                returnMessage(view, false, service.getUserMessage());
                return;
            }
        } catch (Exception e) {
            returnMessage(view, false, e.getMessage());
        }
        if (res) {
            returnMessage(view, true);
        }
    }

    @Override
    public void delete() {
        boolean res = false;
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            if (!ss.isAdministrationValid()) {
                returnMessage(view, "LA ADMINISTRACION ACTUAL NO ES VALIDA");
                return;
            }
            if (!ss.isLock()) {
                returnMessage(view, "LA ADMINISTRACION ACTUAL NO ES VALIDA");
                return;
            }
            if (!view.isValuesOK()) {
                return;
            }
            StreetWrapperDTO dto = view.getDtoWrapper();
            res = service.delete(c, ss, dto);
            if (service.isError()) {
                returnMessage(view, false, service.getUserMessage());
                return;
            }
        } catch (Exception e) {
            returnMessage(view, false, e.getMessage());
        }
        if (res) {
            returnMessage(view, true);
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

    public void setView(StreetProcess view) {
        this.view = view;
    }

}
