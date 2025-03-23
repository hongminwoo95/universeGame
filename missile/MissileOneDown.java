package missile;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class MissileOneDown implements Missile{
	
	private Image missileDown = new ImageIcon("images/ball.png").getImage();
	public Point bullet; // 총알에 x,y좌표 설정 하기위한 변수
	
	public MissileOneDown(int x, int y) {
		bullet = new Point(x,y);
	}
	public void move() {
		bullet.y += 10;
	}
	public Image getImage() {
		return missileDown;
	}
	public int getBulletX() {
		return bullet.x;
	}
	public int getBulletY() {
		return bullet.y;
	}
	public int getWidth() {
		return missileDown.getWidth(null);
	}
	public int getHeight() {
		return missileDown.getHeight(null);
	}
}
