
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dao.PaymentsDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class PaymentService {
    
    private String process_id;
    private PaymentsDAO payment_dao;
    private HysHistoryDAO history_dao;
    private ProcessDAO process_dao;

    public PaymentService(String process_id, PaymentsDAO payment_dao, HysHistoryDAO history_dao, ProcessDAO process_dao) {
        this.process_id = process_id;
        this.payment_dao = payment_dao;
        this.history_dao = history_dao;
        this.process_dao = process_dao;
    }

    public PaymentService(String process_id, boolean flag_dev, String name_module) {
        this.process_id = process_id;
        this.payment_dao = new PaymentsDAO(flag_dev, name_module);
        this.history_dao = new HysHistoryDAO(flag_dev, name_module);
        this.process_dao = new ProcessDAO(flag_dev, name_module);
    }

    public boolean save(JDBConnection connection, String process_id, String user_name,PaymentDTO user) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            //registrar el usuario
            int key = payment_dao.insert(connection, user);
            res = key > 0;
            if (!res) {
                throw new SQLException("REGISTRO DE PAGO ERRONEO");
            }
            //registrar el bitacora
            res = history_dao.insert(40, "SE REGISTRO EL PAGO: %s - ".formatted(
                    key
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //registrar el proceso
            res = process_dao.payProcess(connection, process_id);
            if (!res) {
                throw new SQLException("REGISTRO DEL PROCESO CORRUPTO");
            }
            //registrar en bitacora
            res = history_dao.insert(42, "SE PAGO EL TRAMITE: %s DEL USUARIO: %s".formatted(
                    key,
                    user_name
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
