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
package com.jblue.util.tools;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;

/**
 *
 * @author juan-campos
 */
public class GraphicsUtils {

    public static void disableTreeLock(boolean lock, Component... root){
        lock = !lock;
        for (Component i : root) {
            disableTreeLock(lock, i);
        }
    }
    
    public static void disableTreeLock(boolean lock, Component root) {
        lock = !lock;
        if (root instanceof JComponent jcomponent) {
            jcomponent.setEnabled(lock);
            JPopupMenu jpm = jcomponent.getComponentPopupMenu();
            if (jpm != null) {
                disableTreeLock(lock, root);
            }
        }
        
        Component[] children = null;
        if (root instanceof Container container) {
            children = container.getComponents();
        }
        
        if (children != null) {
            for (Component child : children) {
                disableTreeLock(lock, child);
            }
        }
    }
}
