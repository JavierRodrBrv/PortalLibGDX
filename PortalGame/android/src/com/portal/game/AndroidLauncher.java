package com.portal.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.portal.game.Juego;

import basedatos.BaseDeDatosAndroid;
import funciones.SalidaAplicacion;

/**
 * Clase AndroidLauncher, lanza el juego.
 * @author Javier Rodríguez Bravo.
 */
public class AndroidLauncher extends AndroidApplication {
	/**
	 * Funcion onCreate, lanza el juego y dentro de el la base de datos y una variable boolean.
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Juego(new BaseDeDatosAndroid(this),getIntent().getExtras().getBoolean("cargar")), config);

	}

	/**
	 * Función onBackPressed, llama a la función salidaAplicacion.
	 */
	@Override
	public void onBackPressed() {
		SalidaAplicacion.salidaAplicacion(this);
	}

}
