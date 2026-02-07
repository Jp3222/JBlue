/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.Serializable;
import java.sql.SQLException;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class OwnerRegisterProcessService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UserService user;
    private final PaymentService payment;
    private final WaterIntakeService water_intake;

    public OwnerRegisterProcessService(boolean flag_dev, String name_module) {
        user = new UserService(flag_dev, name_module);
        payment = new PaymentService(flag_dev, name_module);
        water_intake = new WaterIntakeService(flag_dev, name_module);
    }

    public boolean save(JDBConnection connection, String process_type, ProcessWrapperDTO dto) {
        boolean res = false;
        /**
         * Nota1: no es necesario usar el auto commit ya que si el flujo se
         * quedo en alguna parte(sin terminar), se puede terminar en los modulos
         * correspondientes
         *
         * Nota2: en caso de que el flujo no se vea interrumpido(retorne true)
         * en la capa superior debera arrojar un mensaje para lo opcion de
         * imprimir(los documentos correspondientes) por la cual la fase de
         * imprecion(date_print, employee_prin) es opcional
         */
        try {
            //[1]CAPRUTA DE DATOS DE USUARIO
            //[2]VALIDACION DE DATOS DE USUARIO
            /**
             * SE REGISTRAN LOS DATOS DEL USUARIO, LOS DOCUMENTOS DE IDENTIDAD Y
             * LOS REGISTROS NECESARIOS EN BITACORA
             */
            int process_id = user.save(connection, process_type, dto);
            res = process_id > 0;
            if (!res) {
                throw new SQLException("PROCESO DE INICIO Y VALIDACION CORRUPTO");
            }
            //[3]CAPTURA DE PAGO
            //[4]CAPTURA DE PAGO DESGLOSADO
            /**
             * SE REGISTRA EL PAGO TOTAL AL IGUAL QUE LOS CONCEPTOS DESGLOSADOS
             * DEL TRAMITE
             */
            res = payment.saveProcess(connection, process_type, dto.getWater_intake().getUser(), dto.getPayment(), dto.getPayment_concept_list());
            if (!res) {
                throw new SQLException("PROCESO DE PAGO CORRUPTO");
            }
            //[5]FINALIZACION DE UN TRAMITE
            //
            res = water_intake.save(connection, process_type, dto.getWater_intake());
            if (!res) {
                throw new SQLException("PROCESO DE FINALIZACION CORRUPTO");
            }
            //[5]OPCIONAL: IMPRECION DEL COMPROBANTE(INFORMACION FISICA DEL TRAMITE)
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.getLogger(OwnerRegisterProcessService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
    }

    public PaymentService getPayment() {
        return payment;
    }

    public UserService getUser() {
        return user;
    }

    public WaterIntakeService getWater_intake() {
        return water_intake;
    }

}
