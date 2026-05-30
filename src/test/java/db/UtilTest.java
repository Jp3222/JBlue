/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import jsoftware.com.jblue.util.FuncGenerate;
import org.junit.jupiter.api.Test;

/**
 *
 * @author juanp
 */
public class UtilTest {

    @Test
    public void mkPasswordDefault() {
        String password_test = FuncGenerate.getPassword(10, true, true, true, true);
        System.out.println(password_test);
    }

    @Test
    public void mkPasswordOnlyUpper() {
        String password_test = FuncGenerate.getPassword(10, false, false, true, false);
        System.out.println(password_test);
    }

    @Test
    public void mkPasswordOnlyLower() {
        String password_test = FuncGenerate.getPassword(10, false, true, false, false);
        System.out.println(password_test);
    }

    @Test
    public void mkPasswordOnlyText() {
        String password_test = FuncGenerate.getPassword(10, false, true, true, false);
        System.out.println(password_test);
    }

    @Test
    public void mkPasswordOnluyNumber() {
        String password_test = FuncGenerate.getPassword(10, true, false, false, false);
        System.out.println(password_test);
    }

    @Test
    public void mkPassword() {
        String password_test = FuncGenerate.getPassword(10, false, false, false, false);
        System.out.println(password_test);
    }

}
