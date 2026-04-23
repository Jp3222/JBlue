/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import java.io.Serializable;
import jsoftware.com.jblue.controllers.compc.WizardController;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.mod.pro.EmployeeRegisterProcess;

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

    public EmployeeRegisterProcess getRegisterProcess() {
        EmployeeRegisterWrapperDTO dto = new EmployeeRegisterWrapperDTO("14", "REGISTRO DE EMPLEADOS");
        dto.setCurrent_administration(administration_dto);
        dto.setCurrent_employee(employee);
        WizardController controller = new WizardController();
        dto.putController("MAIN", controller);
        EmployeeRegisterProcess mod = new EmployeeRegisterProcess(dto);
        return mod;
    }
}
