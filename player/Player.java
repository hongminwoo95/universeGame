package Player;
import java.awt.Image;
public interface Player {
	public Image getPlayer(); // 플레이어 해당방향 이미지
	//public void move(); // 이동거리
	public int getPlayerX(); // 플레이어 해당이미지 x축
	public int getPlayerY(); // 플레이어 해당이미지 y축
	public int getPlayerWidth(); // 플레이어 너비
	public int getPlayerHeight(); // 플레이어 높이
}
