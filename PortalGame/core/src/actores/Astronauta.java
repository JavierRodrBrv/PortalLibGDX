package actores;

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

import basedatos.BaseDatos;

public class Astronauta extends Actor {
    private Sprite sprite;
    private Body cuerpo;
    private BaseDatos baseDeDatos;
    private int contadorMuertes;
    private int anchuraSprite = 1; //Anchura y altura se expresan ahora en metros
    private int alturaSprite = 1;//Anchura y altura se expresan ahora en metros
    private char direccion;
    private World mundo;
    private Astronauta as;
    private Juego juego;

    public Astronauta(BaseDatos bd, World m,Juego j) {
        this.as = this;
        this.juego=j;
        baseDeDatos = bd;
        contadorMuertes = 0;
        contadorMuertes = baseDeDatos.cargar();
        mundo = m;
        sprite = new Sprite(new Texture("texturaPersonajes/personajeDcha.png"));

        sprite.setBounds(5, 26, anchuraSprite, alturaSprite); //La posición inicial también debe estar en metros


        BodyDef propiedadesCuerpo = new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.DynamicBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());
        propiedadesCuerpo.fixedRotation = true;
        cuerpo = mundo.createBody(propiedadesCuerpo);

        FixtureDef propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        ((PolygonShape) propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite / 3.4f, alturaSprite / 2.2f);
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
            this.getCuerpo().setTransform(5, 26, 0);
            //Aqui se pondrá el incremento de las muertes
            contadorMuertes++;
            baseDeDatos.guardar(contadorMuertes);
        }

        mundo.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureA().getBody() == as.getCuerpo() &&
                        contact.getFixtureB().getBody() == juego.getPortalMiguel().getCuerpo()) {
                    System.out.println("Estoy tocando el portal con mis manos");
                    as.getCuerpo().setTransform(5, 26, 0);

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

    public int getAnchuraSprite() {
        return anchuraSprite;
    }

    public void setAnchuraSprite(int anchuraSprite) {
        this.anchuraSprite = anchuraSprite;
    }

    public int getAlturaSprite() {
        return alturaSprite;
    }

    public void setAlturaSprite(int alturaSprite) {
        this.alturaSprite = alturaSprite;
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
}
