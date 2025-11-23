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
package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.ObjectUtils;

/**
 *
 * @author juanp
 */
public class OWaterIntakes extends Objects implements ForeingKeyObject, StatusObjectModel {

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
        return values[1];
    }

    public String getWaterIntakeType() {
        return values[2];
    }

    public WaterIntakeTypesDTO getWaterIntakeTypeObject() {
        return ObjectUtils.getWaterIntakeTypeObject(getWaterIntakeType());
    }

    public String getUser() {
        return values[3];
    }

    public UserDTO getUserObject() {
        return ObjectUtils.getUser(getUser());
    }

    public String getUserName() {
        return values[4];
    }

    public String getStreet1() {
        return values[5];
    }

    public String getStreet2() {
        return values[6];
    }

    public String getLocation() {
        return values[7];
    }

    public String getDescription() {
        return values[8];
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(values[9]);
    }

    @Override
    public String getStatusString() {
        return CacheFactory.CAT_STATUS[getStatus()];
    }

    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

    public String getDateUpdate() {
        return values[10];
    }

    public String getDateRegister() {
        return values[11];
    }

    public String getDateEnd() {
        return values[12];
    }

    @Override
    public String[] getInfoSinFK() {
        return infoFk;
    }

    @Override
    public String toString() {
        return getUserName();
    }

}
