package org.source;

import org.source.utils.LecturaCsv;

import java.util.LinkedList;

public class Logic {

    static final private String LinkCsv_docente = "C:\\Users\\gabri\\IdeaProjects\\DevTeamUTP-FacilityFinderUTP\\backend_FacilityFinderUTP\\src\\main\\java\\org\\source\\csvs\\csv_docente.csv";
    static final private String LinkCsv_alumno =  "csv_estudiante.csv";
    public static void main(String[] args) {
        // Crear un objeto LecturaCsv pasando como parámetro la ruta del archivo CSV
        LecturaCsv lector = new LecturaCsv(LinkCsv_docente);

        // Llamar al método leer para obtener los datos del archivo en una lista
        LinkedList<String[]> datos = lector.leer();

        // Recorrer la lista e imprimir cada fila por pantalla
        for (String[] fila : datos) {
            java.util.Arrays.toString(fila);
        }
    }


}
