/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

/**
 *
 * @author juanp
 */
public interface DBObjectValues<T> {

    public boolean isValuesOK();

    public T getValues(boolean update);
}
