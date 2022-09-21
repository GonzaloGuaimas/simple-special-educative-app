package com.example.mistalentosapp.Lengua;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;

import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;
import com.example.mistalentosapp.Utiles.Objetos.Actividad;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;


public class EscrituraDePalabras extends AppCompatActivity {

    private Animation scaleUp;
    private TextView textPalabra;
    private ImageView imagePalabra;
    private Bundle bundle;
    private CardView cardLeer,cardTerminarActividad;

    private ArrayList<String> listadoPalabra,listadoPalabraArmada;
    RecyclerView recyclerPalDesarmada,recyclerPalArmada;
    AdaptadorPalabra adaptadorPalabra,adaptadorPalabraArmada;

    private String palabra = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escritura_de_palabras);

        scaleUp =  AnimationUtils.loadAnimation(this,R.anim.scale_up);
        textPalabra = findViewById(R.id.textPalabra);
        imagePalabra = findViewById(R.id.imagePalabra);
        recyclerPalArmada = findViewById(R.id.recyclerPalArmada);
        recyclerPalDesarmada = findViewById(R.id.recyclerPalDesarmada);
        cardLeer = findViewById(R.id.cardLeer);
        cardTerminarActividad = findViewById(R.id.cardTerminarActividad);

        bundle = getIntent().getExtras();
        palabra = bundle.getString("palabra").toUpperCase();
        textPalabra.setText(palabra.replace(" ",""));
        textPalabra.startAnimation(MainActivity.rotate);
        try{
            imagePalabra.setImageURI(Uri.parse(bundle.getString("img")));
            imagePalabra.startAnimation(MainActivity.fadein);
        }catch (Exception es){

        }

        cargarSilabas();

        cardTerminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actividadEnCurso.setFin(System.currentTimeMillis());
                MainActivity.actividadEnCurso.setDuracion(MainActivity.actividadEnCurso.obtenerDuracion());
                MainActivity.controller.nuevoActividad(MainActivity.actividadEnCurso);
                Toast.makeText(getApplicationContext(), "se guardo", Toast.LENGTH_SHORT).show();
                EscrituraDePalabras.this.finish();
            }
        });

        cardLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String palabraEscrita = "";
                for(int i=0;i<listadoPalabraArmada.size();i++){
                    palabraEscrita = palabraEscrita+listadoPalabraArmada.get(i);
                }
                MainActivity.textToSpeech.speak(palabraEscrita, TextToSpeech.QUEUE_FLUSH,null);
                if (palabraEscrita.equals(textPalabra.getText().toString())){
                    cartel("bien");
                }else{
                    cartel("mal");
                }
            }
        });
    }
    private void cargarSilabas(){
        listadoPalabra = new ArrayList<>();
        listadoPalabraArmada = new ArrayList<>();

        String[] silabas = palabra.split(" ");
        int i=0;
        while (i<silabas.length){
            try{
                listadoPalabra.add(silabas[i+1]);
            }catch (Exception es){

            }
            listadoPalabra.add(silabas[i]);
            i++;
            i++;
        }
        /*
        for(int i=0;i<silabas.length;i++){
            listadoPalabra.add(silabas[i]);
        }
         */

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerPalArmada.setLayoutManager(layoutManager);
        adaptadorPalabra = new AdaptadorPalabra(listadoPalabraArmada,getApplicationContext());
        recyclerPalArmada.setAdapter(adaptadorPalabra);
        recyclerPalArmada.setOnDragListener(adaptadorPalabra.dragInstance);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerPalDesarmada.setLayoutManager(layoutManager2);
        adaptadorPalabraArmada = new AdaptadorPalabra(listadoPalabra,getApplicationContext());
        recyclerPalDesarmada.setAdapter(adaptadorPalabraArmada);
        recyclerPalDesarmada.setOnDragListener(adaptadorPalabraArmada.dragInstance);
    }

    private void cartel(String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(EscrituraDePalabras.this);
        View viewInflated = LayoutInflater.from(EscrituraDePalabras.this).inflate(R.layout.cartel, (ViewGroup) findViewById(android.R.id.content), false);
        ImageView imagen = viewInflated.findViewById(R.id.imagen);
        TextView textTitulo = viewInflated.findViewById(R.id.textTitulo);
        TextView textFrase = viewInflated.findViewById(R.id.textFrase);
        switch (tipo){
            case "bien":
                textTitulo.setText("GENIAL");
                textFrase.setText("¡ Lo Lograste !");
                imagen.setBackgroundResource(R.drawable.sonriente);
                //MainActivity.mpGanar.start();
                MainActivity.actividadEnCurso.aumentarAcierto();
                break;
            case "mal":
                textTitulo.setText("UPS");
                textFrase.setText("¡ Intentalo de Nuevo !");
                imagen.setBackgroundResource(R.drawable.asustado);
                //MainActivity.mpPerder.start();
                MainActivity.actividadEnCurso.aumentarFallo();
                break;
        }
        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

}