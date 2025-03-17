package player;

import java.awt.Image;

import javax.swing.ImageIcon;

import missile.Missile;
import missile.MissileCenter;

public class Player {
	
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private int direction; // 현재 캐릭터 진행 방향
//	private int stamina; // 캐릭터의 체력
	
	
	private Image player = new ImageIcon("images/좌측.png").getImage(); // 정면 캐릭터
	private Image playerL = new ImageIcon("images/좌측.png").getImage(); // 좌측면 캐릭터
	private Image playerR = new ImageIcon("images/우측.png").getImage(); // 우측면 캐릭터
	private Image playerU = new ImageIcon("images/상단.png").getImage(); // 상단면 캐릭터
	private Image playerD = new ImageIcon("images/하단.png").getImage(); // 하단면 캐릭터
	
	private MissileCenter missileCenter= MissileCenter.getMissileCenter();
	private Missile ms=missileCenter.getMissile(direction, playerX, playerY);
	
	
	
	
	
	
	public Missile getMissile() {
		return ms;
	}
	public Image getPlayer() {
		return player;
	}
	public int getPlayerX() {
		return playerX;
	}
	public Image getPlayerL() {
		return playerL;
	}
	public Image getPlayerR() {
		return playerR;
	}
	public Image getPlayerU() {
		return playerU;
	}
	public Image getPlayerD() {
		return playerD;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getPlayerY() {
		return playerY;
	}
//	public int getStamina() {
//		return stamina;
//	}
	
}
