/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.media;

import java.io.File;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author jp
 */
public class Media {

    private final ArrayList<File> img;
    private final File raiz;

    public Media() {
        this.img = new ArrayList<>(100);
        this.raiz = new File(Media.class.getResource("/com/jblue/media/img/").toString());
    }

    public void leer() {
        System.out.println(raiz.toString());
        
    }

    private String crearURL(String... o) {
        String url = "/";
        int i = 0;
        for (; i < o.length - 1; i++) {
            url += o[i] + File.separator;
        }
        url += o[i] + "/";
        return url;
    }

}
