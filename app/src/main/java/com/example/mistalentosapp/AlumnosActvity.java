package com.example.mistalentosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistalentosapp.Extras.ColocarImagen;
import com.example.mistalentosapp.Matematicas.NumeroFaltante;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorActividad;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;
import com.example.mistalentosapp.Utiles.Objetos.Actividad;
import com.example.mistalentosapp.Utiles.Objetos.Alumno;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlumnosActvity extends AppCompatActivity {

    Bitmap bitmap;

    TextView textActividadTitulo;
    TextView textNombre;
    TextView textApodo;
    TextView textEdad;
    TextView textGrado;
    LinearLayout linearLayoutPDF2;
    static TextView textFecha;
    CardView cardNuevoAlumno,cardExportar,cardBorrarLibre,cardSelecAlumno,cardModAlumno,cardEliminarPaciente;

    private List<Actividad> listadoActividades;
    AdaptadorActividad adaptadorActividad;
    RecyclerView recyclerActividades;

    private Alumno alumno = new Alumno();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_actvity);

        cardNuevoAlumno = findViewById(R.id.cardNuevoAlumno);
        cardExportar = findViewById(R.id.cardExportar);
        cardBorrarLibre = findViewById(R.id.cardFiltro);
        cardSelecAlumno = findViewById(R.id.cardSelecAlumno);
        cardEliminarPaciente = findViewById(R.id.cardEliminarPaciente);
        cardModAlumno = findViewById(R.id.cardModAlumno);
        textNombre = findViewById(R.id.textNombre);
        textActividadTitulo = findViewById(R.id.textActividadTitulo);
        textApodo = findViewById(R.id.textApodo);
        textEdad = findViewById(R.id.textEdad);
        textGrado = findViewById(R.id.textGrado);
        textFecha = findViewById(R.id.textFecha);
        recyclerActividades = findViewById(R.id.recyclerActividades);

        linearLayoutPDF2 = findViewById(R.id.linearLayoutPDF2);

        cardNuevoAlumno.setOnClickListener(onClickListener);
        cardExportar.setOnClickListener(onClickListener);
        cardSelecAlumno.setOnClickListener(onClickListener);
        cardEliminarPaciente.setOnClickListener(onClickListener);
        cardModAlumno.setOnClickListener(onClickListener);

        textFecha.setText(MainActivity.formatoddMMyyyy.format(System.currentTimeMillis()));

        if (MainActivity.alumnoSesion!=null){
            cargarAlumno(MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre());
        }


        cardBorrarLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlumnosActvity.this);
                final String[] tipo  = new String[]{"Todas","Escritura de Palabras","Escritura de Oracion","Numero Faltante Medio","Numero Faltante Lado","Suma de Numeros","Resta de Numeros","Colocar Imagen","Colocar Palabra","Letra Faltante","Abecedario"};
                builder.setTitle("Seleccioná Actividad").setItems(tipo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textActividadTitulo.setText(tipo[which]);
                        cargarAlumno(MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre());
                    }
                });
                builder.show();

            }
        });

        textFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        textFecha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cargarAlumno(MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre());
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cardEliminarPaciente:

                    final androidx.appcompat.app.AlertDialog.Builder builder2 = new androidx.appcompat.app.AlertDialog.Builder(AlumnosActvity.this);
                    builder2.setMessage("Desea eliminar paciente "+MainActivity.alumnoSesion.getApellido()+" "+MainActivity.alumnoSesion.getNombre()+"?");
                    builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder2.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try{
                                MainActivity.controller.eliminarAlumno(MainActivity.alumnoSesion.getId());
                                Toast.makeText(getApplicationContext(),"Paciente Eliminado",Toast.LENGTH_SHORT).show();
                            }catch (Exception es){

                            }

                            dialogInterface.cancel();
                        }
                    });
                    builder2.create().show();

                    break;
                case R.id.cardNuevoAlumno:
                    nuevoAlumno(null,"Nuevo");
                    break;
                case R.id.cardExportar:
                    Intent intent = new Intent(getApplicationContext(), PDFActivity.class);
                    intent.putExtra("alumno", MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre());
                    intent.putExtra("fecha", textFecha.getText().toString());
                    startActivity(intent);
                    break;
                case R.id.cardModAlumno:
                    nuevoAlumno(MainActivity.alumnoSesion,"Modificar");
                    break;
                case R.id.cardSelecAlumno:
                    try{
                        AlertDialog.Builder builder = new AlertDialog.Builder(AlumnosActvity.this);
                        //final String[] tipo  = new String[10];
                        final String[] tipo  = new String[]{"","","","","","","","","","","","","","","","","","","",""};
                        int o = 1;
                        tipo[0] = "Libre";

                    for (Alumno alumno: MainActivity.controller.obtenerAlumno()) {
                        tipo[o] = alumno.getApellido().toUpperCase()+" "+alumno.getNombre();
                        o++;
                    }
                        builder.setTitle("Seleccioná Paciente").setItems(tipo, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                if (tipo[which].equals("Libre")){

                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(AlumnosActvity.this);
                                    builder2.setTitle("Ingresar Nombre");
                                    EditText input = new EditText(getApplicationContext());
                                    input.setInputType(InputType.TYPE_CLASS_TEXT );
                                    builder2.setView(input);
                                    builder2.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            MainActivity.alumnoSesion = new Alumno( input.getText().toString(),"-","-","-","-");
                                            cargarAlumno(MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre());
                                        }
                                    });
                                    builder2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    builder2.show();

                                }else{
                                    for (Alumno alumno: MainActivity.controller.obtenerAlumno()) {
                                        if ((alumno.getApellido().toUpperCase()+" "+alumno.getNombre()).equals(tipo[which])){
                                            MainActivity.alumnoSesion = alumno;
                                            cargarAlumno(MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre());
                                            break;
                                        }
                                    }
                                }


                            }
                        });
                        builder.show();
                    }catch (Exception es){

                        System.out.println(es.getMessage());
                    }

                    break;
            }
        }
    };

    //--------------------------------------------------------------------------------------------------------------------------------


    private void nuevoAlumno(Alumno alumno, String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(AlumnosActvity.this);
        View viewInflated = LayoutInflater.from(AlumnosActvity.this).inflate(R.layout.z_nuevo_alumno, (ViewGroup) findViewById(android.R.id.content), false);
        EditText textNombre = viewInflated.findViewById(R.id.textNombre);
        EditText textApellido = viewInflated.findViewById(R.id.textApellido);
        EditText textApodo = viewInflated.findViewById(R.id.textApodo);
        EditText textEdad = viewInflated.findViewById(R.id.textEdad);
        TextView textGrado = viewInflated.findViewById(R.id.textGrado);
        CardView cardGuardar = viewInflated.findViewById(R.id.cardGuardar);
        CardView cardGrado = viewInflated.findViewById(R.id.cardGrado);

        if(alumno != null){
           textNombre.setText(alumno.getNombre());
           textApellido.setText(alumno.getApellido());
           textApodo.setText(alumno.getApodo());
           textEdad.setText(alumno.getEdad());
           textGrado.setText(alumno.getGrado());
        }
        cardGrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlumnosActvity.this);
                final String[] tipo  = new String[]{"A","B","C"};
                builder.setTitle("Seleccioná Tipo de Inicio de Sesión").setItems(tipo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textGrado.setText(tipo[which]);
                    }
                });
                builder.show();
            }
        });

        cardGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textNombre.getText()) && !TextUtils.isEmpty(textApellido.getText()) && !TextUtils.isEmpty(textApodo.getText())
                        && !TextUtils.isEmpty(textEdad.getText()) && !textGrado.getText().toString().equals("Seleccionar Grado")){
                    try{
                        if (tipo.equals("Nuevo")){
                            Alumno alumno = new Alumno(textNombre.getText().toString(),textApellido.getText().toString(),textApodo.getText().toString(),textEdad.getText().toString(),textGrado.getText().toString());
                            alumno.setEstado(false);
                            MainActivity.controller.nuevoAlumno(alumno);
                            MainActivity.basedatos.collection("Alumnos").document(alumno.getApellido()+alumno.getNombre()).set(alumno);
                            Toast.makeText(getApplicationContext(), "ALUMNO AGREGADO", Toast.LENGTH_SHORT).show();
                            MainActivity.alumnoSesion = alumno;
                        }else if (tipo.equals("Modificar")){
                            MainActivity.controller.eliminarAlumno(alumno.getId());
                            Alumno alumno = new Alumno(textNombre.getText().toString(),textApellido.getText().toString(),textApodo.getText().toString(),textEdad.getText().toString(),textGrado.getText().toString());
                            MainActivity.controller.nuevoAlumno(alumno);
                            alumno.setEstado(false);
                            MainActivity.basedatos.collection("Alumnos").document(alumno.getApellido()+alumno.getNombre()).set(alumno);
                            Toast.makeText(getApplicationContext(), "ALUMNO MODIFICADO", Toast.LENGTH_SHORT).show();
                            MainActivity.alumnoSesion = alumno;
                        }
                    }catch (Exception es){
                        System.out.println(es.getMessage());
                    }
                    cargarAlumno(MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre());
                }else{
                    Toast.makeText(getApplicationContext(), "Rellene Campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setView(viewInflated);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    private void cargarAlumno(String nombre){
        Boolean band = false;
        for (Alumno alumno:MainActivity.controller.obtenerAlumno()) {
            if ((alumno.getApellido().toLowerCase()+alumno.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
                this.alumno = alumno;
                textNombre.setText(alumno.getApellido()+", "+alumno.getNombre());
                textApodo.setText("Apodo: "+alumno.getApodo());
                textEdad.setText("Edad: "+alumno.getEdad());
                textGrado.setText("Grado: "+alumno.getGrado());
                band = true;
                break;
            }
        }
        if (!band){
            textNombre.setText(MainActivity.alumnoSesion.getApellido()+", "+MainActivity.alumnoSesion.getNombre());
            textApodo.setText("Apodo: "+MainActivity.alumnoSesion.getApodo());
            textEdad.setText("Edad: "+MainActivity.alumnoSesion.getEdad());
            textGrado.setText("Grado: "+MainActivity.alumnoSesion.getGrado());
        }

        listadoActividades = new ArrayList<>();
        for (Actividad actividad:MainActivity.controller.obtenerActividad()) {
            System.out.println("a");
            System.out.println(actividad.getNombre());
            if(textActividadTitulo.getText().toString().equals("Todas") || textActividadTitulo.getText().toString().equals("Actividades realizadas")){
                if ((MainActivity.alumnoSesion.getApellido().toUpperCase()+MainActivity.alumnoSesion.getNombre().toUpperCase()).equals(actividad.getAlumno().toUpperCase()) &&
                        MainActivity.formatoddMMyyyy.format(new Date(actividad.getFecha())).equals(textFecha.getText().toString())){
                    listadoActividades.add(actividad);
                }
            }else{
                System.out.println("lllllllll");
                System.out.println(actividad.getNombre());
                System.out.println(textActividadTitulo.getText().toString());
                if ((MainActivity.alumnoSesion.getApellido().toUpperCase()+MainActivity.alumnoSesion.getNombre().toUpperCase()).equals(actividad.getAlumno().toUpperCase()) &&
                        MainActivity.formatoddMMyyyy.format(new Date(actividad.getFecha())).equals(textFecha.getText().toString())
                        && textActividadTitulo.getText().toString().equals(actividad.getNombre())){
                    System.out.println("XXXXXXXXXXXXXXXX");
                    listadoActividades.add(actividad);
                }
            }

        }
        adaptadorActividad = new AdaptadorActividad(listadoActividades,getApplicationContext());
        recyclerActividades.setAdapter(adaptadorActividad);
        recyclerActividades.setLayoutManager(new LinearLayoutManager(this));


        adaptadorActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AlumnosActvity.this);
                builder.setMessage("Desea eliminar actividad "+listadoActividades.get(recyclerActividades.getChildAdapterPosition(view)).getContenido()+"?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (Actividad actividad:MainActivity.controller.obtenerActividad()) {
                            if (actividad.getAlumno().equals(MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre())
                                    && actividad.getId().equals(listadoActividades.get(recyclerActividades.getChildAdapterPosition(view)).getId())){
                                MainActivity.controller.eliminarActividad(actividad);
                                cargarAlumno(MainActivity.alumnoSesion.getApellido()+MainActivity.alumnoSesion.getNombre());
                                break;
                            }
                        }
                        Toast.makeText(getApplicationContext(),"Actividad Eliminada",Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });

    }





    //------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            if ((month+1)>9){
                if (day>9){
                    textFecha.setText(day+"/"+(month+1)+"/"+year);
                }else{
                    textFecha.setText("0"+day+"/"+(month+1)+"/"+year);
                }

            }else{
                if (day>9){
                    textFecha.setText(day+"/"+"0"+(month+1)+"/"+year);
                }else{
                    textFecha.setText("0"+day+"/"+"0"+(month+1)+"/"+year);
                }

            }
        }
    }
}