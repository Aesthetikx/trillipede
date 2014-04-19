package com.aesthetikx.trillipede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    Trillipede game;
    Texture img;
    Texture spriteSheet;
    TextureRegion mushroomFour;
    TextureRegion mushroomThree;
    TextureRegion mushroomTwo;
    TextureRegion mushroomOne;

    List<Mushroom> mushrooms;

    public GameScreen(final Trillipede game) {
        this.game = game;
        img = new Texture("badlogic.jpg");
        spriteSheet = new Texture("sheet.png");
        mushroomFour = new TextureRegion(spriteSheet, 176, 440, 8, 8);
        mushroomThree= new TextureRegion(spriteSheet, 184, 440, 8, 8);
        mushroomTwo = new TextureRegion(spriteSheet, 192, 440, 8, 8);
        mushroomOne = new TextureRegion(spriteSheet, 200, 440, 8, 8);
        mushrooms = createMushrooms();
    }

    private List<Mushroom> createMushrooms() {
        List<Mushroom> mushrooms = new ArrayList<Mushroom>();
        for (int i = 0; i < 100; i++) {
            int x = MathUtils.random(0, 1080);
            int y = MathUtils.random(0, 1920);
            Mushroom m = new Mushroom(game, this);
            m.setX(x);
            m.setY(y);
            mushrooms.add(m);
        }
        return mushrooms;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(spriteSheet, 0, 0);
        game.batch.draw(img, 0, 0);
        for (Mushroom m: mushrooms) {
            m.draw(game.batch);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
