/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import java.io.Serializable;
import java.util.List;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.dto.wrp.ModuleWrapperDTO;

/**
 * Modelo Wizard para crear y estandarizar vistas de tipo "Asistente". Este
 * contrato permite la iteración de vistas con un CardLayout.
 *
 * @author Juan Pablo Campos Casasanero
 * @param <T> El envoltorio de datos del proceso (DTO)
 */
public interface WizardModel<T extends ModuleWrapperDTO> extends Serializable {

    // --- Comandos de Acción ---
    public static final String NEXT_STEP = "next_step";
    public static final String PREVIOUS_STEP = "previous_step";
    public static final String UPDATE_UI = "update_ui";
    public static final String EXECUTE_FINAL = "execute_final";

    // --- Identificadores de Componentes de Interfaz ---
    public static final int NEXT_VIEW_BUTTON = 1;
    public static final int PREVIOUS_VIEW_BUTTON = 2;
    public static final int NAVIGATION_STEP_BAR = 3;

    /**
     * Valida si el paso actual es correcto para avanzar.
     *
     * @return true si la validación es exitosa.
     */
    boolean nextStep();

    /**
     * Gestiona la lógica necesaria para retroceder un paso.
     *
     * @return true si es posible regresar.
     */
    boolean previousStep();

    /**
     * Actualiza los componentes de la interfaz (botones, barras de progreso).
     *
     * @param componentId El ID del componente a actualizar.
     */
    void updateUi(int componentId);

    /**
     * Ejecuta instrucciones de cierre o cálculos finales antes de la
     * persistencia.
     */
    void executeFinal();

    /**
     * @return El arreglo de módulos/paneles que componen el asistente.
     */
    List<AbstractModuleView<EmployeeRegisterWrapperDTO>> getViews();

    /**
     * @return El índice de la vista que se está mostrando actualmente.
     */
    int getCurrentViewIndex();

    /**
     * Define qué vista debe mostrarse.
     *
     * @param index El índice dentro del arreglo de vistas.
     */
    void setCurrentViewIndex(int index);

    /**
     * Incrementa el puntero del asistente (i++).
     */
    void nextIndex();

    /**
     * Decrementa el puntero del asistente (i--).
     */
    void previousIndex();
}
