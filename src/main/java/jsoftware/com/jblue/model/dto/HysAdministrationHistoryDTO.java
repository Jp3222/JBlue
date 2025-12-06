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

/**
 * Data Transfer Object (DTO) para la información de una Administración. Sigue
 * el patrón de indexación de datos de la clase Objects.
 *
 * @author jp
 */
public class HysAdministrationHistoryDTO extends AuditableObjectMap {

    public String getYearOfAdministration() {
        return get("year_of_administration").toString();
    }

    public String getRoot() {
        return get("root").toString();
    }

    public String getAdministrator() {
        return get("administrator").toString();
    }

    public String getPresident() {
        return get("president").toString();
    }

    public String getTeasurer() {
        return get("treasurer").toString();
    }

    public String getSecretary() {
        return get("secretary").toString();
    }

    public String getPlumber() {
        return get("plumber").toString();
    }

    public String getInternt1() {
        return get("intern_1").toString();
    }

    public String getInternt2() {
        return get("intern_2").toString();
    }

    public String getInternt3() {
        return get("intern_3").toString();
    }

    public String getDescription() {
        return get("description").toString();
    }
}
