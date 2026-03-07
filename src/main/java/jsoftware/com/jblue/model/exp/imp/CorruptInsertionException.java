/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp.imp;

import jsoftware.com.jblue.model.exp.DataAccesObjectException;

/**
 *
 * @author juanp
 */
public class CorruptInsertionException extends DataAccesObjectException {
    
    public CorruptInsertionException() {
        super(CORRUPT_INSERTION_EXCEPTION, "EL REGISTRO NO PUDO SER GUARDADO EN LA BASE DE DATOS");
    }
    
}
