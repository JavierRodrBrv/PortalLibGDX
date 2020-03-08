package com.portal.game;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import funciones.SalidaAplicacion;
import servicios.MiServicio;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout miLayout;
    AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miLayout=(ConstraintLayout)findViewById(R.id.myFondoAnimado);
        animationDrawable=(AnimationDrawable) miLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();



        Intent i= new Intent(this, MiServicio.class);
        i.putExtra("claveServicio", "Esto es un servicio");
        this.startService(i);

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
