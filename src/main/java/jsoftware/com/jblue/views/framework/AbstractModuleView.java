/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import jsoftware.com.jblue.model.view.ProcessModel;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.model.dto.wrp.ModuleWrapperDTO;
import jsoftware.com.jblue.util.Func;

/**
 *
 * @author juanp
 */
public abstract class AbstractModuleView<T extends ModuleWrapperDTO> extends SimpleView implements ProcessModel {

    private static final long serialVersionUID = 1L;

    protected CardLayout card_layout;

    /**
     * DTO envoltorio con todos los objetos necesarios para manejar el proceso
     */
    protected T dto_wrapper;
    /**
     * Controlador principal de la vista
     */
    protected Controller controller;

    protected final List<AbstractModuleView<T>> views;
    protected int current_index;

    public AbstractModuleView(T dto_wrapper) {
        super();
        if (Func.isNotNull(dto_wrapper)) {
            this.dto_wrapper = dto_wrapper;
            if (dto_wrapper.getController_map().containsKey(CModel.MAIN_CONTROLLER)) {
                this.controller = dto_wrapper.getController(CModel.MAIN_CONTROLLER);
            }
            this.setName(dto_wrapper.getModule_name());
        }
        this.views = new ArrayList<>(10);
        this.current_index = 0;
    }

    public AbstractModuleView(T dto_wrapper, Controller controller) {
        this(dto_wrapper);
        this.controller = controller;
    }

    public AbstractModuleView() {
        super();
        this.views = new ArrayList<>(10);
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

    public void setDto_wrapper(T dto_wrapper) {
        this.dto_wrapper = dto_wrapper;
    }

    public T getDtoWrapper() {
        return dto_wrapper;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public CardLayout getCardLayout() {
        return card_layout;
    }

    @Override
    public int getProcessId() {
        return dto_wrapper.getModule_id() == null ? -1 : Integer.parseInt(dto_wrapper.getModule_id());
    }

    @Override
    public void setProcessId(int id) {
        dto_wrapper.setModule_id(String.valueOf(id));
    }

    @Override
    public String getProcessName() {
        return dto_wrapper.getModule_name();
    }

    @Override
    public void setProcessName(String process_name) {
        dto_wrapper.setModule_name(process_name);
    }

}
