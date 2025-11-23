/*
 * Copyright (C) 2025 juanp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jsoftware.com.jblue.model.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.OWaterIntakes;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.querys.PaymentQuerys;
import jsoftware.com.jblue.model.scripts.PaymentsQuerys;
import jsoftware.com.jblue.sys.SystemLogs;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.util.Fecha;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.jexception.JExcp;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.sys.LaunchApp;

/**
 *
 * @author juanp
 */
public class PaymentsDAO extends AbstractDAO implements Serializable {

    public static final long serialVersionUID = 1L;

    public JDBConnection connection = (JDBConnection) LaunchApp.getInstance().getResources("connection");
    private final EmployeeDTO current_employee;

    public PaymentsDAO(String name_module) {
        this(AppConfig.isLogsDev(), name_module);

    }

    public PaymentsDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
        current_employee = SystemSession.getInstancia().getCurrentEmployee();
    }

    /**
     * total_cost, amount_paid, change_amount, month_paid, status
     *
     * @param connection
     * @param month_paid
     * @return
     */
    public int insertServicePayment(JDBConnection connection,
            String uudi,
            OWaterIntakes wk,
            BigDecimal total_cost,
            BigDecimal amount_paid,
            BigDecimal change_amount,
            int months_paid_date,
            int type_payment_id,
            int status_id) {
        return insertPayment(connection, uudi, wk, 1, total_cost, amount_paid, change_amount, months_paid_date, 1, status_id);
    }

    public int insertSurchargePayment(JDBConnection connection,
            String uudi,
            OWaterIntakes wk,
            BigDecimal total_cost,
            BigDecimal amount_paid,
            BigDecimal change_amount,
            int months_paid_date,
            int type_payment_id,
            int status_id) {
        return insertPayment(connection, uudi, wk, 1, total_cost, amount_paid, change_amount, months_paid_date, 2, status_id);
    }

    public int insertOtherPayment(JDBConnection connection,
            String uudi,
            OWaterIntakes wk,
            int payment_concept_id,
            BigDecimal total_cost,
            BigDecimal amount_paid,
            BigDecimal change_amount,
            int months_paid_date,
            int type_payment_id,
            int status_id) {
        return insertPayment(connection, uudi, wk, payment_concept_id, total_cost, amount_paid, change_amount, months_paid_date, 3, status_id);
    }

