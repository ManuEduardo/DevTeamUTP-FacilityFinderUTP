package org.source.modelos;

/**
 * Esta es la descripción de la clase Ambiente:
 * La clase Ambiente representa un lugar físico donde se imparten clases en la universidad.
 * Tiene los atributos Sede y nombreSede que representan el código y el nombre de la sede
 * de la universidad en donde se llevará a cabo la clase, AmbienteDeClase que representa el código
 * del aula y TorreOrAV que indica si la clase se imparte en una torre de la universidad o en una
 * aula virtual. Además, tiene un constructor que recibe estos atributos y un método toString()
 * que devuelve una cadena que representa el código completo del ambiente. La clase también tiene
 * getters y setters para acceder y modificar los atributos.
 * @author Gabriel Paiva
 */

public class Ambiente {

    /** Atributos. */
    private String Sede; // Atributo que representa el código la sede UTP.
    private String nombreSede; // Atributo que representa la sede UTP.
    private String AmbienteDeClase; // Atributo que representa el código del ambiente. (Código de aula)
    private String TorreOrAV; // Atributo que representa la torre o si la clase es en una aula virtual.

    /** Constructor de ambiente. */
    public Ambiente(String sede, String nombreSede, String ambienteDeClase, String torre) {
        Sede = sede;
        this.nombreSede = nombreSede;
        AmbienteDeClase = ambienteDeClase;
        TorreOrAV = torre;
    }

    /** Getter and Setter. */
    public void setSede(String sede) {
        Sede = sede;
    }
    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }
    public void setAmbienteDeClase(String ambienteDeClase) {
        AmbienteDeClase = ambienteDeClase;
    }
    public void setTorreOrAV(String torreOrAV) {
        TorreOrAV = torreOrAV;
    }
    public String getSede() {
        return Sede;
    }
    public String getNombreSede() {
        return nombreSede;
    }
    public String getAmbienteDeClase() {
        return AmbienteDeClase;
    }
    public String getTorreOrAV() {
        return TorreOrAV;
    }

    /**
     * Métodos:
     * Este es el método toString()
     * No cuenta con parámetros
     * @return El código real leído en el csv que indicaba un ambiente de clase.
     * */

    @Override
    public String toString() {
        return Sede  + TorreOrAV + AmbienteDeClase;
    }
}
