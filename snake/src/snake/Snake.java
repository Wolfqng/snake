package snake;

import java.util.ArrayList;

public class Snake {
	private Spot location;
	private ArrayList<Spot> body;
	private int directionX = 1;
	private int directionY;
	
	public Snake(int x, int y) {
		this.location = new Spot(x, y);
		this.body = new ArrayList<>();
		body.add(new Spot(x, y));
		body.add(new Spot(x, y));
		body.add(new Spot(x, y));
	}
	
	public void addBody() {
		//On next move add body part to where the last body part was
	}
	
	public void move() {
		if(!body.isEmpty()) {
			for(int i = body.size() - 1; i > 0; i--) {
				body.set(i, body.get(i - 1));
			}
			
			body.set(0, new Spot(location.getX(), location.getY()));
		}
		
		location.setX(location.getX() + directionX);
		location.setY(location.getY() + directionY);
		
		Enviroment.checkCollisions();
	}
	
	public void grow() {
		Spot last = body.get(body.size() - 1);
		body.add(new Spot(last.getX(), last.getY()));
	}
	
	public int getDirectionX() {
		return directionX;
	}

	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}

	public int getDirectionY() {
		return directionY;
	}

	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}

	public Spot getLocation() {
		return location;
	}

	public void setLocation(Spot location) {
		this.location = location;
	}

	public ArrayList<Spot> getBody() {
		return body;
	}

	public void setBody(ArrayList<Spot> body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Snake [location=" + location + ", directionX=" + directionX
				+ ", directionY=" + directionY + "]";
	}
}
