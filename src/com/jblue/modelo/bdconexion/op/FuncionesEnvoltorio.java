/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.bdconexion.op;

import com.jblue.util.modelo.funbd.ModeloFuncionesDB;
import com.jblue.modelo.objetos.Objetos;
import com.jblue.util.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public abstract class FuncionesEnvoltorio implements ModeloFuncionesDB {

    protected final Conexion CONEXION;
    protected final String TABLA;
    protected final String[] CAMPOS;
    protected final int NO_CAMPOS;

    public FuncionesEnvoltorio(String tabla, String[] campos) {
        this.CONEXION = Conexion.getInstancia();
        this.TABLA = tabla;
        this.CAMPOS = campos;
        this.NO_CAMPOS = campos.length;

    }

    /**
     * Este metodo inserta valores en la base de datos ignorando el primer valor
     * del array suponiendo que pertenece al campo "ID"
     *
     * @param valores - conjunto de valores que se insertara
     * @return true si la inserccion se hizo correctamente en otro clase false
     */
    public boolean _INSERTAR(String[] valores) {
        try {
            //System.out.println(Arrays.toString(valores));
            return CONEXION.insert(TABLA,
                    CONEXION.getCampos(Arrays.copyOfRange(CAMPOS, 1, CAMPOS.length)),
                    CONEXION.getDatos(valores)
            );
        } catch (SQLException ex) {
            Logger.getLogger(FuncionesEnvoltorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * Este metodo elimina registros de la base de dataos segun la "condicion
     * escrita en sql" que se pasa por parametro
     *
     * @param where - condicion en "lenguaje sql" para la eliminacion de algun
     * registro
     * @return true si la eliminacion se hizo correctamente en otro clase false
     */
    public boolean _ELIMINAR(String where) {
        try {
            return CONEXION.delete(TABLA, where);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionesEnvoltorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param campo
     * @param valor
     * @param where
     * @return
     */
    public boolean _ACTUALIZAR(String campo, String valor, String where) {
        try {
            return CONEXION.update(TABLA, campo, valor, where);
        } catch (SQLException ex) {

        }
        return false;
    }

    /**
     *
     * @param campos
     * @param valores
     * @param where
     * @return
     */
    public boolean _ACTUALIZAR(String campos[], String valores[], String where) {
        try {
            return CONEXION.update(TABLA,
                    CONEXION.getCamposDatos(campos, valores),
                    where
            );
        } catch (SQLException ex) {
            Logger.getLogger(FuncionesEnvoltorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    protected boolean _ACTUALIZAR_SIN_ID(String valores[], String where) {
        try {
            return CONEXION.update(TABLA,
                    CONEXION.getCamposDatos(Arrays.copyOfRange(CAMPOS, 1, NO_CAMPOS), valores),
                    where
            );
        } catch (SQLException ex) {
            Logger.getLogger(FuncionesEnvoltorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param <T>
     * @param campos
     * @param where
     * @return
     */
    protected <T extends Objeto> ArrayList<T> _GET(String campos, String where) {
        try {
            ResultSet get = CONEXION.select(TABLA, campos, where);
            ArrayList<T> lista;
            lista = getLista(get, TABLA, CAMPOS);
            CONEXION.closeRS();
            return lista;
        } catch (SQLException | CloneNotSupportedException ex) {
            Excp.impTerminal(ex, this.getClass(), true);
        }
        return null;
    }

    /**
     *
     * @param get
     * @param tabla
     * @param campos
     * @return
     * @throws SQLException
     * @throws CloneNotSupportedException
     */
    private <T extends Objeto> ArrayList<T> getLista(ResultSet get, String tabla, String[] campos) throws SQLException, CloneNotSupportedException {
        if (get == null || tabla == null || campos == null) {
            throw new NullPointerException("Alguno de los parametros es null");
        }
        ArrayList<T> o = runWhile(get, campos);
        return o;
    }

    private <T extends Objeto> ArrayList<T> runWhile(ResultSet rs, String[] campos) {
        ArrayList<T> lista = new ArrayList<>();
        try (rs) {
            while (rs.next()) {
                String[] info = runFor(rs, campos);
                lista.add((T) Objetos.getObjeto(TABLA, info));
            }
        } catch (SQLException ex) {
            Excp.impTerminal(ex, this.getClass(), true);
        }
        return lista;
    }

    /**
     * Este metodo recupera informacion de la base de datos y la almacena en un
     * arreglo, mencionar que este metodo no cierra el objeto de tipo ResultSet
     * ya que se espera que este sea iterado dentro de bucles
     *
     * @param get objeto de tipos ResultSet sobre el cual se esta trabajando
     * @param campos Array de Strings que contienen los campos sobre los cuales
     * se va a recuperar informacion
     * @return un Array con los datos recuperados
     */
    private synchronized String[] runFor(ResultSet get, String[] campos) {
        String[] info = new String[campos.length];
        try {
            int i = 0;
            for (String campo : campos) {
                info[i] = get.getString(campo);
                i++;
            }
        } catch (SQLException ex) {
            Excp.impTerminal(ex, this.getClass(), true);
        }
        return info;
    }

    /**
     *
     * @return la tabla con la que se esta trabajando
     */
    public String getTABLA() {
        return TABLA;
    }

    /**
     *
     * @return un arreglo con los campos de la tabla
     */
    public String[] getCAMPOS() {
        return CAMPOS;
    }

    /**
     *
     * @return el objeto de conexion
     */
    public Conexion getCONEXION() {
        return CONEXION;
    }

}
