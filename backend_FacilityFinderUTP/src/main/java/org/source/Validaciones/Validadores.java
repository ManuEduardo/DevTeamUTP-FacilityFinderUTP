package org.source.Validaciones;

import org.source.utils.Clave;

/**
 * La clase Validadores contiene dos métodos estáticos que verifican si un código es válido y
 * si un array de strings contiene datos repetidos, respectivamente. El primer método,
 * esCodigoValido, toma dos argumentos: un string que representa el código a verificar
 * y una enum llamada Clave que indica si el código es de un profesor o de un alumno.
 * El método retorna true si el código es válido y lanza una excepción CodigoInvalidoException
 * si el código no es válido según la clave especificada.
 *
 * El segundo método, esDataRepetida, toma dos argumentos: un array de strings que representa
 * la matriz anterior y otro array de strings que representa la matriz actual. El método verifica
 * si los dos arrays son iguales y lanza una excepción DataIgualException si los datos se repiten.
 *
 * Ambos métodos lanzan excepciones personalizadas que extienden de la clase Exception y deben
 * ser manejadas o declaradas en los métodos que los llaman.
 *
 * @author Gabriel Paiva
 * @version 1.0
 * @since 2023-03-19
 */

public class Validadores {

    /**
     Verifica si un código es válido según la clave especificada.
     @param codigo El código a verificar.
     @param clave La clave que indica si el código es de un profesor o de un alumno.
     @return true si el código es válido, de lo contrario lanza una excepción.
     @throws CodigoInvalidoException si el código no es válido según la clave especificada.*/
    public static boolean esCodigoValido(String codigo, Clave clave) throws CodigoInvalidoException {

        // Mensajes de excepción para informar al usuario qué regla ha violado el código
        String mensajeCodigoInvalido = "Excepción en el código: El código %s es inválido.";
        String mensajeCodigoProfesor = "Excepción en el código: El código %s del profesor debe empezar con la letra A en mayúscula.";
        String mensajeCodigoAlumno = "Excepción en el código: El código %s del alumno debe empezar con la letra C en mayúscula.";
        String mensajeCodigoNoNumero = "Excepción en el código: El código %s en la posición %d no es un número.";

        // Determinar la longitud del código según la clave
        int longitud = (clave == Clave.PROFESOR) ? 6 : 9;

        // Comprobar si el código tiene la longitud adecuada
        if (codigo.trim().length() != longitud) {
            throw new CodigoInvalidoException(String.format(mensajeCodigoInvalido, codigo));
        }

        // Comprobar si el código del profesor empieza con 'C' o 'E'
        if (clave == Clave.PROFESOR && codigo.charAt(0) != 'C'&& codigo.charAt(0) != 'E') {
            throw new CodigoInvalidoException(String.format(mensajeCodigoProfesor, codigo));
        }

        // Comprobar si el código del alumno empieza con 'U'
        if (clave == Clave.ALUMNO && codigo.charAt(0) != 'U') {
            throw new CodigoInvalidoException(String.format(mensajeCodigoAlumno, codigo));
        }

        // Comprobar que los caracteres del código después del primer carácter son números
        for (int i = 1; i < longitud; i++) {
            char caracterCodigo = codigo.charAt(i);
            if (!Character.isDigit(caracterCodigo)) {
                throw new CodigoInvalidoException(String.format(mensajeCodigoNoNumero, codigo, i));
            }
        }

        // Si el código cumple con todas las reglas, devolver true
        return true;
    }

    /**
     * Verifica si una matriz de datos es igual a otra previamente ingresada.
     * @param arrayAnterior La matriz previamente ingresada.
     * @param arrayActual La matriz actual a comparar con la anterior.
     * @return true si la matriz actual es diferente a la previamente ingresada, de lo contrario lanza una excepción.
     * @throws DataIgualException si la matriz actual es igual a la previamente ingresada.*/
    public static boolean esDataRepetida(String[] arrayAnterior, String[] arrayActual) throws DataIgualException {

        // Mensaje de excepción para informar al usuario que la data se ha repetido
        String mensajeDataRepetida = "Excepción en la data: La data ingresada se repite.";

        // Si el array anterior no es nulo, comprobar si es igual al array actual
        if (arrayAnterior != null) {
            boolean iguales = true;
            for (int i = 0; i < arrayAnterior.length; i++) {
                if (!arrayAnterior[i].equals(arrayActual[i])) {
                    iguales = false;
                    break;
                }
            }

            // Si los arrays son iguales, lanzar una excepción y mostrar un mensaje
            if (iguales) {
                System.out.println("Las matrices son iguales.");
                throw new DataIgualException(String.format(mensajeDataRepetida));
            }
        }

        return true;
    }
}

