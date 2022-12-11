/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.conf;

import com.jblue.util.SalidaDeErrores;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jp
 */
public class Apariencia implements SalidaDeErrores {

    private final String SO;
    private final String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel",
            WIN = "Windows",
            NIM = "javax.swing.plaf.nimbus.NimbusLookAndFeel";

    public Apariencia(String SO) {
        this.SO = SO;
    }

    public void gtk() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("GTK+".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(pwError);
                    closeBufferExeption();
                }
            }
        }
    }

    public void aparienciaPorDefecto() {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            Arrays.stream(installedLookAndFeels).forEach((t) -> {
                System.out.println("name:" + t.getName()
                        + "\nclass name:" + t.getClassName());
            });
            boolean libInstalada = isLibInstalada(installedLookAndFeels);
            if (!libInstalada) {
                UIManager.setLookAndFeel(NIM);
            }
            switch (SO.toLowerCase()) {
                case "linux":
                    UIManager.setLookAndFeel(GTK);
                    break;
                case "windows 10":
                    UIManager.setLookAndFeel(installedLookAndFeels[3].getClassName());
                    break;
                case "mac":
                    UIManager.setLookAndFeel(NIM);
                    break;
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            try (pwError) {
                System.out.println("Error al cargar la apariencia del sistema: " + e.getMessage());
                e.printStackTrace(pwError);
                closeBufferExeption();
            }
        }
    }

    public boolean isLibInstalada(UIManager.LookAndFeelInfo[] installedLookAndFeels) {
        boolean anyMatch = Arrays.stream(installedLookAndFeels).anyMatch((t) -> {
            return t.getClassName().equalsIgnoreCase(GTK)
                    || t.getClassName().equalsIgnoreCase(WIN);
        });
        return anyMatch;
    }
    private static final Logger LOG = Logger.getLogger(Apariencia.class.getName());
}
