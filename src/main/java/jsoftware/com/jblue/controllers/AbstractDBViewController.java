/*
 * Copyright (C) 2025 juan-campos
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
package jsoftware.com.jblue.controllers;

import javax.swing.JOptionPane;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jblue.views.framework.SimpleView;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.db.model.JDBObject;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractDBViewController<T extends JDBObject> extends AbstractViewController implements DBControllerModel {

    private static final long serialVersionUID = 1L;
    protected JDBConnection conn;
    protected MemoListCache<T> memo_cache;

    public AbstractDBViewController(MemoListCache<T> memo_cache) {
        this(memo_cache.getConnection(), memo_cache);
    }

    public AbstractDBViewController(JDBConnection conn, MemoListCache<T> memo_cache) {
        this.conn = conn;
        this.memo_cache = memo_cache;
    }

    /**
     * Este metodo reinicia la cache y la vista, define el estado de la
     * operacion y lanza un mensaje
     *
     * @param view
     * @param mov
     */
    protected void returnMessage(SimpleView view, boolean mov, String msg) {
        if (mov) {
            memo_cache.reLoadData();
            view.initialState();
        }
        returnMessage(view, msg);
    }

    /**
     * Este metodo reinicia la cache y la vista, define el estado de la
     * operacion y lanza un mensaje
     *
     * @param view
     * @param mov
     */
    protected void returnMessage(SimpleView view, boolean mov) {
        String status = mov ? "Exitosa" : "Erronea";
        if (mov) {
            memo_cache.reLoadData();
            view.initialState();
        }
        returnMessage(view, "Operacion %s".formatted(status));
    }

    /**
     * Este metodo solo lanza un mensaje
     *
     * @param view
     * @param msg
     */
    public void returnMessage(SimpleView view, String msg) {
        JOptionPane.showMessageDialog(view,
                msg,
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);

    }
}
