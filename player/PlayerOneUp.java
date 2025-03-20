package Player;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class PlayerOneUp implements Player{
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private Point playerimage; // Point참조한 playerImage 변수선언
	
	private Image playerU = new ImageIcon("images/상단.png").getImage();
	
	private static PlayerOneUp playeroneup = new PlayerOneUp();
	public static PlayerOneUp getPlayerOneUp() {
		return playeroneup;
	}
	
	public PlayerOneUp() {
	}
	@Override
	public Image getPlayer() {
		return playerU;
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
		return playerU.getWidth(null);
	}
	@Override
	public int getPlayerHeight() {
		return playerU.getHeight(null);
	}
}
