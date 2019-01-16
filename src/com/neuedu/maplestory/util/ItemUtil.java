package com.neuedu.maplestory.util;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.entity.BulletType;
import com.neuedu.maplestory.entity.Item;

public class ItemUtil {

	public static class HP extends Item {

		public HP() {
			super(ImageUtil.imgItem.HP);
		}

		@Override
		public void work() {
			MapleStoryClient.hero.HP += 50;
		}

		@Override
		public HP copy() {
			return new HP();
		}

	}

	public static class MP extends Item {

		public MP() {
			super(ImageUtil.imgItem.MP);
		}

		@Override
		public void work() {
			MapleStoryClient.hero.MP += 20;
		}

		@Override
		public MP copy() {
			return new MP();
		}

	}

	public static class shoes extends Item {

		public shoes() {
			super(ImageUtil.imgItem.shoes);
		}

		@Override
		public void work() {
			MapleStoryClient.hero.speed += 1;
		}

		@Override
		public shoes copy() {
			return new shoes();
		}

	}

	public static class shootsin extends Item {

		public shootsin() {
			super(ImageUtil.imgItem.sin);
		}

		@Override
		public void work() {
			MapleStoryClient.hero.bulletType = BulletType.SIN;
		}

		@Override
		public shootsin copy() {
			// TODO Auto-generated method stub
			return new shootsin();
		}

	}

}
