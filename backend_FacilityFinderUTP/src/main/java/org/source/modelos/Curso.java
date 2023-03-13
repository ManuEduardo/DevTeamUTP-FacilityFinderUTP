package org.source.modelos;

public class Curso {
    private String nombre;
    private Profesor profesor;
    private Clase[] clases;

    public Curso(String nombre, Clase[] clases) {
        this.nombre = nombre;
        this.clases = clases;
    }

    public Curso(Clase[] clases) {
        this.clases = clases;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Clase[] getClases() {
        return clases;
    }

    public void setClases(Clase[] clases) {
        this.clases = clases;
    }
}
