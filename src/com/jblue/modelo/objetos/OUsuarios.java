/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.fabricas.FactoryCache;
import com.jblue.util.objetos.ObjetoFK;
import com.jblue.util.tools.ObjectUtils;

/**
 *
 * @author jp
 */
public class OUsuarios extends Objeto implements ObjetoFK {

    public OUsuarios(String[] info) {
        super(info);
    }

    public OUsuarios() {
        super();
    }

    /**
     * metodo que retorna el nombre del usuario
     *
     * @return una cadena con el nombre del usuario
     */
    public String getNombre() {
        return info[1];
    }

    /**
     *
     * @return una cadena con el apellido paterno del usuario
     */
    public String getAp() {
        return info[2];
    }

    /**
     *
     * @return una cadena con el apellido materno del usuario
     */
    public String getAm() {
        return info[3];
    }

    /**
     *
     * @return una cadena con el ID de la calle asociada a este usuario
     */
    public String getCalle() {
        return info[4];
    }
    
    public OCalles getStreet(){
        return FactoryCache.CALLES.get((t) -> t.getId().equals(getCalle()));
    }

    public String getNumeroCasa() {
        return info[5];
    }

    /**
     *
     * @return una cadena con el ID del tipo de toma asociada a este usuario
     */
    public String getToma() {
        return info[6];
    }

    public OTipoTomas getTypeWaterIntakes() {
        return FactoryCache.TIPO_DE_TOMAS.get((t) -> t.getId().equals(getToma()));
    }

    /**
     *
     * @return un entero: 1 si el usuario esta activo -1 si el usuario esta
     * inactivo
     */
    public int getEstado() {
        return Integer.parseInt(info[7]);
    }

    public String getEstadoStr() {
        return switch (getEstado()) {
            case 1:
                yield "Activo";
            case 2:
                yield "Inactivo";
            case 3:
                yield "Baja";
            default:
                throw new AssertionError();
        };
    }

    public boolean isActivo() {
        return getEstado() > 0;
    }

    /**
     *
     * @return -1 si el usuario es titular en cualquier otro caso devuelve el id
     * del usuario al cual esta asociado
     */
    public String getTipo() {
        return info[8];
    }

    public String getTipoStr() {
        return switch (info[8]) {
            case "1":
                yield "Titular";
            default:
                yield "Consumidor";
        };
    }

    public boolean isTitular() {
        return Integer.parseInt(info[8]) < 0;
    }

    /**
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 ap
     * <br> 4 am
     * <br> 5 calle
     * <br> 6 toma
     * <br> 7 registro
     * <br> 8 estado
     * <br> 9 titular
     *
     * @param info array que contiene la informacion en el orden mostrado arriba
     */
    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 ap
     * <br> 4 am
     * <br> 5 calle
     * <br> 6 toma
     * <br> 7 registro
     * <br> 8 estado
     * <br> 9 titular
     *
     * @return un arreglo con la informacion en el orden mostrado
     */
    @Override
    public String[] getInfo() {
        return super.getInfo(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String[] getInfoSinFK() {
        String[] _info = this.info.clone();
        _info[4] = ObjectUtils.getStreed(getCalle());
        _info[6] = ObjectUtils.getWaterIntakes(getToma());
        _info[7] = getEstadoStr();
        _info[8] = getTipoStr();

        return _info;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNombre()).append(" ");
        sb.append(getAp()).append(" ");
        sb.append(getAm());
        return sb.toString();
    }

}
