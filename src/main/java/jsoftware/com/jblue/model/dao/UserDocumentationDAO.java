package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import jsoftware.com.jblue.model.dto.UserDocumentationDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Data Access Object (DAO) encargado de la persistencia y operaciones físicas
 * por lotes de los documentos digitalizados de usuarios en MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-19
 * @version 1.0
 */
public class UserDocumentationDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public UserDocumentationDAO(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
    }

    /**
     * Realiza una inserción masiva (por lotes) de documentos mediante Batch
     * nativo de JDBC. Convierte las cadenas en Base64 del DTO a arreglos de
     * bytes para la columna BLOB.
     *
     * * @param connection Conexión activa y controlada por el orquestador.
     * @param dtoList Lista de UserDocumentationDTO con los datos de los
     * archivos.
     * @return List de Integers con todos los IDs autogenerados recuperados.
     * @throws DataAccesObjectException Si ocurre un error de persistencia o
     * nulos en columnas obligatorias.
     * @throws KeyNotGenerateException Si MySQL falla al retornar la llave
     * primaria de algún registro.
     * @throws SQLException Si ocurre un fallo en la manipulación del Batch o el
     * driver de red.
     */
    public List<Integer> insertBatch(JDBConnection connection, List<UserDocumentationDTO> dtoList)
            throws DataAccesObjectException, KeyNotGenerateException, SQLException {

        List<Integer> generatedIds = new ArrayList<>();

        if (dtoList == null || dtoList.isEmpty()) {
            return generatedIds;
        }

        String sql = """
            INSERT INTO doc_user_documentation (
                document_record_id, sequence, name, path, file, type_id, status, last_employee_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // PASO 1: Preparar y empaquetar los comandos en el Batch
            for (UserDocumentationDTO dto : dtoList) {
                // 1. document_record_id (int NOT NULL)
                ps.setInt(1, Integer.parseInt(dto.getDocumentRecordId()));

                // 2. sequence (int DEFAULT NULL)
                if (dto.getSequence() != null && !dto.getSequence().trim().isEmpty()) {
                    ps.setInt(2, Integer.parseInt(dto.getSequence()));
                } else {
                    ps.setNull(2, java.sql.Types.INTEGER);
                }

                // 3. name (varchar NOT NULL)
                ps.setString(3, dto.getName());

                // 4. path (varchar DEFAULT NULL)
                ps.setString(4, dto.getPath());

                // 5. file (blob NOT NULL)
                if (dto.getFile() != null && !dto.getFile().isEmpty()) {
                    byte[] fileBytes = Base64.getDecoder().decode(dto.getFile());
                    ps.setBytes(5, fileBytes);
                } else {
                    throw new DataAccesObjectException(
                            DataAccesObjectException.CORRUPT_INSERTION_EXCEPTION,
                            "VALIDACIÓN DAO: El archivo físico (BLOB) no puede ser nulo o vacío."
                    );
                }

                // 6. type_id (int NOT NULL)
                ps.setInt(6, Integer.parseInt(dto.getTypeId()));

                // 7. status (int DEFAULT NULL)
                if (dto.getStatus() != null && !dto.getStatus().trim().isEmpty()) {
                    ps.setInt(7, Integer.parseInt(dto.getStatus()));
                } else {
                    ps.setNull(7, java.sql.Types.INTEGER);
                }

                // 8. last_employee_id (int NOT NULL)
                ps.setInt(8, Integer.parseInt(dto.getLastEmployeeId()));

                // Añadimos el registro actual a la cola del Batch
                ps.addBatch();
            }

            // PASO 2: Enviar el lote completo a MySQL en un único viaje de red
            int[] batchResults = ps.executeBatch();

            // Validación defensiva: Verificamos que el lote no se haya ejecutado en vacío
            if (batchResults.length == 0) {
                throw new DataAccesObjectException(
                        DataAccesObjectException.CORRUPT_INSERTION_EXCEPTION,
                        "La inserción por lotes falló, ninguna fila fue modificada."
                );
            }

            // PASO 3: Recuperación ordenada de llaves autogeneradas masivas
            try (ResultSet rs = ps.getGeneratedKeys()) {
                for (UserDocumentationDTO dto : dtoList) {
                    if (rs.next()) {
                        int idGenerated = rs.getInt(1);
                        generatedIds.add(idGenerated);

                        // Enriquecimiento del DTO en caliente (Estándar JBlue)
                        dto.setId(String.valueOf(idGenerated));
                    } else {
                        // Si nos quedamos sin IDs antes de terminar la lista, la integridad falló
                        throw new KeyNotGenerateException();
                    }
                }
            }

        }
        return generatedIds;
    }
}
