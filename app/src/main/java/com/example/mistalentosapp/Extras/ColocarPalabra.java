package com.example.mistalentosapp.Extras;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;

import java.util.ArrayList;

public class ColocarPalabra extends AppCompatActivity {

    private Bundle bundle;
    private CardView cardRevisar,cardTerminarActividad;
    private RecyclerView recycler1,recycler2,recycler3,recycler4, recyclerImagenes;
    private ImageView image1,image2,image3,image4;
    private ArrayList<String> listado1,listado2,listado3,listado4,listadoImagenes;
    AdaptadorPalabra adaptador1,adaptador2,adaptador3,adaptador4,adaptadorImagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocar_palabra);

        cardRevisar = findViewById(R.id.cardRevisar);
        cardTerminarActividad = findViewById(R.id.cardTerminarActividad);
        recycler1 = findViewById(R.id.recycler11);
        recycler2 = findViewById(R.id.recycler22);
        recycler3 = findViewById(R.id.recycler33);
        recycler4 = findViewById(R.id.recycler44);
        recyclerImagenes = findViewById(R.id.recyclerImagenes);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);

        image1.startAnimation(MainActivity.sample);
        image2.startAnimation(MainActivity.sample);
        image3.startAnimation(MainActivity.sample);
        image4.startAnimation(MainActivity.sample);

        bundle = getIntent().getExtras();

        cargarImagenes();

        cardTerminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actividadEnCurso.setFin(System.currentTimeMillis());
                MainActivity.actividadEnCurso.setDuracion(MainActivity.actividadEnCurso.obtenerDuracion());
                MainActivity.controller.nuevoActividad(MainActivity.actividadEnCurso);
                Toast.makeText(getApplicationContext(), "se guardo", Toast.LENGTH_SHORT).show();
                ColocarPalabra.this.finish();
            }
        });
        cardRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardRevisar.startAnimation(MainActivity.scaleUp);
                try {
                    String cad = listado3.get(0)+" "+listado4.get(0)+" "+listado1.get(0)+" y "+listado2.get(0);
                    MainActivity.textToSpeech.speak(cad, TextToSpeech.QUEUE_FLUSH,null);
                    if(listado1.get(0).equals(bundle.getString("nombre1")) && listado2.get(0).equals(bundle.getString("nombre2"))
                            && listado3.get(0).equals(bundle.getString("nombre3")) && listado4.get(0).equals(bundle.getString("nombre4"))){
                        cartel("bien");
                    }else {
                        cartel("mal");
                    }
                }catch (Exception es){

                }
            }
        });
    }

    private void cargarImagenes(){
        listado1 = new ArrayList<>();
        listado2 = new ArrayList<>();
        listado3 = new ArrayList<>();
        listado4 = new ArrayList<>();
        listadoImagenes = new ArrayList<>();


        listadoImagenes.add(bundle.getString("nombre3"));
        listadoImagenes.add(bundle.getString("nombre2"));
        listadoImagenes.add(bundle.getString("nombre4"));
        listadoImagenes.add(bundle.getString("nombre1"));

        image1.setImageURI(Uri.parse(bundle.getString("img1").split("-")[0]));
        image2.setImageURI(Uri.parse(bundle.getString("img2").split("-")[0]));
        image3.setImageURI(Uri.parse(bundle.getString("img3").split("-")[0]));
        image4.setImageURI(Uri.parse(bundle.getString("img4").split("-")[0]));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler1.setLayoutManager(layoutManager);
        adaptador1 = new AdaptadorPalabra(listado1,getApplicationContext());
        recycler1.setAdapter(adaptador1);
        recycler1.setOnDragListener(adaptador1.dragInstance);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler2.setLayoutManager(layoutManager2);
        adaptador2 = new AdaptadorPalabra(listado2,getApplicationContext());
        recycler2.setAdapter(adaptador2);
        recycler2.setOnDragListener(adaptador2.dragInstance);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler3.setLayoutManager(layoutManager3);
        adaptador3 = new AdaptadorPalabra(listado3,getApplicationContext());
        recycler3.setAdapter(adaptador3);
        recycler3.setOnDragListener(adaptador3.dragInstance);

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler4.setLayoutManager(layoutManager4);
        adaptador4 = new AdaptadorPalabra(listado4,getApplicationContext());
        recycler4.setAdapter(adaptador4);
        recycler4.setOnDragListener(adaptador4.dragInstance);

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerImagenes.setLayoutManager(layoutManager5);
        adaptadorImagenes = new AdaptadorPalabra(listadoImagenes,getApplicationContext());
        recyclerImagenes.setAdapter(adaptadorImagenes);
        recyclerImagenes.setOnDragListener(adaptadorImagenes.dragInstance);
    }


    private void cartel(String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(ColocarPalabra.this);
        View viewInflated = LayoutInflater.from(ColocarPalabra.this).inflate(R.layout.cartel, (ViewGroup) findViewById(android.R.id.content), false);
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