package com.example.mistalentosapp.Utiles.Adaptadores;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.DragListener;
import com.example.mistalentosapp.Utiles.Listener;
import com.example.mistalentosapp.Utiles.Objetos.Abecedario;
import com.example.mistalentosapp.Utiles.Objetos.Actividad;
import com.example.mistalentosapp.Utiles.Objetos.ColocarImagenes;
import com.example.mistalentosapp.Utiles.Objetos.LetraFaltante;
import com.example.mistalentosapp.Utiles.Objetos.OperacionNumeros;
import com.example.mistalentosapp.Utiles.Objetos.Palabra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdaptadorActividad  extends RecyclerView.Adapter<AdaptadorActividad.MyViewHolder> implements  View.OnClickListener,View.OnLongClickListener, Filterable {
    private View.OnClickListener listener;
    private View.OnLongClickListener listener2;
    List<Actividad> listado;
    List<Actividad> listadoAll;

    Context context;

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }
    @Override
    public boolean onLongClick(View view) {
        if(listener2!=null){
            listener2.onLongClick(view);
        }
        return true;
    }
    public  void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    public  void setOnLongClickListener(View.OnLongClickListener listener2){
        this.listener2=listener2;
    }
    public AdaptadorActividad(List<Actividad> listado, Context context) {
        this.listado = listado;
        this.listadoAll = new ArrayList<>();
        this.listadoAll.addAll(listado);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_actividad,parent,false);

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textFecha.setText(listado.get(position).getFechaddMMyyyy());
        holder.textDuración.setText("Duración: "+listado.get(position).getDuracion()+" min");
        holder.textAciertos.setText("Aciertos: "+listado.get(position).getAciertos());
        holder.textFallos.setText("Fallos: "+listado.get(position).getFallos());
        holder.textActividad.setText(listado.get(position).getNombre());
        holder.textContenido.setText(listado.get(position).getContenido());

        //ACA SE TIENE QUE REALIZAR UN SWITCH
        switch (listado.get(position).getNombre()){
            case "Numero Faltante Medio":
            case "Numero Faltante Lado":

                break;
            case "Escritura de Oracion":
                for (Palabra abecedario: MainActivity.controller.obtenerOracion()) {
                    if (abecedario.getId()==listado.get(position).getIdActividad()){
                        try{
                            holder.imagenItem1.setImageURI(Uri.parse(abecedario.getPath1()));
                            holder.imagenItem2.setImageURI(Uri.parse(abecedario.getPath2()));
                            holder.imagenItem3.setImageURI(Uri.parse(abecedario.getPath3()));
                            holder.imagenItem4.setImageURI(Uri.parse(abecedario.getPath4()));
                            holder.imagenItem5.setImageURI(Uri.parse(abecedario.getPath5()));
                            holder.imagenItem6.setImageURI(Uri.parse(abecedario.getPath6()));
                        }catch (Exception es){

                        }
                        break;
                    }
                }
                break;
            case "Escritura de Palabras":
                for (Palabra abecedario: MainActivity.controller.obtenerPalabra()) {
                    if (abecedario.getId()==listado.get(position).getIdActividad()){
                        try{
                            holder.imagenItem1.setImageURI(Uri.parse(abecedario.getPathImagen()));
                        }catch (Exception es){

                        }
                        break;
                    }
                }
                break;
            case "Letra Faltante":
                for (LetraFaltante abecedario: MainActivity.controller.obtenerLetraFaltante()) {
                    if (abecedario.getId()==listado.get(position).getIdActividad()){
                        try{
                            holder.imagenItem1.setImageURI(Uri.parse(abecedario.getPath()));
                        }catch (Exception es){

                        }
                        break;
                    }
                }
                break;
            case "Suma de Numeros":
            case "Resta de Numeros":

                break;
            case "Colocar Imagen":
            case "Colocar Palabra":
                for (ColocarImagenes abecedario: MainActivity.controller.obtenerColocarImagen()) {
                    if (abecedario.getId()==listado.get(position).getIdActividad()){
                        try{
                            holder.imagenItem1.setImageURI(Uri.parse(abecedario.getPath1()));
                            System.out.println(abecedario.getPath1());
                            holder.imagenItem2.setImageURI(Uri.parse(abecedario.getPath2()));
                            holder.imagenItem3.setImageURI(Uri.parse(abecedario.getPath3()));
                            holder.imagenItem4.setImageURI(Uri.parse(abecedario.getPath4()));
                        }catch (Exception es){

                        }
                        break;
                    }
                }
                break;
            case "Abecedario":
                for (Abecedario abecedario: MainActivity.controller.obtenerAbecedario()) {
                    if (abecedario.getId()==listado.get(position).getIdActividad()){
                        try{
                            holder.imagenItem1.setImageURI(Uri.parse(abecedario.getPath1()));
                            System.out.println(abecedario.getPath1());
                            holder.imagenItem2.setImageURI(Uri.parse(abecedario.getPath2()));
                            holder.imagenItem3.setImageURI(Uri.parse(abecedario.getPath3()));
                            holder.imagenItem4.setImageURI(Uri.parse(abecedario.getPath4()));
                            holder.imagenItem5.setImageURI(Uri.parse(abecedario.getPath5()));
                            holder.imagenItem6.setImageURI(Uri.parse(abecedario.getPath6()));
                        }catch (Exception es){

                        }
                        break;
                    }
                }
                break;

        }

    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Actividad> listadoFiltrado = new ArrayList<>();
            if (charSequence==null || charSequence.length()==0){
                listadoFiltrado.addAll(listadoAll);
            }else {
                for (Actividad actividad:listadoAll){
                    if (actividad.getFechaddMMyyyy().toString().contains(charSequence.toString())){
                        listadoFiltrado.add(actividad);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = listadoFiltrado;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listado.clear();
            listado.addAll((Collection<? extends Actividad>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textFecha,textDuración,textAciertos,textFallos,textActividad,textContenido;
        ImageView imagenItem1,imagenItem2,imagenItem3,imagenItem4,imagenItem5,imagenItem6;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textFecha = itemView.findViewById(R.id.textFecha);
            textDuración = itemView.findViewById(R.id.textDuración);
            textAciertos = itemView.findViewById(R.id.textAciertos);
            textActividad = itemView.findViewById(R.id.textActividad);
            textFallos = itemView.findViewById(R.id.textFallos);
            textContenido = itemView.findViewById(R.id.textContenido);

            imagenItem1 = itemView.findViewById(R.id.imagenItem1);
            imagenItem2 = itemView.findViewById(R.id.imagenItem2);
            imagenItem3 = itemView.findViewById(R.id.imagenItem3);
            imagenItem4 = itemView.findViewById(R.id.imagenItem4);
            imagenItem5 = itemView.findViewById(R.id.imagenItem5);
            imagenItem6 = itemView.findViewById(R.id.imagenItem6);
        }
    }
}