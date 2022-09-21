package com.example.mistalentosapp.Utiles.Objetos;

public class ColocarImagenes {
    private int id;
    private String grado;
    private String area;
    private String path1;
    private String path2;
    private String path3;
    private String path4;
    private String nombre1;
    private String nombre2;
    private String nombre3;
    private String nombre4;

    public ColocarImagenes(int id, String grado, String area, String path1, String path2, String path3, String path4, String nombre1, String nombre2, String nombre3, String nombre4) {
        this.id = id;
        this.grado = grado;
        this.area = area;
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
        this.path4 = path4;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.nombre3 = nombre3;
        this.nombre4 = nombre4;
    }

    public ColocarImagenes(String grado, String area, String path1, String path2, String path3, String path4, String nombre1, String nombre2, String nombre3, String nombre4) {
        this.grado = grado;
        this.area = area;
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
        this.path4 = path4;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.nombre3 = nombre3;
        this.nombre4 = nombre4;
    }

    public ColocarImagenes() {

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

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getNombre3() {
        return nombre3;
    }

    public void setNombre3(String nombre3) {
        this.nombre3 = nombre3;
    }

    public String getNombre4() {
        return nombre4;
    }

    public void setNombre4(String nombre4) {
        this.nombre4 = nombre4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecuenciaImagenes(){
        return this.nombre1+" "+this.nombre2+" "+this.nombre3+" "+this.nombre4;
    }
}
