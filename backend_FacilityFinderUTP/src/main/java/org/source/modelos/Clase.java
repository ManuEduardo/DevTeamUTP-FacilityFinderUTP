package org.source.modelos;

public class Clase {
    private String DiaSemana;
    private String HoraInicio;
    private String HoraFinal;

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
