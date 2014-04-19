package com.aesthetikx.trillipede;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Laser extends Sprite {

    private TextureRegion region;
    private GameScreen screen;

    public Laser(GameScreen screen) {
        this.region = screen.laserSprite;
        this.screen = screen;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(region, getX(), getY());
    }

    @Override
    public void translate(float dx, float dy) {
        if (getY() + dy > GameScreen.HEIGHT) {
            screen.laser = null;
        } else {
            super.translate(dx, dy);
        }
    }
}
