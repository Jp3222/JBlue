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

import com.jblue.util.tools.ObjetoUtil;
import com.jblue.sistema.Sistema;
import com.jblue.util.Filtros;
import com.jblue.modelo.absobj.Objeto;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstraccionFunciones<T extends Objeto> implements ModeloFuncionesDB {

    protected final String tabla;
    protected final String[] campos;
    protected final Conexion conexion;

    public AbstraccionFunciones(String tabla, String[] campos) {
        this.tabla = tabla;
        this.campos = campos;
        this.conexion = Sistema.getInstancia().getConexion();
    }

    @Override
    public boolean insert(String[] valores) {
        try {
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
    public boolean delete(String where) {
        boolean retorno = false;
        if (Filtros.isNullOrBlank(where)) {
            return false;
        }
        try {
            retorno = conexion.delete(tabla, where);
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
        return retorno;

    }

    @Override
    public boolean update(String campo, String valor, String where) {
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
    public boolean update(String[] campos, String[] valores, String where) {
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
    public Optional<T> get(String campos, String where) {
        Optional<ArrayList<T>> select = select(campos, where);
        if (!select.isEmpty() && !select.get().isEmpty()) {
            return Optional.of(select.get().get(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ArrayList<T>> select(String campos, String where) {
        try {
            return Optional.of(getList(campos, where));
        } catch (SQLException ex) {
            Excp.impTerminal(ex, this.getClass(), true);
        }
        return Optional.empty();
    }

    /**
     * Este metodo recupera una lista de datos de la base de datos retornado un
     * objeto de tipo "Optional" para la verificacion de existencia de datos.
     * <br>
     * este metodo no trata las excepciones lanzadas, por lo que es necerio
     * colocar detro de un bloque "try-catch"
     *
     * @param campos - campos selolicitados a la base de datos; en caso de
     * colocar un "null","","*" la funcion solicitara todos lo campos de la
     * tabla solicitada
     * @param where - condicion bajo la cual se seleccionaran los datos
     * @return un objeto de tipo "optional" el cual contiene una lista con los
     * valores solicitados por la condicion where en caso de un "SQLExcption"
     * retornara un objeto vacio
     * @throws java.sql.SQLException
     */
    public ArrayList<T> getList(String campos, String where) throws SQLException {
        ArrayList<T> lista;
        String[] arr;
        if (campos == null || campos.isBlank() || campos.contains("*")) {
            arr = this.campos;
        } else {
            arr = campos.split(",");
        }
        ResultSet rs = conexion.select(tabla, campos, where);
        lista = new ArrayList<>();

        String[] aux;
        int i;
        while (rs.next()) {
            aux = new String[arr.length];
            i = 0;
            for (String j : arr) {
                aux[i] = rs.getString(j);
                i++;
            }
            Objeto objeto = ObjetoUtil.getObjeto(tabla, aux);
            lista.add((T) objeto);
        }
        return lista;
    }

    public String getTabla() {
        return tabla;
    }

    public String[] getCampos() {
        return campos;
    }

    public Conexion getConexion() {
        return conexion;
    }

}
