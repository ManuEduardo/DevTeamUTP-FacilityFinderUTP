package org.source;

import org.source.utils.LecturaCsv;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //TEST PASA LOS CSV
        LecturaCsv leerCsvAlumnos = new LecturaCsv("src/main/java/org/source/csvs/alumnos.csv");
        LinkedList<String[]> tablaAlumnos = leerCsvAlumnos.leer();
        tablaAlumnos.forEach((campos) -> {
            System.out.println(Arrays.toString(campos));
        });

        LecturaCsv leerCsvProfesores = new LecturaCsv("src/main/java/org/source/csvs/profesores.csv");
        LinkedList<String[]> tablaProfesores = leerCsvProfesores.leer();
        tablaProfesores.forEach((campos) -> {
            System.out.println(Arrays.toString(campos));
        });
    }
}