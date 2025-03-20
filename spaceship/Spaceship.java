package spaceship;

import java.awt.Image;

public interface Spaceship {
	public Image getImage(); //이미지
	public void getmove(); // 움직임
	public int getspaceshipX(); // 우주선 x축
	public int getspaceshipY(); // 우주선 y축
	public int getspaceshipWidth();  // 우주선 너비
	public int getspaceshipHeight(); // 우주선 높이
}
