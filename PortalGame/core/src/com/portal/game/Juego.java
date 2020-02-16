package com.portal.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import input.Teclado;
import personaje.Astronauta;

public class Juego extends ApplicationAdapter {

	private World world;
	private SpriteBatch batch;
	private Astronauta jugador;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camara;
	private Teclado teclado;
	private TiledMap mapa;

	private OrthogonalTiledMapRenderer renderer;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		world=new World(new Vector2(0,-9.8f),true);
		jugador=new Astronauta(world);
		camara=new OrthographicCamera(10,10);
		this.debugRenderer=new Box2DDebugRenderer();
		camara.position.x=jugador.getX();
		camara.position.y=jugador.getY();

		//Era un suelo alternativo, creado sin mapa, lo quitamos para probar mapa
		BodyDef propiedadesSuelo= new BodyDef(); //Establecemos las propiedades del cuerpo
		propiedadesSuelo.type = BodyDef.BodyType.StaticBody;
		Body suelo = world.createBody(propiedadesSuelo);
		FixtureDef propiedadesFisicasSuelo=new FixtureDef();
		propiedadesFisicasSuelo.shape = new PolygonShape();
		((PolygonShape)propiedadesFisicasSuelo.shape).setAsBox(100/2f, 1/2f);
		propiedadesFisicasSuelo.density = 1f;
		suelo.createFixture(propiedadesFisicasSuelo);
		//Fin suelo alternativo, que no está cargado del tmx


		teclado=new Teclado(jugador);
		Gdx.input.setInputProcessor(teclado);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		jugador.seguir(camara);
		//renderer.setView(camara);
		//renderer.render();
		batch.setProjectionMatrix(camara.combined);

		batch.begin();
		jugador.draw(batch,0);
		batch.end();
		camara.update();
		debugRenderer.render(world, camara.combined);
	}
	
	@Override
	public void dispose () {
		world.dispose();
		//renderer.dispose();
		this.debugRenderer.dispose();
		this.batch.dispose();
	}
}
