/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.models;

/**
 * <h1>Diccionario Maestro de Excepciones - Sistema JBlue</h1>
 * * <p>
 * Esta interfaz centraliza los identificadores numéricos de errores para
 * garantizar la trazabilidad absoluta entre las capas de persistencia, negocio
 * y vista.</p>
 * * <p>
 * <b>Arquitectura de Rangos:</b></p>
 * <ul>
 * <li><b>-1:</b> Fallos críticos de implementación y entorno de
 * desarrollo.</li>
 * <li><b>100-199:</b> Excepciones de persistencia (Capa DAO).</li>
 * <li><b>200-299:</b> Errores de lógica de negocio (Capa Service).</li>
 * <li><b>300-399:</b> Inconsistencias en el flujo del Controlador.</li>
 * <li><b>400-499:</b> Errores de validación y renderizado (Capa Vista).</li>
 * <li><b>500-600:</b> Fallos de flujo operativo (Trámites, Permisos,
 * Auditoría).</li>
 * </ul>
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @version 1.1 (2026-02-07)
 */
public interface ExceptionCode {

    /**
     * * Error genérico de implementación producido por lógica de código
     * defectuosa. Valor por defecto para excepciones no capturadas manualmente.
     */
    public static final int DEV_EXCEPTION = -1;

    // --- [100-199] CAPA DE PERSISTENCIA: DATA ACCESS OBJECT (DAO) ---
    /**
     * Error base en la capa de acceso a datos.
     */
    public static final int DATA_ACCESS_OBJECT_EXCEPTION = 100;

    /**
     * * Error en la recuperación de identidad. La inserción fue aceptada por
     * la DB, pero el driver no retornó el ID autogenerado.
     */
    public static final int CORRUPT_GENERATE_KEY_EXCEPTION = 101;

    /**
     * * Error de persistencia en sentencia INSERT. La operación no afectó
     * filas o violó restricciones de integridad.
     */
    public static final int CORRUPT_INSERTION_EXCEPTION = 102;

    /**
     * * Error de persistencia en sentencia UPDATE. Generalmente ocurre si el
     * registro está bloqueado o el ID ya no existe.
     */
    public static final int CORRUPT_UPDATE_EXCEPTION = 103;

    /**
     * * Inconsistencia en lectura de datos (SELECT). Error al recorrer el
     * ResultSet o discrepancia de tipos en el mapeo manual.
     */
    public static final int CORRUPT_SELECT_EXCEPTION = 104;

    /**
     * * Fallo en la ejecución de baja lógica. Error al modificar el 'status' a
     * 3 o fallo en el trigger de auditoría (date_end).
     */
    public static final int CORRUPT_LOGIC_DELETE_EXCEPTION = 105;

    // --- [200-299] CAPA DE NEGOCIO: SERVICE LAYER ---
    /**
     * Error base en la orquestación de servicios.
     */
    public static final int SERVICE_EXCEPTION = 200;

    /**
     * Ruptura en la secuencia lógica de una operación compuesta.
     */
    public static final int BROKEN_FLOW_EXCEPTION = 201;

    /**
     * Fallo crítico al intentar persistir el rastro de la operación en las
     * tablas de historial.
     */
    public static final int BROKEN_HISTORY_EXCEPTION = 202;

    // --- [500-600] FLUJO OPERATIVO: TRÁMITES Y USUARIOS ---
    /**
     * Error genérico en la gestión de cuentas de usuario.
     */
    public static final int USER_EXCEPTION = 500;

    /**
     * Fallo en la apertura y asignación de folio para un trámite nuevo.
     */
    public static final int START_PROCESS_EXCEPTION = 501;

    /**
     * Error durante el proceso de verificación de requisitos o documentación.
     */
    public static final int VALID_PROCESS_EXCEPTION = 502;

    /**
     * No se pudo registrar el cierre administrativo del proceso.
     */
    public static final int END_PROCESS_EXCEPTION = 503;

    /**
     * Fallo al vincular el pago de caja con el folio del trámite
     * correspondiente.
     */
    public static final int PAYMENT_PROCESS_EXCEPTION = 504;

    /**
     * Error en la generación o renderizado del comprobante de trámite
     * (PDF/Ticket).
     */
    public static final int PRINT_PROCESS_EXCEPTION = 505;
}
