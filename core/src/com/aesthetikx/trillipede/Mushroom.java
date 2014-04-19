package com.aesthetikx.trillipede;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Mushroom extends Sprite {

    private int health;
    private Trillipede game;
    private GameScreen screen;

    public Mushroom(Trillipede game, GameScreen screen) {
        this.game = game;
        this.screen = screen;
        health = 4;
    }

    @Override
    public void draw(Batch batch) {
        TextureRegion region;
        switch (health) {
            case 4: region = screen.mushroomFour; break;
            case 3: region = screen.mushroomThree; break;
            case 2: region = screen.mushroomTwo; break;
            default: region = screen.mushroomOne; break;
        }
        batch.draw(region, getX(), getY());
    }

}
