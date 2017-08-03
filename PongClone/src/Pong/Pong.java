package Pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener {
	Thread thread;
	final int WIDTH = 1920, HEIGHT = 1080;
	
	HumanPaddle p1;

	public void init() {
		this.resize(WIDTH, HEIGHT);
		thread = new Thread(this);
		thread.start();
		p1 = new HumanPaddle(1);
		this.addKeyListener(this);
		
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		p1.draw(g);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		for (;;) {

			p1.move();
			
			repaint();
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
