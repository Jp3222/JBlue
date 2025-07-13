/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.objetos.ForeingKeyObject;
import com.jblue.util.objetos.StatusObject;

/**
 *
 * @author jp
 */
public class OWaterIntake extends Objeto implements ForeingKeyObject, StatusObject {

    private String[] infoFK;

    public OWaterIntake(String[] info) {
        super(info);
    }

    public OWaterIntake() {
        super();
    }

    public String getType() {
        return info[1];
    }

    public double getPreviusPrice() {
        return Double.parseDouble(info[2]);
    }

    public double getPrice() {
        return Double.parseDouble(info[3]);
    }

    public double getSurcharge() {
        return Double.parseDouble(info[4]);
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(info[5]);
    }

    @Override
    public String getStatusString() {
        return switch (getStatus()) {
            case 2:
                yield "INACTIVO";
            case 3:
                yield "BORRADO";
            default:
                yield "ACTIVO";
        };
    }

    @Override
    public boolean isActive() {
        return getStatus() != 3;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.infoFK = info.clone();
        this.infoFK[5] = getStatusString();
    }

    @Override
    public String[] getInfoSinFK() {
        return infoFK;
    }

    @Override
    public String toString() {
        return getType();
    }

}
