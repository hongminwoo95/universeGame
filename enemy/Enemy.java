package enemy;

import java.awt.Image;

public interface Enemy {
	public Image getImage(); //이미지
	public void getmove(); // 움직임
	public int getenemyX(); // 우주선 x축
	public int getenemyY(); // 우주선 y축
	public int getenemyWidth();  // 우주선 너비
	public int getenemyHeight(); // 우주선 높이
}
