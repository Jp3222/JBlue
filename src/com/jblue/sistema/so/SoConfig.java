/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import com.jblue.util.SoInfo;
import com.jblue.util.plataformas.soconfig.apariencia;
import java.io.File;

/**
 *
 * @author jp
 */
public class SoConfig {

    private final ConstructorDeArchivos CDA;
    private final String RAIZ_ARCH;

    /**
     *
     */
    public SoConfig() {
        this.RAIZ_ARCH = "jblue";
        this.CDA = new ConstructorDeArchivos();
    }

    private void archivos() {
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_DOCUMENTOS + File.separator + ".jblue");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_DOCUMENTOS + File.separator + ".jblue/user");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_DOCUMENTOS + File.separator + ".jblue/respaldos");
        CDA.add(CDA.ARCHIVO, SoInfo.RUTA_DOCUMENTOS + "/.jblue/user/userbd.jff");
        //
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_ESCRITORIO + "/jblue");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_ESCRITORIO + "/jblue/Reportes");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_ESCRITORIO + "/jblue/PDFs");
        CDA.add(CDA.DIRECTORIO, SoInfo.RUTA_ESCRITORIO + "/jblue/Cobros");
    }

    public void cargar() {
        try {
            apariencia.setDefault();
            archivos();
        } catch (Exception ex) {
        }
    }

    public ConstructorDeArchivos getCDA() {
        return CDA;
    }

    /**
     * Metodo que contruye una url usando el separador adecuado para cada SO,
     * usando el conjunto de cadenas pasado por parametro.
     *
     * @param noms nombres de carpetas que componen la ruta
     * @return
     */
    public String consURL(String... noms) {
        String url = "";
        for (int i = 0; i < noms.length - 1; i++) {
            url += noms[i] + File.separator;
        }
        url += noms[noms.length - 1];
        return url;
    }
}
