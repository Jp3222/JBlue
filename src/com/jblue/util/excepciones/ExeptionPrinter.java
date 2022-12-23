/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.excepciones;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public interface ExeptionPrinter {

    final BufferedOutputStream bosExeption = new BufferedOutputStream(System.out);
    final PrintWriter pwExeption = new PrintWriter(bosExeption);

    default void closeExeptionBuffer() {
        try {
            pwExeption.close();
            bosExeption.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    default void mensajeExeption(String e) {
        JOptionPane.showMessageDialog(null, e, "Error Interno", JOptionPane.ERROR_MESSAGE);
    }

}
