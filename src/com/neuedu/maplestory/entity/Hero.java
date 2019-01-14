package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.img.Img;

enum Dire {
	left, right
};

/**
 * Hero
 * 
 * @author Lain
 *
 */
public class Hero {
	public Image img;
	public int x, y;
	public boolean attack, jump, runing;
	public Dire dire;
	public int width;
	public int height;
	public int speed;

	public Hero(Image img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.attack = false;
		this.jump = false;
		this.runing = false;
		this.dire = Dire.right;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 10;
	}

	public Hero() {
		this(Img.imgHero.stand.l[1], 800, 0);
		this.y = Constant.GAME_HEIGHT - this.height - 30;
	}

	public void move() {

		switch (this.dire) {
		case left:
			// stand
			if (!runing) {
				img = Img.imgHero.stand.l[0];
			} else {
				x -= speed;
				img = Img.imgHero.walk.l[0];
			}
			// attack
			if (attack) {
				img = Img.imgHero.shoot.l[0];
			}
			// jump
			if (jump) {
				img = Img.imgHero.jump.l;
			}
			break;
		case right:
			// stand
			if (!runing) {
				img = Img.imgHero.stand.r[0];
			} else {
				x += speed;
				img = Img.imgHero.walk.r[0];
			}
			// attack
			if (attack) {
				img = Img.imgHero.shoot.r[0];
			}
			// jump
			if (jump) {
				img = Img.imgHero.jump.r;
			}
			break;
		}
		outOfBounds();

	}

	public void outOfBounds() {

		// left bounds
		if (x < 0) {
			x = 0;
		}

		// right bounds
		if (x > Constant.GAME_WIDTH - this.width) {
			x = Constant.GAME_WIDTH - this.width;
		}
	}

	public void draw(Graphics g) {
		move();
		g.drawImage(img, x, y, null);
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			runing = true;
			dire = Dire.left;
			break;
		case KeyEvent.VK_D:
			runing = true;
			dire = Dire.right;
			break;
		case KeyEvent.VK_J:
			attack = true;
			break;
		case KeyEvent.VK_K:
			jump = true;
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			runing = false;
			break;
		case KeyEvent.VK_D:
			runing = false;
			break;
		case KeyEvent.VK_J:
			attack = false;
			break;
		case KeyEvent.VK_K:
			jump = false;
			break;
		default:
			break;
		}
	}
}
