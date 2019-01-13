package com.neuedu.maplestory.constant;

import java.awt.Image;

import com.neuedu.maplestory.util.GameUtil;

public class Constant {
	public static final int GAME_WIDTH = 1500;
	public static final int GAME_HEIGHT = 900;

	public static Image imgBackground = GameUtil.getImage("com/neuedu/maplestory/img/test.png");

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
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/shoot1_0.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/shoot1_1.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/shoot1_2.png"),
					GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/shoot1_3.png") };
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
			public static int size = 2;

			public static Image r = GameUtil.getImage("com/neuedu/maplestory/img/hero/jump/jump_l.png");
			public static Image l = GameUtil.getImage("com/neuedu/maplestory/img/hero/jump/jump_r.png");
		}
	}
}
