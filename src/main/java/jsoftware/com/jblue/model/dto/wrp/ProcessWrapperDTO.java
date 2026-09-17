package jsoftware.com.jblue.model.dto.wrp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.dto.DocumentRecordDTO;
import jsoftware.com.jblue.model.dto.ProcessDTO;
import jsoftware.com.jblue.model.dto.ProcessWaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.UserDocumentationDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypeDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jpaymentlib.model.dto.wrp.PaymentWrapper;

/**
 * Objeto de transferencia de datos envolvente (Wrapper) para procesos
 * transaccionales en JBlue.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @version 1.2
 * @since 2026/02/01
 */
public class ProcessWrapperDTO extends ModuleWrapperDTO {

    private static final long serialVersionUID = 1L;

    protected ProcessDTO process;
    private ProcessWaterIntakeUserDTO process_water_intake_user;
    private boolean user_exists;
    //PASO 1
    private UserDTO user;
    private boolean user_valid;

    //PASO 2
    private AddressDTO address;
    private boolean address_valid;

    //PASO 3
    private List<UserDocumentationDTO> document_list;
    private boolean document_list_valid;

    private WaterIntakeDTO water_intake;
    private boolean water_intake_valid;

    //TIPO DE TOMA
    private WaterIntakeTypeDTO water_intake_type;

    //PASO 4
    private PaymentWrapper payment_wrp;
    private boolean payment_valid;
    private boolean payment_line_valid;
    //PASO 6
    private DocumentRecordDTO document_record;
    private boolean document_record_valid; // Integrado para mantener la simetría del flujo

    //PASO 7
    private WaterIntakeUserDTO wki_user;
    private boolean wki_user_valid;

    public ProcessWrapperDTO(String module_id, String module_name, int transaction_type_mov, String observation) {
        super(module_id, module_name, transaction_type_mov, observation);
        this.clear();
    }

    @Override
    public void clear() {
        process = new ProcessDTO();
        process_water_intake_user = new ProcessWaterIntakeUserDTO();
        user = new UserDTO();
        address = new AddressDTO();
        document_list = new ArrayList<>();
        document_record = new DocumentRecordDTO(); // Corregido: Limpieza total
        water_intake = new WaterIntakeDTO();
        water_intake_type = new WaterIntakeTypeDTO();
        payment_wrp = new PaymentWrapper();
        wki_user = new WaterIntakeUserDTO();
        user_valid = false;
        address_valid = false;
        document_list_valid = false;
        water_intake_valid = false;
        payment_valid = false;
        payment_line_valid = false;
        document_record_valid = false;
        wki_user_valid = false;
    }

    public ProcessDTO getProcess() {
        return process;
    }

    public void setProcess(ProcessDTO process) {
        this.process = process;
    }

    public ProcessWaterIntakeUserDTO getProcess_water_intake_user() {
        return process_water_intake_user;
    }

    public void setProcess_water_intake_user(ProcessWaterIntakeUserDTO process_water_intake_user) {
        this.process_water_intake_user = process_water_intake_user;
    }

    public boolean isUser_exists() {
        return user_exists;
    }

