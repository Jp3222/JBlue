/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

/**
 *
 * @author jp
 */
public interface Mensajes {

    /**
     *
     */
    static final String FORM_SSS = "%s %s %s";

    /**
     *
     */
    static final String FORM_SS = "%s %s";

    /**
     * Cadenas que representan objetos
     * <br> 0 - datos
     * <br> 1 - tabla
     */
    static final String[] _OBJETOS = {
        "datos ", "tabla "
    };

    /**
     * Cadenas que representan operaciones que se ejecutan en los objetos
     * <br> 0 - inserccion
     * <br> 1 - eliminacion
     * <br> 3 - actualizacion
     */
    static final String[] _OPERACIONES = {
        "inserccion ", "eliminacion ", "actualizacion "
    };

    /**
     * Cadenas que representan los estados de una operacion
     * <br> 0 - exitosa
     * <br> 1 - erronea
     * <br> 2 - no validos
     */
    static final String[] _ESTADO = {
        "exitosa", "erronea", "no validos"
    };

}
