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
package jsoftware.com.jblue.controllers.compc;

import java.awt.event.ActionEvent;
import jsoftware.com.jblue.controllers.AbstractComponentController;
import jsoftware.com.jblue.model.dao.TableComponentDAO;
import jsoftware.com.jblue.views.framework.TableSearchViewModel;
import jsoftware.com.jutil.db.JDBMapObject;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juan pablo campos casasanero
 * @param <T>
 */
public class TableController<T extends JDBMapObject> extends AbstractComponentController<T> implements ComponentIterable {

    private static final long serialVersionUID = 1L;

    private TableSearchViewModel view;
    private JTableModel model;
    private TableComponentDAO<T> dao;

    public TableController(TableSearchViewModel view, TableComponentDAO dao) {
        super(view.getTable());
        this.model = new JTableModel(new String[]{}, 0);
        this.view = view;
        this.dao = dao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "search_view" ->
                searchView();
            case "register_view" ->
                registerView();
            case BACK_COMMAND ->
                back();
            case NEXT_COMMAND ->
                next();
            case RELOAD_COMMAND ->
                updateData();
            default ->
                defaultCase(e.getActionCommand(), getClass().getName(), -1);
        }
    }

    @Override
    public void loadData() {
        dao.getList(model);
        view.getTable().setModel(model);
        view.getTable().updateUI();
    }

    @Override
    public void dumpData() {
    }

    @Override
    public void updateData() {
    }

    @Override
    public void next() {
    }

    @Override
    public void back() {
    }

    void searchView() {
        if (view.getViewShow() == TableSearchViewModel.CONSULT_VIEW) {
            return;
        }
        loadData();
        view.setViewShow(TableSearchViewModel.CONSULT_VIEW);
    }

    void registerView() {
        if (view.getViewShow() == TableSearchViewModel.REGISTER_VIEW) {
            return;
        }
        dumpData();
        view.setViewShow(TableSearchViewModel.REGISTER_VIEW);
    }
}
