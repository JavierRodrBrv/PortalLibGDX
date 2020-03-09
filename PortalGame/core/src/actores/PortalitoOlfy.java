package actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
/**
 * Clase que modela el actor de PortalitoOlfy que extiende PortalPadre.
 * @author Javier Rodríguez Bravo
 */
public class PortalitoOlfy extends PortalPadre {
    /**
     * Constructor de PortalitoOlfy que recibe:
     * @param m el mundo.
     * @param x la coordenada x.
     * @param y la coordenada y.
     */
    public PortalitoOlfy(World m, float x, float y) {
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
