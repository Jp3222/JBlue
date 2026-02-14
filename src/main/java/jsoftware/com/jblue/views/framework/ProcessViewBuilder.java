/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import java.io.Serializable;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jblue.views.ShopCartView;
import jsoftware.com.jblue.views.UserView;
import jsoftware.com.jblue.views.process.ConsumerRegisterProcessView;
import jsoftware.com.jblue.views.process.OwnerChangerProcessView;
import jsoftware.com.jblue.views.process.OwnerRegisterProcessView;

/**
 *
 * @author juanp
 */
public class ProcessViewBuilder implements Serializable {

    private static final long serialVersionUID = 1L;

    private String process_id;
    private boolean process;
    private String process_name;
    private ProcessWrapperDTO process_wrapper;
    private boolean dev_flag;

    public ProcessViewBuilder(String process_id, boolean process, String process_name, ProcessWrapperDTO process_wrapper) {
        this.process_id = process_id;
        this.process = process;
        this.process_name = process_name;
        this.process_wrapper = process_wrapper;
        this.dev_flag = false;
    }

    public ProcessViewBuilder() {
        this(null, true, null, null);
    }

    public ProcessViewBuilder(ProcessViewBuilder other) {
        this(other.getProcess_id(), other.isProcess(), other.getProcess_name(), other.getProcess_wrapper());
        this.dev_flag = other.isDev_flag();
    }

    public ProcessViewBuilder setProcess(boolean process) {
        this.process = process;
        return this;
    }

    public ProcessViewBuilder setProcess_id(String process_id) {
        this.process_id = process_id;
        return this;
    }

    public ProcessViewBuilder setProcess_name(String process_name) {
        this.process_name = process_name;
        return this;
    }

    public ProcessViewBuilder setProcess_wrapper(ProcessWrapperDTO process_wrapper) {
        this.process_wrapper = process_wrapper;
        return this;
    }

    public ProcessViewBuilder setDev_flag(boolean dev_flag) {
        this.dev_flag = dev_flag;
        return this;
    }

    public String getProcess_id() {
        return process_id;
    }

    public boolean isProcess() {
        return process;
    }

    public String getProcess_name() {
        return process_name;
    }

    public ProcessWrapperDTO getProcess_wrapper() {
        return process_wrapper;
    }

    public boolean isDev_flag() {
        return dev_flag;
    }

    public AbstractProcessView<?> builder(String cls, ProcessViewBuilder i) {
        try {
            if (UserView.class.getName().equals(cls)) {
                return new UserView(i);
            }
            if (OwnerRegisterProcessView.class.getName().equals(cls)) {
                return new OwnerRegisterProcessView(i);
            }
            if (ConsumerRegisterProcessView.class.getName().equals(cls)) {
                return new ConsumerRegisterProcessView(i);
            }
            if (OwnerChangerProcessView.class.getName().equals(cls)) {
                return new OwnerChangerProcessView(i);
            }
            if (ShopCartView.class.getName().equals(cls)) {
                return new ShopCartView(i);
            }
        } catch (Exception ex) {
            System.getLogger(ProcessViewBuilder.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}
