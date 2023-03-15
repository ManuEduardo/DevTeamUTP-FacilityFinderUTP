package org.source.utils;

import com.opencsv.exceptions.CsvException;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constantes {

    private static final Path PathCsvDocente = Paths.get("src", "main", "java", "org", "source", "csvs", "csv_docente.csv");
    private static final Path PathCsvEstudiante = Paths.get("src", "main", "java", "org", "source", "csvs", "csv_estudiante.csv");

    // Obtenemos las ruta
    public static String RutaCsvDocente() {
        return PathCsvDocente.toAbsolutePath().toString();
    }

    public static String RutaCsvEstudiante() {
        return PathCsvEstudiante.toAbsolutePath().toString();
    }

    // Obtenermos la data, como es algo que está en una "base de datos" csv, entonces será estatica.
    public static Profesor[] dataProfesor() throws IOException, CsvException {return ProcesarCsv.leerProfesoresDesdeCsv(Constantes.RutaCsvDocente());}
    public static Estudiante[] dataEstudiante() throws IOException, CsvException {return ProcesarCsv.leerEstudiantesDesdeCsv(dataProfesor(),Constantes.RutaCsvEstudiante());}
}
