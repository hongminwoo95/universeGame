package Player;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class PlayerOneLU implements Player{
	
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private Point playerimage; // Point참조한 playerImage 변수선언
	
	private Image playerLU = new ImageIcon("images/좌측상단대각.png").getImage(); // 우측면 캐릭터
	
	private static PlayerOneLU playeroneLU = new PlayerOneLU();
	public static PlayerOneLU getPlayerOneLU() {
		return playeroneLU;
	}
	@Override
	public Image getPlayer() {
		return playerLU;
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
		return playerLU.getWidth(null);
	}
	@Override
	public int getPlayerHeight() {
		return playerLU.getHeight(null);
	}
}
