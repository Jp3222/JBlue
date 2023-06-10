/*
 * Copyright (C) 2023 jp
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
package com.jblue.sistema.app;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jp
 */
public class Configs {

    private final HashMap<String, String> configs;

    public Configs() {
        this.configs = new HashMap<>(10);
    }

    public void prop() {
        configs.put("bd_config", "[]");
        configs.put("usuarios_master", "[]");

    }

    public String[] getConfig(String s) {
        String get = configs.get(s);
        get = get.replace("[", "");
        get = get.replace("]", "");
        return get.split(",");
    }

    public String getString() {
        StringBuilder sb = new StringBuilder(100);
        for (Map.Entry<String, String> entry : configs.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            sb.append(key).append("=").append("[").append(val).append("]");
        }
        return sb.toString();
    }

}
