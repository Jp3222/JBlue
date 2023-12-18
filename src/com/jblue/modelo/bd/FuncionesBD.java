/*
 * Copyright (C) 2023 jp
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
package com.jblue.modelo.bd;

import com.jutil.jbd.conexion.Conexion;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author jp
 */
public class FuncionesBD {

    private final Conexion conexion;
    private final String tabla;
    private final String[] campos;
    private final int no_campos;
    //
    private final char[] SIMBOLOS;
    private final String sql_insert;

    public FuncionesBD(String tabla, String[] campos) {
        this.sql_insert = "insert into %s(%s) values %s;";
        this.SIMBOLOS = new char[]{'(', ')', ',', ';'};
        this.conexion = Conexion.getInstancia();
        this.tabla = tabla;
        this.campos = campos;
        this.no_campos = campos.length;

    }

    public void insert(String[] datos) throws SQLException {
        _insert(datos, true);
    }

    public void insertConID(String[] datos) throws SQLException {
        _insert(datos, false);
    }

    public boolean insertCSV(String[] data) throws SQLException {
        if (data == null || data.length == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder(data.length * data[0].length());

        int i = 0;
        while (i < data.length - 1) {
            sb.append("(").append(data[i]).append(")").append(",");
            i++;
        }
        sb.append("(").append(data[i]).append(")");
        System.out.println(getStringCamposSinID());
        System.out.println(sb.toString());
        
        _insertColl(sb.toString(), true);
        return false;
    }

    private boolean _insertColl(String datos, boolean omitirID) throws SQLException {
        String campos_aux;
        if (omitirID) {
            campos_aux = getStringCamposSinID();
        } else {
            campos_aux = getStringCampos();
        }
        return conexion.instruccion(String.format(sql_insert, tabla, campos_aux, datos));
    }

    private void _insert(String[] datos, boolean omitirID) throws SQLException {
        String campos_aux;
        if (omitirID) {
            campos_aux = getStringCamposSinID();
        } else {
            campos_aux = getStringCampos();
        }
        
        conexion.insert(tabla, conexion.getCampos(campos_aux), conexion.getDatos(datos));
    }

    private void update() {
    }

    private void select() {
    }

    private void delete() {
    }

    private String[] omitirID() {
        return Arrays.copyOfRange(campos, 1, no_campos);
    }

    private String getStringCampos() {
        return conexion.getCampos(campos);
    }

    private String getStringCamposSinID() {
        return conexion.getCampos(omitirID());
    }
}
