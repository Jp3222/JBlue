/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.models;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 *
 * @author juanp
 */
public interface ValidationModel extends Serializable {

    String getErrorMessage();

    boolean isValid();

    <T extends Object> void addRuler(String name, T value, Predicate<T> ruler);

    void addErrorMessage(String name, String error_message);

    <T> void addRuler(String name, T value, Predicate<T> ruler, String errorMsg);
}
