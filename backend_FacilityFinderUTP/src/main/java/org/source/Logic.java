package org.source;

import org.source.modelos.Clase;
import org.source.modelos.Curso;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;
import org.source.utils.LecturaCsv;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class Logic {

    static Path path = Paths.get("backend_FacilityFinderUTP", "src\\main\\java\\org\\source\\csvs\\csv_docente.csv");
    static Path path2 = Paths.get("backend_FacilityFinderUTP", "src\\main\\java\\org\\source\\csvs\\csv_estudiante.csv");

    static final private String LINK_CSV_DOCENTE = path.toAbsolutePath().toString();;

    static final private String LINK_CSV_ALUMNO = path2.toAbsolutePath().toString();
    static public Estudiante[] estudiantes;
    static public Profesor[] profesores;

    public static void main(String[] args) {
        profesores = crearProfesores(LINK_CSV_DOCENTE);
        estudiantes = crearEstudiantes(LINK_CSV_ALUMNO);
    }


    public static Estudiante[] crearEstudiantes(String linkCsvAlumno) {
        ArrayList<String[]> filas = leerCsv(linkCsvAlumno);

        Estudiante[] estudiantes = new Estudiante[filas.size()];

        for (int i = 0; i < filas.size(); i++) {
            String[] fila = filas.get(i);

            estudiantes[i] = new Estudiante(
                    fila[0], // nombre completo
                    fila[1], // código de alumno
                    new Curso[] {
                            new Curso(new Clase[] {
                                    new Clase(fila[3], fila[4], fila[5], fila[2]) // día de la semana, hora de inicio, hora final, código de aula
                            })
                    }
            );
        }

        return estudiantes;
    }

    public static Profesor[] crearProfesores(String linkCsvDocente) {
        ArrayList<String[]> datos = leerCsv(linkCsvDocente);

        Profesor[] profesores = new Profesor[datos.size()];

        for (int i = 0; i < datos.size(); i++) {
            String[] fila = datos.get(i);

            String nombreCompleto = fila[0];
            String codigoProfesor = fila[1];
            String curso = fila[2];
            String codigoAula = fila[3];
            String diaSemana = fila[4];
            String horaInicio = fila[5];
            String horaFinal = fila[6];

            Clase[] clases = {new Clase(diaSemana, horaInicio, horaFinal, codigoAula)};
            Curso[] cursos = {new Curso(curso, clases)};

            profesores[i] = new Profesor(nombreCompleto, codigoProfesor, cursos);
        }

        return profesores;
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
