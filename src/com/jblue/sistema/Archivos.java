/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.sistema.so.ConstructorDeArchivos;
import com.jblue.util.SoInfo;
import com.jblue.util.plataformas.soconfig.apariencia;
import java.io.File;

/**
 *
 * @author jp
 */
public class Archivos {

    private final ConstructorDeArchivos archivos;
    private final String raiz;
    private final String raiz_de_basura;

    /**
     *
     */
    public Archivos() {
        this.raiz = "jblue";
        this.raiz_de_basura = ".blue";
        this.archivos = new ConstructorDeArchivos();
    }
    
    private void archivos() {
        //Archivos de respaldo
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz_de_basura));
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz_de_basura, "respaldos"));
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz_de_basura, "cache"));
        archivos.add(archivos.ARCHIVO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz_de_basura, "bd.jff"));
        //Archivos visibles
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz));
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz, "reportes"));
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz, "PDFs"));
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz, "Pagos"));
    }

    public void cargar() {
        try {
            apariencia.setDefault();
            archivos();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ConstructorDeArchivos getArchivos() {
        return archivos;
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
