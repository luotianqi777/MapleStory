package com.neuedu.maplestory.client;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.entity.Background;
import com.neuedu.maplestory.entity.Direction;
import com.neuedu.maplestory.entity.Hero;
import com.neuedu.maplestory.entity.MobBase;
import com.neuedu.maplestory.entity.MobSnail;
import com.neuedu.maplestory.util.FrameUtil;

/**
 * Add hero move Method
 * 
 * @author Lain
 *
 */
@SuppressWarnings("serial")
public class MapleStoryClient extends FrameUtil {

	static public Hero hero = new Hero();
	static public Background backGround = new Background();
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
			mobs.add(new MobSnail((backGround.weight + backGround.getX()) - i * backGround.weight / Constant.MOB_NUM,
					((Hero.Jump.sy == 0) ? hero.y : Hero.Jump.sy) + hero.height - snail.height, Direction.LEFT));
		}
	}

	@Override
	public void loadFrame() {

		super.loadFrame();

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
