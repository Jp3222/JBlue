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
import com.jblue.model.JDBConnection;
import com.jblue.model.dtos.OStreet;
import com.jblue.model.dtos.OtherPaymentsType;
import com.jblue.model.dtos.OServicePayments;
import com.jblue.model.dtos.OEmployee;
import com.jblue.model.dtos.OWaterIntakeTypes;
import com.jblue.model.dtos.OUser;
import com.jblue.model.dtos.OWaterIntakes;

/**
 *
 * @author juan-campos
 */
public class ConnectionFactory {

    public static JDBConnection<OEmployee> getEmployees() {
        return new JDBConnection(Const.EMPLOYEES);
    }

    public static JDBConnection<OUser> getUser() {
        return new JDBConnection(Const.USER);
    }

    public static JDBConnection<OStreet> getStreets() {
        return new JDBConnection(Const.STREETS);
    }

    public static JDBConnection<OWaterIntakeTypes> getWaterIntakesTypes() {
        return new JDBConnection(Const.WATER_INTAKES_TYPES);
    }

    public static JDBConnection<OServicePayments> getServicePayments() {
        return new JDBConnection(Const.SERVICE_PAYMENTS);
    }

    public static JDBConnection<OServicePayments> getSurchargePayments() {
        return new JDBConnection(Const.SURCHARGE_PAYMENTS);
    }

    public static JDBConnection<OtherPaymentsType> getOtherPayments() {
        return new JDBConnection(Const.OTHER_PAYMENTS);
    }
    
    public static JDBConnection<OWaterIntakes> getWaterIntakes(){
        return new JDBConnection(Const.WATER_INTAKES);
    }
}
