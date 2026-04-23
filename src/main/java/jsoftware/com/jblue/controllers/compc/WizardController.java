/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.compc;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.views.framework.WizardModel;

/**
 *
 * @author juanp
 */
public class WizardController extends Controller {

    private static final long serialVersionUID = 1L;
    private WizardModel wizard;

    public WizardController(WizardModel wizard) {
        this.wizard = wizard;
    }

    public WizardController() {
        this.wizard = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case WizardModel.NEXT_STEP ->
                next();
            case WizardModel.PREVIOUS_STEP ->
                previous();
            case WizardModel.EXECUTE_FINAL ->
                executeFinal();
            default ->
                defaultCase("EL COMANDO %s NO ES VALIDO", "COMANDO NO VALIDO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void next() {
        if (!wizard.nextStep()) {
            return;
        }
        if (wizard.getCurrentViewIndex() < wizard.getViews().size() - 1) {
            wizard.nextIndex();
            wizard.updateUi(WizardModel.NEXT_VIEW_BUTTON);
        }
        wizard.updateUi(WizardModel.NAVIGATION_STEP_BAR);
    }

    public void previous() {
        if (!wizard.previousStep()) {
            return;
        }
        if (wizard.getCurrentViewIndex() != 0) {
            wizard.previousIndex();
            wizard.updateUi(WizardModel.PREVIOUS_VIEW_BUTTON);
        }
        wizard.updateUi(WizardModel.NAVIGATION_STEP_BAR);
    }

    public void executeFinal() {
        wizard.updateUi(WizardModel.PREVIOUS_VIEW_BUTTON);
    }

    public void setWizard(WizardModel wizard) {
        this.wizard = wizard;
    }

}
