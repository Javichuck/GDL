package main;

public class Bullet extends DinamicItem{
	
	public Bullet(double x, double y, Game game) {
		super(x,y);
		image = game.getSpriteSheet().grabImage(2, 1, 32, 32);
		velY=-5;
	}
	
	@Override
	public void tick(){
		y += velY;
	}

}
