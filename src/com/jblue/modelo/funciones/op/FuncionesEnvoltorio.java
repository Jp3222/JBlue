/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.funciones.op;

import com.jbd.Exeption.ExeptionPrinter;
import com.jblue.modelo.objetos.OPagosTitular;
import com.jbd.conexion.Conexion;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OConsumidores;
import com.jblue.modelo.objetos.OPagosConsumidor;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTitulares;
import com.jblue.modelo.objetos.OTomas;
import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author jp
 */
public class FuncionesEnvoltorio implements ExeptionPrinter {

    protected final Conexion cn;
    protected final String tabla;
    protected final String[] campos;
    protected final int noCampos;

    public FuncionesEnvoltorio(String tabla, String[] campos) {
        this.cn = Conexion.getInstancia();
        this.tabla = tabla;
        this.campos = campos;
        this.noCampos = campos.length;
    }

    protected boolean INSERTAR(String[] valores) {
        return cn.insert(tabla,
                cn.getCampos(Arrays.copyOfRange(campos, 1, campos.length)),
                cn.getDatos(Arrays.copyOfRange(valores, 1, valores.length)
                )
        );
    }

    protected boolean ELIMINAR(String where) {
        return cn.delete(tabla, where);
    }

    protected boolean ACTUALIZAR(String campo, String valor, String where) {
        return cn.update(tabla, campo, valor, where);
    }

    protected boolean ACTUALIZAR(String campos[], String valores[], String where) {
        return cn.update(tabla, 
                cn.getCamposDatos(campos, valores),
                where
        );
    }

    protected ArrayList<Objeto> GET(String where) {
        try {
            ArrayList<Objeto> lista;
            try ( ResultSet get = cn.select(tabla, "*", where)) {
                lista = getLista(get, noCampos, tabla, campos);
            }
            cn.closeRS();
            return lista;
        } catch (SQLException | CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(pwExeption);
            closeExeptionBuffer();
        }
        return null;
    }

    private ArrayList<Objeto> getLista(ResultSet get, int size, String tabla, String[] campos) throws SQLException, CloneNotSupportedException {
        if (get == null || tabla == null || campos == null) {
            return null;
        }
        switch (tabla) {
            case "calles":
                OCalles calle = new OCalles();
                return runWhile(calle, get, size, campos);
            case "consumidores":
                OConsumidores consumidores = new OConsumidores();
                return runWhile(consumidores, get, size, campos);
            case "pagos_consumidores":
                OPagosConsumidor pagos = new OPagosConsumidor();
                return runWhile(pagos, get, size, campos);
            case "pagos_titulares":
                OPagosTitular pagostitular = new OPagosTitular();
                return runWhile(pagostitular, get, size, campos);
            case "personal":
                OPersonal personal = new OPersonal();
                return runWhile(personal, get, size, campos);
            case "titulares":
                OTitulares titular = new OTitulares();
                return runWhile(titular, get, size, campos);
            case "tomas":
                OTomas toma = new OTomas();
                return runWhile(toma, get, size, campos);
            default:
                return null;
        }
    }

    private <T extends Objeto> ArrayList<T> runWhile(T o, ResultSet get, int size, String[] campos) throws SQLException, CloneNotSupportedException {
        ArrayList<T> lista = new ArrayList<>();
        while (get.next()) {
            String[] info = getArrayInfo(get, campos, size);
            o.setInfo(info);
            lista.add((T) o.clone());
        }
        if (lista.isEmpty()) {
            return null;
        }
        return lista;
    }

    /**
     * Este metodo que recupera informacion de la base de datos segun los campos
     * especificados.
     *
     * Este metodo no cierra el objeto de tipo ResultSet puesto que se espera
     * que se utilice en bluces
     *
     * @param get objeto de tipos ResultSet sobre el cual se esta trabajando
     * @param campos Array de String que contienen los campos sobre los cuales
     * se va a recuperar informacion
     * @param size tamaño del array con el cual se esta trabajando
     * @return un Array con los datos recuperados o una exepcion del tipo
     * nullPointerException o del tipo SQLException
     * @throws SQLException
     */
    public String[] getArrayInfo(ResultSet get, String[] campos, int size) throws SQLException {
        String[] info = new String[size];
        int i = 0;
        for (String campo : campos) {
            info[i] = get.getString(campo);
            i++;
        }
        return info;
    }

    /**
     * 
     * @return 
     */
    public String getTabla() {
        return tabla;
    }

    public String[] getCampos() {
        return campos;
    }

    public Conexion getCn() {
        return cn;
    }
}
