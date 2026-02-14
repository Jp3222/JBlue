/*
 * Copyright (C) 2025 juanp
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
package jsoftware.com.jblue.views.process;

import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jsoftware.com.jblue.controllers.viewc.OwnerRegisterProcessController;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.framework.AbstractProcessView;
import jsoftware.com.jblue.views.framework.ProcessViewBuilder;
import jsoftware.com.jblue.views.proviews.PaymentProcessView;
import jsoftware.com.jblue.views.proviews.UserRegisterView;
import jsoftware.com.jblue.views.proviews.ValidationProcessView;
import jsoftware.com.jblue.views.proviews.WaterIntakeDataView;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public final class OwnerRegisterProcessView extends AbstractProcessView<ProcessWrapperDTO> {

    private static final long serialVersionUID = 1L;

    // Arreglo polimórfico para gestión escalable de pasos
    private final AbstractProcessView[] views;

    // Sub-vistas (Componentes del Wizard)
    private final UserRegisterView user_view;
    private final ValidationProcessView validation_process_view;
    private final PaymentProcessView payment_concepts_view;
    private final WaterIntakeDataView water_intake_view;

    private int current_view;
    private CardLayout ly;
    private OwnerRegisterProcessController controller;

    public OwnerRegisterProcessView(ProcessViewBuilder builder) throws Exception {
        super(builder);
        // 1. Instanciación de sub-vistas compartiendo el builder (y el DTO)
        this.user_view = new UserRegisterView(builder);
        this.validation_process_view = new ValidationProcessView(builder);
        this.payment_concepts_view = new PaymentProcessView(builder);
        this.water_intake_view = new WaterIntakeDataView(builder);
        // 2. Organización en arreglo para navegación polimórfica
        this.views = new AbstractProcessView[]{
            user_view,
            validation_process_view,
            water_intake_view,
            payment_concepts_view
        };
        this.current_view = 0;
        initComponents();
        build();
        controller = new OwnerRegisterProcessController(this);

    }

    @Override
    public void build() {
        components();
        events();
        finalState();
        initialState();
    }

    @Override
    public void components() {
        this.ly = (CardLayout) root_panel.getLayout();

        // Agregamos las vistas al CardLayout usando su nombre como identificador
        for (AbstractProcessView view : views) {
            this.root_panel.add(view, view.getName());
        }
    }

    @Override
    public void events() {
        // Evento SIGUIENTE / FINALIZAR
        next_panel_button.addActionListener((e) -> {
            getDataView(); // Sincroniza vista -> modelo

            boolean isValid = switch (current_view) {
                case 0 ->
                    getProcessWrapper().isUser_valid();
                case 1 ->
                    getProcessWrapper().isDocument_list_valid();
                case 2 ->
                    getProcessWrapper().isWater_intake_valid(); // Ajustar según tu orden
                default ->
                    true;
            };
            if (!isValid) {
                JOptionPane.showMessageDialog(root_panel,
                        "Por favor, complete correctamente los campos obligatorios de esta sección.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (current_view < views.length - 1) {
                ly.next(root_panel);
                current_view++;
                updateNavigationUI();
            } else {
                executeFinalSave();
            }
        });

        // Evento REGRESAR
        last_panel_button.addActionListener((e) -> {
            if (current_view > 0) {
                ly.previous(root_panel);
                current_view--;
                updateNavigationUI();
            }
        });

        // Botón CANCELAR (search_object según tu initComponents)
        save_process_button.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de cancelar el trámite actual? Se perderán los datos no guardados.",
                    "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Lógica para cerrar o resetear la vista
                this.setVisible(false);
            }
        });
    }

    /**
     * Sincroniza el estado de los botones con el paso actual del proceso.
     */
    private void updateNavigationUI() {
        last_panel_button.setEnabled(current_view > 0);
        if (current_view == views.length - 1) {
            next_panel_button.setText("Finalizar Registro");
            next_panel_button.setActionCommand("save");
        } else {
            next_panel_button.setText("Siguiente");
            next_panel_button.setActionCommand("next_view");

        }
    }

    /**
     * Recolecta polimórficamente los datos de la vista activa.
     */
    @Override
    public void getDataView() {
        try {
            if (current_view >= 0 && current_view < views.length) {
                views[current_view].getDataView();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al capturar datos: " + e.getMessage());
            log(e, "getDataView");
        }
    }

    /**
     * Método encargado de invocar al Service para persistir toda la
     * información.
     */
    private void executeFinalSave() {
        for (AbstractProcessView v : views) {
            v.getDataView();
        }
        ProcessWrapperDTO pw = getProcessWrapper();
        // 2. Aquí llamarías a tu Service: userService.save(...)
        JOptionPane.showMessageDialog(this, "Iniciando persistencia de datos en base de datos...");
        //controller.save();
        
    }

    @Override
    public void initialState() {
        current_view = 0;
        ly.show(root_panel, user_view.getName());
        updateNavigationUI();
        next_panel_button.setEnabled(true);
        last_panel_button.setEnabled(false);
    }

    @Override
    public void finalState() {
        // Bloquear controles tras un guardado exitoso
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        north_panel = new javax.swing.JPanel();
        np_cp_center = new javax.swing.JPanel();
        last_panel_button = new javax.swing.JButton();
        next_panel_button = new javax.swing.JButton();
        np_cp_west = new javax.swing.JPanel();
        cancel_process_button = new javax.swing.JButton();
        np_cp_east = new javax.swing.JPanel();
        save_process_button = new javax.swing.JButton();
        root_panel = new javax.swing.JPanel();

        setName("Registro de titular"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 300));
        setLayout(new java.awt.BorderLayout());

        north_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        north_panel.setLayout(new java.awt.BorderLayout(10, 10));

        np_cp_center.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        last_panel_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        last_panel_button.setText("Regresar");
        last_panel_button.setToolTipText("Seccion Anterior");
        last_panel_button.setActionCommand("back_view");
        np_cp_center.add(last_panel_button);

        next_panel_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        next_panel_button.setText("Siguiente");
        next_panel_button.setToolTipText("Siguiente Seccion");
        next_panel_button.setActionCommand("next_view");
        np_cp_center.add(next_panel_button);

        north_panel.add(np_cp_center, java.awt.BorderLayout.CENTER);

        np_cp_west.setPreferredSize(new java.awt.Dimension(100, 30));
        np_cp_west.setLayout(new java.awt.BorderLayout());

        cancel_process_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        cancel_process_button.setToolTipText("Cancelar Tramite");
        cancel_process_button.setActionCommand("search_object");
        np_cp_west.add(cancel_process_button, java.awt.BorderLayout.CENTER);

        north_panel.add(np_cp_west, java.awt.BorderLayout.WEST);

        np_cp_east.setPreferredSize(new java.awt.Dimension(100, 30));
        np_cp_east.setLayout(new java.awt.BorderLayout());

        save_process_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/configuraciones.png"))); // NOI18N
        save_process_button.setToolTipText("Guardar Informacion Actual");
        np_cp_east.add(save_process_button, java.awt.BorderLayout.CENTER);

        north_panel.add(np_cp_east, java.awt.BorderLayout.EAST);

        add(north_panel, java.awt.BorderLayout.NORTH);

        root_panel.setLayout(new java.awt.CardLayout());
        add(root_panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_process_button;
    private javax.swing.JButton last_panel_button;
    private javax.swing.JButton next_panel_button;
    private javax.swing.JPanel north_panel;
    private javax.swing.JPanel np_cp_center;
    private javax.swing.JPanel np_cp_east;
    private javax.swing.JPanel np_cp_west;
    private javax.swing.JPanel root_panel;
    private javax.swing.JButton save_process_button;
    // End of variables declaration//GEN-END:variables

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, getProcessTypeName(), method_name, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public int getCurrent_view() {
        return current_view;
    }

    public CardLayout getLy() {
        return ly;
    }

    public JPanel getRoot_panel() {
        return root_panel;
    }

    public AbstractProcessView[] getViews() {
        return views;
    }

}
