/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.marco.ventanas;

import javax.swing.JFrame;
import com.jblue.sistema.app.AppInfo;
import com.jutil.framework.WindowStates;
import java.awt.HeadlessException;

/**
 *
 * @author jp
 */
public abstract class VentanaSimple extends JFrame implements AppInfo, WindowStates {

    public VentanaSimple() {
    }

    protected final void updateTitle(String title) {
        StringBuilder s = new StringBuilder(NOMBRE_DEL_PROGRAMA);
        s.append(" ");
        s.append(VERSION_DEL_PROGRAMA);
        s.append(" - ").append(title);
        this.setTitle(s.toString());
    }

}
