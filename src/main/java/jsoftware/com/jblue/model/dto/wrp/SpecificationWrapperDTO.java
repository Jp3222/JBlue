/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import jsoftware.com.jpaymentlib.model.dto.PaymentConceptDTO;
import jsoftware.com.jpaymentlib.model.dto.PaymentImportDTO;
import jsoftware.com.jpaymentlib.model.dto.PaymentRulersDTO;
import jsoftware.com.jpaymentlib.model.dto.PaymentSpecificationDTO;

/**
 *
 * @author juanp
 */
public class SpecificationWrapperDTO extends ModuleWrapperDTO {

    private static final long serialVersionUID = 1L;

    private PaymentSpecificationDTO payment_specification;
    private boolean payment_specification_valid;
    private boolean payment_specification_exist;

    private PaymentConceptDTO payment_concept;
    private boolean payment_concept_valid;
    private boolean payment_concept_exist;

    private PaymentImportDTO payment_import;
    private boolean payment_import_valid;
    private boolean payment_import_exist;

    private PaymentRulersDTO payment_ruler;
    private boolean payment_ruler_valid;
    private boolean payment_ruler_exist;

    public SpecificationWrapperDTO(String module_id, String module_name, int transaction_type_mov, String observation) {
        super(module_id, module_name, transaction_type_mov, observation);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PaymentSpecificationDTO getPayment_specification() {
        return payment_specification;
    }

    public void setPayment_specification(PaymentSpecificationDTO payment_specification) {
        this.payment_specification = payment_specification;
    }

    public boolean isPayment_specification_valid() {
        return payment_specification_valid;
    }

    public void setPayment_specification_valid(boolean payment_specification_valid) {
        this.payment_specification_valid = payment_specification_valid;
    }

    public boolean isPayment_specification_exist() {
        return payment_specification_exist;
    }

    public void setPayment_specification_exist(boolean payment_specification_exist) {
        this.payment_specification_exist = payment_specification_exist;
    }

    public PaymentConceptDTO getPayment_concept() {
        return payment_concept;
    }

    public void setPayment_concept(PaymentConceptDTO payment_concept) {
        this.payment_concept = payment_concept;
    }

    public boolean isPayment_concept_valid() {
        return payment_concept_valid;
    }

    public void setPayment_concept_valid(boolean payment_concept_valid) {
        this.payment_concept_valid = payment_concept_valid;
    }

    public boolean isPayment_concept_exist() {
        return payment_concept_exist;
    }

    public void setPayment_concept_exist(boolean payment_concept_exist) {
        this.payment_concept_exist = payment_concept_exist;
    }

    public PaymentImportDTO getPayment_import() {
        return payment_import;
    }

    public void setPayment_import(PaymentImportDTO payment_import) {
        this.payment_import = payment_import;
    }

    public boolean isPayment_import_valid() {
        return payment_import_valid;
    }

    public void setPayment_import_valid(boolean payment_import_valid) {
        this.payment_import_valid = payment_import_valid;
    }

    public boolean isPayment_import_exist() {
        return payment_import_exist;
    }

    public void setPayment_import_exist(boolean payment_import_exist) {
        this.payment_import_exist = payment_import_exist;
    }

    public PaymentRulersDTO getPayment_ruler() {
        return payment_ruler;
    }

    public void setPayment_ruler(PaymentRulersDTO payment_ruler) {
        this.payment_ruler = payment_ruler;
    }

    public boolean isPayment_ruler_valid() {
        return payment_ruler_valid;
    }

    public void setPayment_ruler_valid(boolean payment_ruler_valid) {
        this.payment_ruler_valid = payment_ruler_valid;
    }

    public boolean isPayment_ruler_exist() {
        return payment_ruler_exist;
    }

    public void setPayment_ruler_exist(boolean payment_ruler_exist) {
        this.payment_ruler_exist = payment_ruler_exist;
    }



}
