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
package jsoftware.com.jblue.views.framework;

import jsoftware.com.jblue.sys.JBlueMainSystem;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.sys.LaunchApp;

/**
 *
 * @author juanp
 */
public abstract class DataBaseAccessView extends SimpleView {

    protected final JDBConnection connection;

    public DataBaseAccessView(JDBConnection connection) {
        this.connection = connection;
    }

    public DataBaseAccessView() {
        connection = getDefaultConnection();
    }

    protected final JDBConnection getDefaultConnection() {
        Object resources = LaunchApp.getInstance().getResources(JBlueMainSystem.DATA_BASE_KEY);
        return (JDBConnection) resources;
    }

    protected abstract void dataLoad();

    protected abstract void dataDump();
}
