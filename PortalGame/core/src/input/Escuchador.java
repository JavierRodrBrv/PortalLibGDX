package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import actores.Astronauta;

/**
 * Esta clase modela los controles del juego.
 * @author Javier Rodríguez Bravo.
 */
public class Escuchador implements InputProcessor {
    private Astronauta jugador;
    private Sprite sprite;

    /**
     * Este constructor recibe una variable de tipo Astronauta.
     * @param astronauta
     */
    public Escuchador(Astronauta astronauta) {
        this.jugador = astronauta;
    }

    /**
     * Función para controlar al personaje via teclado. No es importante ya que el juego esta destinado a Android.
     * @param keycode recibe por parametros variable de tipo entero.
     * @return devuelve siempre true.
     */
    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("eventoDown", "Input " + keycode);
        switch (keycode) {
            case Input.Keys.LEFT:
                jugador.getCuerpo().setLinearVelocity(new Vector2(-10, 0));
                jugador.setSprite(sprite = new Sprite(new Texture("texturaPersonajes/personajeIzq.png")));
                jugador.getSprite().setSize(1, 1);

                break;
            case Input.Keys.R:
                System.out.println(Input.Keys.R);
                jugador.getCuerpo().setLinearVelocity(new Vector2(0, 0));
                jugador.getCuerpo().setAngularVelocity(0);
                jugador.getCuerpo().setTransform(5, 26, 0);
                break;
            case Input.Keys.RIGHT:
                jugador.getCuerpo().setLinearVelocity(new Vector2(10, 0));
                jugador.setSprite(sprite = new Sprite(new Texture("texturaPersonajes/personajeDcha.png")));
                jugador.getSprite().setSize(1, 1);
                break;
            case Input.Keys.UP:
                jugador.getCuerpo().applyForceToCenter(0, 100, true);
                break;
            case Input.Keys.E:
                jugador.getCuerpo().setTransform(jugador.getX(), jugador.getY(), 0);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        jugador.getCuerpo().setLinearVelocity(new Vector2(0, 0));

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Función para controlar el movimiento del personaje via Android.
     * @param screenX recoge una variable de tipo entero con la coordenada X. Es lo que va a recoger de la pantalla del telefono.
     * @param screenY recoge una variable de tipo entero con la coordenada Y. Es lo que va a recoger de la pantalla del telefono.
     * @param pointer recoge variable de tipo entero. No es importante en nuestro caso.
     * @param button recoge variable de tipo entero. No es importante en nuestro caso.
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //Esta condición marca la parte de la pantalla para marcar el salto del personaje.
        if (screenY < Gdx.graphics.getHeight() / 2.5) {
            if (jugador.getCuerpo().getLinearVelocity().y == 0) {
                jugador.getCuerpo().applyForceToCenter(0, jugador.getSalto(), true);
            }
        //Sino, revisa en esta condición que lo que hace es desplazar el personaje a hacia la izquierda.
        } else if (screenX < Gdx.graphics.getWidth() / 2) {
            jugador.getCuerpo().setLinearVelocity(-jugador.getVelocidad(), jugador.getCuerpo().getLinearVelocity().y);
            jugador.setSprite(sprite = new Sprite(new Texture("texturaPersonajes/personajeIzq.png")));
            jugador.getSprite().setSize(jugador.getAnchuraSprite(), jugador.getAlturaSprite());
            jugador.setDireccion('i');
        //Y por ultimo, revisa en esta condición que lo que hace es desplazar el personaje a hacia la derecha.
        } else if (screenX > Gdx.graphics.getWidth() / 2) {
            jugador.getCuerpo().setLinearVelocity(jugador.getVelocidad(), jugador.getCuerpo().getLinearVelocity().y);
            jugador.setSprite(sprite = new Sprite(new Texture("texturaPersonajes/personajeDcha.png")));
            jugador.getSprite().setSize(jugador.getAnchuraSprite(), jugador.getAlturaSprite());
            jugador.setDireccion('d');
        }
        return false;
    }

    /**
     * Función para controlar cuando se para el movimiento del personaje via Android.
     * @param screenX recoge una variable de tipo entero con la coordenada X. Es lo que va a recoger de la pantalla del telefono.
     * @param screenY recoge una variable de tipo entero con la coordenada Y. Es lo que va a recoger de la pantalla del telefono.
     * @param pointer recoge variable de tipo entero. No es importante en nuestro caso.
     * @param button recoge variable de tipo entero. No es importante en nuestro caso.
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (screenY > Gdx.graphics.getHeight() / 3) {
            if (screenX > Gdx.graphics.getWidth() / 2) {
                jugador.getCuerpo().setLinearVelocity(0, jugador.getCuerpo().getLinearVelocity().y);
                jugador.setDireccion('p');
            } else {
                jugador.getCuerpo().setLinearVelocity(0, jugador.getCuerpo().getLinearVelocity().y);
                jugador.setDireccion('p');
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {

        return false;
    }

}
