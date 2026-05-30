/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.dto.PaymentListDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypeDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;

/**
 *
 * @author juanp
 */
public class ShopCartWrapperDTO extends ModuleWrapperDTO {

    private static final long serialVersionUID = 1L;
    //DATOS PARA REALIZAR UN PAGO 
    private final PaymentDTO payment_header;
    private final List<PaymentListDTO> payment_details;

    //DATOS DE PAGOS ANTERIORES
    private final List<PaymentDTO> payments_header_made;
    private final List<PaymentListDTO> payments_details_made;

    //INFORMACION DEL PADRON
    private final WaterIntakeUserDTO wki_user;
    private final WaterIntakeTypeDTO wki_type;

    public ShopCartWrapperDTO(String module_id, String module_name) {
        super(module_id, module_name);
        payment_header = new PaymentDTO();
        payment_details = new ArrayList<>();
        payments_header_made = new ArrayList<>();
        payments_details_made = new ArrayList<>();
        wki_user = new WaterIntakeUserDTO();
        wki_type = new WaterIntakeTypeDTO();
    }

    public PaymentDTO getPayment_header() {
        return payment_header;
    }

    public List<PaymentListDTO> getPayment_details() {
        return payment_details;
    }

    public List<PaymentDTO> getPayments_header_made() {
        return payments_header_made;
    }

    public List<PaymentListDTO> getPayments_details_made() {
        return payments_details_made;
    }

    public WaterIntakeUserDTO getWki_user() {
        return wki_user;
    }

    public WaterIntakeTypeDTO getWki_type() {
        return wki_type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.payment_header);
        hash = 29 * hash + Objects.hashCode(this.payment_details);
        hash = 29 * hash + Objects.hashCode(this.payments_header_made);
        hash = 29 * hash + Objects.hashCode(this.payments_details_made);
        hash = 29 * hash + Objects.hashCode(this.wki_user);
        hash = 29 * hash + Objects.hashCode(this.wki_type);
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
        final ShopCartWrapperDTO other = (ShopCartWrapperDTO) obj;
        if (!Objects.equals(this.payment_header, other.payment_header)) {
            return false;
        }
        if (!Objects.equals(this.payment_details, other.payment_details)) {
            return false;
        }
        if (!Objects.equals(this.payments_header_made, other.payments_header_made)) {
            return false;
        }
        if (!Objects.equals(this.payments_details_made, other.payments_details_made)) {
            return false;
        }
        if (!Objects.equals(this.wki_user, other.wki_user)) {
            return false;
        }
        return Objects.equals(this.wki_type, other.wki_type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShopCartWrapperDTO{");
        sb.append("payment_header=").append(payment_header);
        sb.append(", payment_details=").append(payment_details);
        sb.append(", payments_header_made=").append(payments_header_made);
        sb.append(", payments_details_made=").append(payments_details_made);
        sb.append(", wki_user=").append(wki_user);
        sb.append(", wki_type=").append(wki_type);
        sb.append('}');
        return sb.toString();
    }
}
