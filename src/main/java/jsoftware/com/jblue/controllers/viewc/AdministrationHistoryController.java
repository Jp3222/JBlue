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
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.views.AdministrationHistoryView;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class AdministrationHistoryController extends AbstractDBViewController {

    private final AdministrationHistoryView view;

    /**
     *
     * @param view
     */
    public AdministrationHistoryController(AdministrationHistoryView view) {
        super(CacheFactory.EMPLOYEES);
        this.view = view;
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
        JDBConnection conn = connection.getJDBConnection();
        conn.setAutoCommit(false);

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

}
