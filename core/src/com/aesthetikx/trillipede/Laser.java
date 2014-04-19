package com.aesthetikx.trillipede;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Laser extends Sprite implements GameItem {

    private TextureRegion region;
    private GameScreen screen;

    private Body laserBody;
    private PolygonShape laserShape;

    public Laser(GameScreen screen, float x, float y) {
        this.region = screen.laserSprite;
        this.screen = screen;

        BodyDef laserDef = new BodyDef();
        laserDef.type = BodyDef.BodyType.DynamicBody;
        laserDef.position.set(new Vector2(x / 10.0f, y / 10.0f));
        laserBody = screen.world.createBody(laserDef);
        laserShape = new PolygonShape();
        laserShape.setAsBox(0.05f, .35f);
        laserBody.createFixture(laserShape, 1.0f);

        laserBody.setLinearVelocity(0.0f, 25.0f);
        laserBody.setLinearDamping(0);
        laserBody.setUserData(this);
    }

    @Override
    public void draw(Batch batch) {
        //float x = 10 * laserBody.getPosition().x;
        //float y = 10 * laserBody.getPosition().y;
        float x = (10 * laserBody.getPosition().x) - 0.5f;
        float y = (10 * laserBody.getPosition().y) - 3.5f;
        batch.draw(region, x, y);

        if (y > screen.HEIGHT) {
            destroy();
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

    public void destroy() {
        screen.removeList.add(laserBody);
        screen.laser = null;
        laserShape.dispose();
    }

    @Override
    public void collision(Object other) {
        if (other instanceof Mushroom) destroy();
    }
}
