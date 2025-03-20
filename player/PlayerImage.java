//package Player;
//
//import java.awt.Image;
//
//import javax.swing.ImageIcon;
//
//import missile.Missile;
//import missile.MissileCenter;
//
//public class PlayerImage implements Player{
//	
//	private static  PlayerImage playerimage = new PlayerImage();
//	public static PlayerImage getPlayerImage() {
//		return playerimage;
//	}
//	
//	private Image my; // 현재 캐릭터 이미지
//	
//	public Image getMy() {
//		return my;
//	}
//	public void setMy(Image my) {
//		this.my = my;
//	}
//	
//	private Image playerL = new ImageIcon("images/좌측.png").getImage(); // 좌측면 캐릭터
//	private Image playerR = new ImageIcon("images/우측.png").getImage(); // 우측면 캐릭터
//	private Image playerU = new ImageIcon("images/상단.png").getImage(); // 상단면 캐릭터
//	private Image playerD = new ImageIcon("images/하단.png").getImage(); // 하단면 캐릭터
//	private Image playerLU = new ImageIcon("images/좌측상단대각.png").getImage(); //좌측상단대각
//	private Image playerLD = new ImageIcon("images/좌측하단대각.png").getImage(); //좌측상단대각
//	private Image playerRU = new ImageIcon("images/우측상단대각.png").getImage(); //좌측상단대각
//	private Image playerRD = new ImageIcon("images/우측하단대각.png").getImage(); //좌측상단대각
//
//	public Image getPlayerL() {
//		return playerL;
//	}
//	public Image getPlayerR() {
//		return playerR;
//	}
//	public Image getPlayerU() {
//		return playerU;
//	}
//	public Image getPlayerD() {
//		return playerD;
//	}
//	public Image getPlayerLU() {
//		return playerLU;
//	}
//	public Image getPlayerLD() {
//		return playerLD;
//	}
//	public Image getPlayerRU() {
//		return playerRU;
//	}
//	public Image getPlayerRD() {
//		return playerRD;
//	}
//
//	
//	@Override
//	public Missile getMs() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Missile getMissile() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public MissileCenter getPlayerCenter() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Image getPlayer() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public void getPlayerX() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void getPlayerY() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	
//	
//}
