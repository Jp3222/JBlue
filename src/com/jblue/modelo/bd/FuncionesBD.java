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
import com.jutil.jexception.Excp;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final char[] sim;
    private final String sql_insert;

    public FuncionesBD(String tabla, String[] campos) {
        this.sql_insert = "insert into %s (%s)values%s;";
        this.sim = new char[]{'(', ')', ',', ';'};
        this.conexion = Conexion.getInstancia();
        this.tabla = tabla;
        this.campos = campos;
        this.no_campos = campos.length;

    }

    public void insert(String[] datos) {
        _insert(datos, true);
    }

    public void insertConID(String[] datos) {
        _insert(datos, false);
    }

    public String insertCsvData(String[] data) {
        try {
            int capacidad = data.length * data[0].length();
            StringBuilder sb = new StringBuilder(capacidad);
            int i = 0;
            while (i < data.length - 1) {
                sb.append(sim[0]).append(data[i]).append(sim[1]).append(sim[2]);
                i++;
            }
            sb.append(sim[0]).append(data[i]).append(sim[1]);

            conexion.instruccion(
                    String.format(sql_insert,
                            tabla,
                            Arrays.toString(omitirID()),
                            sb.toString())
            );
            return sb.toString();
        } catch (SQLException ex) {
            Excp.impTerminal(ex, getClass(), false);
        }
        return null;
    }

    private void _insert(String[] datos, boolean omitirID) {
        try {
            String[] campos_aux;
            if (omitirID) {
                campos_aux = omitirID();
            } else {
                campos_aux = campos;
            }
            conexion.insert(tabla, conexion.getCampos(campos_aux), conexion.getDatos(datos));
        } catch (SQLException ex) {
            Logger.getLogger(FuncionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
