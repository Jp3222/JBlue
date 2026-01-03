/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import java.util.HashMap;
import java.util.Map;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.views.UserView;
import jsoftware.com.jblue.views.framework.ProcessViewBuilder;
import jsoftware.com.jblue.views.process.ConsumerRegisterProcessView;
import jsoftware.com.jblue.views.process.OwnerChangerProcessView;
import jsoftware.com.jblue.views.process.UserRegisterProcessView;

/**
 *
 * @author juanp
 */
public final class ProcessViewFactory {

    public static final ProcessViewFactory intance = new ProcessViewFactory();

    public static ProcessViewFactory getIntance() {
        return intance;

    }

    private final ProcessViewBuilder init;
    private final EmployeeDTO current_employee;
    private final Map<String, ViewProvider<?, ?>> factory;

    public ProcessViewFactory() {
        this.factory = new HashMap<>();
        this.current_employee = SystemSession.getInstancia().getCurrentEmployee();
        this.init = new ProcessViewBuilder()
                .setDev_flag(AppConfig.isDevMessages())
                .setProcess(true)
                .setProcess_wrapper(new ProcessWrapperDTO(current_employee));
    }

    public UserRegisterProcessView getUserRegisterProcess() {
        ProcessViewBuilder o = new ProcessViewBuilder(init)
                .setProcess_name("REGISTRO DE TITULAR")
                .setProcess(true)
                .setProcess_id("1");
        return (UserRegisterProcessView) o.builder(UserRegisterProcessView.class.getName(), o);
    }

    public ConsumerRegisterProcessView getConsumerRegisterProcess() {
        ProcessViewBuilder o = new ProcessViewBuilder(init)
                .setProcess_name("REGISTRO DE CONSUMIDOR")
                .setProcess(true)
                .setProcess_id("2");
        return (ConsumerRegisterProcessView) o.builder(ConsumerRegisterProcessView.class.getName(), o);
    }

    public UserView getUserProcess() {
        ProcessViewBuilder o = new ProcessViewBuilder(init)
                .setProcess(false)
                .setProcess_name("ACTUALIZACION DE USUARIO")
                .setProcess_id("10");
        return (UserView) o.builder(UserView.class.getName(), o);
    }

    public OwnerChangerProcessView getOwnerChangerProcess() {
        ProcessViewBuilder o = new ProcessViewBuilder(init)
                .setProcess_name("ACTUALIZACION DE USUARIO")
                .setProcess_id("11");
        return (OwnerChangerProcessView) o.builder(OwnerChangerProcessView.class.getName(), o);
    }

    public Map<String, ViewProvider<?, ?>> getFactory() {
        return factory;
    }

}
