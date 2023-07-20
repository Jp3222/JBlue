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
import com.jblue.vista.normas.SuperVentana;
import com.jblue.vista.ventanas.Login;
import com.jblue.vista.ventanas.componentes.CVisorUsuario;
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
import java.awt.Image;
import java.awt.Rectangle;
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
    //--//
    private final CVisorUsuario CVisorUsuario;
    // ---- Herramientas ---- //
    private final MenuCVS herrCSV;
    // ---- Vistas ---- //
    private final VCobros vistaCobros;
    private final VRecargos vistaRecargos;
    // ---- Agrupaciones ---- //
    private final JFrame[] menus;
    private final JPanel[] vistas;

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
        //
        CVisorUsuario = new CVisorUsuario(this, true);
        //vistas
        vistaCobros = new VCobros();
        vistaRecargos = new VRecargos();
        //
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
        //visor v = new visor();
        //img.add(v);
        for (int i = 0; i < vistas.length; i++) {
            JPanel menu = (JPanel) tab_root.getComponentAt(i + 1);
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

        tab_root = new javax.swing.JTabbedPane();
        panel_area_personal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panel_lat_opciones = new javax.swing.JPanel();
        perfil = new javax.swing.JButton();
        cobros = new javax.swing.JButton();
        recargos = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jpVisor = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        img = new javax.swing.JPanel();
        panel_cobros = new javax.swing.JPanel();
        panel_recargos = new javax.swing.JPanel();
        menu_en_barra = new javax.swing.JMenuBar();
        menu_principal = new javax.swing.JMenu();
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
        menu_herramientas = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        csv_rapidos = new javax.swing.JMenuItem();
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
        setState(6);

        tab_root.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tab_root.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tab_root.setPreferredSize(new java.awt.Dimension(1200, 675));

        panel_area_personal.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Areas de Trabajo");
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel1.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        panel_lat_opciones.setLayout(new java.awt.GridLayout(10, 1));

        perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/usuario.png"))); // NOI18N
        perfil.setText("Perfil");
        perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilActionPerformed(evt);
            }
        });
        panel_lat_opciones.add(perfil);

        cobros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img5.png"))); // NOI18N
        cobros.setText("Cobros");
        cobros.setPreferredSize(new java.awt.Dimension(114, 50));
        cobros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobrosActionPerformed(evt);
            }
        });
        panel_lat_opciones.add(cobros);

        recargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/multa.png"))); // NOI18N
        recargos.setText("Recargos");
        recargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargosActionPerformed(evt);
            }
        });
        panel_lat_opciones.add(recargos);

        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerrar-sesion.png"))); // NOI18N
        salir.setText("Salir");
        salir.setPreferredSize(new java.awt.Dimension(200, 50));
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        panel_lat_opciones.add(salir);

        jPanel1.add(panel_lat_opciones, java.awt.BorderLayout.CENTER);

        panel_area_personal.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel12.setPreferredSize(new java.awt.Dimension(900, 600));
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

        panel_area_personal.add(jPanel12, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Area Personal", panel_area_personal);

        panel_cobros.setPreferredSize(new java.awt.Dimension(1200, 643));
        panel_cobros.setLayout(new java.awt.BorderLayout());
        tab_root.addTab("Cobros", new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x16/salario.png")), panel_cobros); // NOI18N

        panel_recargos.setLayout(new java.awt.BorderLayout());
        tab_root.addTab("Recargos", new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x16/pendiente.png")), panel_recargos); // NOI18N

        getContentPane().add(tab_root, java.awt.BorderLayout.PAGE_START);
        tab_root.getAccessibleContext().setAccessibleName("tab_root");

        menu_en_barra.setPreferredSize(new java.awt.Dimension(1200, 25));

        menu_principal.setText("Menu");
        menu_principal.setToolTipText("Apartados principales del programa");

        itemPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/usuario(1).png"))); // NOI18N
        itemPerfil.setText("Perfil");
        itemPerfil.setToolTipText("Apartado para el control de datos del usuario en uso");
        itemPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPerfilActionPerformed(evt);
            }
        });
        menu_principal.add(itemPerfil);

        itemPresidente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/presidente.png"))); // NOI18N
        itemPresidente.setText("Presidente");
        itemPresidente.setName("itemPresidente"); // NOI18N
        itemPresidente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPresidenteActionPerformed(evt);
            }
        });
        menu_principal.add(itemPresidente);

        itemTesoreria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cofre-del-tesoro.png"))); // NOI18N
        itemTesoreria.setText("Tesorero");
        itemTesoreria.setName("tesorero"); // NOI18N
        itemTesoreria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTesoreriaActionPerformed(evt);
            }
        });
        menu_principal.add(itemTesoreria);

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

        menu_principal.add(subMenuDocs);

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

        menu_principal.add(subMenuBD);
        menu_principal.add(jSeparator2);

        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cerrar-sesion(2).png"))); // NOI18N
        itemSalir.setText("Salir");
        itemSalir.setName("itemSalir"); // NOI18N
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menu_principal.add(itemSalir);

        menu_en_barra.add(menu_principal);

        menu_herramientas.setText("Herramientas");

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

    private void cobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobrosActionPerformed
        vistaSeleccionada(1);
    }//GEN-LAST:event_cobrosActionPerformed

    private void recargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recargosActionPerformed
        vistaSeleccionada(2);
    }//GEN-LAST:event_recargosActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        vistaSeleccionada(0);
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void itemPresidenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPresidenteActionPerformed
        SwingUtilities.invokeLater(() -> menuPresidente.setVisible(true));
    }//GEN-LAST:event_itemPresidenteActionPerformed

    private void itemBdTomasRegistradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBdTomasRegistradasActionPerformed
        SwingUtilities.invokeLater(() -> menuTomasRegistradas.setVisible(true));
    }//GEN-LAST:event_itemBdTomasRegistradasActionPerformed

    private void perfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilActionPerformed
        SwingUtilities.invokeLater(() -> menuPerfil.setVisible(true));
    }//GEN-LAST:event_perfilActionPerformed

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
        SwingUtilities.invokeLater(() -> tab_root.setSelectedIndex(i));
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
    private javax.swing.JButton cobros;
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
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPanel jpVisor;
    private javax.swing.JMenu menu_ayuda;
    private javax.swing.JMenuBar menu_en_barra;
    private javax.swing.JMenu menu_herramientas;
    private javax.swing.JMenu menu_principal;
    private javax.swing.JMenuItem pagos_del_dia;
    private javax.swing.JPanel panel_area_personal;
    private javax.swing.JPanel panel_cobros;
    private javax.swing.JPanel panel_lat_opciones;
    private javax.swing.JPanel panel_recargos;
    private javax.swing.JButton perfil;
    private javax.swing.JButton recargos;
    private javax.swing.JButton salir;
    private javax.swing.JMenu subMenuBD;
    private javax.swing.JMenu subMenuDocs;
    private javax.swing.JTabbedPane tab_root;
    // End of variables declaration//GEN-END:variables

// </editor-fold>
    @Override
    public Rectangle getBounds() {
        //vistaCobros.getBounds();
        return super.getBounds(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void dispose() {
        finalizarSesion();
        super.dispose();
        irLogin();
    }

    @Override
    public void permisos() {
        OPersonal o = Sesion.getInstancia().getUsuario();
        itemTesoreria.setEnabled(EnvPersonal.isAdministrador(o) || EnvPersonal.isTesorero(o));
        itemPresidente.setEnabled(EnvPersonal.isAdministrador(o) || EnvPersonal.isPresidente(o));
        itemAdministrador.setEnabled(EnvPersonal.isAdministrador(o));

    }

}

class visor extends JPanel {

    public visor() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon o = new ImageIcon(getClass().getResource("/com/jblue/media/img/JBlue 1.0.png"));
        Image i = o.getImage().getScaledInstance(200, 200, Image.SCALE_FAST);
        ImageIcon x = new ImageIcon(i);
        g.drawImage(x.getImage(), 0, 0, this);
    }

}
