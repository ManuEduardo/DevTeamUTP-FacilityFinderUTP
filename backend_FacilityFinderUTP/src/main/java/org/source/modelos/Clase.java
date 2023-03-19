package org.source.modelos;

/**
 * Esta es la descripción de la clase "Clase":
 * Esta es la clase "Clase" que representa una clase específica de un curso,
 * con información sobre su día de la semana, hora de inicio y finalización,
 * y el ambiente donde se imparte la clase.
 * Tiene tres constructores, uno que toma los valores de todos los atributos,
 * otro sin argumentos y otro que no incluye el atributo ambiente.
 * Además, cuenta con métodos getter y setter para cada atributo y un método adicional
 * getHorarioClase() que devuelve una cadena que representa la hora de inicio y
 * finalización de la clase en un formato específico "HH:mm - HH:mm".
 * @author Gabriel Paiva
 */

public class Clase {

    /** Atributos. */
    private String DiaSemana; // Dia de la semana en el cual se imparte la clase.
    private String HoraInicio; // Hora de inicio de la clase.
    private String HoraFinal; // Hora de finalización de la clase.
    private Ambiente ambiente; // Clase del ambiente donde se brindará la clase.

    /** Constructores. */
    public Clase(String diaSemana, String horaInicio, String horaFinal, Ambiente ambiente) {
        DiaSemana = diaSemana;
        HoraInicio = horaInicio;
        HoraFinal = horaFinal;
        this.ambiente = ambiente;
    }
    public Clase() {
    }
    public Clase(String diaSemana, String horaInicio, String horaFinal) {
        DiaSemana = diaSemana;
        HoraInicio = horaInicio;
        HoraFinal = horaFinal;
    }

    /** Getters and Setters. */
    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }
    public void setDiaSemana(String diaSemana) {
        DiaSemana = diaSemana;
    }
    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }
    public void setHoraFinal(String horaFinal) {
        HoraFinal = horaFinal;
    }
    public Ambiente getAmbiente() {
        return ambiente;
    }
    public String getDiaSemana() {
        return DiaSemana;
    }
    public String getHoraInicio() {
        return HoraInicio;
    }
    public String getHoraFinal() {
        return HoraFinal;
    }
    public String getHorarioClase() {
        return HoraInicio + " - " + HoraFinal;
    } // Devuelve el horario en formato String "HH:mm - HH:mm".
}
