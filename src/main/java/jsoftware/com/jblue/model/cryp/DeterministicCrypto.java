package jsoftware.com.jblue.model.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import jsoftware.com.jblue.model.exp.ServiceException;

/**
 * Motor criptográfico determinista basado en AES/ECB. Diseñado específicamente
 * para campos que requieren búsquedas directas en la base de datos (como el
 * campo 'user'). Garantiza que la misma cadena de entrada con la misma clave
 * siempre devuelva el mismo Hash en Base64.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-11
 * @version 1.0
 */
public class DeterministicCrypto {

    private static final long serialVersionUID = 1L;
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * Encripta una cadena de texto de forma determinista.
     *
     * * @param datos Texto plano (ej. el nombre de usuario "juan123").
     * @param claveSecreta Frase semilla para generar la llave de cifrado.
     * @return String cifrado en Base64 (Siempre idéntico para la misma
     * entrada).
     * @throws ServiceException Si ocurre un error en el cifrador.
     */
    public static String encryp(String datos, String claveSecreta) throws ServiceException {
        if (datos == null || datos.trim().isEmpty() || claveSecreta == null || claveSecreta.trim().isEmpty()) {
            throw new ServiceException(400, "LOS DATOS Y LA CLAVE SECHRETA SON REQUERIDOS");
        }
        try {
            // 1. Derivamos una llave segura de 256 bits (32 bytes) usando SHA-256 basado en tu clave secreta
            SecretKeySpec secretKey = deriveKey(claveSecreta);

            // 2. Configuramos el cifrador en modo ECB (No usa IV, lo que garantiza el determinismo)
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // 3. Ciframos los bytes en formato estándar UTF-8
            byte[] bytesCifrados = cipher.doFinal(datos.getBytes(StandardCharsets.UTF_8));

            // 4. Convertimos a String legible para guardar en el VARCHAR de MySQL
            return Base64.getEncoder().encodeToString(bytesCifrados);

        } catch (Exception e) {
            throw new ServiceException(500, "ERROR AL ENCRIPTAR USUARIO DETERMINISTA: " + e.getMessage());
        }
    }

    /**
     * Desencripta el texto para recuperar el usuario original en texto plano.
     */
    public static String decryp(String datosEncriptados, String claveSecreta) throws ServiceException {
        if (datosEncriptados == null || claveSecreta == null) {
            throw new ServiceException(400, "LOS PARAMETROS PARA EL DESCIFRADO NO PUEDEN SER NULL");
        }
        try {
            SecretKeySpec secretKey = deriveKey(claveSecreta);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Convertimos de Base64 a bytes originales y desciframos
            byte[] bytesPlanos = cipher.doFinal(Base64.getDecoder().decode(datosEncriptados));

            return new String(bytesPlanos, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new ServiceException(500, "ERROR AL DESENCRIPTAR USUARIO DETERMINISTA: " + e.getMessage());
        }
    }

    /**
     * Helper interno para asegurar que la llave siempre mida 32 bytes
     * (AES-256), sin importar la longitud del String que tú elijas como
     * contraseña maestra.
     */
    private static SecretKeySpec deriveKey(String clave) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(clave.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(keyBytes, "AES");
    }
}
