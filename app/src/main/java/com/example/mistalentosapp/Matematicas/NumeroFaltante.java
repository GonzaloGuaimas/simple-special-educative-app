package com.example.mistalentosapp.Matematicas;

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

import com.example.mistalentosapp.Lengua.EscrituraDeOraciones;
import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;

import java.util.ArrayList;

public class NumeroFaltante extends AppCompatActivity {

    private ArrayList<String> listadoNumeros;
    private ArrayList<String> listado1,listado2,listado3,listado4;
    private RecyclerView recyclerNumeros,recycler1,recycler2,recycler3,recycler4;
    private TextView num1,num2,num3,num4,num5,num6,num7,num8;
    private AdaptadorPalabra adaptadorNumeros,adaptador1,adaptador2,adaptador3,adaptador4;

    private CardView cardRevisar,cardTerminarActividad;
    private Animation scaleUp;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_faltante);

        cardRevisar = findViewById(R.id.cardRevisar);
        cardTerminarActividad = findViewById(R.id.cardTerminarActividad);
        recyclerNumeros = findViewById(R.id.recyclerNumeros);

        recycler1 = findViewById(R.id.recycler1);
        recycler2 = findViewById(R.id.recycler2);
        recycler3 = findViewById(R.id.recycler3);
        recycler4 = findViewById(R.id.recycler4);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);

        scaleUp =  AnimationUtils.loadAnimation(this,R.anim.scale_up);

        bundle = getIntent().getExtras();

        num1.setText(bundle.getString("n1"));
        num2.setText(bundle.getString("n2"));
        num3.setText(bundle.getString("n3"));
        num4.setText(bundle.getString("n4"));
        num5.setText(bundle.getString("n5"));
        num6.setText(bundle.getString("n6"));
        num7.setText(bundle.getString("n7"));
        num8.setText(bundle.getString("n8"));

        num1.startAnimation(MainActivity.sample);
        num2.startAnimation(MainActivity.sample);
        num3.startAnimation(MainActivity.sample);
        num4.startAnimation(MainActivity.sample);
        num5.startAnimation(MainActivity.sample);
        num6.startAnimation(MainActivity.sample);
        num7.startAnimation(MainActivity.sample);
        num8.startAnimation(MainActivity.sample);


        cargarPalabras();

        cardTerminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actividadEnCurso.setFin(System.currentTimeMillis());
                MainActivity.actividadEnCurso.setDuracion(MainActivity.actividadEnCurso.obtenerDuracion());
                MainActivity.controller.nuevoActividad(MainActivity.actividadEnCurso);
                Toast.makeText(getApplicationContext(), "se guardo", Toast.LENGTH_SHORT).show();
                NumeroFaltante.this.finish();
            }
        });
        cardRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardRevisar.startAnimation(scaleUp);

                try{
                    String cad = num1.getText().toString()+" menor que "+listado1.get(0)+" menor que "+num2.getText().toString();
                    cad = cad +" "+ num3.getText().toString()+" menor que "+listado2.get(0)+" menor que "+num4.getText().toString();
                    cad = cad +" "+ num5.getText().toString()+" menor que "+listado3.get(0)+" menor que "+num6.getText().toString();
                    cad = cad +" "+ num7.getText().toString()+" menor que "+listado4.get(0)+" menor que "+num8.getText().toString();
                    //MainActivity.textToSpeech.speak(cad, TextToSpeech.QUEUE_FLUSH,null);

                    if (Integer.parseInt(num1.getText().toString()) < Integer.parseInt(listado1.get(0)) &&  Integer.parseInt(num2.getText().toString()) > Integer.parseInt(listado1.get(0)) &&
                            Integer.parseInt(num3.getText().toString()) < Integer.parseInt(listado2.get(0)) &&  Integer.parseInt(num4.getText().toString()) > Integer.parseInt(listado2.get(0)) &&
                            Integer.parseInt(num5.getText().toString()) < Integer.parseInt(listado3.get(0)) &&  Integer.parseInt(num6.getText().toString()) > Integer.parseInt(listado3.get(0)) &&
                            Integer.parseInt(num7.getText().toString()) < Integer.parseInt(listado4.get(0)) &&  Integer.parseInt(num8.getText().toString()) > Integer.parseInt(listado4.get(0))
                    ){
                        cartel("bien");
                    }else{
                        cartel("mal");
                    }
                }catch (Exception es){

                }

            }
        });
    }

    private void cargarPalabras(){

        listadoNumeros = new ArrayList<>();

        listado1 = new ArrayList<>();
        listado2 = new ArrayList<>();
        listado3 = new ArrayList<>();
        listado4 = new ArrayList<>();

        listadoNumeros.add(bundle.getString("num4"));
        listadoNumeros.add(bundle.getString("num1"));
        listadoNumeros.add(bundle.getString("num3"));
        listadoNumeros.add(bundle.getString("num2"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerNumeros.setLayoutManager(layoutManager);
        adaptadorNumeros = new AdaptadorPalabra(listadoNumeros,getApplicationContext());
        recyclerNumeros.setAdapter(adaptadorNumeros);
        recyclerNumeros.setOnDragListener(adaptadorNumeros.dragInstance);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler1.setLayoutManager(layoutManager2);
        adaptador1 = new AdaptadorPalabra(listado1,getApplicationContext());
        recycler1.setAdapter(adaptador1);
        recycler1.setOnDragListener(adaptador1.dragInstance);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler2.setLayoutManager(layoutManager3);
        adaptador2 = new AdaptadorPalabra(listado2,getApplicationContext());
        recycler2.setAdapter(adaptador2);
        recycler2.setOnDragListener(adaptador2.dragInstance);

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler3.setLayoutManager(layoutManager4);
        adaptador3 = new AdaptadorPalabra(listado3,getApplicationContext());
        recycler3.setAdapter(adaptador3);
        recycler3.setOnDragListener(adaptador3.dragInstance);

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler4.setLayoutManager(layoutManager5);
        adaptador4 = new AdaptadorPalabra(listado4,getApplicationContext());
        recycler4.setAdapter(adaptador4);
        recycler4.setOnDragListener(adaptador4.dragInstance);
    }



    private void cartel(String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(NumeroFaltante.this);
        View viewInflated = LayoutInflater.from(NumeroFaltante.this).inflate(R.layout.cartel, (ViewGroup) findViewById(android.R.id.content), false);
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