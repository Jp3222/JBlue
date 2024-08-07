/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas;

import com.jblue.modelo.bdconexion.env.EnvPersonal;
import com.jblue.sistema.Sesion;
import com.jblue.util.fabricas.FabricaCache;
import com.jblue.vista.componentes.CRecuperarContraseña;
import com.jblue.vista.marco.contruccion.ConstTitutlos;
import com.jblue.vista.marco.ventanas.VentanaSimple;
import com.jblue.vista.ventanas.herramientas.MenuCVSExport;
import com.jblue.vista.ventanas.herramientas.MenuDirectorios;
import com.jblue.vista.ventanas.herramientas.MenuDocumentos;
import com.jblue.vista.vistas.menuprincipal.VCobros;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author jp
 */
public class MenuPrincipal extends VentanaSimple {

    public MenuPrincipal(Login login) {
        super(ConstTitutlos.TL_MENU_PRINCIPAL, ConstTitutlos.TL_VENTANAS);
        setUndecorated(true);
        setDefaultLookAndFeelDecorated(false);

        this.initComponents();

        menus_bd = new MenuBD();
        menu_cargos = new MenuCargos();
        //
        //
        this.login = login;
        //
        this.herr_doc = new MenuDocumentos();
        this.herr_dir = new MenuDirectorios();
        //
        vista_cobros = new VCobros();
        this.herr_csv_rap = new MenuCVSExport();
        //
        this.menu_acerca_de = new AcercaDe();
        this.menus = new JFrame[]{
            herr_doc,
            herr_dir,
            herr_csv_rap,
            menus_bd,
            menu_cargos,
            menu_acerca_de
        };
        llamable();
    }

    @Override
    protected final void llamable() {
        construirComponentes();
        componentesEstadoFinal();
        componentesEstadoInicial();
        manejoEventos();
    }

    @Override
    public void componentesEstadoInicial() {

    }

    @Override
    protected void componentesEstadoFinal() {
        super.componentesEstadoFinal();
        accesoUsuario();
    }

    @Override
    protected void construirComponentes() {
        JPanel panel = (JPanel) getContentPane();
        panel.add(vista_cobros, BorderLayout.CENTER);
    }

    @Override
    protected void manejoEventos() {

        addAcLis(e -> evtMostrarMenu((JMenuItem) e.getSource()),
                item_perfil,
                item_presidente,
                item_tesorero,
                item_administrador,
                item_comprobantes,
                item_salir
        );

        addAcLis(e -> evtMostrarBD((JMenuItem) e.getSource()),
                item_config,
                item_usuarios,
                item_Calles,
                item_tipo_tomas,
                item_tomas_reg,
                item_pagos_x_otros_tipos
        );

        addAcLis(e -> evtMostrarHerr((JMenuItem) e.getSource()),
                item_csv_rap,
                item_docs,
                item_dir
        );
    }

    private void addAcLis(ActionListener evt, JMenuItem... items) {
        for (JMenuItem i : items) {
            i.addActionListener(evt);
        }
    }

    private void evtMostrarBD(JMenuItem o) {
        if (o == item_usuarios) {
            menus_bd.showUsuarios();
        } else if (o == item_Calles) {
            menus_bd.showCalles();
        } else if (o == item_tipo_tomas) {
            menus_bd.showTipoTomas();
        } else if (o == item_config) {
            menus_bd.showConfiguraciones();
        } else if (o == item_pagos_x_otros_tipos) {
            menus_bd.showPagosXOtros();
        } else {
            JOptionPane.showMessageDialog(this, String.format(FMT_MEN_D, o.getText()));
            return;
        }
        SwingUtilities.invokeLater(() -> menus_bd.setVisible(true));
    }

    private void evtMostrarHerr(JMenuItem o) {
        final JFrame show;
        if (o == item_dir) {
            show = herr_dir;
        } else if (o == item_docs) {
            show = herr_doc;
        } else if (o == item_csv_rap) {
            show = herr_csv_rap;
        } else {
            JOptionPane.showMessageDialog(this, String.format(FMT_MEN_D, o.getText()));
            return;
        }
        SwingUtilities.invokeLater(() -> show.setVisible(true));
    }

