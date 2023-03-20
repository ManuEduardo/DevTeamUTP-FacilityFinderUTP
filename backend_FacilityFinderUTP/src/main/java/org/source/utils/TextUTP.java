package org.source.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta es la descripción de la clase TextUTP:
 * Esta es una clase de Java llamada "TextUTP", que proporciona una serie de métodos estáticos
 * para leer y escribir texto en archivos. La clase también define un enumerador llamado "OS"
 * que representa el sistema operativo en el que se está ejecutando el programa y se utiliza
 * en algunos métodos para determinar la forma en que se deben procesar los caracteres de
 * fin de línea.
 *
 * La clase incluye los siguientes métodos:
 *
 * @read (String filename): Este método toma un nombre de archivo como parámetro y devuelve
 * una cadena que contiene el contenido del archivo.
 *
 * @readlines (String filename, OS os): Este método toma un nombre de archivo y un objeto de tipo
 * "OS" como parámetros y devuelve una lista de cadenas, una para cada línea del archivo.
 * El parámetro "os" se utiliza para determinar la forma en que se deben procesar los caracteres
 * de fin de línea.
 *
 * @readlines (String filename): Este método es similar al anterior, pero utiliza el valor por
 * defecto "OS.LINUX" para el parámetro "os".
 *
 * @readlinesAsArray (String filename, OS os): Este método es similar a readlines(), pero devuelve
 * un arreglo de cadenas en lugar de una lista.
 *
 * @readlinesAsArray (String filename): Este método es similar al anterior, pero utiliza el
 * valor por defecto "OS.LINUX" para el parámetro "os".
 *
 * @append (String data, String filename): Este método toma una cadena de texto y un nombre de archivo
 * como parámetros y añade el contenido de la cadena al final del archivo.
 *
 * @append (String[] data, String filename, boolean withNewLine, OS os): Este método toma
 * un arreglo de cadenas, un nombre de archivo, un indicador booleano que indica si se debe agregar un
 * carácter de fin de línea después de cada cadena y un objeto de tipo "OS" como parámetros. El método
 * concatena todas las cadenas del arreglo en una sola cadena, separando cada cadena con un carácter
 * de fin de línea según la plataforma del sistema operativo.
 *
 * @append (String[] data, String filename, boolean withNewLine): Este método es similar al anterior,
 * pero utiliza el valor por defecto "OS.LINUX" para el parámetro "os".
 *
 * @append (String[] data, String filename): Este método es similar al anterior, pero agrega un
 * carácter de fin de línea después de cada cadena y utiliza el valor por defecto "OS.LINUX" para
 * el parámetro "os".
 *
 * @append (List<String> data, String filename, boolean withNewLine, OS os): Este método es similar al
 * método anterior, pero toma una lista de cadenas en lugar de un arreglo.
 *
 * @append (List<String> data, String filename, boolean withNewLine): Este método es similar al anterior,
 * pero utiliza el valor por defecto "OS.LINUX" para el parámetro "os".
 *
 * @append (List<String> data, String filename): Este método es similar al anterior, pero agrega un
 * carácter de fin de línea después de cada cadena y utiliza el valor por defecto "OS.LINUX" para el
 * parámetro "os".
 *
 * En resumen, esta clase proporciona una serie de métodos estáticos para leer y escribir texto en
 * archivos, con opciones para especificar la forma en que se procesan los caracteres de fin de línea
 * según el sistema operativo.
 */

public class TextUTP {

    public enum OS {WINDOWS, LINUX};

    public static String read(String filename) throws IOException {
        try(BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(filename))
        ){
            String data = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            return data;
        } catch (IOException e) {
            throw e;
        }
    }

    public static List<String> readlines(String filename, OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        String data = read(filename);
        List<String> res = new LinkedList<>();
        if (data.length() > 0){
            res = Arrays.asList(data.split(delim));
        }
        return res;
    }

    public static List<String> readlines(String filename) throws IOException {
        return readlines(filename, OS.LINUX);
    }

    public static String[] readlinesAsArray(String filename, OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        String data = read(filename);
        String[] res = new String[]{};
        if (data.length() > 0){
            res = data.split(delim);
        }
        return res;
    }

    public static String[] readlinesAsArray(String filename) throws IOException {
        return readlinesAsArray(filename, OS.LINUX);
    }

    private static void writeText(byte[] data, String filename)
            throws IOException{
        try(BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(filename,true))
        ){
            out.write(data);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void append(String data, String filename) throws IOException {
        writeText(data.getBytes(), filename);
    }

    public static void append(String[] data, String filename, boolean withNewLine, OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            if (withNewLine == true) {
                sb.append(item + delim);
            }else{
                sb.append(item);
            }
        }
        writeText(sb.toString().getBytes(), filename);
    }

    public static void append(String[] data, String filename, boolean withNewLine) throws IOException {
        append(data, filename, withNewLine, OS.LINUX);
    }

    public static void append(String[] data, String filename) throws IOException {
        append(data, filename, true);
    }

    public static void append(List<String> data, String filename, boolean withNewLine, OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            if (withNewLine == true) {
                sb.append(item + delim);
            }else{
                sb.append(item);
            }
        }
        writeText(sb.toString().getBytes(), filename);
    }

    public static void append(List<String> data, String filename, boolean withNewLine) throws IOException {
        append(data, filename, withNewLine, OS.LINUX);
    }

    public static void append(List<String> data, String filename) throws IOException {
        append(data, filename, true);
    }

}
