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

}
