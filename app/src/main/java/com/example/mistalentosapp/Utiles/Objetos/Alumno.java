package com.example.mistalentosapp.Utiles.Objetos;

public class Alumno {

    private Integer id;
    private String nombre;
    private String apellido;
    private String apodo;
    private String edad;
    private String grado;
    private Boolean estado;

    public Alumno(Integer id, String nombre, String apellido, String apodo, String edad, String grado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.edad = edad;
        this.grado = grado;
    }

    public Alumno(String nombre, String apellido, String apodo, String edad, String grado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.edad = edad;
        this.grado = grado;
    }

    public Alumno() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
