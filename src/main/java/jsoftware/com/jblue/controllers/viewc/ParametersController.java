/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.dtos.OUser;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jblue.views.ParametersView;

/**
 *
 * @author juanp
 */
public class ParametersController extends AbstractDBViewController<OUser> {

    private static final long serialVersionUID = 1L;
    private final ParametersView view;

    /**
     *
     * @param memo_cache
     * @param view
     */
    public ParametersController(MemoListCache<OUser> memo_cache, ParametersView view) {
        super(memo_cache);
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void save() {
    }

    @Override
    public void delete() {
    }

    @Override
    public void update() {
    }

    @Override
    public void cancel() {
        returnMessage(view, false, "Operacion Cancelada");
    }

}
