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
package com.jblue.util.objetos;

import java.util.Arrays;
import com.jutil.dbcon.cn.JDBObjectModel;

/**
 * Esta clase es una abstraccion maxima de la cual deberan heredar todas las
 * subclases q
 *
 * @author juan-campos
 */
public abstract class AbstractObject implements JDBObjectModel {

    /**
     * Este array almacena un conjunto de valores de diferentes tipos extraido
     * de la base de datos.
     */
    protected String[] info;

    public AbstractObject(String[] info) {
        this.info = info;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Arrays.deepHashCode(this.info);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractObject other = (AbstractObject) obj;
        return Arrays.deepEquals(this.info, other.info);
    }

}
