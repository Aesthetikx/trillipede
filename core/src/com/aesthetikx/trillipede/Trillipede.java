package com.aesthetikx.trillipede;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Trillipede extends Game {
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
        this.setScreen(new GameScreen(this));
	}

}
