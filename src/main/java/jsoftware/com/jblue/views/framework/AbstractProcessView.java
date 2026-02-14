/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import jsoftware.com.jblue.controllers.compc.TableController;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public abstract class AbstractProcessView<T extends JDBMapObject> extends SimpleView implements ProcessViewModel {

    private static final long serialVersionUID = 1L;
    private final String process_id;
    private final boolean dev_flag;
    private boolean process;
    private String process_type;
    private ProcessWrapperDTO process_wrapper;
    private ProcessViewBuilder builder;
    protected TableController<T> table_controller;
    protected int view_show;

    public AbstractProcessView(ProcessViewBuilder builder) {
        this.builder = builder;
        this.process_id = builder.getProcess_id();
        this.process = builder.isProcess();
        this.process_type = builder.getProcess_name();
        this.process_wrapper = builder.getProcess_wrapper();
        this.dev_flag = builder.isDev_flag();
    }

    @Override
    public void setProcessTypeName(String module_name) {
        this.process_type = module_name;
    }

    @Override
    public String getProcessTypeName() {
        return process_type != null ? process_type : getName();
    }

    public String getProcessId() {
        return process_id;
    }

    @Override
    public void setProcess(boolean flag) {
        this.process = flag;
    }

    @Override
    public boolean isProcess() {
        return process;
    }

    public boolean isDev_flag() {
        return dev_flag;
    }

    @Override
    public void build() {
    }

    @Override
    public void events() {
    }

    @Override
    public void components() {
    }

    @Override
    public void initialState() {
    }

    @Override
    public void finalState() {
    }

    public void setProcessWrapper(ProcessWrapperDTO process_wrapper) {
        this.process_wrapper = process_wrapper;

    }

    public ProcessWrapperDTO getProcessWrapper() {
        return process_wrapper;
    }

    public void setBuilder(ProcessViewBuilder builder) {
        this.builder = builder;
    }

    public ProcessViewBuilder getBuilder() {
        return builder;
    }

    public abstract void getDataView();
}
