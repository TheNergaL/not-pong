package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Ball {
	double dx, dy, x, y;
	File boing = new File("../Externals/mario-boing.wav");
	/* TODO: add player and AI scores/sounds */
	File one_up = new File("../Externals/smb3_sound_effects_1_up.wav");
	
	private void playSound(File audio) throws IOException, UnsupportedAudioFileException {
		try {
			Clip sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(audio));
			sound.start();
		} catch (LineUnavailableException e) {
		
			e.printStackTrace();
		}
	}

	public Ball() {
		x = 700 / 2;
		y = 500 / 2;
		dx = getSpeed() * getDir();
		dy = getSpeed() * getDir();
	}

	public double getSpeed() {
		return (Math.random() * 3 + 2);
	}

	public int getDir() {
		int rand = (int) (Math.random() * 2);
		if (rand == 1) {
			return 1;
		} else {
			return -1;
		}
	}

	public void move() {
		x += dx;
		y += dy;

		if (y < 10) {
			dy = -dy;
		}
		if (y > 490) {
			dy = -dy;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int) x - 10, (int) y - 10, 20, 20);
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void checkCollision(Paddle p1, Paddle p2) {
		if (x <= 50) {
			if (y >= p1.getY() && y <= p1.getY() + 80) {
				try {
					playSound(boing);
				} catch (IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
				dx = -dx;
			}
		} else if (x >= 650) {
			if (y >= p2.getY() && y <= p2.getY() + 80) {
				try {
					playSound(boing);
				} catch (IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
				dx = -dx;
			}

		}
	}

}
