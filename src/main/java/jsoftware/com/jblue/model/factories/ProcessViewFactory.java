/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.views.UserView;
import jsoftware.com.jblue.views.framework.AbstractProcessView.ProcessViewBuilder;
import jsoftware.com.jblue.views.process.ConsumerRegisterProcessView;
import jsoftware.com.jblue.views.process.OwnerChangerProcessView;
import jsoftware.com.jblue.views.process.UserRegisterProcessView;

/**
 *
 * @author juanp
 */
public class ProcessViewFactory {

    public static final ProcessViewFactory intance = new ProcessViewFactory();

    public static ProcessViewFactory getIntance() {
        return intance;
    }

    private final ProcessViewBuilder init;
    private final EmployeeDTO current_employee;

    public ProcessViewFactory() {
        this.current_employee = SystemSession.getInstancia().getCurrentEmployee();
        this.init = new ProcessViewBuilder()
                .setDev_flag(AppConfig.isDevMessages())
                .setProcess(true)
                .setProcess_wrapper(new ProcessWrapperDTO(current_employee));
    }

    public UserRegisterProcessView getUserRegisterProcess() {
        return new ProcessViewBuilder(init)
                .setProcess_name("REGISTRO DE TITULAR")
                .setProcess_id("1")
                .builder(UserRegisterProcessView.class);
    }

    public ConsumerRegisterProcessView getConsumerRegisterProcess() {
        return new ProcessViewBuilder(init)
                .setProcess_name("REGISTRO DE CONSUMIDOR")
                .setProcess_id("2")
                .builder(ConsumerRegisterProcessView.class);
    }

    public UserView getUserProcess() {
        return new ProcessViewBuilder(init)
                .setProcess(false)
                .setProcess_name("ACTUALIZACION DE USUARIO")
                .setProcess_id("10")
                .builder(UserView.class);
    }

    public OwnerChangerProcessView getOwnerChangerProcess() {
        return new ProcessViewBuilder(init)
                .setProcess_name("ACTUALIZACION DE USUARIO")
                .setProcess_id("11")
                .builder(OwnerChangerProcessView.class);
    }

}
