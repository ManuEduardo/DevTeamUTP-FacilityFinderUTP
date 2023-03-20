package org.source.modelos;
import java.util.HashSet;

/**
 * Esta es la descripción de la clase Estudiante:
 * La clase Estudiante representa un estudiante universitario y hereda de la
 * clase PersonaUniversitaria. Tiene dos constructores que toman el nombre,
 * el código y una lista de cursos que el estudiante está llevando. La clase tiene
 * un método setAgregarCurso que agrega un curso al arreglo de cursos que
 * lleva el estudiante. También tiene un método aEliminarCursosDuplicados
 * que elimina los cursos duplicados en el arreglo de cursos.
 * @author Gabriel Paiva
 */

public class Estudiante extends PersonaUniversitaria{

    /** Atributos. */
    private Curso[] cursos; // Cursos que lleva el alumno.

    /** Constructores. */
    public Estudiante(String nombre, String codigo) {
        super(nombre, codigo);
        cursos = new Curso[0]; // Inicializar el array de cursos vacío.
    }
    public Estudiante(String nombre, String codigo, Curso[] cursos) {
        super(nombre, codigo);
        this.cursos = cursos;
    }

    /** Getters and Setters. */
    public void setAgregarCurso(Curso curso) {

        // Crear un nuevo array con espacio para un curso más.
        Curso[] nuevosCursos = new Curso[cursos.length + 1];

        // Copiar los cursos existentes al nuevo array.
        System.arraycopy(cursos, 0, nuevosCursos, 0, cursos.length);

        // Añadir el nuevo curso al final del nuevo array.
        nuevosCursos[cursos.length] = curso;

        // Reemplazar el array antiguo por el nuevo array.
        cursos = nuevosCursos;

    } // Añade un objeto Curso al array de objetos Curso.
    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }
    public Curso[] getCursos() {
        return cursos;
    }

    /**
     * Métodos:
     * El método aEliminarCursosDuplicados() cumple la función de
     * eliminar cursos duplicados del arreglo de cursos del estudiante.
     */
    public void aEliminarCursosDuplicados() {

        // Convertir el array de cursos en un HashSet para eliminar duplicados
        HashSet<Curso> cursosUnicos = new HashSet<Curso>();
        for (Curso curso : cursos) {
            cursosUnicos.add(curso);
        }

        // Convertir el HashSet de cursos únicos en un array
        Curso[] cursosArray = new Curso[cursosUnicos.size()];
        int i = 0;
        for (Curso curso : cursosUnicos) {
            cursosArray[i] = curso;
            i++;
        }

        cursos = cursosArray;
    } // Método para eliminar cursos duplicados.
}
