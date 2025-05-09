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
import com.jblue.util.Filters;
import com.jblue.util.cache.MemoListCache;
import com.jblue.vista.marco.ListSearchView;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class ListController<T extends Objeto> extends ComponentController<T> {

    protected ListSearchView view;
    protected DefaultListModel<T> model;
    protected String search_text;

    public ListController(ListSearchView view, MemoListCache<T> memo_cache) {
        super(view.getList(), memo_cache);
        this.view = view;
        model = view.getListModel();
        view.getTextComponentList().addKeyListener((KeyListener) this);
        view.getTextComponentList().addMouseListener((MouseListener) this);
    }

    @Override
    public void loadData() {
        memo_cache.getList().forEach((o) -> {
            model.addElement(o);
        });
    }

    @Override
    public void dumpData() {
        if (model.isEmpty()) {
            return;
        }
        model.clear();
    }

    @Override
    public void updateData() {
        dumpData();
        loadData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        search_text = view.getTextSearchList();
        dumpData();
        if (Filters.isNullOrBlank(search_text)) {
            view.setCountElements(0);
            return;
        }

        List<T> list = memo_cache.getList(o -> {
            return o.getId().equals(search_text) || Filters
                    .clearText(o.toString())
                    .contains(search_text);
        });
        if (list.isEmpty()) {
            dumpData();
            view.setCountElements(0);
            return;
        }
        view.setCountElements(list.size());
        list.forEach((i) -> {
            model.addElement(i);
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() != 2) {
            return;
        }
        int index = view.getList().getSelectedIndex();
        if (index < 0 || index >= view.getList().getModel().getSize()) {
            return;
        }
        view.setScreenListInfo();
        view.getListModel().removeAllElements();
        view.getTextComponentList().setText(null);
    }
}
