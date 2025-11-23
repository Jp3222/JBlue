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
package jsoftware.com.jblue.model.dto;

import java.io.Serializable;

/**
 * Define un contrato para cualquier objeto (DTO/Model) dentro del sistema que
 * posea un estado de vida (Status) definido por un valor entero.
 *
 * Esta interfaz permite a las clases consumidoras consultar y presentar el
 * estado del objeto de manera uniforme, independientemente de la tabla de
 * origen.
 *
 * @author Juan Campos
 * @version 1.0
 * @since 2025-11-22
 */
public interface StatusObjectModel extends Serializable {

    /**
     * Obtiene el código de estado numérico del objeto, el cual se utiliza como
     * clave foránea o referencia al catálogo de estados (cat_status).
     *
     * @return El identificador entero (ID) que representa el estado actual del
     * objeto (ej: 1, 7, 3).
     */
    public int getStatus();

    /**
     * Obtiene una representación textual y legible del estado del objeto,
     * generalmente consultando un catálogo o caché de estados (ej: "ACTIVO",
     * "PENDIENTE", "CANCELADO").
     *
     * @return Una cadena de texto (String) con el nombre o descripción del
     * estado, listo para ser mostrado al usuario.
     */
    public String getStatusString();

    /**
     * Valida si el registro (o la entidad) se encuentra en un estado
     * considerado 'activo' o 'vigente' para la lógica de negocio.
     *
     * @return {@code true} si el estado del objeto es diferente del código de
     * cancelación/eliminación (Status != 3), {@code false} en caso contrario.
     */
    public boolean isActive();
}
