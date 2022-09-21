package com.example.mistalentosapp.Utiles.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.mistalentosapp.Utiles.Objetos.Abecedario;
import com.example.mistalentosapp.Utiles.Objetos.Actividad;
import com.example.mistalentosapp.Utiles.Objetos.Alumno;
import com.example.mistalentosapp.Utiles.Objetos.ColocarImagenes;
import com.example.mistalentosapp.Utiles.Objetos.LetraFaltante;
import com.example.mistalentosapp.Utiles.Objetos.NumeroFaltante;
import com.example.mistalentosapp.Utiles.Objetos.OperacionNumeros;
import com.example.mistalentosapp.Utiles.Objetos.Palabra;

import java.util.ArrayList;

public class Controller {
    private DataBaseHelper dataBaseHelper;
    private String TABLA_PALABRAS = "Palabras";
    private String TABLA_ALUMNOS = "Alumnos";
    private String TABLA_ACTIVIDAD = "Actividades";
    private String TABLA_ORACION = "Oraciones";
    private String TABLA_NUMEROFALTANTE = "NumeroFaltante";
    private String TABLA_OPERACIONNUMEROS = "OperacionNumeros";
    private String TABLA_COLOCARIMAGEN = "ColocarImagen";
    private String TABLA_ABECEDARIO = "Abecedario";
    private String TABLA_LETRAFALTANTE = "LetraFaltante";



    public Controller(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }


    //------------------------PALABRA------------------------------------------------
    //------------------------PALABRA------------------------------------------------
    //------------------------PALABRA------------------------------------------------
    //------------------------PALABRA------------------------------------------------

