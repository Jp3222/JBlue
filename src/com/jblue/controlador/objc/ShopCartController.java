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
package com.jblue.controlador.objc;

import com.jblue.controlador.Controller;
import com.jblue.modelo.fabricas.FabricaCache;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.Filtros;
import com.jblue.util.tools.GraphicsUtils;
import com.jblue.vista.components.CVisorUsuario;
import com.jblue.vista.views.ShopCartView;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 */
public class ShopCartController extends Controller implements KeyListener {

    private final ShopCartView view;

    public ShopCartController(ShopCartView view) {
        super();
        this.view = view;
        memo_cache = FabricaCache.USUARIOS;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "lock" -> {
                GraphicsUtils.disableTreeLock(view.isRootPanelLock(), view.getRootPanel());
            }
            case "info" -> {
                if (view.getObjectSearch() == null) {
                    JOptionPane.showMessageDialog(view, "Usuario no encontrado");
                    return;
                }
                CVisorUsuario.showVisor(view.getObjectSearch());
            }
            case "pay" -> {

            }
            case "cancel" -> {
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
            case "clear" -> {
            }
            case "all_months" -> {
                view.setAllMonths(true);
            }
            default -> {
                JButton o = (JButton) e.getSource();
                String msg = "La opcion '%s' no esta disponible".formatted(o.getText());
                JOptionPane.showMessageDialog(view, msg, "Opcion no disponible", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

        DefaultListModel<OUsuarios> listModel = view.getListModel();
        listModel.clear();

        String msg = view.getSearchField()
                .getText();
        if (Filtros.isNullOrBlank(msg)) {
            return;
        }
        List<OUsuarios> list = memo_cache.getList(o -> Filtros.limpiarYChecar(o.toString(), msg));
        listModel.addAll(list);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int clicks = e.getClickCount();
        if (clicks < 2) {
            return;
        }
        int selectedIndex = view.getUserList().getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex >= view.getListModel().getSize()) {
            return;
        }
        OUsuarios object_search = view.getListModel().get(selectedIndex);
        view.setObjectSearch(object_search);
        view.getListModel().clear();
        view.getSearchField().setText(null);
        view.updateScreenInfo();
    }

}
