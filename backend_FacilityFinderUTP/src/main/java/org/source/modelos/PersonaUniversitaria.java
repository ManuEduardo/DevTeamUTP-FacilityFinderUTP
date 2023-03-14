package org.source.modelos;

public abstract class PersonaUniversitaria {
    private String nombre;
    private String codigo;

    public PersonaUniversitaria(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public PersonaUniversitaria() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }
}
