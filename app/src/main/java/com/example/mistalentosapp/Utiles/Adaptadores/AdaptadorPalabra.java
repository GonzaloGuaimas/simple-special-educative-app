package com.example.mistalentosapp.Utiles.Adaptadores;

import android.content.ClipData;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.GnssAntennaInfo;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.DragListener;
import com.example.mistalentosapp.Utiles.Listener;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPalabra extends RecyclerView.Adapter<AdaptadorPalabra.MyViewHolder> implements View.OnTouchListener ,View.OnClickListener{
    private List<String> listado;
    private Listener listener;
    private View.OnClickListener listener2;
    Context context;

    public DragListener dragInstance = new DragListener(listener);



    @Override
    public void onClick(View view) {
        if(listener2!=null){
            listener2.onClick(view);
        }
    }
    public  void setOnClickListener(View.OnClickListener listener){
        this.listener2=listener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.palabra,parent,false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.cardView.startAnimation(MainActivity.fadein);

        holder.textPalabra.setText(listado.get(position));
        holder.cardView.setTag(position);
        holder.cardView.setOnTouchListener(this);
        holder.cardView.setOnDragListener(new DragListener(listener));
        ViewGroup.LayoutParams param;
        switch (listado.get(position)){
            case "CENTENA":
                holder.textPalabra.setText("");
                param=  holder.cardView.getLayoutParams();
                param.width = 160;
                param.height = 160;
                holder.cardView.requestLayout();
                holder.linear.setBackgroundColor(Color.rgb(205,38,71));
                break;
            case "DECENA":
                holder.textPalabra.setText("");
                param=  holder.cardView.getLayoutParams();
                param.width = 60;
                param.height = 180;
                holder.cardView.requestLayout();
                holder.linear.setBackgroundColor(Color.rgb(205,38,71));
                break;
            case "UNIDAD":
                holder.textPalabra.setText("");
                param=  holder.cardView.getLayoutParams();
                param.width = 60;
                param.height = 60;
                holder.cardView.requestLayout();
                holder.linear.setBackgroundColor(Color.rgb(205,38,71));
                break;
            case "X":
                holder.textPalabra.setText("");
                holder.imageView.setImageURI(Uri.parse("android.resource://com.example.mistalentosapp/drawable/subir"));

                param=  holder.imageView.getLayoutParams();
                param.width = 200;
                param.height = 200;
                //param.height = FrameLayout.LayoutParams.MATCH_PARENT;
                holder.imageView.requestLayout();

                param=  holder.textPalabra.getLayoutParams();
                param.width = 0;
                param.height = 0;
                holder.textPalabra.requestLayout();
                break;
        }
        if (listado.get(position).contains("file") || listado.get(position).contains("storage") || listado.get(position).contains("drawable")){
            try{
                holder.textPalabra.setText("");
                holder.imageView.setImageURI(Uri.parse(listado.get(position).split("-")[0]));

                param=  holder.imageView.getLayoutParams();
                param.width = 200;
                param.height = 200;
                //param.height = FrameLayout.LayoutParams.MATCH_PARENT;
                holder.imageView.requestLayout();

                param=  holder.textPalabra.getLayoutParams();
                param.width = 0;
                param.height = 0;
                holder.textPalabra.requestLayout();

            }catch (Exception es){
            }
        }

    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public AdaptadorPalabra(List<String> listado, Context context) {
        this.listado = listado;
        this.context = context;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");

            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(data, shadowBuilder, view, 0);
            } else {
                view.startDrag(data, shadowBuilder, view, 0);
            }
            return true;
        }
        return false;
    }

    public void updateList(List<String> listado){
        try{
            this.listado = listado;
            int uni = 0;
            int dec = 0;
            int cent = 0;

            for (String cad:listado) {
                if (!cad.contains("file") && cad.length()<15){
                    switch (cad){
                        case "UNIDAD":
                            uni++;
                            break;
                        case "DECENA":
                            dec=dec+10;
                            break;
                        case "CENTENA":
                            cent=cent+100;
                            break;
                    }

                    if (!cad.equals("UNIDAD") || !cad.equals("DECENA") || !cad.equals("CENTENA")){
                        if (cad.length()==1 && !cad.equals("1") && !cad.equals("2") && !cad.equals("3") && !cad.equals("4")
                                && !cad.equals("5")&& !cad.equals("6")&& !cad.equals("7")&& !cad.equals("8")&& !cad.equals("9")
                                && !cad.equals("0")){
                            switch (cad.toLowerCase()){
                                case "a":
                                    MainActivity.a.start();
                                    break;
                                case "b":
                                    MainActivity.b.start();
                                    break;
                                case "c":
                                    MainActivity.c.start();
                                    break;
                                case "d":
                                    MainActivity.d.start();
                                    break;
                                case "e":
                                    MainActivity.e.start();
                                    break;
                                case "f":
                                    MainActivity.f.start();
                                case "g":
                                    MainActivity.g.start();
                                    break;
                                case "i":
                                    MainActivity.i.start();
                                    break;
                                case "j":
                                    MainActivity.j.start();
                                    break;
                                case "k":
                                    MainActivity.k.start();
                                    break;
                                case "l":
                                    MainActivity.l.start();
                                    break;
                                case "m":
                                    MainActivity.m.start();
                                    break;
                                case "n":
                                    MainActivity.n.start();
                                    break;
                                case "o":
                                    MainActivity.o.start();
                                    break;
                                case "p":
                                    MainActivity.p.start();
                                    break;
                                case "q":
                                    MainActivity.q.start();
                                    break;
                                case "r":
                                    MainActivity.r.start();
                                    break;
                                case "s":
                                    MainActivity.s.start();
                                    break;
                                case "t":
                                    MainActivity.t.start();
                                    break;
                                case "u":
                                    MainActivity.u.start();
                                    break;
                                case "v":
                                    MainActivity.v.start();
                                    break;
                                case "w":
                                    MainActivity.w.start();
                                    break;
                                case "y":
                                    MainActivity.y.start();
                                    break;
                                case "z":
                                    MainActivity.z.start();
                                    break;
                            }
                        }else {
                            MainActivity.textToSpeech.speak(cad, TextToSpeech.QUEUE_FLUSH,null);
                        }

                    }
                }else{
                    System.out.println("PASA POR EL OTRO");
                    MainActivity.textToSpeech.speak(listado.get(0).split("-")[1], TextToSpeech.QUEUE_FLUSH,null);
                }
            }
            switch (listado.get(listado.size()-1)){
                case "UNIDAD":
                    MainActivity.textToSpeech.speak(String.valueOf(uni), TextToSpeech.QUEUE_FLUSH,null);
                    break;
                case "DECENA":
                    MainActivity.textToSpeech.speak(String.valueOf(dec), TextToSpeech.QUEUE_FLUSH,null);
                    break;
                case "CENTENA":
                    MainActivity.textToSpeech.speak(String.valueOf(cent), TextToSpeech.QUEUE_FLUSH,null);
                    break;
            }
        }catch (Exception es){

        }
    }
    public List<String> getList(){
        return this.listado;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textPalabra;
        CardView cardView;
        ImageView imageView;
        LinearLayout linear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textPalabra = itemView.findViewById(R.id.textPalabra);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView);
            linear = itemView.findViewById(R.id.linear);
        }
    }
}