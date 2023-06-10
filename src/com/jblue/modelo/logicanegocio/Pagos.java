/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.logicanegocio;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.tiempo.Fecha;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class Pagos {

    private final Operaciones<OPagosServicio> operaciones;

    private OPersonal personal;
    private OUsuarios usuario;
    private OTipoTomas toma;
    private Fecha fecha;
    //

    //
    public Pagos() {
        this.operaciones = FabricaOpraciones.PAGOS_X_SERVICIO;
        fecha = new Fecha();
    }

    public double getDineroIngresado() {
        String dinero = null;
        do {

            dinero = JOptionPane.showInputDialog(null, "Dinero ingresado", "Cobro", JOptionPane.INFORMATION_MESSAGE);
            if (dinero == null) {
                JOptionPane.showMessageDialog(null, "Cobro Cancelado", "Cobro", JOptionPane.INFORMATION_MESSAGE);
                return -1;
            } else if (!dinero.isEmpty() && dinero.matches("([0-9]{1,4})(|(\\.([0-9]{1,2})))")) {
                dinero = dinero.trim();
                break;
            }
            JOptionPane.showMessageDialog(null, "El valor ingresado no es valodo", "Cobro", JOptionPane.ERROR_MESSAGE);
        } while (true);

        return Double.parseDouble(dinero);
    }

    public double hacerPago(int meses_pagados, double total) {

        double dinero_ingresado;

        do {
            dinero_ingresado = getDineroIngresado();
            if (dinero_ingresado == -1) {
                return -1;
            }
            if (dinero_ingresado < total) {
                JOptionPane.showMessageDialog(null, "El valor ingresado es menor al total", "Cobro", JOptionPane.ERROR_MESSAGE);
            }
        } while (dinero_ingresado < total);

        int mes = EnvUsuario.getMesesPagados(fecha.get()[2], usuario.getId());
        ArrayList<String[]> lista = new ArrayList<>(meses_pagados);
        String[] fh = fecha.get();
        int pagos_realizados = 0;

        if (fecha.getNewFechaActual().getMonthValue() < 12) {
            if (mes >= 12) {
                JOptionPane.showMessageDialog(null, "El usuario completo sus 12 pagos, no puede realizar mas.");
                return -1;
            }
        }

        for (int i = 0; i < meses_pagados; i++) {
            if (mes >= 12) {
                mes = 0;
            }
            pagos_realizados += toma.getCosto();
            String[] info = _getInfo(fecha.getMes(mes), fh[0], fh[1], fh[2]);
            lista.add(info);
            mes++;
        }
        for (String[] strings : lista) {
            operaciones.insertar(strings);
        }

        return (dinero_ingresado - total);
    }

    public String[] _getInfo(String... comp) {

        String _personal = this.personal.getId();
        String _usuario = this.usuario.getId();
        String _mes_pagado = comp[0];
        String _monto = String.valueOf(toma.getCosto());
        String _dia = comp[1];
        String _mes = comp[2];
        String _año = comp[3];

        return new String[]{
            _personal, _usuario, _mes_pagado, _monto, _dia, _mes, _año
        };
    }

    public void setUsuario(OUsuarios usuario) {
        this.usuario = usuario;
    }

    public void setPersonal(OPersonal personal) {
        this.personal = personal;
    }

    public void setToma(OTipoTomas toma) {
        this.toma = toma;
    }

}
