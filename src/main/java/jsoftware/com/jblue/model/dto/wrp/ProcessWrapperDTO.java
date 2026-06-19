/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.dto.PaymentListDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.UserDocumentDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
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
    private UserDTO user;
    private boolean user_valid;

    //PASO 2
    private AddressDTO address;
    private boolean address_valid;

    //PASO 3
    private List<UserDocumentDTO> document_list;
    private boolean document_list_valid;

    private WaterIntakeDTO water_intake;
    private boolean water_intake_valid;

    //PASO 4
    private PaymentDTO payment_header;
    private boolean payment_header_valid;

    //PASO 5
    private List<PaymentListDTO> payment_details;
    private boolean payment_detail_valid;

    //PASO 6
    private WaterIntakeUserDTO wki_user;
    private boolean wki_user_valid;

    public ProcessWrapperDTO(String module_id, String module_name) {
        super(module_id, module_name, "3", "REGISTRO DEL CONTRIBUYENTE POR MODULO DEL SISTEMA");
        this.clear();
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

    public List<UserDocumentDTO> getDocument_list() {
        return document_list;
    }

    public void setDocument_list(List<UserDocumentDTO> document_list) {
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

    public PaymentDTO getPayment_header() {
        return payment_header;
    }

    public void setPayment_header(PaymentDTO payment_header) {
        this.payment_header = payment_header;
    }

    public boolean isPayment_header_valid() {
        return payment_header_valid;
    }

    public void setPayment_header_valid(boolean payment_header_valid) {
        this.payment_header_valid = payment_header_valid;
    }

    public List<PaymentListDTO> getPayment_details() {
        return payment_details;
    }

    public void setPayment_details(List<PaymentListDTO> payment_details) {
        this.payment_details = payment_details;
    }

    public boolean isPayment_detail_valid() {
        return payment_detail_valid;
    }

    public void setPayment_detail_valid(boolean payment_detail_valid) {
        this.payment_detail_valid = payment_detail_valid;
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
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + (this.user_valid ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + (this.address_valid ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.document_list);
        hash = 47 * hash + (this.document_list_valid ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.water_intake);
        hash = 47 * hash + (this.water_intake_valid ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.payment_header);
        hash = 47 * hash + (this.payment_header_valid ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.payment_details);
        hash = 47 * hash + (this.payment_detail_valid ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.wki_user);
        hash = 47 * hash + (this.wki_user_valid ? 1 : 0);
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
        if (this.address_valid != other.address_valid) {
            return false;
        }
        if (this.document_list_valid != other.document_list_valid) {
            return false;
        }
        if (this.water_intake_valid != other.water_intake_valid) {
            return false;
        }
        if (this.payment_header_valid != other.payment_header_valid) {
            return false;
        }
        if (this.payment_detail_valid != other.payment_detail_valid) {
            return false;
        }
        if (this.wki_user_valid != other.wki_user_valid) {
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
        if (!Objects.equals(this.payment_header, other.payment_header)) {
            return false;
        }
        if (!Objects.equals(this.payment_details, other.payment_details)) {
            return false;
        }
        return Objects.equals(this.wki_user, other.wki_user);
    }

    @Override
    public final void clear() {
        user = new UserDTO();
        address = new AddressDTO();
        document_list = new ArrayList<>();
        water_intake = new WaterIntakeDTO();
        payment_header = new PaymentDTO();
        payment_details = new ArrayList<>();
        wki_user = new WaterIntakeUserDTO();
        user_valid = false;
        address_valid = false;
        document_list_valid = false;
        water_intake_valid = false;
        payment_header_valid = false;
        payment_detail_valid = false;
        wki_user_valid = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProcessWrapperDTO{");
        sb.append("user=").append(user);
        sb.append(", user_valid=").append(user_valid);
        sb.append(", address=").append(address);
        sb.append(", address_valid=").append(address_valid);
        sb.append(", document_list=").append(document_list);
        sb.append(", document_list_valid=").append(document_list_valid);
        sb.append(", water_intake=").append(water_intake);
        sb.append(", water_intake_valid=").append(water_intake_valid);
        sb.append(", payment_header=").append(payment_header);
        sb.append(", payment_header_valid=").append(payment_header_valid);
        sb.append(", payment_details=").append(payment_details);
        sb.append(", payment_detail_valid=").append(payment_detail_valid);
        sb.append(", wki_user=").append(wki_user);
        sb.append(", wki_user_valid=").append(wki_user_valid);
        sb.append('}');
        return sb.toString();
    }

}
