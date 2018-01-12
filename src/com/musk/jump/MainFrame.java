package com.musk.jump;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 5261564117191747712L;
	private PhonePanel phonePanel = null;
	private boolean isFirst = true;
	private Point prePoint = new Point();
	private Point curPoint = new Point();

	public MainFrame() {
		phonePanel = new PhonePanel();
		this.add(phonePanel);
		this.setSize(480, 854);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent event) {
				System.out.println("==mousePressed==");
				try {
					if (isFirst) {
						prePoint.x = event.getX();
						prePoint.y = event.getY();
						isFirst = false;
						return;
					}
					curPoint.x = event.getX();
					curPoint.y = event.getY();

					// calculate jump distance
					int distance = getDistance(prePoint, curPoint);
					// calculate jump time
					int time = (int) (distance / 0.37);

					System.out.println("==distance=="+distance);
					System.out.println("==time==" + time);
					Runtime.getRuntime().exec("adb shell input touchscreen swipe 170 187 170 187 " + time);
					System.out.println("==touchscreen down==");
					Thread.sleep(3000);

					isFirst = true;
					phonePanel.invalidate();
					phonePanel.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			@Override
			public void mouseReleased(MouseEvent event) {
			}

			@Override
			public void mouseExited(MouseEvent event) {
			}

			@Override
			public void mouseEntered(MouseEvent event) {
			}

			@Override
			public void mouseClicked(MouseEvent event) {
			}
		});
	}

	private int getDistance(Point x, Point y) {
		return (int) Math.sqrt((x.x - y.x) * (x.x - y.y) + (x.y - y.y) * (x.y - y.y));
	}
}