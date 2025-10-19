/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

import jsoftware.com.jblue.sys.app.AppConfig;

/**
 *
 * @author juanp
 */
public class Func {

    public static void devMessages(String msg) {
        if (AppConfig.isDevMessages()) {
            System.out.println(msg);
        }
    }
    
    
}
