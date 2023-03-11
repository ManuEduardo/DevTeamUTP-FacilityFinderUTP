package org.source.modelos;

public class Profesor extends PersonaUniversitaria{
    public Curso[] cursos;
    public Profesor(String nombre, String codigo) {
        super(nombre, codigo);
    }
}
