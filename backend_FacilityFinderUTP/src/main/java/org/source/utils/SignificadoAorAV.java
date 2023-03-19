package org.source.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta es la descripción de la clase SignificadoAorAV:
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
