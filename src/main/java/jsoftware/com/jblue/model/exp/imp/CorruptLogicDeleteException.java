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
public class CorruptLogicDeleteException extends DataAccesObjectException {
    
    public CorruptLogicDeleteException() {
        super(CORRUPT_INSERTION_EXCEPTION, "EL CAMBIO DE STATUS NO PUDO SER REALIZADO");
    }
    
}