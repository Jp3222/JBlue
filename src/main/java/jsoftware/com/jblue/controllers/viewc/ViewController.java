/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jblue.views.framework.SimpleView;

/**
 *
 * @author juanp
 */
public class ViewController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent e) {
    
    }

    public void returnMessage(SimpleView view, boolean ok) {
        returnMessage(view, null, ok ? 1 : 0);
    }

    public void returnMessage(SimpleView view, String msg, boolean ok) {
        returnMessage(view, msg, ok ? 1 : 0);
    }

    /**
     * LANZA UN MENSAJE DADO POR EL PROGRAMADOR O UNO POR DEFECTO SEGUN LA
     * BANDERA "OK", ASOCIADO A UNA VENTANA DE TIPO AbstractAppWindows
     *
     * <br>
     *
     * SI EL msg ES NULL SE EVALUA ok
     * <br>
     * SI ok ES TRUE EL MENSAJE SERA: "OPERACION EXITOSA" Y EL ICONO DE TIPO
     * JOptionPane.INFORMATION_MESSAGE
     * <br>
     * SI NO: "OPERACION ERRONEA" Y EL ICONO DE TIPO JOptionPane.ERROR_MESSAGE
     *
     * @param view - VISTA A LA QUE SE ASOCIA EL MENSAJE
     * @param msg - MENSAJE DADO POR EL PROGRAMADOR
     * @param type - BANDERA QUE INDICA SI LA OPERACION ES EXITOSA O NO
     */
    public void returnMessage(SimpleView view, String msg, int type_mov) {
        int type = switch (type_mov) {
            case 1:
                yield JOptionPane.INFORMATION_MESSAGE;
            case 0:
                yield JOptionPane.ERROR_MESSAGE;
            default:
                yield JOptionPane.WARNING_MESSAGE;
        };
        if (Func.isNull(msg)) {
            msg = switch (type_mov) {
                case 1:
                    yield "OPERACION EXITOSA";
                case 0:
                    yield "OPERACION ERRONEA";
                default:
                    yield "ADVERTENCIA";
            };;
        }
        JOptionPane.showMessageDialog(view,
                msg,
                "ESTADO DE LA OPERACION",
                type
        );
    }
}
