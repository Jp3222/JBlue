package jsoftware.com.jblue.model.cryp;

import java.io.Serializable;
import jsoftware.com.jblue.model.exp.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Motor criptográfico basado en el algoritmo adaptativo BCrypt. Diseñado
 * específicamente para el hashing y verificación de credenciales de usuario. *
 * Al ser un algoritmo de una sola vía, las operaciones de descifrado no están
 * soportadas.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-11
 * @version 1.0
 */
public class BCryptCrypto implements Serializable {

    private static final long serialVersionUID = 1L;

    // Factor de costo por defecto (12 es un excelente equilibrio entre seguridad y milisegundos de CPU)
    private static final int logRounds = 12;

    /**
     * Genera el hash unidireccional de la contraseña. El Salt se genera de
     * manera aleatoria automáticamente y se incrusta dentro del String
     * resultante.
     *
     * @param datos Contraseña en texto plano enviada desde la vista Swing.
     * @param claveSecreta Parámetro requerido por la interfaz; en BCrypt no es
     * necesario ya que el Salt es automático, por lo que puede enviarse null o
     * vacío.
     * @return El String del Hash generado (con longitud estándar de 60
     * caracteres).
     * @throws ServiceException Si los datos de entrada son inválidos.
     */
    public static String encryp(String datos, int logRounds) {
        // Generamos el Salt dinámico con el factor de trabajo y aplicamos el hash
        String salt = BCrypt.gensalt(logRounds);
        return BCrypt.hashpw(datos, salt);
    }

    /**
     * Genera el hash unidireccional de la contraseña. El Salt se genera de
     * manera aleatoria automáticamente y se incrusta dentro del String
     * resultante.
     *
     * @param datos Contraseña en texto plano enviada desde la vista Swing.
     * @param claveSecreta Parámetro requerido por la interfaz; en BCrypt no es
     * necesario ya que el Salt es automático, por lo que puede enviarse null o
     * vacío.
     * @return El String del Hash generado (con longitud estándar de 60
     * caracteres).
     * @throws ServiceException Si los datos de entrada son inválidos.
     */
    public static String encryp(String datos) {
        return encryp(datos, logRounds);
    }

    /**
     * Evalúa de forma segura si la contraseña en texto plano coincide con el
     * Hash de la Base de Datos. BCrypt extrae internamente el Salt del propio
     * 'textoCifrado' para realizar el cálculo.
     */
    public static boolean equalsEncryp(String textoPlano, String textoCifrado, String claveSecreta) throws ServiceException {
        return BCrypt.checkpw(textoPlano, textoCifrado);
    }
}
