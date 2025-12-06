/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.time.LocalDateTime;
import java.util.Map;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * Clase de Objeto de Transferencia de Datos (DTO) que extiende un objeto basado
 * en mapa ({@code JDBMapObject}) para implementar las propiedades de auditoría
 * y estado.
 *
 * Esta clase permite la recuperación dinámica de campos de la base de datos
 * (status, fechas de registro y actualización) utilizando un mapa interno. Es
 * comúnmente utilizada para manejar resultados genéricos de consultas donde no
 * se define un DTO de tipo estricto.
 *
 * @author Juan Campos
 * @version 1.0
 * @since 22/11/2025
 */
public class AuditableObjectMap extends JDBMapObject implements AuditableModel {

    private static final long serialVersionUID = 1L;

    public AuditableObjectMap(Map<String, Object> map) {
        super(map);
    }

    public AuditableObjectMap(int size) {
        super(size);
    }

    public AuditableObjectMap() {
        super();
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(get("status").toString());
    }

    @Override
    public String getStatusString() {
        return CacheFactory.CAT_STATUS[getStatus()];
    }

    @Override
    public boolean isActive() {
        return getStatus() != 3;
    }

    @Override
    public LocalDateTime getDateUpdate() {
        return Formats.getLocalDateTime(get("date_update").toString());
    }

    @Override
    public LocalDateTime getDateRegister() {
        return Formats.getLocalDateTime(get("date_register").toString());
    }

    @Override
    public LocalDateTime getDateEnd() {
        if (values.containsKey("date_end")) {
            return Formats.getLocalDateTime(get("date_end").toString());
        }
        return null;
    }
}
