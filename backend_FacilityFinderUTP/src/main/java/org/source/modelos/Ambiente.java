package org.source.modelos;

public class Ambiente {
    private String Sede;
    private String nombreSede;
    private String Ambiente;
    private String TorreOrNumeroAV;

    public Ambiente(String sede, String nombreSede, String ambiente, String torre) {
        Sede = sede;
        this.nombreSede = nombreSede;
        Ambiente = ambiente;
        TorreOrNumeroAV = torre;
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

    public String getTorreOrNumeroAV() {
        return TorreOrNumeroAV;
    }

    public void setTorreOrNumeroAV(String torreOrNumeroAV) {
        TorreOrNumeroAV = torreOrNumeroAV;
    }

    @Override
    public String toString() {
        return Sede  + TorreOrNumeroAV + Ambiente;
    }
}
