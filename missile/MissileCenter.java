package missile;

import java.awt.Image;

public class MissileCenter {
	
	private static MissileCenter missilecenter = new MissileCenter(); // 싱글톤패턴 적용
	public static MissileCenter getMissileCenter() {
		return missilecenter;
	}
	private String type; // 미사일 타입
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	public Missile getMissileImage() { // 미사일 이미지 가져오기
		Missile missile;
		if (type.equals("미사일좌측")) {
			missile = new getImage;
		}
		
//		
//		
//		
//		return null;
//		
//	}
//	public Missile getMissile(int x, int y) { // GameFrame 클래스 directionProcess메서드참조
//		if (type.equals("미사일좌측")) { // 조건일때
//			return new MissileOneLeft(x,y); // MissileOneLeft x,y값을 가진 객체 생성
//		}else if (type.equals("playerR")) {
//			return new MissileOneRight(x,y);
//		}else if (type.equals("playerU")) {
//			return new MissileOneUp(x,y);
//		}else if (type.equals("playerD")) {
//			return new MissileOneDown(x,y);
//		}
//		return null;
//	}
//	public Missile makeMissile(Missile missile, int x, int y) {
//		if(missile instanceof MissileOneUp) {
//			return new MissileOneUp(x,y);
//		}else if (missile instanceof MissileOneDown) {
//			return new MissileOneDown(x,y);
//		}else if (missile instanceof MissileOneDown) {
//			return new MissileOneDown(x,y);
//		}else if (missile instanceof MissileOneLeft) {
//			return new MissileOneLeft(x,y);
//		}else if (missile instanceof MissileOneRight) {
//			return new MissileOneRight(x,y);
//		}else {
//			return null;
//		}
//	}
}
