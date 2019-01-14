package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

/**
 * Hero
 * 
 * @author Lain
 *
 */
public class Hero {
	public Image[] img;
	public int x, y;
	public boolean left, right, shoot, jump, skill;
	public Direction dire;
	public Action action;
	public int width;
	public int height;
	public int speed;
	private int count = 0;

	public Hero(Image[] img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.left = false;
		this.right = false;
		this.shoot = false;
		this.jump = false;
		this.skill = false;
		this.dire = Direction.right;
		this.action = Action.stand;
		this.speed = 10;

		try {
			this.width = img[0].getWidth(null);
			this.height = img[0].getHeight(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public Hero() {
		this(ImageUtil.imgHero.stand.l, 800, 0);
		this.y = Constant.GAME_HEIGHT - this.height - 140;
	}

	/**
	 * move
	 */
	public void move() {

		if (left) {
			this.dire = Direction.left;
			this.action = Action.walk;
		} else if (right) {
			this.dire = Direction.right;
			this.action = Action.walk;
		} else {
			this.action = Action.stand;
		}

		if (shoot) {
			this.action = Action.shoot;
		}

		if (skill) {
			this.action = Action.skill;
		}

		if (jump) {
			this.action = Action.jump;
			jump();
		}

		switch (this.dire) {
		case left:
			switch (this.action) {
			case walk:
				x -= speed;
				img = ImageUtil.imgHero.walk.l;
				break;
			case shoot:
				img = ImageUtil.imgHero.shoot.l;
				break;
			case jump:
				if (left) {
					x -= speed;
				}
				if (shoot) {
					img = ImageUtil.imgHero.shoot.l;
				} else {
					img = ImageUtil.imgHero.jump.l;
				}
				break;
			case stand:
				img = ImageUtil.imgHero.stand.l;
				break;
			case skill:
				img = ImageUtil.imgHero.skill.l;
				break;
			}
			break;
		case right:
			switch (this.action) {
			case walk:
				x += speed;
				img = ImageUtil.imgHero.walk.r;
				break;
			case shoot:
				img = ImageUtil.imgHero.shoot.r;
				break;
			case jump:
				if (right) {
					x += speed;
				}
				if (shoot) {
					img = ImageUtil.imgHero.shoot.r;
				} else {
					img = ImageUtil.imgHero.jump.r;
				}
				break;
			case stand:
				img = ImageUtil.imgHero.stand.r;
				break;
			case skill:
				img = ImageUtil.imgHero.skill.r;
				break;
			}
			break;
		}
		outOfBounds();
	}

	/**
	 * out of bounds
	 */
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

	/**
	 * jump_Args
	 */
	public double v0 = 40;
	public double vt = 0;
	public static final double g = Constant.G;
	public double t = 0.5;
	public double delta_height = 0;
	public boolean jump_up = true;
	public int sy;

	/**
	 * jump Method
	 */
	public void jump() {
		if (jump_up) {
			vt = v0 - g * t;
			delta_height = v0 * t;
			v0 = vt;
			y -= delta_height;
			if (vt <= 0) {
				jump_up = false;
				vt = 0;
				v0 = 0;
			}
		} else {
			vt = v0 + g * t;
			delta_height = v0 * t;
			v0 = vt;
			y += delta_height;
			if (y >= sy) {
				y = sy;
				this.jump = false;
				v0 = 40;
				vt = 0;
			}
		}
	}

	/**
	 * shoot_Args
	 */
	private List<Bullet> bullets = new LinkedList<>();

	/**
	 * shoot Method
	 */
	void shoot() {
		switch (this.dire) {
		case left:
			for (int i = 0; i < 3; i++) {
				bullets.add(new Bullet(this.x, this.y, -Math.PI + Math.PI / 10 * i));
			}
			break;
		case right:
			for (int i = 0; i < 3; i++) {
				bullets.add(new Bullet(this.x + this.width * 2, this.y, -Math.PI / 10 * i));
			}
			break;
		}
	}

	/**
	 * move Bullets
	 */
	void moveBullets() {
		for (Bullet bullet : bullets) {
			bullet.move();
		}
		bullets.removeIf((e) -> {
			return e.Die();
		});
	}

	/**
	 * skill
	 */
	void skill() {
		final int counts = 36;
		for (int i = 0; i <= counts; i++) {
			bullets.add(new Bullet(this.x, this.y, -Math.PI * 2 / counts * i));
		}
	}

	/**
	 * draw
	 * 
	 * @param g
	 *            g is {@link Graphics}
	 */
	public void draw(Graphics g) {

		move();
		moveBullets();

		for (Bullet bullet : bullets) {
			bullet.draw(g);
		}

		count %= img.length;
		g.drawImage(img[count], x - (img[count].getWidth(null) - this.width),
				y - (img[count].getHeight(null) - this.height), null);
		count++;

	}

	/**
	 * key pressed
	 * 
	 * @param e
	 *            e is {@link KeyEvent}
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_J:
			if(!shoot){
				shoot();
			}
			shoot = true;
			break;
		case KeyEvent.VK_K:
			if (!jump) {
				sy = y;
				jump_up = true;
				jump = true;
			}
			break;
		case KeyEvent.VK_L:
			if (!skill) {
				skill();
			}
			skill = true;
			break;
		default:
			break;
		}
	}

	/**
	 * key released
	 * 
	 * @param e
	 *            e is {@link KeyEvent}
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_J:
			shoot = false;
			break;
		case KeyEvent.VK_K:
			break;
		case KeyEvent.VK_L:
			skill = false;
			break;
		}
	}
}
