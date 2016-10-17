package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

	private LinkedList<DinamicItem> bullets = new LinkedList<DinamicItem>();
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	private Textures tex;
	private int level = 1;
	private int streak = 0;

	public Controller(Textures tex) {
		this.tex = tex;
		addEnemy(new Enemy(Math.random() * Game.WIDTH * Game.SCALE,0,tex));
	}

	public void tick(){
		LinkedList<DinamicItem> toRemove = new LinkedList<DinamicItem>();
		for(DinamicItem bullet : bullets){
			if(bullet.getY()<0)toRemove.add(bullet);
			else bullet.tick();
		}
		LinkedList<Enemy> enemiesAux = (LinkedList<Enemy>) enemies.clone();
		for(Enemy e : enemiesAux){
			DinamicItem ex = (DinamicItem)e;
			if(e.getY() >= Game.HEIGHT * Game.SCALE)toRemove.add(e);
			else if(Physics.collision(ex, bullets)){
				toRemove.add(e);
				streak++;
				if(streak>=level){
					streak = 0;
					level++;
					addEnemies(level);
				}
			}
			else e.tick();
		}
		for(DinamicItem item : toRemove){
			if(item instanceof Bullet)bullets.remove(item);
			else if(item instanceof Enemy)enemies.remove(item);
		}
	}

	public void render(Graphics g){
		try{
			for(DinamicItem bullet : bullets){
				bullet.render(g);
			}
			for(Enemy e : enemies){
				e.render(g);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private void addEnemies(int c){
		while(c > 0){
			addEnemy(new Enemy(Math.random() * Game.WIDTH * Game.SCALE,0,tex));
			c--;
		}
	}

	public void addBullet(Bullet bullet){
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet){
		bullets.remove(bullet);
	}

	public void addEnemy(Enemy e){
		enemies.add(e);
	}

	public void removeEnemy(Enemy e){
		enemies.remove(e);
	}

}
