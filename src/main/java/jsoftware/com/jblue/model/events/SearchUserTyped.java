/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.ShopCarService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.util.FuncSwing;
import jsoftware.com.jblue.views.mod.com.ShopCartView;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class SearchUserTyped implements ActionListener, Serializable {

    private static final long serialVersionUID = 1L;
    private ShopCartView view;
    private ShopCarService service;

    public SearchUserTyped(ShopCarService service) {
        this.service = service;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Esto se ejecuta tras los 600ms de forma segura y sin congelar la pantalla
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection()) {
            SystemSession ss = SystemSession.getInstancia();
            List<WaterIntakeUserDTO> search = service.search(connection, ss, view.getDtoWrapper());
            FuncSwing.updateData(view.getListModel(), search);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void setView(ShopCartView view) {
        this.view = view;
        
    }

}
