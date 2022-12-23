/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author jp
 */
public final class SoWindows {

    private SoConfig CONFIG;

    public SoWindows(SoConfig CONFIG) {
        this.CONFIG = CONFIG;
        ocultarCarpetas();
    }

    public void ocultarCarpetas() {
        try {
            ocultar(CONFIG.getCDA().get(CONFIG.getCDA().DIRECTORIO, 0));
        } catch (Exception e) {
            
        } finally {
        }
    }

    public void ocultar(File o) throws IOException {
        Path path = Paths.get(o.getPath(), o.getName());
        Boolean hidden = (Boolean) Files.getAttribute(path, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
        if (hidden != null && !hidden) {
            Files.setAttribute(path, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
            System.out.println("File is now hidden!");
        }
    }
}
