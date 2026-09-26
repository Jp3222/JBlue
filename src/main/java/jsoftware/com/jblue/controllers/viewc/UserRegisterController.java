/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.compc.FormController;
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

}
