/*
 * Copyright (C) 2024 juan pablo campos casasanero
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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.events.SearchUserTyped;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.service.ShopCarService;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jblue.views.components.ObjectSearchComponent;
import jsoftware.com.jblue.views.mod.pro.ShoppingCartProcess;

/**
 *
 * @author juan pablo campos casasanero
 */
public class ShopCartController extends Controller {

    private static final long serialVersionUID = 1L;

    private final MemoListCache<UserDTO> memo_cache;
    private ShoppingCartProcess view;
    private final StringBuilder mov_book;
    private final ShopCarService service;
    private final SearchUserTyped event;
    private final Timer throttleTimer;

    public ShopCartController(ShoppingCartProcess view) {
        this.view = view;
        this.memo_cache = CacheFactory.USERS;
        this.mov_book = new StringBuilder(3000);
        this.service = new ShopCarService(false, ShopCartController.class.getName());
        this.event = new SearchUserTyped(service);
        this.event.setView(view);
        this.throttleTimer = new Timer(600, event);
        this.throttleTimer.setRepeats(false);
    }

    public ShopCartController() {
        this(null);
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

    }

    private void total() {
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
        //view.setObjectSearch(o);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        view.getDtoWrapper()
//                .getWki_user()
//                .getMap()
//                .put("user_name", view.getTextSearchList());
//        throttleTimer.restart();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getClickCount() == 2) {
//            JList<WaterIntakeUserDTO> list = view.getList();
//            if (list.getSelectedIndex() <= 0) {
//                return;
//            }
//            WaterIntakeUserDTO get = view.getListModel().get(list.getSelectedIndex());
//            ShopCartWrapperDTO dtoWrapper = view.getDtoWrapper();
//        }
    }

    public void setView(ShoppingCartProcess view) {
        this.view = view;
        event.setView(view);
    }

}
