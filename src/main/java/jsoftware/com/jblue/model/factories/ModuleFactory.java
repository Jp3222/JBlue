/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import java.io.Serializable;
import jsoftware.com.jblue.controllers.compc.WizardController;
import jsoftware.com.jblue.controllers.viewc.EmployeeRegisterController;
import jsoftware.com.jblue.controllers.viewc.OwnerRegisterProcessController;
import jsoftware.com.jblue.controllers.winc.LoginController;
import jsoftware.com.jblue.controllers.winc.MainController;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.dto.wrp.LoginWrapperDTO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.dto.wrp.ShopCartWrapperDTO;
import jsoftware.com.jblue.model.dto.wrp.WMainMenuWrapperDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.ShopCartProcess;
import jsoftware.com.jblue.views.mod.pro.EmployeeRegisterProcess;
import jsoftware.com.jblue.views.mod.pro.OwnerRegisterProcess;
import jsoftware.com.jblue.views.win.LoginWindows;
import jsoftware.com.jblue.views.win.WMainMenu;

/**
 *
 * @author juanp
 */
public class ModuleFactory implements Serializable {

    private static final long serialVersionUID = 1L;

    private static ModuleFactory instance;

    public static synchronized ModuleFactory getInstance() {
        if (instance == null) {
            instance = new ModuleFactory();
        }
        instance.init();
        return instance;
    }
    private AdministrationHistoryDTO administration_dto;
    private EmployeeUserDTO employee;

    private ModuleFactory() {
        init();
    }

    public void init() {
        administration_dto = SystemSession.getInstancia().getCurrentAdministration();
        employee = SystemSession.getInstancia().getCurrentEmployee();
    }

    public LoginWindows getLoginProcess() {
        LoginWrapperDTO dto = new LoginWrapperDTO("1", "INICIO DE SESION");
        dto.setCurrent_administration(administration_dto);
        dto.setCurrent_employee(employee);
        //Controlador del asistente
        //WizardController controller = new WizardController();
        //dto.putController("MAIN", controller);
        //Controlador de eventos
        LoginController rc = new LoginController(false, dto.getModule_name());
        dto.putController("MAIN", rc);
        //Nueva vista
        LoginWindows mod = new LoginWindows(dto);
        return mod;
    }

    public WMainMenu getWMainMenu() {
        WMainMenuWrapperDTO dto = new WMainMenuWrapperDTO("2", "MENU PRINCIPAL");
        dto.setCurrent_administration(administration_dto);
        dto.setCurrent_employee(employee);
        //CONTROLADOR DE EVENTOS
        MainController controller = new MainController();
        dto.putController("MAIN", controller);
        //NUEVA VISTA
        WMainMenu mod = new WMainMenu(dto);
        return mod;
    }

    public ShopCartProcess getShopCartProcess() {
        ShopCartWrapperDTO dto = new ShopCartWrapperDTO("5", "SHOP CART");
        dto.setCurrent_administration(administration_dto);
        dto.setCurrent_employee(employee);
        //Controlador del asistente
        WizardController controller = new WizardController();
        dto.putController("MAIN", controller);
        //Controlador de eventos
        LoginController rc = new LoginController(false, dto.getModule_name());
        dto.putController("CONTROLLER", rc);
        //Nueva vista
        ShopCartProcess mod = new ShopCartProcess(dto);
        return mod;
    }

    public OwnerRegisterProcess getOwnerRegisterProcess() {
        ProcessWrapperDTO dto = new ProcessWrapperDTO("1", "ALTA DE TITULAR");
        dto.setCurrent_administration(administration_dto);
        dto.setCurrent_employee(employee);
        //Controlador del asistente
        WizardController controller = new WizardController();
        dto.putController("MAIN", controller);
        //Controlador de eventos
        OwnerRegisterProcessController rc = new OwnerRegisterProcessController(false, dto.getModule_name());
        dto.putController("CONTROLLER", rc);
        //Nueva vista
        OwnerRegisterProcess mod = new OwnerRegisterProcess(dto);
        return mod;
    }

    public EmployeeRegisterProcess getRegisterProcess() {
        EmployeeRegisterWrapperDTO dto = new EmployeeRegisterWrapperDTO("14", "REGISTRO DE EMPLEADOS");
        dto.setCurrent_administration(administration_dto);
        dto.setCurrent_employee(employee);
        //Controlador del asistente
        WizardController controller = new WizardController();
        dto.putController("MAIN", controller);
        //Controlador de eventos
        EmployeeRegisterController rc = new EmployeeRegisterController(false, dto.getModule_name());
        dto.putController("CONTROLLER", rc);
        //Nueva vista
        EmployeeRegisterProcess mod = new EmployeeRegisterProcess(dto);
        return mod;
    }

}
