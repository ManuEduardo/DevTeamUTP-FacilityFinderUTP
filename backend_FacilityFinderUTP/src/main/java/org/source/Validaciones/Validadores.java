package org.source.Validaciones;

import org.source.Validaciones.CodigoInvalidoException;
import org.source.utils.Clave;

public class Validadores {
    public static boolean esCodigoValido(String codigo, Clave clave) throws CodigoInvalidoException {

        String mensajeCodigoInvalido = "Excepción en el código: El código %s es inválido.";
        String mensajeCodigoProfesor = "Excepción en el código: El código %s del profesor debe empezar con la letra A en mayúscula.";
        String mensajeCodigoAlumno = "Excepción en el código: El código %s del alumno debe empezar con la letra C en mayúscula.";
        String mensajeCodigoNoNumero = "Excepción en el código: El código %s en la posición %d no es un número.";

        int longitud = (clave == Clave.PROFESOR) ? 6 : 9;

        if (codigo.trim().length() != longitud) {
            throw new CodigoInvalidoException(String.format(mensajeCodigoInvalido, codigo));
        }

        if (clave == Clave.PROFESOR && codigo.charAt(0) != 'C'&& codigo.charAt(0) != 'E') {
            throw new CodigoInvalidoException(String.format(mensajeCodigoProfesor, codigo));
        }

        if (clave == Clave.ALUMNO && codigo.charAt(0) != 'U') {
            throw new CodigoInvalidoException(String.format(mensajeCodigoAlumno, codigo));
        }

        for (int i = 1; i < longitud; i++) {
            char caracterCodigo = codigo.charAt(i);
            if (!Character.isDigit(caracterCodigo)) {
                throw new CodigoInvalidoException(String.format(mensajeCodigoNoNumero, codigo, i));
            }
        }

        return true;
    }

    public static boolean esDataRepetida(String[] arrayAnterior, String[] arrayActual) throws DataIgualException {

        String mensajeDataRepetida = "Excepción en la data: La data ingresada se repite.";

        if (arrayAnterior != null) {
            boolean iguales = true;
            for (int i = 0; i < arrayAnterior.length; i++) {
                if (!arrayAnterior[i].equals(arrayActual[i])) {
                    iguales = false;
                    break;
                }
            }

            if (iguales) {
                System.out.println("Las matrices son iguales.");
                throw new DataIgualException(String.format(mensajeDataRepetida));
            }
        }

        return true;
    }
}

