package jsoftware.com.jblue.model.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsoftware.com.jblue.model.dto.SequenceDTO;
import jsoftware.com.jblue.model.exp.BlueException;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Data Access Object (DAO) encargado de la administración y recuperación de
 * contadores atómicos centrales a través de funciones nativas en MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-27
 * @version 1.0
 */
public class SequenceDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public SequenceDAO(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
    }

    public BigInteger getNextValBI(JDBConnection connection, String sequenceName) throws DataAccesObjectException, SQLException {
        return new BigInteger(getNextValString(connection, sequenceName));
    }

    public BigDecimal getNextValBD(JDBConnection connection, String sequenceName) throws DataAccesObjectException, SQLException {
        return new BigDecimal(getNextValString(connection, sequenceName));
    }

    public int getNextValInt(JDBConnection connection, String sequenceName) throws DataAccesObjectException, SQLException {
        return Integer.parseInt(getNextValString(connection, sequenceName));
    }

    public long getNextValLong(JDBConnection connection, String sequenceName) throws DataAccesObjectException, SQLException {
        return Long.parseLong(getNextValString(connection, sequenceName));
    }

    /**
     * Incrementa de manera atómica y recupera el siguiente valor disponible
     * para la secuencia solicitada mediante la función almacenada
     * {@code seq_nextval}.
     * <br><br>
     * <strong>Comportamiento Concurrente:</strong> Este método es 100% inmune a
     * condiciones de carrera gracias al uso interno de
     * {@code LAST_INSERT_ID()}.
     *
     * @param connection Conexión activa provista por el orquestador
     * transaccional.
     * @param sequenceName Nombre único de la secuencia en la base de datos (ej:
     * 'seq_receipt_folio').
     * @return El número de folio generado de tipo {@code long}.
     * @throws DataAccesObjectException Si la secuencia no existe, está
     * inactiva, o si ocurre una falla de conectividad en el motor relacional.
     */
    public String getNextValString(JDBConnection connection, String sequenceName) throws DataAccesObjectException, SQLException {
        String fol = null;
        // Estructura limpia de invocación de función determinista
        String sql = "SELECT seq_nextval(?) AS next_val";

        // Implementación con try-with-resources para asegurar el cierre del canal físico
        try (PreparedStatement ps = connection.getNewPreparedStatement(sql)) {

            ps.setString(1, sequenceName.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new DataAccesObjectException(BlueException.DATA_ACCESS_OBJECT_EXCEPTION, "[1] ERROR AL OBTENER UN NUMERO DE FOLIO");
                }
                fol = rs.getString("next_val");
                if (Func.isNullEmptyBlank(fol)) {
                    throw new DataAccesObjectException(BlueException.DATA_ACCESS_OBJECT_EXCEPTION, "[2] ERROR AL OBTENER UN NUMERO DE FOLIO");
                }
            }
        }
        return fol;
    }

    /**
     * Recupera la información completa de una secuencia activa filtrando por su
     * ID.
     *
     * * @param connection Conexión activa provista por el orquestador.
     * @param id Identificador único de la secuencia.
     * @return Un SequenceDTO cargado dinámicamente con los datos, o null si no
     * se encuentra.
     * @throws DataAccesObjectException Si ocurre un error de consistencia.
     * @throws SQLException Si falla la comunicación con MySQL.
     */
    public SequenceDTO get(JDBConnection connection, String id) throws DataAccesObjectException, SQLException {
        SequenceDTO dto = null;
        String sql = "SELECT * FROM cat_sequences WHERE id = ?";

        try (PreparedStatement ps = connection.getNewPreparedStatement(sql)) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Instanciamos el DTO inyectando el mapa genérico y sanitizado extraído por getMap
                    dto = new SequenceDTO(getMap(rs));
                }
            }
        }
        return dto;
    }

    /**
     * Recupera una secuencia activa filtrando por su nombre único
     * (sequence_name).
     *
     * * @param connection Conexión activa provista por el orquestador.
     * @param sequenceName Nombre de la secuencia (ej: 'seq_receipt_folio').
     * @return Un SequenceDTO cargado dinámicamente, o null si no se encuentra o
     * está inactiva.
     * @throws DataAccesObjectException Si ocurre un error de consistencia.
     * @throws SQLException Si falla la comunicación con MySQL.
     */
    public SequenceDTO getSequence(JDBConnection connection, String sequenceName) throws DataAccesObjectException, SQLException {
        SequenceDTO dto = null;
        String sql = "SELECT * FROM cat_sequences WHERE sequence_name = ? AND status = 1";

        try (PreparedStatement ps = connection.getNewPreparedStatement(sql)) {
            ps.setString(1, sequenceName.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto = new SequenceDTO(getMap(rs));
                }
            }
        }
        return dto;
    }

    /**
     * Recupera una secuencia activa filtrando por su prefijo de control
     * (sequence_prefix).
     *
     * * @param connection Conexión activa provista por el orquestador.
     * @param prefix Prefijo de control en mayúsculas (ej: 'PYM', 'CON').
     * @return Un SequenceDTO cargado dinámicamente, o null si no se encuentra o
     * está inactiva.
     * @throws DataAccesObjectException Si ocurre un error de consistencia.
     * @throws SQLException Si falla la comunicación con MySQL.
     */
    public SequenceDTO getCurrentSequence(JDBConnection connection, String prefix) throws DataAccesObjectException, SQLException {
        SequenceDTO dto = null;
        String sql = "SELECT * FROM cat_sequences WHERE sequence_prefix = ? AND status = 1";

        try (PreparedStatement ps = connection.getNewPreparedStatement(sql)) {
            ps.setString(1, prefix.trim().toUpperCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto = new SequenceDTO(getMap(rs));
                }
            }
        }
        return dto;
    }
}
