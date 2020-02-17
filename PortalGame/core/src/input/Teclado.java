package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import personaje.Astronauta;



public class Teclado implements InputProcessor {
    private Astronauta actor;
    private Sprite sprite;

    public Teclado(Astronauta j){
        this.actor=j;
    }


    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("eventoDown","Input "+keycode);
        switch (keycode) {
            case Input.Keys.LEFT:
                actor.getCuerpo().applyForceToCenter(-20,20,true);
                //actor.setSprite(sprite=new Sprite(new Texture("texturaPersonajes/personajeIzq.png")));
                break;
            case Input.Keys.R:
                actor.getCuerpo().setLinearVelocity(new Vector2(0,0));
                actor.getCuerpo().setAngularVelocity(0);
                actor.getCuerpo().setTransform(5,26,0);
                break;
            case Input.Keys.RIGHT:
                actor.getCuerpo().applyForceToCenter(20,20,true);
                //actor.setSprite(sprite=new Sprite(new Texture("texturaPersonajes/personajeDcha.png")));
                break;
            case Input.Keys.UP:
                actor.getCuerpo().applyForceToCenter(0,100,true);
                break;
    }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
