package org.source.modelos;

public class Estudiante extends PersonaUniversitaria{

    //Cursos que lleva el alumno.
    private Curso[] cursos;
    public Estudiante(String nombre, String codigo) {
        super(nombre, codigo);
    }

    public Estudiante(String nombre, String codigo, Curso[] cursos) {
        super(nombre, codigo);
        this.cursos = cursos;
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }
}
