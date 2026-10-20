/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.compc.FormController;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.exp.SystemException;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.UserRegisterService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.mod.pro.UserRegisterProcess;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class UserRegisterController extends FormController {

    private static final long serialVersionUID = 1L;

    private UserRegisterProcess view;
    private UserRegisterService service;

    public UserRegisterController() {
        super();
        service = new UserRegisterService(false, "UserRegisterController");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            case "search" ->
                search();
        }
    }

    @Override
    public void save() {
        boolean res = false;
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            //VALIDACIONES INTERNAS DEL SISTEMA
            ss.systemValid(c);
            //EJECUTAR EL MOVIMIENTO
            res = service.save(c, ss, view.getDtoWrapper());
            if (!res || service.isError()) {
                returnMessage(view, service.getUserMessage(), res);
                return;
            }
            //CONFIRMAR LA REIMPRESION DE FORMATO
            int showConfirmDialog = JOptionPane.showConfirmDialog(view, "¿DESEAS EXPORTAR LOS FORMATOS?");
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(view, "EL FORMATO HA SIDO EXPORTADO CORRECTAMENTE", "FORMATOS", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | SystemException e) {
            returnMessage(view, e.getMessage(), false);
        }

        if (res) {
            returnMessage(view, true);
        }
    }

    public void setView(UserRegisterProcess model) {
        super.setView(model);
        this.view = model;
    }

    private void search() {
        boolean res = false;
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            //VALIDACIONES QUE NO PERMITEN REGISTRO
            ss.systemValid(c);
            //VALIDAR LA SESSION
            if (ss.isLock(c)) {
                returnMessage(view, "LA SESION ACTUAL HA CADUCADO", false);
                return;
            }
            ProcessWrapperDTO dto = view.getDtoWrapper();
            //EJECUTAR EL MOVIMIENTO
            res = service.exists(c, dto.getUser());
            if (service.isError()) {
                returnMessage(view, service.getUserMessage(), false);
                return;
            }
            //CONFIRMAR LA REIMPRESION DE FORMATO
            if (dto.isUser_exists()) {
                JOptionPane.showMessageDialog(view, "EXISTEN DATOS ASOCIADOS A ESTE RFC, SI DESEA HACER ALGUNA CORRECCION, FAVOR DE IR AL MODULO DE EDICION DE INFORMACION");
            }
            view.showData();
        } catch (SQLException | SystemException e) {
            returnMessage(view, e.getMessage(), false);
        }
        if (res) {
            returnMessage(view, true);
        }
    }

}
