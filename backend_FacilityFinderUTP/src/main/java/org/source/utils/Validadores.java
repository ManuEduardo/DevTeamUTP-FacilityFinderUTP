package org.source.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validadores {
    public static boolean ValidarCodigoProfesor(String cadena) {
        Pattern patron = Pattern.compile("^C\\d{4}$");
        // ^ indica que la cadena debe empezar con C
        // \\d{4} indica que deben ir 4 números después del C
        Matcher matcher = patron.matcher(cadena);
        return matcher.matches();
    }

    public static boolean ValidarCodigoAula (String cadena) {
        if (cadena.length() != 5) {
            return false; // la cadena debe tener longitud 5
        }
        char primerCaracter = cadena.charAt(0);
        if (primerCaracter != 'A' && primerCaracter != 'B' && primerCaracter != 'C') {
            return false; // el primer caracter debe ser A, B o C
        }
        for (int i = 1; i < 5; i++) {
            char caracter = cadena.charAt(i);
            if (caracter < '0' || caracter > '9') {
                return false; // los siguientes 4 caracteres deben ser números
            }
        }
        return true; // la cadena es válida
    }

    public static boolean validarDiaSemana(String dia) {
        String[] diasSemana = {"lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"};
        String diaMinusculas = dia.toLowerCase();
        for (String d : diasSemana) {
            if (d.equalsIgnoreCase(diaMinusculas)) {
                return true; // la cadena es un día de la semana válido
            }
        }
        return false; // la cadena no es un día de la semana válido
    }
}
