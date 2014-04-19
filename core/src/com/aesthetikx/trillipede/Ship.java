package com.aesthetikx.trillipede;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship extends Sprite {

    TextureRegion region;

    public Ship(Trillipede game, GameScreen screen) {
        region = screen.shipSprite;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(region, getX(), getY());
    }
}
