package org.source.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta es la descripción de la clase QueryToMap:
 * La clase "QueryToMap" contiene un método estático llamado "queryToMap" que toma una
 * cadena de consulta (query) como argumento y devuelve un mapa de cadena de clave-valor.
 * El método primero verifica si la cadena de consulta no es nula y, si es así, divide la
 * cadena en pares de parámetros separados por "&". Luego divide cada parámetro en su clave
 * y valor respectivos, utilizando el signo de igual ("=") como delimitador.
 * Finalmente, inserta cada clave-valor en el mapa y lo devuelve. Este método es útil
 * para convertir una cadena de consulta en un mapa, lo que permite un acceso más sencillo
 * y manipulación de los parámetros de la consulta.
 * @author Manuel Sanchez Suyon
 */

public class QueryToMap {

    /**
     Convierte una cadena de consulta en formato de cadena de consulta HTTP en un mapa de pares clave-valor.
     @param query La cadena de consulta en formato de cadena de consulta HTTP.
     @return Un mapa de pares clave-valor que representa la cadena de consulta. */
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length > 1) {
                    result.put(pair[0], pair[1]);
                } else {
                    result.put(pair[0], "");
                }
            }
        }
        return result;
    }
}
