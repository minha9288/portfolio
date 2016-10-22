package test1;

import javax.swing.*;
import java.awt.*;

public class Photo extends JPanel {
	Image img1;
	
	public Photo(String dir) {
		//dir(�̹����� �ּ�)�� �ִ� �̹����� ����
		img1 = Toolkit.getDefaultToolkit().getImage(dir);
	}
	public void paint(Graphics g) {
		super.paint(g);
		
		//�̹����� ����� ȭ�鿡 draw
		g.drawImage(img1, 0, 100, 100, 0, 0, 0, img1.getWidth(this), img1.getHeight(this), this); // �̹��� ���, ������ x����, y��, x��, y����, ���������� x�� ����, y�� ����, x�� ��, y�� ��, this
	}
}