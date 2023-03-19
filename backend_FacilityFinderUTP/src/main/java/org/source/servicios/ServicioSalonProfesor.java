package org.source.servicios;

import com.google.gson.Gson;
import com.opencsv.exceptions.CsvException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.source.utils.Clave;
import org.source.utils.QueryToMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.source.utils.FuncionalidadServicios.claseMasCercana;

/**
 * Esta es la descripción de la clase ServicioSalonProfesor:
 * @author Manuel Sanchez Suyon
 * @Editado: Gabriel Paiva
 */


public class ServicioSalonProfesor implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Obtener los parámetros de la URL
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = QueryToMap.queryToMap(query);
        String codigo_profesor = params.get("codigo");

        // Crear un objeto Map para representar los datos que se enviarán como JSON
        Map<String, Object> data = null;
        try {
            data = obtenerSalonProfesor(codigo_profesor);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

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

    private Map<String, Object> obtenerSalonProfesor(String codigoProfesor) throws IOException, CsvException {

        Map<String, Object> informacionClase = new HashMap<>();

        // Se obtiene los datos del curso, el cual el profesor lo tiene más cercano.
        String[] cursoMasCercano = claseMasCercana(codigoProfesor, Clave.PROFESOR);

        String profesor = cursoMasCercano[0];
        String curso = cursoMasCercano[1];
        String sede = cursoMasCercano[2];
        String pabellon = cursoMasCercano[3];
        String piso = cursoMasCercano[4];
        String aula = cursoMasCercano[5];
        String horario = cursoMasCercano[6];
        String torre = pabellon;
        String DiaSemana = cursoMasCercano[8];

        informacionClase.put("profesor", profesor);
        informacionClase.put("curso", curso);
        informacionClase.put("pabellon", pabellon);
        informacionClase.put("piso", piso);
        informacionClase.put("aula", aula);
        informacionClase.put("horario", horario);
        informacionClase.put("torre", torre);
        return informacionClase;
    }

    public static void main(String[] args) throws IOException {
        //Prueba
        String[] cursoMasCercano = claseMasCercana("C21583", Clave.PROFESOR);

        String profesor = cursoMasCercano[0];
        String curso = cursoMasCercano[1];
        String sede = cursoMasCercano[2];
        String pabellon = cursoMasCercano[3];
        String piso = cursoMasCercano[4];
        String aula = cursoMasCercano[5];
        String horario = cursoMasCercano[6];
        String torre = pabellon;
        String DiaSemana = cursoMasCercano[8];

        System.out.println("profesor = " + profesor);
        System.out.println("curso = " + curso);
        System.out.println("sede = " + sede);
        System.out.println("pabellon = " + pabellon);
        System.out.println("piso = " + piso);
        System.out.println("aula = " + aula);
        System.out.println("horario = " + horario);
        System.out.println("torre = " + torre);
        System.out.println("DiaSemana = " + DiaSemana);
    }

}
