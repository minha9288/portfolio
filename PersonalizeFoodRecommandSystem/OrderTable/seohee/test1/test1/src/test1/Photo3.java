package test1;

import javax.swing.*;
import java.awt.*;


public class Photo3 extends JPanel {
	Image img1;
	
	public Photo3(String dir) {
		img1 = Toolkit.getDefaultToolkit().getImage(dir);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img1, 0, 100, 100, 0, 0, 0, img1.getWidth(this), img1.getHeight(this), this);
		g.drawRect(200, 200, 100, 100);
	}
}