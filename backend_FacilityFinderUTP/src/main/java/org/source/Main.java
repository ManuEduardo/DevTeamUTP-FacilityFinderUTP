package org.source;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.source.servicios.ServicioSalonEstudiante;
import org.source.servicios.ServicioSalonProfesor;
import org.source.utils.Constantes;
import org.source.utils.Rutas;

public class Main {
    private static final int PORT = 8000;

    public static void main(String[] args) throws IOException {

        String csvFilePath1 = args[0];
        String csvFilePath2 = args[1];

        Rutas.setRutaCsv(csvFilePath1);
        Rutas.setRutaLog(csvFilePath2);

        // Crear un objeto servidor HTTP en el puerto 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        // Adjuntar un controlador para la ruta "/test"
        server.createContext("/estudiante", new ServicioSalonEstudiante());
        server.createContext("/profesor", new ServicioSalonProfesor());
        // Iniciar el servidor
        server.start();
        System.out.println("Servidor iniciado en el puerto 8000...");
    }
}