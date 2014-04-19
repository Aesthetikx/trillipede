package com.aesthetikx.trillipede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    Trillipede game;
    Texture spriteSheet;
    TextureRegion mushroomFour;
    TextureRegion mushroomThree;
    TextureRegion mushroomTwo;
    TextureRegion mushroomOne;

    TextureRegion shipSprite;

    List<Mushroom> mushrooms;
    Ship ship;

    private Camera camera;

    static int WIDTH = 240;
    static int HEIGHT = 256;

    public GameScreen(final Trillipede game) {
        this.game = game;

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH/2, HEIGHT/2, 0);

        spriteSheet = new Texture("sheet.png");

        mushroomFour = new TextureRegion(spriteSheet, 176, 440, 8, 8);
        mushroomThree= new TextureRegion(spriteSheet, 184, 440, 8, 8);
        mushroomTwo = new TextureRegion(spriteSheet, 192, 440, 8, 8);
        mushroomOne = new TextureRegion(spriteSheet, 200, 440, 8, 8);

        shipSprite = new TextureRegion(spriteSheet, 0, 80, 7, 8);

        ship = new Ship(game, this);
        ship.setX((WIDTH / 2) - shipSprite.getRegionWidth() / 2);
        ship.setY(0);
        mushrooms = createMushrooms();
    }

    private List<Mushroom> createMushrooms() {

        List<Mushroom> mushrooms = new ArrayList<Mushroom>();

        int columns = WIDTH / 8;
        int rows = (int) ((HEIGHT / 8) * .75);

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (MathUtils.random(0, 100) > 80) {
                    Mushroom m = new Mushroom(game, this);
                    m.setX(x * 8);
                    m.setY(HEIGHT - (y * 8));
                    mushrooms.add(m);
                }
            }
        }

        return mushrooms;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.projection);
        game.batch.setTransformMatrix(camera.view);

        game.batch.begin();
        for (Mushroom m: mushrooms) {
            m.draw(game.batch);
        }
        ship.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        Vector2 size = Scaling.fit.apply(WIDTH, HEIGHT, width, height);
        int viewportX = (int) (width - size.x) / 2;
        int viewportY = (int) (height - size.y) / 2;
        int viewportWidth = (int) size.x;
        int viewportHeight = (int) size.y;
        Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
