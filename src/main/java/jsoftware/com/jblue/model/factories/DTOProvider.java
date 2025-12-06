/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
@FunctionalInterface
public interface DTOProvider<T extends JDBMapObject> {

    T get();
}
