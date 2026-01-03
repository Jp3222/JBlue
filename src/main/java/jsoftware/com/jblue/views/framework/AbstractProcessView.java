/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public abstract class AbstractProcessView<T extends JDBMapObject> extends DBView<T> implements ProcessViewModel {

    private static final long serialVersionUID = 1L;

    private final String process_id;
    private boolean process;
    private String process_name;
    private ProcessWrapperDTO process_wrapper;
    private boolean dev_flag;
    private ProcessViewBuilder builder;

    public AbstractProcessView(ProcessViewBuilder builder) {
        this.builder = builder;
        this.process_id = builder.getProcess_id();
        this.process = builder.isProcess();
        this.process_name = builder.getProcess_name();
        this.process_wrapper = builder.getProcess_wrapper();
        this.dev_flag = builder.isDev_flag();
    }

    @Override
    public void setProcessName(String module_name) {
        this.process_name = module_name;
    }

    @Override
    public String getProcessName() {
        return process_name != null ? process_name : getName();
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

    @Override
    public JTextField getTextComponenteTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTextSearchTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DefaultTableModel getModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setViewShow(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getViewShow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends JDBMapObject> void setObjectSearch(T o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends JDBMapObject> T getObjectSearch() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRowsData(String... info) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setScreenTableInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
