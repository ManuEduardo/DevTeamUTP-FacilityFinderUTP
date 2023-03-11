package org.source;
import com.google.gson.JsonObject;
import org.source.modelos.Clase;
import org.source.modelos.Curso;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;
import org.source.utils.LecturaCsv;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Logic {

    static Path path = Paths.get("backend_FacilityFinderUTP", "src\\main\\java\\org\\source\\csvs\\csv_docente.csv");
    static Path path2 = Paths.get("backend_FacilityFinderUTP", "src\\main\\java\\org\\source\\csvs\\csv_estudiante.csv");

    static final private String LINK_CSV_DOCENTE = path.toAbsolutePath().toString();;

    static final private String LINK_CSV_ALUMNO = path2.toAbsolutePath().toString();
    static public Estudiante[] estudiantes;
    static public Profesor[] profesores;

    public static void main(String[] args) {
        InitLlenadoData(LINK_CSV_DOCENTE,LINK_CSV_ALUMNO);

        boolean isCodigoA = true;
        boolean isCodigoC = true;

        //En caso ingresen un codigo del aulumno se ejecutará:
        if(isCodigoA){
            imprimirJSONRespuestaEstudiante("U90239305");
        }if (isCodigoC) {
            imprimirJSONRespuestaMestro("C2701");
        }

        // Cambiarán según la respuesta, sino no se hace la busqueda.
    }

    public static void imprimirJSONRespuestaEstudiante(String codigoEstudiante) {
        String[] resultado = buscarEstudiante(codigoEstudiante);

        if (resultado != null) {
            String[] elementos = obtenerElementosCodigoAula(resultado[0]);
            String profesor = "";
            String PabellonTorre = elementos[0];
            String Piso = elementos[1];
            String NumeroAula = elementos[2];
            String NombreCurso = convertirCamelCaseATitulo(resultado[1]);
            String Horario = resultado[2] + " - " + resultado[3];
            System.out.println(JsonRespuestaAlumno(Piso, NumeroAula, Horario, NombreCurso, profesor, PabellonTorre));
        } else {
            System.out.println("El estudiante no se encontró.");
        }
    }


    public static String[] obtenerElementosCodigoAula(String codigoAula) {
        String[] elementos = new String[3];
        if (codigoAula != null && codigoAula.length() == 5) {
            elementos[0] = codigoAula.substring(0, 1); // Pabellón
            elementos[1] = codigoAula.substring(1, 3); // Piso
            elementos[2] = codigoAula.substring(3);    // Número de aula
        }
        return elementos;
    }

    public static String[] buscarEstudiante(String codigo) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCodigo().equals(codigo)) {
                ArrayList<String> resultado = new ArrayList<String>();
                for (Curso curso : estudiante.cursos) {
                    for (Clase clase : curso.clases) {
                        resultado.add(clase.AulaClase);
                        resultado.add(curso.nombre);
                        resultado.add(clase.HoraInicio);
                        resultado.add(clase.HoraFinal);
                    }
                }
                return resultado.toArray(new String[resultado.size()]);
            }
        }
        return null;
    }

    public static String JsonRespuestaAlumno(String piso, String aula, String horario, String curso, String profesor, String torre) {
        // Crear un objeto JSON vacío
        JsonObject json = new JsonObject();

        // Agregar los parámetros como claves y valores al objeto JSON
        json.addProperty("piso", piso);
        json.addProperty("aula", aula);
        json.addProperty("horario", horario);
        json.addProperty("curso", curso);
        json.addProperty("profesor", profesor);
        json.addProperty("torre", torre);
        json.addProperty("pabellon", torre);

        // Convertir el objeto JSON a una cadena de texto
        return json.toString();
    }

    public static void imprimirJSONRespuestaMestro(String codigoProfesor) {
        String[] datos = obtenerDatos(codigoProfesor);
        String[] datosSeparados = datos[0].split(",");
        String piso = datosSeparados[0];
        String aula = datosSeparados[1];
        String horario = datosSeparados[2];
        String curso = convertirCamelCaseATitulo(datosSeparados[3]);
        String torre = datosSeparados[4];
        String pabellon = datosSeparados[5];

        System.out.println(JsonRespuestaProfesor(piso, aula, horario, curso, torre, pabellon));
    }

    public static String convertirCamelCaseATitulo(String texto) { // Define una función que recibe un String y devuelve un String
        if (texto == null) { // Verifica si el texto es nulo
            return ""; // Devuelve una cadena vacía en ese caso
        }

        StringBuilder resultado = new StringBuilder(); // Crea un objeto StringBuilder para ir construyendo el título
        for (int i = 0; i < texto.length(); i++) { // Itera sobre cada caracter del texto
            char c = texto.charAt(i); // Obtiene el caracter en la posición i
            if (Character.isUpperCase(c)) { // Si el caracter es una letra mayúscula
                resultado.append(" "); // Agrega un espacio al título
                resultado.append(Character.toLowerCase(c)); // Agrega el caracter en minúscula al título
            } else {
                resultado.append(c); // Si el caracter no es mayúscula, se agrega tal cual al título
            }
        }

        String titulo = resultado.toString(); // Convierte el StringBuilder en un String
        return Character.toUpperCase(titulo.charAt(1)) + titulo.substring(2)+"."; // Convierte la primera letra en mayúscula y devuelve el título
    }





    public static String JsonRespuestaProfesor(String piso, String aula, String horario, String curso, String torre, String pabellon) {
        // Crear un objeto JSON vacío
        JsonObject json = new JsonObject();

        // Agregar los parámetros como claves y valores al objeto JSON
        json.addProperty("piso", piso);
        json.addProperty("aula", aula);
        json.addProperty("horario", horario);
        json.addProperty("curso", curso);
        json.addProperty("torre", torre);
        json.addProperty("pabellon", pabellon);

        // Convertir el objeto JSON a una cadena de texto
        return json.toString();
    }

    public static String[] obtenerDatos(String codigoProfesor) {
        List<String> datos = new ArrayList<>();

        // Buscar el profesor con el código dado
        Profesor profesor = null;
        for (Profesor p : profesores) {
            if (p.getCodigo().equals(codigoProfesor)) {
                profesor = p;
                break;
            }
        }

        if (profesor == null) {
            // Si no se encuentra el profesor, devolver un array vacío
            return new String[0];
        }

        // Recorrer los cursos asignados al profesor
        for (Curso curso : profesor.cursos) {
            // Recorrer las clases asociadas al curso
            for (Clase clase : curso.clases) {
                // Extraer los datos de la cadena AulaClase
                String aula = clase.AulaClase.substring(3);
                String piso = clase.AulaClase.substring(1, 3);
                String torre = clase.AulaClase.substring(0, 1);
                String nombreCurso = curso.nombre;
                String horario = clase.HoraInicio + " - " + clase.HoraFinal;

                // Agregar los datos a la lista
                String datosClase = piso +
                        "," + aula +
                        "," + horario +
                        "," + nombreCurso +
                        "," + torre +
                        "," + torre;
                datos.add(datosClase);
            }
        }

        // Convertir la lista de cadenas en un array y devolverlo
        return datos.toArray(new String[0]);
    }



    public static void InitLlenadoData(String linkCsvDocente, String linkCsvAlumno) {
        profesores = crearProfesores(linkCsvDocente);
        estudiantes = crearEstudiantes(linkCsvAlumno);
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
