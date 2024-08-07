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
package com.jblue.util.fabricas;

import com.jblue.modelo.ConstBD;
import com.jblue.modelo.bdconexion.FuncionesBD;

/**
 *
 * @author juan-campos
 */
public class FabricaFuncionesBD {

    public static FuncionesBD getPersonal() {
        return new FuncionesBD(ConstBD.TABLAS[0], ConstBD.TABLA_USUARIOS);
    }

    public static FuncionesBD getUsuarios() {
        return new FuncionesBD(ConstBD.TABLAS[1], ConstBD.TABLA_PERSONAL);
    }

    public static FuncionesBD getCalles() {
        return new FuncionesBD(ConstBD.TABLAS[2], ConstBD.TABLA_CALLES);
    }

    public static FuncionesBD getTipoTomas() {
        return new FuncionesBD(ConstBD.TABLAS[3], ConstBD.TABLA_TIPOS_DE_TOMAS);
    }

    public static FuncionesBD getHistorialMovimientos() {
        return new FuncionesBD(ConstBD.TABLAS[4], ConstBD.TABLA_HISTORIAL_MOVIMIENTOS);
    }

    public static FuncionesBD getMovimientos() {
        return new FuncionesBD(ConstBD.TABLAS[5], ConstBD.TABLA_MOVIMIENTOS);
    }

    public static FuncionesBD getPagosXServicio() {
        return new FuncionesBD(ConstBD.TABLAS[6], ConstBD.TABLA_PAGOS_X_SERVICIO);
    }

    public static FuncionesBD getPagosXRecargos() {
        return new FuncionesBD(ConstBD.TABLAS[7], ConstBD.TABLA_PAGOS_X_SERVICIO);
    }

    public static FuncionesBD getPagosXOtros() {
        return new FuncionesBD(ConstBD.TABLAS[8], ConstBD.TABLA_PAGOS_X_SERVICIO);
    }

    public static FuncionesBD getVariablesSistema() {
        return new FuncionesBD(ConstBD.TABLAS[9], ConstBD.TABLA_VAR_SIS);
    }

}
