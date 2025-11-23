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
package jsoftware.com.jblue.model.l4b;

import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.sys.app.AppConfig;
import java.time.LocalDateTime;

/**
 *
 * @author juanp
 */
public class LoginRulers {

    public static boolean isWorkTime() {
        return !AppConfig.isWorkTime();
    }

    public static boolean isDateEnd(EmployeeDTO o) {
        return o.getDateEnd() != null && LocalDateTime.now().isAfter(o.getDateEnd());
    }

    public static boolean isEmployeeNull(EmployeeDTO o) {
        return o == null;
    }
}
