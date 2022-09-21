package com.example.mistalentosapp.Utiles.Objetos;

public class LetraFaltante {

    private int id;
    private String grado;
    private String area;
    private String palabraCompleta;
    private String palabra;
    private String path;

    public LetraFaltante() {
    }

    public LetraFaltante(int id, String grado, String area, String palabraCompleta, String palabra, String path) {
        this.id = id;
        this.grado = grado;
        this.area = area;
        this.palabraCompleta = palabraCompleta;
        this.palabra = palabra;
        this.path = path;
    }

    public LetraFaltante(String grado, String area, String palabraCompleta, String palabra, String path) {
        this.grado = grado;
        this.area = area;
        this.palabraCompleta = palabraCompleta;
        this.palabra = palabra;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPalabraCompleta() {
        return palabraCompleta;
    }

    public void setPalabraCompleta(String palabraCompleta) {
        this.palabraCompleta = palabraCompleta;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
