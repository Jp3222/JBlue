package com.jblue.vista.ventanas.herramientas;

import com.jblue.modelo.ConstBD;
import com.jblue.sistema.app.AppFiles;
import com.jblue.vista.jbmarco.VentanaExtendida;
import com.jutil.soyjvm.So;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author juan-campos
 */
public class MenuCSVImport extends VentanaExtendida {

    private final Map<String, String[]> mapa_bd;
    private final Map<String, JPanel> cache_opciones;
    private JFileChooser file_chosser;

    /**
     * Creates new form MenuImportCSV
     */
    public MenuCSVImport() {
        So.setDefaultLookAndFeel();
        //
        mapa_bd = new HashMap<>(ConstBD.TABLAS.length);
        cache_opciones = new HashMap<>(ConstBD.TABLAS.length);
        initComponents();
        itemsComboBox();
        jEditorPane1.setEditable(false);
        campo_todos.addItemListener(e -> {
            evtCheckBox(cache_opciones.get((String) jComboBox1.getSelectedItem()));
        });
        file_chosser = new JFileChooser(AppFiles.DIR_USER_REPORTES);
        file_chosser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    private void itemsComboBox() {
        String[] TABLAS = ConstBD.TABLAS;
        String[][] CAMPOS = ConstBD.CAMPOS;
        for (int i = 0; i < TABLAS.length; i++) {
            mapa_bd.put(TABLAS[i], CAMPOS[i]);
            jComboBox1.addItem(TABLAS[i]);
        }

        jComboBox1.addItemListener(e -> {
            JPanel p = cache_opciones.get(String.valueOf(e.getItem()));
            if (p == null) {
                p = buildCampos((String) e.getItem());
            }
            jScrollPane1.setViewportView(p);
        });
    }

    private JPanel buildCampos(String key) {
        System.out.println("nuevo");
        JPanel p = new JPanel();
        GridLayout g = new GridLayout(1, mapa_bd.get(key).length);
        p.setLayout(g);
        for (String i : mapa_bd.get(key)) {
            p.add(buildCheckBox(i));
        }
        cache_opciones.put(key, p);
        System.out.println(cache_opciones.size());
        System.out.println("");
        return p;
    }

    private JCheckBox buildCheckBox(String nombre) {

        JCheckBox c = new JCheckBox(nombre);
        c.setName(nombre);
        c.setVerticalTextPosition(JCheckBox.BOTTOM);
        c.setHorizontalTextPosition(JCheckBox.CENTER);
        return c;
    }

    private void evtCheckBox(JPanel p) {
        if (p == null) {
            campo_todos.setSelected(false);
            return;
        }
        JCheckBox aux;
        for (Component i : p.getComponents()) {
            aux = (JCheckBox) i;
            aux.setSelected(campo_todos.isSelected());
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

        panel_superior = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        campo_todos = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel_central = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        panel_inferior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 600));

        panel_superior.setPreferredSize(new java.awt.Dimension(500, 135));
        panel_superior.setLayout(new java.awt.BorderLayout());

        jButton1.setText("Seleccionar Archivo");
        jButton1.setPreferredSize(new java.awt.Dimension(150, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panel_superior.add(jButton1, java.awt.BorderLayout.WEST);

        jTextField1.setEditable(false);
        jTextField1.setText("url");
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 35));
        panel_superior.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel5.add(jComboBox1, java.awt.BorderLayout.CENTER);

        panel_superior.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel4.setPreferredSize(new java.awt.Dimension(500, 60));
        jPanel4.setLayout(new java.awt.BorderLayout());

        campo_todos.setText("Todos");
        campo_todos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        campo_todos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        campo_todos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(campo_todos, java.awt.BorderLayout.LINE_START);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panel_superior.add(jPanel4, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panel_superior, java.awt.BorderLayout.PAGE_START);

        panel_central.setLayout(new java.awt.BorderLayout());

        jButton3.setText("Mostrar Datos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panel_central.add(jButton3, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setViewportView(jEditorPane1);

        panel_central.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(panel_central, java.awt.BorderLayout.CENTER);

        panel_inferior.setPreferredSize(new java.awt.Dimension(200, 35));
        panel_inferior.setLayout(new java.awt.BorderLayout());
        panel_inferior.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel1.setLayout(new java.awt.GridLayout());

        jButton2.setText("Cancelar");
        jPanel1.add(jButton2);

        Guardar.setText("Guardar");
        jPanel1.add(Guardar);

        panel_inferior.add(jPanel1, java.awt.BorderLayout.EAST);

        getContentPane().add(panel_inferior, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        file_chosser.showDialog(this, "Abrir");
        if (file_chosser.getSelectedFile() != null) {
            f = file_chosser.getSelectedFile();
            jTextField1.setText(f.getName());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try (FileReader fl = new FileReader(f); BufferedReader br = new BufferedReader(fl)) {
            String aux;
            StringBuilder sb = new StringBuilder(200);
            while ((aux = br.readLine()) != null) {
                sb.append(aux).append("\n");
            }
            jEditorPane1.setText(sb.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuCSVImport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuCSVImport.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed
    private File f;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new MenuCSVImport().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JCheckBox campo_todos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panel_central;
    private javax.swing.JPanel panel_inferior;
    private javax.swing.JPanel panel_superior;
    // End of variables declaration//GEN-END:variables
}