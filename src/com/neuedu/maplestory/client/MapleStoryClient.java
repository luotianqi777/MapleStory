package com.neuedu.maplestory.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.GameUtil;

/**
 * Create Frame Of Client:
 * 
 * 1.Extends Frame Class
 * 
 * 2.Defined LoadFrame Method
 * 
 * 3.Write Main Method
 * 
 * @author Lain
 *
 */

@SuppressWarnings("serial")
public class MapleStoryClient extends Frame implements KeyListener {

	/**
	 * Load Frame
	 */
	void loadFrame() {
		// 1.set size
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		// 2.set frame location
		this.setLocationRelativeTo(null);
		// 3.Load frame
		this.setVisible(true);
		// 4.Set Closable
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// close frame
				System.exit(0);
			}
		});
		// 5.Set frame Title
		this.setTitle("Neuedu_MapleStory");
		// 6.add key Listener
		this.addKeyListener(this);

	}

	public enum MOD {
		SHOOT, WALK, STAND, JUMP
	};

	public enum DIRE {
		LEFT, RIGHT
	}

	static class HeroArgs {

		public static int count = 1;
		public static int x = 200;
		public static int y = 200;
		public static final int speed = 10;
		public static MOD mod = MOD.STAND;
		public static DIRE dire = DIRE.RIGHT;
	}

	/**
	 * Draw Background Image
	 */
	@Override
	public void  paint(Graphics g) {
		// draw background
		g.drawImage(GameUtil.imgBackground, 0, 0, null);
		// draw hero
		if (HeroArgs.count > 0) {
			HeroArgs.count--;
		}
		Image imgHero = null;
		if (HeroArgs.mod == MOD.JUMP) {
			if (HeroArgs.dire == DIRE.LEFT) {
				imgHero = GameUtil.imgHero.jump.l;
			} else {
				imgHero = GameUtil.imgHero.jump.r;
			}
		} else {
			switch (HeroArgs.mod) {
			case SHOOT:
				switch (HeroArgs.dire) {
				case LEFT:
					imgHero = GameUtil.imgHero.shoot.l[HeroArgs.count];
					break;
				case RIGHT:
					imgHero = GameUtil.imgHero.shoot.r[HeroArgs.count];
					break;
				}
				break;
			case STAND:
				switch (HeroArgs.dire) {
				case LEFT:
					imgHero = GameUtil.imgHero.stand.l[HeroArgs.count];
					break;
				case RIGHT:
					imgHero = GameUtil.imgHero.stand.r[HeroArgs.count];
					break;
				}
				break;
			case WALK:
				switch (HeroArgs.dire) {
				case LEFT:
					imgHero = GameUtil.imgHero.walk.l[HeroArgs.count];
					HeroArgs.x -= HeroArgs.speed;
					break;
				case RIGHT:
					imgHero = GameUtil.imgHero.walk.r[HeroArgs.count];
					HeroArgs.x += HeroArgs.speed;
					break;
				}
				break;
			default:
				break;
			}
		}
		g.drawImage(imgHero, HeroArgs.x, HeroArgs.y, null);
		if(HeroArgs.count!=0){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Flush Thread
	 * 
	 * @author Lain
	 *
	 */
	class FlushThread extends Thread {
		@Override
		public void run() {
			// repaint
			for (;;) {
					repaint();
				try {
					// fps 25
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MapleStoryClient();
	}

	public MapleStoryClient() {
		// load frame
		loadFrame();
		new Thread(new FlushThread()).start();
	}

	
	
	// 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
	Image backImg = null;

	// 重写update()方法，在窗口的里层添加一个虚拟的图片
	@Override
	public void update(Graphics g) {
		if (backImg == null) {
			// 如果虚拟图片不存在，创建一个和窗口一样大小的图片
			backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		// 获取到虚拟图片的画笔
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.white);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(c);
		// 调用虚拟图片的paint()方法，每50ms刷新一次
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			HeroArgs.mod = MOD.WALK;
			HeroArgs.dire = DIRE.LEFT;
			HeroArgs.count = GameUtil.imgHero.walk.size;
			break;
		case KeyEvent.VK_D:
			HeroArgs.mod = MOD.WALK;
			HeroArgs.dire = DIRE.RIGHT;
			HeroArgs.count = GameUtil.imgHero.walk.size;
			break;
		case KeyEvent.VK_J:
			HeroArgs.mod = MOD.SHOOT;
			HeroArgs.count = GameUtil.imgHero.walk.size;
			break;
		case KeyEvent.VK_K:
			HeroArgs.mod = MOD.JUMP;
			HeroArgs.count = GameUtil.imgHero.walk.size;
			break;
		default:
			HeroArgs.mod = MOD.STAND;
			HeroArgs.count = GameUtil.imgHero.stand.size;
			break;
		}
		HeroArgs.count--;

	}

	@Override
	public void keyReleased(KeyEvent e) {

		HeroArgs.mod = MOD.STAND;
		HeroArgs.count = GameUtil.imgHero.stand.size - 1;
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_W:
//			break;
//		case KeyEvent.VK_S:
//			break;
//		case KeyEvent.VK_A:
//			break;
//		case KeyEvent.VK_D:
//			break;
//		case KeyEvent.VK_J:
//			break;
//		case KeyEvent.VK_K:
//			break;
//		default:
//			break;
//		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
