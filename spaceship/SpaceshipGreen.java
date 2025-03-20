package spaceship;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class SpaceshipGreen implements Spaceship{
	private Image SpaceshipGreen = new ImageIcon("images/적기1").getImage();
	private Point body; // x,y좌표를 설정하기위한 변수
	
	
	public SpaceshipGreen(int x, int y) { //SpaceshipGreen에 x,y좌표 설정
		body = new Point(x,y);
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return SpaceshipGreen;
	}

	@Override
	public void getmove() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public int getspaceshipX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getspaceshipY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getspaceshipWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getspaceshipHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
