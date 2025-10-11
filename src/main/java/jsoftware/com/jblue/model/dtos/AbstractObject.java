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
package jsoftware.com.jblue.model.dtos;

import jsoftware.com.jutil.dbcon.cn.JDBObjectMapModel;
import jsoftware.com.jutil.dbcon.cn.JDBObjectModel;

import java.util.Arrays;

/**
 * Proporciona una implementación base abstracta para los objetos de
 * transferencia de datos (DTO).
 * <p>
 * Esta clase sirve como un punto de partida para todas las subclases que
 * representan registros de la base de datos con una estructura de array simple.
 * Implementa la interfaz {@link JDBObjectModel}, proporcionando
 * implementaciones por defecto para la gestión de datos, la comparación de
 * objetos, y la clonación, lo que simplifica la creación de DTOs concretos.
 *
 * @author juan-campos
 * @see JDBObjectModel
 * @see JDBObjectMapModel
 * @since 1.0
 */
public abstract class AbstractObject implements JDBObjectModel {

    /**
     * Array que almacena los valores de los campos de un registro de la base de
     * datos.
     * <p>
     * La subclase debe manejar el mapeo de los índices de este array a las
     * propiedades específicas del objeto (por ejemplo, info[0] = id, info[1] =
     * nombre).
     */
    protected String[] info;

    /**
     * Construye un nuevo objeto con los datos proporcionados.
     *
     * @param info Un array de {@link String} con los valores de los campos del
     * registro.
     */
    public AbstractObject(String[] info) {
        this.info = info;
    }

    //-------------------------------------------------------------------------
    // Implementaciones de JDBObjectModel
    //-------------------------------------------------------------------------
    /**
     * Retorna el array de datos del objeto.
     *
     * @return Un array de {@link String} con la información del registro.
     */
    @Override
    public String[] getData() {
        return info;
    }

    /**
     * Asigna un nuevo array de datos al objeto.
     *
     * @param info El array de {@link String} con los nuevos valores a asignar.
     */
    @Override
    public void setData(String[] info) {
        this.info = info;
    }

    /**
     * Genera un valor hash para el objeto basado en el contenido del array de
     * información.
     * <p>
     * Se utiliza {@link Arrays#deepHashCode(Object[])} para asegurar que el
     * hash sea consistente con el contenido del array.
     *
     * @return El código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Arrays.deepHashCode(this.info);
        return hash;
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     * <p>
     * La igualdad se determina si el objeto pasado no es nulo, es del mismo
     * tipo, y su array de información {@code info} es profundamente igual.
     *
     * @param obj El objeto con el que se va a comparar.
     * @return {@code true} si los objetos son iguales; {@code false} en caso
     * contrario.
     */
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

    /**
     * Crea y retorna una copia superficial de este objeto.
     *
     * @return Una copia superficial del objeto.
     * @throws CloneNotSupportedException Si la clase no implementa la interfaz
     * {@code Cloneable}.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Verifica si el objeto de datos está vacío.
     * <p>
     * Un objeto se considera vacío si su array de información es {@code null} o
     * si su longitud es cero.
     *
     * @return {@code true} si el array de información es nulo o vacío;
     * {@code false} en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return info == null || info.length == 0;
    }

    //-------------------------------------------------------------------------
    // Metodo de JDBObjectModel que debe ser implementado por la subclase
    //-------------------------------------------------------------------------
    /**
     * Retorna la clave primaria (ID) del objeto.
     * <p>
     * **NOTA:** Este método es abstracto y debe ser implementado por las
     * subclases para devolver el valor de la clave primaria del registro.
     *
     * @return El valor del ID del objeto.
     */
    @Override
    public abstract String getId();

    //-------------------------------------------------------------------------
    // Implementacion de Comparable<JDBObjectModel>
    //-------------------------------------------------------------------------
    /**
     * Compara este objeto con otro del mismo tipo.
     * <p>
     * **NOTA:** Este método es abstracto y debe ser implementado por las
     * subclases para definir el orden natural de los objetos.
     *
     * @param o El objeto con el que se va a comparar.
     * @return Un valor negativo, cero o positivo si este objeto es
     * respectivamente menor que, igual a o mayor que el objeto especificado.
     */
    @Override
    public abstract int compareTo(JDBObjectModel o);
}
