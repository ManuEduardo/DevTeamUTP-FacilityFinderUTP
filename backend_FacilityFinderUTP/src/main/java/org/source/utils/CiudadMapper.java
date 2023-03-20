package org.source.utils;

import org.source.Validaciones.DataIgualException;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta es la descripción de la clase CiudadMapper:
 * @author Gabriel Paiva
 * @version 1.0
 * @since 2023-03-19
 */

public class CiudadMapper {
    private Map<Integer, String> ciudades; // Se crea un mapa que mapea enteros a cadenas de texto (ciudades).

    // Constructor que inicializa el mapa con algunas ciudades.
    public CiudadMapper() {
        ciudades = new HashMap<>();
        ciudades.put(45, "Chiclayo");
        // Puedes agregar más ciudades a medida que sea necesario
    }

    /**
     * Función que verifica si una matriz de datos es igual a otra previamente ingresada.
     * @param valor Un valor entero como entrada que representa la key.
     * @return El nombre de la sede en forma de String.
     * @throws DataIgualException si la matriz actual es igual a la previamente ingresada.*/
    public String obtenerCiudad(int valor) {
        return ciudades.getOrDefault(valor, "Sede desconocida");
    }
}
