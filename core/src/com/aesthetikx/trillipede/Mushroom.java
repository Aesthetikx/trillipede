package com.aesthetikx.trillipede;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Mushroom extends Sprite implements GameItem {

    private int health;
    private Trillipede game;
    private GameScreen screen;

    private Body body;
    private PolygonShape shape;

    public Mushroom(Trillipede game, GameScreen screen, float x, float y) {
        this.game = game;
        this.screen = screen;
        health = 4;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        def.position.set(new Vector2((x + 4) / 10.0f, (y + 4) / 10.0f));
        body = screen.world.createBody(def);
        shape = new PolygonShape();
        shape.setAsBox(.4f, .4f);
        body.createFixture(shape, 1.0f);
        body.setUserData(this);
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

        float x = (10 * body.getPosition().x) - 4;
        float y = (10 * body.getPosition().y) - 4;

        batch.draw(region, x, y);
    }

    public void destroy() {
        screen.mushrooms.remove(this);
        screen.removeList.add(body);
        shape.dispose();
    }

    @Override
    public void collision(Object other) {
        if (other instanceof Laser) {
            health--;
            if (health == 0) destroy();
        }
    }
}
