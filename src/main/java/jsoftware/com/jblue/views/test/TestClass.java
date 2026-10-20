/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.JXHeader;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author juanp
 */
public class TestClass extends JPanel {

    /**
     * Vista modular que integra componentes avanzados de SwingX.
     *
     * * @author JUAN PABLO CAMPOS CASASANERO
     */
    private JXHeader header;
    private JXSearchField txtBuscar;
    private JXTable tblCalles;
    private TableRowSorter<?> sorter;

    public TestClass() {
        setLayout(new BorderLayout(10, 10));

        // 1. Encabezado institucional con JXHeader
        header = new JXHeader();
        header.setTitle("Catálogo de Calles y Geografía");
        header.setDescription("Administre las calles vinculadas a las colonias para el padrón de usuarios.");
        //header.setIcon(new ImageIcon(getClass().getResource("/icons/map_32x32.png")));
        add(header, BorderLayout.NORTH);

        // 2. Barra de herramientas con JXSearchField
        JPanel pnlTools = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        txtBuscar = new JXSearchField("Buscar calle o colonia...");
        txtBuscar.setColumns(25);

        // Configurar el filtrado instantáneo mientras se escribe
        txtBuscar.addActionListener(e -> filtrarDatos(txtBuscar.getText()));
        pnlTools.add(txtBuscar);

        // 3. Tabla avanzada con JXTable
        tblCalles = new JXTable();
        // Asumiendo que ya tienes tu modelo cargado con los DTOs...
        // tblCalles.setModel(miModeloDeDatos); 

        // Configurar el Sorter para el filtrado dinámico
        sorter = new TableRowSorter<>(tblCalles.getModel());
        tblCalles.setRowSorter(sorter);

        // Habilitar funciones nativas de JXTable
        tblCalles.setColumnControlVisible(true); // Menú para ocultar/mostrar columnas
        tblCalles.setHorizontalScrollEnabled(true); // Scroll horizontal inteligente

        // Añadir componentes al layout
        JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));
        pnlCenter.add(pnlTools, BorderLayout.NORTH);
        pnlCenter.add(tblCalles, BorderLayout.CENTER); // Normalmente envuelto en un JScrollPane

        add(pnlCenter, BorderLayout.CENTER);
    }

    /**
     * Aplica el filtro dinámico sobre la JXTable basándose en el DTO
     */
    private void filtrarDatos(String texto) {
        if (texto.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // Filtra sin importar mayúsculas o minúsculas (?i)
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }

}
