/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.eventos.mouse;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class ListaEventos {

    public final int MOUSE_CLICKED = 0;
    public final int MOUSE_PRESED = 1;
    public final int MOUSE_RELEASED = 2;
    public final int MOUSE_ENTERED = 3;
    public final int MOUSE_EXITED = 4;
    public final int MOUSE_DRAGGED = 5;
    public final int MOUSE_MOVED = 6;

    private final ArrayList<MouseEvt>[] LISTA_DE_EVENTOS;
    private final ArrayList<MouseWEvt> LISTA_DE_EVENTOS2;

    public ListaEventos() {
        this.LISTA_DE_EVENTOS = new ArrayList[7];
        for (int i = 0; i < LISTA_DE_EVENTOS.length; i++) {
            LISTA_DE_EVENTOS[i] = new ArrayList<>();
        }
        this.LISTA_DE_EVENTOS2 = new ArrayList<>();
    }

    public void addEvent(int event, MouseEvt e) {
        LISTA_DE_EVENTOS[event].add(e);
    }

    public void addEvent(MouseWEvt e) {
        LISTA_DE_EVENTOS2.add(e);
    }

    protected void aplicar(int event, MouseEvent e) {
        for (MouseEvt o : LISTA_DE_EVENTOS[event]) {
            o.evt(e);
        }
    }

}
