package jsoftware.com.jblue.model.dto.wrp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.TransactionHistoryDTO;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * Contexto y contenedor maestro abstracto para los módulos operativos de JBlue.
 * Centraliza la auditoría, empleados, administraciones y controladores del
 * sistema.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-07
 * @version 1.1
 */
public abstract class ModuleWrapperDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    private final String module_id;
    private final String module_name;
    private final Map<String, Controller> controller_map;
    private final TransactionHistoryDTO transaction; // Corregido typo: transacion -> transaction

    private EmployeeUserDTO current_employee;
    private AdministrationHistoryDTO current_administration;

    public ModuleWrapperDTO(String module_id, String module_name) {
        this(module_id, module_name, null, null);
    }

    public ModuleWrapperDTO(String module_id, String module_name, String transaction_type_mov) {
        this(module_id, module_name, transaction_type_mov, null);
    }

    public ModuleWrapperDTO(String module_id, String module_name, String transaction_type_mov, String observation) {
        super(16); // Inicialización del mapa interno heredado de JDBMapObject
        this.module_id = module_id;
        this.module_name = module_name;
        this.controller_map = new HashMap<>(16); // Optimización a potencia de 2 para controladores

        // Inicialización segura del DTO de Auditoría Maestra
        this.transaction = new TransactionHistoryDTO();
        this.transaction.put("module_id", module_id);
        this.transaction.put("type_mov", transaction_type_mov);
        this.transaction.put("affected_table", "0"); // Por defecto o polimórfico inicial
        this.transaction.put("status", "34");        // Estatus inicial [34 - EN PROCESO]

        // Corrección de lógica inversa: Si la observación viene vacía, se genera una por defecto
        if (Func.isNullEmptyBlank(observation)) {
            this.transaction.put("observation", "REGISTRO INICIADO DESDE EL MÓDULO: " + module_name);
        } else {
            this.transaction.put("observation", observation);
        }
    }

    public String getModule_id() {
        return module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public EmployeeUserDTO getCurrent_employee() {
        return current_employee;
    }

    /**
     * Inyecta el empleado actual del sistema y sincroniza de forma segura el ID
     * requerido por la transacción de auditoría, evitando NullPointerException.
     */
    public void setCurrent_employee(EmployeeUserDTO current_employee) {
        this.current_employee = current_employee;
        if (current_employee != null) {
            // Sincronización segura y tardía una vez que el objeto existe
            this.transaction.put("employee_id", current_employee.getId());
        }
    }

    public AdministrationHistoryDTO getCurrent_administration() {
        return current_administration;
    }

    public void setCurrent_administration(AdministrationHistoryDTO current_administration) {
        this.current_administration = current_administration;
    }

    public TransactionHistoryDTO getTransaction() {
        return transaction;
    }

    public void putController(String name, Controller controller) {
        controller_map.put(name, controller);
    }

    public Controller getController(String name) {
        return controller_map.get(name);
    }

    // El borrado limpia los estados dinámicos del módulo concreto
    public abstract void clear();

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.module_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ModuleWrapperDTO other = (ModuleWrapperDTO) obj;
        return Objects.equals(this.module_id, other.module_id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ModuleWrapperDTO{");
        sb.append("module_id=").append(module_id).append(", ");
        sb.append("module_name=").append(module_name).append("}");
        return sb.toString();
    }
}
