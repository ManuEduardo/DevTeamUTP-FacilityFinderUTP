package org.source.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta es la descripción de la clase CiudadMapper:
 * @author Gabriel Paiva
 */

public class CiudadMapper {
    private Map<Integer, String> ciudades;

    public CiudadMapper() {
        ciudades = new HashMap<>();
        ciudades.put(45, "Chiclayo");
        // Puedes agregar más ciudades a medida que sea necesario
    }

    public String obtenerCiudad(int valor) {
        return ciudades.getOrDefault(valor, "Ciudad desconocida");
    }
}
