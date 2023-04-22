/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.logicanegocio;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OPagosRecargos;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.cache.FabricaOpraciones;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class Recargos {

    public static void generarRecargos() {

        Operaciones<OPagosRecargos> recargos = FabricaOpraciones.PAGOS_X_RECARGOS;
        Operaciones<OPagosServicio> servicio = FabricaOpraciones.PAGOS_X_SERVICIO;
        Operaciones<OUsuarios> usuarios = FabricaOpraciones.USUARIOS;
        
        ArrayList<OUsuarios> _usuarios = usuarios.getLista("estado = 1");
        
        
    }

}
