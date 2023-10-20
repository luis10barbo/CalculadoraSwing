package com.luisbb.utils;

public class UtilsNumero {
    public static Boolean eNumero(char numero) {
        return eNumero(String.valueOf(numero));
    }
    public static Boolean eNumero(String numero) {
        if (numero.equals(".")) return true;
        try {
            Double.parseDouble(numero);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
