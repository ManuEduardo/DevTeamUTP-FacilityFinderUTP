package org.source.utils;

public class Rutas {
    /**
     * Rutas de archivo que representan la ubicación de dos archivos en el proyecto.:
     */

    private static String RutaCsv;
    private static String RutaLog;

    public static String getRutaCsv() {
        return RutaCsv;
    }

    public static void setRutaCsv(String rutaCsv) {
        RutaCsv = rutaCsv;
    }

    public static String getRutaLog() {
        return RutaLog;
    }

    public static void setRutaLog(String rutaLog) {
        RutaLog = rutaLog;
    }
}
