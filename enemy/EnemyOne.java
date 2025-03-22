package enemy;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class EnemyOne implements Enemy{
	private Image EnemyOne = new ImageIcon("images/적기1").getImage();
	private Point body; // x,y좌표를 설정하기위한 변수
	
	
	public EnemyOne(int x, int y) { //SpaceshipGreen에 x,y좌표 설정
		body = new Point(x,y);
	}
	
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return EnemyOne;
	}
	@Override
	public void getmove() {
		
		
		// 동작여부 판단할것
		
		
		
		
		
	}
	@Override
	public int getspaceshipX() {
		// TODO Auto-generated method stub
		return body.x;
	}
	@Override
	public int getspaceshipY() {
		// TODO Auto-generated method stub
		return body.y;
	}
	@Override
	public int getspaceshipWidth() {
		// TODO Auto-generated method stub
		return EnemyOne.getWidth(null);
	}

	@Override
	public int getspaceshipHeight() {
		// TODO Auto-generated method stub
		return EnemyOne.getHeight(null);
	}
}
