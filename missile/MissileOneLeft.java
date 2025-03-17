package missile;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class MissileOneLeft implements Missile{
	Image missileOne = new ImageIcon("images/미사일좌측").getImage();
	public Point bullet;
	
	public MissileOneLeft(int x, int y) {
		bullet = new Point(x,y);
	}
	public void move() {
		bullet.x -= 10;
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
