/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jutil.jexception.Excp;

/**
 *
 * @author jp
 * @since 2022-10-21
 */
public class EncriptadoAES {

    /**
     * Crea la clave de encriptacion usada internamente
     *
     * @param clave Clave que se usara para encriptar
     * @return Clave de encriptacion
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private static SecretKeySpec createKey(String clave) throws
            UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] claveEncriptacion = clave.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);

        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");

        return secretKey;
    }

    /**
     * Aplica la encriptacion AES a la cadena de texto usando la clave indicada
     *
     * @param datos Cadena a encriptar
     * @param claveSecreta Clave para encriptar
     * @return Información encriptada
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String doEncrypt(String datos, String claveSecreta) throws
            UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = createKey(claveSecreta);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] datosEncriptar = datos.getBytes("UTF-8");
        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
        String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);

        return encriptado;
    }

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16; // Tamaño del bloque AES

    /**
     * Aplica la encriptación AES/CBC a la cadena de texto inyectando un IV
     * aleatorio al inicio. Unifica las excepciones criptográficas en un solo
     * punto de control.
     *
     * @param datos Cadena de texto plano a encriptar.
     * @param claveSecreta Clave maestra para el cifrado.
     * @return Información encriptada en formato Base64 (IV + Datos Cifrados).
     * @throws ServiceException Si ocurre un fallo en los componentes de
     * seguridad.
     */
    public static String doEncrypt2(String datos, String claveSecreta) throws GeneralSecurityException, UnsupportedEncodingException {
        // 1. Derivación de la llave (Asegúrate de que createKey use SHA-256 para generar una llave de 256 bits)
        SecretKeySpec secretKey = createKey(claveSecreta);
        // 2. Generación de un Vector de Inicialización (IV) criptográficamente seguro y único por operación
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // 3. Inicialización del cifrador en modo CBC
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        // 4. Cifrado de los datos planos en UTF-8
        byte[] datosEncriptar = datos.getBytes("UTF-8");
        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);

        // 5. Concatenación del IV y el bloque cifrado (El IV no es secreto, se necesita para desencriptar)
        byte[] ivYBytesEncriptados = new byte[IV_SIZE + bytesEncriptados.length];
        System.arraycopy(iv, 0, ivYBytesEncriptados, 0, IV_SIZE);
        System.arraycopy(bytesEncriptados, 0, ivYBytesEncriptados, IV_SIZE, bytesEncriptados.length);

        // 6. Conversión final a String seguro para MySQL
        return Base64.getEncoder().encodeToString(ivYBytesEncriptados);

    }

    /**
     * Desencripta la cadena de texto indicada usando la clave de encriptacion
     *
     * @param dencp datos encriptados
     * @param cscre Clave de encriptacion
     * @return Informacion desencriptada
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String doDecrypt(String dencp, String cscre) throws
            UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = createKey(cscre);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncriptados = Base64.getDecoder().decode(dencp);
        byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
        String datos = new String(datosDesencriptados);
        return datos;
    }

    /**
     * Este metodo verifica si un par de datos descencriptados(Clave y Valor) es
     * igual a un par de datos encriptados(Clave y valor), usando un array de 4
     * posisciones donde la posicion 1 es igual a la clave encriptada, la
     * posicion 2 es igual al valor encriptado la posicion 3 es igual a la clave
     * descencriptada y la posicion 4 es igual al valor descencriptado
     *
     * @param args
     * @return
     */
    public static boolean equalsEncrypt(String... args) {
        try {
            String a = doEncrypt(args[2], args[3]);
            String b = doEncrypt(args[3], args[2]);
            return args[0].equals(a) && args[1].equals(b);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException
                | InvalidKeyException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException ex) {
            Excp.impTerminal(ex, EncriptadoAES.class, true);
        }
        return false;
    }

}
