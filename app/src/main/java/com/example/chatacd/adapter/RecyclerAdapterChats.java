package com.example.chatacd.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.chatacd.R;
import com.example.chatacd.model.Contacto;

import java.util.ArrayList;

public class RecyclerAdapterChats extends RecyclerView.Adapter<RecyclerAdapterChats.RecyclerHolder02>{

    public ArrayList<Contacto> listaChats;

    Context contexto;

    private CircularProgressDrawable progressDrawable;

    //Declaramos los listener de nuestro RecyclerAdapter
    View.OnClickListener onClickListener;
    View.OnLongClickListener onLongClickListener;

    public RecyclerAdapterChats(Context contexto) {
        this.contexto = contexto;
        listaChats = new ArrayList<Contacto>();
    }

    @NonNull
    @Override
    public RecyclerAdapterChats.RecyclerHolder02 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerHolder02 recyclerHolder02;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacto,parent, false);
        recyclerHolder02 = new RecyclerHolder02(view);

        //asignamos los listener a nuestra vista
        view.setOnClickListener(onClickListener);
        view.setOnLongClickListener(onLongClickListener);

        return recyclerHolder02;

    }

    //Metodo para añadir un Item a la lista y al recyclerAdapter
    public void insertarItem(Contacto c){
        listaChats.add(c);
        Log.d("Escrito", "Se ha escrito");
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder02 holder, int position) {

        Contacto contacto = listaChats.get(position);

        holder.nombre.setText(contacto.getNombre());
        holder.ultimoMensaje.setText(contacto.getUltimoMensaje());

        progressDrawable = new CircularProgressDrawable(contexto);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setStyle(CircularProgressDrawable.LARGE);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        Glide.with(contexto)
                .load(contacto.getImg())
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgPerfil);

        Log.d("Entra", "Se escribe");

    }

    @Override
    public int getItemCount() {
        return listaChats.size();
    }

    //Asignamos los elementos de nustro recycled holder a variables creadas
    public class RecyclerHolder02 extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView ultimoMensaje;
        ImageView imgPerfil;

        public RecyclerHolder02(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            ultimoMensaje = (TextView) itemView.findViewById(R.id.txtUltimoMensaje);
            imgPerfil = (ImageView) itemView.findViewById(R.id.imgPerfil);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }


}