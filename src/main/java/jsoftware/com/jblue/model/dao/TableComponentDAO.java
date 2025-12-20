/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.util.List;
import jsoftware.com.jutil.model.dto.DtoMapModel;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public interface TableComponentDAO<T extends DtoMapModel> {

    List<T> getList(JTableModel model);

}
