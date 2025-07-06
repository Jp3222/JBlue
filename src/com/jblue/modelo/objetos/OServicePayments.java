/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OServicePayments extends AbstractPayments {

    public OServicePayments() {
        super();
    }

    public OServicePayments(String... info) {
        super(info);
    }

    @Override
    public String toString() {
        return getUserObject().toString().concat(" ").concat(getMonth().concat(": ").concat(getStatusString()));
    }

    

}
