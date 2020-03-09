package com.portal.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import actores.PortalFinal;
import actores.PortalitoAntonio;
import actores.PortalitoDarash;
import actores.PortalitoMiguel;
import actores.PortalitoOlfy;
import actores.PortalitoUan;
import basedatos.BaseDatos;
import input.Escuchador;
import actores.Astronauta;

/**
 * Esta es la clase principal del juego (Portal Game).
 * @author Javier Rodríguez Bravo
 */
public class Juego extends Game {
    private World world;
    private SpriteBatch batch;
    private SpriteBatch batchTexto;
    private Astronauta jugador;
    private PortalitoUan portalUan;
    private PortalitoMiguel portalMiguel;
    private PortalitoOlfy portalOlfy;
    private PortalitoAntonio portalAntonio;
    private PortalitoDarash portalDarash;
    private PortalFinal portalFinal;
    //private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camara;
    private TiledMap mapa;
    private static final float pixelsPorCuadro = 16f;
    private OrthogonalTiledMapRenderer renderer;
    private BaseDatos baseDeDatos;
    private BitmapFont textoPuntuacion;
    private FreeTypeFontGenerator generator;
    private int contadorMuertes;
    private Boolean cargar;


    /**
     * Constructor de la clase main Juego
     * @param bd traemos por parametros la base de datos.
     * @param b y también pasamos por parametros una variable boolean que la utilizaremos para decidir si se guarda,carga o borra.
     */
    public Juego(BaseDatos bd,Boolean b) {
        this.cargar=b;
        baseDeDatos = bd;
    }


    @Override
    public void create() {

        //Antes de hacer nada, iniciamos la música.
        musicaJuego();

        //Inicializacion de variables.
        contadorMuertes = 0;
        textoPuntuacion = new BitmapFont();
        world = new World(new Vector2(0, -9.8f), true);
        //this.debugRenderer = new Box2DDebugRenderer(); Esta variable es para sacar la hitbox de el mapa junto a los actores.
        camara = new OrthographicCamera(10, 10);
        mapa = new TmxMapLoader().load("mapas/mapaPortal.tmx");
        renderer = new OrthogonalTiledMapRenderer(mapa, 1 / pixelsPorCuadro);

        //Batchs
        batchTexto = new SpriteBatch();
        batch = new SpriteBatch();


        //Este if comprueba si la variable boolean que envia el Main Activity es verdadera o falsa.
        if(cargar){//Si es verdadera, el contador muertes recibe lo que tenia en la base de datos.
            contadorMuertes = baseDeDatos.cargar();
        }else{
            baseDeDatos.guardar(contadorMuertes);//En caso contrario, utilizamos la inicializacion anterior de contador muertes (0)
        }                                       //para guardarla en base de datos, haciendo asi un correcto funcionamiento de restablecer el contador,
                                                //ya sea en la base de datos y en el juego.
        //Actores
        jugador = new Astronauta(baseDeDatos, world,this);
        //A todos los portales le enviamos las coordenadas de su aparición en el juego en base de x e y.
        portalUan=new PortalitoUan(world,10,26.5f);
        portalMiguel=new PortalitoMiguel(world,3,4.5f);
        portalOlfy=new PortalitoOlfy(world,31.5f,6.5f);
        portalAntonio=new PortalitoAntonio(world,45.5f,8.5f);
        portalDarash=new PortalitoDarash(world,29,28);
        portalFinal=new PortalFinal(world,48,28);


        //Cogemos la posiciones de la camara y hacemos que siga al jugador.
        camara.position.x = jugador.getX();
        camara.position.y = jugador.getY();


        //Colisiones del mapa
        contruirColisiones();


        //Escuchador que recibe los controles.
        final Escuchador escuchador = new Escuchador(jugador);
        Gdx.input.setInputProcessor(escuchador);

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
        portalUan.draw(batch,0);
        portalMiguel.draw(batch,0);
        portalOlfy.draw(batch,0);
        portalAntonio.draw(batch,0);
        portalDarash.draw(batch,0);
        portalFinal.draw(batch,0);
        batch.end();

        contadorMuertes = baseDeDatos.cargar();
        batchTexto.begin();
        textoPuntuacion.draw(batchTexto, contadorMuertes + " contador muertes", Gdx.graphics.getHeight() / 10,
                Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10, Gdx.graphics.getWidth(), -1, false);
        batchTexto.end();


        //Las siguientes condiciones recogen una variable tipo char en este caso de la clase Astronauta (jugador) y
        //continuan el movimiento tal y como le indicamos, para que sea un movimiento seguido sin interrupción.
        if(jugador.getDireccion() == 'd'){
            jugador.getCuerpo().setLinearVelocity(jugador.getVelocidad(),jugador.getCuerpo().getLinearVelocity().y);
        }else if( jugador.getDireccion() == 'i'){
            jugador.getCuerpo().setLinearVelocity(-jugador.getVelocidad(),jugador.getCuerpo().getLinearVelocity().y);
        //En cambio, si entra en esta condición, es para que el personaje se pare y no siga movimiendose.
        }else if(jugador.getDireccion()=='p'){
            jugador.getCuerpo().setLinearVelocity(0,jugador.getCuerpo().getLinearVelocity().y);
        }

        camara.update();
        //debugRenderer.render(world, camara.combined);
    }

    @Override
    public void dispose() {
        this.world.dispose();
        this.renderer.dispose();
        //this.debugRenderer.dispose();
        this.batch.dispose();
        this.textoPuntuacion.dispose();
        this.generator.dispose();
    }

    /**
     * Función que se encarga de pasar los pixeles a rectangulos para después construirlos bien.
     * @param rectangleObject estamos indicando por parametros que las colisiones van a ser de rectangulo objetos.
     * @return devolvemos la forma de la que va a ser las colisiones.
     */
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

    /**
     * Función que se encarga de construir todas las colisiones del mapa.
     */
    public void contruirColisiones() {
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

    /**
     * Función para iniciar la música del juego.
     */
    public void musicaJuego(){
        Music music = Gdx.audio.newMusic(Gdx.files.internal("musica/musicafondo/musicaFondo.mp3"));
        music.setLooping(true);
        music.setVolume(1.5f);
        music.play();
    }


    /**
     * Getter del actor PortalMiguel.
     * @return devuelve el actor PortalMiguel.
     */
    public PortalitoMiguel getPortalMiguel() {
        return portalMiguel;
    }
    /**
     * Getter del actor PortalUan.
     * @return devuelve el actor PortalUan.
     */
    public PortalitoUan getPortalUan() {
        return portalUan;
    }
    /**
     * Getter del actor getPortalOlfy.
     * @return devuelve el actor getPortalOlfy.
     */
    public PortalitoOlfy getPortalOlfy() {
        return portalOlfy;
    }
    /**
     * Getter del actor getPortalAntonio.
     * @return devuelve el actor getPortalAntonio.
     */
    public PortalitoAntonio getPortalAntonio() {
        return portalAntonio;
    }
    /**
     * Getter del actor getPortalDarash.
     * @return devuelve el actor getPortalDarash.
     */
    public PortalitoDarash getPortalDarash() {
        return portalDarash;
    }
    /**
     * Getter del actor getPortalFinal.
     * @return devuelve el actor getPortalFinal.
     */
    public PortalFinal getPortalFinal() {
        return portalFinal;
    }
}
