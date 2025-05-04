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
package com.jblue.modelo.objetos;

/**
 *
 * @author juanp
 */
public enum OHistory {
    START_SESSION("START_SESSION"),
    END_SESSION("END_SESSION"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    LOGIC_DELETE("LOGIC_DELETE"),
    DELETE("DELETE"),
    SELECT("SELECT");

    private String employee;
    private final String type;
    private StringBuilder description;

    private OHistory(String type) {
        this.type = type;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void addDescription(String mov) {
        this.description.append(mov);
    }

    public void remove() {
        this.description.delete(0, description.length());
    }

    public void setDescription(StringBuilder description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder values = new StringBuilder("(");
        values.append("'").append(employee).append("',");
        values.append("'").append(type).append("',");
        values.append("'").append(description.toString()).append("')");
        return values.toString();
    }

}
