/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.exp.SystemException;
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
    private final Map<String, JTable> models_map;

    public OwnerRegisterProcessController(boolean flag_dev, String mod_name) {
        this.service = new OwnerRegisterProcessService(flag_dev, mod_name);
        models_map = new HashMap<>();
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
            case "load_payment_concept" ->
                loadPaymentConcept(e.getActionCommand());
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
            if (service.isError()) {
                returnMessage(view, false, service.getUserMessage());
                return;
            }
            //CONFIRMAR LA REIMPRESION DE FORMATO
            int showConfirmDialog = JOptionPane.showConfirmDialog(view, "¿DESEAS EXPORTAR LOS FORMATOS?");
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(view, "EL FORMATO HA SIDO EXPORTADO CORRECTAMENTE", "FORMATOS", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | SystemException e) {
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

    public void add(String comand, JTable model) {
        models_map.put(comand, model);
    }

    private void search() {
        boolean res = false;
        try (JDBConnection c = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            //VALIDACIONES QUE NO PERMITEN REGISTRO
            ss.systemValid(c);
            //VALIDAR LA SESSION
            if (ss.isLock(c)) {
                returnMessage(view, false, "LA SESION ACTUAL HA CADUCADO");
                return;
            }
            ProcessWrapperDTO dto = view.getDtoWrapper();
            //EJECUTAR EL MOVIMIENTO
            res = service.search(c, dto);
            if (service.isError()) {
                returnMessage(view, false, service.getUserMessage());
                return;
            }
            //CONFIRMAR LA REIMPRESION DE FORMATO
            if (dto.isUser_exists()) {
                JOptionPane.showMessageDialog(view, "EXISTEN DATOS ASOCIADOS A ESTE RFC, SI DESEA HACER ALGUNA CORRECCION, FAVOR DE IR AL MODULO DE EDICION DE INFORMACION");
            }
            view.showData();
        } catch (SQLException e) {
            returnMessage(view, false, e.getMessage());
        } catch (SystemException ex) {
            returnMessage(view, false, ex.getUserMessage());
        }
        if (res) {
            returnMessage(view, true);
        }
    }

    private void payment_confirm() {
        int input = JOptionPane.showConfirmDialog(view, "¿DESEA CONFIRMAR PAGO?", "CONFIRMAR PAGO", JOptionPane.INFORMATION_MESSAGE);
        ProcessWrapperDTO dto = view.getDtoWrapper();
        //VALIDA QUE EL EMPLEADO ACTUAL RECIBIO EL PAGO
        if (input == JOptionPane.YES_OPTION) {
            //SI EL PAGO ES VALIDADO, SE ASIGNA TRUE AL PAGO Y FALSE A LA GENERACION DE UNA LINEA
            dto.setPayment_valid(true);
            dto.setPayment_line_valid(false);
        } else {
            //SI, NO SE ASIGNA FALSE Y TRUE A LA GENERACION DE LA LINEA
            dto.setPayment_valid(false);
            dto.setPayment_line_valid(true);
        }
    }

    private void document_confirm() {
        int input = JOptionPane.showConfirmDialog(view, "¿COFIRMA QUE LA INFORMACION DE LOS DOCUMENTOS ES CORRECTA?", "VALIDAR DOCUMENTOS", JOptionPane.INFORMATION_MESSAGE);
        ProcessWrapperDTO dto = view.getDtoWrapper();

        //VALIDA QUE LOS DOCUMEMTOS PRESENTADOS SON VALIDOS Y CON LA INFORMACION CONSISTENTE PARA EL TRAMITE
        if (input == JOptionPane.YES_OPTION) {
            // SI LO SON SE PUEDE CONTINUAR CON EL TRAMITE
            dto.setDocument_list_valid(true);
            dto.setDocument_record_valid(true);
        } else {
            //SI NO LO SON, SE REGISTRA LA INFORMACION CAPTURADA DEL USUARIO Y SU RFC Y/O CURP SE PONE EN SITUACION CRITICA
            dto.setDocument_list_valid(false);
            dto.setDocument_record_valid(false);
            lock(dto);
        }
    }

    private void lock(ProcessWrapperDTO dto) {
        try (JDBConnection connnection = ConnectionFactory.getIntance().getProcessConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            boolean res = false;
            res = service.userLocked(connnection, ss, dto);
            if (service.isError()) {
                returnMessage(view, false, service.getUserMessage());
                return;
            }
            if (res) {
                returnMessage(view, res, "EL USUARIO SE HA COLOCADO EN SITUACION CRITICA");
            }
        } catch (SQLException ex) {
            returnMessage(view, false, ex.getMessage());
        }
    }

    public void loadPaymentConcept(String comand) {
        try (JDBConnection connnection = ConnectionFactory.getIntance().getProcessConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            ProcessWrapperDTO dto = view.getDtoWrapper();
            JTable get = models_map.get(comand);
            service.load(connnection, ss, dto, get);
        } catch (SQLException ex) {
            returnMessage(view, false, ex.getMessage());
        }
        view.showData();
    }

}
