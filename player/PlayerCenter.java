package Player;

import java.awt.Image;

public class PlayerCenter {
	
	private static PlayerCenter playercenter = new PlayerCenter(); // 싱글톤패턴
	public static PlayerCenter getPlayerCenter() {
		return playercenter;
	}
	private String type; // 키이벤트 발생시 인터페이스 접근 변수

	
	public Image getPlayer() {
		Image temp = null;
		if(type.equals("PlayerL")) { //키입력받은 글자열이 PlayerL일시
			temp = PlayerOneLeft.getPlayerOneLeft().getPlayer(); // PlayerL이미지 참조
		}else if (type.equals("PlayerR")) {
			temp = PlayerOneRight.getPlayerOneRight().getPlayer();
		}else if (type.equals("PlayerU")) {
			temp = PlayerOneUp.getPlayerOneUp().getPlayer();
		}else if (type.equals("PlayerD")) {
			temp = PlayerOneDown.getPlayerOneDown().getPlayer();
		}else if (type.equals("PlayerLU")) {
			temp = PlayerOneLU.getPlayerOneLU().getPlayer();
		}else if (type.equals("PlayerLD")) {
			temp = PlayerOneLD.getPlayerOneLD().getPlayer();
		}else if (type.equals("PlayerRU")) {
			temp = PlayerOneRU.getPlayerOneRU().getPlayer();
		}else if (type.equals("PlayerDU")) {
			temp = PlayerOneDU.getPlayerOneDU().getPlayer();
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
	

