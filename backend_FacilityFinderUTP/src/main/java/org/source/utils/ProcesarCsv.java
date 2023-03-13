package org.source.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.source.modelos.Curso;
import org.source.modelos.Profesor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static Profesor[] leerCsvProfesor(String ruta) throws IOException, CsvException {
        List<String[]> filas = leerCsv(ruta);
        Profesor[] maestros = new Profesor[filas.size()-1];

        // La primera fila contiene los nombres de las columnas, así que la ignoramos
        for (int i = 1; i < filas.size(); i++) {
            String[] datos = filas.get(i);
            String nombreCompleto = datos[0];
            String codigoMaestro = datos[1];

            // Los cursos del maestro empiezan en la columna 2
            int j = 2;
            List<Curso> cursos = new ArrayList<>();
            while (j < datos.length) {
                String nombreCurso = datos[j];
                String codigoAula = datos[j+1];
                String diaSemana = datos[j+2];
                String horaInicio = datos[j+3];
                String horaFinal = datos[j+4];

                // Creamos un objeto Curso con los datos correspondientes
                Curso curso = new Curso(nombreCurso, codigoAula, diaSemana, horaInicio, horaFinal);
                cursos.add(curso);

                j += 5;
            }

            // Creamos un objeto Maestro con los datos correspondientes
            Profesor maestro = new Profesor(nombreCompleto, codigoMaestro, cursos.toArray(new Curso[cursos.size()]));
            maestros[i-1] = maestro;
        }

        return maestros;
    }



}
