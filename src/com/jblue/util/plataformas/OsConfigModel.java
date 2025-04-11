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
package com.jblue.util.plataformas;

import com.jutil.platf.So;
import java.io.FileNotFoundException;

/**
 * inteface que busca construir los recursos basicos del sistema operativo para
 * que el programa funcione, principalmente rutas de instalacion
 *
 * <br>
 * los diccionarios son los nombres que maneja el sistema operativo para las
 * carpetas del mismo
 *
 * @author juan-campos
 */
public interface OsConfigModel {

    /**
     * Nombres de directorios en ingles usados recurrentemente
     */
    public String[] DICCIONARIO_EN = {
        "Desktop", "Documents"
    };

    /**
     * Nombres de directorios en español usados recurrentemente
     */
    public String[] DICCIONARIO_ES = {
        "Escritorio", "Documentos"
    };

    public String getPathInstalacion() throws FileNotFoundException;

    public String getPathDocumentos() throws FileNotFoundException;

    public String getPathEscritorio() throws FileNotFoundException;

    /**
     * 1 - EN<br>
     * 2 - ES<br>
     *
     * en caso de usar windows se es preferible usar el diccionario en ingles ya
     * que aunque el idioma de windows sea español, las rutas las maneja en
     * ingles<br>
     *
     * en caso de usar linux se recomienda usar el diccionario seleccionado
     *
     * @return retorna el diccionario adecuado para el sistema operativo segun
     * el idioma del mismo
     *
     * <br>
     * nota perosnal 1: desconozco como funcione el sistema en MacOs por lo que
     * no hay una recomendacion concreta nota personal 2: desconozco si lo dicho
     * de windows se pueda aplicar en linux de alguna forma por lo que si se
     * puede mejorar este apartado, adelante.
     */
    public default String[] getDiccionario() {
        return switch (So.USER_LANGUAGE) {
            case "en":
                yield DICCIONARIO_EN;
            case "es":
                yield DICCIONARIO_ES;
            default:
                yield DICCIONARIO_ES;
        };
    }

}
