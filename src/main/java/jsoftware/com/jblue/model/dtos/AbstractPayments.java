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
package jsoftware.com.jblue.model.dtos;

import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.ObjectUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author juanp
 */
public class AbstractPayments extends Objects implements ForeingKeyObject, StatusObject {

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

    public String getMonthName() {
        return info[4];
    }

    public String getMonth() {
        return info[5];
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(info[6]);
    }

    @Override
    public String getStatusString() {
        return CacheFactory.ITEMS_STATUS_CAT[getStatus()];
    }

    public LocalDateTime getRegister() {
        return LocalDateTime.parse(info[7], DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    @Override
    public void setData(String[] info) {
        super.setData(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        info_fk = info.clone();

    }

    @Override
    public String[] getData() {
        return super.getData(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String[] getInfoSinFK() {
        info_fk[1] = getEmployeeObject().toString();
        OUser u = getUserObject();
        info_fk[2] = u == null ? "USUARIO BORRADO" : u.toString();
        info_fk[6] = getStatusString();
        return info_fk;
    }

    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

}
