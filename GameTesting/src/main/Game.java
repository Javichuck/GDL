package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = WIDTH / 16*9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Game";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private SpriteSheet spriteSheet = null;
	
	private Player p;
	private Controller c;
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
			case(KeyEvent.VK_RIGHT): p.setVelX(5); break;
			case(KeyEvent.VK_LEFT): p.setVelX(-5); break;
			case(KeyEvent.VK_UP): p.setVelY(-5); break;
			case(KeyEvent.VK_DOWN): p.setVelY(5); break;
			case(KeyEvent.VK_ENTER): c.addBullet(new Bullet(p.x, p.y, this));
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
			case(KeyEvent.VK_RIGHT): p.setVelX(0); break;
			case(KeyEvent.VK_LEFT): p.setVelX(0); break;
			case(KeyEvent.VK_UP): p.setVelY(0); break;
			case(KeyEvent.VK_DOWN): p.setVelY(0); break;
		}
	}
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			BufferedImage ss = loader.loadImage("/sprite_sheet.png");
			spriteSheet = new SpriteSheet(ss);
		}catch(IOException e){
			e.printStackTrace();
		}
		addKeyListener(new KeyInput(this));
		p = new Player(0, 200, this);
		c = new Controller();
	}
	
	private synchronized void start(){
		if(!running){
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop(){
		if(running){
			running = false;
			try{
				thread.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.exit(1);
		}
	}
	
	private void tick(){
		p.tick();
		c.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//render
		g.drawImage(image, 0, 0, getWidth(),getHeight(), this);
		p.render(g);
		c.render(g);
		//render
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
				
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + "Ticks, Fps" + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		game.setPreferredSize(dimension);
		game.setMaximumSize(dimension);
		game.setMinimumSize(dimension);
		
		JFrame frame = new JFrame(game.TITLE);
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(dimension);
		panel.setBorder(null);
		frame.getContentPane().add(panel);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		game.start();
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}	
	
}
