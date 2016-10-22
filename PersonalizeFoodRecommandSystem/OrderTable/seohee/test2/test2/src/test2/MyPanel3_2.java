package test2;

import javax.swing.*;
import java.awt.*;

class MyPanel3_2 extends JPanel {
	int nfc_x, nfc_y;
	int nfc_x_s, nfc_y_s;
	int nfc_x1, nfc_y1;
	int nfc_x_s1, nfc_y_s1;
	int nfc_x2, nfc_y2;
	int nfc_x_s2, nfc_y_s2;
	public MyPanel3_2(int x, int y) {
		nfc_x = x;
		nfc_y = y/2;
		nfc_x_s = 0;
		nfc_y_s = y/2;
		
		nfc_x1 = x/2;
		nfc_y1= y/2;
		nfc_x_s1 = 0;
		nfc_y_s1 = 0;
		
		nfc_x2 = x/2;
		nfc_y2 = y/2;
		nfc_x_s2 = x/2;
		nfc_y_s2 = 0;
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawRect(nfc_x_s, nfc_y_s, nfc_x, nfc_y); // 사각형을 그림
		g.drawRect(nfc_x_s1, nfc_y_s1, nfc_x1, nfc_y1);
		g.drawRect(nfc_x_s2, nfc_y_s2, nfc_x2, nfc_y2);
	}
}