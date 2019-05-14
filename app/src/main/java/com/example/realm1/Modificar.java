package com.example.realm1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.realm1.Model.Persona;

import io.realm.Realm;
import io.realm.RealmResults;

public class Modificar extends AppCompatActivity {
    EditText nombre, apellido, genero, edad, correo;
    Button borrar;
    TextView dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_persona);


        borrar = findViewById(R.id.btn_modify);
        nombre = findViewById(R.id.modify_name);
        apellido = findViewById(R.id.modify_surname);
        edad = findViewById(R.id.modify_age);
        dni = findViewById(R.id.modify_dni);
        correo = findViewById(R.id.modify_correo);
        genero = findViewById(R.id.modify_gender);


        Intent intent = getIntent();
        Integer age2 = intent.getIntExtra("edad",2000);
        final String str_dni = intent.getStringExtra("dni");
        nombre.setText(intent.getStringExtra("nombre"));
        apellido.setText(intent.getStringExtra("apellido"));
        edad.setText(String.valueOf(age2));
        genero.setText(intent.getStringExtra("genero"));
        correo.setText(intent.getStringExtra("correo"));
        dni.setText(str_dni);



        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm rm = Realm.getDefaultInstance();
                rm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Persona> per = realm.where(Persona.class).equalTo("dni",str_dni).findAll();
                        per.setValue("nombre", nombre.getText().toString());
                        per.setValue("apellido", apellido.getText().toString());
                        per.setValue("edad",Integer.parseInt(edad.getText().toString()));
                        per.setValue("genero", genero.getText().toString());
                        per.setValue("correo", correo.getText().toString());
                    }
                });
                finish();
            }
        });
    }
}
