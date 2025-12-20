/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.io.Serializable;
import java.util.List;
import jsoftware.com.jutil.model.dto.DtoMapModel;

/**
 *
 * @author juanp
 */
public interface ListComponentDAO <T extends DtoMapModel> extends Serializable {

    public List<T> getList();
}
