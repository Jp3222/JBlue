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
package com.jblue.controlador.viewc;

import com.jblue.controlador.CPagos;
import com.jblue.controlador.Controller;
import com.jblue.controlador.compc.ComponentController;
import com.jblue.modelo.fabricas.FactoryCache;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.sistema.Sesion;
import com.jblue.util.cache.MemoListCache;
import com.jblue.util.tools.GraphicsUtils;
import com.jblue.vista.components.CVisorUsuario;
import com.jblue.vista.views.ShopCartView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author juan-campos
 */
public class ShopCartController extends Controller {

    private final MemoListCache<OUsuarios> memo_cache;
    private final ShopCartView view;

    public ShopCartController(ShopCartView view) {
        this.view = view;
        memo_cache = FactoryCache.USUARIOS;
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
        CVisorUsuario.showVisor(view.getObjectSearch());
    }

    void payments() {
        if (view.getObjectSearch()==null) {
            JOptionPane.showMessageDialog(view, "Usuario no valido", "Operacion Erronea", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String in = JOptionPane.showInputDialog(view, "Dinero Ingresado", "Dinero ingresado", JOptionPane.INFORMATION_MESSAGE);
        double dinero_in = Double.parseDouble(in);

        double deuda = 0;
        
        if (dinero_in < deuda) {
            return;
        }
        
        OPersonal personal = Sesion.getInstancia().getUsuario();
        OUsuarios usuario = view.getObjectSearch();
        String[] meses = view.getSelectMonth();
        CPagos.getInstancia().regPagoXServicio(personal, usuario, meses, 0);
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

    private void surcharges() {
    }

    private void latePayments() {
    }

    private void otherPayments() {
    }

    private void allMonths(JCheckBox all) {

        SwingUtilities.invokeLater(() -> {
            for (Component i : view.getMonthPaidList()) {
                if (i instanceof JCheckBox o) {
                    o.setSelected(o.isEnabled() && all.isSelected());
                }
            }
        });
    }

}
