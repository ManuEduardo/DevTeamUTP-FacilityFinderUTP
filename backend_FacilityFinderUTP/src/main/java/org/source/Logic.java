package org.source;

import org.source.utils.LecturaCsv;

import java.util.ArrayList;
import java.util.LinkedList;

public class Logic {

    static final private String LinkCsv_docente = "C:\\Users\\gabri\\IdeaProjects\\DevTeamUTP-FacilityFinderUTP\\backend_FacilityFinderUTP\\src\\main\\java\\org\\source\\csvs\\csv_docente.csv";
    static final private String LinkCsv_alumno =  "csv_estudiante.csv";
    public static void main(String[] args) {
        // Llamar a la función con la ruta del archivo csv
        ArrayList<String[]> datos = leerCsv(LinkCsv_docente);

        // Mostrar el contenido del ArrayList
        for (String[] fila : datos) {
            for (String valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    // Función que lee un archivo csv y devuelve un ArrayList<String[]>
    public static ArrayList<String[]> leerCsv(String ruta) {
        // Crear una instancia de LecturaCsv con la ruta del archivo
        LecturaCsv lector = new LecturaCsv(ruta);

        // Leer el archivo y obtener una lista de String[]
        LinkedList<String[]> lista = lector.leer();

        // Crear un ArrayList<String[]> para guardar los datos
        ArrayList<String[]> datos = new ArrayList<>();

        // Recorrer la lista y eliminar los caracteres "[" y "]" si existen
        for (String[] fila : lista) {
            for (int i = 0; i < fila.length; i++) {
                fila[i] = fila[i].replace("[", "").replace("]", "");
            }
            // Añadir la fila al ArrayList
            datos.add(fila);
        }

        // Devolver el ArrayList
        return datos;
    }

}
