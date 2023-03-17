package org.source.modelos;

public class Clase {

    // Dia de la semana en el cual se imparte la clase.
    private String DiaSemana;

    private String HoraInicio;
    private String HoraFinal;

    //Codigo del aula de clase donde se impartirá la clase.
    private Ambiente ambiente;

    public Clase(String diaSemana, String horaInicio, String horaFinal, Ambiente ambiente) {
        DiaSemana = diaSemana;
        HoraInicio = horaInicio;
        HoraFinal = horaFinal;
        this.ambiente = ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public String getDiaSemana() {
        return DiaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        DiaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(String horaFinal) {
        HoraFinal = horaFinal;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public String HorarioClase() {
        return HoraInicio + " - " + HoraFinal;
    }
}
