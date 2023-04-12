/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.util.cache.FabricaCache;
import com.jblue.vista.ventanas.menus.bd.MenuTomas;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class CTipoTomas {

    private final MenuTomas R;

    public CTipoTomas(MenuTomas R) {
        this.R = R;
    }

    public void buscar(String txt, ArrayList<OTipoTomas> cache, ArrayList<OTipoTomas> lista_auxiliar) {
        lista_auxiliar.clear();
        vaciarTabla();
        txt = limpiar(txt);
        for (OTipoTomas o : cache) {
            String aux = limpiar(o.getTipo());
            if (aux.contains(txt)) {
                R.getModelo_Tabla().addRow(o.getInfo());
                lista_auxiliar.add(o);
            }
        }
    }

    String limpiar(String txt) {
        return txt.trim().replace(" ", "").toUpperCase();
    }

    public void cargarTabla() {
        ArrayList<OTipoTomas> lista = FabricaCache.MC_TIPOS_DE_TOMAS.getLista();
        DefaultTableModel modelo_Tabla = R.getModelo_Tabla();
        for (OTipoTomas i : lista) {
            modelo_Tabla.addRow(i.getInfo());
        }
    }

    public void vaciarTabla() {
        DefaultTableModel modelo_Tabla = R.getModelo_Tabla();
        while (modelo_Tabla.getRowCount() > 0) {
            modelo_Tabla.removeRow(0);
        }
    }

    public void actualizarTabla() {
        vaciarTabla();
        cargarTabla();
    }
}
