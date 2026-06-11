package jsoftware.com.jblue.model.models;

import java.io.Serializable;

/**
 * Contrato estructural que define el comportamiento y estado de respuesta de
 * las operaciones de la capa de Servicios en JBlue.
 * <br>
 * Centraliza la encapsulación de mensajes amigables para el usuario, banderas
 * de control de fallos y códigos de error para auditoría interna.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-03-06
 * @version 1.1
 */
public interface ServiceModel extends Serializable {

    // Las constantes son implícitamente public static final en interfaces
    int SERVICE_EXECUTE_OK = 0;
    int SERVICE_EXECUTE_ERROR = 1000;

    /**
     * Recupera el mensaje formateado y seguro destinado a mostrarse en la
     * interfaz de usuario (Swing).
     *
     * @return String con la descripción amigable del resultado de la operación.
     */
    String getUserMessage();

    /**
     * Obtiene el código numérico de error asignado para el diagnóstico técnico
     * de la operación.
     *
     * @return int correspondiente al catálogo de errores de JBlue (0 si la
     * operación fue exitosa).
     */
    int getErrorCode();

    /**
     * Evalúa si la ejecución del servicio culminó en un estado de fallo o
     * excepción de negocio.
     *
     * @return true si ocurrió un error durante el proceso, false en caso de
     * éxito rotundo.
     */
    boolean isError();
}
