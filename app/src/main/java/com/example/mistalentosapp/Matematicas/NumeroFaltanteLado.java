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

import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;

import java.util.ArrayList;

public class NumeroFaltanteLado extends AppCompatActivity {

    private ArrayList<String> listadoNumeros;
    private ArrayList<String> listado1,listado2,listado3,listado4,
            listado5,listado6,listado7,listado8;
    private RecyclerView recyclerNumeros,
            recycler1,recycler2,recycler3,recycler4,
            recycler5,recycler6,recycler7,recycler8;
    private TextView num1,num2,num3,num4;
    private AdaptadorPalabra adaptadorNumeros,
            adaptador1,adaptador2,adaptador3,adaptador4,
            adaptador5,adaptador6,adaptador7,adaptador8;

    private CardView cardRevisar,cardTerminarActividad;
    private Animation scaleUp;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_faltante_lado);

        cardRevisar = findViewById(R.id.cardRevisar);
        cardTerminarActividad = findViewById(R.id.cardTerminarActividad);
        recyclerNumeros = findViewById(R.id.recyclerNumeros);

        recycler1 = findViewById(R.id.recycler1);
        recycler2 = findViewById(R.id.recycler2);
        recycler3 = findViewById(R.id.recycler3);
        recycler4 = findViewById(R.id.recycler4);
        recycler5 = findViewById(R.id.recycler5);
        recycler6 = findViewById(R.id.recycler6);
        recycler7 = findViewById(R.id.recycler7);
        recycler8 = findViewById(R.id.recycler8);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);

        scaleUp =  AnimationUtils.loadAnimation(this,R.anim.scale_up);

        bundle = getIntent().getExtras();

        num1.setText(bundle.getString("num1"));
        num2.setText(bundle.getString("num2"));
        num3.setText(bundle.getString("num3"));
        num4.setText(bundle.getString("num4"));

        num1.startAnimation(MainActivity.sample);
        num2.startAnimation(MainActivity.sample);
        num3.startAnimation(MainActivity.sample);
        num4.startAnimation(MainActivity.sample);



        cargarPalabras();

        cardTerminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actividadEnCurso.setFin(System.currentTimeMillis());
                MainActivity.actividadEnCurso.setDuracion(MainActivity.actividadEnCurso.obtenerDuracion());
                MainActivity.controller.nuevoActividad(MainActivity.actividadEnCurso);
                Toast.makeText(getApplicationContext(), "se guardo", Toast.LENGTH_SHORT).show();
                NumeroFaltanteLado.this.finish();
            }
        });
        cardRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardRevisar.startAnimation(scaleUp);
                try{
                    String cad = listado1.get(0)+" menor que "+num1.getText().toString()+" menor que "+listado2.get(0);
                    cad = cad +" "+ listado3.get(0)+" menor que "+num2.getText().toString()+" menor que "+listado4.get(0);
                    cad = cad +" "+ listado5.get(0)+" menor que "+num3.getText().toString()+" menor que "+listado6.get(0);
                    cad = cad +" "+ listado7.get(0)+" menor que "+num4.getText().toString()+" menor que "+listado8.get(0);
                    //MainActivity.textToSpeech.speak(cad, TextToSpeech.QUEUE_FLUSH,null);

                    if (Integer.parseInt(listado1.get(0)) < Integer.parseInt(num1.getText().toString()) &&  Integer.parseInt(listado2.get(0)) > Integer.parseInt(num1.getText().toString()) &&
                            Integer.parseInt(listado3.get(0)) < Integer.parseInt(num2.getText().toString()) &&  Integer.parseInt(listado4.get(0)) > Integer.parseInt(num2.getText().toString()) &&
                            Integer.parseInt(listado5.get(0)) < Integer.parseInt(num3.getText().toString()) &&  Integer.parseInt(listado6.get(0)) > Integer.parseInt(num3.getText().toString()) &&
                            Integer.parseInt(listado7.get(0)) < Integer.parseInt(num4.getText().toString()) &&  Integer.parseInt(listado8.get(0)) > Integer.parseInt(num4.getText().toString())
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
        listado5 = new ArrayList<>();
        listado6 = new ArrayList<>();
        listado7 = new ArrayList<>();
        listado8 = new ArrayList<>();


        listadoNumeros.add(bundle.getString("n2"));
        listadoNumeros.add(bundle.getString("n5"));
        listadoNumeros.add(bundle.getString("n6"));
        listadoNumeros.add(bundle.getString("n1"));
        listadoNumeros.add(bundle.getString("n4"));
        listadoNumeros.add(bundle.getString("n7"));
        listadoNumeros.add(bundle.getString("n3"));
        listadoNumeros.add(bundle.getString("n8"));

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


        LinearLayoutManager layoutManager6 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler5.setLayoutManager(layoutManager6);
        adaptador5 = new AdaptadorPalabra(listado5,getApplicationContext());
        recycler5.setAdapter(adaptador5);
        recycler5.setOnDragListener(adaptador5.dragInstance);

        LinearLayoutManager layoutManager7 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler6.setLayoutManager(layoutManager7);
        adaptador6 = new AdaptadorPalabra(listado6,getApplicationContext());
        recycler6.setAdapter(adaptador6);
        recycler6.setOnDragListener(adaptador6.dragInstance);

        LinearLayoutManager layoutManager8 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler7.setLayoutManager(layoutManager8);
        adaptador7 = new AdaptadorPalabra(listado7,getApplicationContext());
        recycler7.setAdapter(adaptador7);
        recycler7.setOnDragListener(adaptador7.dragInstance);

        LinearLayoutManager layoutManager9 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler8.setLayoutManager(layoutManager9);
        adaptador8 = new AdaptadorPalabra(listado8,getApplicationContext());
        recycler8.setAdapter(adaptador8);
        recycler8.setOnDragListener(adaptador8.dragInstance);
    }



    private void cartel(String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(NumeroFaltanteLado.this);
        View viewInflated = LayoutInflater.from(NumeroFaltanteLado.this).inflate(R.layout.cartel, (ViewGroup) findViewById(android.R.id.content), false);
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