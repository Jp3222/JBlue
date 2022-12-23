/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.eventos.TablasEvt;

import com.jblue.util.eventos.mouse.EventosMouse;
import com.jblue.util.eventos.mouse.MouseEvt;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author jp
 */
public class TablasEvt {

    private final JTable TABLA;
    private final EventosMouse EVT;
    private JButton[] JBT_MAN;
    //
    public final int MOUSE_CLICKED = 0;
    public final int MOUSE_PRESED = 1;
    public final int MOUSE_RELEASED = 3;
    public final int MOUSE_ENTERED = 4;
    public final int MOUSE_EXITED = 5;

    public TablasEvt(JTable TABLA) {
        this.TABLA = TABLA;
        this.EVT = new EventosMouse();
        this.TABLA.addMouseListener(EVT);
    }

    public void setJButtons(JButton... buttons) {
        this.JBT_MAN = buttons;
    }

    public void addActionListener(int index, ActionListener e) {
        JBT_MAN[index].addActionListener(e);
    }

    public void addMouseListener(int evet, MouseEvt e) {
        EVT.addEvent(evet, e);
    }

    public JTable getTABLA() {
        return TABLA;
    }

}
