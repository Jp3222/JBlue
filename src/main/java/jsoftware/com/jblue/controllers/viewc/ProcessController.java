/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dao.PaymentListDAO;
import jsoftware.com.jblue.model.dao.PaymentsDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.UserDao;
import jsoftware.com.jblue.model.dao.WaterIntakeDAO;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.exp.ProcessException;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.UserService;
import jsoftware.com.jblue.model.service.WaterIntakeService;
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

    private final UserDao user_dao;
    private final WaterIntakeDAO wki_dao;
    private final PaymentListDAO pym_list_dao;
    private final PaymentsDAO pym_dao;
    private final ProcessDAO process_dao;
    private final HysHistoryDAO hys_dao;
    private final UserService usr_service;
    private final WaterIntakeService wki_service;

    public ProcessController(AbstractProcessView<UserDTO> view) {
        this.view = view;
        this.user_dao = new UserDao(AppConfig.isDevMessages(), view.getProcessName());
        this.wki_dao = new WaterIntakeDAO(AppConfig.isDevMessages(), view.getProcessName());
        this.pym_list_dao = new PaymentListDAO(AppConfig.isDevMessages(), view.getProcessName());
        this.pym_dao = new PaymentsDAO(AppConfig.isDevMessages(), view.getProcessName());
        this.process_dao = new ProcessDAO(AppConfig.isDevMessages(), view.getProcessName());
        hys_dao = new HysHistoryDAO(AppConfig.isDevMessages(), view.getProcessName());
        this.usr_service = new UserService(view.getProcessId(), user_dao, hys_dao, process_dao);
        this.wki_service = new WaterIntakeService(view.getProcessId(), wki_dao, hys_dao, process_dao);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void save() {
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            c.setAutoCommit(false);
            ProcessWrapperDTO pw = view.getProcessWrapper();
            boolean res = usr_service.save(c, pw.getUser());
            if (res) {
                throw new ProcessException(1, "El tramite no ha podido ser capturado");
            }

            res = wki_service.save(c, pw.getWater_intake());
            if (res) {
                throw new ProcessException(4, "El tramite no ha podido ser finalizado");
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
