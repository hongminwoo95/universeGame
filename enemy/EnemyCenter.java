package enemy;


public class EnemyCenter {

	private static EnemyCenter enemycenter = new EnemyCenter();
	public static EnemyCenter getEnemyCenter() {
		return enemycenter;
	}
	
	private int x; // 적기생성 x좌표
	private int y; // 적기생성 y좌표
	
	private String location; // 적기 생성 위치
	public void setLocation(String location) {
		this.location = location;
	}
	public Enemy getEnemyOne(int x, int y) { // x,y좌표값 설정
		Enemy enemytemp = null;
		if (location.equals("MakeEnemyR")) {
			enemytemp = new EnemyRight(x,y); // EnemyRight 객체주소 생성
			return enemytemp;
		}if (location.equals("MakeEnemyL")) {
			enemytemp = new EnemyLeft(x,y); // EnemyLeft 객체주소 생성
			return enemytemp;
		}if (location.equals("MakeEnemyU")) {
			enemytemp = new EnemyUp(x,y); // EnemyLeft 객체주소 생성
			return enemytemp;
		}
		
		return null;
	}
	public Enemy setEnemyXY(Enemy enemy,int x, int y) {
		if (enemy instanceof EnemyRight) {
			return new EnemyRight(x,y);
		}
		if (enemy instanceof EnemyLeft) {
			return new EnemyLeft(x,y);
		}
		if (enemy instanceof EnemyUp) {
			return new EnemyUp(x,y);
		}
		return null;
	}
}
