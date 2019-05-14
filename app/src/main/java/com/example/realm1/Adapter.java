package com.example.realm1;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.example.realm1.Model.Persona;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class Adapter extends RealmBaseAdapter<Persona> implements ListAdapter {

    public Adapter(@Nullable OrderedRealmCollection<Persona> data) {
        super(data);
    }

    private static class ViewHolder{
        TextView dni, nombre, apellido, genero, edad, correo;
        ImageButton borrar;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder vH;
        if(view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            vH = new ViewHolder();
            vH.dni = view.findViewById(R.id.persona_dni);
            vH.nombre = view.findViewById(R.id.persona_name);
            vH.apellido = view.findViewById(R.id.persona_surname);
            vH.edad = view.findViewById(R.id.persona_age);
            vH.genero = view.findViewById(R.id.persona_gender);
            vH.borrar = view.findViewById(R.id.btn_delete);
            vH.correo= view.findViewById(R.id.persona_correo);
            view.setTag(vH);
        }else {
            vH = (ViewHolder) view.getTag();
        }

        final Persona item = adapterData.get(position);
        vH.dni.setText(item.getDni());
        vH.nombre.setText(item.getName());
        vH.apellido.setText(item.getApellido());
        vH.edad.setText(String.valueOf(item.getEdad()));
        vH.genero.setText(item.getGenero());
        vH.correo.setText(item.getCorreo());
        vH.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(item);
            }
        });
        final View finalConvertView = view;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalConvertView.getContext(), Modificar.class);
                intent.putExtra("dni",item.getDni());
                intent.putExtra("nombre",item.getName());
                intent.putExtra("apellido",item.getApellido());
                intent.putExtra("edad",item.getEdad());
                intent.putExtra("genero",item.getGenero());
                intent.putExtra("correo", item.getCorreo());
                finalConvertView.getContext().startActivity(intent);
            }
        });
        return view;
    }


    public void delete(Persona item){
        Realm rm = Realm.getDefaultInstance();
        final RealmResults<Persona> personas = rm.where(Persona.class).equalTo("dni",item.getDni()).findAll();
        rm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                personas.deleteAllFromRealm();
            }
        });
        notifyDataSetChanged();
    }
}
