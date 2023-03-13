package org.source.modelos;

public class Curso {
    private String NombreCurso;
    private String NombreProfesor;
    private Clase[] clases;

    public Curso(String nombre, Profesor profesor, Clase[] clases) {
        this.NombreCurso = nombre;
        this.clases = clases;
    }

    public Curso(String nombre, Clase[] clases) {
        this.NombreCurso = nombre;
        this.clases = clases;
    }

    public Curso(Clase[] clases) {
        this.clases = clases;
    }

    public void setNombreCurso(String nombreCurso) {
        this.NombreCurso = nombreCurso;
    }

    public String getNombreCurso() {
        return NombreCurso;
    }

    public Clase[] getClases() {
        return clases;
    }

    public void setClases(Clase[] clases) {
        this.clases = clases;
    }
}
