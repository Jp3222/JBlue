/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.OwnerRegisterProcessService;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.views.framework.AbstractProcessView;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class ProcessController extends AbstractDBViewController<ProcessWrapperDTO> {

    private static final long serialVersionUID = 1L;

    private final AbstractProcessView<UserDTO> view;
    private final OwnerRegisterProcessService service;

    public ProcessController(AbstractProcessView<UserDTO> view) {
        this.view = view;
        this.service = new OwnerRegisterProcessService(AppConfig.isDevMessages(), view.getProcessName());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void save() {
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            boolean res = service.save(c, view.getProcessId(), view.getProcessWrapper());
            if (!res) {
            }
            int showConfirmDialog = JOptionPane.showConfirmDialog(view, "¿DESEAS EXPORTAR LOS FORMATOS?");
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
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
