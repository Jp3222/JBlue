package jsoftware.com.jblue.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Gestiona el cálculo de cobros, recargos e inflación para JBlue.
 *
 * * @author juanp
 */
public class FuncPayment {

    private BigDecimal cost;      // El costo original (Base)
    private BigDecimal inflation; // % de Inflación (ej: 1.05)
    private BigDecimal utility;   // % de Utilidad (ej: 1.10)
    private BigDecimal surcharge; // % de Recargo (ej: 1.20)
    private boolean roun;        // ¿Debe redondear?
    private boolean rounUp;      // ¿Redondeo hacia arriba o al más cercano?

    public FuncPayment(BigDecimal cost, boolean roun, boolean rounUp) {
        this.cost = cost != null ? cost : BigDecimal.ZERO;
        this.inflation = BigDecimal.ONE; // 1.0 significa 0% de incremento inicial
        this.utility = BigDecimal.ONE;
        this.surcharge = BigDecimal.ONE;
        this.roun = roun;
        this.rounUp = rounUp;
    }

    // --- CÁLCULOS DE MONTOS ÚNICOS (SOLO EL INCREMENTO) ---
    /**
     * SOLO INFLACION = COSTO * (INFLACION - 1)
     */
    public BigDecimal getOnlyInflation() {
        BigDecimal factor = inflation.subtract(BigDecimal.ONE);
        return applyRounding(cost.multiply(factor));
    }

    /**
     * SOLO UTILIDAD = COSTO * (UTILIDAD - 1)
     */
    public BigDecimal getOnlyUtility() {
        BigDecimal factor = utility.subtract(BigDecimal.ONE);
        return applyRounding(cost.multiply(factor));
    }

    /**
     * SOLO RECARGO = COSTO * (RECARGO - 1)
     */
    public BigDecimal getOnlySurcharge() {
        BigDecimal factor = surcharge.subtract(BigDecimal.ONE);
        return applyRounding(cost.multiply(factor));
    }

    // --- CÁLCULOS DE COSTOS ACUMULADOS ---
    /**
     * COSTO BASE = COSTO + INFLACION (Monto acumulado)
     */
    public BigDecimal getBaseCost() {
        return applyRounding(cost.add(getOnlyInflation()));
    }

    /**
     * COSTO CON GANANCIA = COSTO BASE + UTILIDAD
     */
    public BigDecimal getCostWithUtility() {
        return applyRounding(getBaseCost().add(getOnlyUtility()));
    }

    /**
     * COSTO CON RECARGO = COSTO CON GANANCIA + RECARGO
     */
    public BigDecimal getTotalWithSurcharge() {
        return applyRounding(getCostWithUtility().add(getOnlySurcharge()));
    }

    // --- UTILIDAD DE REDONDEO ---
    private BigDecimal applyRounding(BigDecimal value) {
        if (!roun) {
            return value.setScale(2, RoundingMode.HALF_UP);
        }
        // Redondeo a enteros: CEILING (hacia arriba) o HALF_UP (al más cercano)
        RoundingMode mode = rounUp ? RoundingMode.CEILING : RoundingMode.HALF_UP;
        return value.setScale(0, mode);
    }

    // --- SETTERS PARA CONFIGURAR LOS PORCENTAJES ---
    public void setInflation(BigDecimal inflation) {
        this.inflation = inflation;
    }

    public void setUtility(BigDecimal utility) {
        this.utility = utility;
    }

    public void setSurcharge(BigDecimal surcharge) {
        this.surcharge = surcharge;
    }
}
