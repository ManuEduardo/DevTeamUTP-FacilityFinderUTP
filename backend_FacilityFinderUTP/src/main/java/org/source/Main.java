package org.source;

import com.sun.net.httpserver.HttpServer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.source.servicios.ServicioSalonEstudiante;
import org.source.servicios.ServicioSalonProfesor;
import org.source.utils.Clave;
import org.source.utils.Constantes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;

import static org.source.utils.FuncionalidadServicios.claseMasCercana;

public class Main {
    private static int PORT;
    private static Options options;
    private static CommandLine cmd;
    private static String directorioRaiz;

    private static String separador = System.getProperty("file.separator");

    private static String directorioRecursos = null;

    public static String getDirectorioRaiz() {
        return directorioRaiz;
    }

    public static String getSeparador() {
        return separador;
    }

    public static String getDirectorioRecursos() {
        return directorioRecursos;
    }

    private static void crearOpciones() throws Exception {
        //Generando Opciones de CMD
        options = new Options();
        options.addOption("h", "help", false, "Muestra ayuda");
        options.addOption("r", "resources", true, "Ingresa el Directorio de Recursos");
        options.addOption("p", "port", true, "Especifica el puerto para ejecutar.");
    }

    private static String obtenerDirectorioRecursos(String[] args) throws Exception {

        try {
            cmd = new DefaultParser().parse(options, args);
            if (cmd.hasOption("r")) {
                directorioRecursos = cmd.getOptionValue("r");
            }
        } catch (Exception e) {
            String msg = "Ruta de Directorio de Recursos No ingresada";
            throw new Exception(msg);
        }finally {
            return directorioRecursos;
        }

    }

    private static int obtenerPuerto(String[] args)throws Exception{
        int puerto = 8000;

        try {
            cmd = new DefaultParser().parse(options, args);
            if (cmd.hasOption("p")) {
                puerto = Integer.parseInt(cmd.getOptionValue("p"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            return puerto;
        }

    }

    public static void main(String[] args) throws IOException {

        try {
            crearOpciones();
            directorioRaiz = obtenerDirectorioRecursos(args);
            PORT = obtenerPuerto(args);
            // Crear un objeto servidor HTTP en el puerto 8000
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            // Adjuntar un controlador para la ruta "/test"
            server.createContext("/estudiante", new ServicioSalonEstudiante());
            server.createContext("/profesor", new ServicioSalonProfesor());
            // Iniciar el servidor
            server.start();
            System.out.println("Servidor iniciado en el puerto 8000...");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}