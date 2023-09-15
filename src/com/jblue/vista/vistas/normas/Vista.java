/*
 * Copyright (C) 2023 jp
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
package com.jblue.vista.vistas.normas;

import com.jblue.util.cache.MemoCache;
import com.jblue.vista.normas.SuperVista;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 *
 * @author jp
 * @param <T>
 */
public class Vista extends SuperVista {

    protected MemoCache memo_cache;
    protected ImageIcon icon;

    @Override
    protected void llamable() {
    }

    @Override
    public void componentesEstadoInicial() {
    }

    public ArrayList<JMenu> getMenu() {
        return null;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = (ImageIcon) icon;
    }

    public MemoCache getMemo_cache() {
        return memo_cache;
    }

    public void setMemo_cache(MemoCache memo_cache) {
        this.memo_cache = memo_cache;
    }

}
