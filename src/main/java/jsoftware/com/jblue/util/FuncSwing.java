/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author juanp
 */
public class FuncSwing {

    public static <T> DefaultListModel<T> updateData(DefaultListModel<T> model, List<T> data) {
        if (model == null) {
            return new DefaultListModel<>(); // O manejar el error según tu arquitectura
        }

        model.removeAllElements(); // Limpiamos los elementos anteriores

        if (data != null && !data.isEmpty()) {
            model.addAll(data);    // Agregamos los nuevos elementos solo si hay datos
        }

        return model;
    }
}
