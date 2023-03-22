package org.source.servicios;

import com.google.gson.Gson;
import com.opencsv.exceptions.CsvException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.source.utils.Clave;
import org.source.utils.QueryToMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.source.utils.FuncionalidadServicios.claseMasCercana;

/**
 * Esta es la descripción de la clase ServicioSalonEstudiante:
 * @author Manuel Sanchez Suyon
 * @Editado: Gabriel Paiva
 */


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
        exchange.getResponseBody().write(json.getBytes(StandardCharsets.UTF_8));
        exchange.getResponseBody().close();
    }

    private Map<String, Object> obtenerSalonEstudiante(String codigoEstudiante) throws IOException, CsvException {

        //Devuelve todos los valores null si es que hubo algún error

        // Se obtiene los datos del curso, el cual el profesor lo tiene más cercano.
        String[] cursoMasCercano = new String[9];
        try {
            cursoMasCercano = claseMasCercana(codigoEstudiante, Clave.ALUMNO);
        } catch (NullPointerException e) {
            Arrays.fill(cursoMasCercano, "no encontrado");
        }
        // Para un estudiante también se muestra el nombre del estudiante
        String estudiante = cursoMasCercano[0];
        String profesor = cursoMasCercano[1];
        String curso = cursoMasCercano[2];
        String sede = cursoMasCercano[3];
        String pabellon = cursoMasCercano[4];
        String piso = cursoMasCercano[5];
        String aula = cursoMasCercano[6];
        String horario = cursoMasCercano[7];
        String torre = pabellon;
        String DiaSemana = cursoMasCercano[9];


        Map<String, Object> informacionClase = new HashMap<>();
        informacionClase.put("estudiante", estudiante);
        informacionClase.put("profesor", profesor);
        informacionClase.put("curso", curso);
        informacionClase.put("sede",sede);
        informacionClase.put("pabellon", pabellon);
        informacionClase.put("piso", piso);
        informacionClase.put("aula", aula);
        informacionClase.put("horario", horario);
        informacionClase.put("torre", torre);
        informacionClase.put("dia", DiaSemana);
        return informacionClase;
    }
/*
    public static void main(String[] args) throws IOException {
        //Prueba
        String[] cursoMasCercano = claseMasCercana("U20303242", Clave.ALUMNO);

        // Para un estudiante también se muestra el nombre del estudiante
        String estudiante = cursoMasCercano[0];
        String profesor = cursoMasCercano[1];
        String curso = cursoMasCercano[2];
        String sede = cursoMasCercano[3];
        String pabellon = cursoMasCercano[4];
        String piso = cursoMasCercano[5];
        String aula = cursoMasCercano[6];
        String horario = cursoMasCercano[7];
        String torre = pabellon;
        String DiaSemana = cursoMasCercano[9];

        System.out.println("estudiante = " + estudiante);
        System.out.println("profesor = " + profesor);
        System.out.println("curso = " + curso);
        System.out.println("sede = " + sede);
        System.out.println("pabellon = " + pabellon);
        System.out.println("piso = " + piso);
        System.out.println("aula = " + aula);
        System.out.println("horario = " + horario);
        System.out.println("torre = " + torre);
        System.out.println("DiaSemana = " + DiaSemana);
    }*/
}
