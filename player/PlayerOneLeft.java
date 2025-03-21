package Player;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class PlayerOneLeft implements Player{
	
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private Point playerimage; // Point참조한 playerImage 변수선언
	
	private Image playerL = new ImageIcon("images/좌측.png").getImage(); // 좌측면 캐릭터
	
	public PlayerOneLeft() {
		
	}
	@Override
	public Image getPlayer() {
		return playerL;
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
		return playerL.getWidth(null);
	}
	@Override
	public int getPlayerHeight() {
		return playerL.getHeight(null);
	}

}
