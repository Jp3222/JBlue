/*
 * Copyright (C) 2024 juan-campos
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
package com.jblue.controlador;

import com.jblue.modelo.ConstBD;
import com.jblue.sistema.Sistema;
import com.jblue.util.tiempo.Fecha;
import com.jutil.jbd.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan-campos
 */
public class Contabilidad {

    public static double getSaldoDelDia() {
        return getSaldo(Fecha.getDiaDelMes(),
                Fecha.getMesInt(),
                Fecha.getAñoActual());
    }

    public static double getSaldoDelMes() {
        return getSaldo(0,
                Fecha.getDiaDelMes(),
                Fecha.getAñoActual());
    }

    public static double getSaldoDelAño() {
        return getSaldo(0, 0, Fecha.getAñoActual());
    }

    public static double getSaldo(int dia, int mes, int año) {
        StringBuilder query = new StringBuilder(20);
        if (dia > 0) {
            query.append("dia = ").append(dia);
        }

        if (mes > 0) {
            if (dia > 0) {
                query.append(" and ");
            }
            query.append("mes = '").append(mes).append("'");
        }

        if (año > 0) {
            if (mes > 0) {
                query.append(" and ");
            }
            query.append("año = ").append(año);
        }
        return getSaldo(query.toString());
    }

    private static double getSaldo(String where) {
        Conexion conexion = Sistema.getInstancia().getConexion();
        double saldo = -1;
        try (ResultSet select = conexion.select(ConstBD.TABLAS[6], "monto", where)) {
            saldo = 0;
            while (select.next()) {
                saldo = Double.sum(saldo, select.getDouble(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Contabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saldo;
    }
}
