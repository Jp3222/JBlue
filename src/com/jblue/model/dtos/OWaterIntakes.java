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

import com.jblue.model.factories.CacheFactory;

/**
 *
 * @author juanp
 */
public class OWaterIntakes extends Objects implements ForeingKeyObject, StatusObject {

    private String[] infoFk;

    public OWaterIntakes(String[] info) {
        super(info);
        infoFk = info.clone();
    }

    public OWaterIntakes() {
        super();
        infoFk = null;
    }

    public String getConstProcedure() {
        return info[1];
    }

    public String getWaterIntakeType() {
        return info[2];
    }

    public String getProcedurePayemnts() {
        return info[3];
    }

    public String getUser() {
        return info[4];
    }

    public String getStreet1() {
        return info[5];
    }

    public String getStreet2() {
        return info[6];
    }

    public String getLocation() {
        return info[7];
    }

    public String getDescription() {
        return info[8];
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(info[9]);
    }

    @Override
    public String getStatusString() {
        return CacheFactory.ITEMS_STATUS_CAT[getStatus()];
    }

    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

    @Override
    public String[] getInfoSinFK() {
        return infoFk;
    }

    public String getDateUpdate() {
        return info[10];
    }

    public String getDateRegister() {
        return info[11];
    }

    public String getDateEnd() {
        return info[12];
    }
}
