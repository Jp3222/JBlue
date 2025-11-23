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
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Clase DAO (Data Access Object) responsable de consultar y gestionar el
 * registro histórico de la administración activa (hys_administration_history).
 *
 * @author Juan Campos
 * @version 1.0
 * @since 22/11/2025
 */
public class AdministrationHistoryDAO {

    /**
     * Índice para el campo 'administrator' en EMPLOYEE_REGISTER.
     */
    public static final int ADMINISTRATOR = 0;
    /**
     * Índice para el campo 'president' en EMPLOYEE_REGISTER.
     */
    public static final int PRESIDENT = 1;
    /**
     * Índice para el campo 'treasurer' en EMPLOYEE_REGISTER.
     */
    public static final int TREASURER = 2;

    /**
     * Nombres de los campos en la tabla hys_administration_history.
     */
    public static final String[] EMPLOYEE_REGISTER = {
        "administrator", "president", "treasurer"
    };

    // -------------------------------------------------------------------------
    // MÉTODOS DE VALIDACIÓN
    // -------------------------------------------------------------------------
    /**
     * Valida si el campo del Presidente está registrado en la administración
     * activa.
     *
     * @param connection La conexión JDBC activa.
     * @return {@code true} si el campo 'president' no es nulo en el registro
     * activo.
     */
    public static boolean isPresidentRegistered(JDBConnection connection) {
        return isEmployeeRegistered(connection, PRESIDENT);
    }

    /**
     * Valida si el campo del Tesorero está registrado en la administración
     * activa.
     *
     * @param connection La conexión JDBC activa.
     * @return {@code true} si el campo 'treasurer' no es nulo en el registro
     * activo.
     */
    public static boolean isTreasurerRegistered(JDBConnection connection) {
        return isEmployeeRegistered(connection, TREASURER);
    }

    /**
     * Valida si el campo del Administrador está registrado en la administración
     * activa.
     *
     * @param connection La conexión JDBC activa.
     * @return {@code true} si el campo 'administrator' no es nulo en el
     * registro activo.
     */
    public static boolean isAdministratorRegistered(JDBConnection connection) {
        return isEmployeeRegistered(connection, ADMINISTRATOR);
    }

    /**
     * Verifica si un empleado con un rol específico tiene su campo de ID
     * llenado en el registro de administración activa del año actual.
     *
     * * @param connection La conexión JDBC activa.
     * @param employee El índice del rol ({@code ADMINISTRATOR},
     * {@code PRESIDENT}, etc.).
     * @return {@code true} si se encuentra un registro donde el campo del rol
     * no es nulo.
     */
    private static boolean isEmployeeRegistered(JDBConnection connection, int employee) {
        // Inicializar a false es redundante si se devuelve dentro del try/catch
        String query = getQueryEmployeeRegisterInAdmin(employee);

        // Uso de try-with-resources para asegurar el cierre del ResultSet
        try (ResultSet rs = connection.query(query)) {
            // No es necesario guardar en una variable intermedia, el resultado es directo
            return rs.next();
        } catch (SQLException ex) {
            System.getLogger(AdministrationHistoryDAO.class.getName()).log(
                    System.Logger.Level.ERROR,
                    "Error al verificar registro de empleado en la administración.",
                    ex
            );
            // Re-lanzar para notificar la falla de persistencia a la capa superior
            throw new RuntimeException("Fallo en la persistencia durante la validación.", ex);
        }
    }
// -------------------------------------------------------------------------
// MÉTODOS DE CONSULTA
// -------------------------------------------------------------------------

    /**
     * Obtiene el registro de administración activa para el año en curso.
     * <p>
     * Este método ejecuta la consulta SQL que busca un registro activo para el
     * año actual sin aplicar filtros por empleados específicos.
     * </p>
     *
     * @param connection La conexión JDBC activa.
     * @return El DTO de la historia de administración actual
     * ({@code HysAdministrationHistoryDTO}) o {@code null} si no se encuentra
     * un registro activo.
     * @throws RuntimeException Si ocurre un error de SQL durante la consulta.
     */
    public HysAdministrationHistoryDTO getCurrentAdministration(JDBConnection connection) {
        // -1 en getQueryEmployeeRegisterInAdmin indica que no se debe filtrar por empleado.
        // getQueryEmployeeRegisterInAdmin() se asume que es un método estático de esta clase.
        String query = getQueryEmployeeRegisterInAdmin(-1);
        HysAdministrationHistoryDTO dto = null;

        // Uso de try-with-resources para asegurar que el ResultSet se cierre.
        try (ResultSet rs = connection.query(query)) {

            if (rs.next()) {
                // Se utiliza el método auxiliar estático para el mapeo.
                dto = mapResultSetToDTO(rs);
            }
        } catch (SQLException ex) {
            System.getLogger(AdministrationHistoryDAO.class.getName()).log(
                    System.Logger.Level.ERROR,
                    "Error al obtener el registro de administración actual.",
                    ex
            );
            // Re-lanza la excepción envuelta para ser manejada en la capa de negocio.
            throw new RuntimeException("Fallo en la consulta de administración actual.", ex);
        }
        return dto;
    }

    /**
     * Mapea un ResultSet a un HysAdministrationHistoryDTO de forma dinámica.
     * <p>
     * Este método utiliza los metadatos del ResultSet para iterar sobre todas
     * las columnas del resultado y asignarlas al mapa interno del DTO. Esto
     * asume que {@code HysAdministrationHistoryDTO} implementa la lógica de un
     * DTO basado en mapa (como en {@code AbstractMapDTO}).
     * </p>
     *
     * @param rs El ResultSet posicionado en una fila válida (ya ejecutado).
     * @return Un DTO ({@code HysAdministrationHistoryDTO}) con los valores
     * mapeados.
     * @throws SQLException Si ocurre un error al acceder a los datos del
     * ResultSet (ej. columna no existe).
     */
    private static HysAdministrationHistoryDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        HysAdministrationHistoryDTO dto = new HysAdministrationHistoryDTO();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnLabel(i); // Nombre de la columna (o alias)
            Object value = rs.getObject(i);

            // Asume que dto.getMap() devuelve el mapa interno para inyección de valores.
            dto.getMap().put(columnName, value);
        }
        return dto;
    }

    // -------------------------------------------------------------------------
    // GENERACIÓN DE CONSULTAS Y MANTENIMIENTO
    // -------------------------------------------------------------------------
    /**
     * Genera la consulta SQL para verificar la existencia del registro de
     * administración del año actual.
     *
     * @param index_field El índice del campo de empleado a verificar, o -1 para
     * la consulta base.
     * @return La sentencia SQL generada.
     */
    public static String getQueryEmployeeRegisterInAdmin(int index_field) {
        StringBuilder sb = new StringBuilder(255);
        sb.append("SELECT * FROM hys_administration_history \n");
        sb.append("WHERE year_of_administration = YEAR(CURRENT_TIMESTAMP) \n");
        sb.append("AND status = 1 \n"); // Solo registros ACTIVO

        if (index_field == -1) {
            // Consulta base para obtener el registro completo
            return sb.toString();
        }

        // Corrección Crítica: != NULL debe ser IS NOT NULL en SQL
        sb.append("AND ");
        sb.append(EMPLOYEE_REGISTER[index_field]);
        sb.append(" IS NOT NULL"); // CORREGIDO

        return sb.toString();
    }

}
