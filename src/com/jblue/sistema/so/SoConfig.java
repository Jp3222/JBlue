/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import com.jbd.Exeption.ExeptionPrinter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public class SoConfig implements ExeptionPrinter {

    private final ConstructorDeArchivos CDA;
    private final Apariencia APARIENCIA;

    /**
     *
     */
    public SoConfig() {
        APARIENCIA = new Apariencia(SoInfo.SO_NOMBRE);
        this.CDA = new ConstructorDeArchivos();
    }

    private void archivos() {
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_DOCUMENTOS + "/.jblue");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_DOCUMENTOS + "/.jblue/user");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_DOCUMENTOS + "/.jblue/respaldos");
        CDA.add(CDA.ARCHIVO, SoInfo.RUTA_DOCUMENTOS + "/.jblue/user/userbd.jff");
        //
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_ESCRITORIO + "/jblue");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_ESCRITORIO + "/jblue/Reportes");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_ESCRITORIO + "/jblue/PDFs");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_ESCRITORIO + "/jblue/Cobros");
    }

    public void cargar() {
        try {
            APARIENCIA.aparienciaPorDefecto();
            archivos();
        } catch (Exception ex) {
            Logger.getLogger(SoConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void construir() {
        try {
            CDA.construirTodos();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(pwExeption);
            closeExeptionBuffer();
        }

    }

    public ConstructorDeArchivos getCDA() {
        return CDA;
    }

}
