package org.source.modelos;

public class Profesor extends PersonaUniversitaria{

    //Cursos que imparte el profesor'.
    private Curso[] cursos;

    public Profesor(String nombre, String codigo, Curso[] cursos) {
        super(nombre, codigo);
        this.cursos = cursos;
    }
}
