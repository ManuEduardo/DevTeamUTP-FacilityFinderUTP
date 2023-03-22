package org.source.modelos;

import java.util.Arrays;

/**
 * Esta es la descripción de la clase Curso:
 * La clase Curso es una representación de un curso que se desarrolla en una
 * universidad. Tiene como atributos el nombre del curso, el nombre del
 * profesor que brinda la clase y un arreglo de objetos Clase que representan las
 * clases desarrolladas semanalmente. Cuenta con tres constructores y métodos
 * para agregar una nueva clase al arreglo de clases y para obtener y establecer
 * los atributos del curso y las clases.
 * @author Gabriel Paiva
 */

public class Curso {
    @Override
    public String toString() {
        return "Curso{" +
                "NombreCurso='" + NombreCurso + '\'' +
                ", nombreProfesor='" + nombreProfesor + '\'' +
                ", clases=" + Arrays.toString(clases) +
                '}';
    }

    /** Atributos. */
    private String NombreCurso; // Nombre del curso que se desarrolla.
    private String nombreProfesor; // Nombre del profesor que brinda la clase.
    private Clase[] clases; // Clases desarrolladas semanalmente.

    /** Constructores. */
    public Curso() {
    }
    public Curso(String nombreCurso, String nombreProfesor) {
        NombreCurso = nombreCurso;
        this.nombreProfesor = nombreProfesor;
        this.clases = new Clase[0];
    }
    public Curso(String nombreCurso, String nombreProfesor, Clase[] clases) {
        NombreCurso = nombreCurso;
        this.nombreProfesor = nombreProfesor;
        this.clases = clases;
    }

    /** Getters and Setters. */
    public void setClases(Clase[] clases) {
        this.clases = clases;
    }
    public void setAgregarClase(Clase clase) {

        // Crear un nuevo array con espacio para un curso más.
        Clase[] nuevasClases = new Clase[clases.length + 1];

        // Copiar los cursos existentes al nuevo array.
        System.arraycopy(clases, 0, nuevasClases, 0, clases.length);

        // Añadir el nuevo curso al final del nuevo array.
        nuevasClases[clases.length] = clase;

        // Reemplazar el array antiguo por el nuevo array.
        clases = nuevasClases;

    } // Añade un objeto Clase al array de objetos Clase.
    public void setNombreCurso(String nombreCurso) {
        NombreCurso = nombreCurso;
    }
    public String getNombreCurso() {
        return NombreCurso;
    }
    public String getNombreProfesor() {
        return nombreProfesor;
    }
    public Clase[] getClases() {
        return clases;
    }

}
