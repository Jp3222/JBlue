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
package com.jblue.modelo.objetos;

import com.jblue.util.tools.ObjectUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.jblue.util.objetos.ForeingKeyObject;

/**
 *
 * @author juanp
 */
public class AbstractPayments extends Objeto implements ForeingKeyObject {

    private String[] info_fk;

    public AbstractPayments() {
    }

    public AbstractPayments(String[] info) {
        super(info);
    }

    public String getEmployee() {
        return info[1];
    }

    public OEmployee getEmployeeObject() {
        return ObjectUtils.getEmployee(getEmployee());
    }

    public String getUser() {
        return info[2];
    }

    public OUser getUserObject() {
        return ObjectUtils.getUser(getUser());
    }

    public double getPrice() {
        return Double.parseDouble(info[3]);
    }

    public String getMonth() {
        return info[4];
    }

    public int getStatus() {
        return Integer.parseInt(info[5]);
    }

    public String getStatusString() {
        return switch (getStatus()) {
            case 2:
                yield "PENDIENTE";
            case 3:
                yield "ELIMINADO";
            default:
                yield "PAGADO";
        };
    }

    public LocalDateTime getRegister() {
        return LocalDateTime.parse(info[6], DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        info_fk = info.clone();

    }

    @Override
    public String[] getInfo() {
        return super.getInfo(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String[] getInfoSinFK() {
        info_fk[1] = getEmployeeObject().toString();
        OUser u = getUserObject();
        info_fk[2] = u == null ? "USUARIO BORRADO": u.toString();
        info_fk[5] = getStatusString();
        return info_fk;
    }

}
