/**@author 김서희
 * 기본 4인이 자리에 앉은 경우의 화면 분할
 */
package test3;

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
	
	int imgSx=100, imgSy=100;
	Image img1;
	
	int textsize = 30;
	Font f;
	String text;
	
	public MyPanel4(int x, int y) {
		nfc_x1 = x/2;
		nfc_y1= y/2;
		nfc_x_s1 = 0;
		nfc_y_s1 = 0;
		imgSx = imgSx;
		imgSy = imgSy;
		f = new Font(Font.SERIF, Font.PLAIN, textsize);
		text = "테스트용 test 1234 !@#$%";
		
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
		
		img1 = Toolkit.getDefaultToolkit().getImage("c:\\Users\\김서희\\Pictures\\7.jpg");
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawRect(nfc_x_s1, nfc_y_s1, nfc_x1, nfc_y1);
		g.drawImage(img1, 0, imgSx, imgSy, 0, 0, 0, img1.getWidth(this), img1.getHeight(this), this);
		g.setFont(f);
		g.drawString(text, 100, 100);
		g.drawRect(nfc_x_s2, nfc_y_s2, nfc_x2, nfc_y2);
		g.drawRect(nfc_x_s3, nfc_y_s3, nfc_x3, nfc_y3);
		g.drawRect(nfc_x_s4, nfc_y_s4, nfc_x4, nfc_y4);
		g.setColor(Color.red);
		g.drawRect(0, 0, 400, 400);
	}
}