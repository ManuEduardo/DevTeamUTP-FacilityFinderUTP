package org.source.modelos;

public abstract class PersonaUniversitaria {
    private final String nombre;
    private final String codigo;

    public PersonaUniversitaria(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }
}
