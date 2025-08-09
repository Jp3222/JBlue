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
import com.jblue.model.JDBConnection;
import com.jblue.views.framework.SimpleView;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractDBViewController<T extends Objects> extends AbstractViewController implements DBControllerModel {

    protected final MemoListCache<T> memo_cache;
    protected final JDBConnection<T> connection;

    public AbstractDBViewController(MemoListCache<T> memo_cache) {
        this.memo_cache = memo_cache;
        this.connection = (JDBConnection<T>) memo_cache.getConnection();
    }

    protected void rmessage(SimpleView view, boolean ok) {
        String status = ok ? "Exitoso" : "Erroneo";
        JOptionPane.showMessageDialog(view,
                "Operacion %s".formatted(status),
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);
        if (ok) {
            memo_cache.reLoadData();
            view.initialState();
        }
    }
}
