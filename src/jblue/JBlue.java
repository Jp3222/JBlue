/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jblue;

import com.jblue.modelo.Const;
import com.jblue.sistema.Sistema;
import com.jblue.util.archivos.AEscritor;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;

/**
 *
 * @author jp
 *
 */
public class JBlue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Sistema s = Sistema.getInstancia();
        s.run();
    }

}
