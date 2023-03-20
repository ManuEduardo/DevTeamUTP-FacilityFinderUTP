package org.source.Validaciones;

/**
 * La clase DataIgualException es una excepción personalizada que se utiliza para indicar que se ha producido
 * un error debido a que dos objetos tienen los mismos datos.
 *
 * Esta excepción debe ser manejada o declarada en el método que la lanza.
 *
 * La clase extiende la clase Exception, lo que significa que es una excepción comprobada. Tiene un único
 * constructor que toma un mensaje de error como argumento. Este constructor llama al constructor de la clase
 * padre (Exception) para establecer el mensaje de error que se mostrará cuando se lance la excepción.
 *
 * La excepción DataIgualException se puede lanzar en cualquier parte del código donde se detecte que dos
 * objetos tienen los mismos datos. Por ejemplo, si se está comparando dos objetos y se determina que son
 * iguales, se puede lanzar una excepción DataIgualException para indicar que se ha producido un error debido
 * a que dos objetos tienen los mismos datos. Luego, esta excepción puede ser manejada en otro lugar del código
 * para mostrar un mensaje de error al usuario o realizar alguna otra acción necesaria.
 *
 * @author Gabriel Paiva
 * @version 1.0
 * @since 2023-03-19
 */

public class DataIgualException extends Exception {
    public DataIgualException(String mensaje) {
        super(mensaje);
    }
}
