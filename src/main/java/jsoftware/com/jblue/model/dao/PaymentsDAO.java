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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class PaymentsDAO extends AbstractDAO implements Serializable {

    public PaymentsDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public int insertServicePayment(JDBConnection connection, PaymentDTO dto) throws SQLException {
        dto.getMap().put("payment_type", 1);
        dto.getMap().put("payment_method", 1);
        return insert(connection, dto);
    }

    public int insertSurchargePayment(JDBConnection connection, PaymentDTO dto) throws SQLException {
        dto.getMap().put("payment_type", 2);
        dto.getMap().put("payment_method", 1);
        return insert(connection, dto);
    }

    public int insertOtherPayment(JDBConnection connection, PaymentDTO dto) throws SQLException {
        dto.getMap().put("payment_type", 3);
        dto.getMap().put("payment_method", 1);
        return insert(connection, dto);
    }

    public int insertProcessPayment(JDBConnection connection, PaymentDTO dto) throws SQLException {
        dto.getMap().put("payment_type", 4);
        dto.getMap().put("payment_method", 1);
        return insert(connection, dto);
    }

    public int insert(JDBConnection connection, PaymentDTO dto) throws SQLException {
        int payment_id = -1;
        String query = "";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, dto.getPaymentType());
            ps.setString(1, dto.getUDDI());
            ps.setString(1, dto.getEmployee());
            ps.setString(1, dto.getUser());
            ps.setString(1, dto.getWaterInatke());
            ps.setString(1, dto.getWaterIntakeType());
            ps.setInt(1, dto.getPaymentMethod());
            ps.setInt(1, dto.getPaymentConcept());
            ps.setString(1, dto.getTotalCost());
            ps.setString(1, dto.getAmountPaid());
            ps.setString(1, dto.getChangeAmount());
            ps.setInt(1, dto.getMonthsPaid());
            ps.setInt(1, dto.getStatus());
            int affected_row = ps.executeUpdate();
            if (affected_row == PreparedStatement.EXECUTE_FAILED || affected_row != 1) {
                throw new SQLException("ERROR AL INSERTAR EL PAGO");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("ERROR AL GENERAR LA LLAVE PRIMARIA DEL PAGO");
                }
                payment_id = rs.getInt(1);
                dto.getMap().put("id", payment_id);
                dto.getMap().put("date_update", Formats.getLocalDateTime(LocalDateTime.now()));
                dto.getMap().put("date_register", Formats.getLocalDateTime(LocalDateTime.now()));
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return payment_id;
    }
    
    public String UDDI(){
        return UUID.randomUUID().toString();
    }
}
