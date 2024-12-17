/*
 * Copyright (C) 2024 juan-campos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.controlador;

import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.fabricas.FabricaCache;
import com.jblue.modelo.fabricas.FabricaFuncionesBD;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.sistema.Sesion;
import com.jblue.vista.marco.vistas.SimpleView;
import com.jutil.swingw.modelos.JTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 */
public class CCobros {

    public static double getTotal(OUsuarios user, int meses_seleccionados) {
        if (user == null) {
            return -1;
        }
        OTipoTomas get = FabricaCache.TIPO_DE_TOMAS.get((t) -> user.getToma().equals(t.getId()));
        double total = meses_seleccionados * get.getCosto();
        return total;
    }

    public static Map<String, String> regPagoxServicio(OUsuarios usuario, String[] meses, int monto_ingresado) {
        OPersonal personal = Sesion.getInstancia().getUsuario();
        return CPagos.getInstancia().regPagoXServicio(personal, usuario, meses, 0);

    }

    public static void printPaidsOfDay(JTableModel model) {
        try {
            FuncionesBD<OPagosServicio> fun = FabricaFuncionesBD.getPagosXServicio();
            LocalDate ld = LocalDate.now();
            final String query = "dia = '%s' and mes = '%s' and año = '%s'";
            ArrayList<OPagosServicio> list = fun.getList("*", query.formatted(ld.getDayOfMonth(),
                    ld.getMonthValue(),
                    ld.getYear()));
            if (list.isEmpty()) {
                return;
            }
            
            for (OPagosServicio i : list) {
                model.addData(i.getId(), i.getUsuario(), i.getMesPagado());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void evtCancelar(SimpleView o) {
        int in = JOptionPane.showConfirmDialog(o, "¿Desea cancelar esta operacion?");
        if (in != JOptionPane.OK_OPTION) {
            return;
        }
        o.initialState();
    }

    public static void evtClear(JLabel... o) {
        String txt = "0.0";
        o[0].setText(txt);
        o[1].setText("0.0");
    }
}
