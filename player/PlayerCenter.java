package Player;

import java.awt.Image;

public class PlayerCenter {
	
	private static PlayerCenter playercenter = new PlayerCenter(); // 싱글톤패턴
	public static PlayerCenter getPlayerCenter() {
		return playercenter;
	}
	
	//private Player p = new PlayerOneLeft();
	
	private String type; // 키이벤트 발생시 인터페이스 접근 변수
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Player getPlayer() {
		Player temp = null;
		if(type.equals("PlayerL")) { //키입력받은 글자열이 PlayerL일시
			temp = new PlayerOneLeft();
		}
		if (type.equals("PlayerR")) {
			temp = new PlayerOneRight();
		}
		if (type.equals("PlayerU")) {
			temp = new PlayerOneUp();
		}
		if (type.equals("PlayerD")) {
			temp = new PlayerOneDown();
		}
		if (type.equals("PlayerLU")) {
			temp = new PlayerOneLU();
		}
		if (type.equals("PlayerLD")) {
			temp = new PlayerOneLD();
		}
		if (type.equals("PlayerRU")) {
			temp = new PlayerOneRU();
		}
		if (type.equals("PlayerRD")) {
			temp = new PlayerOneRD();
		}
		return temp;
	}
	
}
	