    public void setUser_exists(boolean user_exists) {
        this.user_exists = user_exists;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public boolean isUser_valid() {
        return user_valid;
    }

    public void setUser_valid(boolean user_valid) {
        this.user_valid = user_valid;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public boolean isAddress_valid() {
        return address_valid;
    }

    public void setAddress_valid(boolean address_valid) {
        this.address_valid = address_valid;
    }

    public List<UserDocumentationDTO> getDocument_list() {
        return document_list;
    }

    public void setDocument_list(List<UserDocumentationDTO> document_list) {
        this.document_list = document_list;
    }

    public boolean isDocument_list_valid() {
        return document_list_valid;
    }

    public void setDocument_list_valid(boolean document_list_valid) {
        this.document_list_valid = document_list_valid;
    }

    public WaterIntakeDTO getWater_intake() {
        return water_intake;
    }

    public void setWater_intake(WaterIntakeDTO water_intake) {
        this.water_intake = water_intake;
    }

    public boolean isWater_intake_valid() {
        return water_intake_valid;
    }

    public void setWater_intake_valid(boolean water_intake_valid) {
        this.water_intake_valid = water_intake_valid;
    }

    public WaterIntakeTypeDTO getWater_intake_type() {
        return water_intake_type;
    }

    public void setWater_intake_type(WaterIntakeTypeDTO water_intake_type) {
        this.water_intake_type = water_intake_type;
    }

    public PaymentWrapper getPayment_wrp() {
        return payment_wrp;
    }

    public void setPayment_wrp(PaymentWrapper payment_wrp) {
        this.payment_wrp = payment_wrp;
    }

    public boolean isPayment_valid() {
        return payment_valid;
    }

    public void setPayment_valid(boolean payment_valid) {
        this.payment_valid = payment_valid;
    }

    public boolean isPayment_line_valid() {
        return payment_line_valid;
    }

    public void setPayment_line_valid(boolean payment_line_valid) {
        this.payment_line_valid = payment_line_valid;
    }

    public DocumentRecordDTO getDocument_record() {
        return document_record;
    }

    public void setDocument_record(DocumentRecordDTO document_record) {
        this.document_record = document_record;
    }

    public boolean isDocument_record_valid() {
        return document_record_valid;
    }

    public void setDocument_record_valid(boolean document_record_valid) {
        this.document_record_valid = document_record_valid;
    }

    public WaterIntakeUserDTO getWki_user() {
        return wki_user;
    }

    public void setWki_user(WaterIntakeUserDTO wki_user) {
        this.wki_user = wki_user;
    }

    public boolean isWki_user_valid() {
        return wki_user_valid;
    }

    public void setWki_user_valid(boolean wki_user_valid) {
        this.wki_user_valid = wki_user_valid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.process);
        hash = 37 * hash + Objects.hashCode(this.process_water_intake_user);
        hash = 37 * hash + (this.user_exists ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.user);
        hash = 37 * hash + (this.user_valid ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.address);
        hash = 37 * hash + (this.address_valid ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.document_list);
        hash = 37 * hash + (this.document_list_valid ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.water_intake);
        hash = 37 * hash + (this.water_intake_valid ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.water_intake_type);
        hash = 37 * hash + Objects.hashCode(this.payment_wrp);
        hash = 37 * hash + (this.payment_valid ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.document_record);
        hash = 37 * hash + (this.document_record_valid ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.wki_user);
        hash = 37 * hash + (this.wki_user_valid ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProcessWrapperDTO other = (ProcessWrapperDTO) obj;
        if (this.user_exists != other.user_exists) {
            return false;
        }
        if (this.user_valid != other.user_valid) {
            return false;
        }
        if (this.address_valid != other.address_valid) {
            return false;
        }
        if (this.document_list_valid != other.document_list_valid) {
            return false;
        }
        if (this.water_intake_valid != other.water_intake_valid) {
            return false;
        }
        if (this.payment_valid != other.payment_valid) {
            return false;
        }
        if (this.document_record_valid != other.document_record_valid) {
            return false;
        }
        if (this.wki_user_valid != other.wki_user_valid) {
            return false;
        }
        if (!Objects.equals(this.process, other.process)) {
            return false;
        }
        if (!Objects.equals(this.process_water_intake_user, other.process_water_intake_user)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.document_list, other.document_list)) {
            return false;
        }
        if (!Objects.equals(this.water_intake, other.water_intake)) {
            return false;
        }
        if (!Objects.equals(this.water_intake_type, other.water_intake_type)) {
            return false;
        }
        if (!Objects.equals(this.payment_wrp, other.payment_wrp)) {
            return false;
        }
        if (!Objects.equals(this.document_record, other.document_record)) {
            return false;
        }
        return Objects.equals(this.wki_user, other.wki_user);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProcessWrapperDTO{");
        sb.append("process=").append(process);
        sb.append(", process_water_intake_user=").append(process_water_intake_user);
        sb.append(", user_exists=").append(user_exists);
        sb.append(", user=").append(user);
        sb.append(", user_valid=").append(user_valid);
        sb.append(", address=").append(address);
        sb.append(", address_valid=").append(address_valid);
        sb.append(", document_list=").append(document_list);
        sb.append(", document_list_valid=").append(document_list_valid);
        sb.append(", water_intake=").append(water_intake);
        sb.append(", water_intake_valid=").append(water_intake_valid);
        sb.append(", water_intake_type=").append(water_intake_type);
        sb.append(", payment_wrp=").append(payment_wrp);
        sb.append(", payment_valid=").append(payment_valid);
        sb.append(", document_record=").append(document_record);
        sb.append(", document_record_valid=").append(document_record_valid);
        sb.append(", wki_user=").append(wki_user);
        sb.append(", wki_user_valid=").append(wki_user_valid);
        sb.append('}');
        return sb.toString();
    }

}
