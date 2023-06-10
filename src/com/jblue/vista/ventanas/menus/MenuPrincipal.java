/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas.menus;

import com.jblue.modelo.envoltorios.env.EnvPersonal;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.vista.ventanas.menus.cargos.MenuPresidente;
import com.jblue.vista.ventanas.menus.cargos.MenuTesoreria;
import com.jblue.sistema.Sesion;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.tiempo.Fecha;
import com.jblue.vista.normas.SuperVentana;
import com.jblue.vista.ventanas.Login;
import com.jblue.vista.ventanas.menus.ayuda.AcercaDe;
import com.jblue.vista.ventanas.menus.bd.MenuCalles;
import com.jblue.vista.ventanas.menus.bd.MenuTomas;
import com.jblue.vista.ventanas.menus.bd.MenuUsuarios;
import com.jblue.vista.ventanas.menus.bd.MenuTRegistradas;
import com.jblue.vista.ventanas.menus.administracion.MenuAdministrador;
import com.jblue.vista.ventanas.menus.administracion.MenuComprobantes;
import com.jblue.vista.ventanas.menus.cargos.MenuPerfil;
import com.jblue.vista.ventanas.menus.herramientas.MenuCVS;
import com.jblue.vista.ventanas.menus.administracion.MenuDirectorios;
import com.jblue.vista.ventanas.menus.administracion.MenuDocumentos;
import com.jblue.vista.ventanas.vistas.principal.VCobros;
import com.jblue.vista.ventanas.vistas.principal.VRecargos;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author jp
 */
public class MenuPrincipal extends SuperVentana {

    /**
     * Variable del login sel sistema, el cual se muestra si el sistema se
     * cerrado con los botones de cerrar o precionando la 'x'
     */
    private final Login login;
    // ---- Menus del sistema ---- //
    private final MenuTesoreria menuTesoreria;
    private final MenuPresidente menuPresidente;
    private final MenuPerfil menuPerfil;
    private final MenuAdministrador menuAdministrador;
    private final MenuComprobantes menuTargetas;
    private final MenuDocumentos menuDocumentos;
    private final MenuDirectorios menuDirectorios;
    // ---- Menus de la base de datos ---- //
    private final MenuUsuarios menuUsuarios;
    private final MenuCalles menuCalles;
    private final MenuTomas menuTipoTomas;
    private final MenuTRegistradas menuTomasRegistradas;
    // ---- Herramientas ---- //
    private final MenuCVS herrCSV;
    // ---- Vistas ---- //
    private final VCobros vistaCobros;
    private final VRecargos vistaRecargos;
    // ---- Agrupaciones ---- //
    private final JFrame[] menus;
    private final JPanel[] vistas;

    //
    private final Fecha fecha;

    //
    public MenuPrincipal(Login login) {
        this._TITULO = 2;
        this.login = login;
        //menus
        this.menuTesoreria = new MenuTesoreria();
        this.menuPresidente = new MenuPresidente();
        this.menuPerfil = new MenuPerfil();
        this.menuAdministrador = new MenuAdministrador();
        this.menuTargetas = new MenuComprobantes();
        this.menuDocumentos = new MenuDocumentos();
        this.menuDirectorios = new MenuDirectorios();
        //menus bd
        this.menuUsuarios = new MenuUsuarios();
        this.menuCalles = new MenuCalles();
        this.menuTipoTomas = new MenuTomas();
        this.menuTomasRegistradas = new MenuTRegistradas();
        //vistas
        vistaCobros = new VCobros();
        vistaRecargos = new VRecargos();
        this.vistas = new JPanel[]{
            vistaCobros, vistaRecargos
        };

        this.herrCSV = new MenuCVS();

        this.menus = new JFrame[]{
            menuTesoreria,
            menuPresidente,
            menuPerfil,
            menuAdministrador,
            menuTargetas,
            menuDocumentos,
            menuDirectorios,
            menuUsuarios,
            menuCalles,
            menuTipoTomas,
            menuTomasRegistradas,
            herrCSV
        };
        //
        fecha = new Fecha();
        //
        this.initComponents();
        llamable();
    }

    @Override
    protected final void llamable() {
        addComponentes();
        estadoFinal();
        estadoInicial();
        addEventos();
    }

    @Override
    public void estadoInicial() {
        OPersonal o = Sesion.getInstancia().getUsuario();
        if (EnvPersonal.isTesorero(o)) {
            itemTesoreria.setEnabled(true);
        }
        if (EnvPersonal.isPresidente(o)) {
            itemPresidente.setEnabled(true);
        }
        if (EnvPersonal.isAdministrador(o)) {
            itemTesoreria.setEnabled(true);
            itemPresidente.setEnabled(true);
            itemAdministrador.setEnabled(true);
        }
    }

