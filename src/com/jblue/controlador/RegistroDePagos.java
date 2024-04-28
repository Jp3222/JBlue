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
package com.jblue.controlador;

import com.jblue.modelo.negocios.EnvPagosXOtros;
import com.jblue.modelo.negocios.EnvPagosXRecargos;
import com.jblue.modelo.negocios.EnvPagosXServicio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OUsuarios;
import java.util.Map;

/**
 *
 * @author jp
 */
public class RegistroDePagos {

    private static RegistroDePagos instancia;

    public static RegistroDePagos getInstancia() {
        if (instancia == null) {
            instancia = new RegistroDePagos();
        }
        return instancia;
    }

    private EnvPagosXServicio pxs = new EnvPagosXServicio();
    private EnvPagosXOtros pxo = new EnvPagosXOtros();
    private EnvPagosXRecargos pxr = new EnvPagosXRecargos();

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
    public Map<String, String> registrarPagoXServicio(OPersonal personal, OUsuarios usuario, String[] meses, double monto_ingresado) {
        pxs.setPersonal(personal);
        pxs.setUsuario(usuario);
        pxs.setMeses(meses);
        pxs.setMonto_ingresado(monto_ingresado);
        return pxs.opPago();
    }

    public Map<String, String> registrarPagosXRecargos() {
        return pxr.opPago();
    }

    public Map<String, String> registrarPagosXOtros() {
        return pxo.opPago();
    }

}
