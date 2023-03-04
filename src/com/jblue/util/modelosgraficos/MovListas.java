/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.modelosgraficos;

import com.jblue.util.interfacesSuper.graficos.InterfaceListaMov;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author jp
 */
public class MovListas implements InterfaceListaMov {

    private final JList<String> LISTA;
    private final DefaultListModel<String> MODELO;
    private final ArrayList<String> ELEMENTOS;

    public MovListas(JList<String> lista, ArrayList<String> elementos) {
        LISTA = lista;
        MODELO = (DefaultListModel<String>) LISTA.getModel();
        ELEMENTOS = elementos;
    }

    @Override
    public void add(String o) {
        MODELO.addElement(o);
    }

    @Override
    public void remove(int index) {
        MODELO.remove(index);
    }

    @Override
    public String get(int index) {
        return MODELO.get(index);
    }

    @Override
    public void set(int index, String o) {
        MODELO.set(index, o);
    }

    @Override
    public DefaultListModel<String> getModelo() {
        return MODELO;
    }

    @Override
    public JList<String> getLista() {
        return LISTA;
    }

    @Override
    public void llenar() {
        for (String string : ELEMENTOS) {
            MODELO.addElement(string);
        }
    }

    @Override
    public void vaciar() {
        MODELO.removeAllElements();
    }

    @Override
    public void actualizar() {
        int i = 0;
        for (String string : ELEMENTOS) {
            MODELO.set(i, string);
        }
    }

    public void resultados(String txt) {
        int size = MODELO.getSize();
        //
        int i = 0;
        for (String string : ELEMENTOS) {
            if (string.contains(txt)) {
                MODELO.set(i, string);
            }
        }
        for (int j = i; j < size; j++) {
            MODELO.remove(i);
        }
    }

}
