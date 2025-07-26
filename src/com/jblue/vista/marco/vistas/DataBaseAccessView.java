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
package com.jblue.vista.marco.vistas;

import com.jblue.sistema.Sistema;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.framework.LaunchApp;

/**
 *
 * @author juanp
 */
public abstract class DataBaseAccessView extends SimpleView {

    protected final DBConnection connection;

    public DataBaseAccessView(DBConnection connection) {
        this.connection = connection;
    }

    public DataBaseAccessView() {
        connection = getDefaultConnection();
    }

    protected final DBConnection getDefaultConnection() {
        Object resources = LaunchApp.getInstance().getResources(Sistema.DATA_BASE_KEY);
        return (DBConnection) resources;
    }

    protected abstract void dataLoad();

    protected abstract void dataDump();
}
