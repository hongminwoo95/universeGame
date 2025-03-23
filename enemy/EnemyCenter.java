package enemy;

import main.GameFrame;

public class EnemyCenter {

	private static EnemyCenter enemycenter = new EnemyCenter();
	public static EnemyCenter getEnemyCenter() {
		return enemycenter;
	}
	private int timer; // 시간초별
	private int x; // 적기생성 x좌표
	private int y; // 적기생성 y좌표
	
	
	
	public void setTimer(int timer) {
		this.timer = timer;
	}
	
	public Enemy getEnemyOne(int x, int y) { // x,y좌표값 설정
		Enemy enemytemp = null;
		if(timer%2==0) { // 타이머가 2초 단위로 올라갈때
			enemytemp = new EnemyRight(x,y); // EnemyRight 객체주소 생성
		}
		return enemytemp;
	}
	
	public Enemy getEnemyXY(Enemy enemy,int x, int y) {
		if (enemy instanceof Enemy) {
			return new EnemyRight(x,y);
		}
		return null;
	}
	
	
	
}
