package missile;

import java.awt.Image;

public interface Missile {
	public void move();
	public Image getImage();
	public int getBulletX();
	public int getBulletY();
	public int getWidth();
	public int getHeight();
}
