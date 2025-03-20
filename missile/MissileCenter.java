package missile;

public class MissileCenter {
	
	private static MissileCenter missilecenter = new MissileCenter(); // 싱글톤패턴 적용
	public static MissileCenter getMissileCenter() {
		return missilecenter;
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
}
