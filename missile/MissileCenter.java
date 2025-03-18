package missile;

import java.awt.Image;

import main.GameFrame;

public class MissileCenter {
	
	
	private GameFrame gameframe;
	private static MissileCenter missilecenter = new MissileCenter();
	public static MissileCenter getMissileCenter() {
		return missilecenter;
	}
	private Image my; // 플레이어 이동중 이미지 //여기에 수정
	private Image playerL; // 좌측면 캐릭터 이미지
	private Image playerR; // 우측면 캐릭터 이미지
	private Image playerU; // 상단면 캐릭터 이미지
	private Image playerD; // 하단면 캐릭터 이미지
	public void setMy(Image my) {
		this.my = my;
	}
	private String a;
	public void setA(String a) {
		this.a = a;
	}
	public Missile getMissile(String t, int x, int y) { // GameFrame 클래스 directionProcess메서드참조
		if (t.equals("playerL")) { // direction가 MissileOneLeft 이미지이면
			return new MissileOneLeft(x,y); // MissileOneLeft x,y값을 가진 객체 생성
		}else if (t.equals("playerR")) {
			return new MissileOneRight(x,y);
		}else if (t.equals("playerU")) {
			return new MissileOneUp(x,y);
		}else if (t.equals("playerD")) {
			return new MissileOneDown(x,y);
		}
		return null;
	}
	public Missile makeMissile(Missile missile, int x, int y) {
		if(missile instanceof MissileOneUp) {
			return new MissileOneUp(x,y);
		}else if (missile instanceof MissileOneDown) {
			return new MissileOneDown(x,y);
		}else if (missile instanceof MissileOneDown) {
			return new MissileOneDown(x,y);
		}else if (missile instanceof MissileOneLeft) {
			return new MissileOneLeft(x,y);
		}else if (missile instanceof MissileOneRight) {
			return new MissileOneRight(x,y);
		}else {
			return null;
		}
	}
	
//	private int a;
//	public void setA(int a) {
//		this.a = a;
//	}
//	public Missile getMissile(Image my, int x, int y) { // GameFrame 클래스 directionProcess메서드참조
//		boolean flag = true;
//		while (flag) {	
//			switch(a) { 			
//			case 1: return new MissileOneLeft(x,y); 
//			case 2: return new MissileOneRight(x,y); 
//			case 3: return new MissileOneUp(x,y);
//			case 4: return new MissileOneDown(x,y);
//			default : flag = false;
//			}
//		}
//		return null;
//	}
	




}
