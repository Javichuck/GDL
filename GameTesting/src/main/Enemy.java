package main;

public class Enemy extends DinamicItem{
	
	public Enemy(double x, double y, Textures tex) {
		super(x,y);
		image = tex.enemy;
		velY = 1;
	}		
	
	@Override
	public void tick(){
		y += velY;
	}
	
}