    private void evtMostrarMenu(JMenuItem o) {
        if (o == item_perfil) {
            menu_cargos.showPerfil();
        } else if (o == item_presidente) {
            menu_cargos.showPresidente();
        } else if (o == item_tesorero) {
            menu_cargos.showTesorero();
        } else if (o == item_administrador) {
            menu_cargos.showAdministrador();
        } else if (o == item_salir) {
            dispose();
            return;
        } else {
            JOptionPane.showMessageDialog(this, String.format(FMT_MEN_D, o.getText()));
            return;
        }
        menu_cargos.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu_en_barra = new javax.swing.JMenuBar();
        menu_principal = new javax.swing.JMenu();
        item_perfil = new javax.swing.JMenuItem();
        item_presidente = new javax.swing.JMenuItem();
        item_tesorero = new javax.swing.JMenuItem();
        item_administrador = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        item_salir = new javax.swing.JMenuItem();
        menu_bd = new javax.swing.JMenu();
        item_config = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        item_tomas_reg = new javax.swing.JMenuItem();
        item_usuarios = new javax.swing.JMenuItem();
        item_Calles = new javax.swing.JMenuItem();
        item_tipo_tomas = new javax.swing.JMenuItem();
        item_pagos_x_otros_tipos = new javax.swing.JMenuItem();
        menu_herramientas = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        item_comprobantes = new javax.swing.JMenuItem();
        item_dir = new javax.swing.JMenuItem();
        item_docs = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        item_csv_rap = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        pagos_del_dia = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        menu_ayuda = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(600, 350));
        setPreferredSize(new java.awt.Dimension(1200, 700));

        menu_en_barra.setMinimumSize(new java.awt.Dimension(344, 20));
        menu_en_barra.setPreferredSize(new java.awt.Dimension(1200, 30));

        menu_principal.setText("Menu");
        menu_principal.setToolTipText("Apartados principales del programa");

