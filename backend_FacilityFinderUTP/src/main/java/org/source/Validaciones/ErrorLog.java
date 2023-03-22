package org.source.Validaciones;

import org.source.utils.Constantes;
import org.source.utils.TextUTP;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La clase ErrorLog se utiliza para registrar y guardar información sobre errores que puedan ocurrir en el
 * programa en un archivo llamado error.log. Esta clase tiene un constructor sin parámetros y un método
 * público llamado log que recibe un mensaje de error, el nivel del error y el lugar donde ocurrió el error.
 * El método log devuelve un string formateado que contiene la información del error, el cual se guarda
 * en el archivo error.log usando la clase TextUTP.
 */

public class ErrorLog {

    /** Constantes. */
    public enum Level {INFO, WARN, ERROR}; // Creamos un enum para especificar el típo de error.
    private final String filename = Constantes.RutaErrorLog(); // Creamos la clase privada String filename.

    /** Constructor. */
    public ErrorLog() {
    } // "log" es una clase pública que recibirá un mensaje, el nivel del error y su lugar devolviendonos un string evento que a su vez se guardará en el error.log.

    /**
     Registra un evento en un archivo de registro y retorna el evento registrado.
     @param msg El mensaje del evento a registrar.
     @param level El nivel del evento (INFO, WARNING, ERROR).
     @param Lugar El lugar donde se produjo el evento.
     @return El evento registrado en el archivo de registro.
     @throws IOException Si ocurre un error al escribir en el archivo de registro.
     */

    public String log(String msg, Level level, String Lugar) throws IOException {

        //FORMATO: FECHA HORA - LEVEL - MENSAJE
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter ftm = DateTimeFormatter.ofPattern("yyyy-MM-dd hh::mm::ss");
        String tiempo = ldt.format(ftm);

        //Aplicando el formato para el evento
                    //Tiempo - Nivel - Lugar del error - Mensaje
        String evento_ftm = "%s - %s - %s - %s\r\n";
        String evento = String.format(evento_ftm,tiempo,level,Lugar,msg);

        //Guardamos el log

        TextUTP.append(evento,filename);

        return evento;
    }
}
