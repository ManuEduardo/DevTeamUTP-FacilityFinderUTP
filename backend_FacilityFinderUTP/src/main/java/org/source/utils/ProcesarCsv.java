package org.source.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.source.modelos.Clase;
import org.source.modelos.Curso;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcesarCsv {

    public static void main(String[] args) throws IOException, CsvException {
        String rutaCsv = Constantes.RutaCsvDocente();

        try {
            List<Profesor> profesores = List.of(leerProfesoresDesdeCsv(rutaCsv));

            // Mostrar el contenido de cada profesor y sus cursos
            for (Profesor profesor : profesores) {
                System.out.println("Profesor: " + profesor.getNombre() + " (" + profesor.getCodigo() + ")");
                for (Curso curso : profesor.getCursos()) {
                    System.out.println("\tCurso: " + curso.getNombreCurso() + " (" + curso.getNombreCurso() + ")");
                    for (Clase clase : curso.getClases()) {
                        System.out.println("\t\tDía: " + clase.getDiaSemana() + " / Hora: " + clase.getHoraInicio() + " - " + clase.getHoraFinal() + " / Aula: " + clase.getAulaClase());
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (CsvException e) {
            System.out.println("Error al procesar el archivo CSV: " + e.getMessage());
        }

        // Leer los datos de los estudiantes desde un archivo CSV.
        Estudiante[] estudiantes = leerEstudiantesDesdeCsv(leerProfesoresDesdeCsv(rutaCsv), Constantes.RutaCsvEstudiante());

        // Mostrar los datos de los estudiantes.
        for (Estudiante estudiante : estudiantes) {
            System.out.println("Nombre del estudiante: " + estudiante.getNombre());
            System.out.println("Código del estudiante: " + estudiante.getCodigo());

            Curso[] cursos = estudiante.getCursos();
            for (Curso curso : cursos) {
                System.out.println("Nombre del curso: " + curso.getNombreCurso());
                System.out.println("Nombre del profesor: " + curso.getNombreProfesor());

                Clase[] clases = curso.getClases();
                for (Clase clase : clases) {
                    System.out.println("Día de la semana: " + clase.getDiaSemana());
                    System.out.println("Hora de inicio: " + clase.getHoraInicio());
                    System.out.println("Hora de finalización: " + clase.getHoraFinal());
                    System.out.println("Código del aula: " + clase.getAulaClase());
                }
            }

            System.out.println("------------------------");
        }
    }


    // Función que lee un archivo csv y devuelve un ArrayList<String[]>
    public static List<String[]> leerCsv(String ruta) throws IOException, CsvException {
        // Crear un lector de archivo csv con la ruta especificada.
        CSVReader lector = new CSVReader(new FileReader(ruta));

        // Leer todas las líneas del archivo csv.
        List<String[]> filas = lector.readAll();

        // Cerrar el lector.
        lector.close();

        // Devolver la lista de filas leídas.
        return filas;
    }

    public static Profesor[] leerProfesoresDesdeCsv(String ruta) throws IOException, CsvException {
        List<String[]> filas = leerCsv(ruta);
        Map<String, List<Curso>> cursosPorProfesor = new HashMap<>();

        String nombreProfesor = "";

        for (String[] fila : filas) {
            nombreProfesor = convertirCamelCaseATitulo(fila[0]);
            String codigoProfesor = fila[1];
            String nombreCurso = fila[2];
            String codigoAula = fila[3];
            String diaSemana = fila[4];
            String horaInicio = fila[5];
            String horaFinal = fila[6];

            Clase clase = new Clase(diaSemana, horaInicio, horaFinal, codigoAula);
            Curso curso = new Curso(nombreCurso, nombreProfesor, new Clase[] { clase });

            if (!cursosPorProfesor.containsKey(codigoProfesor)) {
                cursosPorProfesor.put(codigoProfesor, new ArrayList<>());
            }

            cursosPorProfesor.get(codigoProfesor).add(curso);
        }

        Profesor[] profesores = new Profesor[cursosPorProfesor.size()];
        int i = 0;

        for (Map.Entry<String, List<Curso>> entry : cursosPorProfesor.entrySet()) {
            String codigoProfesor = entry.getKey();
            List<Curso> cursos = entry.getValue();

            profesores[i] = new Profesor(nombreProfesor, codigoProfesor, cursos.toArray(new Curso[0]));
            i++;
        }

        return profesores;
    }


    public static Estudiante[] leerEstudiantesDesdeCsv(Profesor[] profesores, String ruta) throws IOException, CsvException {
        // Leer el archivo CSV.
        List<String[]> filas = leerCsv(ruta);

        // Crear un array para almacenar los objetos Estudiante.
        Estudiante[] estudiantes = new Estudiante[filas.size()];

        // Convertir cada fila en un objeto Estudiante.
        for (int i = 0; i < filas.size(); i++) {
            String[] fila = filas.get(i);
            String nombreEstudiante = fila[0];
            String codigoEstudiante = fila[1];
            String codigoAula = fila[2];
            String diaSemana = fila[3];
            String horaInicio = fila[4];
            String horaFinal = fila[5];

            // Buscar el objeto Profesor correspondiente.
            Profesor profesor = null;
            for (Profesor p : profesores) {
                if (p.getCodigo().equals(codigoAula)) {
                    profesor = p;
                    break;
                }
            }

            // Crear el objeto Estudiante y añadirlo al array.
            Curso[] cursos = new Curso[] { new Curso(obtenerNombreCurso(diaSemana, horaInicio, horaFinal, codigoAula,profesores), obtenerNombreProfesor(diaSemana, horaInicio, horaFinal, codigoAula,profesores), new Clase[] { new Clase(diaSemana, horaInicio, horaFinal, codigoAula) }) };
            Estudiante estudiante = new Estudiante(nombreEstudiante, codigoEstudiante, cursos);
            estudiantes[i] = estudiante;
        }

        return estudiantes;
    }

    public static String obtenerNombreProfesor(String diaSemana, String horaInicio, String horaFinal, String codigoAula, Profesor[] profesores) {
        for (Profesor profesor : profesores) {
            for (Curso curso : profesor.getCursos()) {
                for (Clase clase : curso.getClases()) {
                    if (clase.getDiaSemana().equals(diaSemana) &&
                            clase.getHoraInicio().equals(horaInicio) &&
                            clase.getHoraFinal().equals(horaFinal) &&
                            clase.getAulaClase().equals(codigoAula)) {
                        return profesor.getNombre();
                    }
                }
            }
        }
        return null;
    }

    public static String obtenerNombreCurso(String diaSemana, String horaInicio, String horaFinal, String codigoAula, Profesor[] profesores) {
        for (Profesor profesor : profesores) {
            for (Curso curso : profesor.getCursos()) {
                for (Clase clase : curso.getClases()) {
                    if (clase.getDiaSemana().equals(diaSemana) && clase.getHoraInicio().equals(horaInicio) &&
                            clase.getHoraFinal().equals(horaFinal) && clase.getAulaClase().equals(codigoAula)) {
                        return curso.getNombreCurso();
                    }
                }
            }
        }
        return null;
    }

    public static String convertirCamelCaseATitulo(String texto) {
        if (texto == null) {
            return "";
        }

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isUpperCase(c)) {
                resultado.append(" ");
                resultado.append(Character.toUpperCase(c));
            } else {
                resultado.append(c);
            }
        }

        String titulo = resultado.toString();
        return titulo.toUpperCase().charAt(0) + titulo.substring(1)+".";
    }

}
