package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
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

	public boolean shoot, skill, hit, up, down;
	public boolean left, right;
	public Action action;
	private int count = 0;

	/**
	 * shoot_Args
	 */
	private List<Bullet> bullets = new LinkedList<>();

	public Hero(Image[] img, int x, int y) {
		super(img, x, y, 200, Constant.HERO_SPEED, Direction.RIGHT);
		this.left = false;
		this.right = false;
		this.shoot = false;
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
		this(ImageUtil.imgHero.stand.l, 10, 0);
		this.y = Constant.GAME_HEIGHT - this.height - 50;
	}

	private void updataAction() {
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

		if (up && isOnRope()) {
			this.action = Action.UP;
		}

		if (down && isOnRope()) {
			this.action = Action.DOWN;
		}
	}

	/**
	 * move
	 */
	public void move() {

		updataAction();

		switch (this.dire) {
		case LEFT:
			switch (this.action) {
			case WALK:
				if (!isOnGround()) {
					jump = true;
				} else {
					x -= speed;
					img = ImageUtil.imgHero.walk.l;
				}
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
			case UP:
				y -= speed / 2;
				break;
			case DOWN:
				y += speed / 2;
				break;
			}
			break;
		case RIGHT:
			switch (this.action) {
			case WALK:
				if (!isOnGround()) {
					jump = true;
				} else {
					x += speed;
					img = ImageUtil.imgHero.walk.r;
				}
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
			case UP:
				y -= speed / 2;
				break;
			case DOWN:
				y += speed / 2;
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
			bullet.addAngle(Math.PI);
		}
	}

	/**
	 * die
	 */
	void die() {
		this.die = true;
		MapleStoryClient.hero = new Hero();
	}

	@Override
	void onTheGround() {
		// TODO Auto-generated method stub
		super.onTheGround();
		this.action = Action.WALK;
		jump = false;
	}

	/**
	 * draw
	 * 
	 * @param g
	 *            g is {@link Graphics}
	 */
	@Override
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
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
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
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		default:
			break;
		}
	}

}
