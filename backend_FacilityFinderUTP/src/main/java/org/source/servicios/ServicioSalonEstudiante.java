package org.source.servicios;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.source.utils.LecturaCsv;
import org.source.utils.QueryToMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServicioSalonEstudiante implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Obtener los parámetros de la URL
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = QueryToMap.queryToMap(query);
        String codigo_estudiante = params.get("codigo");

        // Crear un objeto Map para representar los datos que se enviarán como JSON
        // y llama a la función obtenerSalonEstudiante
        Map<String, Object> data = obtenerSalonEstudiante(codigo_estudiante);

        // Convertir el objeto Map a una cadena JSON utilizando la biblioteca Gson
        Gson gson = new Gson();
        String json = gson.toJson(data);

        // Establecer el tipo de contenido de la respuesta a "application/json"
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        // Enviar la respuesta al cliente (Falta los casos si es que no encuentra resultados)
        if(data.get("curso")==null){
            exchange.sendResponseHeaders(404, json.getBytes().length);
        }else{
            exchange.sendResponseHeaders(200, json.getBytes().length);
        }
        exchange.getResponseBody().write(json.getBytes());
        exchange.getResponseBody().close();
    }

    private Map<String, Object> obtenerSalonEstudiante(String codigo){
        //Devuelve todos los valores null si es que hubo algún error

        //AQUI EMPIEZA TU MAGIA

        String LinkDataDocentes = LecturaCsv.getRutaCsvDocentes();
        String LinkDataAlumnos = LecturaCsv.getRutaCsvAlumnos();



        String profesor = "Pedro";
        String curso = "Programación Orientada a Objetos";
        String torre = "A";
        String pabellon = "A";
        String piso = "06";
        String aula = "04";
        String horario = "10:30 - 13:30";
        //AQUI TERMINA TU MAGIA

        Map<String, Object> informacionClase = new HashMap<>();
        informacionClase.put("profesor", profesor);
        informacionClase.put("curso", curso);
        informacionClase.put("torre", torre);
        informacionClase.put("pabellon", pabellon);
        informacionClase.put("piso", piso);
        informacionClase.put("aula", aula);
        informacionClase.put("horario", horario);

        return informacionClase;
    }
}
