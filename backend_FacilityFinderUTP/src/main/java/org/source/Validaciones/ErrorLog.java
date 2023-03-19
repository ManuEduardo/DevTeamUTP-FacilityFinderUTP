package org.source.Validaciones;

import org.source.utils.Constantes;
import org.source.utils.TextUTP;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLog {
    // Creamos un enum para especificar el típo de error.
    public enum Level {INFO, WARN, ERROR};

    // Creamos la clase privada String filename.
    private final String filename = Constantes.RutaErrorLog();

    // log es una clase public que recibirá un mensaje, el nivel del error y su lugar
    // devolviendonos un string evento que a su vez se guardará en el error.log.

    public ErrorLog() {
    }

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
