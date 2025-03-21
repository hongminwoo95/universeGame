package missile;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class MissileOneRight implements Missile{
	
	private Image missileOne = new ImageIcon("images/미사일우측.png").getImage();
	public Point bullet; // 총알에 x,y좌표 설정 하기위한 변수
	
	
	public Point MissileOneR(int x, int y) {
		bullet = new Point(x,y);
		return bullet;
	}
	public void move() {
		bullet.x += 10;
	}
	public Image getImage() {
		return missileOne;
	}
	public int getBulletX() {
		return bullet.x;
	}
	public int getBulletY() {
		return bullet.y;
	}
	public int getWidth() {
		return missileOne.getWidth(null);
	}
	public int getHeight() {
		return missileOne.getHeight(null);
	}
}
