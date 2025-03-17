package missile;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class MissileOneUp implements Missile{
	
	Image missileOne = new ImageIcon("images/미사일상단").getImage();
	public Point bullet;
	
	public MissileOneUp(int x, int y) {
		bullet = new Point(x,y);
	}
	public void move() {
		bullet.y -= 10;
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
