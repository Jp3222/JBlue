/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views;

import jsoftware.com.jblue.model.dao.StatusDAO;
import jsoftware.com.jblue.model.dto.StatusDTO;
import jsoftware.com.jblue.model.factories.TableModelFactory;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public class CatalogViewFactory {

    public static CatalogViewerView<StatusDTO> getStatusType(boolean dev_flag, String name_module) {
        JTableModel table = TableModelFactory.getStatus();
        StatusDAO statusDAO = new StatusDAO(dev_flag, name_module);
        CatalogViewerView<StatusDTO> view = new CatalogViewerView<>(
                table,
                statusDAO
        );
        view.setName(name_module);
        return view;
    }

}
