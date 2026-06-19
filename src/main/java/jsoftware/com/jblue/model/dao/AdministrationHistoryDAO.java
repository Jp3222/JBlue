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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Clase DAO (Data Access Object) responsable de consultar y gestionar el
 * registro histórico de la administración activa (hys_administration_history).
 *
 * @author Juan Campos
 * @version 1.0
 * @since 22/11/2025
 */
public class AdministrationHistoryDAO extends AbstractDAO {

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

    private static final long serialVersionUID = 1L;

    public AdministrationHistoryDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Valida si el campo del Presidente está registrado en la administración
     * activa.
     *
     * @param connection La conexión JDBC activa.
     * @return {@code true} si el campo 'president' no es nulo en el registro
     * activo.
     */
    public boolean isPresidentRegistered(JDBConnection connection) {
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
    public boolean isTreasurerRegistered(JDBConnection connection) {
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
    public boolean isAdministratorRegistered(JDBConnection connection) {
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
    private boolean isEmployeeRegistered(JDBConnection connection, int employee) {
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

    /**
     * Obtiene el registro de administración activa para el año en curso.
     * <p>
     * Este método ejecuta la consulta SQL que busca un registro activo para el
     * año actual sin aplicar filtros por empleados específicos.
     * </p>
     *
     * @param connection La conexión JDBC activa.
     * @return El DTO de la historia de administración actual
     * ({@code AdministrationHistoryDTO}) o {@code null} si no se encuentra un
     * registro activo.
     * @throws RuntimeException Si ocurre un error de SQL durante la consulta.
     */
    public AdministrationHistoryDTO getCurrentAdministration(JDBConnection connection) {
        // -1 en getQueryEmployeeRegisterInAdmin indica que no se debe filtrar por empleado.
        // getQueryEmployeeRegisterInAdmin() se asume que es un método estático de esta clase.
        String query = getQueryEmployeeRegisterInAdmin(-1);
        AdministrationHistoryDTO dto = null;

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
     * asume que {@code AdministrationHistoryDTO} implementa la lógica de un DTO
     * basado en mapa (como en {@code AbstractMapDTO}).
     * </p>
     *
     * @param rs El ResultSet posicionado en una fila válida (ya ejecutado).
     * @return Un DTO ({@code AdministrationHistoryDTO}) con los valores
     * mapeados.
     * @throws SQLException Si ocurre un error al acceder a los datos del
     * ResultSet (ej. columna no existe).
     */
    private static AdministrationHistoryDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        AdministrationHistoryDTO dto = new AdministrationHistoryDTO();
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

    /**
     * Inserta el registro del histórico de administración para la gestión del
     * comité. Mapea de forma manual los Strings del DTO a los tipos requeridos
     * por MySQL, administrando los puestos opcionales y enriqueciendo el DTO
     * con el ID final.
     *
     * @param connection Conexión activa bajo la transacción del Service
     * (autoCommit = false)
     * @param dto DTO con el Map de parámetros provenientes de la Vista
     * @return true si el registro fue exitoso y el DTO fue enriquecido
     * @throws SQLException Si ocurre un fallo en el motor de la base de datos
     * @throws CorruptInsertionException Si la ejecución no afecta exactamente a
     * 1 fila
     * @throws KeyNotGenerateException Si MySQL no logra retornar el ID
     * incremental
     */
    public boolean insert(JDBConnection connection, AdministrationHistoryDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        boolean res = false;
        String INSERT = "INSERT INTO hys_administration_history ("
                + "year_of_administration, root, administrator, president, treasurer, "
                + "secretary, plumber, intern_1, intern_2, internt_3, " // Usando 'internt_3' según tu DDL
                + "description, status, date_end"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.getNewPreparedStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getYearOfAdministration());
            ps.setString(2, dto.getRoot());
            ps.setString(3, dto.getAdministrator());
            ps.setString(4, dto.getPresident());
            ps.setString(5, dto.getTreasurer());
            ps.setString(6, dto.getSecretary());
            ps.setString(7, dto.getPlumber());
            ps.setString(8, dto.getIntern1());
            ps.setString(9, dto.getIntern2());
            ps.setString(10, dto.getInternt3());
            ps.setString(11, dto.getDescription());
            ps.setString(12, dto.getStatus());
            ps.setString(13, dto.getDateEnd());
            // 4. EJECUCIÓN Y VALIDACIÓN ATÓMICA
            res = ps.executeUpdate() == 1;
            if (!res) {
                throw new CorruptInsertionException();
            }
            // 5. CAPTURA DE LLAVE AUTOGENERADA Y ENRIQUECIMIENTO
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                int generatedId = rs.getInt(1);
                dto.put("id", String.valueOf(generatedId)); // Enriquecimiento post-éxito
                res = true;
            }
        }
        return res;
    }

    /**
     * Actualiza un registro existente en la tabla hys_administration_history.
     * Sigue el estándar de JBlue: la Vista maneja Strings, el DTO los
     * transporta y el DAO realiza el casting final, abstrayendo los valores
     * nulos.
     *
     * @param connection Conexión activa bajo la transacción del Service
     * (autoCommit = false)
     * @param dto DTO que contiene los cambios y el ID del registro a modificar
     * @return true si la actualización fue exitosa y afectó exactamente a la
     * fila esperada
     * @throws SQLException Si ocurre un error en el motor de la base de datos
     * @throws CorruptInsertionException Si la ejecución afecta a 0 o a más de 1
     * fila
     */
    public boolean update(JDBConnection connection, AdministrationHistoryDTO dto)
            throws SQLException, CorruptInsertionException {

        boolean success = false;

        String UPDATE = "UPDATE hys_administration_history SET "
                + "year_of_administration = ?, "
                + "root = ?, "
                + "administrator = ?, "
                + "president = ?, "
                + "treasurer = ?, "
                + "secretary = ?, "
                + "plumber = ?, "
                + "intern_1 = ?, "
                + "intern_2 = ?, "
                + "internt_3 = ?, "
                + "description = ?, "
                + "status = ?, "
                + "date_end = ? "
                + "WHERE id = ?";

        try (PreparedStatement ps = connection.getNewPreparedStatement(UPDATE)) {

            // 1. OBLIGATORIOS Y CONTROL FISCAL
            ps.setInt(1, Integer.parseInt(dto.getYearOfAdministration()));

            // 'root' maneja contingencia por si la vista lo manda vacío
            String rootStr = dto.getRoot();
            ps.setInt(2, (rootStr == null || rootStr.trim().isEmpty()) ? 1 : Integer.parseInt(rootStr));

            // 2. PUESTOS DE CONTROL (Casting y manejo de opcionales NULL con método utilitario)
            setOptionalInteger(ps, 3, dto.getAdministrator());
            ps.setInt(4, Integer.parseInt(dto.getPresident()));
            ps.setInt(5, Integer.parseInt(dto.getTreasurer()));
            setOptionalInteger(ps, 6, dto.getSecretary());
            setOptionalInteger(ps, 7, dto.getPlumber());
            setOptionalInteger(ps, 8, dto.getIntern1());
            setOptionalInteger(ps, 9, dto.getIntern2());
            setOptionalInteger(ps, 10, dto.getInternt3()); // Campo 'internt_3' del DDL

            // 3. CAMPOS DESCRIPTIVOS Y TIMESTAMPS
            ps.setString(11, dto.getDescription());
            ps.setInt(12, Integer.parseInt(dto.getStatus()));

            // Tratamiento de la fecha de finalización (date_end)
            String dateEndStr = dto.getDateEnd();
            if (dateEndStr == null || dateEndStr.trim().isEmpty() || dateEndStr.equalsIgnoreCase("N/A")) {
                ps.setNull(13, Types.TIMESTAMP);
            } else {
                ps.setString(13, dateEndStr);
            }

            // 4. CONDICIÓN LLAVE PRIMARIA (Criterio WHERE)
            ps.setInt(14, Integer.parseInt(dto.getId()));

            // 5. EJECUCIÓN TRANSACCIONAL DEFINITIVA
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new CorruptInsertionException();
            } else if (rowsAffected > 1) {
                throw new CorruptInsertionException();
            }

            success = true;

        } catch (SQLException | CorruptInsertionException e) {
            // Se propaga para permitir el rollback seguro desde la capa Service
            throw e;
        }

        return success;
    }

    /**
     * Método utilitario interno para procesar enteros opcionales provenientes
     * del DTO evitando NullPointerException o formatos vacíos en la UI.
     */
    private void setOptionalInteger(PreparedStatement ps, int parameterIndex, String value) throws SQLException {
        if (value == null || value.trim().isEmpty() || value.equalsIgnoreCase("N/A")) {
            ps.setNull(parameterIndex, Types.INTEGER);
        } else {
            ps.setInt(parameterIndex, Integer.parseInt(value));
        }
    }

}
