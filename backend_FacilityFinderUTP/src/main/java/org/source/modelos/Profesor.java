package org.source.modelos;

import java.util.HashSet;

public class Profesor extends PersonaUniversitaria{

    //Cursos que imparte el profesor'.
    private Curso[] cursos;

    public Profesor(String nombre, String codigo, Curso[] cursos) {
        super(nombre, codigo);
        this.cursos = cursos;
    }

    public Profesor(String nombre, String codigo) {
        super(nombre, codigo);
        this.cursos = new Curso[0];
    }

    public void agregarCurso(Curso curso) {
        Curso[] nuevosCursos = new Curso[cursos.length + 1]; // Crear un nuevo array con espacio para un curso más.
        for (int i = 0; i < cursos.length; i++) {
            nuevosCursos[i] = cursos[i]; // Copiar los cursos existentes al nuevo array.
        }
        nuevosCursos[cursos.length] = curso; // Añadir el nuevo curso al final del nuevo array.
        cursos = nuevosCursos; // Reemplazar el array antiguo por el nuevo array.
    }

    public void OneCurse() {
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

    public Curso[] getCursos() {
        return cursos;
    }

    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }

    public void agregarCursos(Curso[] nuevosCursos) {
        Curso[] cursosActualizados = new Curso[cursos.length + nuevosCursos.length]; // Crear un nuevo array con espacio suficiente para los cursos existentes y los nuevos cursos.
        System.arraycopy(cursos, 0, cursosActualizados, 0, cursos.length); // Copiar los cursos existentes al nuevo array.
        System.arraycopy(nuevosCursos, 0, cursosActualizados, cursos.length, nuevosCursos.length); // Copiar los nuevos cursos al final del nuevo array.
        cursos = cursosActualizados; // Reemplazar el array antiguo por el nuevo array.
    }

}
