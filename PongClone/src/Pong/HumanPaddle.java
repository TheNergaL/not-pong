package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle {
	double y, dy;
	boolean up, down;
	int player, x;
	final double GRAVITY = 0.94;

	public HumanPaddle(int player) {
		up = false;
		down = false;
		y = 210;
		dy = 0;
		if (player == 1) {
			x = 20;
		} else {
			x = 660;
		}
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(x, (int) y, 20, 80);
	}

	public void move() {
		// TODO Auto-generated method stub
		if (up) {
			dy -= 2;
		} else if (down) {
			dy += 2;
		} else if (!up && !down) {
			dy *= GRAVITY;
		}

		if (dy >= 5) {
			dy = 5;
		} else if (dy <= -5) {
			dy = -5;
		}
		y += dy; 
		
	}

	public void setUp(boolean input) {
		up = input;
	}

	public void setDown(boolean input) {
		down = input;
	}

	public int getY() {
		// TODO Auto-generated method stub

		return (int) y;
	}

}
