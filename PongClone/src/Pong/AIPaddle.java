package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle implements Paddle {
	double y, dy;
	boolean up, down;
	int player, x;
	final double GRAVITY = 0.94;
	Ball b1;

	public AIPaddle(int player, Ball b) {
		up = false;
		down = false;
		y = 210;
		dy = 0;
		b1 = b;
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
		y = b1.getY() - 40;
		if (y < 0) {
			y = 0;
		}
		if (y > 420) {
			y = 420;
		}

		y += dy;

	}

	public int getY() {
		// TODO Auto-generated method stub

		return (int) y;
	}

}
