package com.example.mistalentosapp.Utiles.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Objetos.Item;

public class AdaptadorItemSpinner extends BaseAdapter implements  View.OnClickListener{
    private View.OnClickListener listener;
    Context context;
    Item items[];
    LayoutInflater inflter;

    public AdaptadorItemSpinner(Context applicationContext, Item[] items) {
        this.context = applicationContext;
        this.items = items;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_spinner, null);
        TextView names = (TextView) view.findViewById(R.id.texto);
        names.setText(items[i].getContenido());
        view.setOnClickListener(this);
        return view;
    }
    public  void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }
}