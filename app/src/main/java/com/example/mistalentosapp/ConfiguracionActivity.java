package com.example.mistalentosapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistalentosapp.Extras.ColocarImagen;
import com.example.mistalentosapp.Extras.ColocarPalabra;
import com.example.mistalentosapp.Lengua.AbecedarioAct;
import com.example.mistalentosapp.Lengua.EscrituraDeOraciones;
import com.example.mistalentosapp.Lengua.EscrituraDePalabras;
import com.example.mistalentosapp.Lengua.LetraFaltanteAct;
import com.example.mistalentosapp.Matematicas.SumaDeNumeros;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorActividad;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorActividadConf;
import com.example.mistalentosapp.Utiles.BaseDatos.Controller;
import com.example.mistalentosapp.Utiles.Objetos.Abecedario;
import com.example.mistalentosapp.Utiles.Objetos.Actividad;
import com.example.mistalentosapp.Utiles.Objetos.ActividadConf;
import com.example.mistalentosapp.Utiles.Objetos.Alumno;
import com.example.mistalentosapp.Utiles.Objetos.ColocarImagenes;
import com.example.mistalentosapp.Utiles.Objetos.LetraFaltante;
import com.example.mistalentosapp.Utiles.Objetos.NumeroFaltante;
import com.example.mistalentosapp.Utiles.Objetos.OperacionNumeros;
import com.example.mistalentosapp.Utiles.Objetos.Palabra;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionActivity extends AppCompatActivity {

    CardView cardNuevaActividad, cardSelecActividad,cardSelecGrado,cardSelecArea;
    TextView textActividad,textArea,textGrado;

    private String grado,area;

    //IMAGENES:
    ImageView imagenPalabra;
    String pathImgPalabra;
    String imagenSeleccionada;

    ImageView imagen1, imagen2,imagen3,imagen4,imagenO1, imagenO2,imagenO3,imagenO4,imagenO5,imagenO6, imagenLetraFaltante,
            imagen01Ab,imagen02Ab,imagen03Ab,imagen04Ab,imagen05Ab,imagen06Ab;;
    String im1,im2,im3,im4,imO1,imO2,imO3,imO4,imO5,imO6,imagenLetraFaltanteString,
            ima1Ab,ima2Ab,ima3Ab,ima4Ab,ima5Ab,ima6Ab;

    private String link = "vacio";
    private String linkImagen1 = "vacio";
    private String linkImagen2 = "vacio";
    private String linkImagen3 = "vacio";
    private String linkImagen4 = "vacio";
    private String linkImagenO1 = "vacio";
    private String linkImagenO2 = "vacio";
    private String linkImagenO3 = "vacio";
    private String linkImagenO4 = "vacio";
    private String linkImagenO5 = "vacio";
    private String linkImagenO6 = "vacio";
    private String linkLetraFaltante = "vacio";
    private String linkImagen1Ab = "vacio";
    private String linkImagen2Ab = "vacio";
    private String linkImagen3Ab = "vacio";
    private String linkImagen4Ab = "vacio";
    private String linkImagen5Ab = "vacio";
    private String linkImagen6Ab = "vacio";

    private Boolean band = false;

    private List<ActividadConf> actividades;
    private AdaptadorActividadConf adaptadorActividadConf;
    private RecyclerView recyclerActividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        recyclerActividades = findViewById(R.id.recyclerActividades);

        textActividad = findViewById(R.id.textActividad);
        textArea = findViewById(R.id.textArea);
        textGrado = findViewById(R.id.textGrado);
        cardNuevaActividad = findViewById(R.id.cardNuevaActividad);
        cardSelecActividad = findViewById(R.id.cardSelecActividad);
        cardSelecGrado = findViewById(R.id.cardSelecGrado);
        cardSelecArea = findViewById(R.id.cardSelecArea);

        cardNuevaActividad.setOnClickListener(onClickListener);
        cardSelecActividad.setOnClickListener(onClickListener);



        cardSelecGrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
                final String[] tipo  = new String[]{"A","B","C"};
                builder.setTitle("Seleccionar Grado").setItems(tipo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textGrado.setText(tipo[which]);
                        grado = tipo[which];
                    }
                });
                builder.show();
            }
        });
        cardSelecArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
                final String[] tipo  = new String[]{"Lengua","Matematicas","Naturales","Sociales","Otros"};
                builder.setTitle("Seleccionar Área").setItems(tipo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textArea.setText(tipo[which]);
                        area = tipo[which];
                    }
                });
                builder.show();
            }
        });
    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
            final String[] tipo  = new String[]{"Escritura de Palabras","Escritura de Oracion","Numero Faltante Medio","Resta de Numeros","Suma de Numeros","Colocar Imagen","Letra Faltante","Abecedario"};
            builder.setTitle("Seleccionar Actividad").setItems(tipo, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (tipo[which]){
                        case "Abecedario":
                            if (view.getId()==R.id.cardNuevaActividad){
                                if (!textGrado.getText().toString().equals("Seleccionar Grado") ){
                                    ingresoAbecedario();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"SELECCIONE GRADO",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                textActividad.setText(tipo[which]);
                                cargarActividad();
                            }
                            break;
                        case "Letra Faltante":
                            if (view.getId()==R.id.cardNuevaActividad){
                                if (!textGrado.getText().toString().equals("Seleccionar Grado")){
                                    ingresoLetraFaltante();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"SELECCIONE GRADO",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                textActividad.setText(tipo[which]);
                                cargarActividad();
                            }
                            break;
                        case "Escritura de Palabras":
                            if (view.getId()==R.id.cardNuevaActividad){
                                if (!textGrado.getText().toString().equals("Seleccionar Grado") && !textArea.getText().toString().equals("Seleccionar Area")){
                                    ingresoEscrituraPalabra();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"SELECCIONE GRADO y área",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                textActividad.setText(tipo[which]);
                                cargarActividad();
                            }
                            break;
                        case "Escritura de Oracion":
                            if (view.getId()==R.id.cardNuevaActividad ){
                                if (!textGrado.getText().toString().equals("Seleccionar Grado") && !textArea.getText().toString().equals("Seleccionar Area")){
                                    ingresoEscrituraOracion();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"SELECCIONE GRADO y ÁREA",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                textActividad.setText(tipo[which]);
                                cargarActividad();
                            }
                            break;
                        case "Numero Faltante Medio":
                            if (view.getId()==R.id.cardNuevaActividad){
                                if (!textGrado.getText().toString().equals("Seleccionar Grado")){
                                    ingresoNumeroFaltante();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"SELECCIONE GRADO",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                textActividad.setText(tipo[which]);
                                cargarActividad();
                            }
                            break;
                        case "Suma de Numeros":
                            if (view.getId()==R.id.cardNuevaActividad ){
                                if (!textGrado.getText().toString().equals("Seleccionar Grado")){
                                    ingresoSumandoNumeros(1);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"SELECCIONE GRADO",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                textActividad.setText(tipo[which]);
                                cargarActividad();
                            }
                            break;
                        case "Resta de Numeros":
                            if (view.getId()==R.id.cardNuevaActividad ){
                                if (!textGrado.getText().toString().equals("Seleccionar Grado")){
                                    ingresoSumandoNumeros(-1);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"SELECCIONE GRADO",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                textActividad.setText(tipo[which]);
                                cargarActividad();
                            }
                            break;
                        case "Colocar Imagen":
                            if (view.getId()==R.id.cardNuevaActividad){
                                if (!textGrado.getText().toString().equals("Seleccionar Grado")){
                                    ingresoColocarImagen();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"SELECCIONE GRADO",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                textActividad.setText(tipo[which]);
                                cargarActividad();
                            }
                            break;
                    }
                }
            });
            builder.show();

        }
    };


    //------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------

    private void ingresoAbecedario(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
        View viewInflated = LayoutInflater.from(ConfiguracionActivity.this).inflate(R.layout.z_abecedario, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textNombre1 = viewInflated.findViewById(R.id.textNombre1);
        TextView textNombre2 = viewInflated.findViewById(R.id.textNombre2);
        TextView textNombre3 = viewInflated.findViewById(R.id.textNombre3);
        TextView textNombre4 = viewInflated.findViewById(R.id.textNombre4);
        TextView textNombre5 = viewInflated.findViewById(R.id.textNombre5);
        TextView textNombre6 = viewInflated.findViewById(R.id.textNombre6);
        TextView textComenzarAgregar = viewInflated.findViewById(R.id.textComenzarAgregar);
        TextView textLetra = viewInflated.findViewById(R.id.textLetra);

        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);

        textComenzarAgregar.setText("Agregar");

        imagen01Ab = viewInflated.findViewById(R.id.imagen1);
        imagen02Ab = viewInflated.findViewById(R.id.imagen2);
        imagen03Ab = viewInflated.findViewById(R.id.imagen3);
        imagen04Ab = viewInflated.findViewById(R.id.imagen4);
        imagen05Ab = viewInflated.findViewById(R.id.imagen5);
        imagen06Ab = viewInflated.findViewById(R.id.imagen6);

        imagen01Ab.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagen02Ab.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagen03Ab.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagen04Ab.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagen05Ab.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagen06Ab.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));

        imagen01Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imagenSeleccionada = "ima1Ab";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagen02Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imagenSeleccionada = "ima2Ab";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagen03Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imagenSeleccionada = "ima3Ab";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagen04Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "ima4Ab";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagen05Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "ima5Ab";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagen06Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "ima6Ab";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(textLetra.getText().toString())){
                    if (internet()){
                        //1 GUARDAR EN CONTROLLER
                        Abecedario abecedario = new Abecedario("",grado,textLetra.getText().toString(),ima1Ab,ima2Ab,ima3Ab,ima4Ab,ima5Ab,ima6Ab,
                                textNombre1.getText().toString(),textNombre2.getText().toString(),textNombre3.getText().toString(),textNombre4.getText().toString()
                                ,textNombre5.getText().toString(),textNombre6.getText().toString());

                        long numeroID = MainActivity.controller.nuevaAbecedario(abecedario);
                        //2 GUARDAR EN BD
                        abecedario.setId(Math.toIntExact(numeroID));
                        abecedario.setPath1(linkImagen1Ab);
                        abecedario.setPath2(linkImagen2Ab);
                        abecedario.setPath3(linkImagen3Ab);
                        abecedario.setPath4(linkImagen4Ab);
                        abecedario.setPath5(linkImagen5Ab);
                        abecedario.setPath6(linkImagen6Ab);
                        MainActivity.basedatos.collection("Abecedario").document(String.valueOf(abecedario.getId())).set(abecedario);
                        Toast.makeText(getApplicationContext(), "Se agregaro Actividad", Toast.LENGTH_LONG).show();
                        actualizarEstadoAlumnos();
                        band = false;
                    }else{
                        Toast.makeText(getApplicationContext(),"SIN CONEXIÓN",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Complete Campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }


    private void ingresoLetraFaltante(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
        View viewInflated = LayoutInflater.from(ConfiguracionActivity.this).inflate(R.layout.z_letra_faltante, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textPalabraCompleta = viewInflated.findViewById(R.id.textPalabraCompleta);
        TextView textPalabraIncompleta = viewInflated.findViewById(R.id.textPalabraIncompleta);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);
        imagenLetraFaltante = viewInflated.findViewById(R.id.imagenLetraFaltante);
        TextView textComenzarAgregar = viewInflated.findViewById(R.id.textComenzarAgregar);

        imagenLetraFaltante.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));

        textComenzarAgregar.setText("Agregar");

        imagenLetraFaltante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imagenLetraFaltante";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(textPalabraCompleta.getText().toString()) && !TextUtils.isEmpty(textPalabraIncompleta.getText().toString())){
                    if (internet()){
                        //1 GUARDAR EN CONTROLLER
                        LetraFaltante letraFaltante = new LetraFaltante(grado,"",textPalabraCompleta.getText().toString(),textPalabraIncompleta.getText().toString(),imagenLetraFaltanteString);

                        long numeroID = MainActivity.controller.nuevaLetraFaltante(letraFaltante);
                        //2 GUARDAR EN BD
                        letraFaltante.setPath(linkLetraFaltante);
                        letraFaltante.setId(Math.toIntExact(numeroID));

                        MainActivity.basedatos.collection("LetraFaltante").document(String.valueOf(letraFaltante.getId())).set(letraFaltante);
                        Toast.makeText(getApplicationContext(), "Se agregó Letra Faltante", Toast.LENGTH_LONG).show();
                        actualizarEstadoAlumnos();

                        //link = "vacio";
                        band = false;
                    }else{
                        Toast.makeText(getApplicationContext(),"SIN CONEXIÓN",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Complete Campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    private void ingresoEscrituraPalabra(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
        View viewInflated = LayoutInflater.from(ConfiguracionActivity.this).inflate(R.layout.z_escritura_de_palabras, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textPalabra = viewInflated.findViewById(R.id.textPalabra);
        TextView textComenzarAgregar = viewInflated.findViewById(R.id.textComenzarAgregar);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);
        imagenPalabra = viewInflated.findViewById(R.id.imagenPalabra);

        textComenzarAgregar.setText("Agregar");

        imagenPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imagenPalabra";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(MainActivity.scaleUp);
                if(!TextUtils.isEmpty(textPalabra.getText().toString())){
                    if (internet()){
                        //1 GUARDAR EN CONTROLLER
                        Palabra palabra = new Palabra(textPalabra.getText().toString(),area,grado,pathImgPalabra);
                        long numeroID = MainActivity.controller.nuevaPalabra(palabra);
                        //2 GUARDAR EN BD
                        palabra.setPathImagen(link);
                        palabra.setId(Math.toIntExact(numeroID));
                        MainActivity.basedatos.collection("Palabras").document(String.valueOf(palabra.getId())).set(palabra);
                        Toast.makeText(getApplicationContext(), "Se agregó Palabra", Toast.LENGTH_LONG).show();
                        actualizarEstadoAlumnos();

                        //link = "vacio";
                        band = false;
                    }else{
                        Toast.makeText(getApplicationContext(),"SIN CONEXIÓN",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Complete Campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
    private void ingresoEscrituraOracion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
        View viewInflated = LayoutInflater.from(ConfiguracionActivity.this).inflate(R.layout.z_escritura_de_oracion, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textOracion = viewInflated.findViewById(R.id.textOracion);
        TextView textComenzarAgregar = viewInflated.findViewById(R.id.textComenzarAgregar);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);

        imagenO1 = viewInflated.findViewById(R.id.imagenO1);
        imagenO2 = viewInflated.findViewById(R.id.imagenO2);
        imagenO3 = viewInflated.findViewById(R.id.imagenO3);
        imagenO4 = viewInflated.findViewById(R.id.imagenO4);
        imagenO5 = viewInflated.findViewById(R.id.imagenO5);
        imagenO6 = viewInflated.findViewById(R.id.imagenO6);

        imagenO1.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagenO2.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagenO3.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagenO4.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagenO5.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagenO6.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));

        imagenO1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imgO1";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagenO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imgO2";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagenO3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imgO3";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagenO4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imgO4";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagenO5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imgO5";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagenO6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imgO6";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });

        textComenzarAgregar.setText("Agregar");

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(MainActivity.scaleUp);
                if(!TextUtils.isEmpty(textOracion.getText().toString())){
                    if (internet()){
                        //1 GUARDAR EN CONTROLLER
                        Palabra palabra = new Palabra(textOracion.getText().toString(),area,grado,"");

                        palabra.setPath1(imO1);
                        palabra.setPath2(imO2);
                        palabra.setPath3(imO3);
                        palabra.setPath4(imO4);
                        palabra.setPath5(imO5);
                        palabra.setPath6(imO6);

                        long numeroID = MainActivity.controller.nuevaOracion(palabra);
                        palabra.setId(Math.toIntExact(numeroID));
                        //2 GUARDAR EN BD
                        palabra.setPath1(linkImagenO1);
                        palabra.setPath2(linkImagenO2);
                        palabra.setPath3(linkImagenO3);
                        palabra.setPath4(linkImagenO4);
                        palabra.setPath5(linkImagenO5);
                        palabra.setPath6(linkImagenO6);

                        MainActivity.basedatos.collection("Oraciones").document(String.valueOf(palabra.getId())).set(palabra);
                        Toast.makeText(getApplicationContext(), "Se agregó Oración", Toast.LENGTH_LONG).show();
                        actualizarEstadoAlumnos();
                    }else{
                        Toast.makeText(getApplicationContext(),"SIN CONEXIÓN",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Complete Campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    private void ingresoNumeroFaltante(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
        View viewInflated = LayoutInflater.from(ConfiguracionActivity.this).inflate(R.layout.z_numero_faltante, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textNum1 = viewInflated.findViewById(R.id.textNum1);
        TextView textNum2 = viewInflated.findViewById(R.id.textNum2);
        TextView textNum3 = viewInflated.findViewById(R.id.textNum3);
        TextView textNum4 = viewInflated.findViewById(R.id.textNum4);
        TextView textNum5 = viewInflated.findViewById(R.id.textNum5);
        TextView textNum6 = viewInflated.findViewById(R.id.textNum6);
        TextView textNum7 = viewInflated.findViewById(R.id.textNum7);
        TextView textNum8 = viewInflated.findViewById(R.id.textNum8);

        TextView textComenzarAgregar = viewInflated.findViewById(R.id.textComenzarAgregar);

        TextView textNumero1 = viewInflated.findViewById(R.id.textNumero1);
        TextView textNumero2 = viewInflated.findViewById(R.id.textNumero2);
        TextView textNumero3 = viewInflated.findViewById(R.id.textNumero3);
        TextView textNumero4 = viewInflated.findViewById(R.id.textNumero4);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);

        textComenzarAgregar.setText("Agregar");

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(textNum1.getText().toString()) &&
                        !TextUtils.isEmpty(textNum2.getText().toString()) &&
                        !TextUtils.isEmpty(textNum3.getText().toString()) &&
                        !TextUtils.isEmpty(textNum4.getText().toString()) &&
                        !TextUtils.isEmpty(textNum5.getText().toString()) &&
                        !TextUtils.isEmpty(textNum6.getText().toString()) &&
                        !TextUtils.isEmpty(textNum7.getText().toString()) &&
                        !TextUtils.isEmpty(textNum8.getText().toString()) &&

                        !TextUtils.isEmpty(textNumero1.getText().toString()) &&
                        !TextUtils.isEmpty(textNumero2.getText().toString()) &&
                        !TextUtils.isEmpty(textNumero3.getText().toString()) &&
                        !TextUtils.isEmpty(textNumero4.getText().toString())

                ){
                    if (internet()){
                        //1 GUARDAR EN CONTROLLER
                        NumeroFaltante numero = new NumeroFaltante(grado,"",textNum1.getText().toString(),textNumero1.getText().toString(),textNum2.getText().toString()
                                ,textNum3.getText().toString(),textNumero2.getText().toString(),textNum4.getText().toString(),textNum5.getText().toString()
                                ,textNumero3.getText().toString(),textNum6.getText().toString(),textNum7.getText().toString(),textNumero4.getText().toString()
                                ,textNum8.getText().toString());

                        long numeroID = MainActivity.controller.nuevaNumeroFaltante(numero);
                        numero.setId(Math.toIntExact(numeroID));
                        //2 GUARDAR EN BD
                        MainActivity.basedatos.collection("NumeroFaltante").document(String.valueOf(numero.getId())).set(numero);
                        Toast.makeText(getApplicationContext(), "Se agregó Numero", Toast.LENGTH_LONG).show();
                        actualizarEstadoAlumnos();
                    }else{
                        Toast.makeText(getApplicationContext(),"SIN CONEXIÓN",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Complete Campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
    private void ingresoSumandoNumeros(int operando){
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
            View viewInflated = LayoutInflater.from(ConfiguracionActivity.this).inflate(R.layout.z_suma_de_numeros, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textProblema = viewInflated.findViewById(R.id.textProblema);
        TextView textResultado = viewInflated.findViewById(R.id.textResultado);
        TextView textSumando1 = viewInflated.findViewById(R.id.textSumando1);
        TextView textSumando2 = viewInflated.findViewById(R.id.textSumando2);
        TextView textComenzarAgregar = viewInflated.findViewById(R.id.textComenzarAgregar);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);

        textComenzarAgregar.setText("Agregar");

        textSumando1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    textResultado.setText(String.valueOf(Integer.parseInt(textSumando1.getText().toString())+(Integer.parseInt(textSumando2.getText().toString()))*operando));
                }catch (Exception es){

                }
            }
        });
        textSumando2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    textResultado.setText(String.valueOf(Integer.parseInt(textSumando1.getText().toString())+(Integer.parseInt(textSumando2.getText().toString()))*operando));
                }catch (Exception es){

                }
            }
        });

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(textResultado.getText().toString()) && !TextUtils.isEmpty(textSumando1.getText().toString()) &&
                        !TextUtils.isEmpty(textSumando2.getText().toString()) ){

                    if (internet()){
                        //1 GUARDAR EN CONTROLLER
                        OperacionNumeros numero = new OperacionNumeros(grado,String.valueOf(operando),textSumando1.getText().toString(),textSumando2.getText().toString(),textProblema.getText().toString());
                        long numeroID = MainActivity.controller.nuevaOperacionNumero(numero);
                        //2 GUARDAR EN BD
                        numero.setId(Math.toIntExact(numeroID));
                        MainActivity.basedatos.collection("OperacionNumeros").document(String.valueOf(numero.getId())).set(numero);
                        Toast.makeText(getApplicationContext(), "Se agregó Numero", Toast.LENGTH_LONG).show();
                        actualizarEstadoAlumnos();
                    }else{
                        Toast.makeText(getApplicationContext(),"SIN CONEXIÓN",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Complete Campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }






    private void ingresoColocarImagen(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracionActivity.this);
        View viewInflated = LayoutInflater.from(ConfiguracionActivity.this).inflate(R.layout.z_colocar_imagen, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textNombre1 = viewInflated.findViewById(R.id.textNombre1);
        TextView textNombre2 = viewInflated.findViewById(R.id.textNombre2);
        TextView textNombre3 = viewInflated.findViewById(R.id.textNombre3);
        TextView textNombre4 = viewInflated.findViewById(R.id.textNombre4);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);

        imagen1 = viewInflated.findViewById(R.id.imagen1);
        imagen2 = viewInflated.findViewById(R.id.imagen2);
        imagen3 = viewInflated.findViewById(R.id.imagen3);
        imagen4 = viewInflated.findViewById(R.id.imagen4);

        imagen1.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagen2.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagen3.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));
        imagen4.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));

        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "img1";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "img2";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "img3";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });
        imagen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "img4";
                CropImage.activity().start(ConfiguracionActivity.this);
            }
        });

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if (band){
                    if(!TextUtils.isEmpty(textNombre1.getText().toString()) && !TextUtils.isEmpty(textNombre2.getText().toString()) &&
                            !TextUtils.isEmpty(textNombre3.getText().toString()) && !TextUtils.isEmpty(textNombre4.getText().toString()) &&
                            !im1.equals(null) && !im2.equals(null) &&
                            !im3.equals(null) && !im4.equals(null)){

                        if (internet()){
                            //1 GUARDAR EN CONTROLLER
                            ColocarImagenes colocarImagenes = new ColocarImagenes(grado,"",im1,im2,im3,im4,textNombre1.getText().toString(),textNombre2.getText().toString(),
                                    textNombre3.getText().toString(),textNombre4.getText().toString());
                            long numeroID = MainActivity.controller.nuevaColocarImagen(colocarImagenes);
                            //2 GUARDAR EN BD
                            colocarImagenes.setId(Math.toIntExact(numeroID));
                            colocarImagenes.setPath1(linkImagen1);
                            colocarImagenes.setPath2(linkImagen2);
                            colocarImagenes.setPath3(linkImagen3);
                            colocarImagenes.setPath4(linkImagen4);
                            MainActivity.basedatos.collection("ColocarImagenes").document(String.valueOf(colocarImagenes.getId())).set(colocarImagenes);
                            Toast.makeText(getApplicationContext(), "Se agregaron Imagenes", Toast.LENGTH_LONG).show();
                            actualizarEstadoAlumnos();
                            //linkImagen1 = "vacio";
                            //linkImagen2 = "vacio";
                            //linkImagen3 = "vacio";
                            //linkImagen4 = "vacio";
                            band = false;
                        }else{
                            Toast.makeText(getApplicationContext(),"SIN CONEXIÓN",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Complete Campos", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Espere porfavor", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }




    //------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------


    private void cargarActividad(){

        List<Palabra> palabras;
        List<OperacionNumeros> operacionNumeros;
        List<ColocarImagenes> colocarImagenes;
        List<com.example.mistalentosapp.Utiles.Objetos.NumeroFaltante> numeros;
        List<LetraFaltante> letraFaltantes;
        List<Abecedario> abecedarios;


        actividades = new ArrayList<>();
        adaptadorActividadConf = new AdaptadorActividadConf(actividades,getApplicationContext());
        recyclerActividades.setAdapter(adaptadorActividadConf);
        recyclerActividades.setLayoutManager(new LinearLayoutManager(this));
        ActividadConf actividadConf = new ActividadConf();

        switch (textActividad.getText().toString()){
            case "Abecedario":
                abecedarios = MainActivity.controller.obtenerAbecedario();
                for (Abecedario abecedario:abecedarios) {
                    if (abecedario.getGrado().equals(grado)){
                        actividadConf = new ActividadConf();
                        actividadConf.setGrado(abecedario.getGrado());
                        actividadConf.setArea(abecedario.getArea());
                        actividadConf.setContenido(abecedario.getContenido());
                        actividadConf.setID(abecedario.getId());
                        actividades.add(actividadConf);
                    }
                }
                break;
            case "Letra Faltante":
                letraFaltantes = MainActivity.controller.obtenerLetraFaltante();
                for (LetraFaltante letra:letraFaltantes) {
                    if (letra.getGrado().equals(grado)){
                        actividadConf = new ActividadConf();
                        actividadConf.setGrado(letra.getGrado());
                        actividadConf.setArea(letra.getArea());
                        actividadConf.setContenido(letra.getPalabraCompleta()+" | "+letra.getPalabra());
                        actividadConf.setID(letra.getId());
                        actividades.add(actividadConf);
                    }
                }
                break;
            case "Numero Faltante Medio":
                numeros = MainActivity.controller.obtenerNumeroFaltante();
                for (com.example.mistalentosapp.Utiles.Objetos.NumeroFaltante numero:numeros) {
                    if (numero.getGrado().equals(grado)){
                        actividadConf = new ActividadConf();
                        actividadConf.setGrado(numero.getGrado());
                        actividadConf.setArea(numero.getArea());
                        actividadConf.setContenido(numero.getSecuenciaNumeros());
                        actividadConf.setID(numero.getId());
                        actividades.add(actividadConf);
                    }
                }
                break;
            case "Suma de Numeros":
                operacionNumeros = MainActivity.controller.obtenerOperacionNumero();
                for (OperacionNumeros numero:operacionNumeros) {
                    if (numero.getGrado().equals(grado) && numero.getArea().equals("1")){
                        actividadConf = new ActividadConf();
                        actividadConf.setGrado(numero.getGrado());
                        actividadConf.setArea(numero.getArea());
                        actividadConf.setContenido(numero.getSecuenciaNumeros(1));
                        actividadConf.setID(numero.getId());
                        actividades.add(actividadConf);

                    }
                }
            case "Resta de Numeros":
                operacionNumeros = MainActivity.controller.obtenerOperacionNumero();
                for (OperacionNumeros numero:operacionNumeros) {
                    if (numero.getGrado().equals(grado) && numero.getArea().equals("-1")){
                        actividadConf = new ActividadConf();
                        actividadConf.setGrado(numero.getGrado());
                        actividadConf.setArea(numero.getArea());
                        actividadConf.setContenido(numero.getSecuenciaNumeros(1));
                        actividadConf.setID(numero.getId());
                        actividades.add(actividadConf);

                    }
                }
                break;



            case "Escritura de Oracion":
                palabras = MainActivity.controller.obtenerOracion();
                for (Palabra palabra:palabras) {
                    if (palabra.getGrado().equals(grado) && palabra.getArea().equals(area)){
                        actividadConf = new ActividadConf();
                        actividadConf.setGrado(palabra.getGrado());
                        actividadConf.setArea(palabra.getArea());
                        actividadConf.setContenido(palabra.getPalabra());
                        actividadConf.setID(palabra.getId());
                        actividades.add(actividadConf);
                    }
                }
                break;




            case "Escritura de Palabras":
                palabras = MainActivity.controller.obtenerPalabra();
                for (Palabra palabra:palabras) {
                    if (palabra.getGrado().equals(grado) && palabra.getArea().equals(area)){
                        actividadConf = new ActividadConf();
                        actividadConf.setGrado(palabra.getGrado());
                        actividadConf.setArea(palabra.getArea());
                        actividadConf.setContenido(palabra.getPalabra());
                        actividadConf.setID(palabra.getId());
                        actividades.add(actividadConf);
                    }
                }
                break;




            case "Colocar Imagen":
                colocarImagenes = MainActivity.controller.obtenerColocarImagen();
                for (ColocarImagenes imagen:colocarImagenes) {
                    if (imagen.getGrado().equals(grado)){
                        actividadConf = new ActividadConf();
                        actividadConf.setGrado(imagen.getGrado());
                        actividadConf.setArea(imagen.getArea());
                        actividadConf.setContenido(imagen.getSecuenciaImagenes());
                        actividadConf.setID(imagen.getId());
                        actividades.add(actividadConf);
                    }
                }
                break;
        }

        adaptadorActividadConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(ConfiguracionActivity.this);
                builder.setMessage("Desea eliminar actividad "+actividades.get(recyclerActividades.getChildAdapterPosition(view)).getContenido()+"?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = 0;
                        switch (textActividad.getText().toString()){
                            case "Letra Faltante":
                                id = actividades.get(recyclerActividades.getChildAdapterPosition(view)).getID();
                                MainActivity.controller.eliminarLetraFaltante(id);
                                MainActivity.basedatos.collection("LetraFaltante").document(String.valueOf(id)).delete();
                                break;
                            case "Escritura de Palabras":
                                id = actividades.get(recyclerActividades.getChildAdapterPosition(view)).getID();
                                MainActivity.controller.eliminarPalabra(id);
                                MainActivity.basedatos.collection("Palabras").document(String.valueOf(id)).delete();
                                break;
                            case "Escritura de Oracion":
                                id = actividades.get(recyclerActividades.getChildAdapterPosition(view)).getID();
                                MainActivity.controller.eliminarPalabra(id);
                                MainActivity.basedatos.collection("Oraciones").document(String.valueOf(id)).delete();
                                break;
                            case "Numero Faltante Medio":
                                id = actividades.get(recyclerActividades.getChildAdapterPosition(view)).getID();
                                MainActivity.controller.eliminarPalabra(id);
                                MainActivity.basedatos.collection("NumeroFaltante").document(String.valueOf(id)).delete();
                                break;
                            case "Suma de Numeros":
                                id = actividades.get(recyclerActividades.getChildAdapterPosition(view)).getID();
                                MainActivity.controller.eliminarPalabra(id);
                                MainActivity.basedatos.collection("OperacionNumeros").document(String.valueOf(id)).delete();
                                break;
                            case "Colocar Imagen":
                                id = actividades.get(recyclerActividades.getChildAdapterPosition(view)).getID();
                                MainActivity.controller.eliminarPalabra(id);
                                MainActivity.basedatos.collection("ColocarImagenes").document(String.valueOf(id)).delete();
                                break;
                        }
                        actualizarEstadoAlumnos();
                        Toast.makeText(getApplicationContext(),"Actividad Eliminada",Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();

            }
        });

    }






    private void actualizarEstadoAlumnos() {
        for (Alumno alumno:MainActivity.controller.obtenerAlumno()) {
            alumno.setEstado(false);
            MainActivity.basedatos.collection("Alumnos").document(alumno.getApellido()+alumno.getNombre()).set(alumno);
        }
    }
























    public Boolean internet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
        };
        return true;
    }



    //------------------------------------------------------------------------------------------------------------------------------------------------
    private void uploadToFirebase(Uri uri){
        try{
            StorageReference fileRef = MainActivity.reference.child("Imagenes/"+String.valueOf(System.currentTimeMillis())+".jpg");
            fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            System.out.println("POR SUBIDA");
                            switch (imagenSeleccionada){
                                case "imagenPalabra":
                                    link = uri.toString();
                                    break;
                                case "img1":
                                    linkImagen1 = uri.toString();
                                    break;
                                case "img2":
                                    linkImagen2 = uri.toString();
                                    break;
                                case "img3":
                                    linkImagen3 = uri.toString();
                                    break;
                                case "img4":
                                    linkImagen4 = uri.toString();
                                    break;
                                case "imgO1":
                                    linkImagenO1 = uri.toString();
                                    break;
                                case "imgO2":
                                    linkImagenO2 = uri.toString();
                                    break;
                                case "imgO3":
                                    linkImagenO3 = uri.toString();
                                    break;
                                case "imgO4":
                                    linkImagenO4 = uri.toString();
                                    break;
                                case "imgO5":
                                    linkImagenO5 = uri.toString();
                                    break;
                                case "imgO6":
                                    linkImagenO6 = uri.toString();
                                    break;
                                case "imagenLetraFaltante":
                                    linkLetraFaltante = uri.toString();
                                    break;
                                case "ima1Ab":
                                    linkImagen1Ab = uri.toString();
                                    break;
                                case "ima2Ab":
                                    linkImagen2Ab = uri.toString();
                                    break;
                                case "ima3Ab":
                                    linkImagen3Ab = uri.toString();
                                    break;
                                case "ima4Ab":
                                    linkImagen4Ab = uri.toString();
                                    break;
                                case "ima5Ab":
                                    linkImagen5Ab = uri.toString();
                                    break;
                                case "ima6Ab":
                                    linkImagen6Ab = uri.toString();
                                    break;
                            }
                            band = true;
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    Toast.makeText(getApplicationContext(), "Espere Porfavor...", Toast.LENGTH_SHORT).show();
                    System.out.println("ESPERE PORFAVOR");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    link = "fallo";
                    System.out.println("POR FALLO");
                }
            });
        }catch (Exception e){
            System.out.println(e.getMessage()+"ERROR");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                switch (imagenSeleccionada){
                    case "imagenPalabra":
                        Picasso.with(this).load(resultUri).into(imagenPalabra);
                        pathImgPalabra = resultUri.toString();
                        uploadToFirebase(Uri.parse(pathImgPalabra));
                        break;
                    case "img1":
                        Picasso.with(this).load(resultUri).into(imagen1);
                        im1 = resultUri.toString();
                        uploadToFirebase(Uri.parse(im1));
                        break;
                    case "img2":
                        Picasso.with(this).load(resultUri).into(imagen2);
                        im2 =  resultUri.toString();
                        uploadToFirebase(Uri.parse(im2));
                        break;
                    case "img3":
                        Picasso.with(this).load(resultUri).into(imagen3);
                        im3 =  resultUri.toString();
                        uploadToFirebase(Uri.parse(im3));
                        break;
                    case "img4":
                        Picasso.with(this).load(resultUri).into(imagen4);
                        im4 =  resultUri.toString();
                        uploadToFirebase(Uri.parse(im4));
                        break;
                    case "imgO1":
                        Picasso.with(this).load(resultUri).into(imagenO1);
                        imO1 = resultUri.toString();
                        break;
                    case "imgO2":
                        Picasso.with(this).load(resultUri).into(imagenO2);
                        imO2 =  resultUri.toString();
                        break;
                    case "imgO3":
                        Picasso.with(this).load(resultUri).into(imagenO3);
                        imO3 =  resultUri.toString();
                        break;
                    case "imgO4":
                        Picasso.with(this).load(resultUri).into(imagenO4);
                        imO4 =  resultUri.toString();
                        break;
                    case "imgO5":
                        Picasso.with(this).load(resultUri).into(imagenO5);
                        imO5 = resultUri.toString();
                        break;
                    case "imgO6":
                        Picasso.with(this).load(resultUri).into(imagenO6);
                        imO6 =  resultUri.toString();
                        break;
                    case "imagenLetraFaltante":
                        Picasso.with(this).load(resultUri).into(imagenLetraFaltante);
                        imagenLetraFaltanteString =  resultUri.toString();
                        break;
                    case "ima1Ab":
                        Picasso.with(this).load(resultUri).into(imagen01Ab);
                        ima1Ab =  resultUri.toString();
                        break;
                    case "ima2Ab":
                        Picasso.with(this).load(resultUri).into(imagen02Ab);
                        ima2Ab =  resultUri.toString();
                        break;
                    case "ima3Ab":
                        Picasso.with(this).load(resultUri).into(imagen03Ab);
                        ima3Ab =  resultUri.toString();
                        break;
                    case "ima4Ab":
                        Picasso.with(this).load(resultUri).into(imagen04Ab);
                        ima4Ab =  resultUri.toString();
                        break;
                    case "ima5Ab":
                        Picasso.with(this).load(resultUri).into(imagen05Ab);
                        ima5Ab =  resultUri.toString();
                        break;
                    case "ima6Ab":
                        Picasso.with(this).load(resultUri).into(imagen06Ab);
                        ima6Ab =  resultUri.toString();
                        break;


                }

            }
        }
    }
}