package missile;

public class MissileCenter {
	
	private static MissileCenter missilecenter = new MissileCenter(); // 싱글톤패턴 적용
	public static MissileCenter getMissileCenter() {
		return missilecenter;
	}
	private String playertype; // 플레이어 타입
	
	
	public String getPlayertype() {
		return playertype;
	}
	public void setPlayertype(String playertype) {
		this.playertype = playertype;
	}

	public Missile getMissile(int x, int y) { // 미사일 이미지 가져오기
		Missile missile = null;
		if (playertype.equals("PlayerL")) { // 플레이어 방향이 좌측일때
			missile = new MissileOneLeft(x,y); // 좌측미사일 객체 생성
		}else if (playertype.equals("PlayerR")) {
			missile = new MissileOneRight(y, y);
 		}else if (playertype.equals("PlayerU")) {
			missile = new MissileOneUp(y, y);
		}else if (playertype.equals("PlayerD")) {
			missile = new MissileOneDown(y, y);
		}else if (playertype.equals("PlayerLU")) {
			missile = new MissileOneLU(x,y);
		}else if (playertype.equals("PlayerLD")) {
			missile = new MissileOneLD(x,y);
		}else if (playertype.equals("PlayerRU")) {
			System.out.println("PlayerRU");
			missile = new MissileOneRU(x,y);
		}else if (playertype.equals("PlayerRD")) {
			System.out.println("PlayerRD");
			missile = new MissileOneRD(x,y);
		}
		return missile;
	}	
	
	public Missile getMissileXY(Missile missileImage, int x, int y) { // 그리기용
			if (missileImage instanceof MissileOneLeft) {
				return new MissileOneLeft(x,y);
			}else if (missileImage instanceof MissileOneRight) {
				return new MissileOneRight(x,y);
			}else if (missileImage instanceof MissileOneUp) {
				return new MissileOneUp(x,y);
			}else if (missileImage instanceof MissileOneDown) {
				return new MissileOneDown(x,y);
			}else if (missileImage instanceof MissileOneLD) {
				return new MissileOneLD(x,y);
			}else if (missileImage instanceof MissileOneLU) {
				return new MissileOneLU(x,y);
			}else if (missileImage instanceof MissileOneRU) {
				return new MissileOneRU(x,y);
			}else if (missileImage instanceof MissileOneRD) {
				return new MissileOneRD(x,y);
			}
			return null;
	}
	
	
//	public Missile getMissile() { // 미사일 이미지 가져오기
//		Missile missile = null;
//		if (playertype.equals("PlayerL")) { // 플레이어 방향이 좌측일때
//			missile = new MissileOneLeft(); // 좌측미사일 객체 생성
//		}else if (playertype.equals("PlayerR")) {
//			missile = new MissileOneRight();
// 		}else if (playertype.equals("PlayerU")) {
//			missile = new MissileOneUp();
//		}else if (playertype.equals("PlayerD")) {
//			missile = new MissileOneDown();
//		}else if (playertype.equals("PlayerLU")) {
//			missile = new MissileOneLU();
//		}else if (playertype.equals("PlayerLD")) {
//			missile = new MissileOneLD();
//		}else if (playertype.equals("PlayerRU")) {
//			missile = new MissileOneRU();
//		}else if (playertype.equals("PlayerRD")) {
//			missile = new MissileOneRD();
//		}
//		return missile;
//	}	
	
	
//	public Missile getMissileXY(Missile missileImage, int x, int y) { // 미사일 이미지 가져오기
//		Missile missile = null;
//		if (playertype.equals("PlayerL")) { // 플레이어 방향이 좌측일때
//			if (Missile instanceof MissileOneLeft) {
//				missile = (Missile) new MissileOneLeft().MissileOneL(x, y); // 좌측미사일 객체 생성
//			}
//		}else if (playertype.equals("PlayerR")) {
//			if (Missile instanceof MissileOneLeft) {
//				missile = (Missile) new MissileOneRight().MissileOneR(x, y);
//			}
//		}else if (playertype.equals("PlayerU")) {
//			if (Missile instanceof MissileOneLeft) {
//				missile = (Missile) new MissileOneUp().MissileOneUp(x, y);
//			}
//		}else if (playertype.equals("PlayerD")) {
//			if (Missile instanceof MissileOneLeft) {
//				missile = (Missile) new MissileOneDown().MissileOneDown(x, y);
//			}
//		}else if (playertype.equals("PlayerLU")) {
//			if (Missile instanceof MissileOneLeft) {
//				missile = (Missile) new MissileOneLU().MissileOneUp(x, y);
//			}
//		}else if (playertype.equals("PlayerLD")) {
//			if (Missile instanceof MissileOneLeft) {
//				missile = (Missile) new MissileOneLD().MissileOneUp(x, y);
//			}
//		}else if (playertype.equals("PlayerRU")) {
//			if (Missile instanceof MissileOneLeft) {
//				missile = (Missile) new MissileOneRU().MissileOneUp(x, y);
//			}
//		}else if (playertype.equals("PlayerRD")) {
//			if (Missile instanceof MissileOneLeft) {
//			missile = new MissileOneRD();
//		}
//		}
//		return missile;
//	}
	
	
	
	
//	public Missile makeMissile(Missile a, int x, int y) {
//		Missile missile = null;
//		if(playertype.equals("PlayerL")) {
//		
//			return makeMissile;
//		}
			
			
			
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
//		return missile;
//	}
}

