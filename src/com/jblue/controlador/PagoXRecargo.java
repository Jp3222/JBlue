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

import com.jblue.util.tools.UtilUsuario;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.util.objetos.pagos.AbstraccionCPagos;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public class PagoXRecargo extends AbstraccionCPagos {

    public boolean atrasado;

    public PagoXRecargo() {
        super(PAGOS_X_REC);
    }

    private boolean pagado;

    @Override
    public String getResgitro(String... args) {
        StringBuilder sb = new StringBuilder(400);
        final String formato = "'%s',";
        sb.append("(");
        sb.append(String.format(formato, personal.getId()));
        sb.append(String.format(formato, usuario.getId()));
        sb.append(String.format(formato, args[0]));
        sb.append(String.format(formato, args[1]));
        LocalDate o = LocalDate.now();
        sb.append(String.format(formato, o.getDayOfMonth()));
        sb.append(String.format(formato, o.getMonthValue()));
        sb.append(String.format(formato, o.getYear()));
        sb.append(args[2].replace(",", ")"));
        return sb.toString();
    }

    @Override
    public Map<String, String> excePago() {
        if (!validarPyU()) {
            return movimientos;
        }
        OTipoTomas toma = UtilUsuario.getTipoToma(usuario.getToma());
        double costo_mensual = toma.getCosto() * meses_pagados.length;

        if (dinero_ingresado < costo_mensual) {
            movimientos.put(ESTADO, VALOR_INCORRECTO);
            movimientos.put(ERROR, createErrMensaje("Monto Menor"));
            return movimientos;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < meses_pagados.length - 1) {
            sb.append(getResgitro(meses_pagados[i], String.valueOf(toma.getCosto())))
                    .append(",\n");
            i++;
        }
        sb.append(getResgitro(meses_pagados[i], String.valueOf(toma.getCosto())));
        movimientos.put(DATOS, sb.toString());
        movimientos.put(ESTADO, VALOR_CORRECTO);
        conexion.insertCol(sb);
        return movimientos;
    }

    public Map<String, String> exceRegistro() {
        if (!validarPyU()) {
            return movimientos;
        }
        OTipoTomas toma = UtilUsuario.getTipoToma(usuario.getToma());
        double costo_mensual = toma.getCosto() * meses_pagados.length;

        dinero_ingresado = toma.getRecargo();

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < meses_pagados.length - 1) {
            sb.append(getResgitro(meses_pagados[i], String.valueOf(toma.getCosto())))
                    .append(",\n");
            i++;
        }
        sb.append(getResgitro(meses_pagados[i],
                String.valueOf(toma.getCosto()),
                "0"
        ));
        movimientos.put(DATOS, sb.toString());
        movimientos.put(ESTADO, VALOR_CORRECTO);
        conexion.insertCol(sb);
        return movimientos;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public boolean isPagado() {
        return pagado;
    }

    public boolean isAtrasado() {
        return atrasado;
    }

}
