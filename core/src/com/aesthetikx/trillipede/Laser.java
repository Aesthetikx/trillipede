package com.aesthetikx.trillipede;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Laser extends Sprite {

    private TextureRegion region;
    private GameScreen screen;

    private Body laserBody;
    private PolygonShape laserShape;

    public Laser(GameScreen screen, float x, float y) {
        this.region = screen.laserSprite;
        this.screen = screen;

        BodyDef laserDef = new BodyDef();
        laserDef.type = BodyDef.BodyType.KinematicBody;
        laserDef.position.set(new Vector2(x / 100.0f, y / 100.0f));
        laserBody = screen.world.createBody(laserDef);
        laserShape = new PolygonShape();
        laserShape.setAsBox(.1f, .7f);
        laserBody.createFixture(laserShape, 0.0f);

        laserBody.setLinearVelocity(0.0f, 2.5f);
        laserBody.setLinearDamping(0);
    }

    @Override
    public void draw(Batch batch) {
        float x = 100 * laserBody.getPosition().x;
        float y = 100 * laserBody.getPosition().y;
        batch.draw(region, x, y);

        if (y > screen.HEIGHT) {
            screen.world.destroyBody(laserBody);
            screen.laser = null;
        }
    }

    @Override
    public void translate(float dx, float dy) {
        if (getY() + dy > GameScreen.HEIGHT) {
            screen.laser = null;
        } else {
            super.translate(dx, dy);
        }
    }

    public void dispose() {
        laserShape.dispose();
    }
}
