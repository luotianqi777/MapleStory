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
