package main;

import java.util.LinkedList;

public class Physics {
	
	public static boolean collision(DinamicItem item, LinkedList<DinamicItem> objects){
		
		for(DinamicItem object : objects){
			if(item.getBounds().intersects(object.getBounds()))return true;
		}
		return false;
	}

}
