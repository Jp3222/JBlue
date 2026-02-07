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

import java.util.Map;

/**
 *
 * @author juanp
 */
public class WaterIntakeDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public WaterIntakeDTO(Map<String, Object> map) {
        super(map);
    }

    public WaterIntakeDTO(int size) {
        super(size);
    }

    public WaterIntakeDTO() {
    }

    public String getConstProcedure() {
        return get("cost_procedure").toString();
    }

    public String getWaterIntakeType() {
        return get("water_intake_type").toString();
    }

    public String getUser() {
        return get("user").toString();
    }

    public String getUserName() {
        return get("user_name").toString();
    }

    public String getStreet1() {
        return get("street1").toString();
    }

    public String getStreet2() {
        return get("street2").toString();
    }

    public String getLocation() {
        return get("location").toString();
    }

    public String getDescription() {
        return get("description").toString();
    }

    public String getLastEmployeeUpdate() {
        return get("last_employee_update").toString();
    }

}
