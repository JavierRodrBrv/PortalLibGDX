package actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Clase que modela el actor de PortalitoUan que extiende PortalPadre.
 * @author Javier Rodríguez Bravo
 */
public class PortalitoUan extends PortalPadre {
    /**
     * Constructor de PortalitoUan que recibe:
     * @param m el mundo.
     * @param x la coordenada x.
     * @param y la coordenada y.
     */
    public PortalitoUan(World m, float x, float y) {
        super(m, x, y);
    }

    /**
     * Función draw que pinta el portal, contiene:
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
