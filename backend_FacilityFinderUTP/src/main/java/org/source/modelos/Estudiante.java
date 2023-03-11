package org.source.modelos;

public class Estudiante extends PersonaUniversitaria{
    public Curso[] cursos;

    public Estudiante(String nombre, String codigo, Curso[] cursos) {
        super(nombre, codigo);
        this.cursos = cursos;
    }

    public Estudiante(String nombre, String codigo) {
        super(nombre, codigo);
    }

    public Curso[] getCursos() {
        return cursos;
    }
}
