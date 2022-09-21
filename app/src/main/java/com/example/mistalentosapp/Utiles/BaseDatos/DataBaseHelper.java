package com.example.mistalentosapp.Utiles.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "misTalentosBD",
            TABLA_PALABRAS = "Palabras",
            TABLA_ORACIONES = "Oraciones",
            TABLA_ACTIVIDAD = "Actividades",
            TABLA_ALUMNOS = "Alumnos",
            TABLA_NUMEROFALTANTE = "NumeroFaltante",
            TABLA_OPERACIONNUMEROS = "OperacionNumeros",
            TABLA_COLOCARIMAGEN = "ColocarImagen",
            TABLA_ABECEDARIO = "Abecedario",
            TABLA_LETRAFALTANTE = "LetraFaltante";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public DataBaseHelper(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, palabra text,area text,grado text,path text)", TABLA_PALABRAS));
        db.execSQL(String.format("INSERT INTO " + TABLA_PALABRAS+ "(palabra,area,grado,path) VALUES ('bien ve ni do','Lengua','A','')"));
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, oracion text,area text,grado text,path1 text,path2 text,path3 text,path4 text,path5 text,path6 text)", TABLA_ORACIONES));


        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, area text,grado text, a1 text,a2 text,a3 text,b1 text,b2 text,b3 text,c1 text,c2 text,c3 text,d1 text,d2 text,d3 text)", TABLA_NUMEROFALTANTE));
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, area text,grado text, numero1 text, numero2 text, problema text)", TABLA_OPERACIONNUMEROS));


        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, area text,grado text, path1 text, path2 text, path3 text, path4 text, nombre1 text, nombre2 text, nombre3 text, nombre4 text)", TABLA_COLOCARIMAGEN));


        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, nombre text,apellido text,apodo text,edad text,grado text)", TABLA_ALUMNOS));
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, fecha text,duracion text,aciertos text,fallos text,alumno text,nombre text, contenido text, idActividad text, nomActividad text)", TABLA_ACTIVIDAD));


        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, area text,grado text,letra text,path1 text,path2 text,path3 text,path4 text,path5 text,path6 text, nombre1 text, nombre2 text, nombre3 text, nombre4 text, nombre5 text, nombre6 text)", TABLA_ABECEDARIO));
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, area text,grado text,palabraCompleta text,palabra text,path text)", TABLA_LETRAFALTANTE));

        System.out.println("SE CREARON TABLAS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public void eliminarDatosTablas(SQLiteDatabase db){
        db.delete(TABLA_PALABRAS, null,null);
        db.delete(TABLA_ORACIONES, null,null);
        db.delete(TABLA_ACTIVIDAD, null,null);
        //db.delete(TABLA_ALUMNOS, null,null);
        db.delete(TABLA_NUMEROFALTANTE, null,null);
        db.delete(TABLA_OPERACIONNUMEROS, null,null);
        db.delete(TABLA_COLOCARIMAGEN, null,null);
        db.delete(TABLA_ABECEDARIO, null,null);
        db.delete(TABLA_LETRAFALTANTE, null,null);
    }
    public void eliminarDatosTablaAlumnos(SQLiteDatabase db){
        db.delete(TABLA_ALUMNOS, null,null);
    }
}
