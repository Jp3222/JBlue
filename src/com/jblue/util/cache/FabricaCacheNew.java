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
package com.jblue.util.cache;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.OValores;
import com.jblue.modelo.objetos.sucls.Objeto;
import static com.jblue.util.cache.FabricaOpraciones.CALLES;
import static com.jblue.util.cache.FabricaOpraciones.PERSONAL;
import static com.jblue.util.cache.FabricaOpraciones.TIPOS_DE_TOMAS;
import static com.jblue.util.cache.FabricaOpraciones.USUARIOS;
import static com.jblue.util.cache.FabricaOpraciones.VALORES;

/**
 *
 * @author juan-campos
 */
public class FabricaCacheNew {

    private static FabricaCacheNew INSTANCIA;

    public synchronized static FabricaCacheNew getINSTANCIA() {
        if (INSTANCIA == null) {
            INSTANCIA = new FabricaCacheNew();
        }
        return INSTANCIA;
    }

    public static boolean cache;
    public final MemoCache<OPersonal> MC_PERSONAL;
    public MemoCache<OCalles> MC_CALLES;
    public MemoCache<OTipoTomas> MC_TIPOS_DE_TOMAS;
    public MemoCache<OUsuarios> MC_USUARIOS;
    public MemoCache<OValores> MC_VALORES;

    private FabricaCacheNew() {
        this.MC_VALORES = new MemoCache(VALORES);
        this.MC_USUARIOS = new MemoCache(USUARIOS);
        this.MC_CALLES = new MemoCache(CALLES);
        this.MC_TIPOS_DE_TOMAS = new MemoCache(TIPOS_DE_TOMAS);
        this.MC_PERSONAL = new MemoCache(PERSONAL);
    }

    public static boolean isCache() {
        return cache;
    }

    public MemoCache<OPersonal> getMC_PERSONAL() {
        return MC_PERSONAL;
    }

    public MemoCache<OCalles> getMC_CALLES() {
        return MC_CALLES;
    }

    public MemoCache<OTipoTomas> getMC_TIPOS_DE_TOMAS() {
        return MC_TIPOS_DE_TOMAS;
    }

    public MemoCache<OUsuarios> getMC_USUARIOS() {
        return MC_USUARIOS;
    }

    public MemoCache<OValores> getMC_VALORES() {
        return MC_VALORES;
    }

    public <T extends Objeto> MemoCache<T> constMemoCach(Class<T> c, Operaciones<T> op) {
        MemoCache<T> o = new MemoCache<>(op);
        return o;
    }
}
