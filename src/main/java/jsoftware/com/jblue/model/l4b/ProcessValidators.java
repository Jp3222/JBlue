/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.l4b;

import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.abst.AbstractValidation;
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
                && Func.isNotNull(process_wrapper_dto.getModule_id())
                && Func.isNotNull(process_wrapper_dto.getModule_name())
                && Func.isNotNull(process_wrapper_dto.getCurrent_employee())
                && Func.isNotNull(process_wrapper_dto.getCurrent_administration()),
                ERR_MSG1.formatted("DEL TRAMITE")
        );
        if (mov_time == BEFORE) {
            addRuler("process_id",
                    process_wrapper_dto.getProcess(),
                    t -> Func.isNotNull(t.getProcessType()),
                    "EL TIPO DE TRAMITE NO HA SIDO DEFINIDO"
            );
        } else {
            addRuler("process_id",
                    process_wrapper_dto.getProcess(),
                    t -> Func.isNotNull(t.getSequenceProcess()),
                    "EL TIPO DE TRAMITE NO HA SIDO DEFINIDO"
            );
        }
        return isValid();
    }

    protected abstract boolean validProcess(int mov_time);

    protected abstract boolean movProcess(int mov_time);

    protected abstract boolean paymentProcess(int mov_time);

    protected abstract boolean finalizeProcess(int mov_time);

}
