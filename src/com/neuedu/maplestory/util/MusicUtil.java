package com.neuedu.maplestory.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

/***
 * 音乐播放器类
 *
 * @author 远哥
 */
// 继承自线程类Thread
public class MusicUtil extends Thread {

	public static String bgm = "com/neuedu/maplestory/music/bgm.mp3";

	private Player player;
	private String musicPath;
	private boolean loop;

	/**
	 * 构造方法（默认不循环播放）
	 * 
	 * @param musicpath
	 */
	public MusicUtil(String musicPath) {
		this.musicPath = musicPath;
	}

	/**
	 * 构造方法(是否循环)
	 * 
	 * @param musicpath
	 *            音乐文件所在路径
	 */
	public MusicUtil(String musicpath, boolean loop) {
		this(musicpath);
		this.loop = loop;
	}

	/**
	 * 重写run方法
	 */
	@Override
	public void run() {
		super.run();
		try {
			if (loop) {
				while (true) {
					play();
				}
			} else {
				play();
			}
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 播放方法
	 * 
	 * @throws FileNotFoundException
	 * @throws JavaLayerException
	 */
	private void play() throws FileNotFoundException, JavaLayerException {
		BufferedInputStream buffer = new BufferedInputStream(
				MusicUtil.class.getClassLoader().getResourceAsStream(musicPath));
		player = new Player(buffer);
		player.play();
	}

}