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
package com.jblue.controlador.compc;

import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.Filtros;
import com.jblue.util.cache.MemoListCache;
import com.jblue.util.objetos.ObjetoFK;
import com.jutil.swingw.modelos.JTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import com.jblue.vista.marco.TableSearchView;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class TableController<T extends Objeto & ObjetoFK> extends ComponentController<T> implements ComponentIterable {

    public static final String RELOAD_COMMAND = "reload";

    protected final TableSearchView view;

    public TableController(TableSearchView view, MemoListCache<T> memo_cache) {
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
        if (Filtros.isNullOrBlank(search_text)) {
            if (view.getModel().getRowCount() < memo_cache.size()) {
                loadData();
            }
            return;
        }
        dumpData();

        boolean id_filter = search_text.contains("ID:");
        List<T> list;
        if (id_filter) {
            String replace = search_text.replace("ID:", "");
            list = memo_cache.getList(i -> {
                //System.out.println(i.getId() + "=" + replace);
                return i.getId().equals(replace);
            });
        } else {
            list = memo_cache.getList(i -> Filtros.limpiar(i.toString()).contains(view.getTextSearchTable()));
        }
        list.forEach(i -> view.getModel().addRow(i.getInfo()));
    }

    @Override
    public void loadData() {
        ArrayList<T> list = memo_cache.getList();
        if (list.isEmpty()) {
            return;
        }
        if (list.getFirst() instanceof ObjetoFK) {
            for (T i : memo_cache.getList()) {
                view.getModel().addRow(i.getInfoSinFK());
            }
        } else {
            for (T i : memo_cache.getList()) {
                view.getModel().addRow(i.getInfo());
            }
        }
        view.setRowsData(String.valueOf(list.size()),
                String.valueOf("%d - %d").formatted(memo_cache.getIndexMin(), memo_cache.getIndexMin()),
                String.valueOf(memo_cache.count())
        );
//        view.getTable().updateUI();
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
        if (view.getViewShow() == TableSearchView.CONSULT_VIEW) {
            return;
        }
        loadData();
        view.setViewShow(TableSearchView.CONSULT_VIEW);
    }

    void registerView() {
        if (view.getViewShow() == TableSearchView.REGISTER_VIEW) {
            return;
        }
        dumpData();
        view.setViewShow(TableSearchView.REGISTER_VIEW);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() != view.getTable() || e.getClickCount() < 2) {
            return;
        }

        int selected_index = view.getTable().getSelectedRow();
        if (selected_index < 1 || selected_index >= view.getTable().getRowCount()) {
            return;
        }
        JTableModel model = (JTableModel) view.getModel();
        String[] row = model.getRow(selected_index);
        T get = memo_cache.get((t) -> t.getId().equals(row[0]));
        System.out.println(get);
        System.out.println(row[0]);
        if (get == null) {
            return;
        }

        view.setObjectSearch(get);
        view.setScreenListInfo();
        view.setViewShow(TableSearchView.REGISTER_VIEW);

    }

}
