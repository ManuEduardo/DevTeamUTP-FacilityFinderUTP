package org.source.servicios;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.source.utils.QueryToMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServicioSalonProfesor implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Obtener los parámetros de la URL
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = QueryToMap.queryToMap(query);
        String codigo_profesor = params.get("codigo");

        // Crear un objeto Map para representar los datos que se enviarán como JSON
        Map<String, Object> data = obtenerSalonProfesor(codigo_profesor);

        // Convertir el objeto Map a una cadena JSON utilizando la biblioteca Gson
        Gson gson = new Gson();
        String json = gson.toJson(data);

        // Establecer el tipo de contenido de la respuesta a "application/json"
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        // Enviar la respuesta al cliente
        exchange.sendResponseHeaders(200, json.getBytes().length);
        exchange.getResponseBody().write(json.getBytes());
        exchange.getResponseBody().close();
    }

    private Map<String, Object> obtenerSalonProfesor(String codigo){
        Map<String, Object> informacionClase = new HashMap<>();
        informacionClase.put("codigo", codigo);
        return informacionClase;
    }
}
