package com.example.realm1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realm1.Model.Persona;

import io.realm.Realm;

public class Añadir extends AppCompatActivity {
    EditText dni, nombre, apellido, genero, edad, correo;
    Button añadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dni = findViewById(R.id.new_dni);
        nombre = findViewById(R.id.new_name);
        apellido = findViewById(R.id.new_surname);
        edad = findViewById(R.id.new_age);
        genero = findViewById(R.id.new_gender);
        correo = findViewById(R.id.new_correo);

        añadir = findViewById(R.id.btn_add);




        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dni.getText().toString().isEmpty()){
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    Persona per = realm.createObject(Persona.class,dni.getText().toString());
                    per.setName(nombre.getText().toString());
                    per.setApellido(apellido.getText().toString());
                    per.setEdad(Integer.parseInt(edad.getText().toString()));
                    per.setGenero(genero.getText().toString());
                    per.setCorreo(correo.getText().toString());
                    realm.commitTransaction();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Error ID no encontrado!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
