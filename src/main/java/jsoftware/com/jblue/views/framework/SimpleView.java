/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import javax.swing.JPanel;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jutil.view.ViewStates;

/**
 *
 * @author jp
 */
public abstract class SimpleView extends JPanel implements ViewStates {

    private static final long serialVersionUID = 1L;

    protected Controller controller;

    public SimpleView() {
    }

}
