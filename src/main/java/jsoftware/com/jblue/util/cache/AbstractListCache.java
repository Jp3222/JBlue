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
package jsoftware.com.jblue.util.cache;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.db.JDBMapObject;
import jsoftware.com.jutil.db.JDBTable;

/**
 *
 * @author juan pablo campos casasanero
 * @param <T>
 */
public class AbstractListCache<T extends JDBMapObject> extends AbstractCache<T> implements ListCacheModel<T> {

    private static final long serialVersionUID = 1L;
    private final List<T> current_cache;

    public AbstractListCache(JDBTable table, Class cls) {
        super(table, cls);
        this.current_cache = new ArrayList<>();
    }

    public AbstractListCache(JDBTable table, Class cls, int range) {
        super(table, range, cls);
        this.current_cache = new ArrayList<>();
    }

    @Override
    public int getSteps() {
        return range;
    }

    @Override
    public void loadData() {
        String query = current_query.formatted(getMinId(), getMaxId());
        if (cache.containsKey(query)) {
            current_cache.clear();
            current_cache.addAll(cache.get(query));
            return;
        }
        try (JDBConnection c = connection(); PreparedStatement ps = c.getNewPreparedStatement(query); ResultSet rs = ps.executeQuery();) {
            ResultSetMetaData md = rs.getMetaData();
            int size_field = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>(table.getFields().length);
                for (int i = 1; i <= size_field; i++) {
                    map.put(md.getColumnLabel(i), rs.getString(md.getColumnLabel(i)));
                }
                T t = (T) c.getFactory().getMapObject(cls);
                t.setMap(map);
                current_cache.add(t);
            }

            List copy = new ArrayList(range);
            copy.addAll(current_cache);
            cache.put(query, copy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dumpData() {
        if (current_cache.isEmpty()) {
            return;
        }
        current_cache.clear();
    }

    @Override
    public void reLoadData() {
        dumpData();
        loadData();
    }

    @Override
    public int size() {
        return current_cache.size();
    }

    @Override
    public List<T> getList() {
        return current_cache;
    }

    @Override
    public List<T> getList(Predicate<T> filter) {
        return current_cache.stream().filter(filter).toList();
    }

}
