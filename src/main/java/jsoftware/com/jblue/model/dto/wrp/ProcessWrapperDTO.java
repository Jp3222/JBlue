/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.dto.PaymentListDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.UserDocumentDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypeDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;

/**
 * Objeto de transferencia de datos envolvente (Wrapper) para procesos
 * transaccionales en JBlue.
 * <p>
 * Centraliza todas las entidades necesarias para completar un flujo de trabajo
 * (como el alta de una toma o registro de titular), permitiendo la comparación
 * de estados (old vs new) y la gestión de pagos vinculados.
 * </p>
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @version 1.2
 * @since 2026/02/01
 */
public class ProcessWrapperDTO extends ModuleWrapperDTO {

    private static final long serialVersionUID = 1L;

    //PASO 1
    private final UserDTO user;
    //PASO 2
    private final List<UserDocumentDTO> user_document_list;
    //PASO 3
    private final WaterIntakeDTO water_intake;
    private final WaterIntakeTypeDTO water_intake_type;

    //PASO 4
    private final PaymentDTO payment;
    private final List<PaymentListDTO> payment_concept_list;
    //PASO 5
    private final WaterIntakeUserDTO wki_user;

    //Banderas de datos validos
    private boolean user_valid;
    private boolean user_document_valid;
    private boolean water_intake_valid;
    private boolean payment_valid;

    //Datos de proceso
    private int process_type_id;
    private int last_process_type;

    //Datos originales antes de un update
    private UserDTO user_copy;
    private WaterIntakeDTO water_intake_copy;
    private WaterIntakeUserDTO wki_user_copy;

    public ProcessWrapperDTO(String module_id, String module_name) {
        super(module_id, module_name);
        this.user = new UserDTO();
        this.user_document_list = new ArrayList<>();
        this.water_intake = new WaterIntakeDTO();
        this.water_intake_type = new WaterIntakeTypeDTO();
        this.payment = new PaymentDTO();
        this.payment_concept_list = new ArrayList<>();
        this.wki_user = new WaterIntakeUserDTO();
        this.user_valid = false;
        this.user_document_valid = false;
        this.water_intake_valid = false;
        this.payment_valid = false;
        this.process_type_id = -1;
        this.last_process_type = -1;
    }

    public boolean isUser_valid() {
        return user_valid;
    }

    public void setUser_valid(boolean user_valid) {
        this.user_valid = user_valid;
    }

    public boolean isUser_document_valid() {
        return user_document_valid;
    }

    public void setUser_document_valid(boolean user_document_valid) {
        this.user_document_valid = user_document_valid;
    }

    public boolean isWater_intake_valid() {
        return water_intake_valid;
    }

    public void setWater_intake_valid(boolean water_intake_valid) {
        this.water_intake_valid = water_intake_valid;
    }

    public boolean isPayment_valid() {
        return payment_valid;
    }

    public void setPayment_valid(boolean payment_valid) {
        this.payment_valid = payment_valid;
    }

    public int getProcess_type_id() {
        return process_type_id;
    }

    public void setProcess_type_id(int process_type_id) {
        this.process_type_id = process_type_id;
    }

    public int getLast_process_type() {
        return last_process_type;
    }

    public void setLast_process_type(int last_process_type) {
        this.last_process_type = last_process_type;
    }

    public UserDTO getUser_copy() {
        return user_copy;
    }

    public void setUser_copy(UserDTO user_copy) {
        this.user_copy = user_copy;
    }

    public WaterIntakeDTO getWater_intake_copy() {
        return water_intake_copy;
    }

    public void setWater_intake_copy(WaterIntakeDTO water_intake_copy) {
        this.water_intake_copy = water_intake_copy;
    }

    public WaterIntakeUserDTO getWki_user_copy() {
        return wki_user_copy;
    }

