/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import jsoftware.com.jblue.controllers.Controller;
import javax.swing.JPanel;
import jsoftware.com.jutil.framework.ViewStates;

/**
 *
 * @author jp
 */
public abstract class SimpleView extends JPanel implements ViewStates {

    protected Controller controller;

    public SimpleView() {
    }

}
