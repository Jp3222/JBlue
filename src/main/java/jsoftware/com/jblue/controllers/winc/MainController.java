/*
 * Copyright (C) 2024 juan pablo campos casasanero
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
package jsoftware.com.jblue.controllers.winc;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.model.exp.imp.CorruptUpdateException;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.service.LoginService;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.win.WMainMenu;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juan pablo campos casasanero
 */
public class MainController extends WindowController {

    private static final long serialVersionUID = 1L;

    private final WMainMenu view;
    private final LoginService service;

    public MainController(WMainMenu view) {
        this.view = view;
        this.service = new LoginService(AppConfig.isDevMessages(), "LOGIN");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand == null || actionCommand.isBlank()) {
            JOptionPane.showMessageDialog(view, "El comando %s no es valido".formatted(actionCommand));
            return;
        }
        if (view.getABOUT().getName().equals(actionCommand)) {
            view.getABOUT().setVisible(true);
            return;
        }
        if (view.getProfileWindow().getName().equals(actionCommand)) {
            view.getProfileWindow().setVisible(true);
            return;
        }
        if (isExit(actionCommand)) {
            return;
        }
        if (isNotAvailable(actionCommand)) {
            return;
        }
        view.getLabelTitle().setText(actionCommand);
        view.getCardLayout().show(view.getViewsPanel(), actionCommand);
        view.updateTitle(actionCommand);
    }

    public boolean isExit(String actionCommand) {
        boolean out = actionCommand.equals(WMainMenu.OUT);
        view.closeWindows();
        if (out) {
            view.dispose();
        }
        return out;
    }

    public boolean isNotAvailable(String actionCommand) {
        boolean out = true;
        for (Component i : view.getViewsPanel().getComponents()) {
            if (i.getName() != null && i.getName().equalsIgnoreCase(actionCommand)) {
                out = false;
                break;
            }
        }
        if (out) {
            JOptionPane.showMessageDialog(view, "La vista \"%s\" No esta disponible".formatted(actionCommand));
        }
        return out;
    }

    @Override
    public void windowClosing(WindowEvent we) {

    }

    @Override
    public void windowClosed(WindowEvent we) {
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection()) {
            service.logout(connection);
        } catch (SQLException | CorruptUpdateException ex) {
            log(ex, "windowClosed");
        }
    }

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, "MAIN", method_name, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void log(String message) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, "MAIN", message);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
