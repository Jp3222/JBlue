/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.time.LocalDateTime;

/**
 * Define el contrato para cualquier objeto (DTO/Model) que requiera la
 * trazabilidad completa de su ciclo de vida en la base de datos, incluyendo las
 * fechas de creación, actualización y finalización. * Extiende
 * {@link StatusObjectModel} para también incluir el estado.
 *
 * @author Juan Campos
 * @version 1.0
 * @since 22/11/2025
 */
public interface AuditableModel extends StatusObjectModel {

    /**
     * Obtiene la marca de tiempo de la última modificación o actualización
     * realizada en el registro. Este valor se actualiza automáticamente en la
     * base de datos (e.g., usando CURRENT_TIMESTAMP ON UPDATE).
     *
     * @return La fecha y hora de la última actualización del registro.
     */
    public LocalDateTime getDateUpdate();

    /**
     * Obtiene la marca de tiempo exacta en que el registro fue insertado por
     * primera vez en la base de datos.
     *
     * @return La fecha y hora de registro (creación) del objeto.
     */
    public LocalDateTime getDateRegister();

    /**
     * Obtiene la fecha y hora en que el registro fue lógicamente finalizado,
     * cancelado o dado de baja (un campo que generalmente permite valores
     * nulos).
     *
     * @return La fecha y hora de finalización del registro, o null si el
     * registro aún está activo.
     */
    public LocalDateTime getDateEnd();
}
