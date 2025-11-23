/*
 * Copyright (C) 2025 juan-campos
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.db.JDBConnectionBuilder;

/**
 *
 * @author juan-campos
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
    
    private JDBConnection payments_connection;
    private JDBConnection water_intake_connection;
    private JDBConnection history_connection;
    private JDBConnection main_connection;
    private JDBConnectionBuilder builder;
    private List<JDBConnection> connection_list;
    
    private ConnectionFactory(JDBConnectionBuilder builder) {
        connection_list = new ArrayList<>(4);
        connection_list.add(payments_connection);
        connection_list.add(water_intake_connection);
        connection_list.add(history_connection);
        connection_list.add(main_connection);
    }
    
    public boolean openFactory() throws SQLException {
        for (JDBConnection i : connection_list) {
            i = new JDBConnection(builder);
            if (i == null) {
                return false;
            }
        }
        return true;
    }
    
    public JDBConnection getPaymentsConnection() {
        if (payments_connection == null) {
            try {
                payments_connection = new JDBConnection(builder);
            } catch (SQLException ex) {
                System.getLogger(ConnectionFactory.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return payments_connection;
    }
    
    public JDBConnection getWater_intake_connection() {
        if (water_intake_connection == null) {
            try {
                water_intake_connection = new JDBConnection(builder);
            } catch (SQLException ex) {
                System.getLogger(ConnectionFactory.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return water_intake_connection;
    }
    
    public JDBConnection getHistory_connection() {
        if (history_connection == null) {
            try {
                history_connection = new JDBConnection(builder);
            } catch (SQLException ex) {
                System.getLogger(ConnectionFactory.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return history_connection;
    }
    
    public JDBConnection getMain_connection() {
        if (main_connection == null) {
            try {
                main_connection = new JDBConnection(builder);
            } catch (SQLException ex) {
                System.getLogger(ConnectionFactory.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return main_connection;
    }
    
    public boolean close() {
        boolean res = false;
        try {
            for (JDBConnection i : connection_list) {
                i.closePool();
            }
            res = true;
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
}