// Asumimos que JDBConnection, OWaterIntakes, JExcp existen.
    /**
     * Inserta un registro en pym_payments y recupera el ID generado. NOTA: La
     * conexión debe tener auto-commit deshabilitado externamente.
     *
     * * @return El ID generado del pago insertado (o 0 si falla).
     */
    public int insertPayment(
            JDBConnection connection,
            String uudi,
            OWaterIntakes wk,
            int payment_concept_id,
            BigDecimal total_cost,
            BigDecimal amount_paid,
            BigDecimal change_amount,
            int months_paid_date,
            int type_payment_id,
            int status_id) {
        int res = 0;
        String query = PaymentQuerys.PAYMENT_INSERT; // Se espera: INSERT INTO pym_payments (...) VALUES (?, ?, ..., ?)

        // El setAutoCommit(false) debe hacerse ANTES del conjunto de transacciones. 
        // Lo he eliminado del método.
        try (PreparedStatement ps = connection.getConnection().prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS // Solicita las claves autogeneradas
        )) {
            // 1. Asignación de parámetros con tipado correcto
            ps.setInt(1, type_payment_id);
            ps.setString(2, uudi);
            ps.setString(3, current_employee.getId()); // Asumiendo que getId() retorna el ID del empleado como String
            ps.setString(4, wk.getUser());
            ps.setString(5, wk.getId());
            ps.setString(6, wk.getWaterIntakeType());
            ps.setInt(7, 1); // payment_method: 1 = EFECTIVO
            ps.setInt(8, payment_concept_id);
            ps.setBigDecimal(9, total_cost);
            ps.setBigDecimal(10, amount_paid);
            ps.setBigDecimal(11, change_amount);
            ps.setInt(12, months_paid_date); // Usar setDate(12, new java.sql.Date(long)) si es tipo Date
            ps.setInt(13, status_id);

            // 2. Ejecutar la sentencia INSERT
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                // 3. Recuperar el ID auto-generado
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        res = rs.getInt(1); // El ID es la primera columna del ResultSet
                    }
                }
            }

            if (res <= 0) {
                // Si la inserción fue exitosa pero no se recuperó el ID (posiblemente la DB no soporta getGeneratedKeys)
                throw new SQLException("Inserción exitosa (" + filasAfectadas + " filas) pero ID no generado/recuperado.");
            }

        } catch (SQLException e) {
            // En un flujo de transacción, la lógica aquí debería incluir un ROLLBACK
            // Ejemplo de ROLLBACK: connection.getConnection().rollback(); 
            // Uso del logger JExcp proporcionado por el código original
            JExcp.getInstance(false, flag_dev_log).print(e, getClass(), "insertPayment");
            res = 0; // Asegurar que el método retorna 0 en caso de error
        }
        return res;
    }

    // Constante para el estado de borrado lógico (STATUS = 3)
    private static final int STATUS_DELETED = 3;

    /**
     * Realiza un borrado lógico de un registro de pago, actualizando el estado
     * a {@code STATUS_DELETED} (3) y registrando la fecha de fin.
     *
     * @param connection La conexión JDBC activa.
     * @param user El ID del pago a eliminar lógicamente.
     * @return true si se borro logicamente
     * encontró).
     * @throws RuntimeException Si ocurre un error de SQL durante la ejecución.
     */
    public boolean deleteWC(JDBConnection connection, int user) {
        boolean res = false;
        // Establece el status a 3 y la fecha de finalización a la hora actual.
        String query = "UPDATE pym_payments SET status = ?, date_end = CURRENT_TIMESTAMP WHERE user = ?";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, STATUS_DELETED);
            ps.setInt(2, user);
            // Ejecuta la actualización y devuelve el número de filas modificadas.
            res = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<String[]> getUsersNotPayed() {
        ArrayList<String[]> list = new ArrayList<>((int) CacheFactory.USERS.count());
        try {
            LocalDate ly = LocalDate.now();
            ResultSet rs = connection.query(PaymentsQuerys.pay_of_day.formatted(Fecha.MESES[ly.getMonthValue() - 1]));
            String[] arr = new String[7];
            while (rs.next()) {
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = rs.getString(i + 1);
                }
                list.add(arr.clone());
            }
        } catch (SQLException e) {
            SystemLogs.severeDbLogs("ERROR: USUARIOS NO PAGADOS", e);
        }
        return list;
    }

    /*
     * public static List<OPagosRecargos> getSurcharges() {
     *
     * }
     */
    public boolean isThisMonthPay() {
        String query = "SELECT id FROM service_payments WHERE month IN(%s) AND YEAR(date_register) = YEAR(CUERRENT_TIMESTAMP)";
        boolean ret = false;
        try (ResultSet rs = connection.query(query.formatted(LocalDate.now().getMonthValue()))) {
            ret = rs.next();
        } catch (SQLException e) {
            SystemLogs.severeDbLogs("ERROR: USUARIOS NO PAGADOS", e);
        }
        return ret;
    }

    public String UUID() {
        UUID o = UUID.randomUUID();
        return o.toString();
    }

    public List<PaymentDTO> getServicePayment(JDBConnection connection) {
        return getPaymentList(connection, "1");
    }

    public List<PaymentDTO> getOtherPayment(JDBConnection connection) {
        return getPaymentList(connection, "2");
    }

    public List<PaymentDTO> getSurchargePayment(JDBConnection connection) {
        return getPaymentList(connection, "3");
    }

    /**
     * Obtiene una lista de DTOs de pago filtrada por el tipo de pago. Esta
     * lógica asume que PaymentDTO utiliza un mapa interno (AbstractMapDTO).
     */
    private List<PaymentDTO> getPaymentList(JDBConnection connection, String type_payment) {
        // Inicializa la lista con un tamaño prudente o vacío
        List<PaymentDTO> res = new ArrayList<>();
        String query = "SELECT * FROM pym_payments WHERE type_payment = ?";

        // Uso de try-with-resources para el PreparedStatement
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, type_payment);
            // Uso de try-with-resources para el ResultSet (anidado)
            try (ResultSet rs = ps.executeQuery()) {
                // Obtener metadatos una sola vez, si es necesario para el mapeo dinámico
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                // 1. Iteración y Mapeo
                while (rs.next()) {
                    PaymentDTO payment = new PaymentDTO();

                    // Mapeo dinámico: similar a la función mapResultSetToDTO
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnLabel(i); // Obtiene el nombre/alias
                        Object value = rs.getObject(i);

                        // Asume que PaymentDTO tiene un método getMap() o set() accesible
                        // Se usa la sintaxis de mapa interno que tenía la clase original
                        payment.put(columnName, value);
                    }
                    res.add(payment);
                }
            } // El ResultSet se cierra aquí
            // 2. Manejo de Excepciones Mejorado (NO TRAGAR EXCEPCIONES)
        } catch (SQLException e) {
            // Loggea la excepción detalladamente para el diagnóstico
            System.err.println("Error al consultar la lista de pagos para el tipo: " + type_payment);
            e.printStackTrace();

            // Es mejor re-lanzar la excepción envuelta en una RuntimeException
            // o una excepción de capa de persistencia (ej. DataAccessException)
            throw new RuntimeException("Fallo en la consulta de pagos", e);
        }
        // El PreparedStatement se cierra aquí

        // 3. Retorno del resultado (corregido)
        return res;
    }

}
