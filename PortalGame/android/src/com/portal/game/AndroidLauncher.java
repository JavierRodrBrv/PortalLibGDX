package com.portal.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.portal.game.Juego;

import basedatos.BaseDeDatosAndroid;
import funciones.SalidaAplicacion;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Juego(new BaseDeDatosAndroid(this),getIntent().getExtras().getBoolean("cargar")), config);
	}

	@Override
	public void onBackPressed() {
		SalidaAplicacion.salidaAplicacion(this);
	}
}
