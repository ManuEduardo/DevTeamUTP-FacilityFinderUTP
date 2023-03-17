package org.source.utils;

import com.opencsv.exceptions.CsvException;
import org.source.modelos.Clase;
import org.source.modelos.Curso;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProcesarCsv {

    // Lista enlazada de filas de datos CSV
    private final LinkedList<String[]> filasCsv;

    // Constructor
    public ProcesarCsv(LinkedList<String[]> filasCsv) {
        this.filasCsv = filasCsv;
    }

    // Método que crea y devuelve un arreglo de mapas
    public HashMap <String,LinkedList <Profesor[]>>[] createData() {

        HashMap <String,LinkedList <Profesor[]>>[] globalData = new HashMap[4];

        return globalData;
    }

}