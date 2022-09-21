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
import com.example.mistalentosapp.Utiles.Objetos.Abecedario;

import java.util.ArrayList;
import java.util.Locale;

public class AbecedarioAct extends AppCompatActivity {

    private ImageView imagen1,imagen2,imagen3,imagen4,imagen5,imagen6;
    private ArrayList<String> listadoAbecedario,listadoLetra;
    private RecyclerView recyclerAbecedario,recyclerLetra;
    private CardView cardLeer,cardTerminarActividad;
    AdaptadorPalabra adaptadorAbecedario,adaptadorLetra;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abecedario);

        imagen1 = findViewById(R.id.imagen1);
        imagen2 = findViewById(R.id.imagen2);
        imagen3 = findViewById(R.id.imagen3);
        imagen4 = findViewById(R.id.imagen4);
        imagen5 = findViewById(R.id.imagen5);
        imagen6 = findViewById(R.id.imagen6);
        cardLeer = findViewById(R.id.cardLeer);
        cardTerminarActividad = findViewById(R.id.cardTerminarActividad);
        recyclerAbecedario = findViewById(R.id.recyclerAbecedario);
        recyclerLetra = findViewById(R.id.recyclerLetra);

        bundle = getIntent().getExtras();

        cargar();

        imagen1.setOnClickListener(onClickListener);
        imagen2.setOnClickListener(onClickListener);
        imagen3.setOnClickListener(onClickListener);
        imagen4.setOnClickListener(onClickListener);
        imagen5.setOnClickListener(onClickListener);
        imagen6.setOnClickListener(onClickListener);


        imagen1.startAnimation(MainActivity.sample);
        imagen2.startAnimation(MainActivity.sample);
        imagen3.startAnimation(MainActivity.sample);
        imagen4.startAnimation(MainActivity.sample);
        imagen5.startAnimation(MainActivity.sample);
        imagen6.startAnimation(MainActivity.sample);

        cardTerminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actividadEnCurso.setFin(System.currentTimeMillis());
                MainActivity.actividadEnCurso.setDuracion(MainActivity.actividadEnCurso.obtenerDuracion());
                MainActivity.controller.nuevoActividad(MainActivity.actividadEnCurso);
                Toast.makeText(getApplicationContext(), "se guardo", Toast.LENGTH_SHORT).show();
                AbecedarioAct.this.finish();
            }
        });

        cardLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String cad = "";
                    if (!bundle.getString("nombre1").equals("")){
                        cad = cad + bundle.getString("nombre1")+"; ";
                    }
                    if (!bundle.getString("nombre2").equals("")){
                        cad = cad + bundle.getString("nombre2")+"; ";
                    }
                    if (!bundle.getString("nombre3").equals("")){
                        cad = cad + bundle.getString("nombre3")+"; ";
                    }
                    if (!bundle.getString("nombre4").equals("")){
                        cad = cad + bundle.getString("nombre4")+"; ";
                    }
                    if (!bundle.getString("nombre5").equals("")){
                        cad = cad + bundle.getString("nombre1")+"; ";
                    }
                    if (!bundle.getString("nombre6").equals("")){
                        cad = cad + bundle.getString("nombre6")+" ";
                    }
                    MainActivity.textToSpeech.speak(cad, TextToSpeech.QUEUE_FLUSH,null);


                    if (bundle.getString("letra").toUpperCase().equals(listadoLetra.get(0))){
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
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                switch (view.getId()){
                    case R.id.imagen1:
                        imagen1.startAnimation(MainActivity.bounce);
                        MainActivity.textToSpeech.speak(bundle.getString("nombre1"), TextToSpeech.QUEUE_FLUSH,null);
                        break;
                    case R.id.imagen2:
                        imagen2.startAnimation(MainActivity.bounce);
                        MainActivity.textToSpeech.speak(bundle.getString("nombre2"), TextToSpeech.QUEUE_FLUSH,null);
                        break;
                    case R.id.imagen3:
                        imagen3.startAnimation(MainActivity.bounce);
                        MainActivity.textToSpeech.speak(bundle.getString("nombre3"), TextToSpeech.QUEUE_FLUSH,null);
                        break;
                    case R.id.imagen4:
                        imagen4.startAnimation(MainActivity.bounce);
                        MainActivity.textToSpeech.speak(bundle.getString("nombre4"), TextToSpeech.QUEUE_FLUSH,null);
                        break;
                    case R.id.imagen5:
                        imagen5.startAnimation(MainActivity.bounce);
                        MainActivity.textToSpeech.speak(bundle.getString("nombre5"), TextToSpeech.QUEUE_FLUSH,null);
                        break;
                    case R.id.imagen6:
                        imagen6.startAnimation(MainActivity.bounce);
                        MainActivity.textToSpeech.speak(bundle.getString("nombre6"), TextToSpeech.QUEUE_FLUSH,null);
                        break;
                }
            }catch (Exception es){

            }

        }
    };

    private void cargar(){
        listadoAbecedario = new ArrayList<>();
        listadoLetra = new ArrayList<>();

        //listadoLetra.add(bundle.getString("img1"));

        try{
            imagen1.setImageURI(Uri.parse(bundle.getString("img1").split("-")[0]));
            imagen2.setImageURI(Uri.parse(bundle.getString("img2").split("-")[0]));
            imagen3.setImageURI(Uri.parse(bundle.getString("img3").split("-")[0]));
            imagen4.setImageURI(Uri.parse(bundle.getString("img4").split("-")[0]));
            imagen5.setImageURI(Uri.parse(bundle.getString("img5").split("-")[0]));
            imagen6.setImageURI(Uri.parse(bundle.getString("img6").split("-")[0]));
        }catch (Exception es){

        }

        try{
            String cad = "";
            if (!bundle.getString("nombre1").equals("")){
                cad = cad + bundle.getString("nombre1")+"; ";
            }
            if (!bundle.getString("nombre2").equals("")){
                cad = cad + bundle.getString("nombre2")+"; ";
            }
            if (!bundle.getString("nombre3").equals("")){
                cad = cad + bundle.getString("nombre3")+"; ";
            }
            if (!bundle.getString("nombre4").equals("")){
                cad = cad + bundle.getString("nombre4")+"; ";
            }
            if (!bundle.getString("nombre5").equals("")){
                cad = cad + bundle.getString("nombre1")+"; ";
            }
            if (!bundle.getString("nombre6").equals("")){
                cad = cad + bundle.getString("nombre6")+"; ";
            }
            MainActivity.textToSpeech.speak(cad, TextToSpeech.QUEUE_FLUSH,null);
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
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerLetra.setLayoutManager(layoutManager);
        adaptadorLetra = new AdaptadorPalabra(listadoLetra,getApplicationContext());
        recyclerLetra.setAdapter(adaptadorLetra);
        recyclerLetra.setOnDragListener(adaptadorLetra.dragInstance);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerAbecedario.setLayoutManager(layoutManager2);
        adaptadorAbecedario = new AdaptadorPalabra(listadoAbecedario,getApplicationContext());
        recyclerAbecedario.setAdapter(adaptadorAbecedario);
        recyclerAbecedario.setOnDragListener(adaptadorAbecedario.dragInstance);

    }


    private void cartel(String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(AbecedarioAct.this);
        View viewInflated = LayoutInflater.from(AbecedarioAct.this).inflate(R.layout.cartel, (ViewGroup) findViewById(android.R.id.content), false);
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