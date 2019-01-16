package com.neuedu.maplestory.entity;

import java.awt.Graphics;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.util.ImageUtil;
import com.neuedu.maplestory.util.ItemUtil;

public class MobSnail extends MobBase {


	public MobSnail(int x, int y, Direction dire) {
		super(ImageUtil.imgMob.snail.move.l, // image
				x, y, // location
				dire, // direction
				500, // HP
				100, // MP
				5, // speed
				3 // attack
		);

		// dropChange
		setDropChange(40);

		addDropItem(new ItemUtil.HP());
		addDropItem(new ItemUtil.MP());
		addDropItem(new ItemUtil.shoes());
	}

	public MobSnail() {
		this(0, 0, Direction.LEFT);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		Hiting();
	}

	@Override
	void updataAction() {
		if (walk) {
			this.action = MobAction.WALK;
		}
		if (hit) {
			this.action = MobAction.HIT;
		}
		if (die) {
			this.action = MobAction.DIE;
		}
	}

	@Override
	void move() {
		super.move();
		switch (this.dire) {
		case LEFT:
			switch (this.action) {
			case DIE:
				img = ImageUtil.imgMob.snail.die.l;
				break;
			case HIT:
				img = ImageUtil.imgMob.snail.hit.l;
				break;
			case WALK:
				fallCheck();
				img = ImageUtil.imgMob.snail.move.l;
				x -= speed;
				break;
			}
			break;
		case RIGHT:
			switch (this.action) {
			case DIE:
				img = ImageUtil.imgMob.snail.die.r;
				break;
			case HIT:
				img = ImageUtil.imgMob.snail.hit.r;
				break;
			case WALK:

				fallCheck();
				img = ImageUtil.imgMob.snail.move.r;
				x += speed;
				break;
			}
			break;
		}
		outOfBounds();
		hit(MapleStoryClient.hero);
	}

}
