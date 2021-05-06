package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Enviroment extends JPanel {
	private static final long serialVersionUID = 1L;
	public static JFrame f = new JFrame();
	public static final int WIDTH = 600 + 16;
	public static final int HEIGHT = 600 + 39;
	public static int[][] map;
	public static ArrayList<Spot> food = new ArrayList<>();
	public static boolean playing = true;
	public static Snake snake;
	public static boolean warp = true;
	
	public void paintComponent(Graphics g) {
		int boxSize = WIDTH / map.length;
		//Draw map pixels
		g.setColor(Color.BLACK);
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				g.drawRect(boxSize * i, boxSize * j, boxSize, boxSize);
			}
		}
		
		//Draw snake
		g.setColor(Color.GREEN);
		g.fillRect(boxSize * snake.getLocation().getX(), boxSize * snake.getLocation().getY(), boxSize, boxSize);
		
		g.setColor(new Color(255, 255, 50));
		for(Spot s : snake.getBody()) 
			g.fillRect(boxSize * s.getX(), boxSize * s.getY(), boxSize, boxSize);
		
		g.setColor(Color.RED);
		for(Spot s : food)
			g.fillRect(boxSize * s.getX(), boxSize * s.getY(), boxSize, boxSize);
	}
	
	public static void main(String args[]) {
		map = new int[60][60];
		snake = new Snake(20, 20);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(WIDTH, HEIGHT);
		f.setBackground(Color.DARK_GRAY);
		f.add(new Enviroment());
		f.setVisible(true);
		f.addKeyListener(new KeyHandler());
		
		Object monitor = new Object();
		int timer = 0;
        synchronized(monitor) {
            while(true) {
            	f.repaint();
            	
            	if(playing)
            		snake.move();
            	
            	if(timer > 20) {
            		spawnFood();
            		timer = 0;
            	}
            	timer++;
            	
                try{Thread.sleep(100);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
            }
        }
        
	}
	
	public static void checkCollisions() {
		Spot loc = snake.getLocation();
		
		for(Spot s : Enviroment.food) {
			if(s.equals(loc)) {
				snake.grow();
				Enviroment.food.remove(s);
				break;
			}
		}
		
		for(Spot s : snake.getBody()) {
			if(s.equals(loc))
				die();
		}
		
		if(!warp) {
			if(loc.getX() < 0 || loc.getX() > WIDTH || loc.getY() < 0 || loc.getY() > HEIGHT)
				die();
		}
		else {
			if(loc.getX() < 0)
				snake.setLocation(new Spot(map.length, loc.getY()));
			if(loc.getX() > map.length)
				snake.setLocation(new Spot(0, loc.getY()));
			if(loc.getY() < 0)
				snake.setLocation(new Spot(loc.getX(), map[0].length));
			if(loc.getY() > map[0].length)
				snake.setLocation(new Spot(loc.getX(), 0));
		}
	}
	
	public static void die() {
		playing = false;
	}
	
	public static void spawnFood() {
		boolean good = false;
		Spot s = new Spot(0, 0);
		while(!good) {
			s = new Spot((int)(Math.random() * map.length), (int)(Math.random() * map[0].length));
			good = true;
			
			for(Spot sp : snake.getBody())
				if(s.equals(sp))
					good = false;
			
			if(s.equals(snake.getLocation()))
				good = false;
			
			for(Spot sp : food)
				if(s.equals(sp))
					good = false;
		}
		
		food.add(s);
	}
	
}
