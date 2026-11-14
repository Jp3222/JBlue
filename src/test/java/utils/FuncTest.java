/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jsoftware.com.jblue.utils.FuncUC;
import org.junit.jupiter.api.Test;

/**
 * CLASE DE PRUEBAS PARA UTILERIAS ESTANDAR
 *
 * @author juanp
 */
public class FuncTest {

    @Test
    public void unicode() {
        String generateFormattedId = FuncUC.generateFormattedId(3, 1, "10000");
        System.out.println(generateFormattedId);
        System.out.println(generateFormattedId.length());
        
        String uuid = FuncUC.getUUID();
        System.out.println(uuid);
    }
}
