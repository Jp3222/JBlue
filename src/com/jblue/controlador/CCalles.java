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
package com.jblue.controlador;

import com.jblue.modelo.cache.MemoListCache;
import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.fabricas.FabricaFuncionesBD;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.objetos.ObjetoFK;
import com.jutil.swingw.modelos.JTableModel;
import java.util.ArrayList;

/**
 *
 * @author juan-campos
 */
public class CCalles {

    private static FuncionesBD<OCalles> op = FabricaFuncionesBD.getCalles();

    public static void save(OCalles obj, String[] o) {
        if (obj == null) {
            save(o);
        } else {
            update(obj.getId(), o);
        }
    }

    public static void save(String[] data) {
        op.insert(data);
    }

    public static void update(String id, String[] o) {
        op.updateByData(o, "id = '%s'".formatted(id));
    }

    public static void loadTableData(JTableModel model, MemoListCache<OCalles> cache) {
        ArrayList<OCalles> list = cache.getList();
        list.forEach(i -> model.addRow(i.getInfo()));
    }

    public static void dumpData(JTableModel model) {
        if (model.isRowsEmpty()) {
            return;
        }
        model.removeAllRows();
    }
}
