package com.example.mistalentosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorActividad;
import com.example.mistalentosapp.Utiles.Objetos.Actividad;
import com.example.mistalentosapp.Utiles.Objetos.Alumno;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PDFActivity extends AppCompatActivity {

    private List<Actividad> listadoActividades;
    AdaptadorActividad adaptadorActividad;
    RecyclerView recyclerActividades;

    Bitmap bitmap;
    Alumno alumno = new Alumno();

    String fecha = "";
    Bundle bundle;

    TextView textCantActividades,textDuracionTotal ,textAciertosTotales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfactivity);

        LinearLayout linearPDF = findViewById(R.id.linearPDF);

        TextView textNombre = findViewById(R.id.textNombre);
        TextView textApodo = findViewById(R.id.textApodo);
        TextView textEdad = findViewById(R.id.textEdad);
        TextView textGrado = findViewById(R.id.textGrado);

        TextView textFecha = findViewById(R.id.textFecha);
        textCantActividades = findViewById(R.id.textCantActividades);
        textDuracionTotal = findViewById(R.id.textDuracionTotal);
        textAciertosTotales = findViewById(R.id.textAciertosTotales);

        recyclerActividades = findViewById(R.id.recyclerActividades);

        bundle = getIntent().getExtras();

        fecha = bundle.getString("fecha");
        textFecha.setText(fecha);
        cargarAlumno(bundle.getString("alumno"));


        textNombre.setText(alumno.getApellido()+", "+alumno.getNombre());
        textApodo.setText("Apodo: "+alumno.getApodo());
        textEdad.setText("Edad: "+alumno.getEdad());
        textGrado.setText("Grado: "+alumno.getGrado());

        new CountDownTimer(2000, 1000){
            public void onTick(long millisUntilFinished){

            }
            public  void onFinish(){
                bitmap = loadBitmapFromView(linearPDF, linearPDF.getWidth(), linearPDF.getHeight());
                createPdf();
            }
        }.start();


    }

    private void cargarAlumno(String nombre){
        Boolean band = false;
        for (Alumno alumno:MainActivity.controller.obtenerAlumno()) {
            if ((alumno.getApellido().toLowerCase()+alumno.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
                this.alumno = alumno;
                band = true;
                break;
            }
        }
        if (!band){
            alumno.setNombre("Libre");
            alumno.setApellido("");
            alumno.setApodo("");
            alumno.setEdad("");
            alumno.setGrado("");
        }

        listadoActividades = new ArrayList<>();
        int sumaDur = 0;
        int aciertos = 0;
        for (Actividad actividad:MainActivity.controller.obtenerActividad()) {
            if ((MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre()).equals(actividad.getAlumno()) &&
                    MainActivity.formatoddMMyyyy.format(new Date(actividad.getFecha())).equals(fecha)){
                listadoActividades.add(actividad);
                sumaDur += Integer.valueOf(actividad.getDuracion());
                aciertos += actividad.getAciertos();
            }
        }
        textCantActividades.setText("Actividades:"+String.valueOf(listadoActividades.size()-1));
        textDuracionTotal.setText("Duración Sesión: "+String.valueOf(sumaDur)+" min");
        textAciertosTotales.setText("Aciertos totales: "+String.valueOf(aciertos));

        adaptadorActividad = new AdaptadorActividad(listadoActividades,getApplicationContext());
        recyclerActividades.setAdapter(adaptadorActividad);
        recyclerActividades.setLayoutManager(new LinearLayoutManager(this));
    }



    //------------------------------------------------------------------------------------------------------------------------------
    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }


    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;
        int convertHighet = (int) hight, convertWidth = (int) width;

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        File filePath;
        filePath =new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre()+
                        DateFormat.format("MM d yyyy ", new Date().getTime())+".pdf");
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF Generado", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al crear PDF", Toast.LENGTH_LONG).show();
            System.out.println(e.toString());
        }
        document.close();
    }

}