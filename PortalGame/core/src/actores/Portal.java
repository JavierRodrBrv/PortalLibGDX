package actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Portal extends Actor {
    private Sprite sprite;
    private Body cuerpoPortal;
    private Astronauta jugador;;
    private char direccion;

    public Portal(Astronauta j, World m){
        int anchuraSprite = 1; //Anchura y altura se expresan ahora en metros
        int alturaSprite = 1;//Anchura y altura se expresan ahora en metros
        World mundo = m;


        jugador=j;
        sprite = new Sprite(new Texture("texturaPersonajes/personaje.png"));
        sprite.setBounds(jugador.getX(), jugador.getY(), anchuraSprite, alturaSprite); //La posición inicial también debe estar en metros

        BodyDef propiedadesCuerpo = new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.DynamicBody;
        propiedadesCuerpo.fixedRotation = true;
        cuerpoPortal = mundo.createBody(propiedadesCuerpo);

        FixtureDef propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        ((PolygonShape) propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite / 3.4f, alturaSprite / 2.2f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpoPortal.createFixture(propiedadesFisicasCuerpo);
        sprite.setOrigin(this.sprite.getWidth() / 2,
                this.sprite.getHeight() / 2);
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Body getCuerpoPortal() {
        return cuerpoPortal;
    }

    public void setCuerpoPortal(Body cuerpoPortal) {
        this.cuerpoPortal = cuerpoPortal;
    }

    public Astronauta getJugador() {
        return jugador;
    }

    public void setJugador(Astronauta jugador) {
        this.jugador = jugador;
    }

    public char getDireccion() {
        return direccion;
    }

    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }
}
