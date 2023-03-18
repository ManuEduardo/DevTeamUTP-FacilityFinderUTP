package org.source.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Estudiante extends PersonaUniversitaria{

    //Cursos que lleva el alumno.
    private Curso[] cursos;
    public Estudiante(String nombre, String codigo) {
        super(nombre, codigo);
        cursos = new Curso[0]; // Inicializar el array de cursos vacío.
    }

    public Estudiante(String nombre, String codigo, Curso[] cursos) {
        super(nombre, codigo);
        this.cursos = cursos;
    }

    public void agregarCurso(Curso curso) {
        Curso[] nuevosCursos = new Curso[cursos.length + 1]; // Crear un nuevo array con espacio para un curso más.
        for (int i = 0; i < cursos.length; i++) {
            nuevosCursos[i] = cursos[i]; // Copiar los cursos existentes al nuevo array.
        }
        nuevosCursos[cursos.length] = curso; // Añadir el nuevo curso al final del nuevo array.
        cursos = nuevosCursos; // Reemplazar el array antiguo por el nuevo array.
    }

    public void oneCurse() {
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
}
