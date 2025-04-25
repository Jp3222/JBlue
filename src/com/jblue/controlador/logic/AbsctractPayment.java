/*
 * Copyright (C) 2025 juan-campos
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
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUser;
import com.jblue.sistema.Sesion;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.framework.LaunchApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public abstract class AbsctractPayment implements PaymentModel {

    public static final String KEY_ERROR = "err_msg";
    public static final String KEY_MOVS = "mov";
    public static final String KEY_STATUS_OP = "status";
    public static final String STATUS_OK = "ok";
    public static final String STATUS_ERR = "err";

    protected final Map<String, String> mov;
    protected final OPersonal personal;
    protected OUser usuario;
    protected OTipoTomas toma;
    protected double dinero_ingresado;
    protected double deuda;
    protected double dinero_sobrante;
    protected DBConnection connection;
    protected ArrayList<String> meses_pagados;

    public AbsctractPayment() {
        this.mov = new HashMap<>();
        this.personal = Sesion.getInstancia().getUsuario();
        System.out.println(personal);
        this.connection = (DBConnection) LaunchApp.getInstance().getResources("connection");
    }

    protected boolean isUserNull() {
        return usuario == null;
    }

    protected boolean isPersonalNull() {
        return personal == null;
    }

    protected boolean isMontoMenor() {
        return dinero_ingresado < deuda;
    }

    @Override
    public void setUsuario(OUser usuario) {
        this.usuario = usuario;
        this.toma = usuario.getWaterIntakesObject();
    }

    @Override
    public void setDineroIngresado(double dinero_ingresado) {
        this.dinero_ingresado = dinero_ingresado;
    }

    @Override
    public void setMesesPagados(ArrayList<String> meses_pagados) {
        this.meses_pagados = meses_pagados;
    }
    
    

    @Override
    public Map<String, String> getMov() {
        return mov;
    }

}
