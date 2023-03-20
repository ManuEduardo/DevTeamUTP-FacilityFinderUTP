package org.source.utils;

import org.source.Validaciones.ErrorLog;
import org.source.Validaciones.Validadores;
import org.source.modelos.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta es la descripción de la clase ProcesarCsv:
 * Esta clase se encarga de procesar un archivo CSV, lo analiza y crea dos estructuras
 * de datos (HashMap) con la información que contiene el archivo.
 * @author Gabriel Paiva
 */

public class ProcesarCsv {

    // Lista enlazada de filas de datos CSV
    private final LinkedList<String[]> filasCsv;

    private final CiudadMapper ciudadMapper = new CiudadMapper();
    ErrorLog errorLog = new ErrorLog();

    // Constructor
    public ProcesarCsv(LinkedList<String[]> filasCsv) {
        this.filasCsv = ordenamientoAlfabetico(filasCsv);
    }

    /**
     * La función recorre un arreglo de filas de un archivo CSV, realiza algunas operaciones y crea
     * una estructura de datos compleja basada en esos datos.
     * @return  Rstructura de datos compleja.
     * - [0] Una estructura que almacena información de los estudiantes.
     * - [1] Una estructura que almacena información de los profesores. */
    public HashMap<String, Object>[] createData() throws IOException {

        // Se crea la data en null, porque posteriormente se utilizará.
        HashMap<String, Object>[] globalData = new HashMap[2];

        globalData[0] = new HashMap<String, Object>();
        globalData[1] = new HashMap<String, Object>();

        String[] valorAnterior = new String[9];

        Arrays.fill(valorAnterior, "");

        Estudiante estudiante = new Estudiante(null,null);
        Profesor profesor = new Profesor(null, null);

        String nombreAlumno = null;
        String codigoAlumno = null;
        String nombreCurso = null;
        String dataAula = null;
        String codigoProfesor = null;
        String nombreProfesor = null;
        String diaClase = null;
        String horarioInicio = null;
        String horarioFinal = null;
        String[] DataAula = new String[0];
        String sede = null;
        String nombreSede = null;
        String torreOrAv = null;
        String ambiente = null;
        Clase clases = new Clase(null,null,null);
        Curso curso = new Curso(null,null);

        // Se recorre todas las filasCsv.
        for (String[] array : filasCsv) {
            try {

                //Verificamos que la data a leer no sea repetida.
                Validadores.esDataRepetida(valorAnterior, array);

                // Creamos las principales variables que utilizaremos al instanciar los datos.
                nombreAlumno = array[0];
                codigoAlumno = array[1];
                nombreCurso = array[2];
                dataAula = array[3];
                codigoProfesor = array[4];
                nombreProfesor = array[5];
                diaClase = array[6];
                horarioInicio = array[7];
                horarioFinal = array[8];

                //Validamos si hay un codigo valido ingresado o no.
                Validadores.esCodigoValido(codigoAlumno, Clave.ALUMNO);
                Validadores.esCodigoValido(codigoProfesor, Clave.PROFESOR);

                // Em estos validadores lo que se hará es que confirmar en caso los
                // datos procesados en un array anterior correspondan al mismo alumno,
                // curso, etc. En estos casos a veces se creará un nuevo profesor o un nuevo
                // curso, dependiendo si pasa los validadores, lo mismo pasa con las clases.
                // Ello gracias al array generado llamado "valoranterior", el cual , en caso esté
                // vació entonces se generarán los datos como si fueran nuevos.

                if (valorAnterior[0].equals("") ||
                        !valorAnterior[0].equals(nombreAlumno) || !valorAnterior[1].equals(codigoAlumno)) {

                    if (!valorAnterior[0].equals("")) {
                        globalData[0].put(valorAnterior[1], estudiante);
                    }
                    estudiante.aEliminarCursosDuplicados();
                    estudiante = new Estudiante(nombreAlumno, codigoAlumno);
                }

                if (valorAnterior[0].equals("") ||
                        !valorAnterior[5].equals(nombreProfesor) || !valorAnterior[4].equals(codigoProfesor)) {

                    if (!valorAnterior[0].equals("")) {
                        if (globalData[1].containsKey(codigoProfesor)) {
                            Profesor profesors = (Profesor) globalData[1].get(codigoProfesor);
                            profesors.setAgregarNuevosCursos(profesor.getCursos());
                            profesors.aEliminarCursosDuplicados();
                            globalData[1].put(valorAnterior[4], profesors);
                        } else {
                            profesor.aEliminarCursosDuplicados();
                            globalData[1].put(valorAnterior[4], profesor);
                        }
                    }

                    profesor = new Profesor(nombreAlumno, codigoAlumno);
                }

                DataAula = separarDataAula(dataAula);

                sede = DataAula[0];
                nombreSede = ciudadMapper.obtenerCiudad(Integer.parseInt(sede));
                torreOrAv = DataAula[1];
                ambiente = DataAula[2];

                Ambiente A = new Ambiente(sede, nombreSede, ambiente, torreOrAv);

                clases = new Clase(diaClase, horarioInicio, horarioFinal, A);

                if (valorAnterior[2].equals("")) {
                    curso = new Curso(nombreCurso, nombreProfesor);
                    clases = new Clase(diaClase, horarioInicio, horarioFinal, A);
                } else {

                    if (codigoAlumno.equals(valorAnterior[2])) {
                        if (!(nombreCurso.equals(valorAnterior[2]))) {
                            curso = new Curso(nombreCurso, nombreProfesor);
                        }

                        if (!diaClase.equals(valorAnterior[6]) || !horarioInicio.equals(valorAnterior[7])) {
                            clases = new Clase(diaClase, horarioInicio, horarioFinal, A);
                        }
                    } else {
                        curso = new Curso(nombreCurso, nombreProfesor);
                        clases = new Clase(diaClase, horarioInicio, horarioFinal, A);
                    }

                }

                curso.setAgregarClase(clases);
                estudiante.setAgregarCurso(curso);
                profesor.setAgregarCurso(curso);

            } catch (Exception ex) {
                if (!valorAnterior[0].equals("")) {
                    String nombreLugar = "ProcesarCsv";
                    errorLog.log(ex.getMessage(), ErrorLog.Level.ERROR, nombreLugar);
                }
            }

            // guardar el valor actual como valor anterior para la siguiente iteración
            valorAnterior = array;

        }

        estudiante.aEliminarCursosDuplicados();
        globalData[0].put(codigoAlumno, estudiante);

        if (!valorAnterior[5].equals(nombreProfesor) || !valorAnterior[4].equals(codigoProfesor)){
            globalData[1].put(codigoProfesor, profesor);
        }else {
            if (globalData[1].containsKey(codigoProfesor)) {
                Profesor profesors = (Profesor) globalData[1].get(codigoProfesor);
                profesors.setAgregarNuevosCursos(profesor.getCursos());

                profesors.aEliminarCursosDuplicados();
                globalData[1].put(codigoProfesor,profesors);
            } else {
                profesor.aEliminarCursosDuplicados();
                globalData[1].put(codigoProfesor, profesor);
            }
        }

        return globalData;
    }

