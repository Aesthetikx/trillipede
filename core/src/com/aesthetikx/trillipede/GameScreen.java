package com.aesthetikx.trillipede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Scaling;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen, InputProcessor {

    Trillipede game;
    Texture spriteSheet;
    TextureRegion mushroomFour;
    TextureRegion mushroomThree;
    TextureRegion mushroomTwo;
    TextureRegion mushroomOne;

    TextureRegion shipSprite;
    TextureRegion laserSprite;

    World world;
    Box2DDebugRenderer debugRenderer;

    List<Mushroom> mushrooms;
    Laser laser;
    Ship ship;

    private Camera camera;

    static int WIDTH = 240;
    static int HEIGHT = 256;
    static int FIELD_BOTTOM = HEIGHT - (int) ((HEIGHT) * .75);

    private int touchX;
    private int touchY;

    private boolean moving = false;
    private boolean shooting = false;

    List<Body> removeList;

    public GameScreen(final Trillipede game) {
        this.game = game;

        Gdx.input.setInputProcessor(this);

        world = new World(new Vector2(0,0), true);
        world.setContactListener(new LaserContactListener());
        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH/2, HEIGHT/2, 0);

        spriteSheet = new Texture("sheet.png");

        mushroomFour = new TextureRegion(spriteSheet, 176, 440, 8, 8);
        mushroomThree= new TextureRegion(spriteSheet, 184, 440, 8, 8);
        mushroomTwo = new TextureRegion(spriteSheet, 192, 440, 8, 8);
        mushroomOne = new TextureRegion(spriteSheet, 200, 440, 8, 8);

        shipSprite = new TextureRegion(spriteSheet, 0, 80, 7, 8);

        laserSprite = new TextureRegion(spriteSheet, 11, 80, 1, 6);

        ship = new Ship(game, this);
        ship.setX((WIDTH / 2) - shipSprite.getRegionWidth() / 2);
        ship.setY(0);
        mushrooms = createMushrooms();

        removeList = new ArrayList<Body>();
    }

    private List<Mushroom> createMushrooms() {

        List<Mushroom> mushrooms = new ArrayList<Mushroom>();

        int columns = WIDTH / 8;
        int rows = (int) ((HEIGHT / 8) * .75);

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (MathUtils.random(0, 100) > 80) {
                    Mushroom m = new Mushroom(game, this, x*8, HEIGHT -(y*8));
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

        debugRenderer.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.projection);
        game.batch.setTransformMatrix(camera.view);

        game.batch.begin();

        for (Mushroom m: mushrooms) {
            m.draw(game.batch);
        }

        if (shooting) {
            ship.shoot();
        }

        if (laser != null) {
            laser.draw(game.batch);
        }

        ship.draw(game.batch);

        game.batch.end();

        //world.step(1/60f, 5, 5);
        world.step(delta, 6, 2);

        for (Body b : removeList) world.destroyBody(b);
        removeList.clear();
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
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
        if (pointer == 0) {
            moving = true;
            touchX = screenX;
            touchY = screenY;
            return true;
        } else if (pointer == 1) {
            shooting = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer == 0) {
            moving = false;
            return true;
        } else if (pointer == 1) {
            shooting = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer == 0) {
            float dx = (screenX - touchX) * .2f;
            float dy = (screenY - touchY) * .2f;
            ship.translate(dx, -dy);
            touchX = screenX;
            touchY = screenY;
            return true;
        }
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
