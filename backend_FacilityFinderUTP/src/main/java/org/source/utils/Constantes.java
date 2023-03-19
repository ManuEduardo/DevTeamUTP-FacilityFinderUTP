package org.source.utils;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta es la descripción de la clase Constantes:
 * @author Gabriel Paiva
 */

public class Constantes {

    // Forma para que no importa donde sea la computadora, no se cambiará la ruta.
    private static final Path CsvDataHorarios = Paths.get("src", "main", "java", "org", "source", "csvs", "DataHorarios.csv");
    private static final Path ErrorLog = Paths.get("src", "main", "resources", "error.log");
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

    public static void main(String[] args) throws IOException {
        totalData.createData();
    }

    public static HashMap <String,Object>[] dataProcesada() throws IOException {
        return totalData.createData();
    }

    public static HashMap <String,Profesor> dataProfesor() throws IOException {
        return convertirHashMapProfesor(dataProcesada()[1]);
    }

    public static HashMap <String,Estudiante> dataEstudiante() throws IOException {
        return convertirHashMapEstudiante(dataProcesada()[0]);
    }

    public static HashMap<String, Estudiante> convertirHashMapEstudiante(HashMap<String, Object> mapa) {
        HashMap<String, Estudiante> nuevoMapa = new HashMap<>();

        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            String clave = entry.getKey();
            Estudiante estudiante = (Estudiante) entry.getValue(); // casting explícito a la clase Estudiante
            nuevoMapa.put(clave, estudiante);
        }

        return nuevoMapa;
    }

    public static HashMap<String, Profesor> convertirHashMapProfesor(HashMap<String, Object> mapa) {
        HashMap<String, Profesor> nuevoMapa = new HashMap<>();

        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            String clave = entry.getKey();
            Profesor profesor= (Profesor) entry.getValue(); // casting explícito a la clase Estudiante
            nuevoMapa.put(clave, profesor);
        }

        return nuevoMapa;
    }
}
