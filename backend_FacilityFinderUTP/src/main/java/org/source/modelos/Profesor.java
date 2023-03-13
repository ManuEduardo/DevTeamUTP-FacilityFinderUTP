package org.source.modelos;

public class Profesor extends PersonaUniversitaria{
    Curso[] clases;
    public Profesor(String nombre, String codigo) {
        super(nombre, codigo);
    }

    public Profesor(String nombreCompleto, String codigoMaestro, Curso[] clases) {
        super();
    }
}
