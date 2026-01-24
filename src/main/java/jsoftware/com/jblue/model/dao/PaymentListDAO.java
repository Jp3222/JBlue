/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.dto.PaymentListDTO;
import jsoftware.com.jblue.model.querys.PaymentListQuery;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class PaymentListDAO extends AbstractDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public PaymentListDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public boolean insertPaymentList(JDBConnection connection, int payment_id, List<String> items, BigDecimal cost, int status) {
        // 1. Inicializa el resultado como false
        boolean res = false;
        // Se asume que PaymentListQuery.PAYMENT_LIST_QUERY es un INSERT statement
        String query = PaymentListQuery.PAYMENT_LIST_QUERY;
        // Usamos try-with-resources para cerrar PreparedStatement automáticamente
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            // 2. Llenar el lote (batch)
            for (String item_id : items) {
                // Asumiendo que el INSERT tiene 4 placeholders: (payment_id, item_id, cost, status)
                ps.setInt(1, payment_id);
                ps.setString(2, item_id);
                ps.setBigDecimal(3, cost);      // Nota: Considerar usar un tipo numérico (e.g., BigDecimal)
                ps.setInt(4, status);    // Nota: Considerar usar int
                ps.addBatch();
            }
            // 3. Ejecutar el lote y obtener los conteos de filas actualizadas
            int[] updateCounts = ps.executeBatch();
            // 4. Verificar si todas las inserciones fueron exitosas
            boolean allSuccessful = true;
            for (int count : updateCounts) {
                // Un conteo >= 1 significa éxito, Statement.SUCCESS_NO_INFO significa que no hay info del conteo
                if (count == PreparedStatement.EXECUTE_FAILED) {
                    allSuccessful = false;
                    break;
                }
            }
            // 5. Actualizar el resultado si todo fue exitoso
            if (allSuccessful) {
                res = true;
            }
            // 6. Manejo de excepciones: Imprimir stack trace para diagnóstico
        } catch (Exception e) {
            System.err.println("Error al ejecutar el lote de inserción de pagos: " + e.getMessage());
            e.printStackTrace();
            // res se mantiene como false
        }
        return res;
    }

    public boolean insert(JDBConnection connection, List<PaymentListDTO> list) {
        boolean res = false;
        String query = "INSERT INTO pym_payment_list(payment, payment_concept, item_name, cost, status) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            // 1. Iniciamos la transacción manual
            connection.setAutoCommit(false);
            for (PaymentListDTO i : list) {
                ps.setString(1, i.getPayment());
                ps.setString(2, i.getPaymentConcept());
                ps.setString(3, i.getItemName());
                ps.setString(4, i.getCost());
                ps.setString(5, i.getStatus());
                ps.addBatch();
            }
            // 2. Ejecutamos el lote
            int[] results = ps.executeBatch();
            // 3. Validamos que el tamaño del resultado coincida con el de la lista enviada
            // Si ps.executeBatch() no lanza excepción, es que el proceso terminó.
            res = results.length == list.size();
            if (!res) {
                throw new SQLException("REGISTRO DE CONCEPTOS PAGO CORRUPTO");
            }
            connection.commit(); // Confirmamos solo si el lote está completo
        } catch (SQLException e) {
            connection.rollBack();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

}
