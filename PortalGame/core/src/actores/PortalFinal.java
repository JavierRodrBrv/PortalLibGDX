package actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class PortalFinal extends PortalPadre {
    public PortalFinal(World m, float x, float y) {
        super(m, x, y);
        sprite=new Sprite(new Texture("texturaPersonajes/portalFinal.png"));
        sprite.setBounds(x,y,anchuraSprite,alturaSprite);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
