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
package com.jblue.controllers.compc;

import com.jblue.controllers.AbstractComponentController;
import com.jblue.model.dtos.Objects;
import com.jblue.util.Filters;
import com.jblue.util.cache.MemoListCache;
import com.jutil.swingw.modelos.JTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import com.jblue.model.dtos.ForeingKeyObject;
import com.jblue.views.framework.TableSearchViewModel;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class TableController<T extends Objects & ForeingKeyObject> extends AbstractComponentController<T> implements ComponentIterable {

    public static final String RELOAD_COMMAND = "reload";

    protected final TableSearchViewModel view;

    public TableController(TableSearchViewModel view, MemoListCache<T> memo_cache) {
        super(view.getTable(), memo_cache);
        this.view = view;
        this.view.getTextComponenteTable().addKeyListener(this);
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
    public void keyReleased(KeyEvent e) {
        String search_text = view.getTextSearchTable();
        if (Filters.isNullOrBlank(search_text)) {
            if (view.getModel().getRowCount() < memo_cache.size()) {
                loadData();
            }
            return;
        }
        dumpData();
        List<T> list;
        list = memo_cache.getList(i -> {
            return Filters.clearAndCheck(i.toString(), view.getTextSearchTable());
        });
        load(list, (JTableModel) view.getModel());
    }

    @Override
    public void loadData() {
        ArrayList<T> list = memo_cache.getList();
        if (list.isEmpty()) {
            return;
        }
        load(list, (JTableModel) view.getModel());
        view.setRowsData(String.valueOf(list.size()),
                String.valueOf("%d - %d").formatted(memo_cache.getIndexMin(), memo_cache.getIndexMin()),
                String.valueOf(memo_cache.count())
        );
    }

    private void load(List<T> data, JTableModel model) {
        if (data.isEmpty()) {
            return;
        }
        if (data.getFirst() instanceof ForeingKeyObject) {
            for (T i : data) {
                model.addRow(i.getInfoSinFK());
            }
            return;
        }
        for (T i : data) {
            model.addRow(i.getInfo());
        }
    }

    @Override
    public void dumpData() {
        JTableModel model = (JTableModel) view.getModel();
        if (model.isRowsEmpty()) {
            return;
        }
        model.removeAllRows();
    }

    @Override
    public void updateData() {
        memo_cache.reLoadData();
        dumpData();
        loadData();
    }

    @Override
    public void next() {
        if (!memo_cache.next()) {
            memo_cache.back();
            defaultCase("No existen mas registros", "Registros", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void back() {
        if (!memo_cache.back()) {
            memo_cache.next();
            defaultCase("No existen mas registros", "Registros", JOptionPane.INFORMATION_MESSAGE);
        }
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() != view.getTable() || e.getClickCount() < 2) {
            return;
        }

        int selected_index = view.getTable().getSelectedRow();
        if (selected_index < 0 || selected_index >= view.getTable().getRowCount()) {
            return;
        }
        JTableModel model = (JTableModel) view.getModel();
        String[] row = model.getRow(selected_index);
        T get = memo_cache.get((t) -> t.getId().equals(row[0]));
        if (get == null) {
            return;
        }

        view.setObjectSearch(get);
        view.setScreenTableInfo();
        dumpData();
        view.setViewShow(TableSearchViewModel.REGISTER_VIEW);

    }

}
