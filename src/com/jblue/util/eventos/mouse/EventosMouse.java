/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.eventos.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author jp
 */
public class EventosMouse extends ListaEventos implements MouseListener, MouseMotionListener, MouseWheelListener {

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

    @Override
    public void mouseDragged(MouseEvent me) {
        super.aplicar(MOUSE_DRAGGED, me);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        super.aplicar(MOUSE_MOVED, me);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
