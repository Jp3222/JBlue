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
package com.jblue.model.dtos;

import com.jblue.model.factories.CacheFactory;

/**
 * Data Transfer Object (DTO) para la información de una Administración. Sigue
 * el patrón de indexación de datos de la clase Objects.
 *
 * @author jp
 */
public class AdministrationHistoryObject extends Objects implements StatusObject {

    // --- Constructores ---
    /**
     * Inicializa el DTO con la información de la administración.
     *
     * @param info Arreglo de strings con los datos de los campos.
     */
    public AdministrationHistoryObject(String... info) {
        super(info);
    }

    /**
     * Constructor por defecto.
     */
    public AdministrationHistoryObject() {
        super();
    }

    // --- Getters de Campos Específicos ---
    @Override
    public String getId() {
        return info[0];
    }

    public String getYearOfAdministration() {
        return info[1];
    }

    public String getRoot() {
        return info[2];
    }

    public String getAdministrator() {
        return info[3];
    }

    public String getPresident() {
        return info[4];
    }

    public String getTreasurer() {
        return info[5];
    }

    public String getSecretary() {
        return info[6];
    }

    public String getPlumber() {
        return info[7];
    }

    public String getIntern1() {
        return info[8];
    }

    public String getIntern2() {
        return info[9];
    }

    public String getIntern3() {
        return info[10];
    }

    public String getDescription() {
        return info[11];
    }

    public String getDateUpdate() {
        return info[13];
    }

    public String getDateRegister() {
        return info[14];
    }

    public String getDateEnd() {
        return info[15];
    }

    // --- Implementación de StatusObject (usando info[12] para 'status') ---
    /**
     * Retorna el estado de la administración como un entero. El estado se
     * encuentra en la posición 12 del array info.
     *
     * @return El código de estado (int).
     */
    @Override
    public int getStatus() {
        // Asumiendo que info[12] contiene el campo 'status'
        return Integer.parseInt(info[12]);
    }

    /**
     * Retorna la descripción textual del estado.
     *
     * @return La descripción del estado.
     */
    @Override
    public String getStatusString() {
        return CacheFactory.ITEMS_STATUS_CAT[getStatus()];
    }

    /**
     * Determina si la administración está activa.
     *
     * @return true si el estado es 1 (activo), false en caso contrario.
     */
    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

    // --- Representación en String ---
    /**
     * Sobreescribe toString() para retornar el campo que mejor identifique al
     * objeto, en este caso, el año de la administración y el administrador.
     *
     * @return String que identifica al objeto.
     */
    @Override
    public String toString() {
        return getYearOfAdministration() + " - " + getAdministrator();
    }
}
