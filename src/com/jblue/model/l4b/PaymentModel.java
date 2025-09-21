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

import com.jblue.model.dtos.OUser;
import com.jblue.model.dtos.OWaterIntakes;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public interface PaymentModel {

    static final int SERVICE_PAYMENT = 1;
    static final int SURCHARGE_PAYMENT = 2;
    static final int OTHERS_PAYMENT = 3;

    static final int STATUS_PAY = 4;
    static final int STATUS_NOT_PAY = 5;
    static final int STATUS_PENDING_PAY = 6;

    static final String KEY_ERROR = "err_msg";
    static final String KEY_MOVS = "mov";
    static final String KEY_STATUS_OP = "status";
    static final String STATUS_OK = "ok";
    static final String STATUS_ERR = "err";

    /**
     *
     * @param args
     * @return
     */
    String getQuery(String args);

    /**
     *
     * @return
     */
    boolean execPayment();

    /**
     * inserta un registro por defecto(Pago pendiente)
     *
     * @return boolean si se ejecuto correctamente
     */
    boolean insertToDefault();

    /**
     * define el tipo de pago a realizar
     *
     * @param TypePayment tipo de pago a realizar
     */
    void setTypePayment(int TypePayment);

    /**
     * metodo para definir validaciones antes de ejecular un movimiento
     *
     * @return true si todas las reglas del juego se cumplen
     */
    boolean gameRulers();

    /**
     *
     * @return
     */
    Map<String, String> getMov();

    /**
     *
     * @param o
     */
    void setUser(OUser o);

    void setWaterIntake(OWaterIntakes o);

    /**
     *
     * @param d
     */
    void setMoneyReceived(double d);

    /**
     *
     * @param meses_pagados
     */
    public void setMonthsPaid(List<String> meses_pagados);

    /**
     *
     * @param mov_book
     */
    public void setMovBook(StringBuilder mov_book);

    /**
     * total a pagar
     *
     * @return el total a pagar con decimales
     */
    public double getTotal();

    /**
     *
     * @return una StringBuilder con los movimientos ejecutados
     */
    public StringBuilder getMovBook();
}
