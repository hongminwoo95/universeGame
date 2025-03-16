package player;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private int type; // 캐릭터 타입 0:평시, 1:진화 , 2:초월진화
	private int stamina; // 캐릭터의 체력
	

	private Image playerL = new ImageIcon("images/좌측.png").getImage(); // 좌측면 캐릭터
	private Image playerR = new ImageIcon("images/우측.png").getImage(); // 우측면 캐릭터
	private Image playerU = new ImageIcon("images/상단.png").getImage(); // 상단면 캐릭터
	private Image playerD = new ImageIcon("images/하단.png").getImage(); // 하단면 캐릭터
	
	

	public int getPlayerX() {
		return playerX;
	}
	public Image getPlayerL() {
		return playerL;
	}
	public void setPlayerL(Image playerL) {
		this.playerL = playerL;
	}
	public Image getPlayerR() {
		return playerR;
	}
	public void setPlayerR(Image playerR) {
		this.playerR = playerR;
	}
	public Image getPlayerU() {
		return playerU;
	}
	public void setPlayerU(Image playerU) {
		this.playerU = playerU;
	}
	public Image getPlayerD() {
		return playerD;
	}
	public void setPlayerD(Image playerD) {
		this.playerD = playerD;
	}
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
}
