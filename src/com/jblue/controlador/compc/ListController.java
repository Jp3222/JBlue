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

import com.jblue.controlador.AbstractComponentController;
import com.jblue.modelo.objetos.OUser;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.Filters;
import com.jblue.util.cache.MemoListCache;
import com.jblue.util.objetos.ForeingKeyObject;
import com.jblue.util.objetos.StatusObject;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.DefaultListModel;
import com.jblue.vista.marco.ListSearchViewModel;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public final class ListController<T extends Objeto & StatusObject & ForeingKeyObject> extends AbstractComponentController<T> {

    protected ListSearchViewModel view;
    protected DefaultListModel<T> model;
    protected String search_text;
    private final ArrayList<Predicate<T>> filters_list;

    public ListController(ListSearchViewModel view, MemoListCache<T> memo_cache) {
        super(view.getList(), memo_cache);
        this.filters_list = new ArrayList<>(15);
        this.view = view;
        model = view.getListModel();
        view.getTextComponentList().addKeyListener((KeyListener) this);
        view.getTextComponentList().addMouseListener((MouseListener) this);
        addFilterList((t) -> t.getId().equals(search_text));
        addFilterList((t) -> Filters.clearAndCheck(t.toString(), search_text));
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
            return isThisUser(o, search_text, false);
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

    public boolean isThisUser(Objeto o, String txt, boolean validateIsTitular) {
        if (o instanceof OUser a && !a.isActive()) {
            return false;
        }
//        if (!(o instanceof OUser b && validateIsTitular && b.isTitular())) {
//            return false;
//        }
        return Filters.clearAndCheck(o.getId(), txt)
                || Filters.clearAndCheck(o.toString(), txt);
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

    public void addFilterList(Predicate<T> o) {
        filters_list.add(o);
    }

}
