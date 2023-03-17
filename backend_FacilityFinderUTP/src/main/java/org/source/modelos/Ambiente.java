package org.source.modelos;

public class Ambiente {
    private String Sede;
    private String nombreSede;
    private String Ambiente;
    private String Torre;

    public Ambiente(String sede, String nombreSede, String ambiente, String torre) {
        Sede = sede;
        this.nombreSede = nombreSede;
        Ambiente = ambiente;
        Torre = torre;
    }

    public String getSede() {
        return Sede;
    }

    public void setSede(String sede) {
        Sede = sede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getAmbiente() {
        return Ambiente;
    }

    public void setAmbiente(String ambiente) {
        Ambiente = ambiente;
    }

    public String getTorre() {
        return Torre;
    }

    public void setTorre(String torre) {
        Torre = torre;
    }

    @Override
    public String toString() {
        return Sede + Ambiente + Torre;
    }
}
