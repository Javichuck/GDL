package main;

public class Bullet extends DinamicItem{
	
	public Bullet(double x, double y, Textures tex) {
		super(x,y);
		image = tex.bullet;
		velY=-5;
	}
	
	@Override
	public void tick(){
		y += velY;
	}

}
