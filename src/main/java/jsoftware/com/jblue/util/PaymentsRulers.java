/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase mejorada para realizar cálculos de pagos con precisión BigDecimal.
 */
public class PaymentsRulers {

    // Define la escala (decimales) para la moneda y el modo de redondeo
    private static final int DECIMAL_SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * Calcula el costo total base antes de recargos.
     *
     * @param items Número de ítems.
     * @param cost Costo unitario por ítem.
     * @return El costo base total.
     */
    public static BigDecimal calculateBaseTotal(int items, BigDecimal cost) {
        // BigDecimal.valueOf(items) convierte el int a BigDecimal
        return cost.multiply(BigDecimal.valueOf(items))
                .setScale(DECIMAL_SCALE, ROUNDING_MODE);
    }

    /**
     * Calcula el monto total del recargo (surcharge).
     *
     * @param items Número de ítems.
     * @param cost Costo del recargo por ítem.
     * @return El recargo total.
     */
    public static BigDecimal calculateTotalSurcharge(int items, BigDecimal cost) {
        // Multiplica el recargo unitario por la cantidad de ítems
        return cost.multiply(BigDecimal.valueOf(items))
                .setScale(DECIMAL_SCALE, ROUNDING_MODE);
    }

    /**
     * Calcula el total final: (Costo Base + Recargo Total).
     *
     * @param items Número de ítems.
     * @param cost Costo unitario por ítem.
     * @param surcharge Costo del recargo por ítem.
     * @return El total final.
     */
    public static BigDecimal calculateFinalTotal(int items, BigDecimal cost, BigDecimal surcharge) {
        BigDecimal baseTotal = calculateBaseTotal(items, cost);
        BigDecimal totalSurcharge = calculateTotalSurcharge(items, surcharge);

        // Suma y aplica redondeo a 2 decimales
        return baseTotal.add(totalSurcharge)
                .setScale(DECIMAL_SCALE, ROUNDING_MODE);
    }
}
