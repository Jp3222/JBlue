/*
 * Copyright (C) 2025 juan pablo campos casasanero
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
package jsoftware.com.jblue.model.factories;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.db.JDBConnectionBuilder;

/**
 *
 * @author juan pablo campos casasanero
 */
public class ConnectionFactory {

    private static ConnectionFactory intance;

    public static synchronized ConnectionFactory getIntance(JDBConnectionBuilder builder) {
        if (intance == null) {
            intance = new ConnectionFactory(builder);
        }
        return intance;
    }

    public static ConnectionFactory getIntance() {
        return intance;
    }

    private JDBConnectionBuilder builder;
    private HikariDataSource data_source;

    private ConnectionFactory(JDBConnectionBuilder builder) {
        this.builder = builder;
        this.data_source = new HikariDataSource(builder.getDb_config());
    }

    public synchronized JDBConnection getPaymentsConnection() throws SQLException {
        return new JDBConnection(builder, data_source.getConnection());
    }

    public synchronized JDBConnection getWaterIntakeConnection() throws SQLException {
        return new JDBConnection(builder, data_source.getConnection());
    }

    public synchronized JDBConnection getHistoryConnection() throws SQLException {
        return new JDBConnection(builder, data_source.getConnection());
    }

    public synchronized JDBConnection getMainConnection() throws SQLException {
        return new JDBConnection(builder, data_source.getConnection());
    }

    public synchronized JDBConnection getCacheConnection() throws SQLException {
        return new JDBConnection(builder, data_source.getConnection());
    }

    public synchronized boolean isOpen() {
        if (builder == null) {
            return false;
        }
        if (data_source == null) {
            return false;
        }
        return true;
    }

    public synchronized void close() {
        data_source.close();
    }
}
