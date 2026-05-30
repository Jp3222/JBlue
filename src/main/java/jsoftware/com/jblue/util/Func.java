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

    /**
     * Metodo que evalua los casos y retorna el valor dato segun la condicion.
     * Funciona en parejas: [Condición1, Valor1, Condición2, Valor2, ...]
     *
     * * @param object_value Objeto base a evaluar.
     * @param object_default Valor de retorno si ninguna condición coincide.
     * @param condition_value Parejas de condición y valor de retorno.
     * @return El valor correspondiente a la condición que coincida, o el valor
     * por defecto.
     */
    public static Object Decode(Object object_value, Object object_default, Object... condition_value) {
        if (object_value == null) {
            throw new NullPointerException("OBJETO EVALUADO NULL");
        }

        if (object_default == null) {
            throw new NullPointerException("OBJETO POR DEFECTO NULL");
        }

        // Si el varargs viene vacío o nulo, retornamos el valor por defecto de inmediato
        if (condition_value == null || condition_value.length == 0) {
            return object_default;
        }

        // CORRECCIÓN: Los argumentos de condición deben ser pares obligatoriamente.
        // Si la longitud es impar (ej. 3 o 5 elementos), la última condición no tendría valor de retorno.
        if (condition_value.length % 2 != 0) {
            throw new IllegalArgumentException("EL ARREGLO DE CONDICIONES DEBE SER PAR [CONDICION, VALOR]");
        }

        // Iteramos el arreglo de dos en dos (i = condición, i + 1 = valor de retorno)
        for (int i = 0; i < condition_value.length; i += 2) {
            Object condicion = condition_value[i];
            Object valorRetorno = condition_value[i + 1];

            // Evaluamos la igualdad de forma segura previniendo nulos
            if (object_value.equals(condicion)) {
                return valorRetorno;
            }
        }

        // Si ninguna condición coincidió en el bucle, se envía el backup
        return object_default;
    }
}
