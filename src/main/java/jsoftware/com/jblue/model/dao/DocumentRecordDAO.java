package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import jsoftware.com.jblue.model.dto.DocumentRecordDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * DAO encargado de la persistencia y operaciones de datos en la tabla para el
 * control y auditoría de registros documentales.
 * <br><br>
 * <strong>Estándar JBlue:</strong> Realiza el desempaque manual del mapa del
 * DTO, aplicando casting controlado a tipos nativos de la base de datos
 * (MySQL/SQLite) y resolviendo de forma defensiva los estados opcionales
 * (NULL).
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-14
 * @version 1.0
 */
public class DocumentRecordDAO {

    /**
     * Inserta un nuevo registro de control documental de forma atómica.
     * <br>
     * Diseñado para participar dentro de la transacción global del Service
     * compartiendo la misma conexión (autoCommit = false).
     *
     * @param connection Conexión activa y segura bajo la transacción actual.
     * @param dto DTO conteniendo los valores transportados desde la
     * Vista/Controlador.
     * @return true si la inserción física fue exitosa y afectó exactamente a
     * una fila.
     * @throws SQLException Si ocurre un error de sintaxis, constrains o
     * desconexión en el motor.
     * @throws CorruptInsertionException Si la ejecución altera 0 o múltiples
     * filas de forma anómala.
     */
    public int insert(JDBConnection connection, DocumentRecordDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        boolean success = false;
        // El ID no se incluye en el INSERT ya que está definido como PRIMARY KEY AUTOINCREMENT
        String INSERT = "INSERT INTO doc_document_record ("
                + "user_id, process_id, sequence, observation, "
                + "document_count, document_start, document_end, status, "
                + "employee_register_id, employee_valid_id, date_update, date_register, date_end"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.getNewPreparedStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // 1. LLAVES DE ASOCIACIÓN OPERATIVA
            ps.setString(1, dto.getUserId());
            ps.setInt(2, Integer.parseInt(dto.getProcessId()));
            ps.setInt(3, Integer.parseInt(dto.getSequence()));

            // 2. TEXTOS OPOCIONALES / AUDITORÍA (Manejo defensivo de NULL)
            String observationStr = dto.getObservation();
            if (observationStr == null || observationStr.trim().isEmpty() || observationStr.equalsIgnoreCase("N/A")) {
                ps.setNull(4, Types.VARCHAR);
            } else {
                ps.setString(4, observationStr);
            }

            // 3. CONTADORES Y PUNTEROS DE RANGOS DOCUMENTALES
            ps.setInt(5, Integer.parseInt(dto.getDocumentCount()));
            ps.setInt(6, Integer.parseInt(dto.getDocumentStart()));
            ps.setInt(7, Integer.parseInt(dto.getDocumentEnd()));
            ps.setInt(8, Integer.parseInt(dto.getStatus()));

            // 4. CONTROL DE EMPLEADOS (Creador obligado, Validador opcional)
            ps.setInt(9, Integer.parseInt(dto.getEmployeeRegisterId()));

            String empValidStr = dto.getEmployeeValidId();
            if (empValidStr == null || empValidStr.trim().isEmpty() || empValidStr.equalsIgnoreCase("N/A")) {
                ps.setNull(10, Types.INTEGER);
            } else {
                ps.setInt(10, Integer.parseInt(empValidStr));
            }

            // 5. MARCAS DE TIEMPO / TIMESTAMPS
            // date_update y date_register (Si vienen vacíos, dejamos que el motor aplique CURRENT_TIMESTAMP)
            String dateUpdateStr = dto.getDateUpdate();
            if (dateUpdateStr == null || dateUpdateStr.trim().isEmpty()) {
                ps.setNull(11, Types.TIMESTAMP);
            } else {
                ps.setString(11, dateUpdateStr);
            }

            String dateRegisterStr = dto.getDateRegister();
            if (dateRegisterStr == null || dateRegisterStr.trim().isEmpty()) {
                ps.setNull(12, Types.TIMESTAMP);
            } else {
                ps.setString(12, dateRegisterStr);
            }

            // date_end es la fecha de finalización o cierre del trámite (Nace nullable por defecto)
            String dateEndStr = dto.getDateEnd();
            if (dateEndStr == null || dateEndStr.trim().isEmpty() || dateEndStr.equalsIgnoreCase("N/A")) {
                ps.setNull(13, Types.TIMESTAMP);
            } else {
                ps.setString(13, dateEndStr);
            }

            // 6. EJECUCIÓN TRANSACCIONAL DEFINITIVA
            int rowsAffected = ps.executeUpdate();
            success = rowsAffected == 1;
            if (!success) {
                throw new CorruptInsertionException();
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                throw new KeyNotGenerateException();
            }
            dto.put("id", rs.getString("id"));
            return Integer.parseInt(dto.getId());
        }
    }
}
