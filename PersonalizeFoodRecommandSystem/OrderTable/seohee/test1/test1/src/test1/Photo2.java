package test1;

import javax.swing.*;
import java.awt.*;

public class Photo2 extends JPanel {
	Image img1;
	
	public Photo2(String dir) {
		img1 = Toolkit.getDefaultToolkit().getImage(dir);
	}
	public void paint(Graphics g) {
		super.paint(g);
		
		// 이미지를 뒤집지 않고 그대로 draw
		//g.drawImage(img1, 0, 0, 100, 100, this); // 이미지 경로, x시작, y시작, x크기, y크기, this
		g.drawRect(10, 10, 100, 100);
	}
}
