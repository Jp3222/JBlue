/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

/**
 *
 * @author juanp
 */
public class FuncGenerate {

    public static final String LOWER_CASE_ABC = "abcdfghijklmnñopqrstuvwxyz";
    public static final String UPPER_CASE_ABC = "ABCDFGHIJKLMNÑOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String SC_VALID = "!#$%&/()=?¡¿,.<>+-/*";
    private static final int SIZE = LOWER_CASE_ABC.length() + UPPER_CASE_ABC.length() + NUMBERS.length() + SC_VALID.length();

    public static String getPassword(int size, boolean number, boolean lower, boolean upper, boolean sc) {
        StringBuilder sb = new StringBuilder(SIZE);

        if (number) {
            sb.append(NUMBERS);
        }

        if (sc) {
            sb.append(SC_VALID);
        }

        if (lower) {
            sb.append(LOWER_CASE_ABC);
        }

        if (upper) {
            sb.append(UPPER_CASE_ABC);
        }
        
        if (!number && !lower && !upper && !sc) {
            sb.append(LOWER_CASE_ABC).append(UPPER_CASE_ABC);
        }
        return mkPassword(size, sb.toString());
    }

    public static String mkPassword(int size, String dictionary) {
        StringBuilder sb = new StringBuilder(size);
        int char_index;
        for (int i = 0; i < size; i++) {
            char_index = (int) (Math.random() * dictionary.length());
            sb.append(dictionary.charAt(char_index));
        }
        return sb.toString();
    }
}
