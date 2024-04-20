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

import java.sql.SQLException;
import com.jblue.controlador.RegistroDePagos;
import com.jblue.modelo.ConstBD;
import com.jblue.modelo.bd.FuncionesBD;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.interpad.pagos.SuperPagos;
import com.jblue.util.tiempo.Fecha;
import com.jutil.jexception.Excp;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public class EnvPagosXServicio extends SuperPagos<OPagosServicio> {

    public EnvPagosXServicio() {
        super();
        operaciones = FabricaOpraciones.getPAGOS_X_SERVICIO();
    }
    
    
    
    /**
     *
     * Metodo diseñado para registar pagos
     *
     *
     * @return retornara un arreglo cuya informacion esta contenida en el
     * siguiente orden
     * <br>
     * <br> 0 - ESTADO = "CORRECTO" O "ERRONEO"
     * <br> 1 - MESES_REGISTRADOS = "NUMERO DE MESES CORRECTOS"
     * <br> 2 - SOBRANTE
     * <br> 3 -
     *
     */
    @Override
    public Map<String, String> opPago() {
        mapa.put(LLAVE_ESTADO, VALOR_CORRECTO);

        if (usuario == null) {
            mapa.put(LLAVE_ESTADO, VALOR_INCORRECTO);
            mapa.put(LLAVE_ERROR, createErrMensaje("Usuario = Null"));
            return mapa;
        }

        OTipoTomas toma = EnvUsuario.getTipoDeTomaEnCache(usuario.getToma());
        double deuda = toma.getCosto() * meses.length;

        if (monto_ingresado < deuda) {
            mapa.put(LLAVE_ESTADO, VALOR_INCORRECTO);
            mapa.put(LLAVE_ERROR, createErrMensaje("Monto Menor"));
            return mapa;
        }

        StringBuilder sb = new StringBuilder(200);
        for (String mes : meses) {
            sb.append(crearRegisro(mes, String.valueOf(toma.getCosto())));
            sb.append("\n");
        }
        mapa.put(LLAVE_DATOS, sb.toString());
        FuncionesBD o = new FuncionesBD(ConstBD.TABLAS[6], ConstBD.BD_PAGOS_X_SERVICIO);
        try {
            o.insertCSV(sb.toString().split("\n"));
        } catch (SQLException ex) {
            mapa.put(LLAVE_ESTADO, VALOR_INCORRECTO);
            mapa.put(LLAVE_ERROR, ex.getMessage());
            System.out.println(ex.getMessage());
            Excp.impTerminal(ex, RegistroDePagos.class, true);
        }
        return mapa;
    }

    @Override
    public boolean movimientoValido() {
        return false;
    }

    @Override
    public String crearRegisro(String... args) {
        StringBuilder sb = new StringBuilder();
        char comilla = '\'';
        char coma = ',';
        sb.append(comilla).append(personal.getId()).append(comilla).append(coma);
        sb.append(comilla).append(usuario.getId()).append(comilla).append(coma);
        sb.append(comilla).append(args[0]).append(comilla).append(coma);
        sb.append(comilla).append(args[1]).append(comilla).append(coma);
        LocalDate o = Fecha.getNewFechaActual();
        sb.append(comilla).append(o.getDayOfMonth()).append(comilla).append(coma);
        sb.append(comilla).append(o.getMonthValue()).append(comilla).append(coma);
        sb.append(comilla).append(o.getYear()).append(comilla);
        return sb.toString();
    }

}