        item_perfil.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/usuario(1).png"))); // NOI18N
        item_perfil.setText("Perfil");
        item_perfil.setToolTipText("Apartado para el control de datos del usuario en uso");
        item_perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_perfilActionPerformed(evt);
            }
        });
        menu_principal.add(item_perfil);

        item_presidente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_presidente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/presidente.png"))); // NOI18N
        item_presidente.setText("Presidente");
        item_presidente.setName("item_presidente"); // NOI18N
        menu_principal.add(item_presidente);

        item_tesorero.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_tesorero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cofre-del-tesoro.png"))); // NOI18N
        item_tesorero.setText("Tesorero");
        item_tesorero.setName("tesorero"); // NOI18N
        item_tesorero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_tesoreroActionPerformed(evt);
            }
        });
        menu_principal.add(item_tesorero);

        item_administrador.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_administrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/perfil.png"))); // NOI18N
        item_administrador.setText("Administrador");
        menu_principal.add(item_administrador);
        menu_principal.add(jSeparator2);

        item_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cerrar-sesion(2).png"))); // NOI18N
        item_salir.setText("Salir");
        item_salir.setName("item_salir"); // NOI18N
        menu_principal.add(item_salir);

        menu_en_barra.add(menu_principal);

        menu_bd.setText("Base de datos");
        menu_bd.setName("menu-bd"); // NOI18N

        item_config.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/configuraciones.png"))); // NOI18N
        item_config.setText("Configuraciones");
        menu_bd.add(item_config);
        menu_bd.add(jSeparator1);

        item_tomas_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/nota.png"))); // NOI18N
        item_tomas_reg.setText("Tomas Registradas");
        item_tomas_reg.setName("tomas -egistradas"); // NOI18N
        menu_bd.add(item_tomas_reg);

        item_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/grupo.png"))); // NOI18N
        item_usuarios.setText("BD Usuarios");
        item_usuarios.setName("usuarios"); // NOI18N
        menu_bd.add(item_usuarios);

        item_Calles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/mapa-de-la-calle.png"))); // NOI18N
        item_Calles.setText("BD Calles");
        item_Calles.setName("calles"); // NOI18N
        menu_bd.add(item_Calles);

        item_tipo_tomas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/grifo.png"))); // NOI18N
        item_tipo_tomas.setText("BD Tipo de tomas");
        item_tipo_tomas.setName("tipo-de-tomas"); // NOI18N
        menu_bd.add(item_tipo_tomas);

        item_pagos_x_otros_tipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/factura.png"))); // NOI18N
        item_pagos_x_otros_tipos.setText("BD Tipos de pagos");
        menu_bd.add(item_pagos_x_otros_tipos);

        menu_en_barra.add(menu_bd);

        menu_herramientas.setText("Herramientas");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/bloqueado.png"))); // NOI18N
        jMenuItem2.setText("Bloquear Caja");
        menu_herramientas.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/desbloquear.png"))); // NOI18N
        jMenuItem3.setText("Desbloquear Caja");
        menu_herramientas.add(jMenuItem3);

        jMenuItem4.setText("Recuperar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menu_herramientas.add(jMenuItem4);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/confidencial.png"))); // NOI18N
        jMenu3.setText("Docs");

        item_comprobantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/comprobado.png"))); // NOI18N
        item_comprobantes.setText("Comprobantes");
        jMenu3.add(item_comprobantes);

        item_dir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/carpeta.png"))); // NOI18N
        item_dir.setText("Directorios de Usuario");
        item_dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_dirActionPerformed(evt);
            }
        });
        jMenu3.add(item_dir);

        item_docs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/documento.png"))); // NOI18N
        item_docs.setText("Documentos de Usuario");
        item_docs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_docsActionPerformed(evt);
            }
        });
        jMenu3.add(item_docs);

        menu_herramientas.add(jMenu3);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/mesa.png"))); // NOI18N
        jMenu5.setText("Tablas");

        item_csv_rap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/csv.png"))); // NOI18N
        item_csv_rap.setText("CSV Rapidos");
        item_csv_rap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_csv_rapActionPerformed(evt);
            }
        });
        jMenu5.add(item_csv_rap);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/csv.png"))); // NOI18N
        jMenuItem1.setText("Importar CSV");
        jMenu5.add(jMenuItem1);

        pagos_del_dia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/csv.png"))); // NOI18N
        pagos_del_dia.setText("Pagos Del Dia");
        jMenu5.add(pagos_del_dia);

        menu_herramientas.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/desarrollo-de-software.png"))); // NOI18N
        jMenu6.setText("Software");

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/configuraciones.png"))); // NOI18N
        jMenuItem6.setText("GC");
        jMenuItem6.setToolTipText("Recolecor de basura");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/configuraciones.png"))); // NOI18N
        jMenuItem7.setText("RC");
        jMenuItem7.setToolTipText("Reiniciar Cache");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        menu_herramientas.add(jMenu6);

        menu_en_barra.add(menu_herramientas);

        menu_ayuda.setText("Ayuda");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/information.png"))); // NOI18N
        jMenuItem5.setText("Acerca de JBlue");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menu_ayuda.add(jMenuItem5);

        menu_en_barra.add(menu_ayuda);

        setJMenuBar(menu_en_barra);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void item_csv_rapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_csv_rapActionPerformed
        SwingUtilities.invokeLater(() -> herr_csv_rap.setVisible(true));
    }//GEN-LAST:event_item_csv_rapActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        SwingUtilities.invokeLater(() -> {

            menu_acerca_de.setVisible(true);
        });
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        StringBuilder s = new StringBuilder();
        s.append("Esta operacion puede pausar momentaneamente o finalizar el programa")
                .append("\n")
                .append("¿Desea continuar?");
        int input = JOptionPane.showConfirmDialog(this, s.toString());
        if (input != JOptionPane.YES_OPTION) {
            return;
        }
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        StringBuilder s = new StringBuilder();
        s.append("Esta operacion puede pausar momentaneamente o finalizar el programa").append("\n");
        s.append("¿Desea continuar?");
        int input = JOptionPane.showConfirmDialog(this, s.toString());
        if (input != JOptionPane.YES_OPTION) {
            return;
        }
        FabricaCache.MC_CALLES.actualizar();
        FabricaCache.MC_PERSONAL.actualizar();
        FabricaCache.MC_TIPOS_DE_TOMAS.actualizar();
        FabricaCache.MC_USUARIOS.actualizar();

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void item_docsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_docsActionPerformed
        herr_doc.setVisible(true);
    }//GEN-LAST:event_item_docsActionPerformed

    private void item_dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_dirActionPerformed
        herr_dir.setVisible(true);
    }//GEN-LAST:event_item_dirActionPerformed

    private void item_perfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_perfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_perfilActionPerformed

    private void item_tesoreroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_tesoreroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_tesoreroActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new CRecuperarContraseña(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public void finalizarSesion() {
        cerrarMenusActivos();
        Sesion instancia = Sesion.getInstancia();
        boolean finSesion = instancia.finSesion();
        if (!finSesion) {
            JOptionPane.showMessageDialog(this, "ERROR AL REGISTRAR FIN DE SESION");
        }
    }

    private void irLogin() {
        SwingUtilities.invokeLater(() -> {
            login.setVisible(true);
            login.limpiarInstancia();
        });
    }

    private void cerrarMenusActivos() {
        JFrame[] arr = menus;
        for (JFrame o : arr) {
            if (!o.isVisible() && !o.isActive()) {
                continue;
            }
            o.setVisible(false);
            o.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem item_Calles;
    private javax.swing.JMenuItem item_administrador;
    private javax.swing.JMenuItem item_comprobantes;
    private javax.swing.JMenuItem item_config;
    private javax.swing.JMenuItem item_csv_rap;
    private javax.swing.JMenuItem item_dir;
    private javax.swing.JMenuItem item_docs;
    private javax.swing.JMenuItem item_pagos_x_otros_tipos;
    private javax.swing.JMenuItem item_perfil;
    private javax.swing.JMenuItem item_presidente;
    private javax.swing.JMenuItem item_salir;
    private javax.swing.JMenuItem item_tesorero;
    private javax.swing.JMenuItem item_tipo_tomas;
    private javax.swing.JMenuItem item_tomas_reg;
    private javax.swing.JMenuItem item_usuarios;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu menu_ayuda;
    private javax.swing.JMenu menu_bd;
    private javax.swing.JMenuBar menu_en_barra;
    private javax.swing.JMenu menu_herramientas;
    private javax.swing.JMenu menu_principal;
    private javax.swing.JMenuItem pagos_del_dia;
    // End of variables declaration//GEN-END:variables
    private final Login login;

    // ---- Herramientas ---- //
    private final MenuCVSExport herr_csv_rap;
    private final MenuDocumentos herr_doc;
    private final MenuDirectorios herr_dir;
    private final AcercaDe menu_acerca_de;

    // ---- Vistas ---- //
    private final VCobros vista_cobros;
    // ---- Agrupaciones ---- //
    private final JFrame[] menus;
    //
    private final MenuBD menus_bd;
    private final MenuCargos menu_cargos;
    //
    private final String FMT_MEN_D = "El apartado '%s' aun esta en desarrollo";

    @Override
    public void dispose() {
        finalizarSesion();
        super.dispose();
        irLogin();
    }

    private void accesoUsuario() {
//        JMenuItem[] cargos = {
//            item_presidente, item_tesorero, item_administrador
//        };
//        String cargo = EnvPersonal.getCargoString(Sesion.getInstancia().getUsuario());
//        for (JMenuItem i : cargos) {
//            i.setEnabled(i.getText().equalsIgnoreCase(cargo));
//        }
    }
}
