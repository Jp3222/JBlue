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
package com.jblue.modelo.negocios;

import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.tiempo.Fecha;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jp
 */
public class RegistroDePagos {

    public static String LLAVE_ESTADO = "ESTADO";
    public static String LLAVE_DATOS = "DATOS";
    public static String LLAVE_ERROR = "ERROR";
    public static String LLAVE_DINERO_SOBRANTE = "DINERO-INGRESADO";
    public static String LLAVE_DINERO_INGRESADO = "DINERO-SOBRANTE";

    public static String VALOR_CORRECTO = "CORRECTO";
    public static String VALOR_INCORRECTO = "INCORRECTO";

    /**
     *
     * Metodo diseñado para registar pagos
     *
     * @param personal - personal que esta haciendo uso del programa
     * @param usuario - usuario a quien se le registraran los pagos.
     * @param meses - meses que se registraran
     * @param monto_ingresado - dinero ingresado por el usuario
     * @return retornara un arreglo cuya informacion esta contenida en el
     * siguiente orden
     * <br>
     * <br> 0 - ESTADO = "CORRECTO" O "ERRONEO"
     * <br> 1 - MESES_REGISTRADOS = "NUMERO DE MESES CORRECTOS"
     * <br> 2 - SOBRANTE
     * <br> 3 -
     *
     */
    public static Map<String, String> registrarPagos(OPersonal personal, OUsuarios usuario, String[] meses, double monto_ingresado) {
        Map<String, String> mapa = new HashMap<>(10);
        mapa.put(LLAVE_ESTADO, VALOR_CORRECTO);
        if (usuario == null) {
            mapa.put(LLAVE_ESTADO, VALOR_INCORRECTO);
            mapa.put(LLAVE_ERROR, "ERROR: USUARIO = NULL");
            return mapa;
        }

        OTipoTomas toma = EnvUsuario.getTipoDeTomaEnCache(usuario.getToma());
        double deuda = toma.getCosto() * meses.length;

        if (monto_ingresado < deuda) {
            mapa.put(LLAVE_ESTADO, VALOR_INCORRECTO);
            mapa.put(LLAVE_ERROR, "ERROR: Monto Menor");
            return mapa;
        }
        
        StringBuilder sb = new StringBuilder(200);
        for (String mes : meses) {
            sb.append(getRegistroDePago(personal, usuario, mes, toma.getCosto()));
            sb.append("\n");
        }
        mapa.put(LLAVE_DATOS, sb.toString());

        return mapa;
    }

    private static String getRegistroDePago(OPersonal personal, OUsuarios usuario, String mes, double monto) {
        StringBuilder sb = new StringBuilder();
        char comilla = '\'';
        char coma = ',';
        sb.append(comilla).append(personal.getId()).append(comilla).append(coma);
        sb.append(comilla).append(usuario.getId()).append(comilla).append(coma);
        sb.append(comilla).append(mes).append(comilla).append(coma);
        sb.append(comilla).append(monto).append(comilla).append(coma);
        LocalDate o = Fecha.getNewFechaActual();
        sb.append(comilla).append(o.getDayOfMonth()).append(comilla).append(coma);
        sb.append(comilla).append(Fecha.MESES[Fecha.getMesInt()]).append(comilla).append(coma);
        sb.append(comilla).append(o.getYear()).append(comilla);
        return sb.toString();

    }

}
