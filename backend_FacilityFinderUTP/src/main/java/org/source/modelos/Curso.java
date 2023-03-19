package org.source.modelos;

public class Curso {
    private String NombreCurso;
    private String profesor;

    // Clases a la semana.
    private Clase[] clases;

    public Curso() {
    }

    public Curso(String nombreCurso, String profesor, Clase[] clases) {
        NombreCurso = nombreCurso;
        this.profesor = profesor;
        this.clases = clases;
    }

    public Curso(String nombreCurso, String profesor) {
        NombreCurso = nombreCurso;
        this.profesor = profesor;
        this.clases = new Clase[0];
    }

    public void agregarClase(Clase clase) {
        Clase[] nuevasClases = new Clase[clases.length + 1]; // Crear un nuevo array con espacio para un curso más.
        for (int i = 0; i < clases.length; i++) {
            nuevasClases[i] = clases[i]; // Copiar los cursos existentes al nuevo array.
        }
        nuevasClases[clases.length] = clase; // Añadir el nuevo curso al final del nuevo array.
        clases = nuevasClases; // Reemplazar el array antiguo por el nuevo array.
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
