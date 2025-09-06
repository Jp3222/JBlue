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
package com.jblue.controllers;

import com.jblue.model.dtos.Objects;
import com.jblue.util.cache.MemoListCache;
import com.jblue.model.DBConnection;
import com.jblue.model.constants.Const;
import com.jblue.model.constants.LogBookFormats;
import com.jblue.sys.SystemSession;
import com.jblue.views.framework.SimpleView;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractDBViewController<T extends Objects> extends AbstractViewController implements DBControllerModel {

    protected final MemoListCache<T> memo_cache;
    protected final DBConnection<T> connection;

    public AbstractDBViewController(MemoListCache<T> memo_cache) {
        this.memo_cache = memo_cache;
        this.connection = (DBConnection<T>) memo_cache.getConnection();
    }

    public void rmessage(SimpleView view, boolean mov) {
        rmessage(view, mov, -1, null);
    }

    protected void rmessage(SimpleView view, boolean mov, int mov_type, String description) {
        String status = mov ? "Exitosa" : "Erronea";
        if (mov_type > 0) {
            SystemSession.getInstancia().register(mov_type, description);
        }
        if (mov) {
            memo_cache.reLoadData();
            view.initialState();
        }
        JOptionPane.showMessageDialog(view,
                "Operacion %s".formatted(status),
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