    @Override
    protected void estadoFinal() {
        super.estadoFinal();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    protected void addEventos() {
        super.addEventos();
    }

    @Override
    protected void addComponentes() {
        for (int i = 0; i < vistas.length; i++) {
            JPanel menu = (JPanel) jTabbedPane1.getComponentAt(i + 1);
            menu.add(vistas[i], BorderLayout.CENTER);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpAreaPersonal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jbtPerfil = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jbtCobros = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jbtRecargos = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jbtSalir = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jpVisor = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        img = new javax.swing.JPanel();
        jpCobros = new javax.swing.JPanel();
        jpRecargos = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemPerfil = new javax.swing.JMenuItem();
        itemPresidente = new javax.swing.JMenuItem();
        itemTesoreria = new javax.swing.JMenuItem();
        subMenuDocs = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        itemDirUsuarios = new javax.swing.JMenuItem();
        itemDocsUsuarios = new javax.swing.JMenuItem();
        itemAdministrador = new javax.swing.JMenuItem();
        itemComprobantes = new javax.swing.JMenuItem();
        subMenuBD = new javax.swing.JMenu();
        itemBdTomasRegistradas = new javax.swing.JMenuItem();
        itemBdUsuarios = new javax.swing.JMenuItem();
        itemBdCalles = new javax.swing.JMenuItem();
        itemBdTipoTomas = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        csv_rapidos = new javax.swing.JMenuItem();
        pagos_del_dia = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(6);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1200, 675));

        jpAreaPersonal.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Areas de Trabajo");
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel1.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jbtPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/usuario.png"))); // NOI18N
        jbtPerfil.setText("Perfil");
        jbtPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPerfilActionPerformed(evt);
            }
        });
        jPanel7.add(jbtPerfil, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7);

        jPanel4.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jbtCobros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img5.png"))); // NOI18N
        jbtCobros.setText("Cobros");
        jbtCobros.setPreferredSize(new java.awt.Dimension(114, 50));
        jbtCobros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCobrosActionPerformed(evt);
            }
        });
        jPanel4.add(jbtCobros, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel3.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jbtRecargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/multa.png"))); // NOI18N
        jbtRecargos.setText("Recargos");
        jbtRecargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRecargosActionPerformed(evt);
            }
        });
        jPanel3.add(jbtRecargos, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel5.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel6.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jPanel6);

        jPanel8.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jPanel8);

        jPanel11.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel11.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jPanel11);

        jPanel10.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jPanel10);

        jPanel9.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jbtSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerrar-sesion.png"))); // NOI18N
        jbtSalir.setText("Salir");
        jbtSalir.setPreferredSize(new java.awt.Dimension(200, 50));
        jbtSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalirActionPerformed(evt);
            }
        });
        jPanel9.add(jbtSalir, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel9);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jpAreaPersonal.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jpVisor.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sistema de cobranza del servicio de agua potable");
        jLabel3.setPreferredSize(new java.awt.Dimension(323, 50));
        jpVisor.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        img.setLayout(new java.awt.BorderLayout());
        jpVisor.add(img, java.awt.BorderLayout.CENTER);

        jPanel12.add(jpVisor, java.awt.BorderLayout.CENTER);

        jpAreaPersonal.add(jPanel12, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Area Personal", jpAreaPersonal);

        jpCobros.setPreferredSize(new java.awt.Dimension(1200, 643));
        jpCobros.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Cobros", new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x16/salario.png")), jpCobros); // NOI18N

        jpRecargos.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Recargos", new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x16/pendiente.png")), jpRecargos); // NOI18N

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);
        jTabbedPane1.getAccessibleContext().setAccessibleName("tab_root");

        jMenuBar1.setPreferredSize(new java.awt.Dimension(1200, 25));

        jMenu1.setText("Menu");
        jMenu1.setToolTipText("Apartados principales del programa");

        itemPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/usuario(1).png"))); // NOI18N
        itemPerfil.setText("Perfil");
        itemPerfil.setToolTipText("Apartado para el control de datos del usuario en uso");
        itemPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPerfilActionPerformed(evt);
            }
        });
        jMenu1.add(itemPerfil);

        itemPresidente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/presidente.png"))); // NOI18N
        itemPresidente.setText("Presidente");
        itemPresidente.setEnabled(false);
        itemPresidente.setName("itemPresidente"); // NOI18N
        itemPresidente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPresidenteActionPerformed(evt);
            }
        });
        jMenu1.add(itemPresidente);

        itemTesoreria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cofre-del-tesoro.png"))); // NOI18N
        itemTesoreria.setText("Tesorero");
        itemTesoreria.setEnabled(false);
        itemTesoreria.setName("tesorero"); // NOI18N
        itemTesoreria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTesoreriaActionPerformed(evt);
            }
        });
        jMenu1.add(itemTesoreria);

        subMenuDocs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/grupo.png"))); // NOI18N
        subMenuDocs.setText("Administracion");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/confidencial.png"))); // NOI18N
        jMenu3.setText("Docs");

        itemDirUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/carpeta.png"))); // NOI18N
        itemDirUsuarios.setText("Directorios de Usuario");
        itemDirUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDirUsuariosActionPerformed(evt);
            }
        });
        jMenu3.add(itemDirUsuarios);

        itemDocsUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/documento.png"))); // NOI18N
        itemDocsUsuarios.setText("Documentos de Usuario");
        itemDocsUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDocsUsuariosActionPerformed(evt);
            }
        });
        jMenu3.add(itemDocsUsuarios);

        subMenuDocs.add(jMenu3);

        itemAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/perfil.png"))); // NOI18N
        itemAdministrador.setText("Administrador");
        itemAdministrador.setEnabled(false);
        itemAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAdministradorActionPerformed(evt);
            }
        });
        subMenuDocs.add(itemAdministrador);

        itemComprobantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/comprobado.png"))); // NOI18N
        itemComprobantes.setText("Comprobantes");
        itemComprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemComprobantesActionPerformed(evt);
            }
        });
        subMenuDocs.add(itemComprobantes);

        jMenu1.add(subMenuDocs);

        subMenuBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/base-de-datos(1).png"))); // NOI18N
        subMenuBD.setText("Base de datos");
        subMenuBD.setName("menu-bd"); // NOI18N

        itemBdTomasRegistradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/nota.png"))); // NOI18N
        itemBdTomasRegistradas.setText("Tomas Registradas");
        itemBdTomasRegistradas.setName("tomas -egistradas"); // NOI18N
        itemBdTomasRegistradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBdTomasRegistradasActionPerformed(evt);
            }
        });
        subMenuBD.add(itemBdTomasRegistradas);

        itemBdUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/grupo.png"))); // NOI18N
        itemBdUsuarios.setText("BD Usuarios");
        itemBdUsuarios.setName("usuarios"); // NOI18N
        itemBdUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBdUsuariosActionPerformed(evt);
            }
        });
        subMenuBD.add(itemBdUsuarios);

        itemBdCalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/mapa-de-la-calle.png"))); // NOI18N
        itemBdCalles.setText("BD Calles");
        itemBdCalles.setName("calles"); // NOI18N
        itemBdCalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBdCallesActionPerformed(evt);
            }
        });
        subMenuBD.add(itemBdCalles);

        itemBdTipoTomas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/grifo.png"))); // NOI18N
        itemBdTipoTomas.setText("BD Tipo de tomas");
        itemBdTipoTomas.setName("tipo-de-tomas"); // NOI18N
        itemBdTipoTomas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBdTipoTomasActionPerformed(evt);
            }
        });
        subMenuBD.add(itemBdTipoTomas);

        jMenu1.add(subMenuBD);
        jMenu1.add(jSeparator2);

        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cerrar-sesion(2).png"))); // NOI18N
        itemSalir.setText("Salir");
        itemSalir.setName("itemSalir"); // NOI18N
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Herramientas");

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/mesa.png"))); // NOI18N
        jMenu5.setText("Tablas");

        csv_rapidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/csv.png"))); // NOI18N
        csv_rapidos.setText("CSV Rapidos");
        csv_rapidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csv_rapidosActionPerformed(evt);
            }
        });
        jMenu5.add(csv_rapidos);

        pagos_del_dia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/csv.png"))); // NOI18N
        pagos_del_dia.setText("Pagos Del Dia");
        pagos_del_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagos_del_diaActionPerformed(evt);
            }
        });
        jMenu5.add(pagos_del_dia);

        jMenu2.add(jMenu5);

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

        jMenu2.add(jMenu6);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Ayuda");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/information.png"))); // NOI18N
        jMenuItem5.setText("Acerca de JBlue");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemBdUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBdUsuariosActionPerformed
        SwingUtilities.invokeLater(() -> menuUsuarios.setVisible(true));
    }//GEN-LAST:event_itemBdUsuariosActionPerformed

    private void itemBdCallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBdCallesActionPerformed
        SwingUtilities.invokeLater(() -> menuCalles.setVisible(true));
    }//GEN-LAST:event_itemBdCallesActionPerformed

    private void itemBdTipoTomasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBdTipoTomasActionPerformed
        SwingUtilities.invokeLater(() -> menuTipoTomas.setVisible(true));
    }//GEN-LAST:event_itemBdTipoTomasActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        vistaSeleccionada(0);
        dispose();
    }//GEN-LAST:event_itemSalirActionPerformed

    private void jbtCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCobrosActionPerformed
        vistaSeleccionada(1);
    }//GEN-LAST:event_jbtCobrosActionPerformed

    private void jbtRecargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRecargosActionPerformed
        vistaSeleccionada(2);
    }//GEN-LAST:event_jbtRecargosActionPerformed

    private void jbtSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalirActionPerformed
        vistaSeleccionada(0);
        dispose();
    }//GEN-LAST:event_jbtSalirActionPerformed

    private void itemPresidenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPresidenteActionPerformed
        SwingUtilities.invokeLater(() -> menuPresidente.setVisible(true));
    }//GEN-LAST:event_itemPresidenteActionPerformed

    private void itemBdTomasRegistradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBdTomasRegistradasActionPerformed
        SwingUtilities.invokeLater(() -> menuTomasRegistradas.setVisible(true));
    }//GEN-LAST:event_itemBdTomasRegistradasActionPerformed

    private void jbtPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPerfilActionPerformed
        SwingUtilities.invokeLater(() -> menuPerfil.setVisible(true));
    }//GEN-LAST:event_jbtPerfilActionPerformed

    private void csv_rapidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csv_rapidosActionPerformed
        SwingUtilities.invokeLater(() -> herrCSV.setVisible(true));
    }//GEN-LAST:event_csv_rapidosActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        SwingUtilities.invokeLater(() -> {
            AcercaDe ac = new AcercaDe();
            ac.setVisible(true);
        });
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void itemTesoreriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTesoreriaActionPerformed
        SwingUtilities.invokeLater(() -> menuTesoreria.setVisible(true));
    }//GEN-LAST:event_itemTesoreriaActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        StringBuilder s = new StringBuilder();
        s.append("Esta operacion puede pausar momentaneamente o finalizar el programa").append("\n");
        s.append("¿Desea continuar?");
        int input = JOptionPane.showConfirmDialog(this, s.toString());
        if (input != JOptionPane.YES_OPTION) {
            return;
        }
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void itemPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPerfilActionPerformed
        SwingUtilities.invokeLater(() -> {
            menuPerfil.setVisible(true);
        });
    }//GEN-LAST:event_itemPerfilActionPerformed

    private void itemAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAdministradorActionPerformed
        SwingUtilities.invokeLater(() -> {
            menuAdministrador.setVisible(true);
        });
    }//GEN-LAST:event_itemAdministradorActionPerformed

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

    private void pagos_del_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagos_del_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pagos_del_diaActionPerformed

    private void itemComprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemComprobantesActionPerformed

        menuTargetas.setVisible(true);

    }//GEN-LAST:event_itemComprobantesActionPerformed

    private void itemDocsUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDocsUsuariosActionPerformed
        menuDocumentos.setVisible(true);
    }//GEN-LAST:event_itemDocsUsuariosActionPerformed

    private void itemDirUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDirUsuariosActionPerformed
        menuDirectorios.setVisible(true);
    }//GEN-LAST:event_itemDirUsuariosActionPerformed

    public void vistaSeleccionada(int i) {
        SwingUtilities.invokeLater(() -> jTabbedPane1.setSelectedIndex(i));
    }

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

