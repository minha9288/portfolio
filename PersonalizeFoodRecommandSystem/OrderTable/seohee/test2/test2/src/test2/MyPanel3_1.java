package test2;

import javax.swing.*;
import java.awt.*;

class MyPanel3_1 extends JPanel {
	int nfc_x, nfc_y;
	int nfc_x_s, nfc_y_s;
	int nfc_x3, nfc_y3;
	int nfc_x_s3, nfc_y_s3;
	int nfc_x4, nfc_y4;
	int nfc_x_s4, nfc_y_s4;
	public MyPanel3_1(int x, int y) {
		nfc_x = x;
		nfc_y = y/2;
		nfc_x_s = 0;
		nfc_y_s = 0;
		
		nfc_x3 = x/2;
		nfc_y3= y/2;
		nfc_x_s3 = 0;
		nfc_y_s3 = y/2;
		
		nfc_x4 = x/2;
		nfc_y4 = y/2;
		nfc_x_s4 = x/2;
		nfc_y_s4 = y/2;
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawRect(nfc_x_s, nfc_y_s, nfc_x, nfc_y); // 사각형을 그림
		g.drawRect(nfc_x_s3, nfc_y_s3, nfc_x3, nfc_y3);
		g.drawRect(nfc_x_s4, nfc_y_s4, nfc_x4, nfc_y4);
	}
}