package Player;

import java.awt.Image;

public class PlayerCenter {
	
	private static PlayerCenter playercenter = new PlayerCenter(); // 싱글톤패턴
	

	public static PlayerCenter getPlayerCenter() {
	    
		return playercenter;
	}
	
	//private Player p = new PlayerOneLeft();
	
	private String type; // 키이벤트 발생시 인터페이스 접근 변수

	public Player getPlayer() {
		Player temp = null;
		if(type.equals("PlayerL")) { //키입력받은 글자열이 PlayerL일시
			temp = new PlayerOneLeft();
			// PlayerL이미지 참조
		}else if (type.equals("PlayerR")) {
			temp = new PlayerOneRight();
		}else if (type.equals("PlayerU")) {
			temp = new PlayerOneUp();
		}else if (type.equals("PlayerD")) {
			temp = new PlayerOneDown();
		}else if (type.equals("PlayerLU")) {
			temp = new PlayerOneLU();
		}else if (type.equals("PlayerLD")) {
			temp = new PlayerOneLD();
		}else if (type.equals("PlayerRU")) {
			temp = new PlayerOneRU();
		}else if (type.equals("PlayerDU")) {
			temp = new PlayerOneDU();
		}
		return temp;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
	

