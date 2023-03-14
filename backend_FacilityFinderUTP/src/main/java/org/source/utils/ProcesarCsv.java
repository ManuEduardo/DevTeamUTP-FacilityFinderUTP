package org.source.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.source.modelos.Clase;
import org.source.modelos.Curso;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProcesarCsv {

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
        Map<Profesor, List<Curso>> cursosPorProfesor = new HashMap<>();

        for (String[] fila : filas) {
            String nombreProfesor = convertirCamelCaseATitulo(fila[0]);
            String codigoProfesor = fila[1];
            String nombreCurso = fila[2];
            String codigoAula = fila[3];
            String diaSemana = fila[4];
            String horaInicio = fila[5];
            String horaFinal = fila[6];

            Clase clase = new Clase(diaSemana, horaInicio, horaFinal, codigoAula);
            Curso curso = new Curso(nombreCurso, nombreProfesor, new Clase[]{clase});

            Profesor profesor = new Profesor(nombreProfesor, codigoProfesor, null);

            if (!cursosPorProfesor.containsKey(profesor)) {
                cursosPorProfesor.put(profesor, new ArrayList<>());
            }

            cursosPorProfesor.get(profesor).add(curso);
        }

        Profesor[] profesores = new Profesor[cursosPorProfesor.size()];
        int i = 0;

        for (Map.Entry<Profesor, List<Curso>> entry : cursosPorProfesor.entrySet()) {
            Profesor profesor = entry.getKey();
            List<Curso> cursos = entry.getValue();

            profesor.setCursos(cursos.toArray(new Curso[0]));
            profesores[i] = profesor;
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
            Curso[] cursos = new Curso[]{new Curso(obtenerNombreCurso(diaSemana, horaInicio, horaFinal, codigoAula, profesores), obtenerNombreProfesor(diaSemana, horaInicio, horaFinal, codigoAula, profesores), new Clase[]{new Clase(diaSemana, horaInicio, horaFinal, codigoAula)})};
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
        return titulo.toUpperCase().charAt(0) + titulo.substring(1) + ".";
    }

    public static String[] buscarClaseMasCercana(String codigoEstudiante, Estudiante[] estudiantes) {
        // Buscar al estudiante en el array
        Estudiante estudiante = null;
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equals(codigoEstudiante)) {
                estudiante = e;
                break;
            }
        }
        if (estudiante == null) {
            return null;
        }

        // Obtener la fecha y hora actual
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Obtener el día de la semana actual
        DayOfWeek diaActual = fechaActual.getDayOfWeek();

        // Obtener la lista de clases del estudiante
        List<Clase> clases = new ArrayList<>();
        for (Curso curso : estudiante.getCursos()) {
            clases.addAll(List.of(curso.getClases()));
        }

        // Obtener la clase más cercana
        Clase claseMasCercana = null;
        long minutosMasCercanos = Long.MAX_VALUE;
        for (Clase clase : clases) {
            // Obtener el día de la semana de la clase
            DayOfWeek diaClase = DayOfWeek.valueOf(convertirPalabra(clase.getDiaSemana()).toUpperCase());

            // Si el día de la clase es anterior al día actual, considerar la próxima clase en la siguiente semana
            if (diaClase.getValue() < diaActual.getValue()) {
                diaClase = diaClase.plus(7);
            }

            // Si el día de la clase es el mismo que el día actual o en el futuro
            if (diaClase.getValue() == diaActual.getValue()) {
                // Obtener la hora de inicio de la clase
                LocalTime horaInicio = LocalTime.parse(clase.getHoraInicio(), DateTimeFormatter.ISO_LOCAL_TIME);

                // Si la hora de inicio ya pasó hoy, considerar la próxima clase en la siguiente semana
                if (horaInicio.isBefore(horaActual)) {
                    diaClase = diaClase.plus(7);
                }

                // Calcular los minutos entre la hora actual y la hora de inicio de la clase
                long minutos = horaActual.until(horaInicio, java.time.temporal.ChronoUnit.MINUTES);

                // Si es la clase más cercana hasta ahora, actualizar los valores
                if (claseMasCercana == null || minutos < minutosMasCercanos) {
                    claseMasCercana = clase;
                    minutosMasCercanos = minutos;
                }
            }
        }

        // Si se encontró una clase cercana, devolver sus datos en un array de Strings
        if (claseMasCercana != null) {
            String[] resultado = new String[4];
            resultado[0] = Objects.requireNonNull(buscarCursoPorClase(claseMasCercana.getAulaClase(), claseMasCercana.getDiaSemana(), claseMasCercana.getHoraInicio(), estudiante.getCursos()))[0];
            resultado[1] = Objects.requireNonNull(buscarCursoPorClase(claseMasCercana.getAulaClase(), claseMasCercana.getDiaSemana(), claseMasCercana.getHoraInicio(), estudiante.getCursos()))[1];
            resultado[2] = claseMasCercana.getAulaClase();
            resultado[3] = claseMasCercana.getHoraInicio() + " - " + claseMasCercana.getHoraFinal();
            return resultado;
        }

        // Si no se encontró ninguna clase cercana, devolver null
        return null;
    }

    public static String[] buscarProfesor(String codigoProfesor, Profesor[] profesores) {

        Profesor profesor = null;
        for (Profesor e : profesores) {
            if (e.getCodigo().equals(codigoProfesor)) {
                profesor = e;
                break;
            }
        }
        if (profesor == null) {
            return null;
        }

        // Obtener la fecha y hora actual
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Obtener el día de la semana actual
        DayOfWeek diaActual = fechaActual.getDayOfWeek();

        // Obtener la lista de clases del profesor
        List<Clase> clases = new ArrayList<>();
        for (Curso curso : profesor.getCursos()) {
            clases.addAll(List.of(curso.getClases()));
        }

        // Obtener la clase más cercana
        Clase claseMasCercana = null;
        long minutosMasCercanos = Long.MAX_VALUE;
        for (Clase clase : clases) {
            // Obtener el día de la semana de la clase
            DayOfWeek diaClase = DayOfWeek.valueOf(convertirPalabra(clase.getDiaSemana()).toUpperCase());

            // Si el día de la clase es anterior al día actual, considerar la próxima clase en la siguiente semana
            if (diaClase.getValue() < diaActual.getValue()) {
                diaClase = diaClase.plus(7);
            }

            // Si el día de la clase es el mismo que el día actual o en el futuro
            if (diaClase.getValue() == diaActual.getValue()) {
                // Obtener la hora de inicio de la clase
                // Crear un objeto DateTimeFormatter para el formato de hora actualizado
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

                // Analizar la cadena utilizando el nuevo formato
                LocalTime horaInicio = LocalTime.parse(clase.getHoraInicio(), formatter);

                // Si la hora de inicio ya pasó hoy, considerar la próxima clase en la siguiente semana
                if (horaInicio.isBefore(horaActual)) {
                    diaClase = diaClase.plus(7);
                    claseMasCercana = clase;
                }

                // Calcular los minutos entre la hora actual y la hora de inicio de la clase
                long minutos = horaActual.until(horaInicio, java.time.temporal.ChronoUnit.MINUTES);

                // Si es la clase más cercana hasta ahora, actualizar los valores
                if (claseMasCercana == null || minutos < minutosMasCercanos) {
                    claseMasCercana = clase;
                    minutosMasCercanos = minutos;
                }
            }
            if (claseMasCercana == null) {
                claseMasCercana = clase;
            }
        }

        // Si se encontró una clase cercana, devolver sus datos en un array de Strings
        if (claseMasCercana != null) {
            String[] resultado = new String[4];
            resultado[0] = Objects.requireNonNull(buscarCursoPorClase(claseMasCercana.getAulaClase(), claseMasCercana.getDiaSemana(), claseMasCercana.getHoraInicio(), profesor.getCursos()))[0];
            resultado[1] = Objects.requireNonNull(buscarCursoPorClase(claseMasCercana.getAulaClase(), claseMasCercana.getDiaSemana(), claseMasCercana.getHoraInicio(), profesor.getCursos()))[1];
            resultado[2] = claseMasCercana.getAulaClase();
            resultado[2] = claseMasCercana.getAulaClase();
            resultado[3] = claseMasCercana.getHoraInicio() + " - " + claseMasCercana.getHoraFinal();
            return resultado;
        }

        // Si no se encontró ninguna clase cercana, devolver null
        return null;
    }

            public static String[] buscarCursoPorClase(String aulaClase, String diaSemana, String horaInicio, Curso[] cursos) {
        for (Curso curso : cursos) {
            for (Clase clase : curso.getClases()) {
                if (clase.getAulaClase().equals(aulaClase) && clase.getDiaSemana().equals(diaSemana) &&
                        clase.getHoraInicio().equals(horaInicio)) {
                    String[] datosCurso = new String[4];
                    datosCurso[0] = curso.getNombreProfesor();
                    datosCurso[1] = convertirCamelCaseATitulo(curso.getNombreCurso());
                    datosCurso[2] = clase.getAulaClase();
                    datosCurso[3] = clase.getHoraInicio() + " - " + clase.getHoraFinal();
                    return datosCurso;
                }
            }
        }
        return null;
    }
    public static String convertirPalabra(String palabra) {
        String resultado = "";
        switch(palabra.toLowerCase()) {
            case "lunes":
                resultado = "Monday";
                break;
            case "martes":
                resultado = "Tuesday";
                break;
            case "miércoles":
                resultado = "Wednesday";
                break;
            case "jueves":
                resultado = "Thursday";
                break;
            case "viernes":
                resultado = "Friday";
                break;
            case "sábado":
                resultado = "Saturday";
                break;
            case "domingo":
                resultado = "Sunday";
                break;
            case "profesor":
                resultado = "professor";
                break;
            case "curso":
                resultado = "course";
                break;
            case "aula de clase":
                resultado = "classroom";
                break;
            case "hora":
                resultado = "hour";
                break;
            default:
                resultado = palabra;
                break;
        }
        return resultado;
    }

}