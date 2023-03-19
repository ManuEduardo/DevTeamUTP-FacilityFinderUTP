package org.source.modelos;

import java.util.HashSet;

/**
 * Esta es la descripción de la clase Profesor.
 * La clase "Profesor" es una subclase de "PersonaUniversitaria" y representa
 * a un profesor universitario que imparte uno o varios cursos. La clase tiene
 * dos constructores, uno que recibe el nombre del profesor, su código y un array
 * de cursos, y otro que recibe solo el nombre y el código del profesor, pero
 * inicializa su array de cursos como vacío. La clase tiene métodos para agregar
 * un curso al array de cursos, eliminar cursos duplicados y agregar varios cursos
 * a la vez. También tiene los getters y setters para el array de cursos.
 * @author Gabriel Paiva
 */

public class Profesor extends PersonaUniversitaria{

    /** Atributos de la clase Profesor. */
    private Curso[] cursos; //Cursos que imparte el profesor.

    /** Constructores de la clase Profesor. */
    public Profesor(String nombre, String codigo, Curso[] cursos) {
        super(nombre, codigo);
        this.cursos = cursos;
    }
    public Profesor(String nombre, String codigo) {
        super(nombre, codigo);
        this.cursos = new Curso[0];
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

    }
    public void setAgregarNuevosCursos(Curso[] nuevosCursos) {

        // Crear un nuevo array con espacio suficiente para los cursos existentes y los nuevos cursos.
        Curso[] cursosActualizados = new Curso[cursos.length + nuevosCursos.length];

        // Copiar los cursos existentes al nuevo array.
        System.arraycopy(cursos, 0, cursosActualizados, 0, cursos.length);

        // Copiar los nuevos cursos al final del nuevo array.
        System.arraycopy(nuevosCursos, 0, cursosActualizados, cursos.length, nuevosCursos.length);

        // Reemplazar el array antiguo por el nuevo array.
        cursos = cursosActualizados;
    }
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
    }

}