    public int eliminarPalabra(int id){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(id)};
        return baseDatos.delete(TABLA_PALABRAS,"id = ?",args);
    }
    public long nuevaPalabra(Palabra palabra) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("palabra", palabra.getPalabra());
        valoresParaInsertar.put("area", palabra.getArea());
        valoresParaInsertar.put("grado", palabra.getGrado());
        valoresParaInsertar.put("path", palabra.getPathImagen());
        return baseDeDatos.insert(TABLA_PALABRAS, null, valoresParaInsertar);
    }

    public ArrayList<Palabra> obtenerPalabra() {
        ArrayList<Palabra> palabras = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "palabra", "area","grado","path"};
        Cursor cursor = baseDeDatos.query(TABLA_PALABRAS,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return palabras;
        }
        if (!cursor.moveToFirst()) return palabras;
        do {
            Palabra palabra = new Palabra(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            palabras.add(palabra);
        } while (cursor.moveToNext());
        cursor.close();
        return palabras;
    }
    public Palabra obtenerPalabra(int id) {
        Palabra palabraReturn = new Palabra();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "palabra", "area","grado","path"};
        Cursor cursor = baseDeDatos.query(TABLA_PALABRAS,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return palabraReturn;
        }
        if (!cursor.moveToFirst()) return palabraReturn;
        do {
            Palabra palabra = new Palabra(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            if (palabra.getId()==id){
                palabraReturn = palabra;
                break;
            }
        } while (cursor.moveToNext());
        cursor.close();

        return palabraReturn;
    }

    //------------------------ORACION------------------------------------------------
    //------------------------ORACION------------------------------------------------
    //------------------------ORACION------------------------------------------------
    //------------------------ORACION------------------------------------------------

    public int eliminarOracion(int id){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(id)};
        return baseDatos.delete(TABLA_ORACION,"id = ?",args);
    }
    public long nuevaOracion(Palabra palabra) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("oracion", palabra.getPalabra());
        valoresParaInsertar.put("area", palabra.getArea());
        valoresParaInsertar.put("grado", palabra.getGrado());
        valoresParaInsertar.put("path1", palabra.getPath1());
        valoresParaInsertar.put("path2", palabra.getPath2());
        valoresParaInsertar.put("path3", palabra.getPath3());
        valoresParaInsertar.put("path4", palabra.getPath4());
        valoresParaInsertar.put("path5", palabra.getPath5());
        valoresParaInsertar.put("path6", palabra.getPath6());
        return baseDeDatos.insert(TABLA_ORACION, null, valoresParaInsertar);
    }

    public ArrayList<Palabra> obtenerOracion() {
        ArrayList<Palabra> palabras = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "oracion", "area","grado","path1","path2","path3","path4","path5","path6"};
        Cursor cursor = baseDeDatos.query(TABLA_ORACION,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return palabras;
        }
        if (!cursor.moveToFirst()) return palabras;
        do {
            Palabra palabra = new Palabra(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)
                    ,cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
            palabras.add(palabra);
        } while (cursor.moveToNext());
        cursor.close();
        return palabras;
    }
    public Palabra obtenerOracion(int id) {
        Palabra palabraReturn = new Palabra();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "oracion", "area","grado","path1","path2","path3","path4","path5","path6"};
        Cursor cursor = baseDeDatos.query(TABLA_ORACION,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return palabraReturn;
        }
        if (!cursor.moveToFirst()) return palabraReturn;
        do {
            Palabra palabra = new Palabra(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)
                    ,cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));

            if (palabra.getId()==id){
                palabraReturn = palabra;
                break;
            }
        } while (cursor.moveToNext());
        cursor.close();

        return palabraReturn;
    }

    //-----------------------ABECEDARIO------------------------------------------------
    //-----------------------ABECEDARIO------------------------------------------------
    //-----------------------ABECEDARIO------------------------------------------------
    //-----------------------ABECEDARIO------------------------------------------------

    public int eliminarAbecedario(int id){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(id)};
        return baseDatos.delete(TABLA_ABECEDARIO,"id = ?",args);
    }
    public long nuevaAbecedario(Abecedario abecedario) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("area", abecedario.getArea());
        valoresParaInsertar.put("grado", abecedario.getGrado());
        valoresParaInsertar.put("letra", abecedario.getLetra());
        valoresParaInsertar.put("path1", abecedario.getPath1());
        valoresParaInsertar.put("path2", abecedario.getPath2());
        valoresParaInsertar.put("path3", abecedario.getPath3());
        valoresParaInsertar.put("path4", abecedario.getPath4());
        valoresParaInsertar.put("path5", abecedario.getPath5());
        valoresParaInsertar.put("path6", abecedario.getPath6());
        valoresParaInsertar.put("nombre1", abecedario.getNombre1());
        valoresParaInsertar.put("nombre2", abecedario.getNombre2());
        valoresParaInsertar.put("nombre3", abecedario.getNombre3());
        valoresParaInsertar.put("nombre4", abecedario.getNombre4());
        valoresParaInsertar.put("nombre5", abecedario.getNombre5());
        valoresParaInsertar.put("nombre6", abecedario.getNombre6());
        return baseDeDatos.insert(TABLA_ABECEDARIO, null, valoresParaInsertar);
    }

    public ArrayList<Abecedario> obtenerAbecedario() {
        ArrayList<Abecedario> abecedarios = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "area","grado","letra","path1","path2","path3","path4","path5","path6","nombre1","nombre2","nombre3","nombre4","nombre5","nombre6"};
        Cursor cursor = baseDeDatos.query(TABLA_ABECEDARIO,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return abecedarios;
        }
        if (!cursor.moveToFirst()) return abecedarios;
        do {
            Abecedario palabra = new Abecedario(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10)
                    ,cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15));
            abecedarios.add(palabra);
        } while (cursor.moveToNext());
        cursor.close();
        return abecedarios;
    }
    public Abecedario obtenerAbecedario(int id) {
        Abecedario palabraReturn = new Abecedario();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "area","grado","letra","path1","path2","path3","path4","path5","path6","nombre1","nombre2","nombre3","nombre4","nombre5","nombre6"};
        Cursor cursor = baseDeDatos.query(TABLA_ABECEDARIO,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return palabraReturn;
        }
        if (!cursor.moveToFirst()) return palabraReturn;
        do {
            Abecedario palabra = new Abecedario(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10)
                    ,cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15));
            if (palabra.getId()==id){
                palabraReturn = palabra;
                break;
            }
        } while (cursor.moveToNext());
        cursor.close();

        return palabraReturn;
    }

    //-----------------------LETRAFALTANTE------------------------------------------------
    //-----------------------LETRAFALTANTE------------------------------------------------
    //-----------------------LETRAFALTANTE------------------------------------------------
    //-----------------------LETRAFALTANTE------------------------------------------------

    public int eliminarLetraFaltante(int id){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(id)};
        return baseDatos.delete(TABLA_LETRAFALTANTE,"id = ?",args);
    }
    public long nuevaLetraFaltante(LetraFaltante letraFaltante) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("area", letraFaltante.getArea());
        valoresParaInsertar.put("grado", letraFaltante.getGrado());
        valoresParaInsertar.put("palabraCompleta", letraFaltante.getPalabraCompleta());
        valoresParaInsertar.put("palabra", letraFaltante.getPalabra());
        valoresParaInsertar.put("path", letraFaltante.getPath());

        return baseDeDatos.insert(TABLA_LETRAFALTANTE, null, valoresParaInsertar);
    }

    public ArrayList<LetraFaltante> obtenerLetraFaltante() {
        ArrayList<LetraFaltante> letraFaltantes = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id","grado","area","palabraCompleta","palabra","path"};
        Cursor cursor = baseDeDatos.query(TABLA_LETRAFALTANTE,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return letraFaltantes;
        }
        if (!cursor.moveToFirst()) return letraFaltantes;
        do {
            LetraFaltante letraFaltante = new LetraFaltante(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5));
            letraFaltantes.add(letraFaltante);
        } while (cursor.moveToNext());
        cursor.close();
        return letraFaltantes;
    }
    public LetraFaltante obtenerLetraFaltante(int id) {
        LetraFaltante letraFaltanteReturn = new LetraFaltante();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar =  {"id", "grado","area","palabraCompleta","palabra","path"};
        Cursor cursor = baseDeDatos.query(TABLA_LETRAFALTANTE,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return letraFaltanteReturn;
        }
        if (!cursor.moveToFirst()) return letraFaltanteReturn;
        do {
            LetraFaltante letraFaltante = new LetraFaltante(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5));
            if (letraFaltante.getId()==id){
                letraFaltanteReturn = letraFaltante;
                break;
            }
        } while (cursor.moveToNext());
        cursor.close();

        return letraFaltanteReturn;
    }









    //------------------------NUMEROSFALTANTES------------------------------------------------
    //------------------------NUMEROSFALTANTES------------------------------------------------
    //------------------------NUMEROSFALTANTES------------------------------------------------
    //------------------------NUMEROSFALTANTES------------------------------------------------

    public int eliminarNumeroFaltante(int id){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(id)};
        return baseDatos.delete(TABLA_NUMEROFALTANTE,"id = ?",args);
    }
    public long nuevaNumeroFaltante(NumeroFaltante numeroFaltante) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("area", numeroFaltante.getArea());
        valoresParaInsertar.put("grado", numeroFaltante.getGrado());
        valoresParaInsertar.put("a1", numeroFaltante.getA1());
        valoresParaInsertar.put("a2", numeroFaltante.getA2());
        valoresParaInsertar.put("a3", numeroFaltante.getA3());
        valoresParaInsertar.put("b1", numeroFaltante.getB1());
        valoresParaInsertar.put("b2", numeroFaltante.getB2());
        valoresParaInsertar.put("b3", numeroFaltante.getB3());
        valoresParaInsertar.put("c1", numeroFaltante.getC1());
        valoresParaInsertar.put("c2", numeroFaltante.getC2());
        valoresParaInsertar.put("c3", numeroFaltante.getC3());
        valoresParaInsertar.put("d1", numeroFaltante.getD1());
        valoresParaInsertar.put("d2", numeroFaltante.getD2());
        valoresParaInsertar.put("d3", numeroFaltante.getD3());
        return baseDeDatos.insert(TABLA_NUMEROFALTANTE, null, valoresParaInsertar);
    }

    public ArrayList<NumeroFaltante> obtenerNumeroFaltante() {
        ArrayList<NumeroFaltante> numeros = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "grado","area","a1","a2","a3","b1","b2","b3","c1","c2","c3","d1","d2","d3"};
        Cursor cursor = baseDeDatos.query(TABLA_NUMEROFALTANTE,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return numeros;
        }
        if (!cursor.moveToFirst()) return numeros;
        do {
            NumeroFaltante numero = new NumeroFaltante(cursor.getInt(0),cursor.getString(1),"",cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6), cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11)
                    ,cursor.getString(12),cursor.getString(13),cursor.getString(14));
            numeros.add(numero);
        } while (cursor.moveToNext());
        cursor.close();
        return numeros;
    }

    public NumeroFaltante obtenerNumeroFaltante(int id) {
        NumeroFaltante numeroRetorno = new NumeroFaltante();
        ArrayList<NumeroFaltante> numeros = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "grado","area","a1","a2","a3","b1","b2","b3","c1","c2","c3","d1","d2","d3"};
        Cursor cursor = baseDeDatos.query(TABLA_NUMEROFALTANTE,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return numeroRetorno;
        }
        if (!cursor.moveToFirst()) return numeroRetorno;
        do {
            NumeroFaltante numero = new NumeroFaltante(cursor.getInt(0),cursor.getString(1),"",cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6), cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11)
                    ,cursor.getString(12),cursor.getString(13),cursor.getString(14));
            if(numero.getId()==id){
                numeroRetorno = numero;
                break;
            }
        } while (cursor.moveToNext());
        cursor.close();
        return numeroRetorno;
    }
    //------------------------OPERACIONNUMEROS------------------------------------------------
    //------------------------OPERACIONNUMEROS------------------------------------------------
    //------------------------OPERACIONNUMEROS------------------------------------------------
    //------------------------OPERACIONNUMEROS------------------------------------------------

    public int eliminarOperacionNumero(int id){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(id)};
        return baseDatos.delete(TABLA_OPERACIONNUMEROS,"id = ?",args);
    }
    public long nuevaOperacionNumero(OperacionNumeros operacionNumeros) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("area", operacionNumeros.getArea());
        valoresParaInsertar.put("grado", operacionNumeros.getGrado());
        valoresParaInsertar.put("numero1", operacionNumeros.getNumero1());
        valoresParaInsertar.put("numero2", operacionNumeros.getNumero2());
        valoresParaInsertar.put("problema", operacionNumeros.getProblema());
        return baseDeDatos.insert(TABLA_OPERACIONNUMEROS, null, valoresParaInsertar);
    }

    public ArrayList<OperacionNumeros> obtenerOperacionNumero() {
        ArrayList<OperacionNumeros> numeros = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "grado","area","numero1","numero2","problema"};
        Cursor cursor = baseDeDatos.query(TABLA_OPERACIONNUMEROS,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return numeros;
        }
        if (!cursor.moveToFirst()) return numeros;
        do {
            OperacionNumeros numero = new OperacionNumeros(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            numeros.add(numero);
        } while (cursor.moveToNext());
        cursor.close();
        return numeros;
    }
    public OperacionNumeros obtenerOperacionNumero(int id) {
        OperacionNumeros operacionRetorno = new OperacionNumeros();
        ArrayList<OperacionNumeros> numeros = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "grado","area","numero1","numero2","problema"};
        Cursor cursor = baseDeDatos.query(TABLA_OPERACIONNUMEROS,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return operacionRetorno;
        }
        if (!cursor.moveToFirst()) return operacionRetorno;
        do {
            OperacionNumeros numero = new OperacionNumeros(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            if (numero.getId()==id){
                operacionRetorno = numero;
                break;
            }

        } while (cursor.moveToNext());
        cursor.close();
        return operacionRetorno;
    }









    //------------------------COLOCAR IMAGEN------------------------------------------------
    //------------------------COLOCAR IMAGEN------------------------------------------------
    //------------------------COLOCAR IMAGEN------------------------------------------------
    //------------------------COLOCAR IMAGEN------------------------------------------------

    public int eliminarColocarImagen(int id){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(id)};
        return baseDatos.delete(TABLA_COLOCARIMAGEN,"id = ?",args);
    }
    public long nuevaColocarImagen(ColocarImagenes colocarImagenes) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("area", colocarImagenes.getArea());
        valoresParaInsertar.put("grado", colocarImagenes.getGrado());
        valoresParaInsertar.put("path1", colocarImagenes.getPath1());
        valoresParaInsertar.put("path2", colocarImagenes.getPath2());
        valoresParaInsertar.put("path3", colocarImagenes.getPath3());
        valoresParaInsertar.put("path4", colocarImagenes.getPath4());
        valoresParaInsertar.put("nombre1", colocarImagenes.getNombre1());
        valoresParaInsertar.put("nombre2", colocarImagenes.getNombre2());
        valoresParaInsertar.put("nombre3", colocarImagenes.getNombre3());
        valoresParaInsertar.put("nombre4", colocarImagenes.getNombre4());
        return baseDeDatos.insert(TABLA_COLOCARIMAGEN, null, valoresParaInsertar);
    }

    public ArrayList<ColocarImagenes> obtenerColocarImagen() {
        ArrayList<ColocarImagenes> imagenes = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "grado","area","path1","path2","path3","path4","nombre1","nombre2","nombre3","nombre4"};
        Cursor cursor = baseDeDatos.query(TABLA_COLOCARIMAGEN,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return imagenes;
        }
        if (!cursor.moveToFirst()) return imagenes;
        do {
            ColocarImagenes numero = new ColocarImagenes(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)
                    ,cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
            imagenes.add(numero);
        } while (cursor.moveToNext());
        cursor.close();
        return imagenes;
    }
    public ColocarImagenes obtenerColocarImagen(int id) {
        ColocarImagenes colocarReturn = new ColocarImagenes();
        ArrayList<ColocarImagenes> imagenes = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "grado","area","path1","path2","path3","path4","nombre1","nombre2","nombre3","nombre4"};
        Cursor cursor = baseDeDatos.query(TABLA_COLOCARIMAGEN,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return colocarReturn;
        }
        if (!cursor.moveToFirst()) return colocarReturn;
        do {
            ColocarImagenes numero = new ColocarImagenes(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)
                    ,cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
            if (id==numero.getId()){
                colocarReturn = numero;
                break;
            }
        } while (cursor.moveToNext());
        cursor.close();
        return colocarReturn;
    }











    //------------------------ALUMNO-----------------------------------------------
    //------------------------ALUMNO-----------------------------------------------
    //------------------------ALUMNO-----------------------------------------------
    //------------------------ALUMNO-----------------------------------------------

    public int eliminarAlumno(int id){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(id)};
        return baseDatos.delete(TABLA_ALUMNOS,"id = ?",args);
    }
    public long nuevoAlumno(Alumno alumno) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", alumno.getNombre());
        valoresParaInsertar.put("apellido", alumno.getApellido());
        valoresParaInsertar.put("apodo", alumno.getApodo());
        valoresParaInsertar.put("edad", alumno.getEdad());
        valoresParaInsertar.put("grado", alumno.getGrado());

        return baseDeDatos.insert(TABLA_ALUMNOS, null, valoresParaInsertar);
    }

    public long guardarCambiosAlumno(Alumno alumno) {
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(alumno.getId())};
        baseDatos.delete(TABLA_ALUMNOS,"id = ?",args);

        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", alumno.getNombre());
        valoresParaInsertar.put("apellido", alumno.getApellido());
        valoresParaInsertar.put("apodo", alumno.getApodo());
        valoresParaInsertar.put("edad", alumno.getEdad());
        valoresParaInsertar.put("grado", alumno.getGrado());
        return baseDatos.insert(TABLA_ALUMNOS, null, valoresParaInsertar);
    }

    public ArrayList<Alumno> obtenerAlumno() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "nombre", "apellido","apodo","edad","grado"};
        Cursor cursor = baseDeDatos.query(TABLA_ALUMNOS,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return alumnos;
        }
        if (!cursor.moveToFirst()) return alumnos;
        do {
            Alumno alumno = new Alumno(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            alumnos.add(alumno);
        } while (cursor.moveToNext());
        cursor.close();
        return alumnos;
    }



    //------------------------ACTIVIDAD-----------------------------------------------
    //------------------------ACTIVIDAD-----------------------------------------------
    //------------------------ACTIVIDAD-----------------------------------------------
    //------------------------ACTIVIDAD-----------------------------------------------

    public int eliminarActividad(Actividad actividad){
        SQLiteDatabase baseDatos = dataBaseHelper.getWritableDatabase();
        String[] args = {String.valueOf(actividad.getId())};
        return baseDatos.delete(TABLA_ACTIVIDAD,"id = ?",args);
    }
    public long nuevoActividad(Actividad actividad) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("fecha", actividad.getFecha());
        valoresParaInsertar.put("duracion", actividad.getDuracion());
        valoresParaInsertar.put("aciertos", actividad.getAciertos());
        valoresParaInsertar.put("fallos", actividad.getFallos());
        valoresParaInsertar.put("alumno", actividad.getAlumno());
        valoresParaInsertar.put("nombre", actividad.getNombre());
        valoresParaInsertar.put("contenido", actividad.getContenido());
        valoresParaInsertar.put("idActividad", actividad.getIdActividad());
        //valoresParaInsertar.put("nomActividad", actividad.getNomActividad());
        return baseDeDatos.insert(TABLA_ACTIVIDAD, null, valoresParaInsertar);
    }

    public int guardarCambiosActividad(Actividad actividad) {
        SQLiteDatabase baseDeDatos = dataBaseHelper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("fecha", actividad.getFecha());
        valoresParaInsertar.put("duracion", actividad.getDuracion());
        valoresParaInsertar.put("aciertos", actividad.getAciertos());
        valoresParaInsertar.put("fallos", actividad.getFallos());
        valoresParaInsertar.put("alumno", actividad.getAlumno());
        valoresParaInsertar.put("nombre", actividad.getNombre());
        valoresParaInsertar.put("contenido", actividad.getContenido());
        valoresParaInsertar.put("idActividad", actividad.getIdActividad());
       //valoresParaInsertar.put("nomActividad", actividad.getNomActividad());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(actividad.getId())};
        return baseDeDatos.update(TABLA_ACTIVIDAD, valoresParaInsertar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Actividad> obtenerActividad() {
        ArrayList<Actividad> actividades = new ArrayList<>();
        SQLiteDatabase baseDeDatos = dataBaseHelper.getReadableDatabase();
        String[] columnasAConsultar = {"id", "fecha", "duracion","aciertos","fallos","alumno","nombre","contenido","idActividad"};
        Cursor cursor = baseDeDatos.query(TABLA_ACTIVIDAD,columnasAConsultar,null,null,null,null,null);
        if (cursor == null) {
            return actividades;
        }
        if (!cursor.moveToFirst()) return actividades;
        do {
            Actividad actividad = new Actividad(cursor.getInt(0),cursor.getLong(1),cursor.getString(2),
                    cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getInt(8));
            actividades.add(actividad);
        } while (cursor.moveToNext());
        cursor.close();
        return actividades;
    }

}
