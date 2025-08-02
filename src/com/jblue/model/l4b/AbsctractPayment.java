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
package com.jblue.model.l4b;

import com.jblue.model.dtos.OEmployee;
import com.jblue.model.dtos.OWaterIntakeTypes;
import com.jblue.model.dtos.OUser;
import com.jblue.sys.SystemSession;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.framework.LaunchApp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public abstract class AbsctractPayment implements PaymentModel {

    protected final Map<String, String> mov;
    protected final OEmployee personal;
    protected OUser usuario;
    protected OWaterIntakeTypes toma;
    protected double dinero_ingresado;
    protected double deuda;
    protected double dinero_sobrante;
    protected DBConnection connection;
    protected List<String> meses_pagados;
    protected int type_payment;
    protected String pay_query;
    protected String default_query;
    

    protected StringBuilder mov_book;

    public AbsctractPayment() {
        this.mov = new HashMap<>();
        this.personal = SystemSession.getInstancia().getUsuario();
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

    protected boolean isWaterIntakeNull() {
        return usuario.getWaterIntakesObject() == null;
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
    public void setMesesPagados(List<String> meses_pagados) {
        this.meses_pagados = meses_pagados;
    }

    @Override
    public Map<String, String> getMov() {
        return mov;
    }

    @Override
    public void setMovBook(StringBuilder mov_book) {
        this.mov_book = mov_book;
    }

    @Override
    public StringBuilder getMovBook() {
        return mov_book;
    }

    @Override
    public double getTotal() {
        return usuario.getWaterIntakesObject().getPrice() * meses_pagados.size();
    }

    @Override
    public void setTypePayment(int TypePayment) {
        this.type_payment = TypePayment;
    }

}
