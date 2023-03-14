package org.source.modelos;

public class Curso {
    private String NombreCurso;
    private String NombreProfesor;
    private Clase[] clases;

    public Curso(String nombreCurso, String nombreProfesor, Clase[] clases) {
        NombreCurso = nombreCurso;
        NombreProfesor = nombreProfesor;
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

    public String getNombreProfesor() {
        return NombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        NombreProfesor = nombreProfesor;
    }

    public void setClases(Clase[] clases) {
        this.clases = clases;
    }
}
