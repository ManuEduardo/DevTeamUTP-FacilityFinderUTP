package org.source;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class TestServ {

    private static final int PORT = 8000;

    public static void main(String[] args) throws Exception {

        // Crear un objeto servidor HTTP en el puerto 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        // Adjuntar un controlador para la ruta "/test"
        server.createContext("/test", new MyHandler());
        server.createContext("/", new MyHandlerTwo());
        // Iniciar el servidor
        server.start();
    }

    // Clase que implementa el controlador HTTP
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Obtener el método de la petición (GET o POST)
            String method = t.getRequestMethod();
            // Si es GET, responder con un mensaje de "Hola mundo"
            if (method.equals("GET")) {
                // Crear una respuesta con código 200 (OK) y longitud del mensaje
                String response = "Hola mundo";
                t.sendResponseHeaders(200, response.length());
                // Obtener el stream de salida para escribir la respuesta
                OutputStream os = t.getResponseBody();
                // Escribir la respuesta y cerrar el stream
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    // Clase interna que implementa el manejador de solicitudes
    static class MyHandlerTwo implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Obtiene la URI de la solicitud
            String uri = t.getRequestURI().toString();
            // Obtiene el método de la solicitud (debe ser "GET")
            String method = t.getRequestMethod();
            // Obtiene los encabezados de la respuesta
            Headers headers = t.getResponseHeaders();
            // Agrega un encabezado para indicar el tipo de contenido
            headers.add("Content-Type", "text/plain");
            // Crea una cadena con la información a mostrar
            String response = "URI: " + uri + "\n";
            response += "Method: " + method + "\n";
            response += "Headers: " + headers.toString() + "\n";

            // Establece el código de estado y la longitud del contenido
            t.sendResponseHeaders(200, response.length());
            // Obtiene el cuerpo de la respuesta como una secuencia de salida
            OutputStream os = t.getResponseBody();
            // Escribe la cadena en la secuencia de salida
            os.write(response.getBytes());
            // Cierra la secuencia de salida y el objeto HttpExchange
            os.close();
            t.close();
        }
    }
}