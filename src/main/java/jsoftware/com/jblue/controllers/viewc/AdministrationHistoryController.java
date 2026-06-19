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

import java.awt.event.ActionEvent;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.dto.wrp.AdministrationWrapperDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.AdministrationHistoryService;
import jsoftware.com.jblue.views.AdministrationHistoryView;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class AdministrationHistoryController extends AbstractDBViewController<AdministrationWrapperDTO> {

    private static final long serialVersionUID = 1L;

    private AdministrationHistoryView view;
    private AdministrationHistoryService service;

    /**
     *
     * @param view
     */
    public AdministrationHistoryController() {

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
                throw new AssertionError();
        }
    }

    @Override
    public void save() {
        AdministrationWrapperDTO dto = view.getDtoWrapper();
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection()) {
            service.insert(connection, dto);
            if (service.isError()) {
                returnMessage(view, false, service.getUserMessage());
            } else {
                returnMessage(view, true);
            }
        } catch (Exception e) {
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

    public void setView(AdministrationHistoryView view) {
        this.view = view;
    }

}
