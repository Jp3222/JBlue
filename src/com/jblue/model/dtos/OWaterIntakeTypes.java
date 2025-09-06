/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.model.dtos;

import com.jblue.model.factories.CacheFactory;

/**
 *
 * @author jp
 */
public class OWaterIntakeTypes extends Objects implements ForeingKeyObject, StatusObject {

    private String[] infoFK;

    public OWaterIntakeTypes(String[] info) {
        super(info);
    }

    public OWaterIntakeTypes() {
        super();
    }

    public String getTypeName() {
        return info[1];
    }

    public double getPreviousPrice() {
        return Double.parseDouble(info[2]);
    }

    public double getCurrentPrice() {
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
        return CacheFactory.ITEMS_STATUS_CAT[getStatus()];
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
        return getTypeName();
    }

}
