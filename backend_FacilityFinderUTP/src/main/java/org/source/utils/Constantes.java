package org.source.utils;

import com.opencsv.exceptions.CsvException;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Constantes {

    // Forma para que no importa donde sea la computadora, no se cambiará la ruta.
    private static final Path CsvDataHorarios = Paths.get("backend_FacilityFinderUTP","src", "main", "java", "org", "source", "csvs", "DataHorarios.csv");

    // Obtenemos las ruta
    public static String RutaCsvDataHorarios() {
        return CsvDataHorarios.toAbsolutePath().toString();
    }

    private static LecturaCsv lecturaCsv = new LecturaCsv(RutaCsvDataHorarios());

    // Obtenermos la data, como es algo que está en una "base de datos" csv, entonces será estatica.
    // En el primer Hashmap estará la data de los alumnos, en el segundo la data de los profesores.
    // En el tercero la data de los cursos, la cuarta de las clases que habrán.
    private static ProcesarCsv totalData = new ProcesarCsv(lecturaCsv.leer());

    public static void main(String[] args) {
        for (String[] array : lecturaCsv.leer()) {
            // Imprimir los elementos del array en la consola
            for (String elemento : array) {
                System.out.print(elemento + " ");
            }
            System.out.println(); // Agregar un salto de línea después de cada array
        }
    }

    public static HashMap <String,LinkedList <Profesor[]>>[] totalData() throws IOException, CsvException {return totalData.createData();}
}
