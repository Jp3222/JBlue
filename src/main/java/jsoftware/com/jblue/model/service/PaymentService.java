
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.PaymentListDAO;
import jsoftware.com.jblue.model.dao.PaymentsDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.dto.PaymentListDTO;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Clase de capa de servicio que implementa la lógica de negocio para el
 * procesamiento de pagos de manera transaccional dentro del sistema JBlue.
 * * <p>
 * Esta clase orquestra la interacción entre múltiples DAOs (Payments,
 * PaymentList, Process y History) para asegurar que el registro de un pago, su
 * desglose de conceptos, la actualización del estatus del trámite y su
 * correspondiente bitácora de auditoría ocurran como una única unidad de
 * trabajo (atómica).</p>
 * * <p>
 * <strong>Principios de diseño aplicados:</strong></p>
 * <ul>
 * <li><b>Transaccionalidad:</b> Uso de {@code setAutoCommit(false)} y
 * {@code rollBack()} para garantizar la integridad de los datos ante fallos en
 * cualquier etapa del proceso.</li>
 * <li><b>Auditoría Integral:</b> Registro automatizado en la tabla de historial
 * mediante el uso de wrappers especializados en {@link HistoryDAO}.</li>
 * <li><b>Inyección de Dependencias:</b> Soporte para inicialización mediante
 * DAOs externos o instanciación interna según el entorno.</li>
 * </ul>
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @version 1.0
 * @since 2026-01-17
 */
public class PaymentService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final PaymentsDAO payment_dao;
    private final PaymentListDAO payment_list_dao;
    private final ProcessDAO process_dao;

    public PaymentService(PaymentsDAO payment_dao, PaymentListDAO payment_list_dao, ProcessDAO process_dao) {
        this.payment_dao = payment_dao;
        this.payment_list_dao = payment_list_dao;
        this.process_dao = process_dao;
    }

    public PaymentService(boolean flag_dev, String name_module) {
        this.payment_dao = new PaymentsDAO(flag_dev, name_module);
        this.payment_list_dao = new PaymentListDAO(flag_dev, name_module);
        this.process_dao = new ProcessDAO(flag_dev, name_module);
    }

    /**
     * Procesa y persiste la transacción integral de un pago en el sistema.
     * <p>
     * Este método implementa un <b>patrón de transacción atómica</b>. Realiza
     * las siguientes operaciones en bloque:</p>
     * <ol>
     * <li>Inserta el encabezado del pago (monto total).</li>
     * <li>Registra la auditoría del pago en la bitácora.</li>
     * <li>Inserta el desglose detallado de los conceptos pagados.</li>
     * <li>Registra la auditoría del desglose en la bitácora.</li>
     * <li>Actualiza el estado del trámite/proceso a 'PAGADO'.</li>
     * <li>Registra la auditoría del cambio de estado del proceso.</li>
     * </ol>
     * <p>
     * <b>Gestión de Errores:</b> Si cualquiera de las inserciones falla o
     * devuelve un estado negativo, se lanza una {@link SQLException} que
     * dispara un <code>rollBack()</code> automático, asegurando que la base de
     * datos no quede en un estado inconsistente (registros parciales).</p>
     *
     *
     * @param connection Objeto {@link JDBConnection} con la conexión activa a
     * la base de datos.
     * @param procedure_id Identificador único del trámite relacionado con el
     * pago.
     * @param user_name Nombre del usuario que efectúa la operación para
     * registro de auditoría.
     * @param payment Objeto {@link PaymentDTO} que contiene los datos maestros
     * del pago.
     * @param list_items Colección de {@link PaymentListDTO} con el desglose de
     * los ítems liquidados.
     * @return {@code true} si todas las fases de la transacción se completaron
     * y confirmaron (commit) exitosamente; {@code false} en caso de error o
     * rollback.
     */
    public boolean save(JDBConnection connection, String procedure_id, String user_name, PaymentDTO payment, List<PaymentListDTO> list_items) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            //[1]REGISTRO DEL PAGO(TOTAL)
            int key = payment_dao.insert(connection, payment);
            res = key > 0;
            if (!res) {
                throw new SQLException("REGISTRO DE PAGO ERRONEO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.PaymentHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL PAGO: %s - ".formatted(
                    key
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //[2]REGISTRO DE CONCEPTOS DE PAGO(PAGO DESGLOSADO)
            res = payment_list_dao.insert(connection, list_items);
            if (!res) {
                throw new SQLException("REGISTRO DE PAGO DE CONCEPTOS ERRONEO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.PaymentListHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL PAGO DE %s CONCEPTOS ".formatted(
                    list_items.size()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //[3]ACTUALIZA EL STATUS DEL TRAMITE A PAGADO
            res = process_dao.payProcess(connection, procedure_id);
            if (!res) {
                throw new SQLException("REGISTRO DEL PROCESO CORRUPTO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.ProcessHistoryDAO.getInstance().update(connection, "SE PAGO EL TRAMITE: %s DEL USUARIO: %s".formatted(
                    key,
                    user_name
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //si no hubo error alguno se confirma la transaccion 
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
            e.printStackTrace();
        } catch (Exception e) {
            connection.rollBack();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
