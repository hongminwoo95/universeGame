package Player;

import java.awt.Image;

import javax.swing.ImageIcon;

import missile.Missile;
import missile.MissileCenter;

public class PlayerCenteraaaa {

	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	private Image my; // player 이동중 이미지
	
	private Image playerL = new ImageIcon("images/좌측.png").getImage(); // 좌측면 캐릭터
	private Image playerR = new ImageIcon("images/우측.png").getImage(); // 우측면 캐릭터
	private Image playerU = new ImageIcon("images/상단.png").getImage(); // 상단면 캐릭터
	private Image playerD = new ImageIcon("images/하단.png").getImage(); // 하단면 캐릭터
	private Image playerLU = new ImageIcon("images/좌측상단대각.png").getImage(); //좌측상단대각
	private Image playerLD = new ImageIcon("images/좌측하단대각.png").getImage(); //좌측상단대각
	private Image playerRU = new ImageIcon("images/우측상단대각.png").getImage(); //좌측상단대각
	private Image playerRD = new ImageIcon("images/우측하단대각.png").getImage(); //좌측상단대각
	
	private MissileCenter missileCenter = MissileCenter.getMissileCenter();
	// MissileCenter 객체에 getMissileCenter메서드 주소를 missileCenter에 대입
	
	private Missile ms; // 미사일 접근 변수
	
	// MissileCenter객체 주소의 getMissile 인자값을 받아
	public Missile getMissile() {
		return ms;
	}
	
	public void setMy(Image my) {
		this.my = my;
		String type = "player";
		if (my == playerU) {
			type = "playerU";
		}
		if (my == playerD) {
			type = "playerD";
		}
		if (my == playerL) {
			type = "playerL";
		}
		if (my == playerR) {
			type = "playerR";
		}
		ms = missileCenter.getMissile(type, playerX, playerY);
	}

	public MissileCenter getMissileCenter() {
		return missileCenter;
	}

	public Missile getMs() {
		return ms;
	}
	
	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
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

//	public int getStamina() {
//		return stamina;
//	}

}
