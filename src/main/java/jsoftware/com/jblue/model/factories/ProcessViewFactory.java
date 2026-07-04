
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import java.io.Serializable;

/**
 *
 * @author juanp
 */
@Deprecated
public final class ProcessViewFactory implements Serializable {

    public static final ProcessViewFactory instance = new ProcessViewFactory();
    private static final long serialVersionUID = 1L;

    public static ProcessViewFactory getInstance() {
        return instance;

    }
//
//    private final ProcessViewBuilder init;
//    private final EmployeeUserDTO current_employee;
//
//    public ProcessViewFactory() {
//        this.current_employee = SystemSession.getInstancia().getCurrentEmployee();
//        this.init = new ProcessViewBuilder()
//                .setDev_flag(AppConfig.isDevMessages())
//                .setProcess(true)
//                .setProcess_wrapper(new ProcessWrapperDTO("0", "name"));
//    }
//
//    public OwnerRegisterProcessView getUserRegisterProcess() {
//        ProcessViewBuilder o = new ProcessViewBuilder(init)
//                .setProcess_name("REGISTRO DE TITULAR")
//                .setProcess(true)
//                .setProcess_id("1");
//        return (OwnerRegisterProcessView) o.builder(OwnerRegisterProcessView.class, o);
//    }
//
//    public ConsumerRegisterProcessView getConsumerRegisterProcess() {
//        ProcessViewBuilder o = new ProcessViewBuilder(init)
//                .setProcess_name("REGISTRO DE CONSUMIDOR")
//                .setProcess(true)
//                .setProcess_id("2");
//        return (ConsumerRegisterProcessView) o.builder(ConsumerRegisterProcessView.class, o);
//    }
//
//    public UserView getUserProcess() {
//        ProcessViewBuilder o = new ProcessViewBuilder(init)
//                .setProcess(false)
//                .setProcess_name("ACTUALIZACION DE USUARIO")
//                .setProcess_id("10");
//        return (UserView) o.builder(UserView.class, o);
//    }
//
//    public OwnerChangerProcessView getOwnerChangerProcess() {
//        ProcessViewBuilder o = new ProcessViewBuilder(init)
//                .setProcess_name("ACTUALIZACION DE USUARIO")
//                .setProcess_id("11");
//        return (OwnerChangerProcessView) o.builder(OwnerChangerProcessView.class, o);
//    }
//
//    public ShopCartView getShopCarProcess() {
//        ProcessViewBuilder o = new ProcessViewBuilder(init)
//                .setProcess_name("CAJA DE COBRO")
//                .setProcess_id("13");
//        return (ShopCartView) o.builder(ShopCartView.class, o);
//    }
//
//    public WaterIntakeTypeProcess getIntakeTypeView() {
//        ProcessViewBuilder o = new ProcessViewBuilder(init)
//                .setProcess_name("TIPO DE TOMAS DE AGUA")
//                .setProcess_id("14");
//        return (WaterIntakeTypeProcess) o.builder(WaterIntakeTypeProcess.class, o);
//    }
//
//    public StreetProcess getStreetView() {
//        ProcessViewBuilder o = new ProcessViewBuilder(init)
//                .setProcess_name("CALLES REGISTRADAS")
//                .setProcess_id("15");
//        return (StreetProcess) o.builder(StreetProcess.class, o);
//    }

}
