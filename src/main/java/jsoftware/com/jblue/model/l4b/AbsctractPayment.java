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
package jsoftware.com.jblue.model.l4b;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jblue.model.dtos.OUser;
import jsoftware.com.jblue.model.dtos.OWaterIntakeTypes;
import jsoftware.com.jblue.model.dtos.OWaterIntakes;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.sys.LaunchApp;

/**
 *
 * @author juan-campos
 */
public abstract class AbsctractPayment implements PaymentModel {

    private static final long serialVersionUID = 1L;
    
    private final String[] type_payments_table = {
        _Const.PYM_SERVICE_PAYMENTS_TABLE.getTableName(),
        _Const.PYM_SURCHARGE_PAYMENTS_TABLE.getTableName(),
        _Const.PYM_OTHER_PAYMENTS_TABLE.getTableName()
    };
    
    protected final Map<String, String> mov;
    protected final OEmployee current_employee;
    protected final JDBConnection connection;
    protected String pay_query;

    protected OUser user;
    protected OWaterIntakes water_intake;
    protected OWaterIntakeTypes water_intake_type;
    protected String payment_method;
    protected double deuda;
    protected double input_money;
    protected double output_money;
    protected List<String> month_paid_list;
    protected int type_payment;

    protected StringBuilder mov_book;

    public AbsctractPayment() {
        this.mov = new HashMap<>();
        this.current_employee = SystemSession.getInstancia().getCurrentEmployee();
        this.connection = (JDBConnection) LaunchApp.getInstance().getResources("connection");
    }

    protected boolean isUserNull() {
        return user == null;
    }

    protected boolean isPersonalNull() {
        return current_employee == null;
    }

    protected boolean isMontoMenor() {
        return input_money < deuda;
    }

    protected boolean isWaterIntakeNull() {
        return user.getWaterIntakesObject() == null;
    }

    @Override
    public void setUser(OUser usuario) {
        this.user = usuario;
        this.water_intake_type = usuario.getWaterIntakesObject();
    }

    @Override
    public void setMoneyReceived(double dinero_ingresado) {
        this.input_money = dinero_ingresado;
    }

    @Override
    public void setMonthsPaid(List<String> meses_pagados) {
        this.month_paid_list = meses_pagados;
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
        return user.getWaterIntakesObject().getCurrentPrice() * month_paid_list.size();
    }

    @Override
    public void setTypePayment(int TypePayment) {
        this.type_payment = TypePayment;
    }

    @Override
    public void setWaterIntake(OWaterIntakes o) {
        this.water_intake = o;
    }

    public String getPayQuery(int type_payment) {
        StringBuilder sb = new StringBuilder(200);
        sb.append("INSERT INTO ");
        sb.append(type_payments_table[type_payment]);
        sb.append("(employee, user, water_intake, water_intake_type, price, month_name, month, status) ");
        sb.append("VALUES('?,?,?,?,?,?,?,?,)");
        return sb.toString();
    }
}
