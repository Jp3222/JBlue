/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OWaterIntake extends Objeto {

    public OWaterIntake(String[] info) {
        super(info);
    }

    public OWaterIntake() {
        super();
    }

    public String getType() {
        return info[1];
    }

    public double getPrice() {
        return Double.parseDouble(info[2]);
    }

    public double getPreviusPrice() {
        return Double.parseDouble(info[3]);
    }

    public double getSurcharge() {
        return Double.parseDouble(info[4]);
    }

    public int getStatus() {
        return Integer.parseInt(info[5]);
    }

    public String getStatusString() {
        return switch (getStatus()) {
            case 2:
                yield "PENDIENTE";
            case 3:
                yield "BOrrado";
            default:
                yield "PAGADO";
        };
    }

    @Override
    public String toString() {
        return getType();
    }

}