// <editor-fold defaultstate="collapsed" desc="Variables De Interfaz">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem csv_rapidos;
    private javax.swing.JPanel img;
    private javax.swing.JMenuItem itemAdministrador;
    private javax.swing.JMenuItem itemBdCalles;
    private javax.swing.JMenuItem itemBdTipoTomas;
    private javax.swing.JMenuItem itemBdTomasRegistradas;
    private javax.swing.JMenuItem itemBdUsuarios;
    private javax.swing.JMenuItem itemComprobantes;
    private javax.swing.JMenuItem itemDirUsuarios;
    private javax.swing.JMenuItem itemDocsUsuarios;
    private javax.swing.JMenuItem itemPerfil;
    private javax.swing.JMenuItem itemPresidente;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemTesoreria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtCobros;
    private javax.swing.JButton jbtPerfil;
    private javax.swing.JButton jbtRecargos;
    private javax.swing.JButton jbtSalir;
    private javax.swing.JPanel jpAreaPersonal;
    private javax.swing.JPanel jpCobros;
    private javax.swing.JPanel jpRecargos;
    private javax.swing.JPanel jpVisor;
    private javax.swing.JMenuItem pagos_del_dia;
    private javax.swing.JMenu subMenuBD;
    private javax.swing.JMenu subMenuDocs;
    // End of variables declaration//GEN-END:variables

// </editor-fold>
    
    @Override
    public Rectangle getBounds() {
        vistaCobros.getBounds();
        return super.getBounds(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void dispose() {
        finalizarSesion();
        super.dispose();
        irLogin();
    }
}

class visor extends JPanel {

    int sizeX, sizeY;

    public visor(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon o = new ImageIcon(getClass().getResource("/com/jblue/media/img/Fondo.svg.png"));
        g.drawImage(o.getImage(), 0, 0, sizeX, sizeY, this);
    }

}
