package com.example.mistalentosapp.Utiles.Objetos;

public class OperacionNumeros {
    private int id;
    private String grado;
    private String area;
    private String numero1;
    private String numero2;
    private String problema;

    public OperacionNumeros(int id, String grado, String area, String numero1, String numero2, String problema) {
        this.id = id;
        this.grado = grado;
        this.area = area;
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.problema = problema;
    }

    public OperacionNumeros(String grado, String area, String numero1, String numero2, String problema) {
        this.grado = grado;
        this.area = area;
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.problema = problema;
    }

    public OperacionNumeros() {
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
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

    public String getNumero1() {
        return numero1;
    }

    public void setNumero1(String numero1) {
        this.numero1 = numero1;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    public String getSecuenciaNumeros(int operando){
        String retorno = "";
        if (operando == 1){
            retorno = this.numero1+"+"+this.numero2+"="+String.valueOf(Integer.parseInt(this.numero1)+Integer.parseInt(this.numero2));
        }else {
            retorno = this.numero1+"-"+this.numero2+"="+String.valueOf(Integer.parseInt(this.numero1)-Integer.parseInt(this.numero2));
        }
        return retorno;
    }
    public String getResultado(int operando){
        String retorno = "";
        if (operando == 1){
            retorno = String.valueOf(Integer.parseInt(this.numero1)+Integer.parseInt(this.numero2));
        }else {
            retorno = String.valueOf(Integer.parseInt(this.numero1)-Integer.parseInt(this.numero2));
        }
        return retorno;
    }
}
