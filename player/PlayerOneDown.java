package Player;

import java.awt.Image;
import java.awt.Paint;

import javax.swing.ImageIcon;

import missile.Missile;
import missile.MissileCenter;


public class PlayerOneDown implements Player{ // 캐릭터 하단
	
	private int playerX = 0; // 캐릭터 x좌표
	private int playerY = 0; // 캐릭터 y좌표
	
	private Image playerD = new ImageIcon("images/좌측.png").getImage(); // 좌측면 캐릭터

	public Image PlayerOneDown() {
		return playerD;
	}



	@Override
	public Image getPlayer() {
		return playerD;
	}



	@Override
	public void getPlayerX() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getPlayerY() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void getPlayerWidth() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void getPlayerHeight() {
		// TODO Auto-generated method stub
		
	}




	
	

}
