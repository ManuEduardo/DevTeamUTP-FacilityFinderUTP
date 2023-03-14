package org.source.modelos;

public class Profesor extends PersonaUniversitaria{
    Curso[] cursos;
    public Profesor(String nombre, String codigo) {
        super(nombre, codigo);
    }

    public Profesor(String nombre, String codigo, Curso[] cursos) {
        super(nombre, codigo);
        this.cursos = cursos;
    }

    public Profesor(Curso[] cursos) {
        this.cursos = cursos;
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }
}
