package com.example.mistalentosapp.Lengua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;

import java.util.ArrayList;

public class LetraFaltanteAct extends AppCompatActivity {
    private ImageView imagen;
    private ArrayList<String> listadoAbecedario,listado1,listado2,listado3,listado4,listado5,listado6,listado7
            ,listado8,listado9,listado10;
    private RecyclerView recyclerAbecedario,recyclerLetra1,recyclerLetra2,recyclerLetra3,recyclerLetra4,recyclerLetra5
            ,recyclerLetra6,recyclerLetra7,recyclerLetra8,recyclerLetra9,recyclerLetra10;
    private CardView cardLeer,cardTerminarActividad,card;
    AdaptadorPalabra adaptadorAbecedario,adaptador1,adaptador2,adaptador3,adaptador4,adaptador5,adaptador6,adaptador7,adaptador8,adaptador9,adaptador10;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letra_faltante);

        imagen = findViewById(R.id.imagen);
        recyclerAbecedario = findViewById(R.id.recyclerAbecedario);
        recyclerLetra1 = findViewById(R.id.recyclerLetra1);
        recyclerLetra2 = findViewById(R.id.recyclerLetra2);
        recyclerLetra3 = findViewById(R.id.recyclerLetra3);
        recyclerLetra4 = findViewById(R.id.recyclerLetra4);
        recyclerLetra5 = findViewById(R.id.recyclerLetra5);
        recyclerLetra6 = findViewById(R.id.recyclerLetra6);
        recyclerLetra7 = findViewById(R.id.recyclerLetra7);
        recyclerLetra8 = findViewById(R.id.recyclerLetra8);
        recyclerLetra9 = findViewById(R.id.recyclerLetra9);
        recyclerLetra10 = findViewById(R.id.recyclerLetra10);
        cardLeer = findViewById(R.id.cardLeer);
        cardTerminarActividad = findViewById(R.id.cardTerminarActividad);
        card = findViewById(R.id.card);

        bundle = getIntent().getExtras();

        cargar();

        cardTerminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actividadEnCurso.setFin(System.currentTimeMillis());
                MainActivity.actividadEnCurso.setDuracion(MainActivity.actividadEnCurso.obtenerDuracion());
                MainActivity.controller.nuevoActividad(MainActivity.actividadEnCurso);
                Toast.makeText(getApplicationContext(), "se guardo", Toast.LENGTH_SHORT).show();
                LetraFaltanteAct.this.finish();
            }
        });

        cardLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    String cad = "";
                    if (!listado1.isEmpty()){
                        cad = listado1.get(0);
                    }
                    if (!listado2.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0);
                    }
                    if (!listado3.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0)+listado3.get(0);
                    }
                    if (!listado4.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0)+listado3.get(0)+listado4.get(0);
                    }
                    if (!listado5.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0)+listado3.get(0)+listado4.get(0)+listado5.get(0);
                    }
                    if (!listado6.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0)+listado3.get(0)+listado4.get(0)+listado5.get(0)+listado6.get(0);
                    }
                    if (!listado7.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0)+listado3.get(0)+listado4.get(0)+listado5.get(0)+listado6.get(0)+
                                listado7.get(0);
                    }
                    if (!listado8.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0)+listado3.get(0)+listado4.get(0)+listado5.get(0)+listado6.get(0)+
                                listado7.get(0)+listado8.get(0);
                    }
                    if (!listado9.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0)+listado3.get(0)+listado4.get(0)+listado5.get(0)+listado6.get(0)+
                                listado7.get(0)+listado8.get(0)+listado9.get(0);
                    }
                    if (!listado10.isEmpty()){
                        cad = listado1.get(0)+listado2.get(0)+listado3.get(0)+listado4.get(0)+listado5.get(0)+listado6.get(0)+
                                listado7.get(0)+listado8.get(0)+listado9.get(0)+listado10.get(0);
                    }

                    MainActivity.textToSpeech.speak(cad, TextToSpeech.QUEUE_FLUSH,null);
                    if (bundle.getString("palabraCompleta").equals(cad)){
                        cartel("bien");
                    }else{
                        cartel("mal");
                    }
                }catch (Exception es){
                    cartel("mal");
                }
            }
        });
    }

    private void cargar(){
        listadoAbecedario = new ArrayList<>();
        listado1 = new ArrayList<>();
        listado2 = new ArrayList<>();
        listado3 = new ArrayList<>();
        listado4 = new ArrayList<>();
        listado5 = new ArrayList<>();
        listado6 = new ArrayList<>();
        listado7 = new ArrayList<>();
        listado8 = new ArrayList<>();
        listado9 = new ArrayList<>();
        listado10 = new ArrayList<>();

        try{
            imagen.setImageURI(Uri.parse(bundle.getString("img")));
            card.startAnimation(MainActivity.sample);
        }catch (Exception es){

        }

        if (bundle.getString("tipo").equals("Abecedario")){
            listadoAbecedario.add("A");
            listadoAbecedario.add("B");
            listadoAbecedario.add("C");
            listadoAbecedario.add("D");
            listadoAbecedario.add("E");
            listadoAbecedario.add("F");
            listadoAbecedario.add("G");
            listadoAbecedario.add("H");
            listadoAbecedario.add("I");
            listadoAbecedario.add("J");
            listadoAbecedario.add("K");
            listadoAbecedario.add("L");
            listadoAbecedario.add("M");
            listadoAbecedario.add("N");
            listadoAbecedario.add("O");
            listadoAbecedario.add("P");
            listadoAbecedario.add("Q");
            listadoAbecedario.add("R");
            listadoAbecedario.add("S");
            listadoAbecedario.add("T");
            listadoAbecedario.add("U");
            listadoAbecedario.add("V");
            listadoAbecedario.add("W");
            listadoAbecedario.add("X");
            listadoAbecedario.add("Y");
            listadoAbecedario.add("Z");
        }else{

        }




        try{
            if (!bundle.getString("palabraIncompleta").substring(0,1).equals(" ")){
                listado1.add(bundle.getString("palabraIncompleta").substring(0,1));
            }
            if (!bundle.getString("palabraIncompleta").substring(1,2).equals(" ")){
                listado2.add(bundle.getString("palabraIncompleta").substring(1,2));
            }
            if (!bundle.getString("palabraIncompleta").substring(2,3).equals(" ")){
                listado3.add(bundle.getString("palabraIncompleta").substring(2,3));
            }
            if (!bundle.getString("palabraIncompleta").substring(3,4).equals(" ")){
                listado4.add(bundle.getString("palabraIncompleta").substring(3,4));
            }
            if (!bundle.getString("palabraIncompleta").substring(4,5).equals(" ")){
                listado5.add(bundle.getString("palabraIncompleta").substring(4,5));
            }
            if (!bundle.getString("palabraIncompleta").substring(5,6).equals(" ")){
                listado6.add(bundle.getString("palabraIncompleta").substring(5,6));
            }
            if (!bundle.getString("palabraIncompleta").substring(6,7).equals(" ")){
                listado7.add(bundle.getString("palabraIncompleta").substring(6,7));
            }
            if (!bundle.getString("palabraIncompleta").substring(7,8).equals(" ")){
                listado8.add(bundle.getString("palabraIncompleta").substring(7,8));
            }
            if (!bundle.getString("palabraIncompleta").substring(8,9).equals(" ")){
                listado9.add(bundle.getString("palabraIncompleta").substring(8,9));
            }
            if (!bundle.getString("palabraIncompleta").substring(9,10).equals(" ")){
                listado10.add(bundle.getString("palabraIncompleta").substring(9,10));
            }

        }catch (Exception es){

        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerAbecedario.setLayoutManager(layoutManager);
        adaptadorAbecedario = new AdaptadorPalabra(listadoAbecedario,getApplicationContext());
        recyclerAbecedario.setAdapter(adaptadorAbecedario);
        recyclerAbecedario.setOnDragListener(adaptadorAbecedario.dragInstance);


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra1.setLayoutManager(layoutManager1);
        adaptador1 = new AdaptadorPalabra(listado1,getApplicationContext());
        recyclerLetra1.setAdapter(adaptador1);
        recyclerLetra1.setOnDragListener(adaptador1.dragInstance);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra2.setLayoutManager(layoutManager2);
        adaptador2 = new AdaptadorPalabra(listado2,getApplicationContext());
        recyclerLetra2.setAdapter(adaptador2);
        recyclerLetra2.setOnDragListener(adaptador2.dragInstance);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra3.setLayoutManager(layoutManager3);
        adaptador3 = new AdaptadorPalabra(listado3,getApplicationContext());
        recyclerLetra3.setAdapter(adaptador3);
        recyclerLetra3.setOnDragListener(adaptador3.dragInstance);

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra4.setLayoutManager(layoutManager4);
        adaptador4 = new AdaptadorPalabra(listado4,getApplicationContext());
        recyclerLetra4.setAdapter(adaptador4);
        recyclerLetra4.setOnDragListener(adaptador4.dragInstance);

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra5.setLayoutManager(layoutManager5);
        adaptador5 = new AdaptadorPalabra(listado5,getApplicationContext());
        recyclerLetra5.setAdapter(adaptador5);
        recyclerLetra5.setOnDragListener(adaptador5.dragInstance);

        LinearLayoutManager layoutManager6 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra6.setLayoutManager(layoutManager6);
        adaptador6 = new AdaptadorPalabra(listado6,getApplicationContext());
        recyclerLetra6.setAdapter(adaptador6);
        recyclerLetra6.setOnDragListener(adaptador6.dragInstance);

        LinearLayoutManager layoutManager7 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra7.setLayoutManager(layoutManager7);
        adaptador7 = new AdaptadorPalabra(listado7,getApplicationContext());
        recyclerLetra7.setAdapter(adaptador7);
        recyclerLetra7.setOnDragListener(adaptador7.dragInstance);

        LinearLayoutManager layoutManager8 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra8.setLayoutManager(layoutManager8);
        adaptador8 = new AdaptadorPalabra(listado8,getApplicationContext());
        recyclerLetra8.setAdapter(adaptador8);
        recyclerLetra8.setOnDragListener(adaptador8.dragInstance);

        LinearLayoutManager layoutManager9 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra9.setLayoutManager(layoutManager9);
        adaptador9 = new AdaptadorPalabra(listado9,getApplicationContext());
        recyclerLetra9.setAdapter(adaptador9);
        recyclerLetra9.setOnDragListener(adaptador9.dragInstance);

        LinearLayoutManager layoutManager10 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra10.setLayoutManager(layoutManager10);
        adaptador10 = new AdaptadorPalabra(listado10,getApplicationContext());
        recyclerLetra10.setAdapter(adaptador10);
        recyclerLetra10.setOnDragListener(adaptador10.dragInstance);
    }


    private void cartel(String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(LetraFaltanteAct.this);
        View viewInflated = LayoutInflater.from(LetraFaltanteAct.this).inflate(R.layout.cartel, (ViewGroup) findViewById(android.R.id.content), false);
        ImageView imagen = viewInflated.findViewById(R.id.imagen);
        TextView textTitulo = viewInflated.findViewById(R.id.textTitulo);
        TextView textFrase = viewInflated.findViewById(R.id.textFrase);
        switch (tipo){
            case "bien":
                textTitulo.setText("GENIAL");
                textFrase.setText("¡ Lo Lograste !");
                imagen.setBackgroundResource(R.drawable.sonriente);
                MainActivity.actividadEnCurso.aumentarAcierto();
                break;
            case "mal":
                textTitulo.setText("UPS");
                textFrase.setText("¡ Intentalo de Nuevo !");
                imagen.setBackgroundResource(R.drawable.asustado);
                MainActivity.actividadEnCurso.aumentarFallo();
                break;
        }

        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
}