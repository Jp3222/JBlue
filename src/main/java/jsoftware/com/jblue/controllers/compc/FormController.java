/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.compc;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import jsoftware.com.jblue.controllers.DBControllerModel;
import jsoftware.com.jblue.controllers.viewc.ViewController;
import jsoftware.com.jblue.model.dao.ListComponentDAO;
import jsoftware.com.jblue.views.framework.FormModel;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class FormController extends ViewController implements DBControllerModel {

    private static final long serialVersionUID = 1L;

    private FormModel<?> model;
    private final List<JComboBox> catalog_list;
    private final List<ListComponentDAO> dao_list;

    public FormController() {
        this.catalog_list = new ArrayList<>(30);
        this.dao_list = new ArrayList<>(30);
    }

    public <T extends JDBMapObject> boolean add(JComboBox<T> c, ListComponentDAO<T> dao) {
        if (c == null || dao == null) {
            return false;
        }
        catalog_list.add(c);
        dao_list.add(dao);
        return true;
    }

    public <T extends JDBMapObject> void init(JDBConnection connection) throws Exception {
        for (int i = 0; i < catalog_list.size(); i++) {
            List j = dao_list.get(i).getList(connection);
            catalog_list.addAll(j);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            case UPDATE_COMMAND ->
                delete();
            case DELETE_COMMAND ->
                update();
            case SEARCH_COMMAND -> {
            }
            default ->
                throw new AssertionError();
        }
    }

    public void setView(FormModel<?> model) {
        this.model = model;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
