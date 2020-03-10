package com.portal.game;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import funciones.SalidaAplicacion;
import servicios.MiServicio;

/**
 * Clase MainActivity que extiende de AppCompatActivity
 * @author Javier Rodríguez Bravo.
 */
public class MainActivity extends AppCompatActivity {

    private ConstraintLayout miLayout;
    private AnimationDrawable animationDrawable;

    /**
     * Función onCreate, donde lanza la animación del Layout y el servicio.
     * @param savedInstanceState
     */
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

    /**
     * Función eventoNuevaPartida. Envia un boolean false para que no cargue, solo guarde.
     * @param view
     */
    public void eventoNuevaPartida(View view) {
        Intent it=new Intent(this,AndroidLauncher.class);
        Bundle b=new Bundle();
        b.putBoolean("cargar",false);
        it.putExtras(b);
        startActivity(it);
    }

    /**
     * Función eventoCargarPartida. Envia un boolean true, para que guarde en la base de datos.
     * @param view
     */
    public void eventoCargarPartida(View view) {
        Intent it=new Intent(this,AndroidLauncher.class);
        Bundle b=new Bundle();
        b.putBoolean("cargar",true);
        it.putExtras(b);
        startActivity(it);
    }

    /**
     * Función para cuando se presione el boton atrás de la aplicación.
     */
    @Override
    public void onBackPressed() {
        SalidaAplicacion.salidaAplicacion(this);
    }



}
