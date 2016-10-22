package test1;

import javax.swing.*;
import java.awt.*;

public class Photo4 extends JPanel {
	Image img1;
	
	public Photo4(String dir) {
		img1 = Toolkit.getDefaultToolkit().getImage(dir);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img1, 0, 0, 100, 100, this);
	}
}
