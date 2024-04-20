/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.crypto;

import com.jutil.jexception.Excp;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author jp
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
    public static SecretKeySpec crearClave(String clave) throws
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
    public static String encriptar(String datos, String claveSecreta) throws
            UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = crearClave(claveSecreta);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] datosEncriptar = datos.getBytes("UTF-8");
        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
        String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);

        return encriptado;
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
    public static String desencriptar(String dencp, String cscre) throws
            UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = crearClave(cscre);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncriptados = Base64.getDecoder().decode(dencp);
        byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
        String datos = new String(datosDesencriptados);

        return datos;
    }

    /**
     * Compara si dos claves encriptadas son correctas
     *
     * @param ken - key encriptada
     * @param ven - value - encriptado
     * @param kdes - key - descencriptada
     * @param vdes - value - descencriptada
     * @return
     */
    public static boolean comparador(String ken, String ven, String kdes, String vdes) {
        try {
            String k = kdes;
            String v = vdes;
            String a = encriptar(kdes, v);
            String b = encriptar(vdes, k);
            return ken.equals(a) && ven.equals(b);

        } catch (UnsupportedEncodingException
                | NoSuchAlgorithmException
                | InvalidKeyException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | BadPaddingException ex) {

            Excp.impTerminal(ex, EncriptadoAES.class, true);
        }
        return false;
    }
}
