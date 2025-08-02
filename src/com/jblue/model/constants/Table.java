/*
 * Copyright (C) 2025 juan-campos
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
package com.jblue.model.constants;

/**
 *
 * @author juan-campos
 */
public class Table {

    /**
     * Tabla de base de datos
     */
    private final String table;
    /**
     * Campos de base de datos
     */
    private final String[] fields;
    /**
     * Campos Graficos
     */
    private String[] graphics_field;
    /**
     * tamaño total de los campos
     */
    private int real_count_fields;
    /**
     * Formato para 
     */
    private String history_to_formtat;
    /**
     * Movimientos posibles
     */
    private String[] movs;

    public Table(String table, String[] fields, String[] graphics_field) {
        this.table = table;
        this.fields = fields;
        this.graphics_field = graphics_field;
        this.real_count_fields = fields.length;
    }

    public Table(String table, String... fields) {
        this(table, fields, fields);
    }

    public int getReal_count_fields() {
        return real_count_fields;
    }

    public String getTable() {
        return table;
    }

    public String[] getFields() {
        return fields;
    }

    public void setGraphics_field(String... graphics_field) {
        this.graphics_field = graphics_field;
    }

    public String[] getGraphics_field() {
        return graphics_field;
    }

    public void setHistoryToFormat(String txt) {
        this.history_to_formtat = txt;
    }

    public String getHistoryToFormtat() {
        return history_to_formtat;
    }

    public void setMovs(String... movs) {
        this.movs = movs;
    }

    public String[] getMovs() {
        return movs;
    }

}
