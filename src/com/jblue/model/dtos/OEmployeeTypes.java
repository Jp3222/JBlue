/*
 * Copyright (C) 2025 juanp
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
package com.jblue.model.dtos;

import com.jblue.model.constants._Const;
import com.jblue.model.factories.CacheFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author juanp
 */
public class OEmployeeTypes extends Objects implements ForeingKeyObject, StatusObject {

    private String[] infoFK;

    public OEmployeeTypes(String[] info) {
        super(info);
        this.infoFK = info.clone();
    }

    public OEmployeeTypes() {
        this.info = null;
        this.infoFK = null;
    }

    @Override
    public String[] getInfoSinFK() {
        return infoFK;
    }

    @Override
    public void setData(String[] info) {
        super.setData(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.infoFK = info.clone();
    }

    public String getEmployeeType() {
        return info[1];
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(info[2]);
    }

    @Override
    public String getStatusString() {
        return CacheFactory.EMPLOYEE_TYPES.getList().get(getStatus()).getEmployeeType();
    }

    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

    public LocalDate getDateRegister() {
        return LocalDate.parse(info[3], _Const.DATE_TIME);
    }
}
