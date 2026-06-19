
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.PaymentListDAO;
import jsoftware.com.jblue.model.dao.PaymentsDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.dto.PaymentListDTO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.dto.wrp.ShopCartWrapperDTO;
import jsoftware.com.jblue.model.exp.ProcessException;
import jsoftware.com.jblue.model.models.AbstractService;
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
public class PaymentService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final PaymentsDAO payment_dao;
    private final PaymentListDAO payment_list_dao;
    private final ProcessDAO process_dao;

    public PaymentService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        this.payment_dao = new PaymentsDAO(dev_flag, process_name);
        this.payment_list_dao = new PaymentListDAO(dev_flag, process_name);
        this.process_dao = new ProcessDAO(dev_flag, process_name);
    }

    public boolean saveProcess(JDBConnection connection, String process_id, ProcessWrapperDTO dto) throws SQLException {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            //REGISTRO DE LA CABEZERA DEL PAGO
            int key = savePayment(connection, dto.getPayment_header());
            if (key <= 0) {
                throw new SQLException("REGISTRO DE TOTAL CORRUPO");
            }

            //SE AÑADE EL PAGO
            for (PaymentListDTO i : dto.getPayment_details()) {
                i.getMap().put("payment", key);
            }
            //REGISTRO DE LOS CONCEPTOS DE PAGO
            res = savePaymentList(connection, dto.getPayment_details());
            if (!res) {
                throw new SQLException("REGISTRO DE CONCEPTOS CORRUPO");
            }

            //[3]ACTUALIZA EL STATUS DEL TRAMITE A PAGADO
            res = process_dao.payProcess(connection, dto.getPayment_header().getProcessId());
            if (!res) {
                throw new SQLException("ACTUALIZACION DE \"STATUS PAGADO\" CORRUPO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.ProcessHistoryDAO.getInstance().update(connection, "SE PAGO EL TRAMITE: %s DEL USUARIO: %s".formatted(
                    key,
                    dto.getUser().toString()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException | ProcessException e) {
            connection.rollBack();
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

    public boolean getPaymentMadeThisYear(JDBConnection connection, ShopCartWrapperDTO dto) {
        boolean res = false;
        return res;
    }
}
