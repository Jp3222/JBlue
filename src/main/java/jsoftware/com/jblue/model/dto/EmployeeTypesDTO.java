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
import java.util.Map;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class EmployeeTypesDTO extends JDBMapObject implements StatusObjectModel {

    private static final long serialVersionUID = 1L;

    public EmployeeTypesDTO() {
        super();
    }

    public EmployeeTypesDTO(Map<String, Object> map) {
        super(map);
    }

    public EmployeeTypesDTO(int size) {
        super(size);
    }

    public String getEmployeeType() {
        return get("employee_type").toString();
    }

    @Override
    public int getStatus() {
        return Integer.valueOf(get("status").toString());
    }

    @Override
    public String getStatusString() {
        return CacheFactory.CAT_STATUS[getStatus()];
    }

    @Override
    public boolean isActive() {
        return getStatus() != 3;
    }

    public LocalDate getDateRegister() {
        return Formats.getLocalDate(get("date_register").toString());
    }
}
