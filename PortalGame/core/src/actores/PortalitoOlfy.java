package actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

public class PortalitoOlfy extends PortalPadre {
    public PortalitoOlfy(World m, float x, float y) {
        super(m, x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
