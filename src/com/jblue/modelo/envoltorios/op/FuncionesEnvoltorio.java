/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.envoltorios.op;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;

/**
 *
 * @author jp
 */
public abstract class FuncionesEnvoltorio {

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
    protected boolean _INSERTAR(String[] valores) {
        return CONEXION.insert(TABLA,
                CONEXION.getCampos(Arrays.copyOfRange(CAMPOS, 1, CAMPOS.length)),
                CONEXION.getDatos(valores)
        );
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
    protected boolean _ELIMINAR(String where) {
        return CONEXION.delete(TABLA, where);
    }

    protected boolean _ACTUALIZAR(String campo, String valor, String where) {
        return CONEXION.update(TABLA, campo, valor, where);
    }

    protected boolean _ACTUALIZAR(String campos[], String valores[], String where) {
        return CONEXION.update(TABLA,
                CONEXION.getCamposDatos(campos, valores),
                where
        );
    }

    protected <T extends Objeto> ArrayList<T> _GET(String campos, String where) {
        try {
            ResultSet get = CONEXION.select(TABLA, campos, where);
            ArrayList<T> lista;
            lista = (ArrayList<T>) getLista(get, TABLA, CAMPOS);
            CONEXION.closeRS();
            return lista;
        } catch (SQLException | CloneNotSupportedException ex) {
            Excp.impTerminal(ex, this.getClass(), true);
        }
        return null;
    }

    private ArrayList<Objeto> getLista(ResultSet get, String tabla, String[] campos) throws SQLException, CloneNotSupportedException {
        if (get == null || tabla == null || campos == null) {
            throw new NullPointerException("Alguno de los parametros es null");
        }
        try {
            switch (tabla) {
                case "calles":
                    OCalles calle = new OCalles();
                    return runWhile(calle, get, campos);
                case "usuarios":
                    OUsuarios usuarios = new OUsuarios();
                    return runWhile(usuarios, get, campos);
                case "personal":
                    OPersonal personal = new OPersonal();
                    return runWhile(personal, get, campos);
                case "tipo_tomas":
                    OTipoTomas toma = new OTipoTomas();
                    return runWhile(toma, get, campos);
            }
        } catch (Exception ex) {
            Excp.impTerminal(ex, this.getClass(), true);
        }
        return null;
    }

    private <T extends Objeto> ArrayList<T> runWhile(T o, ResultSet rs, String[] campos) {
        ArrayList<T> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                String[] info = runFor(rs, campos);
                o.setInfo(info);
                lista.add((T) o.clone());
            }
        } catch (CloneNotSupportedException | SQLException ex) {
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
    private String[] runFor(ResultSet get, String[] campos) {
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
     * @return
     */
    public String getTABLA() {
        return TABLA;
    }

    public String[] getCAMPOS() {
        return CAMPOS;
    }

    public Conexion getCONEXION() {
        return CONEXION;
    }

}
