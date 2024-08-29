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
package com.jblue.modelo.dbconexion;

import com.jblue.modelo.absobj.Objeto;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class FuncionesBD<T extends Objeto> extends AbstraccionFunciones<T> {

    public FuncionesBD(String TABLA, String[] CAMPOS) {
        super(TABLA, CAMPOS);
    }

    public boolean _INSERTAR_SIN_ID(String... valores) {
        boolean retorno = false;
        try {
            retorno = conexion.insert(tabla,
                    conexion.getCampos(Arrays.copyOfRange(campos, 1, campos.length)),
                    conexion.getDatos(valores));
        } catch (SQLException ex) {
            Logger.getLogger(FuncionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean _INSERTAR(StringBuilder valores) {
        boolean retorno = false;
        try {
            System.out.println(conexion.getCampos(Arrays.copyOfRange(campos, 1, campos.length)));
            System.out.println(valores);
            retorno = conexion.insert(tabla,
                    conexion.getCampos(Arrays.copyOfRange(campos, 1, campos.length)),
                    valores);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean _ACTUALIZAR_SIN_ID(String[] valores, String where) {
        return update(
                Arrays.copyOfRange(campos, 1, campos.length),
                valores,
                where);
    }

}
