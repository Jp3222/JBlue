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
package com.jblue.util.modelo.funbd;

import com.jblue.modelo.objetos.Objetos;
import com.jblue.sistema.Sistema;
import com.jblue.util.Filtros;
import com.jblue.util.modelo.objetos.Objeto;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author juan-campos
 */
public abstract class AbstraccionFunciones implements ModeloFuncionesDB {

    protected final String tabla;
    protected final String[] campos;
    protected final Conexion conexion;

    public AbstraccionFunciones(String TABLA, String[] CAMPOS) {
        this.tabla = TABLA;
        this.campos = CAMPOS;
        this.conexion = Sistema.getInstancia().getConexion();
    }

    @Override
    public boolean _INSERTAR(String[] valores) {
        try {
            //System.out.println(Arrays.toString(valores));
            return conexion.insert(tabla,
                    conexion.getCampos(campos),
                    conexion.getDatos(valores)
            );
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
        return false;
    }

    @Override
    public boolean _ELIMINAR(String where) {
        boolean retorno = false;
        try {
            if (Filtros.isNullOrBlank(where)) {
                return false;
            }
            retorno = conexion.delete(tabla, where);
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
        return retorno;

    }

    @Override
    public boolean _ACTUALIZAR(String campo, String valor, String where) {
        try {
            if (Filtros.isNullOrBlank(campo, valor, where)) {
                return false;
            }
            return conexion.update(tabla, campo, valor, where);
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);

        }
        return false;
    }

    @Override
    public boolean _ACTUALIZAR(String[] campos, String[] valores, String where) {
        try {
            if (Filtros.isNullOrBlank(campos) || Filtros.isNullOrBlank(valores) || Filtros.isNullOrBlank(where)) {
                return false;
            }
            return conexion.update(tabla,
                    conexion.getCamposDatos(campos, valores),
                    where
            );
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
        return false;
    }

    @Override
    public <T extends Objeto> Optional<ArrayList<T>> _SELECT(String campos, String where) {

        try {
            String[] arr;
            if (campos == null || campos.isBlank() || campos.contains("*")) {
                arr = this.campos;
            } else {
                arr = campos.split(",");
            }
            ResultSet rs = conexion.select(tabla, campos, where);
            String[] aux;
            int i;
            ArrayList<T> lista = new ArrayList<>();
            while (rs.next()) {
                aux = new String[arr.length];
                i = 0;
                for (String j : arr) {
                    aux[i] = rs.getString(j);
                    i++;
                }
                Objeto objeto = Objetos.getObjeto(tabla, aux);
                lista.add((T) objeto);
            }
            return Optional.of(lista);
        } catch (SQLException ex) {
            Excp.impTerminal(ex, this.getClass(), true);
        }
        return Optional.empty();
    }

    public String getTABLA() {
        return tabla;
    }

    public String[] getCampos() {
        return campos;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void retornarObj(boolean o) {
        this.obj = o;
    }

    private boolean obj;
}
