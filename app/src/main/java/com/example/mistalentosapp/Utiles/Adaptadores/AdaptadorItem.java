package com.example.mistalentosapp.Utiles.Adaptadores;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Objetos.Item;
import java.util.List;

public class AdaptadorItem extends RecyclerView.Adapter<AdaptadorItem.MyViewHolder> implements  View.OnClickListener {
    private View.OnClickListener listener;
    List<Item> listado;
    Context context;

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public  void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public AdaptadorItem(List<Item> listado, Context context) {
        this.listado = listado;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorItem.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_item,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorItem.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorItem.MyViewHolder holder, int position) {
        holder.texto.setText(listado.get(position).getContenido());

    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView texto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            texto = itemView.findViewById(R.id.texto);
        }
    }
}