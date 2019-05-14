package com.example.realm1.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.realm1.Añadir;
import com.example.realm1.R;

public class MenuActivity extends AppCompatActivity {
    public static ListView lv;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MainActivity mainActivity= new MainActivity();

        Button buttonlista ;

        Button buttonañadir ;
        lv = findViewById(R.id.person_list);

        buttonañadir = findViewById(R.id.añadir);
        buttonlista = findViewById(R.id.lista);

 buttonlista.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MenuActivity.this,MainActivity.class);
        startActivity(intent);

    }
});


        buttonañadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Añadir.class);
                startActivity(intent);

            }
        });



    }
}
