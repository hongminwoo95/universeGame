package Player;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class PlayerOneDown implements Player{ // 캐릭터 하단
	
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private Point playerimage; // Point참조한 playerImage 변수선언
	
	private Image playerD = new ImageIcon("images/하단.png").getImage(); // 좌측면 캐릭터
	
	
	public PlayerOneDown() {
	}
	@Override
	public Image getPlayer() {
		return playerD;
	}
	@Override
	public int getPlayerX() {
		return playerimage.x;
	}
	@Override
	public int getPlayerY() {
		return playerimage.y;
	}
	@Override
	public int getPlayerWidth() {
		return playerD.getWidth(null);
	}
	@Override
	public int getPlayerHeight() {
		return playerD.getHeight(null);
	}

}
