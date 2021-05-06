package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W && Enviroment.snake.getDirectionY() != 1) {
			Enviroment.snake.setDirectionX(0);
			Enviroment.snake.setDirectionY(-1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_A && Enviroment.snake.getDirectionX() != 1) {
			Enviroment.snake.setDirectionX(-1);
			Enviroment.snake.setDirectionY(0);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S && Enviroment.snake.getDirectionY() != -1) {
			Enviroment.snake.setDirectionX(0);
			Enviroment.snake.setDirectionY(1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D && Enviroment.snake.getDirectionX() != -1) {
			Enviroment.snake.setDirectionX(1);
			Enviroment.snake.setDirectionY(0);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
