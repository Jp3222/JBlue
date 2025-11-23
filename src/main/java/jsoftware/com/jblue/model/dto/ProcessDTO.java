package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.model.constants.Const;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DTO para representar la información de un Proceso. Implementación basada en
 * arreglo de String indexado, similar a OUser.
 *
 * @author Gemini
 */
public class ProcessDTO extends Objects { // Se asume que extiende 'Objects' como OUser

    // Definición de índices (basada en el orden de los campos solicitados)
    /*
    * 0: id
    * 1: process_type (int)
    * 2: employee_start
    * 3: date_start (LocalDateTime)
    * 4: employee_valid
    * 5: date_valid (LocalDateTime)
    * 6: employee_payment
    * 7: date_payment (LocalDateTime)
    * 8: employee_ends
    * 9: date_end (LocalDateTime)
    * 10: employee_print
    * 11: date_print (LocalDateTime)
    * 12: administration
    * 13: last_employee_update
    * 14: current_db_user
    * 15: original_user
    * 16: water_intake
    * 17: status (int)
    * 18: date_register (LocalDateTime)
     */
    public ProcessDTO(String[] info) {
        super(info);
    }

    public ProcessDTO() {
        super();
    }

    // --- Métodos Getters ---
    /**
     * Retorna el ID del proceso.
     *
     * @return El ID (values[0])
     */
    public String getId() {
        return values[0];
    }

    /**
     * Retorna el tipo de proceso.
     *
     * @return El tipo de proceso como entero (values[1])
     */
    public int getProcessType() {
        return Integer.parseInt(values[1]);
    }

    // --- Empleados y Fechas de Etapas ---
    public String getEmployeeStart() {
        return values[2];
    }

    public LocalDateTime getDateStart() {
        return LocalDateTime.parse(values[3], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    public String getEmployeeValid() {
        return values[4];
    }

    public LocalDateTime getDateValid() {
        return LocalDateTime.parse(values[5], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    public String getEmployeePayment() {
        return values[6];
    }

    public LocalDateTime getDatePayment() {
        return LocalDateTime.parse(values[7], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    public String getEmployeeEnds() {
        return values[8];
    }

    public LocalDateTime getDateEnd() {
        return LocalDateTime.parse(values[9], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    public String getEmployeePrint() {
        return values[10];
    }

    public LocalDateTime getDatePrint() {
        return LocalDateTime.parse(values[11], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    // --- Campos Administrativos y de Auditoría ---
    public String getAdministration() {
        return values[12];
    }

    public String getLastEmployeeUpdate() {
        return values[13];
    }

    public String getCurrentDbUser() {
        return values[14];
    }

    public String getOriginalUser() {
        return values[15];
    }

    // --- Llave Foránea y Estado ---
    /**
     * Retorna el ID de la toma de agua asociada.
     *
     * @return El ID de la toma de agua (values[16])
     */
    public String getWaterIntake() {
        return values[16];
    }

    // Asumiendo que necesitas resolver el objeto de la toma de agua, similar a OUser
    /*
    public OWaterIntake getWaterIntakeObject() {
        // Se asume la existencia de un método similar en ObjectUtils
        return ObjectUtils.getWaterIntakeObject(getWaterIntake());
    }
     */
    /**
     * Retorna el estado del proceso.
     *
     * @return El estado como entero (values[17])
     */
    public int getStatus() {
        return Integer.parseInt(values[17]);
    }

    /**
     * Retorna la fecha de registro.
     *
     * @return La fecha de registro (values[18])
     */
    public LocalDateTime getDateRegister() {
        return LocalDateTime.parse(values[18], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    // --- Métodos de Conveniencia (Similar a OUser) ---
    public boolean isStarted() {
        // Un ejemplo de lógica de estado simple
        return getDateStart() != null;
    }

    @Override
    public String toString() {
        return "Processo ID: " + getId() + ", Tipo: " + getProcessType() + ", Inicio: " + getDateStart();
    }
}
