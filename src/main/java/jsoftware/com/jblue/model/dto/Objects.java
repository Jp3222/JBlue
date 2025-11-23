/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 * Representa un objeto de transferencia de datos (DTO) genérico y simple.
 * <p>
 * Esta clase es una implementación concreta de {@link AbstractObject} que sirve
 * como un "recipiente" o contenedor básico para datos de una base de datos. La
 * información se almacena en un array de cadenas, y se asume que el primer
 * elemento del array (índice 0) es el identificador o clave primaria.
 *
 * @author jp
 * @see AbstractObject
 * @see JDBObjectArrayModel
 * @see JDBObjectMapModel
 * @since 1.0
 */
public class Objects extends AbstractObject {

    private static final long serialVersionUID = 1L;

    /**
     * Construye un nuevo objeto vacío.
     * <p>
     * Inicializa el objeto con un array de información nulo.
     */
    public Objects() {
        super(null);
    }

    /**
     * Construye un objeto a partir de un array de datos.
     *
     * @param info Un array de {@link String} que contiene los valores de los
     * campos del registro.
     */
    public Objects(String[] info) {
        super(info);
    }

}
