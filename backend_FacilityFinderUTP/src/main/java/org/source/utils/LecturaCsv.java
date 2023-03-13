package org.source.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class LecturaCsv {

    private final String UrlScv;
    final String SEPARADOR = ",";
    BufferedReader bufferLectura = null;


    // Definimos rutas de los archivos CSV donde se encuentra la data: uno para docentes y otro para estudiantes.
    static Path path = Paths.get("backend_FacilityFinderUTP", "src\\main\\java\\org\\source\\csvs\\csv_docente.csv");
    static Path path2 = Paths.get("backend_FacilityFinderUTP", "src\\main\\java\\org\\source\\csvs\\csv_estudiante.csv");

    // Se obtiene la ruta absoluta para el archivo CSV de docentes y se almacena en la constante LINK_CSV_DOCENTE
    static final private String LINK_CSV_DOCENTE = path.toAbsolutePath().toString();;

    // Se obtiene la ruta absoluta para el archivo CSV de estudiantes y se almacena en la constante LINK_CSV_ALUMNO
    static final private String LINK_CSV_ALUMNO = path2.toAbsolutePath().toString();


    public LecturaCsv(String urlScv) {
        this.UrlScv = urlScv;
    }

    //La función usa el csv pasado por parametro para luego devoler un linked list de String[]
    public LinkedList<String[]> leer() {
        LinkedList<String[]> arrayCsv = new LinkedList<>();
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(UrlScv));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(SEPARADOR);
                arrayCsv.add(campos);
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayCsv;
    }

    // Se añaden los gets, que se usarán en los servicios.
    public static String getRutaCsvDocentes() {
        return LINK_CSV_DOCENTE;
    }

    public static String getRutaCsvAlumnos() {
        return LINK_CSV_ALUMNO;
    }
}
