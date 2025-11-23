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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Clase DAO (Data Access Object) responsable de gestionar la lógica de sesión y
 * consultar datos relacionados con el estado de la aplicación, como la vigencia
 * de sesiones y la administración activa.
 *
 * @author Juan Campos
 * @version 1.1
 * @since 22/11/2025
 */
public class SessionDAO {

    // Asumimos que la lógica de mapeo se ha refactorizado a un método estático
    // para ser reutilizable, o se mantiene aquí.
    /**
     * Valida si la sesión de un empleado ha caducado o está inactiva. La sesión
     * es considerada válida si el empleado tiene status 1 y su fecha de fin
     * (DateEnd) no ha llegado aún.
     *
     * @param o Objeto EmployeeDTO con la información de la sesión.
     * @return {@code true} si la sesión está expirada o el empleado no tiene
     * status 1; {@code false} si es válida.
     */
    public static boolean isSessionExpired(EmployeeDTO o) {

        // 1. Si no hay fecha de fin definida (o está activa indefinidamente)
        if (o.getDateEnd() == null) {
            return false;
        }

        // 2. Si el empleado no tiene status ACTIVO (asumimos 1)
        // La lógica original es confusa, se cambia a verificar si NO está activo
        if (o.getStatus() != 1) {
            return true;
        }

        // 3. Verifica si la fecha y hora actual es POSTERIOR a la fecha de fin de sesión.
        // Se invierte la lógica: esBefore() original -> isAfter() para determinar caducidad.
        return LocalDateTime.now().isAfter(o.getDateEnd());
    }

    // -------------------------------------------------------------------------
    /**
     * Obtiene el registro de administración actual (activo) para el año en
     * curso.
     *
     * * @param connection Conexión JDBC activa.
     * @return El DTO de la historia de administración activa o {@code null} si
     * no se encuentra.
     */
    public static HysAdministrationHistoryDTO getActiveAdministrationRegister(JDBConnection connection) {
        HysAdministrationHistoryDTO o = null;

        // Uso de try-with-resources para asegurar el cierre del ResultSet
        try (ResultSet rs = connection.query(getQuery())) {

            if (rs.next()) {
                o = mapResultSetToDTO(rs);
            }

        } catch (SQLException ex) {
            // Manejo de errores: Loggear y re-lanzar como excepción no chequeada (RuntimeException)
            System.getLogger(SessionDAO.class.getName()).log(
                    System.Logger.Level.ERROR,
                    "Error al consultar el registro de administración: ",
                    ex
            );
            throw new RuntimeException("Fallo en la consulta de administración activa.", ex);
        }
        return o;
    }

    /**
     * Genera la consulta SQL para obtener la administración vigente del año.
     *
     * * @return La sentencia SQL para la consulta.
     */
    private static String getQuery() {
        // Uso de texto de bloque para SQL multilínea y corrección de '!= NULL' a 'IS NOT NULL'
        return """
            SELECT * FROM hys_administration_history
            WHERE year_of_administration = YEAR(CURRENT_TIMESTAMP)
            AND root IS NOT NULL
            AND president IS NOT NULL
            AND treasurer IS NOT NULL
            AND secretary IS NOT NULL
            AND status = 1
            """;
    }

    /**
     * Mapea un ResultSet a un HysAdministrationHistoryDTO de forma dinámica
     * (Asume que HysAdministrationHistoryDTO se basa en AbstractMapDTO).
     *
     * * @param rs El ResultSet posicionado en una fila válida.
     * @return Un DTO con los valores mapeados.
     */
    private static HysAdministrationHistoryDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        HysAdministrationHistoryDTO dto = new HysAdministrationHistoryDTO();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnLabel(i);
            Object value = rs.getObject(i);
            // Asume que HysAdministrationHistoryDTO tiene un método getMap() o setValues() 
            // para almacenar valores. Aquí usamos un mapeo dinámico basado en Map.
            dto.getMap().put(columnName, value);
        }
        return dto;
    }
}
