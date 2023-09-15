/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.plataformas.soconfig;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
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
            String UI = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(UI);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {

            Logger.getLogger(apariencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFlatMacLightlaf() {

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {

            Logger.getLogger(apariencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
