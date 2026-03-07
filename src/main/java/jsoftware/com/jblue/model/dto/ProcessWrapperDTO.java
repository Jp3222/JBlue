/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private EmployeeDTO current_employee;
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
    private int process_id;
    private int last_process_type;

    //Datos originales antes de un update
    private UserDTO user_copy;
    private WaterIntakeDTO water_intake_copy;
    private WaterIntakeUserDTO wki_user_copy;

    public ProcessWrapperDTO(EmployeeDTO employee) {
        this.user = new UserDTO();
        this.user_document_list = new ArrayList<>(15);
        this.water_intake = new WaterIntakeDTO();
        this.water_intake_type = new WaterIntakeTypeDTO();
        this.payment = new PaymentDTO();
        this.payment_concept_list = new ArrayList<>(15);
        this.wki_user = new WaterIntakeUserDTO();
        this.user_valid = false;
        this.user_document_valid = false;
        this.water_intake_valid = false;
        this.payment_valid = false;
    }

    public EmployeeDTO getCurrent_employee() {
        return current_employee;
    }

    public void setCurrent_employee(EmployeeDTO current_employee) {
        this.current_employee = current_employee;
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

    public int getProcess_id() {
        return process_id;
    }

    public void setProcess_id(int process_id) {
        this.process_id = process_id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("DATOS DE USUARIO:");
        sb.append(user.getMap().toString()).append("\n");

        sb.append("DATOS DE DOCUMENTOS DE USUARIO:");
        for (UserDocumentDTO i : user_document_list) {
            sb.append(i.getMap().toString()).append("\n");
        }

        sb.append("DATOS DE LA TOMA:");
        sb.append(water_intake.getMap().toString()).append("\n");

        sb.append("DATOS DEL TIPO DE TOMA:");
        sb.append(water_intake_type.getMap().toString()).append("\n");

        sb.append("DATOS DE LA CABEZERA DE PAGO:");
        sb.append(payment.getMap().toString()).append("\n");

        sb.append("DATOS DE LOS CONCEPTOS DE PAGO:");
        for (PaymentListDTO i : payment_concept_list) {
            sb.append(i.getMap().toString()).append("\n");
        }
        sb.append("DATOS DE USUARIO EN EL PADRON:");
        sb.append(wki_user.getMap().toString()).append("\n");
        return sb.toString();
    }

    public void updateToStarted() {
        for (UserDocumentDTO i : user_document_list) {
            i.put("user_id", user.getId());
        }
        water_intake.put("owner_name", user.toString());
        payment.put("user", user.getId());
    }

    public void updateToValidated() {

    }

    public void updateToRegistered() {
        payment.put("water_intake", water_intake.getId());
        payment.put("water_intake_type", water_intake.getWaterIntakeType());
    }

    public void updateToPaid() {
        wki_user.put("user_id", user.getId());
        wki_user.put("water_intake_id", water_intake.getId());
        wki_user.put("water_intake_type_id", water_intake.getWaterIntakeType());
        wki_user.put("original_process", process_id);
        wki_user.put("last_process_type", last_process_type);

    }

}
