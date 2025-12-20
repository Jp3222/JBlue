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

import jsoftware.com.jutil.db.JDBMapObject;
import jsoftware.com.jutil.db.JDBTable;

/**
 *
 * @author juan pablo campos casasanero
 * @param <T>
 */
public class MemoListCache<T extends JDBMapObject> extends AbstractListCache<T> implements Paginated {

    private static final long serialVersionUID = 1L;

    public MemoListCache(JDBTable table, Class cls) {
        super(table, cls);
    }

    public MemoListCache(JDBTable table, Class cls, int range) {
        super(table, cls, range);
    }

    @Override
    public boolean next() {
        return movData(MOV_TO_NEXT);
    }

    @Override
    public boolean back() {
        return movData(MOV_TO_BACK);
    }

    @Override
    public boolean movBuffer(int mov) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean movData(int mov) {
        if (mov == MOV_TO_BACK) {
            this.min_id -= range;
            this.max_id -= range;
        }
        if (mov == MOV_TO_NEXT) {
            this.min_id += range;
            this.max_id += range;
        }
        reLoadData();
        return !cache.isEmpty();
    }

}
