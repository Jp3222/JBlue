/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.sucls.Objeto;
import com.jblue.util.cache.FabricaCache;
import com.jblue.vista.ventanas.menus.bd.MenuUsuarios;
import java.util.ArrayList;
import java.util.function.Predicate;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class CUsuarios {

    private final MenuUsuarios R;

    public CUsuarios(MenuUsuarios vista) {
        this.R = vista;
    }

    public void cargarTodo() {
        cargarComboModel(R.getJcbCalle(), FabricaCache.MC_CALLES.getLista());
        cargarComboModel(R.getJcbTipoToma(), FabricaCache.MC_TIPOS_DE_TOMAS.getLista());
        cargarComboModel(R.getJcbTitular(), FabricaCache.MC_USUARIOS.getLista(), (t) -> t.isTitular());
        cargarTabla();
    }

    public void cargarFiltros() {
        R.getFiltro_calle().addItem("SELECCIONE CALLE");
        R.getFiltro_toma().addItem("SELECCIONE TIPO DE TOMA");
        cargarComboModel(R.getFiltro_calle(), FabricaCache.MC_CALLES.getLista());
        cargarComboModel(R.getFiltro_toma(), FabricaCache.MC_TIPOS_DE_TOMAS.getLista());
    }

    public void vaciarFiltros() {
        vaciarCB(R.getFiltro_calle());
        vaciarCB(R.getFiltro_toma());
    }

    public void vaciarCB(JComboBox<String> componente) {
        if (componente.getItemCount() > 0) {
            componente.removeAllItems();
        }
    }

    public void actualizarVistaPrincipal() {
        actualizarComboModel(R.getJcbTitular(), FabricaCache.MC_USUARIOS.getLista(), (t) -> t.isTitular());
        actualizarLista();
    }

    public void vaciarTodo() {
        R.getModelo_lista().removeAllElements();
        R.getJcbCalle().removeAllItems();
        R.getJcbTipoToma().removeAllItems();
        R.getJcbTitular().removeAllItems();
        R.estadoInicial();
    }

    /**
     * 0 calles
     * <br>1 tipo tomas
     * <br>2 titular
     *
     * @param i
     */
    public void cargarJCB(int i) {
        switch (i) {
            case 0 ->
                cargarComboModel(R.getJcbCalle(), FabricaCache.MC_CALLES.getLista());
            case 1 ->
                cargarComboModel(R.getJcbTipoToma(), FabricaCache.MC_TIPOS_DE_TOMAS.getLista());
            case 2 ->
                cargarComboModel(R.getJcbTitular(), FabricaCache.MC_USUARIOS.getLista(), (t) -> t.isTitular());
        }
    }

    /**
     * 0 calles
     * <br>1 tipo tomas
     * <br>2 titular
     *
     * @param i
     */
    public void actualizarJCB(int i) {
        switch (i) {
            case 0 ->
                actualizarComboModel(R.getJcbCalle(), FabricaCache.MC_CALLES.getLista());
            case 1 ->
                cargarComboModel(R.getJcbTipoToma(), FabricaCache.MC_TIPOS_DE_TOMAS.getLista());
            case 2 ->
                cargarComboModel(R.getJcbTitular(), FabricaCache.MC_USUARIOS.getLista(), (t) -> t.isTitular());
        }
    }

    public <T extends Objeto> void cargarComboModel(JComboBox<String> componente, ArrayList<T> lista) {
        if (lista.isEmpty()) {
            return;
        }
        for (T t : lista) {
            componente.addItem(t.getStringR().replace("_", " "));
        }
    }

    public <T extends Objeto> void cargarComboModel(JComboBox<String> componente, ArrayList<T> lista, Predicate<T> filtro) {
        if (lista.isEmpty()) {
            return;
        }
        for (T t : lista) {
            if (!filtro.test(t)) {
                continue;
            }
            componente.addItem(t.getStringR().replace("_", " "));
        }
    }

    public <T extends Objeto> void actualizarComboModel(JComboBox<String> componente, ArrayList<T> lista) {
        int size = componente.getModel().getSize();
        if (size > 0) {
            componente.removeAllItems();
        }
        cargarComboModel(componente, lista);
    }

    public <T extends Objeto> void actualizarComboModel(JComboBox<String> componente, ArrayList<T> lista, Predicate<T> filtro) {
        int size = componente.getModel().getSize();
        if (size > 0) {
            componente.removeAllItems();
        }
        cargarComboModel(componente, lista, filtro);
    }

    public void cargarLista() {
        ArrayList<OUsuarios> lista = FabricaCache.MC_USUARIOS.getLista();
        if (lista.isEmpty()) {
            return;
        }
        DefaultListModel<String> modelo_lista = R.getModelo_lista();
        for (OUsuarios oUsuarios : lista) {
            modelo_lista.addElement(oUsuarios.getStringR().replace("_", " "));
        }
    }

    public void actualizarLista() {
        DefaultListModel<String> modelo_lista = R.getModelo_lista();
        if (!modelo_lista.isEmpty()) {
            modelo_lista.clear();
        }
        cargarLista();
    }

    public void cargarTabla() {
        ArrayList<OUsuarios> lista = FabricaCache.MC_USUARIOS.getLista();
        if (lista.isEmpty()) {
        }
        DefaultTableModel modelo_tabla = R.getModelo_tabla();
        for (OUsuarios oUsuarios : lista) {
            modelo_tabla.addRow(oUsuarios.getInfoSinFK());
        }
    }

    public void actualizarTabla() {
        DefaultTableModel modelo_tabla = R.getModelo_tabla();
        if (modelo_tabla.getRowCount() > 0) {
            vaciarTabla();
        }
        cargarTabla();
    }

    public void vaciarTabla() {
        DefaultTableModel modelo_tabla = R.getModelo_tabla();
        while (modelo_tabla.getRowCount() > 0) {
            modelo_tabla.removeRow(0);
        }
    }

    public void filtros_calles() {

    }

    public void filtros_tomas() {
    }

}
