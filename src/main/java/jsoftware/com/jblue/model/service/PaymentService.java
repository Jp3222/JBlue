
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.util.UUID;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jpaymentlib.model.dao.PaymentDetailDAO;
import jsoftware.com.jpaymentlib.model.dao.PaymentHeaderDAO;
import jsoftware.com.jpaymentlib.model.dto.PaymentDetailDTO;
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

    private final PaymentHeaderDAO payment_dao;
    private final PaymentDetailDAO payment_list_dao;
    private final ProcessDAO process_dao;

    public PaymentService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        this.payment_dao = new PaymentHeaderDAO(dev_flag, process_name);
        this.payment_list_dao = new PaymentDetailDAO(dev_flag, process_name);
        this.process_dao = new ProcessDAO(dev_flag, process_name);
    }

    /**
     * FUNCION QUE REGISTRA EL PAGO Y EL DESGLOSE DEL MISMO
     *
     * @param connection
     * @param dto
     * @return
     * @throws SQLException
     */
    public boolean saveProcess(JDBConnection connection, ProcessWrapperDTO dto) throws SQLException {
        boolean res = false;

        //[1]REGISTRO DEL PAGO(TOTAL)
        res = payment_dao.insert(connection, dto.getPayment_header());
        if (!res) {
            throw new SQLException("NO SE REGISTRO EL PAGO");
        }

        //REGISTRO EN BITACORA
        res = HistoryDAO.PaymentHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL PAGO: %s - ".formatted(
                dto.getId()
        ));
        if (!res) {
            throw new SQLException("REGISTRO DEL PAGO CORRUPTO");
        }

        //SE AÑADE EL PAGO
        for (PaymentDetailDTO i : dto.getPayment_details()) {
            // DIFERENTE DEL PAGO Y DE CADA UNO PARA RASTREAR CADA CONCEPTO POR APARTE
            String uuid = UUID.randomUUID().toString();
            i.getMap().put("payment_header_id", i.getPaymentHeaderId());
            i.getMap().put("sequence", i.getSequence());
            i.getMap().put("uuid", uuid);
        }
        //[2]REGISTRO DE CONCEPTOS DE PAGO(PAGO DESGLOSADO)
        res = payment_list_dao.insert(connection, dto.getPayment_details());
        if (!res) {
            throw new SQLException("NO SE REGISTRARON LOS CONCEPTOS DE PAGO");
        }
        //REGISTRO EN BITACORA
        res = HistoryDAO.PaymentListHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL PAGO DE %s CONCEPTOS CON SECUENCIA: %s".formatted(
                dto.getPayment_details().size(),
                //LA SECUENCIA ES LA MISMA PARA TODOS
                dto.getPayment_header().getSequence()
        ));
        if (!res) {
            throw new SQLException("REGISTRO DE CONCEPTOS DE PAGO EN BITACORA CORRUPTOS");
        }

        return res;
    }

}
