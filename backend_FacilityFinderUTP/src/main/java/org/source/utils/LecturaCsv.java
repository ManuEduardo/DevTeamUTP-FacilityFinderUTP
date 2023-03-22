package org.source.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta es la descripción de la clase LecturaCsv:
 * Este código es una clase llamada "LecturaCsv" que contiene un método llamado "leer" que lee un
 * archivo CSV y devuelve una lista enlazada que contiene solo los campos de interés. La clase tiene
 * un atributo llamado "UrlScv" que es la URL del archivo CSV que se va a leer. El archivo CSV se
 * lee utilizando un BufferedReader que lee el archivo línea por línea y divide cada línea en campos
 * utilizando un separador ";" (SEPARADOR). Después de leer cada línea, se almacenan solo los campos
 * necesarios (campos[4], campos[6], campos[28], campos[33], campos[41], campos[42], campos[43],
 * campos[44], campos[45]) en un arreglo llamado "FiltroCampos" que se agrega a una lista enlazada
 * llamada "arrayCsv". Finalmente, el método devuelve la lista enlazada que contiene solo los campos
 * de interés. Si hay algún error en la lectura del archivo, se maneja con un bloque try-catch y,
 * finalmente, se cierra el BufferedReader en el bloque finally.
 * @author Manuel Sanchez Suyon
 * @Editado: Gabriel Paiva
 */
    public class LecturaCsv {

        private final String urlCsv;
        private final String SEPARADOR = ";";

        public LecturaCsv(String urlCsv) {
            this.urlCsv = urlCsv;
        }

        /**
         * Función que lee un archivo CSV y devuelve una lista enlazada que contiene sólo los campos de interés.
         *
         * @return una lista enlazada que contiene sólo los campos de interés.
         */
        public List<String[]> leer() {
            List<String[]> arrayCsv = new LinkedList<>();
            try {
                // Abrir el archivo csv en buffer de lectura
                BufferedReader bufferLectura = Files.newBufferedReader(Paths.get(urlCsv), StandardCharsets.UTF_8);

                // Leer una linea del archivo
                String linea = bufferLectura.readLine();

                // La primera linea del csv no es data, son los nombres de las filas del csv. Por ello la pasamos.
                linea = bufferLectura.readLine();

                while (linea != null) {
                    String[] campos = linea.split(SEPARADOR);
                    // En este caso Solo almacenar las variables que usaremos.

                    String[] FiltroCampos = new String[]{campos[4], campos[6], campos[28], campos[33], campos[41], campos[42], campos[43], campos[44], campos[45]};
                    arrayCsv.add(FiltroCampos);
                    // Volver a leer otra línea del fichero
                    linea = bufferLectura.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayCsv;
        }
    }