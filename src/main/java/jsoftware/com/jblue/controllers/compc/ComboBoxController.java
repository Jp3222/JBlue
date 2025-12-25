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
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import jsoftware.com.jblue.controllers.AbstractComponentController;
import jsoftware.com.jblue.model.dao.ListComponentDAO;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juan pablo campos casasanero
 * @param <T>
 */
public class ComboBoxController<T extends JDBMapObject> extends AbstractComponentController<T> {

    private static final long serialVersionUID = 1L;
    private ListComponentDAO<T> dao;

    public ComboBoxController(JComboBox<T> component, List<T> list_items) {
        super(component, list_items);
    }

    public ComboBoxController(JComboBox<T> componente, ListComponentDAO<T> dao) {
        super(componente);
        this.dao = dao;
    }

    @Override
    public void loadData() {
        JComboBox<T> box = getComponent();
        if (list == null || list.isEmpty()) {
            list.addAll(dao.getList());
        }
        box.addItem((T) new JDBMapObject() {
            @Override
            public String toString() {
                return "SELECCIONA ELEMENTO";
            }

        });
        for (T i : list) {
            box.addItem(i);
        }
    }

    @Override
    public void dumpData() {
        JComboBox<T> box = getComponent();
        DefaultComboBoxModel<T> model = (DefaultComboBoxModel<T>) box.getModel();
        if (model.getSize() == 0) {
            return;
        }
        box.removeAllItems();
    }

    @Override
    public void updateData() {
        dumpData();
        loadData();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

}
