/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.util.List;
import java.util.Objects;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class ProcessWrapperDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    private EmployeeDTO current_employee;
    private UserDTO user;
    private List<UserDocumentDTO> user_document_list;
    private WaterIntakeTypesDTO water_intake_type;
    private WaterIntakesDTO water_intake;
    private PaymentDTO payment;
    private List<PaymentListDTO> payment_concept_list;

    /**/
    private UserDTO user_copy;
    private WaterIntakesDTO water_intake_copy;

    public ProcessWrapperDTO(EmployeeDTO employee) {
        this.current_employee = new EmployeeDTO();
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

    public WaterIntakeTypesDTO getWater_intake_type() {
        return water_intake_type;
    }

    public void setWater_intake_type(WaterIntakeTypesDTO water_intake_type) {
        this.water_intake_type = water_intake_type;
    }

    public WaterIntakesDTO getWater_intake() {
        return water_intake;
    }

    public void setWater_intake(WaterIntakesDTO water_intake) {
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

    public WaterIntakesDTO getWater_intake_copy() {
        return water_intake_copy;
    }

    public void setWater_intake_copy(WaterIntakesDTO water_intake_copy) {
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

}
