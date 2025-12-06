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

import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class HistoryDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public String getEmployee() {
        return get("employee").toString();
    }

    public String getDbUser() {
        return get("db_user").toString();
    }

    public String getAffectedTable() {
        return get("affected_table").toString();
    }

    public String getTypeMov() {
        return get("type_mov").toString();
    }

    public String getDescription() {
        return get("description").toString();
    }

    public String getDateRegister() {
        return get("date_register").toString();
    }
}
