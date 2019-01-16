package com.neuedu.maplestory.entity;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class HeroBullet extends Bullet {

	BulletType type;
	Direction dire;
	protected static int axi = Constant.HERO_BULLET_AXI;

	public HeroBullet(int x, int y, double angle, BulletType type) {
		super(ImageUtil.imgBullet.norml, x, y, angle, Constant.HERO_BULLET_SPEED);
		this.type = type;
		dire = MapleStoryClient.hero.dire;
	}

	@Override
	public void move() {
		switch (this.type) {
		case COMMEN:
			x += speed * Math.cos(angle);
			y += speed * Math.sin(angle);
			break;
		case SIN:
			switch (dire) {
			case LEFT:
				this.x -= speed;
				axi += speed;
				this.y = y + (int) (Math.sin(axi) * MapleStoryClient.hero.height / 2);
				break;
			case RIGHT:
				axi += speed;
				this.x += speed;
				this.y = y + (int) (Math.sin(axi) * MapleStoryClient.hero.height / 2);
				break;
			}
			break;
		case SCATTER:
			break;
		}

		outOfBounds();
	}

}
