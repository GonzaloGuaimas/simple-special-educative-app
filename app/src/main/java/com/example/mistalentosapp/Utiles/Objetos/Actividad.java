package com.example.mistalentosapp.Utiles.Objetos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Actividad {
    private Integer id;
    private long fecha;
    private String duracion;
    private String nombre;
    private Integer aciertos;
    private Integer fallos;
    private String alumno;
    private String contenido;
    private Integer idActividad;


    private long inicio;
    private long fin;

    public Actividad(Integer id, long fecha, String duracion, Integer aciertos, Integer fallos, String alumno, String nombre, String contenido, Integer idActividad) {
        this.id = id;
        this.fecha = fecha;
        this.duracion = duracion;
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.alumno = alumno;
        this.nombre = nombre;
        this.contenido = contenido;
        this.idActividad = idActividad;

    }
    public Actividad(long fecha, String duracion, Integer aciertos, Integer fallos, String alumno,String nombre, String contenido, Integer idActividad) {
        this.id = id;
        this.fecha = fecha;
        this.duracion = duracion;
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.alumno = alumno;
        this.nombre = nombre;
        this.contenido = contenido;
        this.idActividad = idActividad;

    }
    public Actividad() {

    }




    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaddMMyyyy() {
        SimpleDateFormat formatoddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
        return formatoddMMyyyy.format(new Date(this.fecha));
    }
    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Integer getAciertos() {
        return aciertos;
    }

    public void setAciertos(Integer aciertos) {
        this.aciertos = aciertos;
    }

    public Integer getFallos() {
        return fallos;
    }

    public void setFallos(Integer fallos) {
        this.fallos = fallos;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //----------------------------------------------------------------------------------


    public long getInicio() {
        return inicio;
    }

    public void setInicio(long inicio) {
        this.inicio = inicio;
    }

    public long getFin() {
        return fin;
    }

    public void setFin(long fin) {
        this.fin = fin;
    }


    public void aumentarAcierto(){
        this.aciertos++;
    }

    public void aumentarFallo(){
        this.fallos++;
    }

    public String obtenerDuracion(){
        return String.valueOf((this.fin-this.inicio)/(1000*60) % 60);
    }

}
