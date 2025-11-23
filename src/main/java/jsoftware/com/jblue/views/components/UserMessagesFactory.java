/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.components;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author juanp
 */
public class UserMessagesFactory {

    public static final String GENERIC_MESSAGE = "HA OCURRIDO UN ERROR INESPERADO";
    public static final String GENERIC_MESSAGES_WITH_CODE = "[%s] HA OCURRIDO UN ERROR INESPERADO";
    public static final String PAYMENT_TITLE = "Pago por el servicio";
    public static final String USER_TITLE = "Usuario";
    public static final String SESION_TITLE = "Inicio de sesion";

    public static void showSessionWarninMessage(JComponent c, String msg) {
        showWarninMessage(c, msg, SESION_TITLE);
    }

    public static void showSessionErrorMessage(JComponent c, String msg) {
        showErrorMessage(c, msg, SESION_TITLE);
    }

    public static void showUserMessage(JComponent c, String msg) {
        showOkMessage(c, msg, USER_TITLE);
    }

    public static void showPaymentErr(JComponent c, String msg) {
        showErrorMessage(c, msg, PAYMENT_TITLE);
    }

    public static void showOkMessage(JComponent c, String msg, String title) {
        JOptionPane.showMessageDialog(c, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorMessage(JComponent c, String msg, String title) {
        JOptionPane.showMessageDialog(c, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarninMessage(JComponent c, String msg, String title) {
        JOptionPane.showMessageDialog(c, msg, title, JOptionPane.WARNING_MESSAGE);
    }
}
