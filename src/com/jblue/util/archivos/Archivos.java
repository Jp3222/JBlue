/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.archivos;

import java.io.File;

/**
 *
 * @author jp
 */
public abstract class Archivos {

    protected boolean aValido(File file) {
        return fValido(file) && file.isFile();
    }

    protected boolean dValido(File file) {
        return fValido(file) && file.isDirectory();
    }

    private boolean fValido(File file) {
        return file != null && file.exists();
    }
}
