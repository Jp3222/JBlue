/*
 * Copyright (C) 2023 jp
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
package com.jblue.vista.jbmarco;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author jp
 */
public class VentanaExtendida extends VentanaSimple {
    public VentanaExtendida() {
    }

    public VentanaExtendida(String[] _TITULOS_VEN) throws HeadlessException {
        super(_TITULOS_VEN);
    }

    public VentanaExtendida(int _TITULO, String[] _TITULOS_VEN) throws HeadlessException {
        super(_TITULO, _TITULOS_VEN);
    }
    protected void evtCambios(JMenuBar menu_bar, CardLayout ly, VistaExtendida vista, int titulo) {
        if (vista == null) {
            return;
        }
        ly.show(getContentPane(), vista.getName());
        _TITULO = titulo;
        
        updateTitle();
        
        menu_bar.removeAll();
        
        if (vista.getMenu() != null && !vista.getMenu().isEmpty()) {
            for (JMenu i : vista.getMenu()) {
                menu_bar.add(i);
            }
        }
        menu_bar.updateUI();
    }

    @Override
    protected void llamable() {

    }

    @Override
    public void componentesEstadoInicial() {
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
