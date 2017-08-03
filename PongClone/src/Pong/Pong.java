package Pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener {
	/**
	 * 
	 */
	Thread thread;
	final int WIDTH = 700, HEIGHT = 500;
	HumanPaddle p1;
	AIPaddle p2;
	Ball b1;
	boolean begun;
	Graphics gfx;
	Image img;
	int player_score, ai_score;

	public void init() {
		player_score = 0;
		ai_score = 0;
		begun = false;
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		this.resize(WIDTH, HEIGHT);
		thread = new Thread(this);
		p1 = new HumanPaddle(1);
		this.addKeyListener(this);
		b1 = new Ball();
		p2 = new AIPaddle(2, b1);
		thread.start();

	}

	@Override
	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		if (b1.getX() < -10 || b1.getX() > 710) {
			g.drawString("Game Over", 350, 250);
		} else {
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
		}

		if (!begun) {
			gfx.setColor(Color.white);
			gfx.drawString("Totally Not Pong!", 340, 100);
			gfx.drawString("Press Enter to Play", 340, 130);
		}
		g.drawImage(img, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public boolean checkRestart(Ball b) {
		boolean restart = false;
		b = b1;
		if (b.getX() < 0) {
			ai_score++;
			restart = true;
		}
		if (b.getX() > 700) {
			player_score++;
			restart = true;
		}
		return restart;
	}

	public void reset() {
		if (checkRestart(b1)) {
			b1 = new Ball();
			p1 = new HumanPaddle(1);
			p2 = new AIPaddle(2, b1);
		}
	}

	public void run() {
		while (true) {
			if (begun) {
				repaint();
				p1.move();
				b1.move();
				p2.move();
				b1.checkCollision(p1, p2);
				reset();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUp(true);
		} else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDown(true);
		} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			begun = true;
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUp(false);
		} else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDown(false);
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
