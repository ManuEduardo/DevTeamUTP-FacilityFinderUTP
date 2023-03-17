package org.source.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class LecturaCsv {
    private final String UrlScv;
    final String SEPARADOR = ";";
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
                String[] campos = linea.split(SEPARADOR);
                // En este caso Solo almacenar las variables que usaremos.
                String[] FiltroCampos = new String[]{campos[4], campos[6], campos[28], campos[33], campos[41], campos[42], campos[43], campos[44], campos[45]};
                arrayCsv.add(FiltroCampos);
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
