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

import com.jblue.model.constants.Const;
import com.jblue.model.DBConnection;
import com.jblue.model.dtos.*;

/**
 *
 * @author juan-campos
 */
public class ConnectionFactory {

    public static DBConnection<OEmployee> getEmployees() {
        return new DBConnection(Const.EMPLOYEES);
    }

    public static DBConnection<OUser> getUser() {
        return new DBConnection(Const.USERS);
    }

    public static DBConnection<OStreet> getStreets() {
        return new DBConnection(Const.STREETS);
    }

    public static DBConnection<OWaterIntakeTypes> getWaterIntakesTypes() {
        return new DBConnection(Const.WATER_INTAKES_TYPES);
    }

    public static DBConnection<OServicePayments> getServicePayments() {
        return new DBConnection(Const.SERVICE_PAYMENTS);
    }

    public static DBConnection<OServicePayments> getSurchargePayments() {
        return new DBConnection(Const.SURCHARGE_PAYMENTS);
    }

    public static DBConnection<OtherPaymentsType> getOtherPayments() {
        return new DBConnection(Const.OTHER_PAYMENTS);
    }

    public static DBConnection<OWaterIntakes> getWaterIntakes() {
        return new DBConnection(Const.WATER_INTAKES);
    }

    public static DBConnection<OEmployeeTypes> getEmployeeTypes() {
        return new DBConnection(Const.EMPLOYEES_TYPES);
    }
}
