/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.bd;

import com.jblue.util.SalidaDeErrores;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public class BD implements SalidaDeErrores {

    private Connection cn;
    private ResultSet rs;
    private Statement st;

    public BD() {
        try {
            this.cn = DriverManager.getConnection("jdbc:psql://localhost//jblue", "jp", "12345");
        } catch (SQLException ex) {
            System.out.println("Error en la inicializacion de BD: " + ex.getMessage());
            ex.printStackTrace(pwError);
        }
    }

    public void insert() {
        try {
            st = cn.createStatement();
        
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void delete() {
    }

    public void update() {
    }

    public ResultSet select() {

        return null;
    }

}
