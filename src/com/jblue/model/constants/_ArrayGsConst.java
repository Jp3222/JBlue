/*
 * Copyright (C) 2025 juanp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.model.constants;

/**
 *
 * @author juanp
 */
public interface _ArrayGsConst {

    public static String[] CAT_GROUP_PREFIX_FIELDS_GS = {
        "ID",
        "TIPO_DE_PREFIJO",
        "DESCRIPCION",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO",
        "FECHA_FIN"
    };
    public static String[] CAT_HISTORY_TYPE_MOV_FIELDS_GS = {
        "ID",
        "DESCRIPCION",
        "ESTADO",
        "FECHA_REGISTRO"
    };
    public static String[] CAT_PROCESS_TYPE_FIELDS_GS = {
        "ID",
        "NOMBRE_TIPO",
        "ID_TABLA_AFECTADA",
        "TABLA_AFECTADA",
        "DESCRIPCION",
        "ESTADO",
        "FECHA_REGISTRO"
    };
    public static String[] CAT_STATUS_FIELDS_GS = {
        "ID",
        "DESCRIPCION",
        "TABLA_AFECTADA",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO",
        "FECHA_FINALIZACION"
    };
    public static String[] CAT_STREET_FIELDS_GS = {
        "ID",
        "NOMBRE_CALLE",
        "ESTADO",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO"
    };
    public static String[] CAT_TABLES_DB_FIELDS_GS = {
        "ID",
        "PREFIJO_GRUPO",
        "NOMBRE_TABLA",
        "DESCRIPCION",
        "USUARIO_CREADOR",
        "NOMBRE_USUARIO_CREADOR",
        "ESTADO",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO",
        "FECHA_FIN"
    };
    public static String[] CAT_USER_MOVEMENTS_FIELDS_GS = {
        "ID",
        "TIPO_MOVIMIENTO",
        "GRUPO",
        "ESTADO",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO"
    };
    public static String[] DEV_PARAMETERS_FIELDS_GS = {
        "ID",
        "PARAMETRO",
        "DESCRIPCION",
        "VALOR",
        "UNIDAD",
        "TIPO_DATO",
        "ESTADO",
        "USUARIO_BD_CREACION",
        "USUARIO_BD_ACTUALIZACION",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO"
    };
    public static String[] EMP_EMPLOYEE_TYPES_FIELDS_GS = {
        "ID",
        "TIPO_EMPLEADO",
        "ESTADO",
        "FECHA_REGISTRO"
    };
    public static String[] EMP_EMPLOYEES_FIELDS_GS = {
        "ID",
        "CURP",
        "PRIMER_NOMBRE",
        "APELLIDO1",
        "APELLIDO2",
        "GENERO",
        "CORREO_ELECTRONICO",
        "FECHA_CUMPLEANOS",
        "TELEFONO1",
        "TELEFONO2",
        "TIPO_EMPLEADO",
        "ESTADO",
        "USUARIO",
        "CONTRASENA",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO",
        "FECHA_FIN"
    };
    public static String[] HYS_HISTORY_FIELDS_GS = {
        "ID",
        "ID_AMBIENTE_AFECTADO",
        "DESCRIPCION_AMBIENTE_AFECTADO",
        "TABLA_AFECTADA",
        "DESCRIPCION_TABLA_AFECTADA",
        "ID_MOVIMIENTO",
        "FECHA_REGISTRO"
    };
    public static String[] HYS_PROGRAM_HISTORY_FIELDS_GS = {
        "ID",
        "EMPLEADO",
        "USUARIO_BD",
        "TABLA_AFECTADA",
        "TIPO_MOVIMIENTO",
        "DESCRIPCION",
        "FECHA_REGISTRO"
    };
    public static String[] LOGBOOK_TO_PAYMENTS_FIELDS_GS = {
        "ID",
        "ID_PAGO",
        "TIPO_PAGO",
        "TIPO_MOVIMIENTO",
        "VALORES_ANTERIORES",
        "VALORES_NUEVOS",
        "DESCRIPCION",
        "USUARIO_BD",
        "FECHA_REGISTRO"
    };
    public static String[] LOGBOOK_TO_USERS_FIELDS_GS = {
        "ID",
        "ID_USUARIO",
        "TIPO_MOVIMIENTO",
        "VALORES_ANTERIORES",
        "VALORES_NUEVOS",
        "DESCRIPCION",
        "USUARIO_BD",
        "FECHA_REGISTRO"
    };
    public static String[] LOGBOOK_TO_WATER_INTAKES_FIELDS_GS = {
        "ID",
        "ID_TOMA_DE_AGUA",
        "ID_USUARIO",
        "TIPO_MOVIMIENTO",
        "VALORES_ANTERIORES",
        "VALORES_NUEVOS",
        "DESCRIPCION",
        "USUARIO_BD",
        "FECHA_REGISTRO"
    };
    public static String[] LOGBOOK_TO_WATER_INTAKES_TYPE_FIELDS_GS = {
        "ID",
        "ID_TIPO_TOMA_DE_AGUA",
        "TIPO_MOVIMIENTO",
        "VALORES_ANTERIORES",
        "VALORES_NUEVOS",
        "DESCRIPCION",
        "USUARIO_BD",
        "FECHA_REGISTRO"
    };
    public static String[] PRO_CHANGE_OWNER_FIELDS_GS = {
        "ID",
        "PRECIO",
        "TOMA_DE_AGUA",
        "PROPIETARIO_ANTERIOR",
        "NUEVO_PROPIETARIO",
        "EMPLEADO",
        "PRESIDENTE",
        "DESCRIPCION",
        "ESTADO",
        "FECHA_REGISTRO"
    };
    public static String[] PRO_PROCESS_FIELDS_GS = {
        "ID",
        "TIPO_PROCESO",
        "EMPLEADO_INICIO",
        "FECHA_INICIO",
        "EMPLEADO_VALIDO",
        "FECHA_VALIDO",
        "EMPLEADO_FIN",
        "FECHA_FIN",
        "PRESIDENTE",
        "USUARIO_ACTUAL",
        "USUARIO_ORIGINAL",
        "TOMA_DE_AGUA",
        "ESTADO",
        "FECHA_REGISTRO"
    };
    public static String[] PYM_OTHER_PAYMENTS_FIELDS_GS = {
        "ID",
        "EMPLEADO",
        "USUARIO",
        "TIPO_PAGO",
        "PRECIO",
        "ESTADO",
        "FECHA_REGISTRO",
        "FECHA_PAGO"
    };
    public static String[] PYM_OTHER_PAYMENTS_TYPE_FIELDS_GS = {
        "ID",
        "TIPO_PAGO",
        "DESCRIPCION",
        "ESTADO",
        "RUTA_ARCHIVOS",
        "FECHA_REGISTRO",
        "FECHA_FIN"
    };
    public static String[] PYM_PROCEDURE_PAYMENTS_FIELDS_GS = {
        "ID",
        "TOMA_DE_AGUA",
        "COSTO_TOTAL",
        "COSTO_OPERACION",
        "COSTO_PROCEDIMIENTO",
        "COSTO_BOTA",
        "COSTO_ABRAZADERA",
        "COSTO_LLAVE"
    };
    public static String[] PYM_SERVICE_PAYMENTS_FIELDS_GS = {
        "ID",
        "EMPLEADO",
        "USUARIO",
        "PRECIO",
        "NOMBRE_MES",
        "MES",
        "ESTADO",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO"
    };
    public static String[] PYM_SURCHARGE_PAYMENTS_FIELDS_GS = {
        "ID",
        "EMPLEADO",
        "USUARIO",
        "PRECIO",
        "NOMBRE_MES",
        "MES",
        "ESTADO",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO"
    };
    public static String[] USR_USER_TYPE_FIELDS_GS = {
        "ID",
        "TIPO_USUARIO",
        "ESTADO",
        "FECHA_REGISTRO",
        "FECHA_FINALIZACION"
    };
    public static String[] USR_USERS_FIELDS_GS = {
        "ID",
        "CURP",
        "PRIMER_NOMBRE",
        "APELLIDO1",
        "APELLIDO2",
        "GENERO",
        "CORREO_ELECTRONICO",
        "TELEFONO1",
        "TELEFONO2",
        "CALLE1",
        "CALLE2",
        "NUMERO_INTERIOR",
        "NUMERO_EXTERIOR",
        "TIPO_TOMA_DE_AGUA",
        "TIPO_USUARIO",
        "ESTADO",
        "FECHA_ULTIMA_ACTUALIZACION",
        "FECHA_REGISTRO"
    };
    public static String[] USR_USERS_CONSUMERS_FIELDS_GS = {
        "ID",
        "TOMA_DE_AGUA",
        "TITULAR",
        "CONSUMIDOR",
        "ESTADO",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO",
        "FECHA_FIN"
    };
    public static String[] WKI_WATER_INTAKE_TYPE_FIELDS_GS = {
        "ID",
        "NOMBRE_TIPO",
        "PRECIO_ACTUAL",
        "PRECIO_ANTERIOR",
        "RECARGO",
        "ESTADO",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO",
        "FECHA_FIN"
    };
    public static String[] WKI_WATER_INTAKES_FIELDS_GS = {
        "ID",
        "COSTO_PROCEDIMIENTO",
        "TIPO_TOMA_DE_AGUA",
        "USUARIO",
        "CALLE1",
        "CALLE2",
        "UBICACION",
        "DESCRIPCION",
        "ESTADO",
        "FECHA_ACTUALIZACION",
        "FECHA_REGISTRO",
        "FECHA_FIN"
    };
}
