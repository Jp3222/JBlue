/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.eventos.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author jp
 */
public class EventosMouse extends ListaEventos implements MouseListener {

    public EventosMouse() {
        super();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        super.aplicar(MOUSE_CLICKED, me);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        super.aplicar(MOUSE_PRESED, me);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        super.aplicar(MOUSE_RELEASED, me);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        super.aplicar(MOUSE_ENTERED, me);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        super.aplicar(MOUSE_EXITED, me);
    }

}
