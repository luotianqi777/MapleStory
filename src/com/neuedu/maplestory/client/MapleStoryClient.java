package com.neuedu.maplestory.client;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.neuedu.maplestory.entity.Background;
import com.neuedu.maplestory.entity.Hero;
import com.neuedu.maplestory.util.FrameUtil;

/**
 * Add hero move Method
 * 
 * @author Lain
 *
 */
@SuppressWarnings("serial")
public class MapleStoryClient extends FrameUtil {

	private Hero hero = new Hero();
	private Background backGround = new Background();

	@Override
	public void paint(Graphics g) {
		// draw background
		backGround.draw(g);
		hero.draw(g);
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
