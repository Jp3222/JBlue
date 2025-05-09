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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author juanp
 */
public final class OParameter extends Objeto {

    public OParameter(String... info) {
        super(info);
    }

    public OParameter() {
        super();
    }

    public String getParameter() {
        return info[1];
    }

    public String getDescription() {
        return info[2];
    }

    public Object getValue() {
        return info[3];
    }

    public TypesValues getTypeValue() {
        return new TypesValues();
    }

    /**
     *
     * @return 1 si el usuario esta "activo", 2 si el usuario esta "inactivo" o
     * 3 si el usuario esta de "baja"
     */
    public int getStatus() {
        return Integer.parseInt(info[8]);
    }

    public String getStatusString() {
        return switch (getStatus()) {
            case 1:
                yield "Activo";
            case 2:
                yield "Inactivo";
            case 3:
                yield "Baja";
            default:
                throw new AssertionError();
        };
    }

    public boolean isActive() {
        return getStatus() == 1;
    }

    public boolean isDelete() {
        return getStatus() == 3;
    }

    public String getDataType() {
        return info[0];
    }

    public String getDateUpdate() {
        return info[0];
    }

    public String getDateRegister() {
        return info[0];
    }

    public final class TypesValues {

        private TypesValues() {
        }

        public Object getValueObject() {
            return switch (info[5]) {
                case "TEXT":
                    yield getStringValue();
                case "BOOL":
                    yield getBooleanValue();
                case "INT":
                    yield getIntValue();
                case "DATE_TIME":
                    yield getTimeDateValue();
                case "DATE":
                    yield getTimeValue();
                case "TIME":
                    yield getDateValue();
                default:
                    yield getObject();
            };
        }

        public Object getObject() {
            return info[3];
        }

        public int getIntValue() {
            return Integer.parseInt(info[3]);
        }

        public String getStringValue() {
            return info[3];
        }

        public boolean getBooleanValue() {
            return Boolean.parseBoolean(info[3]);
        }

        public LocalDateTime getTimeDateValue() {
            return LocalDateTime.parse(info[3], DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        }

        public LocalTime getTimeValue() {
            return LocalTime.parse(info[3], DateTimeFormatter.ofPattern("hh:mm:ss"));
        }

        public LocalDate getDateValue() {
            return LocalDate.parse(info[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

    }
}
