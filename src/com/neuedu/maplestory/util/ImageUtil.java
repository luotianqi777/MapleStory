package com.neuedu.maplestory.util;

import java.awt.Image;
import com.neuedu.maplestory.util.GameUtil;

/**
 * All Image
 * 
 * @author Lain
 *
 */

public class ImageUtil {

	public static Image imgBackground = GameUtil.getImage("com/neuedu/maplestory/img/background/BackGround.png");

	public static class imgHero {
		public static class walk {

			public static final int size = 5;

			public static Image[] r = new Image[size];

			public static Image[] l = new Image[size];

			static {
				for (int i = 0; i < size; i++) {
					r[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_r/walk1_" + i + ".png");
					l[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/walk_l/walk1_" + i + ".png");
				}
			}
		}

		static public class shoot {

			public static final int size = 5;

			public static Image[] r = new Image[size];

			public static Image[] l = new Image[size];

			static {
				for (int i = 0; i < size; i++) {
					r[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_r/shoot2_" + i + ".png");
					l[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_l/shoot2_" + i + ".png");
				}
			}

		}

		static public class stand {
			public static final int size = 4;

			public static Image[] r = new Image[size];

			public static Image[] l = new Image[size];

			static {
				for (int i = 0; i < size; i++) {
					r[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_r/stand1_" + i + ".png");
					l[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/stand_l/stand1_" + i + ".png");
				}
			}
		}

		static public class jump {
			public static final int size = 2;

			public static Image[] r = new Image[size];

			public static Image[] l = new Image[size];

			static {
				for (int i = 0; i < size; i++) {
					r[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/jump_r/jump_" + i + ".png");
					l[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/jump_l/jump_" + i + ".png");
				}
			}
		}
		
		static public class skill{
			public static final int size = 3;

			public static Image[] r = new Image[size];

			public static Image[] l = new Image[size];

			static {
				for (int i = 0; i < size; i++) {
					r[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/skill_r/skill1_" + i + ".png");
					l[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/skill_l/skill1_" + i + ".png");
				}
			}
			
		}
	}

	
	
	public static class imgBullet {
		
		public static final int size = 1;

		public static Image[] norml = new Image[size];
		
		public static Image[] skill = new Image[size];

		static {
			for (int i = 0; i < size; i++) {
				norml[i] = GameUtil.getImage("com/neuedu/maplestory/img/bullet/bullet2_" + i + ".png");
				skill[i] = GameUtil.getImage("com/neuedu/maplestory/img/bullet/bullet1_" + i + ".png");
			}
		}

	}

}
