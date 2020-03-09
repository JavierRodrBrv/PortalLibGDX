package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.portal.game.Juego;

import java.awt.Dialog;

import basedatos.BaseDatos;

public class Astronauta extends Actor {
    private Sprite sprite;
    private Body cuerpo;
    private BaseDatos baseDeDatos;
    private int contadorMuertes;
    private float anchuraSprite; //Anchura y altura se expresan ahora en metros
    private float alturaSprite;//Anchura y altura se expresan ahora en metros
    private float hitBoxAnchura;
    private float hitBoxAltura;
    private float velocidad;
    private int salto;
    private char direccion;
    private World mundo;
    private Juego juego;
    private String portal;
    private Astronauta as;

    public Astronauta(BaseDatos bd, World m, Juego j) {
        this.juego = j;
        this.as=this;
        portal="";
        valoresPredeterminados();
        baseDeDatos = bd;
        contadorMuertes = 0;
        contadorMuertes = baseDeDatos.cargar();
        mundo = m;
        sprite = new Sprite(new Texture("texturaPersonajes/personajeDcha.png"));

        sprite.setBounds(3,26.5f, anchuraSprite, alturaSprite); //La posición inicial también debe estar en metros


        BodyDef propiedadesCuerpo = new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.DynamicBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());
        propiedadesCuerpo.fixedRotation = true;
        cuerpo = mundo.createBody(propiedadesCuerpo);

        FixtureDef propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        ((PolygonShape) propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite / hitBoxAnchura, alturaSprite / hitBoxAltura);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);
        sprite.setOrigin(this.sprite.getWidth() / 2,
                this.sprite.getHeight() / 2);
    }


    public void draw(Batch batch, float parentAlpha) {
        //Si la posición es menor que el nivel del suelo, reseteo
        if (cuerpo.getPosition().y < 0 - sprite.getHeight() * 3) {

            //Estas tres líneas anulan todas las fuerzas, y ponen al pollo en la posición predeterminada.
            cuerpo.setLinearVelocity(new Vector2(0, 0));
            cuerpo.setAngularVelocity(0);
            teletransportar("inicio");
            //Aqui se pondrá el incremento de las muertes
            contadorMuertes++;
            baseDeDatos.guardar(contadorMuertes);
            valoresPredeterminados();
        }

        mundo.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                //El portal de Juanka no modifica ningun valor sobre el jugador
                if (contact.getFixtureA().getBody() == as.getCuerpo() &&
                        contact.getFixtureB().getBody() == juego.getPortalUan().getCuerpo()) {
                    System.out.println("Estoy tocando el portal Uan con mis manos");
                    portal="juanca";
                    musicaPortal();

                    //El portal de Miguel le suma anchura y altura al personaje
                } else if(contact.getFixtureA().getBody() == as.getCuerpo() &&
                                contact.getFixtureB().getBody() == juego.getPortalMiguel().getCuerpo()) {
                    System.out.println("Estoy tocando el portal Miguel con mis manos");
                    portal="miguel";
                    anchuraSprite = 2;
                    alturaSprite = 2;
                    hitBoxAltura = 1;
                    hitBoxAnchura = 1;
                    musicaPortal();

                    //El portal de Olfy le quita altura y anchura al personaje
                } else if (contact.getFixtureA().getBody() == as.getCuerpo() &&
                        contact.getFixtureB().getBody() == juego.getPortalOlfy().getCuerpo()) {
                    System.out.println("Estoy tocando el portal Olfy con mis manos");
                    portal="olfy";
                    velocidad=50;
                    anchuraSprite = 0.5f;
                    alturaSprite = 0.5f;
                    hitBoxAltura = 5;
                    hitBoxAnchura = 5;
                    musicaPortal();

                    //El portal de Antonio le devuelve la altura original, le quita velocidad y le añade mas salto.
                }else if (contact.getFixtureA().getBody() == as.getCuerpo() &&
                        contact.getFixtureB().getBody() == juego.getPortalAntonio().getCuerpo()) {
                    System.out.println("Estoy tocando el portal Antonio con mis manos");
                    portal="antonio";
                    anchuraSprite = 1;
                    alturaSprite = 1;
                    salto=650;
                    velocidad=0.1f;
                    musicaPortal();
                    //El portal de Darash restablece todos los valores del jugador.
                }else if (contact.getFixtureA().getBody() == as.getCuerpo() &&
                        contact.getFixtureB().getBody() == juego.getPortalDarash().getCuerpo()) {
                    System.out.println("Estoy tocando el portal Darash con mis manos");
                    portal="darash";
                    velocidad=5;
                    salto=150;
                    musicaPortal();
                }else if (contact.getFixtureA().getBody() == as.getCuerpo() &&
                        contact.getFixtureB().getBody() == juego.getPortalFinal().getCuerpo()) {
                    System.out.println("He llegado al final");

                    portal="inicio";
                    velocidad=5;
                    salto=150;
                    musicaPortal();
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        teletransportar(portal);

        //Esta cuenta hace falta por lo de la media altura.
        sprite.setPosition(cuerpo.getPosition().x - sprite.getWidth() / 2, cuerpo.getPosition().y - sprite.getHeight() / 2);
        //Sprite quiere la rotación en grados, el cuerpo la da en radianes. Esta constante convierte de uno a otro.
        sprite.setRotation(MathUtils.radiansToDegrees * cuerpo.getAngle());
        sprite.draw(batch);
    }


    public float getX() {
        return this.cuerpo.getPosition().x;
    }


    public float getY() {
        return this.cuerpo.getPosition().y;
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite s) {
        this.sprite = s;
    }

    public Body getCuerpo() {
        return cuerpo;
    }


    public void seguir(OrthographicCamera camara) {
        camara.position.x = this.cuerpo.getPosition().x;
        camara.position.y = this.cuerpo.getPosition().y;
    }

    public char getDireccion() {
        return direccion;
    }

    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public float getAnchuraSprite() {
        return anchuraSprite;
    }

    public float getAlturaSprite() {
        return alturaSprite;
    }

    public int getSalto() {
        return salto;
    }

    public void teletransportar(String portal){
        switch (portal){

            case "inicio":
                this.getCuerpo().setTransform(5,26,0 );
                break;
            case "juanca":
                this.getCuerpo().setTransform(22,20,0 );
                break;
            case "miguel":
                this.getCuerpo().setTransform(25,7,0 );
                break;
            case "olfy":
                this.getCuerpo().setTransform(48.4f,8.5f,0 );
                break;
            case "antonio":
                this.getCuerpo().setTransform(29,11,0 );
                break;
            case "darash":
                this.getCuerpo().setTransform(45.5f,28,0 );
                break;
        }
        this.portal="";
    }
    public void musicaPortal(){
        Music music = Gdx.audio.newMusic(Gdx.files.internal("musica/musicaportales/musicaPortal.mp3"));
        music.setLooping(false);
        music.setVolume(8.5f);
        music.play();
    }

    public void valoresPredeterminados(){
        anchuraSprite = 1;
        alturaSprite = 1;
        hitBoxAltura = 2.2f;
        hitBoxAnchura = 3.4f;
        velocidad = 5;
        salto=100;
    }

}
