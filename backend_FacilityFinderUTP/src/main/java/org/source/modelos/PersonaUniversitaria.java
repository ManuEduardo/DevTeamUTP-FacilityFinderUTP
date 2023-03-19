package org.source.modelos;

/**
 * Esta es la descripción de la clase PersonaUniversitaria.
 * Se utiliza como superclase de Estudiante y Profesor.
 * @author Gabriel Paiva
 */

public abstract class PersonaUniversitaria {

    /** Atributos de la clase PersonaUniversitaria. */
    private String nombre; /* Nombre de la persona universitaria */
    private String codigo; /* Código de la persona universitaria  */

    /** Constructor utilizado en los super(). */
    public PersonaUniversitaria(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    /** Getter and setters */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
}
