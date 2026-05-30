/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.l4b;

import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.models.AbstractValidation;
import jsoftware.com.jblue.util.Func;

/**
 *
 * @author juanp
 */
public abstract class ProcessValidators extends AbstractValidation {

    public static final int AFTER = 0;
    public static final int BEFORE = 1;

    private static final long serialVersionUID = 1L;
    private final String ERR_MSG1 = "LA INFORMACION %s NO SE GUARDO CORRECTAMNETE";

    private ProcessWrapperDTO process_wrapper_dto;

    public ProcessValidators(ProcessWrapperDTO dto) {
        super();
    }

    /**
     * Metodo que valida que todos
     *
     * @return
     */
    protected boolean startProcess(int mov_time) {
        addRuler("dto-not-null",
                process_wrapper_dto,
                t -> Func.isNull(t)
                && Func.isNotNull(process_wrapper_dto.getProcess_type_id())
                && Func.isNotNull(process_wrapper_dto.getModule_id())
                && Func.isNotNull(process_wrapper_dto.getModule_name())
                && Func.isNotNull(process_wrapper_dto.getModule_name()),
                ERR_MSG1.formatted("DEL TRAMITE")
        );
        if (mov_time == BEFORE) {
            addRuler("user-not-null", process_wrapper_dto.getUser(), t -> Func.isNotNull(t), ERR_MSG1.formatted("DEL USUARIO"));
            addRuler("cur-employee-not-null", process_wrapper_dto.getCurrent_employee(), t -> Func.isNotNull(t), ERR_MSG1.formatted("DEL EMPLEADO ACTUAL"));
            addRuler("cur-admin-null", process_wrapper_dto.getCurrent_administration(), t -> Func.isNotNull(t), ERR_MSG1.formatted("DE LA ADMINISTRACION"));
            addRuler("payment-not-null", process_wrapper_dto.getPayment(), t -> Func.isNotNull(t), ERR_MSG1.formatted("DE LA CEBEZERA DEL PAGO"));
            addRuler("payment-list-not-null", process_wrapper_dto.getPayment_concept_list(), t -> Func.isNotNull(t), ERR_MSG1.formatted("DE LOS CONCEPTOS DE PAGO"));
            addRuler("water-inatke-not-null", process_wrapper_dto.getWater_intake(), t -> Func.isNotNull(t),
                    ERR_MSG1.formatted("DE LA TOMA"));
            addRuler("wki-user-not-null", process_wrapper_dto.getWki_user(), t -> Func.isNotNull(t),
                    ERR_MSG1.formatted("DEL USUARIO DE TOMA"));

        }
        return isValid();
    }

    protected abstract boolean validProcess(int mov_time);

    protected abstract boolean WaterIntakeRegisterValid(int mov_time);

    protected abstract boolean FinalizeProcessValid(int mov_time);

    protected abstract boolean PrintValid(int mov_time);

}
