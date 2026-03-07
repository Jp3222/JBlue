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
public class CorruptSelectException extends DataAccesObjectException {

    public CorruptSelectException() {
        super(CORRUPT_INSERTION_EXCEPTION, "LA CONSULTA NO PUDO SER REALIZADA");
    }

}
