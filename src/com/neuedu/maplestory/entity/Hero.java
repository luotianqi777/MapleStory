package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

/**
 * Hero
 * 
 * @author Lain
 *
 */
public class Hero extends NPC implements Bloodable {

	public boolean left, right, shoot, jump, skill, hit;
	public Action action;
	private int count = 0;

	/**
	 * jump_Args
	 */
	static public class Jump {
		public static double v0 = 40;
		public static double vt = 0;
		public static final double g = Constant.G;
		public static double t = 0.5;
		public static double delta_height = 0;
		public static boolean jump_up = true;
		public static int sy = 0;
	}

	/**
	 * shoot_Args
	 */
	private List<Bullet> bullets = new LinkedList<>();

	public Hero(Image[] img, int x, int y) {
		super(img, x, y, 100, Constant.HERO_SPEED, Direction.RIGHT);
		this.left = false;
		this.right = false;
		this.shoot = false;
		this.jump = false;
		this.skill = false;
		this.hit = false;
		this.action = Action.STAND;

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
		this.y = Constant.GAME_HEIGHT - this.height - 25;
	}

	/**
	 * move
	 */
	public void move() {

		if (left) {
			this.dire = Direction.LEFT;
			this.action = Action.WALK;
		} else if (right) {
			this.dire = Direction.RIGHT;
			this.action = Action.WALK;
		} else {
			this.action = Action.STAND;
		}

		if (shoot) {
			shoot();
			this.action = Action.SHOOT;
		}

		if (skill) {
			this.action = Action.SKILL;
		}

		if (jump) {
			this.action = Action.JUMP;
			jump();
		}

		if (hit) {
			this.action = Action.HIT;
		}

		switch (this.dire) {
		case LEFT:
			switch (this.action) {
			case WALK:
				x -= speed;
				img = ImageUtil.imgHero.walk.l;
				break;
			case SHOOT:
				img = ImageUtil.imgHero.shoot.l;
				break;
			case JUMP:
				if (left) {
					x -= speed;
				}
				if (shoot) {
					img = ImageUtil.imgHero.shoot.l;
				} else if (skill) {
					img = ImageUtil.imgHero.skill.l;
				} else {
					img = ImageUtil.imgHero.jump.l;
				}
				break;
			case STAND:
				img = ImageUtil.imgHero.stand.l;
				break;
			case SKILL:
				img = ImageUtil.imgHero.skill.l;
				break;
			case HIT:
				break;
			}
			break;
		case RIGHT:
			switch (this.action) {
			case WALK:
				x += speed;
				img = ImageUtil.imgHero.walk.r;
				break;
			case SHOOT:
				img = ImageUtil.imgHero.shoot.r;
				break;
			case JUMP:
				if (right) {
					x += speed;
				}
				if (shoot) {
					img = ImageUtil.imgHero.shoot.r;
				} else if (skill) {
					img = ImageUtil.imgHero.skill.r;
				} else {
					img = ImageUtil.imgHero.jump.r;
				}
				break;
			case STAND:
				img = ImageUtil.imgHero.stand.r;
				break;
			case SKILL:
				img = ImageUtil.imgHero.skill.r;
				break;
			case HIT:
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

		// left bound
		if (x < 0) {
			x = 0;
			MapleStoryClient.backGround.move(Direction.RIGHT);
		}

		// right bound
		if (x > Constant.GAME_WIDTH - this.width) {
			x = Constant.GAME_WIDTH - this.width;
			MapleStoryClient.backGround.move(Direction.LEFT);
		}

		// bullets out of bound
		bullets.removeIf((e) -> {
			return e.isDie();
		});
	}

	/**
	 * jump Method
	 */
	public void jump() {
		if (Jump.jump_up) {
			Jump.vt = Jump.v0 - Jump.g * Jump.t;
			Jump.delta_height = Jump.v0 * Jump.t;
			Jump.v0 = Jump.vt;
			y -= Jump.delta_height;
			if (Jump.vt <= 0) {
				Jump.jump_up = false;
				Jump.vt = 0;
				Jump.v0 = 0;
			}
		} else {
			Jump.vt = Jump.v0 + Jump.g * Jump.t;
			Jump.delta_height = Jump.v0 * Jump.t;
			Jump.v0 = Jump.vt;
			y += Jump.delta_height;
			if (y >= Jump.sy) {
				y = Jump.sy;
				this.jump = false;
				Jump.v0 = 40;
				Jump.vt = 0;
			}
		}
	}

	/**
	 * shoot Method
	 */
	void shoot() {
		double abs = (new Random().nextDouble()) * Math.PI / 8;
		switch (this.dire) {
		case LEFT:
			for (int i = 0; i < 3; i++) {
				bullets.add(new Bullet(this.x - this.width - MapleStoryClient.getBackX(), this.y,
						-abs - Math.PI - Math.PI / 10 * (i - 1)));
			}
			break;
		case RIGHT:
			for (int i = 0; i < 3; i++) {
				bullets.add(new Bullet(this.x + this.width * 2 - MapleStoryClient.getBackX(), this.y,
						abs - Math.PI / 10 * (i - 1)));
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
			bullet.hitMods(MapleStoryClient.mobs);
		}
	}

	/**
	 * skill
	 */
	void skill() {
		final int counts = 18;
		for (int i = 0; i <= counts; i++) {
			bullets.add(new Bullet(ImageUtil.imgBullet.skill, this.x - MapleStoryClient.backGround.x, this.y,
					-Math.PI * 2 / counts * i, 20));
		}
		for (Bullet bullet : bullets) {
			bullet.grow();
			bullet.addAngle(new Random().nextDouble() * Math.PI * 2);
		}
	}

	/**
	 * die
	 */
	void die(){
		this.die = true;
		MapleStoryClient.hero = new Hero();
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

		drawBloodBar(g, this, false);

		count %= img.length;
		switch (this.dire) {
		case LEFT:
			g.drawImage(img[count], x - (img[count].getWidth(null) - this.width),
					y - (img[count].getHeight(null) - this.height), null);
			break;
		case RIGHT:
			g.drawImage(img[count], x, y - (img[count].getHeight(null) - this.height), null);
		}
		count++;

	}

	/**
	 * get Rectangle
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
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
			shoot = true;
			break;
		case KeyEvent.VK_K:
			if (!jump) {
				Jump.sy = y;
				Jump.jump_up = true;
				jump = true;
			}
			break;
		case KeyEvent.VK_L:
			if (!skill) {
				skill();
			}
			skill = true;
			break;
		case KeyEvent.VK_G:
			shoot = !shoot;
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
