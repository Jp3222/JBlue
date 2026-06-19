package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al registro de la administración actual e histórico de comités
 * del sistema (AdministrationHistory).
 * <br>
 * Utiliza la estructura heredada dinámica de Map para transportar las marcas de
 * tiempo, estados y roles múltiples hacia empleados de manera flexible entre los
 * controladores de Swing y la capa DAO de MySQL, apegándose al estándar actual
 * de JBlue.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.0
 */
public class AdministrationHistoryDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public AdministrationHistoryDTO() {
        // Inicializa con una capacidad de 16 para albergar de forma eficiente los 13 campos operativos (incluyendo id),
        // garantizando un factor de carga óptimo, evitando re-hasheos o redimensionamientos en memoria 
        // durante el flujo transaccional.
        super(16);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia

    public String getYearOfAdministration() {
        return Func.nullSafeToString(get("year_of_administration"));
    }

    public String getRoot() {
        return Func.nullSafeToString(get("root"));
    }

    public String getAdministrator() {
        return Func.nullSafeToString(get("administrator"));
    }

    public String getPresident() {
        return Func.nullSafeToString(get("president"));
    }

    public String getTreasurer() {
        return Func.nullSafeToString(get("treasurer"));
    }

    public String getSecretary() {
        return Func.nullSafeToString(get("secretary"));
    }

    public String getPlumber() {
        return Func.nullSafeToString(get("plumber"));
    }

    public String getIntern1() {
        return Func.nullSafeToString(get("intern_1"));
    }

    public String getIntern2() {
        return Func.nullSafeToString(get("intern_2"));
    }

    public String getInternt3() { // Respetando nombre exacto 'internt_3' de tu DDL
        return Func.nullSafeToString(get("internt_3"));
    }

    public String getDescription() {
        return Func.nullSafeToString(get("description"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public String getDateRegister() {
        return Func.nullSafeToString(get("date_register"));
    }

    public String getDateEnd() {
        return Func.nullSafeToString(get("date_end"));
    }

    @Override
    public String toString() {
        // Asegura un vaciado seguro en tus logs de auditoría o inserciones de payload previniendo excepciones nulas
        return (values != null) ? values.toString() : "{}";
    }
}