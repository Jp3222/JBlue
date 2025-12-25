/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.views.framework;

import java.io.Serializable;

/**
 *
 * @author juanp
 */
public interface ProcessViewModel extends Serializable {

    public void setProcessName(String module_name);

    public String getProcessName();

    public void setProcess(boolean flag);

    public boolean isProcess();
}
