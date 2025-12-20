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
package jsoftware.com.jblue.views.framework;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juan pablo campos casasanero
 */
public interface TableSearchViewModel {

    static int REGISTER_VIEW = 1;

    static int CONSULT_VIEW = 2;

    JTextField getTextComponenteTable();

    String getTextSearchTable();

    JTable getTable();

    DefaultTableModel getModel();

    void setViewShow(int i);

    int getViewShow();

    <T extends JDBMapObject> void setObjectSearch(T o);

    <T extends JDBMapObject> T getObjectSearch();
    
    public void setRowsData(String... info);
    
    void setScreenTableInfo();

}
