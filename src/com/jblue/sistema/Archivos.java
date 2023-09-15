/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.sistema.app.AppProp;
import com.jblue.util.SoInfo;
import com.jblue.util.archivos.ConstructorArchivos;
import java.io.File;

/**
 *
 * @author jp
 */
public class Archivos {

    private final ConstructorArchivos archivos;
    private final String raiz;
    private final String raiz_de_basura;
    public final int USUARIO_BD;
    public final int REPORTES = 4;
    /**
     *
     */
    public Archivos() {
        this.USUARIO_BD = 0;
        this.raiz = "JBlue";
        this.raiz_de_basura = ".blue";
        this.archivos = new ConstructorArchivos();
    }

    private void archivos() {

        //Archivos de respaldo
        archivos.add(archivos.DIRECTORIO, AppProp.DIR_PROG);
        archivos.add(archivos.DIRECTORIO, AppProp.DIR_PROG_PERSONAL);
        archivos.add(archivos.DIRECTORIO, AppProp.DIR_PROG_USUARIOS);
        archivos.add(archivos.ARCHIVO, AppProp.DIR_ARC_CONFIG);

        //Archivos visibles
        archivos.add(archivos.DIRECTORIO, AppProp.DIR_USU);
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz, "reportes"));
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz, "PDFs"));
        archivos.add(archivos.DIRECTORIO, consURL(SoInfo.RUTA_DOCUMENTOS, raiz, "Pagos"));
    }

    public void cargar() {
        try {
            archivos();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ConstructorArchivos getArchivos() {
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
