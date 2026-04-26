/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import java.io.IOException;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import static jsoftware.com.jblue.controllers.DBControllerModel.SAVE_COMMAND;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.EmployeeRegisterService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.mod.com.EmployeeRegistrationView;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class EmployeeRegisterController extends AbstractDBViewController<EmployeeRegisterWrapperDTO> {

    private EmployeeRegistrationView view;
    private EmployeeRegisterService service;

    public EmployeeRegisterController(EmployeeRegistrationView view) {
        this.view = view;
        this.service = new EmployeeRegisterService(AppConfig.isDevMessages(), view.getDtoWrapper().getModule_name());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public void save() {
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            if (!ss.isAdministrationValid()) {
                returnMessage(view, false, "LA ADMINISTRACION ACTUAL NO SE HA REGISTRADO O NO ES VALIDA");
                return;
            }
            boolean res = service.insert(c, view.getDtoWrapper());
            returnMessage(view, res);
        } catch (Exception e) {
            log(e, "save");
        }
    }

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, view.getDtoWrapper().getModule_name(), method_name, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
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
}
