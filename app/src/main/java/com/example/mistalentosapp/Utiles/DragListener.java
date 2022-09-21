package com.example.mistalentosapp.Utiles;

import android.speech.tts.TextToSpeech;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mistalentosapp.MainActivity;
import com.example.mistalentosapp.R;
import com.example.mistalentosapp.Utiles.Adaptadores.AdaptadorPalabra;

import java.util.List;


public class DragListener implements View.OnDragListener{

    private boolean isDropped = false;
    private Listener listener;

    public DragListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            isDropped = true;
            int positionTarget = -1;

            View viewSource = (View) event.getLocalState();
            int viewId = view.getId();
            final int cardView = R.id.cardView;

            //------------------------LENGUA--------------------------
            //------------Recycler Oraciones------------------
            final int recyclerOración = R.id.recyclerOración;
            final int recyclerPalabras = R.id.recyclerPalabras;
            //------------Recycler Palabra------------------
            final int recyclerPalDesarmada = R.id.recyclerPalDesarmada;
            final int recyclerPalArmada = R.id.recyclerPalArmada;
            //------------Recycler ABECEDARIO Y LETRAFALTANTE------------------
            final int recyclerAbecedario = R.id.recyclerAbecedario;
            final int recyclerLetra = R.id.recyclerLetra;
            final int recyclerLetra1 = R.id.recyclerLetra1;
            final int recyclerLetra2 = R.id.recyclerLetra2;
            final int recyclerLetra3 = R.id.recyclerLetra3;
            final int recyclerLetra4 = R.id.recyclerLetra4;
            final int recyclerLetra5 = R.id.recyclerLetra5;
            final int recyclerLetra6 = R.id.recyclerLetra6;
            final int recyclerLetra7 = R.id.recyclerLetra7;
            final int recyclerLetra8 = R.id.recyclerLetra8;
            final int recyclerLetra9 = R.id.recyclerLetra9;
            final int recyclerLetra10 = R.id.recyclerLetra10;

            //------------------------MATEMATICAS--------------------------
            //------------Recycler Numero Faltate------------------
            final int recyclerNumeros = R.id.recyclerNumeros;
            final int recycler1 = R.id.recycler1;
            final int recycler2 = R.id.recycler2;
            final int recycler3 = R.id.recycler3;
            final int recycler4 = R.id.recycler4;
            final int recycler5 = R.id.recycler5;
            final int recycler6 = R.id.recycler6;
            final int recycler7 = R.id.recycler7;
            final int recycler8 = R.id.recycler8;
            //------------Recycler SumaNumeros------------------
            final int recyclerMultibase = R.id.recyclerMultibase;
            final int recyclerSumando1 = R.id.recyclerSumando1;
            final int recyclerSumando2 = R.id.recyclerSumando2;
            final int recyclerSumando3 = R.id.recyclerSumando3;

            //------------------------EXTRA--------------------------
            //------------Recycler colocarImagenes------------------
            final int recycler11 = R.id.recycler11;
            final int recycler22 = R.id.recycler22;
            final int recycler33 = R.id.recycler33;
            final int recycler44 = R.id.recycler44;
            final int recyclerImagenes = R.id.recyclerImagenes;

            switch (viewId) {
                case cardView:
                case recyclerOración:
                case recyclerPalabras:

                case recyclerNumeros:
                case recycler1:
                case recycler2:
                case recycler3:
                case recycler4:
                case recycler5:
                case recycler6:
                case recycler7:
                case recycler8:


                case recyclerMultibase:
                case recyclerSumando1:
                case recyclerSumando2:
                case recyclerSumando3:

                case recyclerPalArmada:
                case recyclerPalDesarmada:

                case recycler11:
                case recycler22:
                case recycler33:
                case recycler44:
                case recyclerImagenes:

                case recyclerAbecedario:
                case recyclerLetra:
                case recyclerLetra1:
                case recyclerLetra2:
                case recyclerLetra3:
                case recyclerLetra4:
                case recyclerLetra5:
                case recyclerLetra6:
                case recyclerLetra7:
                case recyclerLetra8:
                case recyclerLetra9:
                case recyclerLetra10:

                    RecyclerView target;
                    switch (viewId) {
                        case recyclerOración:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerOración);
                            break;
                        case recyclerPalabras:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerPalabras);
                            break;


