package botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.portal.game.Juego;

public class Botones  {
    private Viewport viewport;
    private Stage stage;
    boolean arriba, izquierda, derecha;
    private OrthographicCamera camera;

    public Botones() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 480, camera);
        stage = new Stage(viewport, Juego.batch2);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();


        Image imagenArriba = new Image(new Texture("botones/botonArriba.png"));
        imagenArriba.setSize(50, 50);
        imagenArriba.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Hola");
                arriba = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Hola");
                arriba = false;
            }
        });

        Image imagenIzquierda = new Image(new Texture("botones/botonIzq.png"));
        imagenIzquierda.setSize(50, 50);
        imagenIzquierda.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                izquierda = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                izquierda = false;
            }
        });

        Image imagenDerecha = new Image(new Texture("botones/botonDcha.png"));
        imagenDerecha.setSize(50, 50);
        imagenDerecha.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                derecha = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                derecha = false;
            }
        });

        table.add();
        table.add(imagenArriba).size(imagenArriba.getWidth(),imagenArriba.getHeight());
        table.add();
        table.row().pad(5,5,5,5);
        table.add(imagenIzquierda).size(imagenIzquierda.getWidth(),imagenIzquierda.getHeight());
        table.add();
        table.add(imagenDerecha).size(imagenDerecha.getWidth(),imagenDerecha.getHeight());

        stage.addActor(table);


    }
    public void draw(){
        stage.draw();
    }

    public boolean isArriba() {
        return arriba;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void resize(int width,int height){
        viewport.update(width,height);

    }
}
