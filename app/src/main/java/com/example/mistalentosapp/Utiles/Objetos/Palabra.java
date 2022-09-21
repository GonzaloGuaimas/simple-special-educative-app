package com.example.mistalentosapp.Utiles.Objetos;

public class Palabra {

    private int id;
    private String palabra;
    private String grado;
    private String area;
    private String pathImagen;
    private String path1;
    private String path2;
    private String path3;
    private String path4;
    private String path5;
    private String path6;


    public Palabra() {

    }

    public Palabra(String palabra,String area,String grado,String pathImagen) {
        this.palabra = palabra;
        this.grado = grado;
        this.area = area;
        this.pathImagen = pathImagen;
    }

    public Palabra(int id,String palabra,String area,String grado,String pathImagen) {
        this.palabra = palabra;
        this.id = id;
        this.grado = grado;
        this.area = area;
        this.pathImagen = pathImagen;
    }

    public Palabra(int id,String palabra,String area,String grado,String path1,String path2,String path3,String path4,String path5,String path6) {
        this.palabra = palabra;
        this.id = id;
        this.grado = grado;
        this.area = area;
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
        this.path4 = path4;
        this.path5 = path5;
        this.path6 = path6;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
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

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath3() {
        return path3;
    }

    public void setPath3(String path3) {
        this.path3 = path3;
    }

    public String getPath4() {
        return path4;
    }

    public void setPath4(String path4) {
        this.path4 = path4;
    }

    public String getPath5() {
        return path5;
    }

    public void setPath5(String path5) {
        this.path5 = path5;
    }

    public String getPath6() {
        return path6;
    }

    public void setPath6(String path6) {
        this.path6 = path6;
    }

}
