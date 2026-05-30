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
 *
 * @version 1.0
 */
public interface TableStatusIndexConst {

    public static final int INDEX_STATUS_ACTIVO_1 = 1;
    public static final int INDEX_STATUS_INACTIVO_2 = 2;
    public static final int INDEX_STATUS_ELIMINADO_3 = 3;
    public static final int INDEX_STATUS_FINALIZADO_4 = 4;
    public static final int INDEX_STATUS_CANCELADO_5 = 5;
    public static final int INDEX_STATUS_NO_ACCESIBLE_PARA_EL_USUARIO_6 = 6;
    public static final int INDEX_STATUS_PAGADO_7 = 7;
    public static final int INDEX_STATUS_PENDIENTE_8 = 8;
    public static final int INDEX_STATUS_EN_PROCESO_9 = 9;
    public static final int INDEX_STATUS_INICIADO_10 = 10;
    public static final int INDEX_STATUS_VALIDADO_11 = 11;
    public static final int INDEX_STATUS_PAGADO_12 = 12;
    public static final int INDEX_STATUS_FINALIZADO_13 = 13;
    public static final int INDEX_STATUS_CADUCADO_14 = 14;
    public static final int INDEX_STATUS_HABILITADO_15 = 15;
    public static final int INDEX_STATUS_DESHABILITADO_16 = 16;
    public static final int INDEX_STATUS_ACTIVO_TEMPORALMENTE_17 = 17;
    public static final int INDEX_STATUS_REGISTRADO_18 = 18;
    public static final int INDEX_STATUS_RECHAZADO_19 = 19;
    public static final int INDEX_STATUS_EN_PROCESO_20 = 20;
    public static final int INDEX_STATUS_REGISTRADA_21 = 21;
    public static final int INDEX_STATUS_RECONECTADA_22 = 22;
    public static final int INDEX_STATUS_DESCONECTADA_23 = 23;
    public static final int INDEX_STATUS_CONCEPTO_INMUTABLE_24 = 24;
    public static final int INDEX_STATUS_CONCEPTO_FINALIZADO_25 = 25;
    public static final int INDEX_STATUS_DOCUMENTO_CAPTURADO_26 = 26;
    public static final int INDEX_STATUS_DOCUMENTO_VALIDADO_27 = 27;
    public static final int INDEX_STATUS_DOCUMENTO_RECHAZADO_28 = 28;
    public static final int INDEX_STATUS_DOCUMENTO_EN_REVISION_29 = 29;
    public static final int INDEX_STATUS_DOCUMENTO_LIBERADO_30 = 30;
    public static final int INDEX_STATUS_SITUACION_CRITICA_31 = 31;
    public static final int INDEX_STATUS_REPORTADO_32 = 32;
}
