package Player;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class PlayerOneDU implements Player{
	
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private Point playerimage; // Point참조한 playerImage 변수선언
	
	private Image playerDU = new ImageIcon("images/우측하단대각.png").getImage(); // 우측면 캐릭터
	
	private static PlayerOneDU playeroneDU = new PlayerOneDU();
	public static PlayerOneDU getPlayerOneDU() {
		return playeroneDU;
	}
	@Override
	public Image getPlayer() {
		return playerDU;
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
		return playerDU.getWidth(null);
	}
	@Override
	public int getPlayerHeight() {
		return playerDU.getHeight(null);
	}
}
