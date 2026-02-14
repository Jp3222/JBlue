/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jsoftware.com.jutil.db.JDBMapObject;

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
public class ProcessWrapperDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    /**
     * DTO con los datos del empleado actual en el sistema
     */
    private EmployeeDTO current_employee;

    /**
     * DTO con los datos actuales del usuario
     */
    private UserDTO user;

    /**
     * lista de DTOs con los docuemtos del identidad del usuario
     */
    private List<UserDocumentDTO> user_document_list;

    /**
     * Tipo de toma del usuario
     */
    private WaterIntakeTypeDTO water_intake_type;

    /**
     * DTO con los datos de la toma de agua potable del usuario
     */
    private WaterIntakeDTO water_intake;

    /**
     * Cabezera o pago total del tramite
     */
    private PaymentDTO payment;

    /**
     * Lista de pagos o Pago total desglosado
     */
    private List<PaymentListDTO> payment_concept_list;

    /**
     * DTO con datos anteriores en caso de que el usuario ya extista o se
     * requiera una actualizacion
     */
    private UserDTO user_copy;

    /**
     * DTO con datos anteriores en caso de que la toma de agua potable ya
     * extista o se requiera una actualizacion
     */
    private WaterIntakeDTO water_intake_copy;

    /**
     * bandera para indicar que los dato de usuario son validos
     */
    private boolean user_valid;
    /**
     * bandera para indicar que los documentos validan la identidad del usuario
     */
    private boolean user_document_valid;
    /**
     * bandera para inidicar que los datos de la toma de agua son validos
     */
    private boolean water_intake_valid;

    /**
     * bandera para indicar que los datos de pago son validos
     */
    private boolean payment_valid;

    /**
     * bandera para indicar que los documentos guardados son validos
     */
    private boolean document_list_valid;

    public ProcessWrapperDTO(EmployeeDTO employee) {
        this.current_employee = employee;
        //this.user = new UserDTO();
        this.water_intake = new WaterIntakeDTO();
        this.water_intake_type = new WaterIntakeTypeDTO();
        this.payment = new PaymentDTO();
        this.payment_concept_list = new ArrayList<>();
        this.payment_valid = false;
        this.user_valid = false;
        this.water_intake_valid = false;
    }

    public EmployeeDTO getCurrent_employee() {
        return current_employee;
    }

    public void setCurrent_employee(EmployeeDTO current_employee) {
        this.current_employee = current_employee;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<UserDocumentDTO> getUser_document_list() {
        return user_document_list;
    }

    public void setUser_document_list(List<UserDocumentDTO> user_document_list) {
        this.user_document_list = user_document_list;
    }

    public WaterIntakeTypeDTO getWater_intake_type() {
        return water_intake_type;
    }

    public void setWater_intake_type(WaterIntakeTypeDTO water_intake_type) {
        this.water_intake_type = water_intake_type;
    }

    public WaterIntakeDTO getWater_intake() {
        return water_intake;
    }

    public void setWater_intake(WaterIntakeDTO water_intake) {
        this.water_intake = water_intake;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<PaymentListDTO> getPayment_concept_list() {
        return payment_concept_list;
    }

    public void setPayment_concept_list(List<PaymentListDTO> payment_concept_list) {
        this.payment_concept_list = payment_concept_list;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.current_employee);
        hash = 37 * hash + Objects.hashCode(this.user);
        hash = 37 * hash + Objects.hashCode(this.user_document_list);
        hash = 37 * hash + Objects.hashCode(this.water_intake_type);
        hash = 37 * hash + Objects.hashCode(this.water_intake);
        hash = 37 * hash + Objects.hashCode(this.payment);
        hash = 37 * hash + Objects.hashCode(this.payment_concept_list);
        hash = 37 * hash + Objects.hashCode(this.user_copy);
        hash = 37 * hash + Objects.hashCode(this.water_intake_copy);
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
        if (!Objects.equals(this.current_employee, other.current_employee)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.user_document_list, other.user_document_list)) {
            return false;
        }
        if (!Objects.equals(this.water_intake_type, other.water_intake_type)) {
            return false;
        }
        if (!Objects.equals(this.water_intake, other.water_intake)) {
            return false;
        }
        if (!Objects.equals(this.payment, other.payment)) {
            return false;
        }
        if (!Objects.equals(this.payment_concept_list, other.payment_concept_list)) {
            return false;
        }
        if (!Objects.equals(this.user_copy, other.user_copy)) {
            return false;
        }
        return Objects.equals(this.water_intake_copy, other.water_intake_copy);
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

    public boolean isDocument_list_valid() {
        return document_list_valid;
    }

    public void setDocument_list_valid(boolean document_list_valid) {
        this.document_list_valid = document_list_valid;
    }

}
