/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.dto.UserDocumentDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.util.JFunc;

/**
 *
 * @author juanp
 */
public class UserDocumentDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public UserDocumentDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }
    public final String INSERT = "INSERT INTO usr_user_document(user, document_name, document_path, doc_file) VALUES(?,?,?,?)";

    /**
     * Inserta una lista de documentos de identidad mediante procesamiento por
     * lotes (Batch).
     * <p>
     * Optimiza la persistencia de archivos binarios (BLOB) y rutas de servidor,
     * vinculando cada documento al ID de usuario proporcionado.
     * </p>
     *
     * @param connection Conexión transaccional activa.
     * @param doc_list Lista de documentos capturados en el proceso de registro.
     * @return true si el número de registros insertados coincide con el tamaño
     * de la lista.
     */
    public boolean insert(JDBConnection connection, List<UserDocumentDTO> doc_list) throws SQLException, Exception{
        boolean res = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(INSERT)) {
            for (UserDocumentDTO i : doc_list) {
                ps.setInt(1, Integer.parseInt(i.getUser()));
                ps.setString(2, i.getDocumentName());
                if (JFunc.isNotNullEmptyBlank(i.getDocumentPath())) {
                    ps.setString(3, i.getDocumentPath());
                } else {
                    ps.setNull(3, java.sql.Types.VARCHAR);
                }
                if (i.getDocFile() != null) {
                    ps.setBytes(4, i.getDocFile().getBytes());
                } else {
                    ps.setNull(4, java.sql.Types.BLOB);
                }
                ps.addBatch(); //Agrega este registro al lote
            }

            // Ejecuta todos los registros juntos
            int[] resultados = ps.executeBatch();
            res = resultados.length == doc_list.size();
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
}
