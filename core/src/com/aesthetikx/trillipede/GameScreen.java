package com.aesthetikx.trillipede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {

    Trillipede game;
    Texture img;
    Texture spriteSheet;
    TextureRegion mushroom;

    public GameScreen(final Trillipede game) {
        this.game = game;
        img = new Texture("badlogic.jpg");
        spriteSheet = new Texture("sheet.png");
        mushroom = new TextureRegion(spriteSheet, 176, 440, 8, 8);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(spriteSheet, 0, 0);
        game.batch.draw(img, 0, 0);
        game.batch.draw(mushroom, 0, 0);
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
