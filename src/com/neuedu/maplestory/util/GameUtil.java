package com.neuedu.maplestory.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Tool Class: Load Source File
 * 
 * @author Lain
 *
 */

public class GameUtil {

	public static Image imgBackground = GameUtil.getImage("com/neuedu/maplestory/img/background/test.png");

	public static class imgHero {
		public static class walk {

			public static int size = 5;

			public static Image[] r = new Image[] {
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_r/walk1_0.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_r/walk1_1.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_r/walk1_2.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_r/walk1_3.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_r/walk1_4.png") };

			public static Image[] l = new Image[] {
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/walk1_0.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/walk1_1.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/walk1_2.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/walk1_3.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/walk1_4.png") };
		}

		static public class shoot {

			public static int size = 4;

			public static Image[] r = new Image[] {
					GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_r/shoot1_0.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_r/shoot1_1.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_r/shoot1_2.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_r/shoot1_3.png") };

			public static Image[] l = new Image[] {
					GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_l/shoot1_0.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_l/shoot1_1.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_l/shoot1_2.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_l/shoot1_3.png") };
		}

		static public class stand {
			public static int size = 4;

			public static Image[] r = new Image[] {
					GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_0.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_1.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_2.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_3.png") };

			public static Image[] l = new Image[] {
					GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_l/stand1_0.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_l/stand1_1.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_l/stand1_2.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_l/stand1_3.png") };
		}

		static public class jump {
			public static int size = 1;

			public static Image r = GameUtil.getImage("com/neuedu/maplestory/img/hero/jump/jump_r.png");
			public static Image l = GameUtil.getImage("com/neuedu/maplestory/img/hero/jump/jump_l.png");
		}
	}

	/**
	 * Load Image Method
	 * 
	 * @param imgPath
	 * @return the image of igmPath
	 */
	public static Image getImage(String imgPath) {
		URL u = GameUtil.class.getClassLoader().getResource(imgPath);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
