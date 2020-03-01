package botones;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BotonArriba extends Actor {
    private Sprite sprite;
    public BotonArriba(float x, float y){

        sprite=new Sprite(new Texture("botones/botonArriba.png"));
        sprite.setBounds(x,y, 1,1);
        this.setPosition(x,y);
        this.setSize(sprite.getWidth(),sprite.getHeight());


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.setPosition(getX(),getY());
        sprite.setScale(getScaleX(),getScaleY());
        sprite.draw(batch);
    }


}
