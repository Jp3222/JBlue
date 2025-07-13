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

import com.jblue.modelo.objetos.OUser;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public interface PaymentModel {

    String getQuery(String args);

    boolean execPayment();

    boolean gameRulers();

    Map<String, String> getMov();

    void setUsuario(OUser o);

    void setDineroIngresado(double d);

    public void setMesesPagados(List<String> meses_pagados);

    public void setMovBook(StringBuilder mov_book);

    public double getTotal();
    public StringBuilder getMovBook();
    }
