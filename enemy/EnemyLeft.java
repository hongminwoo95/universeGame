package enemy;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class EnemyLeft implements Enemy{
	private Image EnemyOne = new ImageIcon("images/적기1").getImage();
	private Point body; // x,y좌표를 설정하기위한 변수
	
	
	public EnemyLeft(int x, int y) { //SpaceshipGreen에 x,y좌표 설정
		body = new Point(x,y);
	}


	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void getmove() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getenemyX() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getenemyY() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getenemyWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getenemyHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
