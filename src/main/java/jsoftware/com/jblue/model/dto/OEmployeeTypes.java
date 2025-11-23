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

import java.time.LocalDate;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBArrayObject;

/**
 *
 * @author juanp
 */
public class OEmployeeTypes extends JDBArrayObject implements ForeingKeyObject, StatusObjectModel {

    private String[] infoFK;

    public OEmployeeTypes(String[] info) {
        super(info);
        this.infoFK = info.clone();
    }

    public OEmployeeTypes() {
        super();
        this.infoFK = null;
    }

    @Override
    public String[] getInfoSinFK() {
        return infoFK;
    }

    public String getEmployeeType() {
        return values[1].toString();
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(values[2].toString());
    }

    @Override
    public String getStatusString() {
        return CacheFactory.EMPLOYEE_TYPES.getList().get(getStatus()).getEmployeeType();
    }

    @Override
    public boolean isActive() {
        return getStatus() != 3;
    }

    public LocalDate getDateRegister() {
        return Formats.getLocalDate(values[3].toString());
    }
}
