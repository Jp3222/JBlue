/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.conf;

import com.jblue.sistema.app.InfoApp;
import javax.swing.JFrame;

/**
 *
 * @author jp
 */
public abstract class SuperVentana extends JFrame implements InfoApp {

    public abstract void llamable();
    
    public abstract void init();

    public abstract void addComponentes();

    public abstract void addEventos();

}
