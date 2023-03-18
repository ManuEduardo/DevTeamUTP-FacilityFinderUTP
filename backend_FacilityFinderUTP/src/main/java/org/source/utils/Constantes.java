package org.source.utils;

import com.opencsv.exceptions.CsvException;
import org.source.Validaciones.ErrorLog;
import org.source.Validaciones.Validadores;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static org.source.utils.ProcesarCsv.separarDataAula;

public class Constantes {

    // Forma para que no importa donde sea la computadora, no se cambiará la ruta.
    private static final Path CsvDataHorarios = Paths.get("backend_FacilityFinderUTP","src", "main", "java", "org", "source", "csvs", "DataHorarios.csv");
    private static final Path ErrorLog = Paths.get("backend_FacilityFinderUTP","src", "main", "resources", "error.log");
    // Obtenemos las ruta
    public static String RutaCsvDataHorarios() {
        return CsvDataHorarios.toAbsolutePath().toString();
    }
    public static String RutaErrorLog() {
        return ErrorLog.toAbsolutePath().toString();
    }

    private static LecturaCsv lecturaCsv = new LecturaCsv(RutaCsvDataHorarios());

    // Obtenermos la data, como es algo que está en una "base de datos" csv, entonces será estatica.
    // En el primer Hashmap estará la data de los alumnos, en el segundo la data de los profesores.
    // En el tercero la data de los cursos, la cuarta de las clases que habrán.
    private static ProcesarCsv totalData = new ProcesarCsv(lecturaCsv.leer());

    public static void main(String[] args) throws IOException, CsvException {
        Estudiante Juan = (Estudiante) totalData()[0].get("U19200293");
    }

    public static HashMap <String,Object>[] totalData() throws IOException, CsvException {return totalData.createData();}


}
