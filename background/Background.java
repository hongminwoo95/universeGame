package background;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Background {

	private Image background1 = new ImageIcon("images/화염행성.png").getImage();
	private Image background2 = new ImageIcon("images/얼음행성.png").getImage();
	
	
	public Image getBackground1() {
		return background1;
	}
	public void setBackground1(Image background1) {
		this.background1 = background1;
	}
	public Image getBackground2() {
		return background2;
	}
	public void setBackground2(Image background2) {
		this.background2 = background2;
	}
	
	
}
