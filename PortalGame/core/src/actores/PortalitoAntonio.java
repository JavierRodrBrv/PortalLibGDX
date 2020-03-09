package actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
/**
 * Clase que modela el actor de PortalitoAntonio que extiende PortalPadre.
 * @author Javier Rodríguez Bravo
 */
public class PortalitoAntonio extends PortalPadre {
    /**
     * Constructor de PortalitoDarash que recibe:
     * @param m el mundo.
     * @param x la coordenada x.
     * @param y la coordenada y.
     */
    public PortalitoAntonio(World m, float x, float y) {
        super(m, x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
