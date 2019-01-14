package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.neuedu.maplestory.img.Img;

/**
 * Hero
 * 
 * @author Lain
 *
 */
public class Hero {
	public Image img;
	public int x, y;
	public boolean left, right;
	public int speed = 10;

	public Hero(Image img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
	}

	public Hero() {
		this(Img.imgHero.stand.l[1], 800, 200);
	}

	public void move() {

		//left
		if (left) {
			x -= speed;
		}
		//right
		if (right) {
			x += speed;
		}
		
	}

	public void draw(Graphics g) {
		move();
		g.drawImage(img, x, y, null);
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_J:
			break;
		case KeyEvent.VK_K:
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_J:
			break;
		case KeyEvent.VK_K:
			break;
		default:
			break;
		}
	}
}
