/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.models;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import jsoftware.com.jblue.util.Func;

/**
 *
 * @author juanp
 */
public class AbstractValidation implements ValidationModel {

    private static final long serialVersionUID = 1L;

    private final Map<String, Ruler> rulers;
    private String error;

    public AbstractValidation() {
        this.rulers = new HashMap<>(20);
        this.error = null;
    }

    @Override
    public <T> void addRuler(String name, T value, Predicate<T> ruler, String errorMsg) {
        addRuler(name, value, ruler);
        addErrorMessage(name, errorMsg);
    }

    @Override
    public String getErrorMessage() {
        return error;
    }

    @Override
    public boolean isValid() {
        boolean res = false;
        if (rulers.isEmpty()) {
            error = "LAS REGLAS NO HAN SIDO AÑADIDAS";
            return res;
        }
        String msg_aux = "Error con la regla: %s";
        for (Map.Entry<String, Ruler> entry : rulers.entrySet()) {
            Ruler val = entry.getValue();
            //VALIDAMOS QUE LA REGLA TENGA APLICACION
            if (Func.isNull(val)) {
                error = "LA REGLA: %s NO TIENE APLICACION".formatted(entry.getKey());
                return res;
            }
            //EVALUAMOS LA REGLA
            res = val.apply();
            if (!res) {
                error = Func.isNull(val.getFalseMessage())
                        ? msg_aux.formatted(entry.getKey())
                        : val.getFalseMessage();
                return res;
            }
        }
        return res;
    }

    @Override
    public <T> void addRuler(String name, T value, Predicate<T> ruler) {
        rulers.put(name, new Ruler(value, ruler));
    }

    @Override
    public void addErrorMessage(String name, String error_message) {
        if (rulers.containsKey(name)) {
            Ruler r = rulers.get(name);
            r.setFlaseMessage(error_message);
        }
    }

    private class Ruler<T> {

        private final T value;
        private final Predicate<T> ruler;
        private String error_message;

        public Ruler(T value, Predicate<T> ruler) {
            this.value = value;
            this.ruler = ruler;
        }

        boolean apply() {
            return ruler.test(value);
        }

        String getFalseMessage() {
            return error_message;
        }

        void setFlaseMessage(String msg) {
            this.error_message = msg;
        }

    }

}
