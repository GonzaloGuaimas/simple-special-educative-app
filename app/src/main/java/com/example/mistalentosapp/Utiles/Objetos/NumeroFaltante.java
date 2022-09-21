package com.example.mistalentosapp.Utiles.Objetos;

public class NumeroFaltante {
    private int id;
    private String grado;
    private String area;
    private String a1,a2,a3,
        b1,b2,b3,
        c1,c2,c3,
        d1,d2,d3;

    public NumeroFaltante(int id, String grado, String area, String a1, String a2, String a3, String b1, String b2, String b3, String c1, String c2, String c3, String d1, String d2, String d3) {
        this.id = id;
        this.grado = grado;
        this.area = area;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
    }

    public NumeroFaltante(String grado, String area, String a1, String a2, String a3, String b1, String b2, String b3, String c1, String c2, String c3, String d1, String d2, String d3) {
        this.grado = grado;
        this.area = area;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
    }

    public NumeroFaltante() {

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

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getSecuenciaNumeros(){
        return this.a1+"-"+this.a2+"-"+this.a3+"|"+this.b1+"-"+this.b2+"-"+this.b3+"|"+this.c1+"-"+this.c2+"-"+this.c3+"|"+this.d1+"-"+this.d2+"-"+this.d3;
    }
}
