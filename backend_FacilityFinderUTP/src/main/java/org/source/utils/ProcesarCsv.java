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
        this.filasCsv = filasCsv;
    }

    // Método que crea y devuelve un arreglo de mapas
    public HashMap <String,Object>[] createData() throws IOException {

        HashMap<String, Object>[] globalData = new HashMap[4];

        globalData[0] =  new HashMap<String, Object>();
        globalData[1] =  new HashMap<String, Object>();
        globalData[2] =  new HashMap<String, Object>();
        globalData[3] =  new HashMap<String, Object>();

        int contador = 0;

        for (String[] array : filasCsv) {

            String nombreAlumno = array[0];
            String codigoAlumno = array[1];
            String nombreCurso = array[2];
            String dataAula = array[3];
            String codigoProfesor = array[4];
            String nombreProfesor = array[5];
            String diaClase = array[6];
            String horarioInicio = array[7];
            String horarioFinal = array[8];

            try {
                Validadores.esCodigoValido(codigoAlumno,Clave.ALUMNO);
                Validadores.esCodigoValido(codigoProfesor,Clave.PROFESOR);

                String[] DataAula = separarDataAula(dataAula);

                String sede = DataAula[0];
                String nombreSede = ciudadMapper.obtenerCiudad(Integer.parseInt(sede));

                // Torre o aula Virtual.
                String torreOrAv = DataAula[1];

                String ambiente = DataAula[2];

                Ambiente A = new Ambiente(sede,nombreSede,ambiente,torreOrAv);

                Clase[] clases = new Clase[1];

                clases[0] = new Clase(diaClase,horarioInicio,horarioFinal,A);

                Curso[] cursos = new Curso[1];

                cursos[0] = new Curso(nombreCurso,nombreProfesor,clases);

                Estudiante estudiante = new Estudiante(nombreAlumno,codigoAlumno,cursos);

                Profesor profesor = new Profesor(nombreProfesor,nombreProfesor,cursos);

                globalData[0].put(codigoAlumno, estudiante);
                globalData[1].put(codigoProfesor, profesor);
                globalData[2].put(nombreCurso,cursos[0]);
                globalData[3].put(Integer.toString(contador), clases[0]);
                contador++;

            }catch (Exception ex){
                errorLog.log(ex.getMessage(), ErrorLog.Level.ERROR,nombreLugar);
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