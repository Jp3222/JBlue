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
package com.jblue.model.factories;

import com.jblue.model.constants._Const;
import com.jblue.model.DBConnection;
import com.jblue.model.dtos.*;

/**
 *
 * @author juan-campos
 */
public class ConnectionFactory {

    public static DBConnection<OEmployee> getEmployees() {
        return new DBConnection(_Const.EMP_EMPLOYEES_TABLE);
    }

    public static DBConnection<OUser> getUser() {
        return new DBConnection(_Const.USR_USERS_TABLE);
    }

    public static DBConnection<OStreet> getStreets() {
        return new DBConnection(_Const.CAT_STREET_TABLE);
    }

    public static DBConnection<OWaterIntakeTypes> getWaterIntakesTypes() {
        return new DBConnection(_Const.WKI_WATER_INTAKE_TYPE_TABLE);
    }

    public static DBConnection<OServicePayments> getServicePayments() {
        return new DBConnection(_Const.PYM_SERVICE_PAYMENTS_TABLE);
    }

    public static DBConnection<OServicePayments> getSurchargePayments() {
        return new DBConnection(_Const.PYM_SURCHARGE_PAYMENTS_TABLE);
    }

    public static DBConnection<OtherPaymentsType> getOtherPayments() {
        return new DBConnection(_Const.PYM_OTHER_PAYMENTS_TABLE);
    }

    public static DBConnection<OWaterIntakes> getWaterIntakes() {
        return new DBConnection(_Const.WKI_WATER_INTAKES_TABLE);
    }

    public static DBConnection<OEmployeeTypes> getEmployeeTypes() {
        return new DBConnection(_Const.EMP_EMPLOYEE_TYPES_TABLE);
    }
}
