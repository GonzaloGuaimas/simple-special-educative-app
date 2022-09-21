package com.example.mistalentosapp.Lengua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;

public class EscrituraDeOraciones extends AppCompatActivity {

    private ArrayList<String> listadoPalabras, listadoPalabras2,listadoImagenes; //lo que va en oracion
    RecyclerView recyclerPalabras,recyclerOración,recyclerImagenes;
    AdaptadorPalabra adaptadorPalabra,adaptadorPalabra2,adaptadorPalabra3;

    private CardView cardLeer,cardTerminarActividad;
    private Animation scaleUp;
    private Bundle bundle;
    private String oracion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escritura_de_oraciones);
        recyclerPalabras = findViewById(R.id.recyclerPalabras);
        recyclerOración = findViewById(R.id.recyclerOración);
        recyclerImagenes = findViewById(R.id.recyclerImagenes);
        cardLeer = findViewById(R.id.cardLeer);
        cardTerminarActividad = findViewById(R.id.cardTerminarActividad);
        scaleUp =  AnimationUtils.loadAnimation(this,R.anim.scale_up);

        bundle = getIntent().getExtras();
        oracion = bundle.getString("oracion").toUpperCase();

        cargarPalabras();



        cardTerminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actividadEnCurso.setFin(System.currentTimeMillis());
                MainActivity.actividadEnCurso.setDuracion(MainActivity.actividadEnCurso.obtenerDuracion());
                MainActivity.controller.nuevoActividad(MainActivity.actividadEnCurso);
                Toast.makeText(getApplicationContext(), "se guardo", Toast.LENGTH_SHORT).show();
                EscrituraDeOraciones.this.finish();
            }
        });


        cardLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardLeer.startAnimation(scaleUp);
                String oracionEscrita = "";
                for(int i=0;i<listadoPalabras2.size();i++){
                    oracionEscrita = oracionEscrita +" "+listadoPalabras2.get(i);
                }
                MainActivity.textToSpeech.speak(oracionEscrita, TextToSpeech.QUEUE_FLUSH,null);
                if (oracionEscrita.replace(" ","").equals(oracion.replace(" ",""))){
                    cartel("bien");
                }else{
                    cartel("mal");
                }

            }
        });
    }

    private void cargarPalabras(){
        listadoPalabras = new ArrayList<>();
        listadoPalabras2 = new ArrayList<>();
        listadoImagenes = new ArrayList<>();

        String[] particionOracion = oracion.split(" ");
        for(int i=0;i<particionOracion.length;i++){

            //listadoPalabras.add(particionOracion[i]);

        }

        int pos;
        int nCartas = particionOracion.length;
        Stack <Integer> pCartas = new Stack<Integer>();
        for (int i = 0; i < nCartas ; i++) {
            pos = (int) Math.floor(Math.random() * nCartas );
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * nCartas );
            }
            pCartas.push(pos);
        }

        for (int i=0;i<pCartas.size();i++){
            listadoPalabras.add(particionOracion[pCartas.get(i)]);
        }

        System.out.println("Núm. aleatorios sin repetición:");
        System.out.println(pCartas.toString());



        if (!bundle.getString("img1").equals("")){
            listadoImagenes.add(bundle.getString("img1"));
        }
        if (!bundle.getString("img2").equals("")){
            listadoImagenes.add(bundle.getString("img2"));
        }
        if (!bundle.getString("img3").equals("")){
            listadoImagenes.add(bundle.getString("img3"));
        }
        if (!bundle.getString("img4").equals("")){
            listadoImagenes.add(bundle.getString("img4"));
        }
        if (!bundle.getString("img5").equals("")){
            listadoImagenes.add(bundle.getString("img5"));
        }
        if (!bundle.getString("img6").equals("")){
            listadoImagenes.add(bundle.getString("img6"));
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerPalabras.setLayoutManager(layoutManager);
        adaptadorPalabra = new AdaptadorPalabra(listadoPalabras,getApplicationContext());
        recyclerPalabras.setAdapter(adaptadorPalabra);
        recyclerPalabras.setOnDragListener(adaptadorPalabra.dragInstance);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerOración.setLayoutManager(layoutManager2);
        adaptadorPalabra2 = new AdaptadorPalabra(listadoPalabras2,getApplicationContext());
        recyclerOración.setAdapter(adaptadorPalabra2);
        recyclerOración.setOnDragListener(adaptadorPalabra2.dragInstance);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerImagenes.setLayoutManager(layoutManager3);
        adaptadorPalabra3 = new AdaptadorPalabra(listadoImagenes,getApplicationContext());
        recyclerImagenes.setAdapter(adaptadorPalabra3);
        recyclerImagenes.setOnDragListener(adaptadorPalabra3.dragInstance);


    }


    private void cartel(String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(EscrituraDeOraciones.this);
        View viewInflated = LayoutInflater.from(EscrituraDeOraciones.this).inflate(R.layout.cartel, (ViewGroup) findViewById(android.R.id.content), false);
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