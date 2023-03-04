/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.plataformas;

import com.jblue.sistema.so.ConstructorDeArchivos;
import com.jblue.util.plataformas.interfaces.Configuraciones;
import com.jblue.util.plataformas.soconfig.apariencia;

/**
 *
 * @author jp
 */
public class SoConfig implements Configuraciones {

    private final ConstructorDeArchivos CDA;

    public SoConfig(ConstructorDeArchivos CDA) {
        this.CDA = CDA;
    }

    @Override
    public void createLanzador() {
        throw new UnsupportedOperationException("No creado aun"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDefaultApariencia() {
        apariencia.setAparienciaDEF();
    }

    @Override
    public void ocultarFicheros() {
        
    }

}
