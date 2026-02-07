/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

import java.util.List;
import java.util.Objects;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jutil.util.JFunc;

/**
 *
 * @author juanp
 */
public class Func extends JFunc {

    public static void devMessages(String msg) {
        if (AppConfig.isDevMessages()) {
            System.out.println(msg);
        }
    }

    /**
     * Agrega una cláusula SET al StringBuilder y el valor a la lista de
     * parámetros solo si el valor nuevo es distinto al anterior.
     */
    public static void appendIfChanged(StringBuilder sb, List<Object> params, String column, Object oldValue, Object newValue) {
        if (!Objects.equals(oldValue, newValue)) {
            sb.append(column).append(" = ?, ");
            params.add(newValue);
        }
    }

    /**
     * Versión específica para campos numéricos que vienen como String desde la
     * vista.
     */
    public static void appendIfChangedInt(StringBuilder sb, List<Object> params, String column, String oldValue, String newValue) {
        if (!Objects.equals(oldValue, newValue)) {
            sb.append(column).append(" = ?, ");
            params.add(newValue == null || newValue.isBlank() ? null : Integer.valueOf(newValue));
        }
    }

    /**
     * Compara valores y construye dinámicamente la cláusula SET. Maneja
     * automáticamente la conversión a Integer si el valor es numérico.
     */
    public static void appendIfChanged(StringBuilder sb, List<Object> params, String column, Object oldValue, Object newValue, boolean isNumeric) {
        if (!Objects.equals(oldValue, newValue)) {
            sb.append(column).append(" = ?, ");

            if (isNumeric) {
                params.add((newValue == null || newValue.toString().isBlank())
                        ? null : Integer.valueOf(newValue.toString()));
            } else {
                params.add(newValue);
            }
        }
    }
}
