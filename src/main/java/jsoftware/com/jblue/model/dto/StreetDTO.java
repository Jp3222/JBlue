package jsoftware.com.jblue.model.dto;

import java.util.Map;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al catálogo de calles (Street).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre la interfaz Swing y la capa DAO de MySQL,
 * asegurando el aislamiento de tipos hasta el casteo final en el DAO.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.0
 */
public class StreetDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public StreetDTO(Map<String, Object> map) {
        super(map);
    }

    public StreetDTO() {
        // Inicializa con una capacidad de 12 para albergar con holgura los 7 campos,
        // garantizando eficiencia de carga sin reestructuraciones en memoria.
        super(12);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia
    public String getStreetName() {
        return Func.nullSafeToString(get("street_name"));
    }

    public String getColonyId() {
        return Func.nullSafeToString(get("colony_id"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public String getLastEmployeeUpdate() {
        return Func.nullSafeToString(get("employee_last_update"));
    }

    public String getDateUpdate() {
        return Func.nullSafeToString(get("date_update"));
    }

    public String getDateRegister() {
        return Func.nullSafeToString(get("date_register"));
    }

    public String getDateEnd() {
        return Func.nullSafeToString(get("date_end"));
    }

    @Override
    public String toString() {
        // Asegura una impresión limpia en los logs de Log4j2 previniendo excepciones nulas
        return (values != null) ? values.toString() : "{}";
    }
}
