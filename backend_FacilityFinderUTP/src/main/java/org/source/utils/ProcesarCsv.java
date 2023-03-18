package org.source.utils;

import org.source.Validaciones.ErrorLog;
import org.source.Validaciones.Validadores;
import org.source.modelos.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesarCsv {

    // Lista enlazada de filas de datos CSV
    private final LinkedList<String[]> filasCsv;

    private String nombreLugar = "ProcesarCsv";
    private final CiudadMapper ciudadMapper = new CiudadMapper();
    ErrorLog errorLog = new ErrorLog();

    // Constructor
    public ProcesarCsv(LinkedList<String[]> filasCsv) {
        this.filasCsv = ordenamientoAlfabetico(filasCsv);
    }

    // Método que crea y devuelve un arreglo de mapas
    public HashMap<String, Object>[] createData() throws IOException {

        HashMap<String, Object>[] globalData = new HashMap[2];

        globalData[0] = new HashMap<String, Object>();
        globalData[1] = new HashMap<String, Object>();

        int contador = 0;
        String[] valorAnterior = null;

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

        for (int i = 0; i < filasCsv.size(); i++) {
            String[] array = filasCsv.get(i);

            try {

                Validadores.esDataRepetida(valorAnterior,array);

                nombreAlumno = array[0];
                codigoAlumno = array[1];
                nombreCurso = array[2];
                dataAula = array[3];
                codigoProfesor = array[4];
                nombreProfesor = array[5];
                diaClase = array[6];
                horarioInicio = array[7];
                horarioFinal = array[8];

                Validadores.esCodigoValido(codigoAlumno, Clave.ALUMNO);
                Validadores.esCodigoValido(codigoProfesor, Clave.PROFESOR);

                if (valorAnterior == null ||
                        !valorAnterior[0].equals(nombreAlumno) || !valorAnterior[1].equals(codigoAlumno)) {

                    if (valorAnterior != null){
                        globalData[0].put(codigoAlumno, estudiante);
                    }
                    estudiante.oneCurse();
                    estudiante = new Estudiante(nombreAlumno, codigoAlumno);
                }

                if (valorAnterior == null ||
                        !valorAnterior[5].equals(nombreProfesor) || !valorAnterior[4].equals(codigoProfesor)) {

                    if (valorAnterior != null){
                        if (globalData[1].containsKey(codigoProfesor)) {
                            // La clave ya existe en el mapa
                            // Agrega aquí el código que quieres ejecutar cuando se detecte la clave repetida
                            Profesor profesors = (Profesor) globalData[1].get(codigoProfesor);
                            profesors.agregarCursos(profesor.getCursos());
                            profesors.OneCurse();
                            globalData[1].put(codigoProfesor,profesors);
                        } else {
                            profesor.OneCurse();
                            globalData[1].put(codigoProfesor, profesor);
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

                if (!(nombreCurso.equals(valorAnterior[2]))){
                    curso = new Curso(nombreCurso, nombreProfesor);
                }

                if (nombreCurso.equals(valorAnterior[2])){
                    clases = new Clase(diaClase, horarioInicio, horarioFinal, A);
                }

                curso.agregarClase(clases);
                estudiante.agregarCurso(curso);
                profesor.agregarCurso(curso);

            } catch (Exception ex) {
                errorLog.log(ex.getMessage(), ErrorLog.Level.ERROR, nombreLugar);
            }

            // guardar el valor actual como valor anterior para la siguiente iteración
            valorAnterior = array;

        }

        estudiante.oneCurse();
        globalData[0].put(codigoAlumno, estudiante);

        if (!valorAnterior[5].equals(nombreProfesor) || !valorAnterior[4].equals(codigoProfesor)){
            globalData[1].put(codigoProfesor, profesor);
        }else {
            if (globalData[1].containsKey(codigoProfesor)) {
                // La clave ya existe en el mapa
                // Agrega aquí el código que quieres ejecutar cuando se detecte la clave repetida
                Profesor profesors = (Profesor) globalData[1].get(codigoProfesor);
                profesors.agregarCursos(profesor.getCursos());

                profesors.OneCurse();
                globalData[1].put(codigoProfesor,profesors);
            } else {
                profesor.OneCurse();
                globalData[1].put(codigoProfesor, profesor);
            }
        }

        return globalData;
    }

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
    class CiudadMapper {
    private Map<Integer, String> ciudades;

    public CiudadMapper() {
        ciudades = new HashMap<>();
        ciudades.put(45, "Chiclayo");
        // Puedes agregar más ciudades a medida que sea necesario
    }

    public String obtenerCiudad(int valor) {
        return ciudades.getOrDefault(valor, "Ciudad desconocida");
    }
}

class SignificadoAorAV {
    private Map<String, String> AorAV;

    public SignificadoAorAV() {
        AorAV = new HashMap<>();
        AorAV.put("A", "Ambiente");
        AorAV.put("AV", "Ambiente Virtual");
    }

    public String obtenerSignificado(int valor) {
        return AorAV.getOrDefault(valor, null);
    }
}