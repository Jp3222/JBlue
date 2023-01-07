/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public class AEscritor extends Archivos {

    public File crear(String url, String nom) {
        try {
            File file = new File(url + File.separator + nom);
            file.createNewFile();
            return file;
        } catch (IOException ex) {
            Logger.getLogger(AEscritor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void escribir(File file, String txt) throws FileNotFoundException, IOException {
        FileOutputStream os = new FileOutputStream(file);
        if (aValido(file)) {
            byte[] bytes = txt.getBytes();
            for (int b : bytes) {
                os.write(b);
            }
        }
        os.close();
    }

}
