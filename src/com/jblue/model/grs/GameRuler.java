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
package com.jblue.model.grs;

import java.util.function.Predicate;

/**
 *
 * @author juanp
 */
public class GameRuler<T> {

    private String message;
    private Predicate<T> ruler;
    private T param;

    public GameRuler(Predicate<T> t) {
        this("La regla %s ha fallado".formatted(t.toString()), t, null);
    }

    public GameRuler(String msg, Predicate<T> t) {
        this(msg, t, null);
    }

    public GameRuler(String message, Predicate<T> ruler, T param) {
        this.message = message;
        this.ruler = ruler;
        this.param = param;
    }

    public boolean exce(T t) {
        return ruler.test(t);
    }

    public boolean exce() {
        return ruler.test(param);
    }

    public Predicate<T> getRuler() {
        return ruler;
    }

}
