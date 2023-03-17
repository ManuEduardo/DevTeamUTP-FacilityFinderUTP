package org.source.utils;

import com.opencsv.exceptions.CsvException;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constantes {

    // Forma para que no importa donde sea la computadora, no se cambiará la ruta.
    private static final Path CsvDataHorarios = Paths.get("backend_FacilityFinderUTP","src", "main", "java", "org", "source", "csvs", "csv_docente.csv");

    // Obtenemos las ruta
    public static String RutaCsvDataHorarios() {
        return CsvDataHorarios.toAbsolutePath().toString();
    }

    // Obtenermos la data, como es algo que está en una "base de datos" csv, entonces será estatica.
    public static Profesor[] dataProfesor() throws IOException, CsvException {return ProcesarCsv.leerProfesoresDesdeCsv("a");}
    public static Estudiante[] dataEstudiante() throws IOException, CsvException {return ProcesarCsv.leerEstudiantesDesdeCsv(dataProfesor(),"");}

}
