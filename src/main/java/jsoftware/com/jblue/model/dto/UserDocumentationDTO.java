package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.model.AbstractMapDTO;

/**
 * Data Transfer Object (DTO) para la gestión y transporte de documentos
 * digitalizados de los usuarios en el sistema JBlue.
 * <br><br>
 * <strong>Estándar JBlue:</strong> Equilibra el mapeo manual para asegurar
 * tipos mediante getters/setters explícitos mientras mantiene la flexibilidad
 * de transporte de datos basados en {@code String} mediante un mapa interno.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-19
 * @version 1.0
 */
public class UserDocumentationDTO extends AbstractMapDTO {

    private static final long serialVersionUID = 1L;

    public UserDocumentationDTO() {
        super(20);
    }

    // =====================================================================
    // GETTERS Y SETTERS CON CONVERSIÓN HOMOLOGADA A STRING
    // =====================================================================
    public String getId() {
        return Func.nullSafeToString("id");
    }

    public void setId(String id) {
        put("id", id);
    }

    public String getDocumentRecordId() {
        return Func.nullSafeToString("document_record_id");
    }

    public void setDocumentRecordId(String documentRecordId) {
        put("document_record_id", documentRecordId);
    }

    public String getSequence() {
        return Func.nullSafeToString("sequence");
    }

    public void setSequence(String sequence) {
        put("sequence", sequence);
    }

    public String getName() {
        return Func.nullSafeToString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getPath() {
        return Func.nullSafeToString("path");
    }

    public void setPath(String path) {
        put("path", path);
    }

    /**
     * Recupera el archivo binario en formato de cadena de texto (Base64).
     */
    public String getFile() {
        return Func.nullSafeToString("file");
    }

    /**
     * Asigna el archivo binario convertido previamente a cadena de texto
     * (Base64).
     */
    public void setFile(String fileBase64) {
        put("file", fileBase64);
    }

    public String getTypeId() {
        return Func.nullSafeToString("type_id");
    }

    public void setTypeId(String typeId) {
        put("type_id", typeId);
    }

    public String getStatus() {
        return Func.nullSafeToString("status");
    }

    public void setStatus(String status) {
        put("status", status);
    }

    public String getLastEmployeeId() {
        return Func.nullSafeToString("last_employee_id");
    }

    public void setLastEmployeeId(String lastEmployeeId) {
        put("last_employee_id", lastEmployeeId);
    }

    public String getDateUpdate() {
        return Func.nullSafeToString("date_update");
    }

    public void setDateUpdate(String dateUpdate) {
        put("date_update", dateUpdate);
    }

    public String getDateRegister() {
        return Func.nullSafeToString("date_register");
    }

    public void setDateRegister(String dateRegister) {
        put("date_register", dateRegister);
    }

    public String getDateEnd() {
        return Func.nullSafeToString("date_end");
    }

    public void setDateEnd(String dateEnd) {
        put("date_end", dateEnd);
    }
}
