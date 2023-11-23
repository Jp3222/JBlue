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

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OValores;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jp
 */
public class ConfigSis {

    public static final ConfigSis instancia = new ConfigSis();

    public static ConfigSis getInstancia() {
        return instancia;
    }

    private static final String USUARIO_MAESTRO = "root";
    private static final String DIA_DE_COBRO = "dia_cob";
    private static final String HORA_INICIO = "hr_ini";
    private static final String HORA_CIERRE = "hr_fin";

    private final String[][] CLAVES;
    private final MemoCache<OValores> memo_cache;
    private final Operaciones<OValores> op;
    private final ArrayList<OValores> cache;
    private final HashMap<String, OValores> mapa;

    public ConfigSis() {
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
        mapa = new HashMap<>(cache.size());
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
                boolean insertar = op.insertar(arr);
                if (insertar) {
                    System.out.println(String.format("Clave %s Creada", arr[0]));
                    memo_cache.actualizar();
                }
            } else {
                System.out.println(String.format("Clave %s Confirmada", i[0]));
            }
        }
        addkey();
    }

    private void addkey() {
        for (OValores i : cache) {
            System.out.println(i.getClave());
            mapa.put(i.getClave(), i);
        }
    }
}
