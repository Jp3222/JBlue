/*
 * Copyright (C) 2024 juan-campos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.controllers.viewc;

import com.jblue.controllers.Controller;
import com.jblue.model.l4b.PaymentFactory;
import com.jblue.model.l4b.PaymentModel;
import com.jblue.model.constants.Const;
import com.jblue.model.factories.CacheFactory;
import com.jblue.model.dtos.OUser;
import com.jblue.sys.DevFlags;
import com.jblue.sys.Session;
import com.jblue.util.cache.MemoListCache;
import com.jblue.util.GraphicsUtils;
import com.jblue.views.components.UserViewComponent;
import com.jblue.views.ShopCartView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author juan-campos
 */
public class ShopCartController extends Controller {

    private final MemoListCache<OUser> memo_cache;
    private final ShopCartView view;
    private final PaymentModel service_payment;
    private final PaymentModel surcharge_payment;
    private final StringBuilder mov_book;

    public ShopCartController(ShopCartView view) {
        System.out.println("xd 1");
        this.view = view;
        memo_cache = CacheFactory.USERS;
        this.service_payment = PaymentFactory.getServicePayment();
        this.surcharge_payment = PaymentFactory.getSurchargePayment();
        this.mov_book = new StringBuilder(3000);
        service_payment.setMovBook(mov_book);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "payments" ->
                payments();
            case "cancel" ->
                cancel();
            case "clear" ->
                clear();
            case "surcharges" ->
                surcharges();
            case "late_payments" ->
                latePayments();
            case "other_payments" ->
                otherPayments();
            case "lock" ->
                lock();
            case "info" ->
                info();
            case "all_months" ->
                allMonths((JCheckBox) e.getSource());
            case "month" ->
                total();
            case "mov_book" -> {
                mov_book();
            }
            default ->
                defaultCase(e.getActionCommand(), null, -1);
        }
    }

    void lock() {
        GraphicsUtils.disableTreeLock(view.isRootPanelLock(), view.getRootPanel());
    }

    void info() {
        if (view.getObjectSearch() == null) {
            JOptionPane.showMessageDialog(view, "Usuario no encontrado");
            return;
        }
        UserViewComponent.showVisor(view.getObjectSearch());
    }

    void payments() {
        mov_book.setLength(0);
        mov_book.setLength(3000);
        mov_book.append("Usuario: ").append(view.getObjectSearch().toString());
        mov_book.append("\nPAGOS POR EL SERVICIO\n");
        if (view.getObjectSearch() == null) {
            JOptionPane.showMessageDialog(view, "Usuario no valido", "Operacion Erronea", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String in = JOptionPane.showInputDialog(view, "Dinero Ingresado", "Dinero ingresado", JOptionPane.INFORMATION_MESSAGE);
        if (DevFlags.DEV_MSG_CODE) {
            System.out.println("Dinero ingresado" + in);
        }
        float dinero_in = Float.parseFloat(in.concat(".00"));

        service_payment.setUsuario(view.getObjectSearch());
        service_payment.setMesesPagados(view.getMonthPaidList());
        service_payment.setDineroIngresado(dinero_in);
        boolean execPayment = service_payment.execPayment();
        mov_book.append("Total: ").append(service_payment.getTotal());
        if (execPayment) {
            Session.getInstancia().register(
                    Const.INSERT_TO_SERVICE_PAYMENTS,
                    DESCRIPTION_FORMAT.formatted(view.getObjectSearch().getId(), view.getMonthPaidList().toString())
            );
        }
        rmessage(execPayment);
    }

    private String DESCRIPTION_FORMAT = "USUARIO:%s, MESES:%s";

    void cancel() {
        int input = JOptionPane.showConfirmDialog(view,
                "¿Desea cancelar la operacion?",
                "Cancelar operacion",
                JOptionPane.YES_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (input == JOptionPane.YES_OPTION) {
            view.initialState();
        }
    }

    void clear() {

    }

    private void surcharges() {
        int i = JOptionPane.showConfirmDialog(view,
                "¿Desea generarle un recargo a este usuario?",
                "Recargos",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (i == JOptionPane.YES_OPTION) {
            mov_book.setLength(0);
            mov_book.setLength(3000);
            mov_book.append("Usuario: ").append(view.getObjectSearch().toString());
            mov_book.append("\nRECARGOS POR PAGOS TARDIOS\n");
            if (view.getObjectSearch() == null) {
                JOptionPane.showMessageDialog(view, "Usuario no valido", "Operacion Erronea", JOptionPane.ERROR_MESSAGE);
                return;
            }
            surcharge_payment.setUsuario(view.getObjectSearch());
            surcharge_payment.setMesesPagados(view.getMonthPaidList());
            boolean execPayment = service_payment.insertToDefault();
            mov_book.append("Total: ").append(service_payment.getTotal());
            if (execPayment) {
                Session.getInstancia().register(
                        Const.INSERT_TO_SURCHARGE_PAYMENTS,
                        DESCRIPTION_FORMAT.formatted(view.getObjectSearch().getId(), view.getMonthPaidList().toString())
                );
            }
            rmessage(execPayment);
        }
    }

    private void latePayments() {
    }

    private void otherPayments() {
    }

    private void allMonths(JCheckBox all) {
        SwingUtilities.invokeLater(() -> {
            for (Component i : view.getMonthList()) {
                if (i instanceof JCheckBox o) {
                    o.setSelected(o.isEnabled() && all.isSelected());
                }
            }
            total();
        });
    }

    private void total() {
        double price = view.getObjectSearch().getWaterIntakesObject().getPrice();
        double months_paids = view.getMonthPaidList().size();
        double total = price * months_paids;
        view.setTotalField(total);
    }

    public void rmessage(boolean op) {
        String status = op ? "Exitoso" : "Erroneo";
        JOptionPane.showMessageDialog(view,
                "Operacion %s".formatted(status),
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);
        if (op) {
            memo_cache.reLoadData();
            view.initialState();
        }
    }

    public void setPaymentsInfo(OUser user) {
        try {
            System.out.println("lista");
            LocalDate ld = LocalDate.now();
            String query = "SELECT month_name FROM service_payments WHERE user = '%s' AND YEAR(NOW()) = '%s' AND status != 3"
                    .formatted(user.getId(), ld.getYear());

            ResultSet res = CacheFactory.SERVICE_PAYMENTS
                    .getConnection().getConnection().query(query);
            ArrayList<String> list = new ArrayList<>();
            while (res.next()) {
                list.add(res.getString(1));
            }

            System.out.println(list.toString());

            ArrayList<JCheckBox> check_box = view.getMonthList();
            boolean contains = false;
            for (JCheckBox i : check_box) {

                System.out.println("con: " + contains);
                contains = list.contains(i.getText());
                i.setSelected(contains);
                i.setEnabled(!contains);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopCartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void mov_book() {
        JOptionPane.showMessageDialog(view, service_payment.getMovBook());
    }
}
