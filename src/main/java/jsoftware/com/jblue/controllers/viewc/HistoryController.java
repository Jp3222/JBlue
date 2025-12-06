/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import jsoftware.com.jblue.controllers.AbstractViewController;
import jsoftware.com.jblue.views.HistoryView;

/**
 *
 * @author juanp
 */
public class HistoryController extends AbstractViewController {

    private HistoryView view;

    public HistoryController(HistoryView view) {
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "load_history" ->
                load();
            default ->
                throw new AssertionError();
        }
    }

    private void load() {

    }

}