                        case recyclerAbecedario:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerAbecedario);
                            break;
                        case recyclerLetra:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra);
                            break;
                        case recyclerLetra1:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra1);
                            break;
                        case recyclerLetra2:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra2);
                            break;
                        case recyclerLetra3:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra3);
                            break;
                        case recyclerLetra4:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra4);
                            break;
                        case recyclerLetra5:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra5);
                            break;
                        case recyclerLetra6:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra6);
                            break;
                        case recyclerLetra7:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra7);
                            break;
                        case recyclerLetra8:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra8);
                            break;
                        case recyclerLetra9:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra9);
                            break;
                        case recyclerLetra10:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerLetra10);
                            break;



                        case recycler1:
                            target = (RecyclerView) view.getRootView().findViewById(recycler1);
                            break;
                        case recycler2:
                            target = (RecyclerView) view.getRootView().findViewById(recycler2);
                            break;
                        case recycler3:
                            target = (RecyclerView) view.getRootView().findViewById(recycler3);
                            break;
                        case recycler4:
                            target = (RecyclerView) view.getRootView().findViewById(recycler4);
                            break;
                        case recycler5:
                            target = (RecyclerView) view.getRootView().findViewById(recycler5);
                            break;
                        case recycler6:
                            target = (RecyclerView) view.getRootView().findViewById(recycler6);
                            break;
                        case recycler7:
                            target = (RecyclerView) view.getRootView().findViewById(recycler7);
                            break;
                        case recycler8:
                            target = (RecyclerView) view.getRootView().findViewById(recycler8);
                            break;
                        case recyclerNumeros:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerNumeros);
                            break;

                        case recyclerMultibase:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerMultibase);
                            break;
                        case recyclerSumando1:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerSumando1);
                            break;
                        case recyclerSumando2:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerSumando2);
                            break;
                        case recyclerSumando3:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerSumando3);
                            break;

                        case recyclerPalArmada:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerPalArmada);
                            break;
                        case recyclerPalDesarmada:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerPalDesarmada);
                            break;

                        case recycler11:
                            target = (RecyclerView) view.getRootView().findViewById(recycler11);
                            break;
                        case recycler22:
                            target = (RecyclerView) view.getRootView().findViewById(recycler22);
                            break;
                        case recycler33:
                            target = (RecyclerView) view.getRootView().findViewById(recycler33);
                            break;
                        case recycler44:
                            target = (RecyclerView) view.getRootView().findViewById(recycler44);
                            break;
                        case recyclerImagenes:
                            target = (RecyclerView) view.getRootView().findViewById(recyclerImagenes);
                            break;

                        default:
                            target = (RecyclerView) view.getParent();
                            positionTarget = (int) view.getTag();
                    }

                    if (viewSource != null) {
                        RecyclerView source = (RecyclerView) viewSource.getParent();

                        AdaptadorPalabra adapterSource = (AdaptadorPalabra) source.getAdapter();
                        int positionSource = (int) viewSource.getTag();
                        int sourceId = source.getId();

                        String list = adapterSource.getList().get(positionSource);
                        List<String> listSource = adapterSource.getList();

                        switch ( source.getId()){
                            case R.id.recyclerMultibase:
                            case R.id.recyclerAbecedario:
                                System.out.println("NO HAGO NADA");
                                break;
                            default:
                                listSource.remove(positionSource);
                                adapterSource.updateList(listSource);
                                adapterSource.notifyDataSetChanged();
                                break;
                        }
                        /*
                        if (source.getId()!=R.id.recyclerMultibase){
                            listSource.remove(positionSource);
                            adapterSource.updateList(listSource);
                            adapterSource.notifyDataSetChanged();
                        }
                         */

                        switch (target.getId()){
                            case R.id.recyclerAbecedario:
                            case R.id.recyclerMultibase:
                                System.out.println("NO HAGO NADAAA");
                                break;
                            default:
                                AdaptadorPalabra adapterTarget = (AdaptadorPalabra) target.getAdapter();
                                List<String> customListTarget = adapterTarget.getList();
                                if (positionTarget >= 0) {
                                    customListTarget.add(positionTarget, list);
                                } else {
                                    customListTarget.add(list);
                                }
                                adapterTarget.updateList(customListTarget);
                                adapterTarget.notifyDataSetChanged();
                                break;
                        }

                    }
                    break;
            }
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}
