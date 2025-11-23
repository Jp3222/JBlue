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
package jsoftware.com.jblue.controllers.viewc;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.model.dao.PaymentsDAO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.OWaterIntakes;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.util.GraphicsUtils;
import jsoftware.com.jblue.util.PaymentsRulers;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jblue.views.ShopCartView;
import jsoftware.com.jblue.views.components.ObjectSearchComponent;
import jsoftware.com.jblue.views.components.UserViewComponent;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juan-campos
 */
public class ShopCartController extends Controller {

    private static final long serialVersionUID = 1L;

    private final MemoListCache<UserDTO> memo_cache;
    private final ShopCartView view;
    private final StringBuilder mov_book;
    private final PaymentsDAO payments_dao;

    public ShopCartController(ShopCartView view) {
        this.view = view;
        this.memo_cache = CacheFactory.USERS;
        this.mov_book = new StringBuilder(3000);
        this.payments_dao = new PaymentsDAO("ShopCartController");
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
            case "search_user_button" ->
                searchUser();
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
        UserViewComponent.showVisor(view.getObjectSearch().getUserObject());
    }

    private void surcharges() {

    }

    void payments() {
        try {
        } catch (Exception e) {

        } finally {
        }
    }

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
        OWaterIntakes o = view.getObjectSearch();
        String total = PaymentsRulers.calculateBaseTotal(
                view.getMonthPaidList().size(),
                new BigDecimal(o.getWaterIntakeTypeObject().getCurrentPrice())
        ).toPlainString();
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

    public void setPaymentsInfo(UserDTO user) {
        JDBConnection connection = ConnectionFactory.getIntance().getPaymentsConnection();
        List<PaymentDTO> paymentList = payments_dao.getPaymentList(connection);
        paymentList.stream().filter((t) -> t.)
        ArrayList<JCheckBox> check_box = view.getMonthList();
        boolean contains = false;
        for (JCheckBox i : check_box) {
            contains = list.contains(i.getText());
            i.setSelected(contains);
            i.setEnabled(!contains);

        }

    }

    private void mov_book() {
        String book = mov_book.toString();
        if (book == null || book.isBlank()) {
            book = "Sin Movimientos";
        }
        JOptionPane.showMessageDialog(view, book, "Movimientos Recientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchUser() {
        UserDTO o = ObjectSearchComponent.getUser(null);
        if (o == null) {
            JOptionPane.showMessageDialog(view, "Usuario no encontrado");
            return;
        }
        view.setObjectSearch(o);
        UserViewComponent.showVisor(view.getObjectSearch().getUserObject());
    }
}
