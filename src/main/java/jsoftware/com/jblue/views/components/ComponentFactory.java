/*
 * Copyright (C) 2025 juanp
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
package jsoftware.com.jblue.views.components;

import jsoftware.com.jblue.sys.app.AppFiles;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 *
 * @author juanp
 */
public class ComponentFactory {

    public static File getFileChooser(JComponent parent, String txt) {
        JFileChooser file = new JFileChooser(AppFiles.DIR_USER);
        file.setDialogType(JFileChooser.SAVE_DIALOG);
        file.setMultiSelectionEnabled(false);
        file.showDialog(parent, txt);
        return file.getSelectedFile();
    }

}
