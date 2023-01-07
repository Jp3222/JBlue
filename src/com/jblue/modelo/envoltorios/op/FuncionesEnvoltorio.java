/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.envoltorios.op;

import com.jblue.modelo.objetos.OPagosTitular;
import com.jbd.conexion.Conexion;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OConsumidores;
import com.jblue.modelo.objetos.OPagosConsumidor;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTitulares;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.excepciones.ExeptionPrinter;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author jp
 */
public abstract class FuncionesEnvoltorio implements ExeptionPrinter {

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

    protected boolean INSERTAR(String[] valores) {
        return CONEXION.insert(TABLA, 
                CONEXION.getCampos(Arrays.copyOfRange(CAMPOS, 1, CAMPOS.length)),
                CONEXION.getDatos(Arrays.copyOfRange(valores, 1, valores.length))
        );
    }

    protected boolean ELIMINAR(String where) {
        return CONEXION.delete(TABLA, where);
    }

    protected boolean ACTUALIZAR(String campo, String valor, String where) {
        return CONEXION.update(TABLA, campo, valor, where);
    }

    protected boolean ACTUALIZAR(String campos[], String valores[], String where) {
        return CONEXION.update(TABLA,
                CONEXION.getCamposDatos(campos, valores),
                where
        );
    }

    protected <T extends Objeto> ArrayList<T> GET(String campos, String where) {
        try {
            ResultSet get = CONEXION.select(TABLA, campos, where);
            ArrayList<T> lista;
            lista = (ArrayList<T>) getLista(get, TABLA, CAMPOS);
            CONEXION.closeRS();
            return lista;
        } catch (SQLException | CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(pwExeption);
            closeExeptionBuffer();
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
                case "consumidores":
                    OConsumidores consumidores = new OConsumidores();
                    return runWhile(consumidores, get, campos);
                case "pagos_consumidores":
                    OPagosConsumidor pagos = new OPagosConsumidor();
                    return runWhile(pagos, get, campos);
                case "pagos_titulares":
                    OPagosTitular pagostitular = new OPagosTitular();
                    return runWhile(pagostitular, get, campos);
                case "personal":
                    OPersonal personal = new OPersonal();
                    return runWhile(personal, get, campos);
                case "titulares":
                    OTitulares titular = new OTitulares();
                    return runWhile(titular, get, campos);
                case "tipo_tomas":
                    OTipoTomas toma = new OTipoTomas();
                    return runWhile(toma, get, campos);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(pwExeption);
            closeExeptionBuffer();
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
            if (lista.isEmpty()) {
                return null;
            }
        } catch (CloneNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(pwExeption);
            closeExeptionBuffer();
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
    public String[] runFor(ResultSet get, String[] campos) {
        String[] info = new String[campos.length];
        try {
            int i = 0;
            for (String campo : campos) {
                info[i] = get.getString(campo);
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(pwExeption);
            closeExeptionBuffer();
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
