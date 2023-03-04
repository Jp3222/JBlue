/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.modelosgraficos;

import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.cache.MemoCache2;
import com.jblue.util.interfacesSuper.graficos.InterfaceComboBoxMov;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author jp
 * @param <T>
 */
public class MovComboBox<T extends Objeto> implements InterfaceComboBoxMov {

    private final JComboBox<String> COMBO_BOX;
    private final DefaultComboBoxModel<String> MODELO;
    private final MemoCache2<T> cache;
    private int strCont;

    public MovComboBox(JComboBox<String> COMBO_BOX, MemoCache2<T> cache) {
        this.COMBO_BOX = COMBO_BOX;
        this.MODELO = (DefaultComboBoxModel<String>) COMBO_BOX.getModel();
        this.cache = cache;
        this.strCont = -1;
    }

    @Override
    public JComboBox<String> getJComboBox() {
        return COMBO_BOX;
    }

    @Override
    public DefaultComboBoxModel<String> getModel() {
        return MODELO;
    }

    @Override
    public void add(String o) {
        MODELO.addElement(o);
    }

    @Override
    public void remove(int index) {
        MODELO.removeElementAt(index);
    }

    @Override
    public String get(int index) {
        return MODELO.getElementAt(index);
    }

    @Override
    public void set(int index, String o) {
        MODELO.insertElementAt(o, index);
    }

    public int getSelect() {
        return COMBO_BOX.getSelectedIndex() - 1;
    }

    @Override
    public void llenar() {
        ArrayList<T> lista = cache.getMEMORIA();
        for (T t : lista) {
            MODELO.addElement(t.getStringElements(strCont));
        }
    }

    public void setStrCont(int strCont) {
        this.strCont = strCont;
    }

    @Override
    public void vaciar() {
        while (MODELO.getSize() > 0) {            
            MODELO.removeElementAt(0);
        }
    }

    @Override
    public void actualizar() {
        vaciar();
        llenar();
    }

}
