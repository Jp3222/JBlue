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
package jsoftware.com.jblue.views.framework;

import java.io.Serializable;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public interface ListSearchViewModel<T extends JDBMapObject> extends Serializable {

    JList<T> getList();

    JTextField getTextComponentList();

    String getTextSearchList();

    DefaultListModel<T> getListModel();

    void setCountElements(int count);

    int getCountElements();

    void setScreenListInfo();

    T getObjectSearch();

    void setObjectSearch(T object);

}
