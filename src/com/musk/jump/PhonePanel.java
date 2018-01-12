package com.musk.jump;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PhonePanel extends JPanel {

	private static final long serialVersionUID = -6349470466268193389L;
	private Image image = null;

	@Override
	public void paint(Graphics graphics) {
		try {
			Process cap = Runtime.getRuntime().exec("adb shell screencap -p /sdcard/jump.png");
			cap.waitFor();
			Process pull = Runtime.getRuntime().exec("adb pull /sdcard/jump.png screencap\\jump.png");
			pull.waitFor();
			image = ImageIO.read(new File("screencap\\jump.png"));
			graphics.drawImage(image, 0, 0, 480, 854, null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}