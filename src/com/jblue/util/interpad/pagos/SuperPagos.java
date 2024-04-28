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
package com.jblue.util.interpad.pagos;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.sucls.OPagos;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class SuperPagos<T extends OPagos> implements LlavesTipoMov {

    protected OUsuarios usuario;
    protected OPersonal personal;
    protected Map<String, String> mapa;
    protected String[] meses;
    protected double monto_ingresado;
    protected double monto_sobrante;
    protected Operaciones<T> operaciones;

    public SuperPagos() {
        this.mapa = new HashMap<>(10);
        mapa.put(LLAVE_ESTADO, VALOR_CORRECTO);
    }

    public abstract Map<String, String> opPago();

    public abstract boolean movimientoValido();

    public abstract String crearRegisro(String... argumentos);

    public OUsuarios getUsuario() {
        return usuario;
    }

    public boolean isUsuarioNull() {
        return usuario == null;
    }

    public boolean isPersonalNull() {
        return personal == null;
    }

    public void setUsuario(OUsuarios usuario) {
        this.usuario = usuario;
    }

    public OPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(OPersonal personal) {
        this.personal = personal;
    }

    public Map<String, String> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, String> mapa) {
        this.mapa = mapa;
    }

    public String[] getMeses() {
        return meses;
    }

    public void setMeses(String[] meses) {
        this.meses = meses;
    }

    public double getMonto_ingresado() {
        return monto_ingresado;
    }

    public void setMonto_ingresado(double monto_ingresado) {
        this.monto_ingresado = monto_ingresado;
    }

    public double getMonto_sobrante() {
        return monto_sobrante;
    }

    public void setMonto_sobrante(double monto_sobrante) {
        this.monto_sobrante = monto_sobrante;
    }

    protected String formatoReg(String o) {
        return String.format("'%s'", o);
    }

}
