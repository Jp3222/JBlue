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
import java.math.BigDecimal;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.wrp.ShopCartWrapperDTO;
import jsoftware.com.jblue.model.events.SearchUserTyped;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.ShopCarService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.components.ObjectSearchComponent;
import jsoftware.com.jblue.views.framework.AbstractModuleView;
import jsoftware.com.jblue.views.mod.com.ShopCartView;
import jsoftware.com.jblue.views.mod.pro.ShoppingCartProcess;
import jsoftware.com.jpaymentlib.model.dto.PaymentDTO;
import jsoftware.com.jpaymentlib.model.dto.PaymentHeaderDTO;
import jsoftware.com.jpaymentlib.model.dto.wrp.PaymentWrapper;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juan pablo campos casasanero
 */
public class ShopCartController extends AbstractDBViewController {

    private static final long serialVersionUID = 1L;

    private ShopCarService service;
    private ShoppingCartProcess view;
    private SearchUserTyped event;
    private Timer throttleTimer;

    public ShopCartController(ShoppingCartProcess view) {
        this.view = view;
    }

    public ShopCartController() {
        this.service = new ShopCarService(false, ShopCartController.class.getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "cancel" ->
                cancel();
            case "search_user_button" ->
                searchUser();
            case "load_payment_concept" ->
                quote();
            case "payment_register" ->
                payment();
            default -> {
            }
        }
    }

    public void quote() {
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection();) {
            ShopCartWrapperDTO wrp = view.getDtoWrapper();
            boolean res = service.calculation(connection, SystemSession.getInstancia(), wrp);
            if (!res || service.isError()) {
                JOptionPane.showMessageDialog(view, service.getUserMessage());
                return;
            }
            view.showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Override
    public void cancel() {
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

    public void rmessage(boolean op) {
        String status = op ? "Exitoso" : "Erroneo";
        JOptionPane.showMessageDialog(view,
                "Operacion %s".formatted(status),
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);
        if (op) {
            view.initialState();
        }
    }

    public void setPaymentsInfo(UserDTO user) {

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
        AbstractModuleView<ShopCartWrapperDTO> module = view.getViews().get(0);
        if (module instanceof ShopCartView j) {
            j.getDtoWrapper()
                    .getWki_user()
                    .getMap()
                    .put("user_name", j.getTextSearchList());
            throttleTimer.restart();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            event((ShopCartView) view.getViews().get(0));
        }
    }

    private void event(ShopCartView view) {
        JList<WaterIntakeUserDTO> list = view.getList();
        if (list.getSelectedIndex() < 0 && list.getSelectedIndex() >= list.getModel().getSize()) {
            return;
        }
        WaterIntakeUserDTO get = view.getListModel().get(list.getSelectedIndex());
        ShopCartWrapperDTO wrp = view.getDtoWrapper();
        wrp.getWki_user().setMap(get.getMap());
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection();) {
            boolean res = service.getUser(connection, SystemSession.getInstancia(), wrp);
            if (!res || service.isError()) {
                JOptionPane.showMessageDialog(view, service.getUserMessage());
                return;
            }
            view.showData();
            view.getListModel().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setView(ShoppingCartProcess view) {
        this.view = view;
    }

    public void setEventObject(ShopCartView o) {
        this.event = new SearchUserTyped(service);
        this.event.setView(o);
        this.throttleTimer = new Timer(300, event);
        this.throttleTimer.setRepeats(false);
    }

    private void payment() {
        int option = JOptionPane.showConfirmDialog(
                view,
                "¿CONFIRMAR EL PAGO?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION // Evita que exista el botón Cancelar ambiguo
        );

        // Si el usuario cierra la ventana (CLOSED_OPTION), abortamos sin abrir conexión
        if (option == JOptionPane.CLOSED_OPTION) {
            return;
        }

        BigDecimal amountReceived = BigDecimal.ZERO;

        if (option == JOptionPane.YES_OPTION) {
            String input = JOptionPane.showInputDialog(view, "DINERO INGRESADO");

            // Si el usuario cancela el input o lo deja en blanco, abortamos
            if (input == null || input.trim().isEmpty()) {
                return;
            }

            try {
                // Limpiamos espacios y reemplazamos comas por puntos por si acaso
                String cleanAmount = input.trim().replace(",", ".");
                amountReceived = new BigDecimal(cleanAmount);

                if (amountReceived.compareTo(BigDecimal.ZERO) < 0) {
                    JOptionPane.showMessageDialog(view, "El monto no puede ser negativo.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Monto inválido: '" + input + "'. Ingresa solo números.", "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        // Abrimos la conexión solo hasta que los datos de entrada ya son seguros
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection()) {
            ShopCartWrapperDTO wrp = view.getDtoWrapper();

            PaymentWrapper pym_wrp = wrp.getPym_wrp();
            pym_wrp.setPaid(option == JOptionPane.YES_OPTION);
            pym_wrp.setPayment_line(option != JOptionPane.YES_OPTION);

            PaymentHeaderDTO header = pym_wrp.getHeader();
            header.setAmount_received(amountReceived);

            PaymentDTO payment = pym_wrp.getPayment();
            if (pym_wrp.isPaid()) {
                payment.setPayment_method_id(1);
                payment.setStatus(7);
                header.setChange_returned(header.getAmount_received().subtract(header.getTotal_amount()));
            } else {
                payment.setPayment_method_id(6);
                payment.setStatus(8);
                header.setChange_returned(BigDecimal.ZERO);
            }
            boolean res = service.payment(connection, SystemSession.getInstancia(), wrp);
            if (!res || service.isError()) {
                JOptionPane.showMessageDialog(view, service.getUserMessage());
                return;
            }
            view.showData();
            if (res) {
                returnMessage(view, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
