package org.source.Validaciones;

/**
 * La clase CodigoInvalidoException es una excepción personalizada que se utiliza para indicar que se ha
 * producido un error debido a un código inválido. Esta excepción debe ser manejada o declarada en el
 * método que la lanza.
 *
 * La clase extiende la clase Exception, por lo que es una excepción comprobada. Tiene un único constructor
 * que toma un mensaje de error como argumento. Este constructor llama al constructor de la clase padre
 * (Exception) para establecer el mensaje de error que se mostrará cuando se lance la excepción.
 *
 * La excepción CodigoInvalidoException se puede lanzar en cualquier parte del código donde se detecte que
 * un código es inválido. Por ejemplo, si se está validando un código de acceso en un sistema y se determina
 * que el código ingresado es incorrecto, se puede lanzar una excepción CodigoInvalidoException para
 * indicar que se ha producido un error debido a un código inválido. Luego, esta excepción puede ser
 * manejada en otro lugar del código para mostrar un mensaje de error al usuario o realizar alguna otra
 * acción necesaria.
 *
 * @author Gabriel Paiva
 * @version 1.0
 * @since 2022-03-19
 */


public class CodigoInvalidoException extends Exception {
    public CodigoInvalidoException(String mensaje) {
        super(mensaje);
    }
}

