package org.source.modelos;

public class Curso {
    public String nombre;
    public Profesor profesor;
    public Clase[] clases;

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
}
