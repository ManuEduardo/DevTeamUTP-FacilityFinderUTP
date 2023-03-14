package org.source.servicios;

import com.google.gson.Gson;
import com.opencsv.exceptions.CsvException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;
import org.source.utils.Constantes;
import org.source.utils.ProcesarCsv;
import org.source.utils.QueryToMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.source.utils.ProcesarCsv.buscarClaseMasCercana;

public class ServicioSalonEstudiante implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Obtener los parámetros de la URL
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = QueryToMap.queryToMap(query);
        String codigo_estudiante = params.get("codigo");

        // Crear un objeto Map para representar los datos que se enviarán como JSON
        // y llama a la función obtenerSalonEstudiante
        Map<String, Object> data = null;
        try {
            data = obtenerSalonEstudiante(codigo_estudiante);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        // Convertir el objeto Map a una cadena JSON utilizando la biblioteca Gson
        Gson gson = new Gson();
        String json = gson.toJson(data);

        // Establecer el tipo de contenido de la respuesta a "application/json"
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Enviar la respuesta al cliente (Falta los casos si es que no encuentra resultados)
        if(data.get("curso")==null){
            exchange.sendResponseHeaders(404, json.getBytes().length);
        }else{
            exchange.sendResponseHeaders(200, json.getBytes().length);
        }
        exchange.getResponseBody().write(json.getBytes());
        exchange.getResponseBody().close();
    }

    private Map<String, Object> obtenerSalonEstudiante(String codigoEstudiante) throws IOException, CsvException {

        //Devuelve todos los valores null si es que hubo algún error

        //AQUI EMPIEZA TU MAGIA
        // Se obtiene los datos del curso, el cual el profesor lo tiene más cercano.
        String[] cursoMasCercano = buscarClaseMasCercana(codigoEstudiante,  Constantes.dataEstudiante());

        String profesor = cursoMasCercano[0];
        String curso = cursoMasCercano[1];
        String pabellon = cursoMasCercano[2].substring(0,1);
        String piso = cursoMasCercano[2].substring(1,3);;
        String aula = cursoMasCercano[2].substring(3);;
        String horario = cursoMasCercano[3];
        String torre = pabellon;

        Map<String, Object> informacionClase = new HashMap<>();
        informacionClase.put("profesor", profesor);
        informacionClase.put("curso", curso);
        informacionClase.put("pabellon", pabellon);
        informacionClase.put("piso", piso);
        informacionClase.put("aula", aula);
        informacionClase.put("horario", horario);
        informacionClase.put("torre", torre);
        return informacionClase;
    }
}
