/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.plataformas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jp
 */
public class apariencia {

    private static final String DEF = "nimbus",
            GTK = "gtk",
            WIN = "windows",
            MAC = "mac";

    public static void setDefault() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(apariencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setAparienciaDEF() {
        try {
            String valor = getApariencia(DEF);
            if (valor != null) {
                UIManager.setLookAndFeel(valor);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("error de look " + e.getMessage());
        }
    }

    public static void setAparienciaLIN() {

    }

    public static void setAparienciaWIN() {
    }

    public static void setAparienciaMac() {
    }

    private static String getApariencia(String name) {
        UIManager.LookAndFeelInfo[] look = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo o : look) {
            if (o.getName().toLowerCase().contains(name)) {
                return o.getClassName();
            }
        }
        return null;
    }

}
