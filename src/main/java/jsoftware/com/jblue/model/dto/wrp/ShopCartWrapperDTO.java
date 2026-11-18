/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.vw.WaterIntakeUserPaymentDTO;
import jsoftware.com.jpaymentlib.model.dto.wrp.PaymentWrapper;

/**
 *
 * @author juanp
 */
public class ShopCartWrapperDTO extends ModuleWrapperDTO {

    private static final long serialVersionUID = 1L;

    private WaterIntakeUserDTO wki_user;
    private WaterIntakeUserPaymentDTO wki_usr_pym;
    private PaymentWrapper pym_wrp;
    private List<Integer> months;
    private boolean month_list_valid;
    private boolean payment_valid;

    public ShopCartWrapperDTO(String module_id, String module_name) {
        super(module_id, module_name);
        wki_user = new WaterIntakeUserDTO();
        wki_usr_pym = new WaterIntakeUserPaymentDTO();
        pym_wrp = new PaymentWrapper();
        month_list_valid = false;
        payment_valid = false;
        months=new ArrayList<>(12);
    }

    public WaterIntakeUserDTO getWki_user() {
        return wki_user;
    }

    public void setWki_user(WaterIntakeUserDTO wki_user) {
        this.wki_user = wki_user;
    }

    public WaterIntakeUserPaymentDTO getWki_usr_pym() {
        return wki_usr_pym;
    }

    public void setWki_usr_pym(WaterIntakeUserPaymentDTO wki_usr_pym) {
        this.wki_usr_pym = wki_usr_pym;
    }

    public PaymentWrapper getPym_wrp() {
        return pym_wrp;
    }

    public void setPym_wrp(PaymentWrapper pym_wrp) {
        this.pym_wrp = pym_wrp;
    }

    public boolean isMonth_list_valid() {
        return month_list_valid;
    }

    public void setMonth_list_valid(boolean month_list_valid) {
        this.month_list_valid = month_list_valid;
    }

    public boolean isPayment_valid() {
        return payment_valid;
    }

    public void setPayment_valid(boolean payment_valid) {
        this.payment_valid = payment_valid;
    }

    public List<Integer> getMonths() {
        return months;
    }

    public void setMonths(List<Integer> months) {
        this.months = months;
    }

    @Override
    public void clear() {
        wki_user = new WaterIntakeUserDTO();
        wki_usr_pym = new WaterIntakeUserPaymentDTO();
        pym_wrp = new PaymentWrapper();
        month_list_valid = false;
        payment_valid = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShopCartWrapperDTO{");
        sb.append("wki_user=").append(wki_user);
        sb.append("wki_usr_pym=").append(wki_usr_pym);
        sb.append(", pym_wrp=").append(pym_wrp);
        sb.append(", month_list_valid=").append(month_list_valid);
        sb.append(", payment_valid=").append(payment_valid);
        sb.append(", months=").append(months);
        sb.append('}');
        return sb.toString();
    }

}
