package org.source.modelos;

public class Clase {

    // Dia de la semana en el cual se imparte la clase.
    private String DiaSemana;

    private String HoraInicio;
    private String HoraFinal;

    //Codigo del aula de clase donde se impartirá la clase.
    private String AulaClase;

    public Clase(String diaSemana, String horaInicio, String horaFinal, String aulaClase) {
        DiaSemana = diaSemana;
        HoraInicio = horaInicio;
        HoraFinal = horaFinal;
        AulaClase = aulaClase;
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

    public String getAulaClase() {
        return AulaClase;
    }

    public void setAulaClase(String aulaClase) {
        AulaClase = aulaClase;
    }
}
