package org.source.utils;

public class Validadores {

    private static final int LONGITUD_CODIGO_PROFESOR = 9;
    private static final int LONGITUD_CODIGO_ALUMNO = 6;

    public static ResultadoValidacion validarCodigo(String codigo, Clave clave) {

        if (clave == null) {
            return new ResultadoValidacion(false, "Clave no puede ser nula");
        }

        int longitudRequerida = (clave == Clave.PROFESOR) ? LONGITUD_CODIGO_PROFESOR : LONGITUD_CODIGO_ALUMNO;

        if (codigo == null || codigo.strip().length() != longitudRequerida) {
            return new ResultadoValidacion(false, String.format("El código '%s' es inválido (debe tener %d dígitos)", codigo, longitudRequerida));
        }

        char primerCaracterEsperado = (clave == Clave.PROFESOR) ? 'A' : 'C';

        if (codigo.strip().charAt(0) != primerCaracterEsperado) {
            return new ResultadoValidacion(false, String.format("El código '%s' debe empezar con la letra '%c' en mayúscula.", codigo, primerCaracterEsperado));
        }

        if (!codigo.strip().substring(1).matches("\\d+")) {
            return new ResultadoValidacion(false, String.format("El código '%s' debe contener solo dígitos numéricos después del primer carácter.", codigo));
        }

        return new ResultadoValidacion(true, "Código válido");
    }

    public static class ResultadoValidacion {
        private final boolean codigoValido;
        private final String mensaje;

        public ResultadoValidacion(boolean codigoValido, String mensaje) {
            this.codigoValido = codigoValido;
            this.mensaje = mensaje;
        }

        public boolean isCodigoValido() {
            return codigoValido;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}

