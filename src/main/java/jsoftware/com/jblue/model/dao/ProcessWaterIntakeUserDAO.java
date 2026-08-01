/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import jsoftware.com.jblue.model.dto.ProcessWaterIntakeUserDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.CorruptUpdateException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class ProcessWaterIntakeUserDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public ProcessWaterIntakeUserDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public boolean insert(JDBConnection connection, ProcessWaterIntakeUserDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        boolean res = false;
        String query = """ 
                       INSERT INTO pro_water_intake_user
                       (process_id, sequence, process_type, status, last_employee_update) 
                       VALUES (?,?,?,?,?)
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getProcessId());
            ps.setString(2, dto.getSequence());
            ps.setString(3, dto.getProcessType());
            ps.setString(4, dto.getStatus());
            ps.setString(5, dto.getLastEmployeeUpdate());
            int affected_row = ps.executeUpdate();
            if (affected_row != 1) {
                throw new CorruptInsertionException();
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }

                // FLUJO JBLUE: Enriquecimiento dinámico del DTO tras el éxito de la operación
                dto.put("id", rs.getString(1));
                String now = Formats.getLocalDateTime(LocalDateTime.now());
                dto.put("date_update", now);
                dto.put("date_register", now);
            }
        }
        return res;
    }

    public boolean userRegister(JDBConnection connection, ProcessWaterIntakeUserDTO dto) throws SQLException, CorruptUpdateException {
        boolean res = false;
        String query = """ 
                       UPDATE pro_water_intake_user SET
                            user_id = ?, 
                            status = ?,
                            last_employee_update = ?
                       WHERE sequence = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getUserId());
            ps.setString(2, dto.getStatus());
            ps.setString(3, dto.getLastEmployeeUpdate());
            ps.setString(4, dto.getSequence());
            int affected_row = ps.executeUpdate();
            if (affected_row != 1) {
                throw new CorruptUpdateException("FOLIO : %s CORRUPTO".formatted(dto.getSequence()));
            }
            res = true;
        }
        return res;
    }

    public boolean documentRegister(JDBConnection connection, ProcessWaterIntakeUserDTO dto) throws SQLException, CorruptUpdateException {
        boolean res = false;
        String query = """ 
                       UPDATE pro_water_intake_user SET
                            document_id = ?, 
                            status = ?,
                            last_employee_update = ?
                       WHERE sequence = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getDocumentId());
            ps.setString(2, dto.getStatus());
            ps.setString(3, dto.getLastEmployeeUpdate());
            ps.setString(4, dto.getSequence());
            int affected_row = ps.executeUpdate();
            if (affected_row != 1) {
                throw new CorruptUpdateException("FOLIO : %s CORRUPTO".formatted(dto.getSequence()));
            }
            res = true;
        }
        return res;
    }

    public boolean wkiUserRegister(JDBConnection connection, ProcessWaterIntakeUserDTO dto) throws SQLException, CorruptUpdateException {
        boolean res = false;
        String query = """ 
                       UPDATE pro_water_intake_user SET
                            water_intake_user_id = ?, 
                            status = ?,
                            last_employee_update = ?
                       WHERE sequence = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getWaterIntakeUserId());
            ps.setString(2, dto.getStatus());
            ps.setString(3, dto.getLastEmployeeUpdate());
            ps.setString(4, dto.getSequence());
            int affected_row = ps.executeUpdate();
            if (affected_row != 1) {
                throw new CorruptUpdateException("FOLIO : %s CORRUPTO".formatted(dto.getSequence()));
            }
            res = true;
        }
        return res;
    }

    public boolean paymentRegister(JDBConnection connection, ProcessWaterIntakeUserDTO dto) throws SQLException, CorruptUpdateException {
        boolean res = false;
        String query = """ 
                       UPDATE pro_water_intake_user SET
                            payment_id = ?, 
                            status = ?,
                            last_employee_update = ?
                       WHERE sequence = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getPaymentId());
            ps.setString(2, dto.getStatus());
            ps.setString(3, dto.getLastEmployeeUpdate());
            ps.setString(4, dto.getSequence());
            int affected_row = ps.executeUpdate();
            if (affected_row != 1) {
                throw new CorruptUpdateException("FOLIO : %s CORRUPTO".formatted(dto.getSequence()));
            }
            res = true;
        }
        return res;
    }

}
