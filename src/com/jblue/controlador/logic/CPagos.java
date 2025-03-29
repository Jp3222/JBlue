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
package com.jblue.controlador.logic;

import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OUsuarios;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public class CPagos {

    private final static CPagos instancia = new CPagos();

    public static CPagos getInstancia() {
        return instancia;
    }

    private final PagosXServicio pagos_x_servicio;
    private final PagoXRecargo pagos_x_recargo;

    CPagos() {
        this.pagos_x_servicio = new PagosXServicio();
        this.pagos_x_recargo = new PagoXRecargo();
    }

    public Map<String, String> regPagoXServicio(OPersonal personal, OUsuarios usuario, String[] meses, double monto_ingresado) {
        pagos_x_servicio.setPersonal(personal);
        pagos_x_servicio.setUsuario(usuario);
        pagos_x_servicio.setMeses_pagados(meses);
        pagos_x_servicio.setDinero_ingresado(monto_ingresado);
        return pagos_x_servicio.excePago();
    }
}
