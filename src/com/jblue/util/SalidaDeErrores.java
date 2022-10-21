/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public interface SalidaDeErrores {

    BufferedOutputStream bosError = new BufferedOutputStream(System.out);
    PrintWriter pwError = new PrintWriter(bosError);
    
    public default void closeBufferExeption(){
        try {
            pwError.close();
            bosError.close();
        } catch (IOException ex) {
            Logger.getLogger(SalidaDeErrores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
