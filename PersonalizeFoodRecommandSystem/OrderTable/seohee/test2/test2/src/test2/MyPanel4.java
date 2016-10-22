package test2;

import javax.swing.*;
import java.awt.*;

class MyPanel4 extends JPanel {
	int nfc_x1, nfc_y1;
	int nfc_x_s1, nfc_y_s1;
	int nfc_x2, nfc_y2;
	int nfc_x_s2, nfc_y_s2;
	int nfc_x3, nfc_y3;
	int nfc_x_s3, nfc_y_s3;
	int nfc_x4, nfc_y4;
	int nfc_x_s4, nfc_y_s4;
	
	public MyPanel4(int x, int y) {
		nfc_x1 = x/2;
		nfc_y1= y/2;
		nfc_x_s1 = 0;
		nfc_y_s1 = 0;
		
		nfc_x2 = x/2;
		nfc_y2 = y/2;
		nfc_x_s2 = x/2;
		nfc_y_s2 = 0;
		
		nfc_x3 = x/2;
		nfc_y3 = y/2;
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
		g.drawRect(nfc_x_s1, nfc_y_s1, nfc_x1, nfc_y1);
		g.drawRect(nfc_x_s2, nfc_y_s2, nfc_x2, nfc_y2);
		g.drawRect(nfc_x_s3, nfc_y_s3, nfc_x3, nfc_y3);
		g.drawRect(nfc_x_s4, nfc_y_s4, nfc_x4, nfc_y4);
	}
}