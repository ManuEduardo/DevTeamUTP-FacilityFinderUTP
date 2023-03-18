package org.source.modelos;

public class Curso {
    private String NombreCurso;
    private String profesor;

    // Clases a la semana.
    private Clase[] clases;

    public Curso(String nombreCurso, String profesor, Clase[] clases) {
        NombreCurso = nombreCurso;
        this.profesor = profesor;
        this.clases = clases;
    }

    public String getNombreCurso() {
        return NombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        NombreCurso = nombreCurso;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Clase[] getClases() {
        return clases;
    }

    public void setClases(Clase[] clases) {
        this.clases = clases;
    }
}
