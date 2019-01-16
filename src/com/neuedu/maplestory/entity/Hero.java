package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Font;
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

	public boolean shoot, skill, hit, get;
	public boolean left, right;
	public Action action;
	public int kill;
	public BulletType bulletType;

	/**
	 * shoot_Args
	 */
	private List<HeroBullet> bullets = new LinkedList<>();

	public Hero(Image[] img, int x, int y) {
		super(img, // image
				x, y, // location
				300, // HP
				100, // MP
				Constant.HERO_SPEED, // speed
				Direction.RIGHT // direction
		);
		this.left = false;
		this.right = false;
		this.shoot = false;
		this.skill = false;
		this.hit = false;
		this.action = Action.STAND;
		this.bulletType = BulletType.COMMEN;
		this.kill = 0;
		setFix(Ground.IMAGE.getHeight(null));
		try {
			this.width = img[0].getWidth(null);
			this.height = img[0].getHeight(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Hero() {
		this(ImageUtil.imgHero.stand.l, 10, 0);
		this.y = Constant.GAME_HEIGHT - this.height - 50;
	}

	public void updataItemsWork() {
		if (speed > Constant.HERO_MAX_SPEED) {
			speed = Constant.HERO_MAX_SPEED;
		}
		if (HP > MAX_HP) {
			HP = MAX_HP;
		}
		if (MP > MAX_MP) {
			MP = MAX_MP;
		}
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
			skill();
			this.action = Action.SKILL;
		}

		if (jump) {
			this.action = Action.JUMP;
			if (!up || !isOnRope()) {
				jump();
			} else {
				this.jump = false;
				Jump.v0 = Jump.v_init;
				Jump.jump_up = true;
				Jump.vt = 0;
			}
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

		if (get) {
			this.action = Action.GET;
			getItems();
		}
		if (!isOnGround() && !Jump.jump_up && !(up || down)) {
			fallCheck();
		}
	}

	/**
	 * move
	 */
	public void move() {

		updataItemsWork();

		updataAction();

		switch (this.dire) {
		case LEFT:
			switch (this.action) {
			case WALK:
				if (!isOnGround()) {
					fallCheck();
				}
				x -= speed;
				img = ImageUtil.imgHero.walk.l;

				break;
			case SHOOT:
				if (left) {
					fallCheck();
					x -= speed;
				}
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
				if (left) {
					fallCheck();
					fall();
					x -= speed;
				}
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
			case GET:
				break;
			}
			break;
		case RIGHT:
			switch (this.action) {
			case WALK:
				if (!isOnGround()) {
					fallCheck();
				}
				x += speed;
				img = ImageUtil.imgHero.walk.r;

				break;
			case SHOOT:
				if (right) {
					fallCheck();
					x += speed;
				}
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
				if (right) {
					fallCheck();
					x += speed;
				}
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
			case GET:
				break;
			}
			break;
		}
		if (this.isOnRope()) {
			img = ImageUtil.imgHero.rope.rope;
		}
		outOfBounds();
	}

	/**
	 * out of bounds
	 */
	public void outOfBounds() {

		// left bound
		if (x < Constant.GAME_WIDTH / 4 && MapleStoryClient.getBackX() < 0) {
			x += speed;
			MapleStoryClient.backGround.move(Direction.RIGHT);
		} else if (x < 0) {
			x = 0;
		}

		// right bound
		if (x > Constant.GAME_WIDTH * 3 / 4 - this.width
				&& (MapleStoryClient.getBackX() > Constant.GAME_WIDTH - MapleStoryClient.backGround.width)) {
			x -= speed;
			MapleStoryClient.backGround.move(Direction.LEFT);
		} else if (x > Constant.GAME_WIDTH - this.width) {
			x = Constant.GAME_WIDTH - this.width;
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
		// 调整角度
		double abs = (new Random().nextDouble()) * Math.PI / 8;
		switch (this.dire) {
		case LEFT:
			for (int i = 0; i < 3; i++) {
				bullets.add(new HeroBullet(this.x - MapleStoryClient.getBackX(), this.y + 10,
						-abs - Math.PI - Math.PI / 10 * (i - 1), bulletType));
			}
			break;
		case RIGHT:
			for (int i = 0; i < 3; i++) {
				bullets.add(new HeroBullet(this.x + this.width - MapleStoryClient.getBackX(), this.y + 10,
						abs - Math.PI / 10 * (i - 1), bulletType));
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
			bullet.hitNPCs(MapleStoryClient.mobs);
		}
	}

	/**
	 * skill
	 */
	void skill() {
		if (new Random().nextInt(100) < Constant.SKILL_P && MP > 0) {
			MP -= 1;
			int X = this.x - MapleStoryClient.getBackX();
			for (HeroBullet bullet : bullets) {
				bullet.grow();
				bullet.type = BulletType.COMMEN;
				double atan = (double) (bullet.y - this.y) / (bullet.x - X);
				atan = Math.atan(atan) + (bullet.x > X ? Math.PI : 0);
				bullet.setAngle(atan);
			}
		}
	}

	/**
	 * get Item
	 */
	void getItem(Item item) {
		if (this.getRectangle().intersects(item.getRectangle())) {
			item.work();
			item.die();
		}
	}

	void getItems() {
		for (Item item : MapleStoryClient.items) {
			getItem(item);
		}
	}

	/**
	 * die
	 */
	public void die() {
		this.die = true;
		MapleStoryClient.hero = new Hero();
	}

	@Override
	void onTheGround() {
		super.onTheGround();
		this.action = Action.WALK;
		jump = false;
	}

	@Override
	public int getTrueX() {
		return this.x;
	}

	void drawInfo(Graphics g) {
		Color c = g.getColor();
		Font f = g.getFont();

		int info_x = Constant.GAME_WIDTH / 2 + 200;
		int[] info_y = new int[5];
		for (int i = 0; i < 5; i++) {
			info_y[i] = i * 30 + 60;
		}

		g.setColor(Color.BLUE);
		g.setFont(new Font("微软雅黑", Font.BOLD, 24));
		g.drawString("攻击:J 跳跃:K", info_x, info_y[0]);
		g.drawString("拾取:空格", info_x, info_y[1]);
		g.drawString("上/下绳子:W/D", info_x, info_y[2]);
		g.drawString("开/关技能:L", info_x, info_y[3]);
		g.drawString("开/关挂机:G", info_x, info_y[4]);

		g.setColor(c);
		g.setFont(f);
	}

	public void drawMenu(Graphics g, Hero hero) {

		Color c = g.getColor();
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString("HP: " + hero.HP, Constant.GAME_WIDTH - 150, 70);
		g.setColor(Color.BLUE);
		g.drawString("MP: " + hero.MP, Constant.GAME_WIDTH - 150, 100);
		g.setColor(Color.GREEN);
		g.drawString("Speed: " + hero.speed, Constant.GAME_WIDTH - 150, 130);
		g.setColor(Color.PINK);
		g.drawString("Kill: " + hero.kill, Constant.GAME_WIDTH - 150, 160);
		g.setColor(Color.BLACK);
		g.drawString("Skill: " + (hero.skill ? "ON" : "OFF"), Constant.GAME_WIDTH - 150, 190);
		g.setColor(c);
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

		drawInfo(g);

		drawBloodBar(g, this, false);

		drawMenu(g, this);

		drawHeadInfos(g);

		if (img == null) {
			return;
		}

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
			HeroBullet.axi = Constant.HERO_BULLET_AXI;
			shoot = true;
			break;
		case KeyEvent.VK_K:
			if (!jump) {
				jump = true;
			}
			break;
		case KeyEvent.VK_L:
			skill = !skill;
			break;
		case KeyEvent.VK_G:
			shoot = !shoot;
			HeroBullet.axi = Constant.HERO_BULLET_AXI;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case 32: // 空格
			get = true;
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
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case 32: // 空格
			get = false;
			break;
		default:
			break;
		}
	}

}