    public void setWki_user_copy(WaterIntakeUserDTO wki_user_copy) {
        this.wki_user_copy = wki_user_copy;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserDTO getUser() {
        return user;
    }

    public List<UserDocumentDTO> getUser_document_list() {
        return user_document_list;
    }

    public WaterIntakeDTO getWater_intake() {
        return water_intake;
    }

    public WaterIntakeTypeDTO getWater_intake_type() {
        return water_intake_type;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<PaymentListDTO> getPayment_concept_list() {
        return payment_concept_list;
    }

    public WaterIntakeUserDTO getWki_user() {
        return wki_user;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.user);
        hash = 53 * hash + Objects.hashCode(this.user_document_list);
        hash = 53 * hash + Objects.hashCode(this.water_intake);
        hash = 53 * hash + Objects.hashCode(this.water_intake_type);
        hash = 53 * hash + Objects.hashCode(this.payment);
        hash = 53 * hash + Objects.hashCode(this.payment_concept_list);
        hash = 53 * hash + Objects.hashCode(this.wki_user);
        hash = 53 * hash + (this.user_valid ? 1 : 0);
        hash = 53 * hash + (this.user_document_valid ? 1 : 0);
        hash = 53 * hash + (this.water_intake_valid ? 1 : 0);
        hash = 53 * hash + (this.payment_valid ? 1 : 0);
        hash = 53 * hash + this.process_type_id;
        hash = 53 * hash + this.last_process_type;
        hash = 53 * hash + Objects.hashCode(this.user_copy);
        hash = 53 * hash + Objects.hashCode(this.water_intake_copy);
        hash = 53 * hash + Objects.hashCode(this.wki_user_copy);
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
        if (this.user_valid != other.user_valid) {
            return false;
        }
        if (this.user_document_valid != other.user_document_valid) {
            return false;
        }
        if (this.water_intake_valid != other.water_intake_valid) {
            return false;
        }
        if (this.payment_valid != other.payment_valid) {
            return false;
        }
        if (this.process_type_id != other.process_type_id) {
            return false;
        }
        if (this.last_process_type != other.last_process_type) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.user_document_list, other.user_document_list)) {
            return false;
        }
        if (!Objects.equals(this.water_intake, other.water_intake)) {
            return false;
        }
        if (!Objects.equals(this.water_intake_type, other.water_intake_type)) {
            return false;
        }
        if (!Objects.equals(this.payment, other.payment)) {
            return false;
        }
        if (!Objects.equals(this.payment_concept_list, other.payment_concept_list)) {
            return false;
        }
        if (!Objects.equals(this.wki_user, other.wki_user)) {
            return false;
        }
        if (!Objects.equals(this.user_copy, other.user_copy)) {
            return false;
        }
        if (!Objects.equals(this.water_intake_copy, other.water_intake_copy)) {
            return false;
        }
        return Objects.equals(this.wki_user_copy, other.wki_user_copy);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(5000);

        //DATOS DEL USUARIO
        sb.append("USER: {\n").append(user.getMap().toString()).append("\n} \n");
        sb.append("USER_VALID: {\n").append(user_valid).append("\n} \n");

        //DOCUMENTACION DE USUARIO
        sb.append("DOCUMENT_LIST: {\n").append(user_document_list.toString()).append("\n} \n");
        sb.append("USER_DOCUMENT_VALID: {\n").append(user_document_valid).append("\n} \n");

        //INFORMACION DE LA TOMA
        sb.append("WATER_INTAKE: {\n").append(water_intake.getMap().toString()).append("\n} \n");
        sb.append("WATER_INTAKE_TYPE: {\n").append(water_intake_type.getMap().toString()).append("\n} \n");
        sb.append("WATER_INTAKE_VALID: {\n").append(water_intake_valid).append("\n} \n");

        //INFORMACION DEL PAGO
        sb.append("PAYMENT: {\n").append(payment.getMap().toString()).append("\n} \n");
        sb.append("PAYMENT_CONCEPT_LIST: {\n").append(payment_concept_list.toString()).append("\n} \n");
        sb.append("PAYMENT_VALID: {\n").append(payment_valid).append("\n} \n");

        //INFORMACION DE DATOS PARA EL PADRON DE USUARIO
        sb.append("WKI_USER: {\n").append(wki_user.getMap().toString()).append("\n} \n");
        return sb.toString();
    }

}
