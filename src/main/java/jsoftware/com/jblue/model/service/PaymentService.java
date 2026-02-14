
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
     * Procesa y persiste la transacción integral de un pago en el sistema bajo
     * un modelo de
     * <b>Atomicidad (ACID)</b>.
     * <p>
     * Este método centraliza la orquestación de múltiples DAOs para garantizar
     * que el registro del pago, el desglose de conceptos y la actualización del
     * trámite ocurran como una única unidad de trabajo. Si falla un solo paso,
     * el sistema revierte todos los cambios.</p>
     * * <b>Flujo de ejecución:</b>
     * <ol>
     * <li>Desactivación de <code>autoCommit</code> para iniciar el bloque
     * transaccional.</li>
     * <li>Apertura de auditoría en bitácora (Inicio de transacción).</li>
     * <li>Persistencia del encabezado del pago mediante
     * {@link #savePayment}.</li>
     * <li>Cambio de estado del trámite administrativo a 'PAGADO'.</li>
     * <li>Cierre de auditoría y ejecución de <code>commit()</code>.</li>
     * </ol>
     * * <b>Gestión de Seguridad e Integridad:</b>
     * <ul>
     * <li>Cualquier anomalía dispara un <code>rollBack()</code>, invalidando
     * los registros parciales.</li>
     * <li>En el bloque <code>finally</code>, se restaura siempre el
     * <code>autoCommit</code> a <code>true</code> para no afectar futuras
     * operaciones del pool de conexiones.</li>
     * </ul>
     *
     *
     *
     * @param connection Objeto {@link JDBConnection} con la sesión activa.
     * @param procedure_id Identificador único (UUID/ID) del trámite que se está
     * liquidando.
     * @param user_name Nombre del operador/empleado que procesa el cobro (para
     * auditoría).
     * @param payment DTO con los datos maestros (Monto total, método de pago,
     * etc.).
     * @param list_items Lista detallada de los ítems que componen el total del
     * pago.
     * @return {@code true} si la transacción completa fue confirmada con éxito.
     * @throws SQLException Si ocurre un error de integridad, conexión o reglas
     * de negocio.
     * @throws Exception Captura errores inesperados de ejecución para asegurar
     * el rollback.
     */
    public boolean saveProcess(JDBConnection connection, String procedure_id, String user_name, PaymentDTO payment, List<PaymentListDTO> list_items) throws SQLException {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            //REGISTRO DE LA CABEZERA DEL PAGO
            int key = savePayment(connection, payment);
            if (key <= 0) {
                throw new SQLException("REGISTRO DE TOTAL CORRUPO");
            }
            
            //SE AÑADE EL PAGO
            for (PaymentListDTO i : list_items) {
                i.getMap().put("payment", key);
            }
            //REGISTRO DE LOS CONCEPTOS DE PAGO
            res = savePaymentList(connection, list_items);
            if (!res) {
                throw new SQLException("REGISTRO DE CONCEPTOS CORRUPO");
            }

            //[3]ACTUALIZA EL STATUS DEL TRAMITE A PAGADO
            res = process_dao.payProcess(connection, procedure_id);
            if (!res) {
                throw new SQLException("ACTUALIZACION DE \"STATUS PAGADO\" CORRUPO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.ProcessHistoryDAO.getInstance().update(connection, "SE PAGO EL TRAMITE: %s DEL USUARIO: %s".formatted(
                    key,
                    user_name
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
            throw e;
        } catch (Exception e) {
            connection.rollBack();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    /**
     * Registra la cabecera de un pago en el sistema y genera su entrada en la
     * bitácora.
     * <p>
     * <b>Nota de Transaccionalidad:</b> Este método NO gestiona el inicio o fin
     * de una transacción (AutoCommit). Se asume que el objeto
     * {@code JDBConnection} proporcionado es manejado por un servicio superior
     * que controla el {@code commit} o {@code rollback}.
     * </p>
     *
     * @param connection Objeto de conexión activa a la base de datos MySQL.
     * @param dto Objeto de transferencia de datos con la información del pago.
     * @return {@code int} El ID (Primary Key) generado para el pago registrado.
     * @throws SQLException Si ocurre un error en la inserción del pago o si la
     * actualización de la bitácora de historial falla (integridad de datos).
     */
    public int savePayment(JDBConnection connection, PaymentDTO dto) throws SQLException {
        boolean res;
        //[1]REGISTRO DEL PAGO(TOTAL)
        int key = payment_dao.insert(connection, dto);
        res = key > 0;
        if (!res) {
            throw new SQLException("NO SE PUDO OBTENER EL ID DEL PAGO");
        }
        //REGISTRO EN BITACORA
        res = HistoryDAO.PaymentHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL PAGO: %s - ".formatted(
                key
        ));
        if (!res) {
            throw new SQLException("REGISTRO DEL PAGO CORRUPTO");
        }
        return key;
    }

    /**
     * Realiza el registro masivo de los conceptos desglosados asociados a un
     * pago.
     * <p>
     * Este método procesa una lista de conceptos (detalles) y, tras una
     * inserción exitosa, genera un asiento en la bitácora registrando la
     * cantidad de ítems procesados.
     * </p>
     * * <b>Control de Integridad:</b>
     * <ul>
     * <li>Si la inserción de la lista falla, se lanza una excepción para forzar
     * el rollback en la capa de servicio.</li>
     * <li>La bitácora es obligatoria; si falla su registro, se considera una
     * operación corrupta.</li>
     * </ul>
     *
     * @param connection Objeto de conexión {@code JDBConnection} activa. Debe
     * tener el auto-commit desactivado si se usa en conjunto con savePayment.
     * @param list {@code ArrayList<PaymentListDTO>} conteniendo los conceptos,
     * montos y referencias del pago.
     * @return {@code true} si todos los conceptos y la bitácora se registraron
     * correctamente.
     * @throws SQLException Si ocurre un error de persistencia o si el historial
     * no puede ser actualizado.
     */
    public boolean savePaymentList(JDBConnection connection, List<PaymentListDTO> list) throws SQLException {
        boolean res = false;
        //[2]REGISTRO DE CONCEPTOS DE PAGO(PAGO DESGLOSADO)
        res = payment_list_dao.insert(connection, list);
        if (!res) {
            throw new SQLException("NO SE REGISTRARON LOS CONCEPTOS DE PAGO");
        }
        //REGISTRO EN BITACORA
        res = HistoryDAO.PaymentListHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL PAGO DE %s CONCEPTOS ".formatted(
                list.size()
        ));
        if (!res) {
            throw new SQLException("REGISTRO DE CONCEPTOS DE PAGO EN BITACORA CORRUPTOS");
        }
        return res;
    }
}
