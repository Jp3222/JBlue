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
package jsoftware.com.jblue.util.cache;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.db.JDBTable;
import jsoftware.com.jutil.db.model.JDBObject;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractCache<T extends JDBObject> implements CacheModel<T> {

    private static final long serialVersionUID = 1L;

    /**
     * En este map se almacena el query(key) y la coleccion(value)
     */
    protected Map<String, Collection<T>> cache;
    /**
     * Query que retorna el total de datos de una tabla
     */
    private final String count_query;
    /**
     * Query por defecto para la extraccion de datos
     */
    private final String default_query;
    /**
     * query actual que se ejecuta en el buffer
     */
    protected String current_query;
    /**
     * id maximo del rango
     */
    protected int min_id;
    /**
     * id minimo del rangp
     */
    protected int max_id;
    protected int range;
    protected JDBTable table;
    protected Class<T> cls;

    public AbstractCache(JDBTable table, int range, Class<T> cls) {
        this.count_query = "SELECT COUNT(*) FROM %s".formatted(table.getTableName());
        this.default_query = "SELECT * FROM %s WHERE status !=3 AND id >= %s AND id <= %s ORDER BY id".formatted(table.getTableName(), "1", range);
        this.current_query = default_query;
        this.cache = new HashMap(300);
        this.min_id = 1;
        this.max_id = range;
        this.range = range;
        this.table = table;
        this.cls = cls;
    }

    public AbstractCache(JDBTable table, Class<T> cls) {
        this(table, MIN, cls);
    }

    @Override
    public long count() {
        int res = -1;
        try (JDBConnection conn = connection(); PreparedStatement ps = conn.getNewPreparedStatement(count_query); ResultSet rs = ps.executeQuery();) {
            if (rs.next()) {
                res = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.getLogger(AbstractCache.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
    }

    public JDBConnection connection() throws SQLException {
        return ConnectionFactory.getIntance().getCacheConnection();
    }

    public void setCurrentQuery(String current_query) {
        this.current_query = current_query;
    }

    public void setCurrent_query(String current_query) {
        this.current_query = current_query;
    }

    public String getCurrent_query() {
        return current_query;
    }

    public int getMaxId() {
        return max_id;
    }

    public int getMinId() {
        return min_id;
    }

}
