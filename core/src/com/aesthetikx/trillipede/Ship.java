package com.aesthetikx.trillipede;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship extends Sprite {

    TextureRegion region;
    GameScreen screen;

    private long lastShot = 0;

    public Ship(Trillipede game, GameScreen screen) {
        this.screen = screen;
        region = screen.shipSprite;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(region, getX(), getY());
    }

    @Override
    public void translate(float dx, float dy) {
        if (getX() + dx > (screen.WIDTH - region.getRegionWidth())) dx = screen.WIDTH - region.getRegionWidth() - getX();
        if (getX() + dx < 0) dx = -getX();
        if (getY() + dy < 0) dy = -getY();
        if (getY() + dy > screen.FIELD_BOTTOM) dy = screen.FIELD_BOTTOM - getY();
        super.translate(dx, dy);
    }

    public void shoot() {
        if (screen.laser == null) {
            float x = getX() + region.getRegionWidth() / 2;
            float y = getY() + 3;
            Laser l = new Laser(screen, x, y);
            screen.laser = l;
        }
    }
}
