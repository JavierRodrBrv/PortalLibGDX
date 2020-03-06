package actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PortalPadre extends Actor {

    protected Sprite sprite;
    private World mundo;
    private BodyDef propiedadesCuerpo;
    private Body cuerpo;
    private FixtureDef propiedadesFisicasCuerpo;
    protected int anchuraSprite=1; //Anchura y altura se expresan ahora en metros
    protected int alturaSprite=1;//Anchura y altura se expresan ahora en metros

    public PortalPadre(World m,float x,float y){

        mundo=m;
        sprite=new Sprite(new Texture("texturaPersonajes/portal.png"));
        sprite.setBounds(x,y,anchuraSprite,alturaSprite);



        propiedadesCuerpo= new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.StaticBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());
        propiedadesCuerpo.fixedRotation = true;
        cuerpo = mundo.createBody(propiedadesCuerpo);

        propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        propiedadesFisicasCuerpo.isSensor=true;
        ((PolygonShape)propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite/3.4f, alturaSprite/2.3f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);

        sprite.setOrigin(this.sprite.getWidth()/2,
                this.sprite.getHeight()/2);

    }


    public void draw(Batch batch, float parentAlpha) {
        //Esta cuenta hace falta por lo de la media altura. Ese absurdo cálculo...
        sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
        //Sprite quiere la rotación en grados, el cuerpo la da en radianes. Esta constante convierte de uno a otro.
        sprite.setRotation(MathUtils.radiansToDegrees*cuerpo.getAngle());
        sprite.draw(batch);
    }

    public Body getCuerpo() {
        return cuerpo;
    }
}
