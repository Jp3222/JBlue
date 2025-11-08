/*
 * Copyright (C) 2025 juan-campos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jsoftware.com.jblue.model.l4b;

import java.util.List;
import jsoftware.com.jblue.model.dtos.OUser;
import jsoftware.com.jblue.model.dtos.OWaterIntakeTypes;
import jsoftware.com.jblue.model.dtos.OWaterIntakes;

/**
 *
 * @author juan-campos
 */
public class PaymentBuilder {

    public static PaymentModel getServicePayment() {
        return new ServicePaymentLogic();
    }

    public static PaymentModel getSurchargePayment() {
        return new SurchargePaymentsLogic();
    }

    public static PaymentModel getOtherPayment() {
        return new OthersPaymentLogic();
    }

    public static class Builder {

        private OUser user;
        private OWaterIntakes water_intake;
        private OWaterIntakeTypes water_intake_type;
        private double input_money;
        private double deuda;
        private double output_money;
        private List<String> month_paid;
        private String pay_query;
        private String default_query;

        public OUser getUser() {
            return user;
        }

        public Builder setUser(OUser user) {
            this.user = user;
            return this;
        }

        public OWaterIntakes getWater_intake() {
            return water_intake;
        }

        public Builder setWater_intake(OWaterIntakes water_intake) {
            this.water_intake = water_intake;
            return this;
        }

        public OWaterIntakeTypes getWater_intake_type() {
            return water_intake_type;
        }

        public Builder setWater_intake_type(OWaterIntakeTypes water_intake_type) {
            this.water_intake_type = water_intake_type;
            return this;
        }

        public double getInput_money() {
            return input_money;
        }

        public Builder setInput_money(double input_money) {
            this.input_money = input_money;
            return this;
        }

        public double getDeuda() {
            return deuda;
        }

        public Builder setDeuda(double deuda) {
            this.deuda = deuda;
            return this;
        }

        public double getOutput_money() {
            return output_money;
        }

        public Builder setOutput_money(double output_money) {
            this.output_money = output_money;
            return this;
        }

        public List<String> getMonth_paid() {
            return month_paid;
        }

        public Builder setMonthPaid(List<String> meses_pagados) {
            this.month_paid = meses_pagados;
            return this;
        }


        public String getPay_query() {
            return pay_query;
        }

        public Builder setPay_query(String pay_query) {
            this.pay_query = pay_query;
            return this;
        }

        public String getDefault_query() {
            return default_query;
        }

        public Builder setDefault_query(String default_query) {
            this.default_query = default_query;
            return this;
        }

        public PaymentModel buildServicePayment() {
            return new ServicePaymentLogic(this, PaymentModel.SERVICE_PAYMENT);
        }
    }
}
