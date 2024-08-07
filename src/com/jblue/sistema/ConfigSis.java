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
package com.jblue.sistema;

import com.jblue.modelo.bdconexion.Operaciones;
import com.jblue.modelo.objetos.OValores;
import com.jblue.util.fabricas.FabricaCache;
import com.jblue.util.modelo.MemoCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jp
 */
public class ConfigSis {

    private static final ConfigSis instancia = new ConfigSis();

    public static ConfigSis getInstancia() {
        return instancia;
    }

    private static final String USUARIO_MAESTRO = "root";
    private static final String DIA_DE_COBRO = "dia_cob";
    private static final String HORA_INICIO = "hr_ini";
    private static final String HORA_CIERRE = "hr_fin";

    private final String[][] CLAVES;
    private final MemoCache<OValores> memo_cache;
    private final Map<String, OValores> mapa;

    private final Operaciones<OValores> op;
    private final ArrayList<OValores> cache;

    private ConfigSis() {
        mapa = new HashMap<>();
        this.CLAVES = new String[][]{
            {USUARIO_MAESTRO, "Usuario Maestro"},
            {DIA_DE_COBRO, "Dia de Cobro"},
            {HORA_CIERRE, "Hora de Inicio"},
            {HORA_INICIO, "Hora de Cierre"}
        };
        memo_cache = FabricaCache.MC_VALORES;
        op = memo_cache.getOperaciones();
        cache = memo_cache.getLista();
        //
        addkey();
        add();
    }

    private void add() {
        String[] arr = new String[3];
        for (String[] i : CLAVES) {
            if (mapa.get(i[0]) == null) {
                arr[0] = i[0];
                arr[1] = "null";
                arr[2] = i[1];
                boolean insertar = op.insert(arr);
                if (insertar) {
                    System.out.println(String.format("CLAVE %s CREADA", arr[0]));
                    memo_cache.actualizar();
                }
            } else {
                System.out.println(String.format("CLAVE %s CONFIRMADA", i[0]));
            }
        }
        addkey();
    }

    private void addkey() {
        for (OValores i : cache) {
            mapa.put(i.getClave(), i);
        }
    }
}
