package Player;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class PlayerOneRight implements Player{
	
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private Point playerimage; // Point참조한 playerImage 변수선언
	
	private Image playerR = new ImageIcon("images/우측.png").getImage(); // 우측면 캐릭터

	@Override
	public Image getPlayer() {
		return playerR;
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
		return playerR.getWidth(null);
	}
	@Override
	public int getPlayerHeight() {
		return playerR.getHeight(null);
	}

}
