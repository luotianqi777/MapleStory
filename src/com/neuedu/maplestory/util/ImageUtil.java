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

	public static class imgBackGround {

		public static Image[] background = { GameUtil.getImage("com/neuedu/maplestory/img/background/BackGround.png") };

		public static Image[] ground = { GameUtil.getImage("com/neuedu/maplestory/img/ground/ground.png") };

		public static Image[] rope = new Image[3];

		static {
			for (int i = 0; i < 3; i++) {
				rope[i] = GameUtil.getImage("com/neuedu/maplestory/img/rope/rope_" + i + ".png");
			}
		}

	}

	public static class imgHero {

		public static class rope {

			public static int size = 9;

			public static Image[] rope = new Image[size];

			static {

				for (int i = 0; i < size; i++) {
					rope[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/rope/rope_" + i + ".png");
				}

			}
		}

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

		static public class skill {

			public static final int size = 4;

			public static Image[] r = new Image[size];

			public static Image[] l = new Image[size];

			static {
				for (int i = 0; i < size; i++) {
					r[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/skill_r/skill1_" + i + ".png");
					l[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/skill_l/skill1_" + i + ".png");
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

		static public class shoot {
			public static final int size = 4;

			public static Image[] r = new Image[size];

			public static Image[] l = new Image[size];

			static {
				for (int i = 0; i < size; i++) {
					r[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_r/shoot1_" + i + ".png");
					l[i] = GameUtil.getImage("com/neuedu/maplestory/img/hero/shoot_l/shoot1_" + i + ".png");
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

	public static class imgMob {

		public static class boss {

			public static class die {

				public static final int size = 7;

				public static Image[] r = new Image[size];

				public static Image[] l = new Image[size];

				static {

					for (int i = 0; i < size; i++) {
						l[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/boos/die_l/" + i + ".png");
					}

					for (int i = 0; i < size; i++) {
						r[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/boos/die_r/" + i + ".png");
					}

				}

			}

			public static class move {

				public static final int size = 6;

				public static Image[] r = new Image[size];

				public static Image[] l = new Image[size];

				static {

					for (int i = 0; i < size; i++) {
						l[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/boos/move_l/" + i + ".png");
					}

					for (int i = 0; i < size; i++) {
						r[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/boos/move_r/" + i + ".png");
					}

				}

			}

		}

		public static class snail {

			public static class die {

				public static final int size = 12;

				public static Image[] r = new Image[size];

				public static Image[] l = new Image[size];

				static {

					for (int i = 0; i < size; i++) {
						l[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/snail/die_l/" + i + ".png");
					}

					for (int i = 0; i < size; i++) {
						r[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/snail/die_r/" + i + ".png");
					}

				}

			}

			public static class move {

				public static final int size = 4;

				public static Image[] r = new Image[size];

				public static Image[] l = new Image[size];

				static {

					for (int i = 0; i < size; i++) {
						l[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/snail/move_l/" + i + ".png");
					}

					for (int i = 0; i < size; i++) {
						r[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/snail/move_r/" + i + ".png");
					}

				}

			}

			public static class hit {

				public static final int size = 3;

				public static Image[] r = new Image[size];

				public static Image[] l = new Image[size];

				static {

					for (int i = 0; i < size; i++) {
						l[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/snail/hit_l/" + i + ".png");
					}

					for (int i = 0; i < size; i++) {
						r[i] = GameUtil.getImage("com/neuedu/maplestory/img/mob/snail/hit_r/" + i + ".png");
					}

				}

			}

		}
	}

	public static class imgItem {

		public static Image[] HP = { GameUtil.getImage("com/neuedu/maplestory/img/item/HP.png") };

		public static Image[] MP = { GameUtil.getImage("com/neuedu/maplestory/img/item/MP.png") };

		public static Image[] shoes = { GameUtil.getImage("com/neuedu/maplestory/img/item/shoes.png") };

		public static Image[] sin = { GameUtil.getImage("com/neuedu/maplestory/img/item/sin.png") };
	}
}
