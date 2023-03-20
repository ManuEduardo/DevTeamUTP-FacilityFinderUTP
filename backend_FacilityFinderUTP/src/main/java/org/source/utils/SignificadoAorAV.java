package org.source.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta es la descripción de la clase SignificadoAorAV:
 * Esta es una clase de Java llamada "SignificadoAorAV" que tiene como objetivo almacenar y
 * recuperar los significados de dos abreviaturas: "A" y "AV". La clase tiene un atributo privado
 * llamado "AorAV" que es un objeto de tipo Map, que almacena las abreviaturas y sus significados
 * correspondientes.
 *
 * En el constructor de la clase, se inicializa el objeto "AorAV" y se agregan las dos abreviaturas
 * ("A" y "AV") con sus respectivos significados ("Ambiente" y "Ambiente Virtual") utilizando el
 * método put() de la clase Map.
 *
 * La clase también tiene un método público llamado "obtenerSignificado" que toma un valor
 * entero como parámetro y devuelve el significado correspondiente a la abreviatura asociada a ese
 * valor. En este caso, el método utiliza el método getOrDefault() de la clase Map para obtener
 * el significado de la abreviatura correspondiente al valor dado como parámetro, o bien,
 * si el valor no coincide con ninguna abreviatura almacenada en el objeto "AorAV", devuelve null.
 *
 * En resumen, esta clase proporciona una forma sencilla de almacenar y recuperar los significados
 * de dos abreviaturas específicas en un programa Java.
 * @author Gabriel Paiva
 */

public class SignificadoAorAV {
    private Map<String, String> AorAV;

    public SignificadoAorAV() {
        AorAV = new HashMap<>();
        AorAV.put("A", "Ambiente");
        AorAV.put("AV", "Ambiente Virtual");
    }

    public String obtenerSignificado(int valor) {
        return AorAV.getOrDefault(valor, null);
    }
}
