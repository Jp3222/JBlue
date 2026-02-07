/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.constants.des;

/**
 * Definición de Estados Universales del Sistema JBlue.
 * <p>
 * Esta interfaz centraliza los identificadores numéricos que representan los
 * estados lógicos de cualquier entidad en la base de datos (Usuarios, Trámites,
 * Pagos, etc.).
 * </p>
 * * <b>Uso recomendado:</b>
 * Utilice estas constantes en las cláusulas {@code WHERE} de los DAOs o para
 * comparaciones de lógica de negocio en la capa de servicio para evitar el uso
 * de valores literales (números mágicos). * * @author JUAN PABLO CAMPOS
 * CASASANERO
 * @version 1.0
 */
public interface TableStatusIndexConst {

    /**
     * Representa un registro vigente y disponible para operaciones normales.
     */
    public static final int INDEX_STATUS_ACTIVO = 1;

    /**
     * Representa un registro deshabilitado temporalmente, no visible en
     * operaciones comunes.
     */
    public static final int INDEX_STATUS_INACTIVO = 2;

    /**
     * Representa un borrado lógico; el registro permanece en DB para integridad
     * referencial.
     */
    public static final int INDEX_STATUS_ELIMINADO = 3;

    /**
     * Indica que un proceso o trámite ha concluido satisfactoriamente.
     */
    public static final int INDEX_STATUS_FINALIZADO = 4;

    /**
     * Indica que un proceso o trámite fue abortado sin completarse.
     */
    public static final int INDEX_STATUS_CANCELADO = 5;

    /**
     * Estado de seguridad: el registro existe pero está restringido para el
     * acceso del cliente/usuario.
     */
    public static final int INDEX_STATUS_NO_ACCESIBLE_PARA_EL_USUARIO = 6;
}
