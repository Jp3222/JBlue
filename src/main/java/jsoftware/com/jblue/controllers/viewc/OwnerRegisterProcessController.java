/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.OwnerRegisterProcessService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.mod.pro.OwnerRegisterProcess;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class OwnerRegisterProcessController extends AbstractDBViewController<ProcessWrapperDTO> {

    private static final long serialVersionUID = 1L;

    private OwnerRegisterProcess view;
    private final OwnerRegisterProcessService service;

    public OwnerRegisterProcessController(boolean flag_dev, String mod_name) {
        this.service = new OwnerRegisterProcessService(flag_dev, mod_name);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            case "search" ->
                search();
            case "payment_confirm" ->
                payment_confirm();
            case "documet_confirm" ->
                document_confirm();
        }
    }

    @Override
    public void save() {
        boolean res = false;
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            if (!ss.isAdministrationValid()) {
                returnMessage(view, false, "LA ADMINISTRACION ACTUAL NO SE HA REGISTRADO O NO ES VALIDA");
                return;
            }

            res = service.save(c, view.getDtoWrapper());
            if (service.isError()) {
                returnMessage(view, false, service.getUserMessage());
                return;
            }
            
            int showConfirmDialog = JOptionPane.showConfirmDialog(view, "¿DESEAS EXPORTAR LOS FORMATOS?");
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(view, "EL FORMATO HA SIDO EXPORTADO CORRECTAMENTE", "FORMATOS", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            returnMessage(view, false, e.getMessage());
        }
        if (res) {
            returnMessage(view, true);
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

    public void setView(OwnerRegisterProcess aThis) {
        this.view = aThis;
    }

    private void search() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void payment_confirm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void document_confirm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
