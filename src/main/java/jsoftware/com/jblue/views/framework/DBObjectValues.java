/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public interface DBObjectValues<T extends JDBMapObject> {

    public boolean isValuesOK();

    public T getValues(boolean update);
}
