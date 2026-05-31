package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al motor de trámites y máquina de estados (Process).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre las interfaces de Swing y la capa DAO de MySQL,
 * soportando la mutación progresiva del ciclo de vida del trámite.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-10-18
 * @version 2.0
 */
public class ProcessDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public ProcessDTO() {
        // Inicializa con una capacidad de 40 para albergar de forma eficiente los 27 campos,
        // garantizando un factor de carga óptimo sin redimensionamientos en memoria.
        super(40); 
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia

    public String getProcessType() {
        return Func.nullSafeToString(get("process_type"));
    }

    public String getSequenceProcess() {
        return Func.nullSafeToString(get("sequence_process"));
    }

    public String getEmployeeStart() {
        return Func.nullSafeToString(get("employee_start"));
    }

    public String getDateStart() {
        return Func.nullSafeToString(get("date_start"));
    }

    public String getUserId() {
        return Func.nullSafeToString(get("user_id"));
    }

    public String getEmployeeValid() {
        return Func.nullSafeToString(get("employee_valid"));
    }

    public String getDateValid() {
        return Func.nullSafeToString(get("date_valid"));
    }

    public String getDocumentId() {
        return Func.nullSafeToString(get("document_id"));
    }

    public String getEmployeeWkiUpdate() {
        return Func.nullSafeToString(get("employee_wki_update"));
    }

    public String getDateWkiUpdate() {
        return Func.nullSafeToString(get("date_wki_update"));
    }

    public String getWkiUserId() {
        return Func.nullSafeToString(get("wki_user_id"));
    }

    public String getEmployeePayment() {
        return Func.nullSafeToString(get("employee_payment"));
    }

    public String getDatePayment() {
        return Func.nullSafeToString(get("date_payment"));
    }

    public String getPaymentId() {
        return Func.nullSafeToString(get("payment_id"));
    }

    /**
     * Mapea la columna de la base de datos manteniendo compatibilidad 
     * exacta con el identificador 'employe_print'.
     */
    public String getEmployePrint() {
        return Func.nullSafeToString(get("employe_print"));
    }

    public String getDatePrint() {
        return Func.nullSafeToString(get("date_print"));
    }

    public String getEmployeeFinalize() {
        return Func.nullSafeToString(get("employee_finalize"));
    }

    public String getDateFinalize() {
        return Func.nullSafeToString(get("date_finalize"));
    }

    public String getAdministrationStart() {
        return Func.nullSafeToString(get("administration_start"));
    }

    public String getAdministrationEnd() {
        return Func.nullSafeToString(get("administration_end"));
    }

    public String getCurrentDbUser() {
        return Func.nullSafeToString(get("current_db_user"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public String getLastEmployeeUpdate() {
        return Func.nullSafeToString(get("last_employee_update"));
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
        // Asegura una impresión limpia en tus logs de Log4j2 previniendo excepciones nulas
        return (values != null) ? values.toString() : "{}";
    }
}