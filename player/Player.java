package Player;

import java.awt.Image;

import missile.Missile;
import missile.MissileCenter;

public interface Player {
	public Image getPlayer(); // 플레이어 해당방향 이미지
	public void getPlayerX(); // 플레이어 해당이미지 x축
	public void getPlayerY(); // 플레이어 해당이미지 y축
	public void getPlayerWidth(); // 플레이어 너비
	public void getPlayerHeight(); // 플레이어 높이
}
