/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dtos;

import jsoftware.com.jblue.model.factories.CacheFactory;

/**
 *
 * @author jp
 */
public class OStreet extends Objects implements StatusObject {

    public OStreet(String... info) {
        super(info);
    }

    public OStreet() {
        super();
    }

    public String getNombre() {
        return info[1];
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(info[2]);
    }

    @Override
    public String getStatusString() {
        return CacheFactory.ITEMS_STATUS_CAT[getStatus()];
    }

    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
