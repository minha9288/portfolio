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
		
		// �̹����� ������ �ʰ� �״�� draw
		//g.drawImage(img1, 0, 0, 100, 100, this); // �̹��� ���, x����, y����, xũ��, yũ��, this
		g.drawRect(10, 10, 100, 100);
	}
}
