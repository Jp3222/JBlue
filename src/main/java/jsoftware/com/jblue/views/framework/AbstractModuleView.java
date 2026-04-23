/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import java.awt.CardLayout;
import java.util.List;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.model.dto.wrp.ModuleWrapperDTO;

/**
 *
 * @author juanp
 */
public abstract class AbstractModuleView<T extends ModuleWrapperDTO> extends SimpleView {

    private static final long serialVersionUID = 1L;

    protected CardLayout card_layout;

    /**
     *
     */
    private final T dto_wrapper;
    /**
     *
     */
    private final Controller controller;

    protected List<AbstractModuleView<T>> views;
    protected int current_index;

    public AbstractModuleView(T dto_wrapper) {
        this.dto_wrapper = dto_wrapper;
        this.controller = dto_wrapper.getController("MAIN");
        views = null;
    }

    @Override
    public void build() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void events() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void components() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialState() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void finalState() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public abstract void getData();

    public T getDtoWrapper() {
        return dto_wrapper;
    }

    public Controller getMainController() {
        return controller;
    }

    public CardLayout getCardLayout() {
        return card_layout;
    }

}
