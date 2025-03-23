package enemy;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class EnemyRight implements Enemy{
	private Image EnemyOne = new ImageIcon("images/적기1.png").getImage();
	private Point body; // x,y좌표를 설정하기위한 변수
	
	
	public EnemyRight(int x, int y) { //SpaceshipGreen에 x,y좌표 설정
		body = new Point(x,y);
	}
	@Override
	public Image getImage() {
		return EnemyOne;
	}
	@Override
	public void getmove() {
		body.x -=10;
	}
	@Override
	public int getenemyX() {
		return body.x;
	}
	@Override
	public int getenemyY() {
		return body.y;
	}
	@Override
	public int getenemyWidth() {
		return EnemyOne.getWidth(null);
	}
	@Override
	public int getenemyHeight() {
		return EnemyOne.getHeight(null);
	}
}
