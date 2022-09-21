package com.example.mistalentosapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistalentosapp.Extras.ColocarImagen;
import com.example.mistalentosapp.Extras.ColocarPalabra;
import com.example.mistalentosapp.Lengua.AbecedarioAct;
import com.example.mistalentosapp.Lengua.EscrituraDeOraciones;
import com.example.mistalentosapp.Lengua.EscrituraDePalabras;
import com.example.mistalentosapp.Lengua.LetraFaltanteAct;
import com.example.mistalentosapp.Matematicas.NumeroFaltante;
import com.example.mistalentosapp.Matematicas.NumeroFaltanteLado;
import com.example.mistalentosapp.Matematicas.SumaDeNumeros;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorActividadConf;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorItem;
import com.example.mistalentosapp.Utiles.BaseDatos.Controller;
import com.example.mistalentosapp.Utiles.BaseDatos.DataBaseHelper;
import com.example.mistalentosapp.Utiles.Objetos.Abecedario;
import com.example.mistalentosapp.Utiles.Objetos.Actividad;
import com.example.mistalentosapp.Utiles.Objetos.ActividadConf;
import com.example.mistalentosapp.Utiles.Objetos.Alumno;
import com.example.mistalentosapp.Utiles.Objetos.ColocarImagenes;
import com.example.mistalentosapp.Utiles.Objetos.Item;
import com.example.mistalentosapp.Utiles.Objetos.LetraFaltante;
import com.example.mistalentosapp.Utiles.Objetos.OperacionNumeros;
import com.example.mistalentosapp.Utiles.Objetos.Palabra;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static Animation scaleUp,bounce,lefttoright,righttoleft,zoomin,zoomout,mixed,rotate,fadein,fadeout,sample,blink;
    private CardView cardEscrituraDeOraciones,cardSuma,
            cardNumeroFaltante,cardNumeroFaltanteCostados,
            cardColocarImagen,cardColocarPalabra,
            cardEscrituraDePalabras,
            cardEscrituraDeOracionesCSSociales,cardEscrituraDeOracionesCSNaturales,
            cardEscrituraDePalabrasCSSociales,cardEscrituraDePalabrasCSNaturales,
            cardResta,
            cardAbecedario,
            cardLetraFaltante;
    private String imagenSeleccionada;

    private ImageView imagen1, imagen2,imagen3,imagen4,imagenPalabra,imagenO1, imagenO2,imagenO3,imagenO4,imagenO5,imagenO6,imagenLetraFaltante,
            imagen01Ab,imagen02Ab,imagen03Ab,imagen04Ab,imagen05Ab,imagen06Ab;
    private String imagenPalabraStr="",im1="",im2="",im3="",im4="",imO1="",imO2="",imO3="",imO4="",imO5="",imO6="",imagenLetraFaltanteString="",
            ima1Ab="",ima2Ab="",ima3Ab="",ima4Ab="",ima5Ab="",ima6Ab = "";

    public static MediaPlayer mpGanar,mpPerder,a,b,c,d,e,f,g,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,y,z;
    private ImageView imageAlumnos,imageConfiguracion;

    public static Alumno alumnoSesion;
    public static Actividad actividadEnCurso = new Actividad();
    public static Controller controller;
    public static FirebaseFirestore basedatos;
    public static StorageReference reference  = FirebaseStorage.getInstance().getReference();

    public static SimpleDateFormat formatoddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");

    public static TextToSpeech textToSpeech;
    public static DownloadManager downloadManager;



    ImageView imageIcono;
    CardView cardTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scaleUp =  AnimationUtils.loadAnimation(this,R.anim.scale_up);
        bounce =  AnimationUtils.loadAnimation(this,R.anim.bounce);
        lefttoright =  AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        righttoleft =  AnimationUtils.loadAnimation(this,R.anim.righttoleft);
        zoomin =  AnimationUtils.loadAnimation(this,R.anim.zoomin);
        zoomout =  AnimationUtils.loadAnimation(this,R.anim.zoomout);
        rotate =  AnimationUtils.loadAnimation(this,R.anim.rotate);
        mixed =  AnimationUtils.loadAnimation(this,R.anim.mixed_anim);
        fadein =  AnimationUtils.loadAnimation(this,R.anim.fadein);
        fadeout =  AnimationUtils.loadAnimation(this,R.anim.fadeout);
        sample =  AnimationUtils.loadAnimation(this,R.anim.sample_anim);
        blink =  AnimationUtils.loadAnimation(this,R.anim.blink_anim);

        cardTitulo = findViewById(R.id.cardTitulo);
        imageIcono = findViewById(R.id.imageIcono);



        cardEscrituraDeOraciones = findViewById(R.id.cardEscrituraDeOraciones);
        cardEscrituraDeOracionesCSSociales = findViewById(R.id.cardEscrituraDeOracionesCSSociales);
        cardEscrituraDeOracionesCSNaturales = findViewById(R.id.cardEscrituraDeOracionesCSNaturales);

        cardSuma = findViewById(R.id.cardSuma);
        cardResta = findViewById(R.id.cardResta);

        cardNumeroFaltante = findViewById(R.id.cardNumeroFaltante);
        cardNumeroFaltanteCostados = findViewById(R.id.cardNumeroFaltanteCostados);

        cardColocarImagen = findViewById(R.id.cardColocarImagen);
        cardColocarPalabra = findViewById(R.id.cardColocarPalabra);

        cardEscrituraDePalabras = findViewById(R.id.cardEscrituraDePalabras);
        cardEscrituraDePalabrasCSSociales = findViewById(R.id.cardEscrituraDePalabrasCSSociales);
        cardEscrituraDePalabrasCSNaturales = findViewById(R.id.cardEscrituraDePalabrasCSNaturales);


        cardAbecedario = findViewById(R.id.cardAbecedario);
        cardLetraFaltante = findViewById(R.id.cardLetraFaltante);

        imageAlumnos = findViewById(R.id.imageAlumnos);
        imageConfiguracion = findViewById(R.id.imageConfiguracion);

        cardEscrituraDeOraciones.setOnClickListener(onClickListener);
        cardEscrituraDeOracionesCSNaturales.setOnClickListener(onClickListener);
        cardEscrituraDeOracionesCSSociales.setOnClickListener(onClickListener);


        cardAbecedario.setOnClickListener(onClickListener);
        cardLetraFaltante.setOnClickListener(onClickListener);


        cardSuma.setOnClickListener(onClickListener);
        cardResta.setOnClickListener(onClickListener);


        cardNumeroFaltante.setOnClickListener(onClickListener);
        cardNumeroFaltanteCostados.setOnClickListener(onClickListener);

        cardColocarImagen.setOnClickListener(onClickListener);
        cardColocarPalabra.setOnClickListener(onClickListener);

        cardEscrituraDePalabras.setOnClickListener(onClickListener);
        cardEscrituraDePalabrasCSSociales.setOnClickListener(onClickListener);
        cardEscrituraDePalabrasCSNaturales.setOnClickListener(onClickListener);



        mpGanar = MediaPlayer.create(this,R.raw.ganar);
        mpPerder = MediaPlayer.create(this,R.raw.perder);
        a = MediaPlayer.create(this,R.raw.a);
        b = MediaPlayer.create(this,R.raw.b);
        c = MediaPlayer.create(this,R.raw.c);
        d = MediaPlayer.create(this,R.raw.d);
        e = MediaPlayer.create(this,R.raw.e);
        f = MediaPlayer.create(this,R.raw.f);
        g = MediaPlayer.create(this,R.raw.g);
        i = MediaPlayer.create(this,R.raw.i);
        j = MediaPlayer.create(this,R.raw.j);
        k = MediaPlayer.create(this,R.raw.k);
        l = MediaPlayer.create(this,R.raw.l);
        m = MediaPlayer.create(this,R.raw.m);
        n = MediaPlayer.create(this,R.raw.n);
        o = MediaPlayer.create(this,R.raw.o);
        p = MediaPlayer.create(this,R.raw.p);
        q = MediaPlayer.create(this,R.raw.q);
        r = MediaPlayer.create(this,R.raw.r);
        s = MediaPlayer.create(this,R.raw.s);
        t = MediaPlayer.create(this,R.raw.t);
        u = MediaPlayer.create(this,R.raw.u);
        v = MediaPlayer.create(this,R.raw.v);
        w = MediaPlayer.create(this,R.raw.w);
        y = MediaPlayer.create(this,R.raw.y);
        z = MediaPlayer.create(this,R.raw.z);

        controller = new Controller(MainActivity.this);
        basedatos = FirebaseFirestore.getInstance();

        //PARA OTRO ALUMNITO SOLO SE DEJA LO SIGUIENTE Y SE COMENTA EL INICIO DE SESION:
        //SE INSTANCIA LA CLASE ALUMNO CON EL NOMBRE DEL CHANGUITO Y LISTO. ya no es null el alumno ... entra por manual o elem guard
        inicioSesion();

        /*
        alumnoSesion = new Alumno();
        alumnoSesion.setApellido("guaimas");
        alumnoSesion.setNombre("gonzalo");
        updateActividades();
         */



        imageAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlumnosActvity.class);
                startActivity(intent);
            }
        });
        imageConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConfiguracionActivity.class);
                startActivity(intent);
            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR){
                    Locale locSpanish = new Locale("spa", "MEX");
                    textToSpeech.setLanguage(locSpanish);
                    textToSpeech.setSpeechRate(0.85f);
                }
            }
        });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation=newConfig.orientation;
        try {
            switch(orientation) {
                case Configuration.ORIENTATION_LANDSCAPE:


                    break;
                case Configuration.ORIENTATION_PORTRAIT:


                    break;
            }
        }catch (Exception es){
            System.out.println("DASDASDA");
            System.out.println(es.getMessage());
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (alumnoSesion == null){
                inicioSesion();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final String[] tipo = new String[]{"Manual","Elementos Guardados"};
                builder.setTitle("Seleccionar Actividad").setItems(tipo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (tipo[which]){
                            case "Manual":
                                if(view.getId()==R.id.cardEscrituraDeOraciones || view.getId()==R.id.cardEscrituraDeOracionesCSNaturales || view.getId()==R.id.cardEscrituraDeOracionesCSSociales){
                                    cardEscrituraDeOraciones.startAnimation(fadein);
                                    ingresoEscrituraOracion(null);
                                }
                                if(view.getId()==R.id.cardEscrituraDePalabras || view.getId()==R.id.cardEscrituraDePalabrasCSNaturales || view.getId()==R.id.cardEscrituraDePalabrasCSSociales){
                                    cardEscrituraDePalabras.startAnimation(fadein);
                                    ingresoEscrituraPalabra(null);
                                }

                                switch (view.getId() ){
                                    case R.id.cardSuma:
                                        cardSuma.startAnimation(fadein);
                                        ingresoSumandoNumeros(null,1);
                                        break;
                                    case R.id.cardResta:
                                        cardResta.startAnimation(fadein);
                                        ingresoSumandoNumeros(null,-1);
                                        break;
                                    case R.id.cardNumeroFaltante:
                                        cardNumeroFaltante.startAnimation(fadein);
                                        ingresoNumeroFaltante(null,"medio");
                                        break;
                                    case R.id.cardNumeroFaltanteCostados:
                                        cardNumeroFaltante.startAnimation(fadein);
                                        ingresoNumeroFaltante(null,"lado");
                                        break;
                                    case R.id.cardColocarImagen:
                                        cardColocarImagen.startAnimation(fadein);
                                        ingresoColocarImagen(null,"imagen");
                                        break;
                                    case R.id.cardColocarPalabra:
                                        cardColocarImagen.startAnimation(fadein);
                                        ingresoColocarImagen(null,"palabra");
                                        break;
                                    case R.id.cardAbecedario:
                                        cardAbecedario.startAnimation(fadein);
                                        ingresoAbecedario(null);
                                        break;
                                    case R.id.cardLetraFaltante:
                                        cardLetraFaltante.startAnimation(fadein);
                                        ingresoLetraFaltante(null);
                                        break;
                                }
                                break;
                            case "Elementos Guardados":
                                try{
                                    switch (view.getId() ){
                                        //LENGUA-------------------------------------------------------------------------------------------------
                                        //NUEVOS
                                        case R.id.cardAbecedario:
                                            cardAbecedario.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Abecedario","Lengua");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Abecedario","Lengua");
                                            }
                                            break;
                                        case R.id.cardLetraFaltante:
                                            cardLetraFaltante.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("LetraFaltante","Lengua");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"LetraFaltante","Lengua");
                                            }
                                            break;
                                        //ESCRITURA DE ORACIONES-------------------------------------------------------------------------------------------------
                                        case R.id.cardEscrituraDeOraciones:
                                            cardEscrituraDeOraciones.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Escritura de Oracion","Lengua");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Escritura de Oracion","Lengua");
                                            }
                                            break;
                                        case R.id.cardEscrituraDeOracionesCSSociales:
                                            cardEscrituraDeOraciones.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Escritura de Oracion","Sociales");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Escritura de Oracion","Lengua");
                                            }
                                            break;
                                        case R.id.cardEscrituraDeOracionesCSNaturales:
                                            cardEscrituraDeOraciones.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Escritura de Oracion","Naturales");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Escritura de Oracion","Lengua");
                                            }
                                            break;
                                        //ESCRITURA DE PALABRAS-------------------------------------------------------------------------------------------------
                                        case R.id.cardEscrituraDePalabras:
                                            cardEscrituraDePalabras.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Escritura de Palabras","Lengua");
                                            }else {
                                                seleccionarEjemplo(alumnoSesion.getGrado(), "Escritura de Palabras", "Lengua");
                                            }
                                            break;
                                        case R.id.cardEscrituraDePalabrasCSNaturales:
                                            cardEscrituraDePalabras.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Escritura de Palabras","Naturales");
                                            }else {
                                                seleccionarEjemplo(alumnoSesion.getGrado(), "Escritura de Palabras", "Naturales");
                                            }
                                            break;
                                        case R.id.cardEscrituraDePalabrasCSSociales:
                                            cardEscrituraDePalabras.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Escritura de Palabras","Sociales");
                                            }else {
                                                seleccionarEjemplo(alumnoSesion.getGrado(), "Escritura de Palabras", "Sociales");
                                            }
                                            break;
                                        //MATEMÁTICAS-------------------------------------------------------------------------------------------------
                                        //SUMA-------------------------------------------------------------------------------------------------
                                        case R.id.cardSuma:
                                            cardSuma.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Suma de Numeros","");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Suma de Numeros","");
                                            }
                                            break;

                                        case R.id.cardResta:
                                            cardSuma.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Resta de Numeros","");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Resta de Numeros","");
                                            }
                                            break;
                                        //NUMERO FALTANTE-------------------------------------------------------------------------------------------------
                                        case R.id.cardNumeroFaltante:
                                            cardNumeroFaltante.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Numero Faltante Medio","");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Numero Faltante Medio","");
                                            }
                                            break;

                                        case R.id.cardNumeroFaltanteCostados:
                                            cardNumeroFaltante.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Numero Faltante Lado","");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Numero Faltante Lado","");
                                            }
                                            break;
                                        //OTROS-------------------------------------------------------------------------------------------------
                                        //COLOCAR IMAGEN-------------------------------------------------------------------------------------------------
                                        case R.id.cardColocarImagen:
                                            cardColocarImagen.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Colocar Imagen","");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Colocar Imagen","");
                                            }
                                            break;
                                        case R.id.cardColocarPalabra:
                                            cardColocarPalabra.startAnimation(fadein);
                                            if(alumnoSesion.getApellido().contains("-")){
                                                seleccionarGrado("Colocar Palabra","");
                                            }else{
                                                seleccionarEjemplo(alumnoSesion.getGrado(),"Colocar Palabra","");
                                            }
                                            break;
                                    }
                                }catch (Exception es){
                                    System.out.println(es.getMessage());
                                }

                                break;
                        }
                    }
                });
                builder.show();
            }

        }
    };

    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    private void inicioSesion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final String[] tipo  = new String[]{"Libre","Iniciar Sesión"};
        builder.setTitle("Seleccioná Tipo de Inicio de Sesión").setItems(tipo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               switch (tipo[which]){
                   case "Libre":

                       AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                       builder2.setTitle("Ingresar Nombre");
                       EditText input = new EditText(getApplicationContext());
                       input.setInputType(InputType.TYPE_CLASS_TEXT );
                       builder2.setView(input);
                       builder2.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               alumnoSesion = new Alumno( input.getText().toString(),"-","-","-","-");

                               cardTitulo.startAnimation(fadein);
                               imageIcono.startAnimation(fadein);
                               imageConfiguracion.startAnimation(fadein);
                               imageAlumnos.startAnimation(fadein);
                           }
                       });
                       builder2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.cancel();
                           }
                       });
                       builder2.show();


                       break;
                   case "Iniciar Sesión":
                       try{
                           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                           //final String[] tipo  = new String[10];
                           final String[] tipo  = new String[]{"","","","","","","","","","","","","","","","","","","",""};
                           int o = 0;

                           for (Alumno alumno: controller.obtenerAlumno()) {
                               tipo[o] = alumno.getApellido().toUpperCase()+" "+alumno.getNombre();
                               o++;
                           }
                           builder.setTitle("Seleccioná Alumno").setItems(tipo, new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int which) {
                                   for (Alumno alumno: controller.obtenerAlumno()) {
                                       if ((alumno.getApellido().toUpperCase()+" "+alumno.getNombre()).equals(tipo[which])){
                                           alumnoSesion = alumno;
                                           cardTitulo.startAnimation(fadein);
                                           imageIcono.startAnimation(fadein);
                                           imageConfiguracion.startAnimation(fadein);
                                           imageAlumnos.startAnimation(fadein);
                                           break;
                                       }
                                   }
                               }
                           });
                           builder.show();
                       }catch (Exception es){
                           System.out.println("AAAAAAAAAAA");
                           System.out.println(es.getMessage());
                       }

                       break;
               }


            }
        });
        builder.show();
    }

    private void seleccionarGrado(String actividad,String area){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final String[] tipo  = new String[]{"A","B","C"};
        builder.setTitle("Seleccioná Grado").setItems(tipo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                seleccionarEjemplo(tipo[which],actividad,area);
            }
        });
        builder.show();
    }




    private void seleccionarEjemplo(String grado,String actividad,String area){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.z_seleccionar_item, (ViewGroup) findViewById(android.R.id.content), false);
        RecyclerView recyclerView = viewInflated.findViewById(R.id.recyclerView);

        List<Item> items = new ArrayList<>();
        Item item;

        switch (actividad){
            case "Abecedario":
                for (Abecedario abecedario:controller.obtenerAbecedario()) {
                    if (abecedario.getGrado().equals(grado)){
                        item = new Item(abecedario.getId(),abecedario.getContenido());
                        items.add(item);
                    }
                }
                break;
            case "LetraFaltante":
                for (LetraFaltante letraFaltante:controller.obtenerLetraFaltante()) {
                    if (letraFaltante.getGrado().equals(grado)){
                        item = new Item(letraFaltante.getId(),letraFaltante.getPalabraCompleta());
                        items.add(item);
                    }
                }
                break;
            case "Numero Faltante Medio":
            case "Numero Faltante Lado":
                for (com.example.mistalentosapp.Utiles.Objetos.NumeroFaltante numero:controller.obtenerNumeroFaltante()) {
                    if (numero.getGrado().equals(grado)){
                        item = new Item(numero.getId(),numero.getSecuenciaNumeros());
                        items.add(item);
                    }
                }
                break;
            case "Resta de Numeros":
                for (OperacionNumeros numero:controller.obtenerOperacionNumero()) {
                    if (numero.getGrado().equals(grado) && numero.getArea().equals("-1")){
                        item = new Item(numero.getId(),numero.getSecuenciaNumeros(-1));
                        items.add(item);
                    }
                }
                break;
            case "Suma de Numeros":
                for (OperacionNumeros numero:controller.obtenerOperacionNumero()) {
                    if (numero.getGrado().equals(grado) && numero.getArea().equals("1")){
                        item = new Item(numero.getId(),numero.getSecuenciaNumeros(1));
                        items.add(item);
                    }
                }
                break;
            case "Escritura de Oracion":
                for (Palabra palabra:controller.obtenerOracion()) {
                    if (palabra.getGrado().equals(grado)  && palabra.getArea().equals(area)){
                        item = new Item(palabra.getId(),palabra.getPalabra());
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println(palabra.getPath6());

                        items.add(item);
                    }
                }
                break;
            case "Escritura de Palabras":
                for (Palabra palabra:controller.obtenerPalabra()) {
                    if (palabra.getGrado().equals(grado) && palabra.getArea().equals(area)){
                        item = new Item(palabra.getId(),palabra.getPalabra());
                        items.add(item);
                    }
                }
                break;
            case "Colocar Imagen":
            case "Colocar Palabra":
                for (ColocarImagenes imagen:controller.obtenerColocarImagen()) {
                    if (imagen.getGrado().equals(grado) && imagen.getArea().equals(area)){
                        item = new Item(imagen.getId(),imagen.getSecuenciaImagenes());
                        items.add(item);
                    }
                }
                break;
        }

        AdaptadorItem adaptadorItem = new AdaptadorItem(items,getApplicationContext());
        recyclerView.setAdapter(adaptadorItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adaptadorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (actividad){
                    case "Numero Faltante Medio":
                        ingresoNumeroFaltante(controller.obtenerNumeroFaltante(items.get(recyclerView.getChildAdapterPosition(view)).getId()),"medio");
                        break;
                    case "Numero Faltante Lado":
                        ingresoNumeroFaltante(controller.obtenerNumeroFaltante(items.get(recyclerView.getChildAdapterPosition(view)).getId()),"lado");
                        break;
                    case "Resta de Numeros":
                        ingresoSumandoNumeros( controller.obtenerOperacionNumero(items.get(recyclerView.getChildAdapterPosition(view)).getId()),-1);
                        break;
                    case "Suma de Numeros":
                        ingresoSumandoNumeros(controller.obtenerOperacionNumero (items.get(recyclerView.getChildAdapterPosition(view)).getId()),1);
                        break;
                    case "Escritura de Oracion":
                        ingresoEscrituraOracion(controller.obtenerOracion(items.get(recyclerView.getChildAdapterPosition(view)).getId()));
                        break;
                    case "Escritura de Palabras":
                        ingresoEscrituraPalabra(controller.obtenerPalabra(items.get(recyclerView.getChildAdapterPosition(view)).getId()));
                        break;
                    case "Colocar Imagen":
                        ingresoColocarImagen(controller.obtenerColocarImagen(items.get(recyclerView.getChildAdapterPosition(view)).getId()), "imagen");
                        break;
                    case "Colocar Palabra":
                        ingresoColocarImagen(controller.obtenerColocarImagen(items.get(recyclerView.getChildAdapterPosition(view)).getId()), "palabra");
                        break;
                    case "LetraFaltante":
                        ingresoLetraFaltante(controller.obtenerLetraFaltante(items.get(recyclerView.getChildAdapterPosition(view)).getId()));
                        break;
                    case "Abecedario":
                        ingresoAbecedario(controller.obtenerAbecedario(items.get(recyclerView.getChildAdapterPosition(view)).getId()));
                        break;
                }
            }
        });

        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }





    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    private void ingresoAbecedario(Abecedario abecedario){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.z_abecedario, (ViewGroup) findViewById(android.R.id.content), false);

        TextView textNombre1 = viewInflated.findViewById(R.id.textNombre1);
        TextView textNombre2 = viewInflated.findViewById(R.id.textNombre2);
        TextView textNombre3 = viewInflated.findViewById(R.id.textNombre3);
        TextView textNombre4 = viewInflated.findViewById(R.id.textNombre4);
        TextView textNombre5 = viewInflated.findViewById(R.id.textNombre5);
        TextView textNombre6 = viewInflated.findViewById(R.id.textNombre6);
        TextView textLetra = viewInflated.findViewById(R.id.textLetra);

        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);

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


        if (abecedario!=null){
            try {

                textNombre1.setText(abecedario.getNombre1());
                textNombre2.setText(abecedario.getNombre2());
                textNombre3.setText(abecedario.getNombre3());
                textNombre4.setText(abecedario.getNombre4());
                textNombre5.setText(abecedario.getNombre5());
                textNombre6.setText(abecedario.getNombre6());

                textLetra.setText(abecedario.getLetra());

                imagen01Ab.setImageURI(Uri.parse(abecedario.getPath1()));
                imagen02Ab.setImageURI(Uri.parse(abecedario.getPath2()));
                imagen03Ab.setImageURI(Uri.parse(abecedario.getPath3()));
                imagen04Ab.setImageURI(Uri.parse(abecedario.getPath4()));
                imagen05Ab.setImageURI(Uri.parse(abecedario.getPath5()));
                imagen06Ab.setImageURI(Uri.parse(abecedario.getPath6()));

                ima1Ab = abecedario.getPath1();
                ima2Ab = abecedario.getPath2();
                ima3Ab = abecedario.getPath3();
                ima4Ab = abecedario.getPath4();
                ima5Ab = abecedario.getPath5();
                ima6Ab = abecedario.getPath6();
            }catch (Exception es){

            }
        }

        actividadEnCurso.setNombre("Abecedario");
        actividadEnCurso.setAlumno(alumnoSesion.getApellido()+alumnoSesion.getNombre());
        actividadEnCurso.setFecha(System.currentTimeMillis());
        actividadEnCurso.setInicio(System.currentTimeMillis());
        actividadEnCurso.setAciertos(0);
        actividadEnCurso.setFallos(0);

        imagen01Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen01Ab.startAnimation(scaleUp);
                imagenSeleccionada = "ima1Ab";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagen02Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen02Ab.startAnimation(scaleUp);
                imagenSeleccionada = "ima2Ab";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagen03Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen03Ab.startAnimation(scaleUp);
                imagenSeleccionada = "ima3Ab";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagen04Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen04Ab.startAnimation(scaleUp);
                imagenSeleccionada = "ima4Ab";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagen05Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen05Ab.startAnimation(scaleUp);
                imagenSeleccionada = "ima5Ab";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagen06Ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen04Ab.startAnimation(scaleUp);
                imagenSeleccionada = "ima6Ab";
                CropImage.activity().start(MainActivity.this);
            }
        });

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(scaleUp);
                if(!TextUtils.isEmpty(textLetra.getText().toString())){
                    actividadEnCurso.setContenido(textLetra.getText().toString()+" || "+
                            textNombre1.getText().toString()+" | "+
                            textNombre2.getText().toString()+" | "+
                            textNombre3.getText().toString()+" | "+
                            textNombre4.getText().toString()+" | "+
                            textNombre5.getText().toString()+" | "+
                            textNombre6.getText().toString());
                    try{
                        actividadEnCurso.setIdActividad(abecedario.getId());
                    }catch (Exception es){

                    }
                    Intent intent = new Intent(getApplicationContext(), AbecedarioAct.class);
                    intent = new Intent(getApplicationContext(), AbecedarioAct.class);

                    intent.putExtra("letra",textLetra.getText().toString() );
                    intent.putExtra("tipo", "Abecedario");
                    intent.putExtra("nombre1",textNombre1.getText().toString() );
                    intent.putExtra("img1", ima1Ab+"-"+textNombre1.getText().toString());
                    intent.putExtra("nombre2",textNombre2.getText().toString() );
                    intent.putExtra("img2", ima2Ab+"-"+textNombre2.getText().toString());
                    intent.putExtra("nombre3",textNombre3.getText().toString() );
                    intent.putExtra("img3", ima3Ab+"-"+textNombre3.getText().toString());
                    intent.putExtra("nombre4",textNombre4.getText().toString() );
                    intent.putExtra("img4", ima4Ab+"-"+textNombre4.getText().toString());
                    intent.putExtra("nombre5",textNombre5.getText().toString() );
                    intent.putExtra("img5", ima5Ab+"-"+textNombre4.getText().toString());
                    intent.putExtra("nombre6",textNombre6.getText().toString() );
                    intent.putExtra("img6", ima6Ab+"-"+textNombre4.getText().toString());


                    startActivity(intent);
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


    private void ingresoNumeroFaltante(com.example.mistalentosapp.Utiles.Objetos.NumeroFaltante numeroFaltante,String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.z_numero_faltante, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textNum1 = viewInflated.findViewById(R.id.textNum1);
        TextView textNum2 = viewInflated.findViewById(R.id.textNum2);
        TextView textNum3 = viewInflated.findViewById(R.id.textNum3);
        TextView textNum4 = viewInflated.findViewById(R.id.textNum4);
        TextView textNum5 = viewInflated.findViewById(R.id.textNum5);
        TextView textNum6 = viewInflated.findViewById(R.id.textNum6);
        TextView textNum7 = viewInflated.findViewById(R.id.textNum7);
        TextView textNum8 = viewInflated.findViewById(R.id.textNum8);

        TextView textNumero1 = viewInflated.findViewById(R.id.textNumero1);
        TextView textNumero2 = viewInflated.findViewById(R.id.textNumero2);
        TextView textNumero3 = viewInflated.findViewById(R.id.textNumero3);
        TextView textNumero4 = viewInflated.findViewById(R.id.textNumero4);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);

        if (numeroFaltante!=null){
            textNum1.setText(numeroFaltante.getA1());
            textNum2.setText(numeroFaltante.getA3());
            textNum3.setText(numeroFaltante.getB1());
            textNum4.setText(numeroFaltante.getB3());
            textNum5.setText(numeroFaltante.getC1());
            textNum6.setText(numeroFaltante.getC3());
            textNum7.setText(numeroFaltante.getD1());
            textNum8.setText(numeroFaltante.getD3());

            textNumero1.setText(numeroFaltante.getA2());
            textNumero2.setText(numeroFaltante.getB2());
            textNumero3.setText(numeroFaltante.getC2());
            textNumero4.setText(numeroFaltante.getD2());
        }

        if (tipo.equals("medio")){
            actividadEnCurso.setNombre("Numero Faltante Medio");
        }else{
            actividadEnCurso.setNombre("Numero Faltante Lado");
        }

        actividadEnCurso.setAlumno(alumnoSesion.getApellido()+alumnoSesion.getNombre());
        actividadEnCurso.setFecha(System.currentTimeMillis());
        actividadEnCurso.setInicio(System.currentTimeMillis());
        actividadEnCurso.setAciertos(0);
        actividadEnCurso.setFallos(0);

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(scaleUp);
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
                    actividadEnCurso.setContenido(textNum1.getText().toString()+"-"+textNumero1.getText().toString()+"-"+textNum2.getText().toString()
                            +"|"+textNum3.getText().toString()+"-"+textNumero2.getText().toString()+"-"+textNum4.getText().toString()
                            +"|"+textNum5.getText().toString()+"-"+textNumero3.getText().toString()+"-"+textNum6.getText().toString()
                            +"|"+textNum7.getText().toString()+"-"+textNumero4.getText().toString()+"-"+textNum8.getText().toString());
                    try{
                        //actividadEnCurso.setNomActividad("Numero Faltante");
                        actividadEnCurso.setIdActividad(numeroFaltante.getId());
                    }catch (Exception es){

                    }
                    Intent intent = new Intent(getApplicationContext(), NumeroFaltante.class);
                    if (tipo.equals("medio")){
                        intent = new Intent(getApplicationContext(), NumeroFaltante.class);
                    }else{
                        intent = new Intent(getApplicationContext(), NumeroFaltanteLado.class);
                    }

                    intent.putExtra("n1",textNum1.getText().toString());
                    intent.putExtra("n2",textNum2.getText().toString());
                    intent.putExtra("n3",textNum3.getText().toString());
                    intent.putExtra("n4",textNum4.getText().toString());
                    intent.putExtra("n5",textNum5.getText().toString());
                    intent.putExtra("n6",textNum6.getText().toString());
                    intent.putExtra("n7",textNum7.getText().toString());
                    intent.putExtra("n8",textNum8.getText().toString());

                    intent.putExtra("num1",textNumero1.getText().toString());
                    intent.putExtra("num2",textNumero2.getText().toString());
                    intent.putExtra("num3",textNumero3.getText().toString());
                    intent.putExtra("num4",textNumero4.getText().toString());
                    startActivity(intent);
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

    private void ingresoEscrituraOracion(Palabra palabra){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.z_escritura_de_oracion, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textOracion = viewInflated.findViewById(R.id.textOracion);
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


        if (palabra!=null){
            try {
                textOracion.setText(palabra.getPalabra());
                imagenO1.setImageURI(Uri.parse(palabra.getPath1()));
                imO1 = palabra.getPath1();
                imagenO2.setImageURI(Uri.parse(palabra.getPath2()));
                imO2 = palabra.getPath2();
                imagenO3.setImageURI(Uri.parse(palabra.getPath3()));
                imO3 = palabra.getPath3();
                imagenO4.setImageURI(Uri.parse(palabra.getPath4()));
                imO4 = palabra.getPath4();
                imagenO5.setImageURI(Uri.parse(palabra.getPath5()));
                imO5 = palabra.getPath5();
                imagenO6.setImageURI(Uri.parse(palabra.getPath6()));
                imO6 = palabra.getPath6();
            }catch (Exception es){

            }
        }

        actividadEnCurso.setNombre("Escritura de Oracion"); //VER
        actividadEnCurso.setAlumno(alumnoSesion.getApellido()+alumnoSesion.getNombre());
        actividadEnCurso.setFecha(System.currentTimeMillis());
        actividadEnCurso.setInicio(System.currentTimeMillis());
        actividadEnCurso.setAciertos(0);
        actividadEnCurso.setFallos(0);



        imagenO1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenO1.startAnimation(scaleUp);
                imagenSeleccionada = "imgO1";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagenO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenO2.startAnimation(scaleUp);
                imagenSeleccionada = "imgO2";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagenO3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenO3.startAnimation(scaleUp);
                imagenSeleccionada = "imgO3";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagenO4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenO4.startAnimation(scaleUp);
                imagenSeleccionada = "imgO4";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagenO5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenO5.startAnimation(scaleUp);
                imagenSeleccionada = "imgO5";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagenO6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenO6.startAnimation(scaleUp);
                imagenSeleccionada = "imgO6";
                CropImage.activity().start(MainActivity.this);
            }
        });



        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(scaleUp);
                if(!TextUtils.isEmpty(textOracion.getText().toString())){
                    actividadEnCurso.setContenido(textOracion.getText().toString());
                    try{
                        //actividadEnCurso.setNomActividad("Escritura de Oracion");
                        actividadEnCurso.setIdActividad(palabra.getId());
                    }catch (Exception es){

                    }

                    Intent intent = new Intent(getApplicationContext(), EscrituraDeOraciones.class);
                    intent.putExtra("oracion",textOracion.getText().toString());
                    intent.putExtra("img1", imO1);
                    intent.putExtra("img2", imO2);
                    intent.putExtra("img3", imO3);
                    intent.putExtra("img4", imO4);
                    intent.putExtra("img5", imO5);
                    intent.putExtra("img6", imO6);
                    startActivity(intent);
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

    private void ingresoEscrituraPalabra(Palabra palabra){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.z_escritura_de_palabras, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textPalabra = viewInflated.findViewById(R.id.textPalabra);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);
        imagenPalabra = viewInflated.findViewById(R.id.imagenPalabra);

        if (palabra!=null){
            try {
                imagenPalabra.setImageURI(Uri.parse(palabra.getPathImagen()));
                imagenPalabraStr = palabra.getPathImagen();
            }catch (Exception es){

            }
            textPalabra.setText(palabra.getPalabra());
        }

        imagenPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imagenPalabra";
                CropImage.activity().start(MainActivity.this);
            }
        });


        actividadEnCurso.setNombre("Escritura de Palabras"); //VER
        actividadEnCurso.setAlumno(alumnoSesion.getApellido()+alumnoSesion.getNombre());
        actividadEnCurso.setFecha(System.currentTimeMillis());
        actividadEnCurso.setInicio(System.currentTimeMillis());
        actividadEnCurso.setAciertos(0);
        actividadEnCurso.setFallos(0);


        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(scaleUp);
                if(!TextUtils.isEmpty(textPalabra.getText().toString())){
                    actividadEnCurso.setContenido(textPalabra.getText().toString());
                    try{
                        //actividadEnCurso.setNomActividad("Escritura de Palabra");
                        actividadEnCurso.setIdActividad(palabra.getId());
                    }catch (Exception es){

                    }
                    Intent intent = new Intent(getApplicationContext(), EscrituraDePalabras.class);
                    intent.putExtra("palabra",textPalabra.getText().toString());
                    try{
                        //intent.putExtra("img",palabra.getPathImagen());
                        intent.putExtra("img",imagenPalabraStr);
                    }catch (Exception es){

                    }
                    startActivity(intent);
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

    private void ingresoLetraFaltante(LetraFaltante letraFaltante){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.z_letra_faltante, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textPalabraCompleta = viewInflated.findViewById(R.id.textPalabraCompleta);
        TextView textPalabraIncompleta = viewInflated.findViewById(R.id.textPalabraIncompleta);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);
        imagenLetraFaltante = viewInflated.findViewById(R.id.imagenLetraFaltante);

        imagenLetraFaltante.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));

        if (letraFaltante!=null){
            try {
                imagenLetraFaltante.setImageURI(Uri.parse(letraFaltante.getPath()));
                imagenLetraFaltanteString = letraFaltante.getPath();
            }catch (Exception es){
            }
            textPalabraCompleta.setText(letraFaltante.getPalabraCompleta());
            textPalabraIncompleta.setText(letraFaltante.getPalabra());
        }

        imagenLetraFaltante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imagenLetraFaltante";
                CropImage.activity().start(MainActivity.this);
            }
        });

        actividadEnCurso.setNombre("Letra Faltante"); //VER
        actividadEnCurso.setAlumno(alumnoSesion.getApellido()+alumnoSesion.getNombre());
        actividadEnCurso.setFecha(System.currentTimeMillis());
        actividadEnCurso.setInicio(System.currentTimeMillis());
        actividadEnCurso.setAciertos(0);
        actividadEnCurso.setFallos(0);


        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(scaleUp);
                if(!TextUtils.isEmpty(textPalabraCompleta.getText().toString()) && !TextUtils.isEmpty(textPalabraIncompleta.getText().toString())){
                    actividadEnCurso.setContenido(textPalabraCompleta.getText().toString()+" | "+textPalabraIncompleta.getText().toString());
                    try{
                        //actividadEnCurso.setNomActividad("Letra Faltante");
                        actividadEnCurso.setIdActividad(letraFaltante.getId());
                    }catch (Exception es){

                    }
                    Intent intent = new Intent(getApplicationContext(), LetraFaltanteAct.class);
                    intent.putExtra("palabraCompleta",textPalabraCompleta.getText().toString().toUpperCase());
                    intent.putExtra("tipo","Abecedario");
                    intent.putExtra("palabraIncompleta",textPalabraIncompleta.getText().toString().toUpperCase());
                    try{
                        intent.putExtra("img",imagenLetraFaltanteString);
                    }catch (Exception es){

                    }
                    startActivity(intent);
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


    private void ingresoSumandoNumeros(OperacionNumeros operacionNumeros,int operando){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.z_suma_de_numeros, (ViewGroup) findViewById(android.R.id.content), false);
        TextView textResultado = viewInflated.findViewById(R.id.textResultado);
        TextView textSumando1 = viewInflated.findViewById(R.id.textSumando1);
        TextView textSumando2 = viewInflated.findViewById(R.id.textSumando2);
        TextView textProblema = viewInflated.findViewById(R.id.textProblema);
        CardView cardComenzar = viewInflated.findViewById(R.id.cardComenzar);

        if (operacionNumeros!=null){
            textSumando1.setText(operacionNumeros.getNumero1());
            textSumando2.setText(operacionNumeros.getNumero2());
            textResultado.setText(operacionNumeros.getResultado(operando));
            textProblema.setText(operacionNumeros.getProblema());
        }
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

        if (operando==1){
            actividadEnCurso.setNombre("Suma de Numeros");
        }else{
            actividadEnCurso.setNombre("Resta de Numeros");
        }

        actividadEnCurso.setAlumno(alumnoSesion.getApellido()+alumnoSesion.getNombre());
        actividadEnCurso.setFecha(System.currentTimeMillis());
        actividadEnCurso.setInicio(System.currentTimeMillis());
        actividadEnCurso.setAciertos(0);
        actividadEnCurso.setFallos(0);

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(scaleUp);
                if(!TextUtils.isEmpty(textResultado.getText().toString()) && !TextUtils.isEmpty(textSumando1.getText().toString()) &&
                        !TextUtils.isEmpty(textSumando2.getText().toString()) ){

                    if (operando == 1){
                        actividadEnCurso.setContenido(textSumando1.getText().toString()+"+"+textSumando2.getText().toString()
                                +"="+textResultado.getText().toString());
                    }else {
                        actividadEnCurso.setContenido(textSumando1.getText().toString()+"-"+textSumando2.getText().toString()
                                +"="+textResultado.getText().toString());
                    }
                    try{
                        //actividadEnCurso.setNomActividad("Suma de Numeros");
                        actividadEnCurso.setIdActividad(operacionNumeros.getId());
                    }catch (Exception es){

                    }

                    Intent intent = new Intent(getApplicationContext(), SumaDeNumeros.class);
                    intent.putExtra("resultado",textResultado.getText().toString());
                    intent.putExtra("sumando1",textSumando1.getText().toString());
                    intent.putExtra("sumando2",textSumando2.getText().toString());
                    intent.putExtra("problema",textProblema.getText().toString());
                    intent.putExtra("operando",operando);
                    startActivity(intent);
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

    private void ingresoColocarImagen(ColocarImagenes colocarImagenes,String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.z_colocar_imagen, (ViewGroup) findViewById(android.R.id.content), false);
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


        if (colocarImagenes!=null){
            try {
                imagen1.setImageURI(Uri.parse(colocarImagenes.getPath1()));
                imagen2.setImageURI(Uri.parse(colocarImagenes.getPath2()));
                imagen3.setImageURI(Uri.parse(colocarImagenes.getPath3()));
                imagen4.setImageURI(Uri.parse(colocarImagenes.getPath4()));
                textNombre1.setText(colocarImagenes.getNombre1());
                textNombre2.setText(colocarImagenes.getNombre2());
                textNombre3.setText(colocarImagenes.getNombre3());
                textNombre4.setText(colocarImagenes.getNombre4());
                im1 = colocarImagenes.getPath1();
                im2 = colocarImagenes.getPath2();
                im3 = colocarImagenes.getPath3();
                im4 = colocarImagenes.getPath4();
            }catch (Exception es){

            }
        }


        actividadEnCurso.setNombre("Colocar Imagen");
        actividadEnCurso.setAlumno(alumnoSesion.getApellido()+alumnoSesion.getNombre());
        actividadEnCurso.setFecha(System.currentTimeMillis());
        actividadEnCurso.setInicio(System.currentTimeMillis());
        actividadEnCurso.setAciertos(0);
        actividadEnCurso.setFallos(0);

        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen1.startAnimation(scaleUp);
                imagenSeleccionada = "img1";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen2.startAnimation(scaleUp);
                imagenSeleccionada = "img2";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen3.startAnimation(scaleUp);
                imagenSeleccionada = "img3";
                CropImage.activity().start(MainActivity.this);
            }
        });
        imagen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen4.startAnimation(scaleUp);
                imagenSeleccionada = "img4";
                CropImage.activity().start(MainActivity.this);
            }
        });

        cardComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardComenzar.startAnimation(scaleUp);
                if(!TextUtils.isEmpty(textNombre1.getText().toString()) && !TextUtils.isEmpty(textNombre2.getText().toString()) &&
                        !TextUtils.isEmpty(textNombre3.getText().toString()) && !TextUtils.isEmpty(textNombre4.getText().toString())){
                    actividadEnCurso.setContenido(textNombre1.getText().toString()+" "+textNombre2.getText().toString()+" "+textNombre3.getText().toString()+" "+textNombre4.getText().toString());
                    try{
                        //actividadEnCurso.setNomActividad("Colocar Imagen");
                        actividadEnCurso.setIdActividad(colocarImagenes.getId());
                    }catch (Exception es){

                    }
                    Intent intent = new Intent(getApplicationContext(), ColocarImagen.class);
                    if (tipo.equals("palabra")){
                        intent = new Intent(getApplicationContext(), ColocarPalabra.class);
                        actividadEnCurso.setNombre("Colocar Palabra"); //VER
                    }else{
                        intent = new Intent(getApplicationContext(), ColocarImagen.class);
                        actividadEnCurso.setNombre("Colocar Imagen"); //VER
                    }
                    intent.putExtra("nombre1",textNombre1.getText().toString() );
                    intent.putExtra("nombre2",textNombre2.getText().toString() );
                    intent.putExtra("nombre3",textNombre3.getText().toString() );
                    intent.putExtra("nombre4",textNombre4.getText().toString() );
                    intent.putExtra("img1", im1+"-"+textNombre1.getText().toString());
                    intent.putExtra("img2", im2+"-"+textNombre2.getText().toString());
                    intent.putExtra("img3", im3+"-"+textNombre3.getText().toString());
                    intent.putExtra("img4", im4+"-"+textNombre4.getText().toString());
                    startActivity(intent);
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

    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

    private void updateActividades(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        dataBaseHelper.eliminarDatosTablaAlumnos(dataBaseHelper.getWritableDatabase());
        basedatos.collection("Alumnos").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    if(documentSnapshot.getId().equalsIgnoreCase(alumnoSesion.getApellido()+alumnoSesion.getNombre()) ){
                        Alumno alumno = documentSnapshot.toObject(Alumno.class);
                        controller.nuevoAlumno(alumno);
                        if (alumno.getEstado()==false){
                            //BORRAR TABLAS
                            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                            dataBaseHelper.eliminarDatosTablas(dataBaseHelper.getWritableDatabase());
                            //GUARDAR NUEVOS DATOS DESCARGANDO LAS IMAGENES Y PONIENDOLE EL PATH DONDE SE DESCARGO
                            descargarActividades();
                            //ACTUALIZAR ESTADO DE ALUMNO
                            alumno.setEstado(true);
                            basedatos.collection("Alumnos").document(alumno.getApellido()+alumno.getNombre()).set(alumno);
                            break;
                        }

                        break;
                    }
                }

                for (Alumno alumno:controller.obtenerAlumno()) {
                    alumnoSesion = alumno;
                }
            }
        });



    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------







    public class DonwloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
                Toast.makeText(context,"Descarga Completada", Toast.LENGTH_LONG).show();
                // DO SOMETHING WITH THIS FILE
            }
        }
    }


    private void descargarActividades(){

        basedatos.collection("Palabras").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Palabra palabra = documentSnapshot.toObject(Palabra.class);

                    registerReceiver(new DonwloadCompleteReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(palabra.getPathImagen()));
                    request.setDescription("Descargando imagen");
                    request.setTitle("Descargando");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    long timeId = System.currentTimeMillis();
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, timeId+".jpg");
                    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                    palabra.setPathImagen("/storage/emulated/0/Download/"+timeId+".jpg");
                    controller.nuevaPalabra(palabra);

                }
            }
        });
        basedatos.collection("Oraciones").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    controller.nuevaOracion(documentSnapshot.toObject(Palabra.class));
                }
            }
        });
        basedatos.collection("NumeroFaltante").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    controller.nuevaNumeroFaltante(documentSnapshot.toObject(com.example.mistalentosapp.Utiles.Objetos.NumeroFaltante.class));
                }
            }
        });
        basedatos.collection("OperacionNumeros").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    controller.nuevaOperacionNumero(documentSnapshot.toObject(OperacionNumeros.class));
                }
            }
        });
        basedatos.collection("ColocarImagenes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    ColocarImagenes colocarImagen = documentSnapshot.toObject(ColocarImagenes.class);

                    registerReceiver(new DonwloadCompleteReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(colocarImagen.getPath1()));
                    request.setDescription("Descargando imagen");
                    request.setTitle("Descargando");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    long timeId = System.currentTimeMillis();
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, timeId+".jpg");
                    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                    colocarImagen.setPath1("/storage/emulated/0/Download/"+timeId+".jpg");


                    request = new DownloadManager.Request(Uri.parse(colocarImagen.getPath2()));
                    request.setDescription("Descargando imagen");
                    request.setTitle("Descargando");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    timeId = System.currentTimeMillis();
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, timeId+".jpg");
                    manager.enqueue(request);
                    colocarImagen.setPath2("/storage/emulated/0/Download/"+timeId+".jpg");


                    request = new DownloadManager.Request(Uri.parse(colocarImagen.getPath3()));
                    request.setDescription("Descargando imagen");
                    request.setTitle("Descargando");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    timeId = System.currentTimeMillis();
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, timeId+".jpg");
                    manager.enqueue(request);
                    colocarImagen.setPath3("/storage/emulated/0/Download/"+timeId+".jpg");


                    request = new DownloadManager.Request(Uri.parse(colocarImagen.getPath4()));
                    request.setDescription("Descargando imagen");
                    request.setTitle("Descargando");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    timeId = System.currentTimeMillis();
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, timeId+".jpg");
                    manager.enqueue(request);
                    colocarImagen.setPath4("/storage/emulated/0/Download/"+timeId+".jpg");

                    controller.nuevaColocarImagen(colocarImagen);
                }
            }
        });


    }


    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------

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
                        imagenPalabraStr = resultUri.toString();
                        break;
                    case "img1":
                        Picasso.with(this).load(resultUri).into(imagen1);
                        im1 = resultUri.toString();
                        break;
                    case "img2":
                        Picasso.with(this).load(resultUri).into(imagen2);
                        im2 =  resultUri.toString();
                        break;
                    case "img3":
                        Picasso.with(this).load(resultUri).into(imagen3);
                        im3 =  resultUri.toString();
                        break;
                    case "img4":
                        Picasso.with(this).load(resultUri).into(imagen4);
                        im4 =  resultUri.toString();
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
                        imO1 = resultUri.toString();
                        break;
                    case "imgO6":
                        Picasso.with(this).load(resultUri).into(imagenO6);
                        imO2 =  resultUri.toString();
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