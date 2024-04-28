/*
 * Copyright (C) 2023 juan-campos
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
package com.jblue.modelo.negocios;

import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.objetos.OPagosRecargos;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.interpad.pagos.SuperPagos;
import com.jblue.util.tiempo.Fecha;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public class EnvPagosXRecargos extends SuperPagos<OPagosRecargos> {

    private int estado;
    private double deuda;

    public EnvPagosXRecargos() {
        super();
        operaciones = FabricaOpraciones.getPAGOS_X_RECARGOS();
    }

    @Override
    public Map<String, String> opPago() {
        mapa.put(LLAVE_ESTADO, VALOR_CORRECTO);
        return null;

    }

    @Override
    public boolean movimientoValido() {
        if (personal == null) {
            mapa.put(LLAVE_ESTADO, VALOR_INCORRECTO);
        }
        return true;
    }

    @Override
    public String crearRegisro(String... args) {
        StringBuilder sb = new StringBuilder();
        char comilla = '\'';
        char coma = ',';
        sb.append(formatoReg(personal.getId())).append(coma);
        sb.append(comilla).append(usuario.getId()).append(comilla).append(coma);
        sb.append(comilla).append(args[0]).append(comilla).append(coma);
        sb.append(comilla).append(args[1]).append(comilla).append(coma);
        LocalDate o = Fecha.getNewFechaActual();
        sb.append(comilla).append(o.getDayOfMonth()).append(comilla).append(coma);
        sb.append(comilla).append(o.getMonthValue()).append(comilla).append(coma);
        sb.append(comilla).append(o.getYear()).append(comilla);
        sb.append(comilla).append(estado).append(comilla);
        return sb.toString();
    }

    private double getDeuda() {
        String toma = usuario.getToma();
        OTipoTomas tipo_de_toma = EnvUsuario.getTipoDeTomaEnCache(toma);
        ArrayList<OPagosRecargos> lista = operaciones.getLista(String.format(
                "usuario = '%s' and año = '%s' and estado = '%s'",
                usuario.getId(),
                LocalDate.now().getYear(), "-1"
        ));
        for (OPagosRecargos i : lista) {
            deuda += i.getMonto();
        }
        return deuda;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
