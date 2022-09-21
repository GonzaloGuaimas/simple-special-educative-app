package com.example.mistalentosapp.Utiles.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Objetos.ActividadConf;

import java.util.List;

public class AdaptadorActividadConf extends RecyclerView.Adapter<AdaptadorActividadConf.MyViewHolder> implements  View.OnClickListener,View.OnLongClickListener{
    private View.OnClickListener listener;
    private View.OnLongClickListener listener2;
    private List<ActividadConf> listado;
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
    public AdaptadorActividadConf(List<ActividadConf> listado, Context context) {
        this.listado = listado;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorActividadConf.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_actividad_conf,parent,false);

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new AdaptadorActividadConf.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorActividadConf.MyViewHolder holder, int position) {
        holder.textGrado.setText(listado.get(position).getGrado());
        holder.textArea.setText(listado.get(position).getArea());
        holder.textContenido.setText(listado.get(position).getContenido());
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textGrado,textArea,textContenido;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textGrado = itemView.findViewById(R.id.textGrado);
            textArea = itemView.findViewById(R.id.textArea);
            textContenido = itemView.findViewById(R.id.textContenido);

        }
    }
}