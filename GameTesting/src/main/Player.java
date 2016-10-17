package main;

public class Player extends DinamicItem{
	
	public Player(double x, double y, Textures tex) {
		super(x, y);
		image = tex.player;
	}
	
	@Override
	public void tick(){
		x += velX;
		y += velY;
		if(x<0) x = 0;
		if(y<0) y = 0;
		if(x>Game.WIDTH * Game.SCALE - 30) x = Game.WIDTH * Game.SCALE - 30;
		if(y>Game.HEIGHT * Game.SCALE - 31) y = Game.HEIGHT * Game.SCALE - 31;
	}	
	
}
