/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.marco.vistas;

import com.jblue.controlador.Controller;
import javax.swing.JPanel;
import com.jutil.framework.ViewStates;

/**
 *
 * @author jp
 */
public abstract class SimpleView extends JPanel implements ViewStates {

    protected Controller controller;

    public SimpleView() {
    }

}
