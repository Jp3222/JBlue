package jsoftware.com.jblue.model.dto.wrp;

/**
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 */
public class OwnerRegisterProcessWrapper extends ProcessWrapperDTO {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor para el proceso de registro de titular.
     *
     * * @param module_id ID del módulo desde Swing
     * @param module_name Nombre del módulo
     * @param currentAdminId ID de la administración activa (Inyectado)
     * @param currentEmployeeId ID del empleado en sesión (Inyectado)
     */
    public OwnerRegisterProcessWrapper(String module_id, String module_name) {
        // El constructor padre ya ejecuta this.clear() e inicializa process = new ProcessDTO();
        super(module_id, module_name, "3", "REGISTRO DEL CONTRIBUYENTE POR MODULO DEL SISTEMA");

        // Asignación homogénea usando la estructura de Map del DTO
        this.process.put("process_type", "1");
    }
}
