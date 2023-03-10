package org.source.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class LecturaCsv {
    private final String UrlScv;
    final String SEPARADOR = ",";
    BufferedReader bufferLectura = null;

    public LecturaCsv(String urlScv) {
        this.UrlScv = urlScv;
    }

    //La función usa el csv pasado por parametro para luego devoler un linked list de String[]
    public LinkedList<String[]> leer() {
        LinkedList<String[]> arrayCsv = new LinkedList<>();
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(UrlScv));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(SEPARADOR);
                arrayCsv.add(campos);
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayCsv;
    }

}
