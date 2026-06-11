/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import static jsoftware.com.jblue.controllers.DBControllerModel.SAVE_COMMAND;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.EmployeeRegisterService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.mod.pro.EmployeeRegisterProcess;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class EmployeeRegisterController extends AbstractDBViewController<EmployeeRegisterWrapperDTO> {

    private static final long serialVersionUID = 1L;

    private EmployeeRegisterProcess view;
    private EmployeeRegisterService service;

    public EmployeeRegisterController(boolean flag_dev, String module_name) {
        this.service = new EmployeeRegisterService(false, module_name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            case "GENERATE" ->
                generate();
        }
    }

    @Override
    public void save() {
        System.out.println("save");
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            if (ss.isLock()) {
                returnMessage(view, false, "LA SESION HA CADUCADO, CIERRE EL PROGRAMA Y VUELVA A INTENTAR");
            }
//            if (!ss.isAdministrationValid()) {
//                returnMessage(view, false, "LA ADMINISTRACION ACTUAL NO SE HA REGISTRADO O NO ES VALIDA");
//                return;
//            }
            boolean res = service.insert(c, view.getDtoWrapper());
            if (service.isError()) {
                returnMessage(view, service.getUserMessage());
                return;
            }
            returnMessage(view, res);
        } catch (Exception e) {
            log(e, "save");
            returnMessage(view, false, "ERROR INTERNO");
        }
    }

    public void log(Exception e, String method_name) {
        FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, view.getDtoWrapper().getModule_name(), method_name, e.getMessage());
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setView(EmployeeRegisterProcess view) {
        this.view = view;
    }

    private void generate() {
        view.showData();
    }

}
