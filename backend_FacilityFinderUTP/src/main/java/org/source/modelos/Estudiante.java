package org.source.modelos;

public class Estudiante extends PersonaUniversitaria{
    private Curso[] cursos;
    public Estudiante(String nombre, String codigo) {
        super(nombre, codigo);
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }
}
