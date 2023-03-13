package org.source.modelos;

public class Profesor extends PersonaUniversitaria{
    public Profesor(String nombre, String codigo) {
        super(nombre, codigo);
    }

    public Profesor(String nombreCompleto, String codigoMaestro, Curso[] toArray) {
        super();
    }
}
