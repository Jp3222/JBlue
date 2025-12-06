/*
 * Copyright (C) 2025 juanp
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
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import jsoftware.com.jblue.controllers.AbstractViewController;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jblue.views.VContabilidad;

/**
 *
 * @author juanp
 */
public final class ContableController extends AbstractViewController {

    private MemoListCache<PaymentDTO> cache;
    private VContabilidad view;
    private double total;

    public ContableController(VContabilidad view) {
        this.view = view;
        load();
    }

    public void load() {
        this.totalOfDay();
        this.totalOfMonth();
        this.totalOfYear();
        view.setTotal_field(total);
    }

    private void totalOfDay() {

    }

    private void totalOfMonth() {

    }

    private void totalOfYear() {

    }

    public String sum_total(boolean month, boolean day) {
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
