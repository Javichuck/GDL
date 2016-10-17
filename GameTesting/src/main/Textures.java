package main;

import java.awt.image.BufferedImage;

public class Textures {
	
	private SpriteSheet ss;
	public BufferedImage player, bullet, enemy;
	
	public Textures(Game game) {
		ss = game.getSpriteSheet();
		getTextures();
	}
	
	private void getTextures(){
		player = ss.grabImage(1, 1, 32, 32);
		bullet = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(3, 1, 32, 32);
	}

}
