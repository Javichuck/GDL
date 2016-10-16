package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	
	Bullet tempBullet;
	
	public void tick(){
		LinkedList<Bullet> toRemove = new LinkedList<Bullet>();
		for(Bullet bullet : b){
			if(bullet.getY()<0)toRemove.add(bullet);
			else bullet.tick();
		}
		for(Bullet bullet : toRemove){
			b.remove(bullet);
		}
	}
	
	public void render(Graphics g){
		for(Bullet bullet : b){
			bullet.render(g);
		}
	}
	
	public void addBullet(Bullet bullet){
		b.add(bullet);
	}
	
	public void removeBullet(Bullet bullet){
		b.remove(bullet);
	}

}
