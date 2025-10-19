package jsoftware.com.jblue.model.dtos;

import jsoftware.com.jblue.model.constants._Const;
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
     * @return El ID (info[0])
     */
    public String getId() {
        return info[0];
    }

    /**
     * Retorna el tipo de proceso.
     *
     * @return El tipo de proceso como entero (info[1])
     */
    public int getProcessType() {
        return Integer.parseInt(info[1]);
    }

    // --- Empleados y Fechas de Etapas ---
    public String getEmployeeStart() {
        return info[2];
    }

    public LocalDateTime getDateStart() {
        return LocalDateTime.parse(info[3], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
    }

    public String getEmployeeValid() {
        return info[4];
    }

    public LocalDateTime getDateValid() {
        return LocalDateTime.parse(info[5], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
    }

    public String getEmployeePayment() {
        return info[6];
    }

    public LocalDateTime getDatePayment() {
        return LocalDateTime.parse(info[7], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
    }

    public String getEmployeeEnds() {
        return info[8];
    }

    public LocalDateTime getDateEnd() {
        return LocalDateTime.parse(info[9], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
    }

    public String getEmployeePrint() {
        return info[10];
    }

    public LocalDateTime getDatePrint() {
        return LocalDateTime.parse(info[11], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
    }

    // --- Campos Administrativos y de Auditoría ---
    public String getAdministration() {
        return info[12];
    }

    public String getLastEmployeeUpdate() {
        return info[13];
    }

    public String getCurrentDbUser() {
        return info[14];
    }

    public String getOriginalUser() {
        return info[15];
    }

    // --- Llave Foránea y Estado ---
    /**
     * Retorna el ID de la toma de agua asociada.
     *
     * @return El ID de la toma de agua (info[16])
     */
    public String getWaterIntake() {
        return info[16];
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
     * @return El estado como entero (info[17])
     */
    public int getStatus() {
        return Integer.parseInt(info[17]);
    }

    /**
     * Retorna la fecha de registro.
     *
     * @return La fecha de registro (info[18])
     */
    public LocalDateTime getDateRegister() {
        return LocalDateTime.parse(info[18], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
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
