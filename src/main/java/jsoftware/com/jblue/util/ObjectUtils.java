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
package jsoftware.com.jblue.util;

import jsoftware.com.jblue.model.factories.CacheFactory;

/**
 *
 * @author juan pablo campos casasanero
 */
public class ObjectUtils {

    public static int getIndexEmployeeCAT(String value) {
        return getIndexCAT(value, CacheFactory.EMPLOYEE_TYPE_CAT);
    }

    public static String getDescriptionEmployeeTypeCAT(int index) {
        return getDescriptionCAT(index, CacheFactory.EMPLOYEE_TYPE_CAT);
    }

    public static int getIndexStatusCAT(String value) {
        return getIndexCAT(value, CacheFactory.CAT_STATUS);
    }

    public static String getDescriptionStatusCAT(int index) {
        return getDescriptionCAT(index, CacheFactory.CAT_STATUS);

    }

    public static String getDescriptionCAT(int index, String[] cataloge) {
        return cataloge[index];
    }

    public static int getIndexCAT(String value, String[] cataloge) {
        for (int i = 0; i < cataloge.length; i++) {
            if (value.equalsIgnoreCase(cataloge[i])) {
                return i;
            }
        }
        return 5;
    }
}
