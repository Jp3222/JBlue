/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class SearchableComboBox<T> {

    private final JComboBox<T> comboBox;
    private final List<T> originalList;

    public SearchableComboBox(JComboBox<T> comboBox, List<T> data) {
        this.comboBox = comboBox;
        this.originalList = data;
        init();
    }

    private void init() {
        // Configuramos el ComboBox para que sea editable
        comboBox.setEditable(true);
        JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();

        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Ignorar teclas de navegación (flechas, enter, etc)
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP
                        || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    return;
                }

                String text = editor.getText();
                updateModel(text);

                // Mantener el texto actual y reabrir el menú desplegable
                editor.setText(text);
                if (text.length() > 0) {
                    comboBox.showPopup();
                }
            }
        });
    }

    private void updateModel(String filter) {
        DefaultComboBoxModel<T> model = new DefaultComboBoxModel<>();

        // Filtramos la lista original usando Streams de Java 17
        List<T> filtered = originalList.stream()
                .filter(item -> item.toString().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());

        for (T item : filtered) {
            model.addElement(item);
        }

        comboBox.setModel(model);
    }
}
