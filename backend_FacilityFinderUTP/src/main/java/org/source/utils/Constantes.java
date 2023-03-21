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
 * @version 1.0
 * @since 2023-03-19
 */

public class Constantes {

    /**
     * Rutas de archivo que representan la ubicación de dos archivos en el proyecto.:
     */
    private static final Path CsvDataHorarios = Paths.get("backend_FacilityFinderUTP","src", "main", "java", "org", "source", "csvs", "DataHorarios.csv");
    private static final Path ErrorLog = Paths.get("backend_FacilityFinderUTP","src", "main", "resources", "error.log");
    public static String RutaCsvDataHorarios() {
        return CsvDataHorarios.toAbsolutePath().toString();
    }
    public static String RutaErrorLog() {
        return ErrorLog.toAbsolutePath().toString();
    }

    /**
     * Creación de un objeto de la clase LecturaCsv utilizando el método RutaCsvDataHorarios()
     * para obtener la ruta del archivo CSV que se utilizará para la lectura.
     */
    private static LecturaCsv lecturaCsv = new LecturaCsv(RutaCsvDataHorarios());

    /**
     * Obtenermos la data, como es algo que está en una "base de datos" csv, entonces
     * será estatica. En el primer Hashmap estará la data de los alumnos, en el
     * segundo la data de los profesores. Finalmente, convertiremos de clase Object a la
     * clase que corresponde, o profesor o estudiante.
     */
    private static ProcesarCsv totalData = new ProcesarCsv(lecturaCsv.leer()); // Constructor de la clase ProcesarCsv
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

        // Primero, se crea un nuevo HashMap que tendrá las mismas claves que el
        // HashMap original, pero con valores de tipo Estudiante.
        HashMap<String, Estudiante> nuevoMapa = new HashMap<>();

        // Luego, se recorre cada entrada del HashMap original utilizando
        // un ciclo for-each.
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {

            // Dentro del ciclo, se obtiene la clave y el valor correspondientes
            // a cada entrada.
            String clave = entry.getKey();
            Estudiante estudiante = (Estudiante) entry.getValue(); // casting explícito a la clase Estudiante
            nuevoMapa.put(clave, estudiante);

        }

        return nuevoMapa;

    }
    public static HashMap<String, Profesor> convertirHashMapProfesor(HashMap<String, Object> mapa) {

        // Funciona de la misma manera que: convertirHashMapEstudiante pero con el objeto Profesor.
        HashMap<String, Profesor> nuevoMapa = new HashMap<>();

        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            String clave = entry.getKey();
            Profesor profesor= (Profesor) entry.getValue(); // casting explícito a la clase Estudiante
            nuevoMapa.put(clave, profesor);
        }

        return nuevoMapa;

    }
}
