/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.util.cache.FabricaCache;
import com.jblue.vista.ventanas.menus.bd.MenuCalles;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class CCalles {

    private final MenuCalles R;

    public CCalles(MenuCalles R) {
        this.R = R;
    }

    public ArrayList<OCalles> buscar(String buscado, ArrayList<OCalles> info, ArrayList<OCalles> lista_aux) {
        lista_aux.clear();
        vaciarTabla();
        //
        buscado = limpiar(buscado);
        //
        for (OCalles o : info) {
            String aux = limpiar(o.getStringR());
            if (!aux.contains(buscado)) {
                continue;
            }
            R.getModelo_Tabla().addRow(o.getInfo());
            lista_aux.add(o);
        }
        return lista_aux;
    }

    public void cargarTabla() {
        ArrayList<OCalles> lista = FabricaCache.MC_CALLES.getLista();
        DefaultTableModel modelo_Tabla = R.getModelo_Tabla();

        for (OCalles oCalles : lista) {
            modelo_Tabla.addRow(oCalles.getInfo());
        }
    }

    public void vaciarTabla() {
        DefaultTableModel modelo_Tabla = R.getModelo_Tabla();
        if (modelo_Tabla.getRowCount() == 0) {
            return;
        }
        while (modelo_Tabla.getRowCount() > 0) {
            modelo_Tabla.removeRow(0);
        }
    }

    public void actualizarTabla() {
        vaciarTabla();
        cargarTabla();
    }

    public String limpiar(String txt) {
        return txt.trim().replace(" ", "").toUpperCase();
    }

}
