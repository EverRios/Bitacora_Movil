//ADAPTADOR-----------------------
package com.example.everardo.aplicacion.adaptadores;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.example.everardo.aplicacion.R;
import com.example.everardo.aplicacion.usuarios;
import java.util.List;

/**
 * Created by Everardo on 22/abr/2015.
 */
public class usuarioAdaptador extends RecyclerView.Adapter<usuarioAdaptador.usuarioViewHolder> {
    // agregar el modelo del List
    private List<usuarios> items;

    public static class usuarioViewHolder extends RecyclerView.ViewHolder {
//declaramos los Tecview de todos los campos
        public TextView id_persona;
        public TextView nombre;
        public TextView apellidos;
        public TextView tipo;
        public TextView nivel;
        public TextView modulo;

///Agregar el usuarioViewHolder---
        public usuarioViewHolder(View v) {
            super(v);
            id_persona = (TextView) v.findViewById(R.id.id_persona);
            nombre= (TextView) v.findViewById(R.id.nommbre_c);
            apellidos = (TextView) v.findViewById(R.id.apellidos_c);
            tipo = (TextView) v.findViewById(R.id.tipo_c);
            nivel = (TextView) v.findViewById(R.id.nivel_c);
            modulo = (TextView) v.findViewById(R.id.modulo_c);
        }
    }

    public usuarioAdaptador(List<usuarios> items) {
        this.items = items;
    }

    public int getItemCount() {
        return items.size();
    }

    @Override
    public usuarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       //Agregar el LayoutInflanter de la tarjeta
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.usuarios_card, viewGroup, false);
        return new usuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(usuarioViewHolder usuarioViewHolder, int i) {
        //// muestra los registros en la vista

        usuarioViewHolder.id_persona.setText("ID:"+String.valueOf(items.get(i).getIdusuario()));
        usuarioViewHolder.nombre.setText("NOMBRE:"+String.valueOf(items.get(i).getNombres()));
        usuarioViewHolder.apellidos.setText("APELLIDOS:"+String.valueOf(items.get(i).getApellidos()));
        usuarioViewHolder.tipo.setText("TIPO:"+String.valueOf(items.get(i).getTipo()));
        usuarioViewHolder.nivel.setText("NIVEL:"+String.valueOf(items.get(i).getNivel()));
        usuarioViewHolder.modulo.setText("MODULO:"+String.valueOf(items.get(i).getModulo()));

    }

}
