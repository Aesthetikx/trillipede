package com.aesthetikx.trillipede;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Mushroom extends Rectangle {

    private int health;
    private Trillipede game;
    private GameScreen screen;

    public Mushroom(Trillipede game, GameScreen screen) {
        this.game = game;
        this.screen = screen;
        health = 4;
    }

    public void draw() {
        TextureRegion region;
        switch (health) {
            case 4: region = screen.mushroomFour; break;
            case 3: region = screen.mushroomThree; break;
            case 2: region = screen.mushroomTwo; break;
            default: region = screen.mushroomOne; break;
        }
        game.batch.draw(region, x, y);
    }

}
