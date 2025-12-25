/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.util.ArrayList;
import java.util.Objects;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class ProcessWrapperDTO extends JDBMapObject {

    private EmployeeDTO current_employee;
    private UserDTO user;
    private WaterIntakeTypesDTO water_intake_type;
    private WaterIntakesDTO water_intake;
    private PaymentDTO payment;
    private ArrayList<PaymentConceptDTO> payment_concept_list;

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

    public ArrayList<PaymentConceptDTO> getPayment_concept_list() {
        return payment_concept_list;
    }

    public void setPayment_concept_list(ArrayList<PaymentConceptDTO> payment_concept_list) {
        this.payment_concept_list = payment_concept_list;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.current_employee);
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + Objects.hashCode(this.water_intake_type);
        hash = 97 * hash + Objects.hashCode(this.water_intake);
        hash = 97 * hash + Objects.hashCode(this.payment);
        hash = 97 * hash + Objects.hashCode(this.payment_concept_list);
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
        if (!Objects.equals(this.water_intake_type, other.water_intake_type)) {
            return false;
        }
        if (!Objects.equals(this.water_intake, other.water_intake)) {
            return false;
        }
        if (!Objects.equals(this.payment, other.payment)) {
            return false;
        }
        return Objects.equals(this.payment_concept_list, other.payment_concept_list);
    }

    @Override
    public String toString() {
        return "ProcessWrapperDTO{" + "current_employee=" + current_employee + ", user=" + user + ", water_intake_type=" + water_intake_type + ", water_intake=" + water_intake + ", payment=" + payment + ", payment_concept_list=" + payment_concept_list + '}';
    }

}
