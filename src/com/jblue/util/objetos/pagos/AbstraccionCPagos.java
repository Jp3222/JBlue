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
package com.jblue.util.objetos.pagos;

import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.fabricas.FabricaFuncionesBD;
import com.jutil.framework.LaunchApp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public abstract class AbstraccionCPagos implements ModeloCPagos {

    protected static final int PAGOS_X_SER = 0;
    protected static final int PAGOS_X_REC = 1;
    protected static final int PAGOS_X_OTR = 2;

    protected OUsuarios usuario;
    protected OPersonal personal;
    protected Map<String, String> movimientos;
    protected String[] meses_pagados;
    protected double dinero_ingresado;
    protected double dinero_sobrante;
    protected JDBConnection conexion;

    public AbstraccionCPagos(int tipo_pago) {
        conexion = (JDBConnection) switch (tipo_pago) {
            case 1:
                yield LaunchApp.getInstance().getResources("connnection");
            case 2:
                yield FabricaFuncionesBD.getPagosXOtros();
            default:
                yield FabricaFuncionesBD.getPagosXServicio();
        };
        movimientos = new HashMap<>(TODAS_LLAVES.length + 2);
    }

    protected boolean validarPyU() {
        return !isPersonalNull() && !isUsuarioNull();
    }

    private boolean isPersonalNull() {
        if (personal == null || personal.isEmpty()) {
            movimientos.put(ESTADO, VALOR_INCORRECTO);
            movimientos.put(ERROR, createErrMensaje("Personal == NULL"));
            return true;
        }
        return false;
    }

    private boolean isUsuarioNull() {
        if (usuario == null || usuario.isEmpty()) {
            movimientos.put(ESTADO, VALOR_INCORRECTO);
            movimientos.put(ERROR, createErrMensaje("Usuario == NULL"));
            return true;
        }
        return false;
    }

    public String[] getMeses_pagados() {
        return meses_pagados;
    }

    public void setMeses_pagados(String[] meses_pagados) {
        this.meses_pagados = meses_pagados;
    }

    public double getDinero_ingresado() {
        return dinero_ingresado;
    }

    public void setDinero_ingresado(double dinero_ingresado) {
        this.dinero_ingresado = dinero_ingresado;
    }

    public double getDinero_sobrante() {
        return dinero_sobrante;
    }

    public void setPersonal(OPersonal personal) {
        this.personal = personal;
    }

    public void setUsuario(OUsuarios usuario) {
        this.usuario = usuario;
    }

}
