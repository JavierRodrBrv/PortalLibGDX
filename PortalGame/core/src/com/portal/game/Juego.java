package com.portal.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import actores.Portal;
import basedatos.BaseDatos;
import input.Teclado;
import actores.Astronauta;

public class Juego extends Game {

    private World world;
    private SpriteBatch batch;
    private SpriteBatch batchTexto;
    private Astronauta jugador;
    private Portal portal;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camara;
    private TiledMap mapa;
    private static final float pixelsPorCuadro = 16f;
    private OrthogonalTiledMapRenderer renderer;
    private BaseDatos baseDeDatos;
    private BitmapFont textoPuntuacion;
    private FreeTypeFontGenerator generator;
    private Sprite sprite;

    private int contadorMuertes;

    public Juego(BaseDatos bd) {
        baseDeDatos = bd;
    }

    @Override
    public void create() {
        contadorMuertes = 0;
        contadorMuertes = baseDeDatos.cargar();
        textoPuntuacion = new BitmapFont();

        //Batchs
        batchTexto = new SpriteBatch();
        batch = new SpriteBatch();

        //Actores
        jugador = new Astronauta(baseDeDatos, world);
        portal =new Portal(jugador,world);
        world = new World(new Vector2(0, -9.8f), true);

        camara = new OrthographicCamera(10, 10);
        this.debugRenderer = new Box2DDebugRenderer();
        camara.position.x = jugador.getX();
        camara.position.y = jugador.getY();

        mapa = new TmxMapLoader().load("mapas/mapaPortal.tmx");
        renderer = new OrthogonalTiledMapRenderer(mapa, 1 / pixelsPorCuadro);

        //Colisiones del mapa
        contruirColisiones();


        //Controles
        Teclado teclado = new Teclado(jugador,portal);
        Gdx.input.setInputProcessor(teclado);


        //Texto de contador muertes
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fuentes/BAUHS93.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderColor = new Color(0.1f, 0.1f, 0.1f, 1);
        parameter.borderWidth = 3f;
        parameter.incremental = true;
        textoPuntuacion = generator.generateFont(parameter);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        jugador.seguir(camara);
        renderer.setView(camara);
        renderer.render();
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        jugador.draw(batch, 0);
        portal.draw(batch,0);
        batch.end();

        contadorMuertes = baseDeDatos.cargar();
        batchTexto.begin();
        textoPuntuacion.draw(batchTexto, contadorMuertes + " contador muertes", Gdx.graphics.getHeight() / 10,
                Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10, Gdx.graphics.getWidth(), -1, false);
        batchTexto.end();

        if(jugador.getDireccion() == 'd'){
            jugador.getCuerpo().setLinearVelocity(10,jugador.getCuerpo().getLinearVelocity().y);
            portal.getCuerpoPortal().setLinearVelocity(25,portal.getCuerpoPortal().getLinearVelocity().y);
        }else if( jugador.getDireccion() == 'i'){
            jugador.getCuerpo().setLinearVelocity(-10,jugador.getCuerpo().getLinearVelocity().y);
        }else if(jugador.getDireccion()=='p'){
            jugador.getCuerpo().setLinearVelocity(0,jugador.getCuerpo().getLinearVelocity().y);
        }

        camara.update();
        debugRenderer.render(world, camara.combined);
    }

    @Override
    public void dispose() {
        this.world.dispose();
        this.renderer.dispose();
        this.debugRenderer.dispose();
        this.batch.dispose();
        this.textoPuntuacion.dispose();
        this.generator.dispose();
    }


    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / pixelsPorCuadro,
                (rectangle.y + rectangle.height * 0.5f) / pixelsPorCuadro);
        polygon.setAsBox(rectangle.width * 0.5f / pixelsPorCuadro,
                rectangle.height * 0.5f / pixelsPorCuadro,
                size,
                0.0f);
        return polygon;
    }

    public void contruirColisiones() {
        //Creamos el cuerpo físico de todos los rectángulos del tmx
        for (MapObject objeto : mapa.getLayers().get("colisiones").getObjects()) {
            BodyDef propiedadesRectangulo = new BodyDef(); //Establecemos las propiedades del cuerpo
            propiedadesRectangulo.type = BodyDef.BodyType.StaticBody;
            Body rectanguloSuelo = world.createBody(propiedadesRectangulo);
            FixtureDef propiedadesFisicasRectangulo = new FixtureDef();
            Shape formaRectanguloSuelo = getRectangle((RectangleMapObject) objeto);
            propiedadesFisicasRectangulo.shape = formaRectanguloSuelo;
            propiedadesFisicasRectangulo.density = 1f;
            rectanguloSuelo.createFixture(propiedadesFisicasRectangulo);
        }
    }


}
