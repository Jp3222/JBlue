package jsoftware.com.jblue.controllers.compc;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.views.framework.AbstractModuleView;
import jsoftware.com.jblue.views.framework.WizardModel;

/**
 * Controlador intermedio encargado de gobernar el flujo de navegación y
 * transición de pantallas de los asistentes paso a paso (Wizards).
 *
 * * @author juanp
 * @author JUAN PABLO CAMPOS CASASANERO (Revision de consistencia)
 */
public class WizardController extends Controller {

    private static final long serialVersionUID = 1L;
    private WizardModel<?> view; // Uso de comodín genérico para mantener desacoplado el DTO

    public WizardController(WizardModel<?> wizard) {
        this.view = wizard;
    }

    public WizardController() {
        this.view = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view == null) {
            return;
        }
        switch (e.getActionCommand()) {
            case WizardModel.NEXT_STEP ->
                next();
            case WizardModel.PREVIOUS_STEP ->
                previous();
            case WizardModel.EXECUTE_FINAL ->
                executeFinal();
            case DBControllerModel.SAVE_COMMAND, DBControllerModel.UPDATE_COMMAND -> {
            }
            case DBControllerModel.CANCEL_COMMAND -> {
                view.setStartMode();
            }
            default ->
                defaultCase("EL COMANDO %s NO ES VALIDO", "COMANDO NO VALIDO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * METODO FUNCIONANDO
     */
    public void next() {
        // CORRECCIÓN: Se evalúa una sola vez y se guarda el resultado en una variable local
        boolean nextStep = view.nextStep();
        if (!nextStep) {
            return; // La vista ya debió mostrar su respectivo JOptionPane de error interno
        }
        // Ejecución ordenada del flujo de la UI
        view.nextIndex();
        view.updateUi(WizardModel.NEXT_VIEW_BUTTON);
        view.updateUi(WizardModel.NAVIGATION_STEP_BAR);
    }

    /**
     * Metodo con bugs
     */
    public void previous() {
        // Validar si la vista permite regresar (útil si hay hilos o procesos intermedios)
        boolean previousStep = view.previousStep();
        if (!previousStep) {
            return;
        }

        // CORRECCIÓN: Si ya estamos en el primer panel (índice 0), no podemos ir más atrás
        if (view.getCurrentViewIndex() <= 0) {
            return;
        }
        view.previousIndex();
        view.updateUi(WizardModel.PREVIOUS_VIEW_BUTTON); // Cambia la tarjeta de forma segura
        view.updateUi(WizardModel.NAVIGATION_STEP_BAR);  // Actualiza estados de botones
    }

    /**
     * PROXIMA MEJORA
     */
    public void executeFinal() {
        // 1. Forzar a la vista actual a recolectar los últimos datos del formulario hacia el DTO
        if (view instanceof AbstractModuleView<?> V) {
            V.getData();
        }
        // Nota: Aquí invocarás al controlador del caso de uso (ej: employeeController.insert();)
        // Por ahora, cerramos o reiniciamos el estado inicial del asistente de manera limpia
        if (view instanceof AbstractModuleView<?> V) {
            V.initialState();
        }
    }

    public void setView(WizardModel<?> view) {
        this.view = view;
    }
}
