package com.example.mistalentosapp.Matematicas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistalentosapp.Lengua.EscrituraDePalabras;
import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorItemSpinner;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorSpinner;
import com.example.mistalentosapp.Utiles.Objetos.Item;

import java.util.ArrayList;
import java.util.List;

public class SumaDeNumeros extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Animation scaleUp;
    private Spinner cent1, cent2,dec1,dec2,uni1,uni2,centResult,decResult,uniResult;
    private CardView cardCalcular,cardTerminarActividad;
    private RecyclerView recyclerMultibase,recyclerSumando1,recyclerSumando2,recyclerSumando3;
    private ImageView imagenOperando;
    private Bundle bundle;
    private TextView textProblema;
    private int resultado;
    private ArrayList<String> listado1,listado2,listado3,listadoMultibase;
    AdaptadorPalabra adaptadorPalabra1,adaptadorPalabra2,adaptadorPalabra3,adaptadorMultibase;
    private String[] digitos = {"0","1","2","3","4","5","6","7","8","9"};

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma_de_numeros);
        scaleUp =  AnimationUtils.loadAnimation(this,R.anim.scale_up);

        textProblema = findViewById(R.id.textProblema);
        cent1 = findViewById(R.id.cent1);
        cent2 = findViewById(R.id.cent2);
        dec1 = findViewById(R.id.dec1);
        dec2 = findViewById(R.id.dec2);
        uni1 = findViewById(R.id.uni1);
        uni2 = findViewById(R.id.uni2);

        centResult = findViewById(R.id.centResult);
        decResult = findViewById(R.id.decResult);
        uniResult = findViewById(R.id.uniResult);

        cardTerminarActividad = findViewById(R.id.cardTerminarActividad);

        cardCalcular = findViewById(R.id.cardCalcular);
        imagenOperando = findViewById(R.id.imagenOperando);

        recyclerMultibase = findViewById(R.id.recyclerMultibase);
        recyclerSumando1 = findViewById(R.id.recyclerSumando1);
        recyclerSumando2 = findViewById(R.id.recyclerSumando2);
        recyclerSumando3 = findViewById(R.id.recyclerSumando3);

        AdaptadorSpinner customAdapter=new AdaptadorSpinner(getApplicationContext(),digitos);

        cent1.setAdapter(customAdapter);
        cent1.setOnItemSelectedListener(this);

        cent2.setAdapter(customAdapter);
        cent2.setOnItemSelectedListener(this);

        centResult.setAdapter(customAdapter);
        centResult.setOnItemSelectedListener(this);


        dec1.setAdapter(customAdapter);
        dec1.setOnItemSelectedListener(this);

        dec2.setAdapter(customAdapter);
        dec2.setOnItemSelectedListener(this);

        decResult.setAdapter(customAdapter);
        decResult.setOnItemSelectedListener(this);


        uni1.setAdapter(customAdapter);
        uni1.setOnItemSelectedListener(this);

        uni2.setAdapter(customAdapter);
        uni2.setOnItemSelectedListener(this);

        uniResult.setAdapter(customAdapter);
        uniResult.setOnItemSelectedListener(this);

        cargarMultibase();
        bundle = getIntent().getExtras();

        resultado = Integer.parseInt( bundle.getString("resultado") );
        String resultadoString = bundle.getString("resultado") ;
        String sumando1 = bundle.getString("sumando1");
        String sumando2 = bundle.getString("sumando2");
        textProblema.setText(bundle.getString("problema"));
        if ( bundle.getInt("operando")==1){
            imagenOperando.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/mas"));
            imagenOperando.bringToFront();
        }else{
            imagenOperando.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/menos"));
            imagenOperando.bringToFront();
        }




        imagenOperando.startAnimation(MainActivity.sample);
        imagenOperando.startAnimation(MainActivity.fadein);

        imagenOperando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.textToSpeech.speak(bundle.getString("problema"), TextToSpeech.QUEUE_FLUSH,null);
            }
        });


        cardTerminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actividadEnCurso.setFin(System.currentTimeMillis());
                MainActivity.actividadEnCurso.setDuracion(MainActivity.actividadEnCurso.obtenerDuracion());
                MainActivity.controller.nuevoActividad(MainActivity.actividadEnCurso);
                Toast.makeText(getApplicationContext(), "se guardo", Toast.LENGTH_SHORT).show();
                SumaDeNumeros.this.finish();
            }
        });

        cardCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardCalcular.startAnimation(scaleUp);

                int resul = 0;
                if(bundle.getInt("operando")==1){
                    for (int i =0; i<listado3.size();i++){
                        switch (listado3.get(i)){
                            case "UNIDAD":
                                resul = resul+1;
                                break;
                            case "DECENA":
                                resul = resul+10;
                                break;
                            case "CENTENA":
                                resul = resul+100;
                                break;
                        }
                    }
                }else {
                    for (int i =0; i<listado1.size();i++){
                        switch (listado1.get(i)){
                            case "UNIDAD":
                                resul = resul+1;
                                break;
                            case "DECENA":
                                resul = resul+10;
                                break;
                            case "CENTENA":
                                resul = resul+100;
                                break;
                        }
                    }
                }

                MainActivity.textToSpeech.speak(String.valueOf(resul), TextToSpeech.QUEUE_FLUSH,null);
                if (resultado==resul){
                    cartel("bien");
                }else{
                    cartel("mal");
                }
            }
        });
    }
    private void cargarMultibase(){
        listado1 = new ArrayList<>();
        listado2 = new ArrayList<>();
        listado3 = new ArrayList<>();
        listadoMultibase = new ArrayList<>();

        listadoMultibase.add("UNIDAD");
        listadoMultibase.add("DECENA");
        listadoMultibase.add("CENTENA");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerSumando1.setLayoutManager(layoutManager);
        adaptadorPalabra1 = new AdaptadorPalabra(listado1,getApplicationContext());
        recyclerSumando1.setAdapter(adaptadorPalabra1);
        recyclerSumando1.setOnDragListener(adaptadorPalabra1.dragInstance);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerSumando2.setLayoutManager(layoutManager2);
        adaptadorPalabra2 = new AdaptadorPalabra(listado2,getApplicationContext());
        recyclerSumando2.setAdapter(adaptadorPalabra2);
        recyclerSumando2.setOnDragListener(adaptadorPalabra2.dragInstance);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerSumando3.setLayoutManager(layoutManager3);
        adaptadorPalabra3 = new AdaptadorPalabra(listado3,getApplicationContext());
        recyclerSumando3.setAdapter(adaptadorPalabra3);
        recyclerSumando3.setOnDragListener(adaptadorPalabra3.dragInstance);

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerMultibase.setLayoutManager(layoutManager4);
        adaptadorMultibase = new AdaptadorPalabra(listadoMultibase,getApplicationContext());
        recyclerMultibase.setAdapter(adaptadorMultibase);
        recyclerMultibase.setOnDragListener(adaptadorMultibase.dragInstance);


    }



    private void cartel(String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(SumaDeNumeros.this);
        View viewInflated = LayoutInflater.from(SumaDeNumeros.this).inflate(R.layout.cartel, (ViewGroup) findViewById(android.R.id.content), false);
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


    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)
    {
        try{
            MainActivity.textToSpeech.speak(digitos[pos], TextToSpeech.QUEUE_FLUSH,null);
        }catch (Exception es){

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {

    }


}