package org.source.modelos;

public class Curso {
    private String NombreCurso;
    private Profesor profesor;

    // Clases a la semana.
    private Clase[] clases;

    public Curso(String nombreCurso, Clase[] clases) {
        NombreCurso = nombreCurso;
        this.clases = clases;
    }

    public String getNombreCurso() {
        return NombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        NombreCurso = nombreCurso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Clase[] getClases() {
        return clases;
    }

    public void setClases(Clase[] clases) {
        this.clases = clases;
    }
}
