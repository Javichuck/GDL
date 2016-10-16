package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DinamicItem {
	protected double x,y;
	protected BufferedImage image;
	
	protected double velX = 0;
	protected double velY = 0;
	
	public DinamicItem(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(){
	}
	
	public void render(Graphics g){
		g.drawImage(image, (int)x, (int)y, null);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

}
