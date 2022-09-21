package com.example.mistalentosapp.Utiles.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mistalentosapp.R;

public class AdaptadorSpinner extends BaseAdapter {
    Context context;
    String digitos[];
    LayoutInflater inflter;

    public AdaptadorSpinner(Context applicationContext, String[] digitos) {
        this.context = applicationContext;
        this.digitos = digitos;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return digitos.length;
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
        names.setText(digitos[i]);
        return view;
    }
}
