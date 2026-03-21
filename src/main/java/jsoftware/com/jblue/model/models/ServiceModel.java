/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jsoftware.com.jblue.model.models;

import java.io.Serializable;

/**
 *
 * @author juanp
 */
public interface ServiceModel extends Serializable {

    String getUserMessage();

    int getErrorCode();

    boolean isError();

}