    /**
     Esta función toma como entrada un código de aula en formato String y lo separa en tres partes: los números
     que aparecen al inicio, las letras que siguen y los números que aparecen al final, devolviendo un arreglo
     con estas tres partes. Si el código de aula no cumple con un patrón válido, la función lanzará una excepción.
     @param codigoAula El código de aula que se desea separar en tres partes.
     @return Un arreglo de tres elementos que contiene las tres partes del código de aula.
     @throws IllegalArgumentException Si el código de aula no cumple con un patrón válido. */
    static String[] separarDataAula(String codigoAula) {

        String[] partes = new String[3];

        // Expresión regular para separar en 3 partes: números, letras, números
        Pattern patron = Pattern.compile("(\\d+)([A-Za-z]+)(\\d+)");
        Matcher matcher = patron.matcher(codigoAula);
        if (matcher.matches()) {
            partes[0] = matcher.group(1); // Primera parte: números
            partes[1] = matcher.group(2); // Segunda parte: letras
            partes[2] = matcher.group(3); // Tercera parte: números después de las letras
        } else {
            // Si no se encuentra un patrón válido, lanzamos una excepción
            throw new IllegalArgumentException("El código de aula es inválido");
        }

        return partes;
    }

    /**
     Esta función ordena alfabéticamente una LinkedList de nombres con base en los apellidos,
     nombres y una tercera posición. El método utiliza un comparador anónimo para
     definir el criterio de ordenamiento por apellido, nombre y tercera posición en caso de empate.
     @param nombres  El parámetro de entrada es una LinkedList de arrays de String,
     donde cada array contiene el nombre completo y la tercera posición.
     @return  Devuelve la LinkedList ordenado. */
    public static LinkedList<String[]> ordenamientoAlfabetico(LinkedList<String[]> nombres) {

        // Definir el comparador para ordenar por apellido y en caso de empate, por nombre,
        // y en caso de otro empate, por la tercera posición
        Comparator<String[]> ordenamientoAlfabetico = new Comparator<String[]>() {
            @Override
            public int compare(String[] nombre1, String[] nombre2) {
                String[] apellidoNombre1 = nombre1[0].split(",");
                String[] apellidoNombre2 = nombre2[0].split(",");
                int comparacionApellido = apellidoNombre1[0].compareTo(apellidoNombre2[0]);
                if (comparacionApellido == 0) {
                    int comparacionNombre = apellidoNombre1[1].compareTo(apellidoNombre2[1]);
                    if (comparacionNombre == 0) {
                        return nombre1[2].compareTo(nombre2[2]);
                    }
                    return comparacionNombre;
                }
                return comparacionApellido;
            }
        };

        // Ordenar el LinkedList por apellido y en caso de empate, por nombre,
        // y en caso de otro empate, por la tercera posición
        Collections.sort(nombres, ordenamientoAlfabetico);

        // Devolver el LinkedList ordenado
        return nombres;
    }
}

