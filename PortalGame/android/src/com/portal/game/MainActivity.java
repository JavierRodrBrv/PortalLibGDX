package com.portal.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import funciones.SalidaAplicacion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void eventoNuevaPartida(View view) {
        Intent it=new Intent(this,AndroidLauncher.class);
        Bundle b=new Bundle();
        b.putBoolean("cargar",false);
        it.putExtras(b);
        startActivity(it);
    }

    public void eventoCargarPartida(View view) {
        Intent it=new Intent(this,AndroidLauncher.class);
        Bundle b=new Bundle();
        b.putBoolean("cargar",true);
        it.putExtras(b);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        SalidaAplicacion.salidaAplicacion(this);
    }
}
