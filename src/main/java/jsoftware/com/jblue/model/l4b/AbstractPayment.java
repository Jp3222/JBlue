/*
 * Copyright (C) 2025 juan pablo campos casasanero
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jsoftware.com.jblue.model.dao.PaymentListDAO;
import jsoftware.com.jblue.model.dao.PaymentsDAO;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypeDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.util.PaymentsRulers;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.sys.LaunchApp;

/**
 *
 * @author juan pablo campos casasanero
 */
public abstract class AbstractPayment implements PaymentModel {

    private static final long serialVersionUID = 1L;

    protected final Map<String, String> mov;
    protected final EmployeeDTO current_employee;
    protected final JDBConnection connection;

    protected int payment_concept;
    protected UserDTO user;
    protected WaterIntakeDTO water_intake;
    protected WaterIntakeTypeDTO water_intake_type;
    protected String payment_method;

    protected BigDecimal total_cost;
    protected BigDecimal amount_paid;
    protected BigDecimal change_amount;
    protected List<String> month_paid_list;
    protected int type_payment;

    protected StringBuilder mov_book;

    protected final PaymentsDAO payments_dao;
    protected final PaymentListDAO payments_list_dao;

    public AbstractPayment() {
        this.mov = new HashMap<>();
        this.current_employee = SystemSession.getInstancia().getCurrentEmployee();
        this.connection = (JDBConnection) LaunchApp.getInstance().getResources("connection");
        payments_dao = new PaymentsDAO(AppConfig.isLogsDev(), "PAGOS");
        payments_list_dao = new PaymentListDAO(AppConfig.isLogsDev(), "PAGOS");
    }

    protected boolean isUserNull() {
        return user == null;
    }

    protected boolean isPersonalNull() {
        return current_employee == null;
    }

    protected boolean isMontoMenor() {
        return amount_paid.compareTo(total_cost) < 0;
    }

    protected boolean isWaterIntakeNull() {
        return user.get("water_intake_object")== null;
    }

    protected int payment(int payment_type, int status) {
        
        return 0;
    }

    @Override
    public void setUser(UserDTO usuario) {
        this.user = usuario;
        this.water_intake_type = (WaterIntakeTypeDTO) usuario.get("water_intake_object");
    }

    @Override
    public void setMoneyReceived(double dinero_ingresado) {
        this.amount_paid = new BigDecimal(dinero_ingresado);
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
        return PaymentsRulers.calculateBaseTotal(
                month_paid_list.size(), 
                new BigDecimal(water_intake_type.getCurrentPrice())
        ).doubleValue();
    }

    @Override
    public void setTypePayment(int TypePayment) {
        this.type_payment = TypePayment;
    }

    @Override
    public void setWaterIntake(WaterIntakeDTO o) {
        this.water_intake = o;
    }

}
