package actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
/**
 * Clase que modela el actor de PortalFinal que extiende PortalPadre.
 * @author Javier Rodríguez Bravo
 */
public class PortalFinal extends PortalPadre {
    /**
     * Constructor de PortalFinal que recibe:
     * @param m el mundo.
     * @param x la coordenada x.
     * @param y la coordenada y.
     */
    public PortalFinal(World m, float x, float y) {
        super(m, x, y);
        //A esta clase le tenemos que pasar manualmente la textura y el setBounds.
        sprite=new Sprite(new Texture("texturaPersonajes/portalFinal.png"));
        sprite.setBounds(x,y,anchuraSprite,alturaSprite);
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
