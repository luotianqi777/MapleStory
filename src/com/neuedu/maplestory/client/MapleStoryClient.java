package com.neuedu.maplestory.client;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.entity.Background;
import com.neuedu.maplestory.entity.Direction;
import com.neuedu.maplestory.entity.Ground;
import com.neuedu.maplestory.entity.Hero;
import com.neuedu.maplestory.entity.MobBase;
import com.neuedu.maplestory.entity.MobSnail;
import com.neuedu.maplestory.entity.Rope;
import com.neuedu.maplestory.util.FrameUtil;

/**
 * Add hero move Method
 * 
 * @author Lain
 *
 */
@SuppressWarnings("serial")
public class MapleStoryClient extends FrameUtil {

	static public Background backGround = new Background();
	static public Hero hero = new Hero();
	static public List<MobBase> mobs = new LinkedList<>();
	private final MobBase snail = new MobSnail();

	static public int getBackX() {
		return backGround.getX();
	}

	@Override
	public void paint(Graphics g) {
		// draw background
		backGround.draw(g);
		// draw hero
		hero.draw(g);
		// draw mobs
		mobs.removeIf((e) -> {
			return e.isDie();
		});
		if (mobs.isEmpty()) {
			addMobs();
		}
		for (MobBase mob : mobs) {
			mob.draw(g);
		}
	}

	private void addMobs() {
		// add mobs
		for (int i = 0; i < Constant.MOB_NUM; i++) {
			mobs.add(new MobSnail((backGround.width + backGround.getX()) - i * backGround.width / Constant.MOB_NUM,
					hero.y + hero.height - snail.height,
					(new Random().nextInt(2) == 0 ? Direction.LEFT : Direction.RIGHT)));
		}
	}

	void createBackground() {

		backGround.addRope(new Rope(400, 400, 10));
		backGround.addRope(new Rope(1300, 550, 7));
		backGround.addRope(new Rope(1000, 220, 5));

		for (int i = 0;i < 9; i++) {
			backGround.addGround(new Ground(i * Ground.IMAGE.getWidth(null) / 2,
					Constant.GAME_HEIGHT - Ground.IMAGE.getHeight(null)));
		}
		backGround.grounds.add(new Ground(0, 400));
		backGround.grounds.add(new Ground(600, 220));
		backGround.grounds.add(new Ground(500, 700));
		backGround.grounds.add(new Ground(1200, 550));

	}

	@Override
	public void loadFrame() {

		super.loadFrame();

		createBackground();
		// add key listener
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				hero.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				hero.keyReleased(e);
			}
		});
	}

	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MapleStoryClient().loadFrame();
	}
}
