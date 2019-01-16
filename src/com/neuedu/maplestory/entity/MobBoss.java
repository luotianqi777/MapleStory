package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;
import com.neuedu.maplestory.util.ItemUtil;

public class MobBoss extends MobBase{
	
	static{
		addDropItem(new ItemUtil.shootsin());
	}
	
	private ArrayList<BossBullet> bullets = new ArrayList<>();
	public MobBoss(int x, int y, Direction dire) {
		super(ImageUtil.imgMob.boos.move.l, // image
				x, y, // location
				dire, // direction
				1000, // HP
				300, // MP
				3, // speed
				10 // attack
		);

		this.jump = false;
		// dropChange
		setDropChange(100);
	}
	void shoot() {
		/*
		 */
		if(new Random().nextInt(50) > 35) {
			Hero hero = MapleStoryClient.hero;
			double atan = (double) (hero.y - this.y) / (hero.x - this.getTrueX());
			atan = Math.atan(atan) + (hero.x > this.getTrueX() ? Math.PI : 0);
			bullets.add(new BossBullet(this.x, this.y, (atan + Math.PI)));
		}
		

	}
	public void move() {
		super.move();
		Hero hero = MapleStoryClient.hero;
		if(this.x - hero.x > Constant.GAME_WIDTH / 2) {
			this.dire = Direction.LEFT;
		}  else if(hero.x - this.x > Constant.GAME_WIDTH / 2) {
			this.dire = Direction.RIGHT;
		}
		switch (this.dire) {
		case LEFT:
			switch (this.action) {
			case DIE:
				img = ImageUtil.imgMob.boos.die.l;
				break;
			case HIT:
				img = ImageUtil.imgMob.boos.move.l;
				break;
			case WALK:
				img = ImageUtil.imgMob.boos.move.l;
					x -= speed;
				break;
			}
			break;
		case RIGHT:
			switch (this.action) {
			case DIE:
				img = ImageUtil.imgMob.boos.die.r;
				break;
			case HIT:
				img = ImageUtil.imgMob.boos.move.r;
				break;
			case WALK:
				img = ImageUtil.imgMob.boos.move.r;
					x += speed;
				break;
			}
			break;
		}
		outOfBounds();
		
	}
	void moveBullets() {
		for (Bullet bullet : bullets) {
			bullet.move();
			bullet.hit(MapleStoryClient.hero);
		}
	}
	public void draw(Graphics g) {
		move();
		shoot();
		moveBullets();
		for (Bullet bullet : bullets) {
			bullet.draw(g);
		}
		
		super.draw(g);
		
	}
	
	@Override
	void updataAction() {
		if(walk) {
			this.action = MobAction.WALK;
		}
	}
}
