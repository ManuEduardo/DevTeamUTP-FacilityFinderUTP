package org.source.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constantes {
    private static final Path PathCsvDocente = Paths.get("backend_FacilityFinderUTP", "src", "main", "java", "org", "source", "csvs", "csv_docente.csv").toAbsolutePath();
    private static final Path PathCsvEstudiante = Paths.get("backend_FacilityFinderUTP", "src", "main", "java", "org", "source", "csvs", "csv_estudiante.csv").toAbsolutePath();

    public static String RutaCsvDocente() {
        return PathCsvDocente.toAbsolutePath().toString();
    }

    public static String RutaCsvEstudiante() {
        return PathCsvEstudiante.toAbsolutePath().toString();
    }
}
