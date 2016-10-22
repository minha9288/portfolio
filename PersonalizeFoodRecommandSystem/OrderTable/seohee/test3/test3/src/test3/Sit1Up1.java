/**@author 김서희
 * 1번 자리에 앉은 사람이 1단계 화면을 크게 키웠을 경우
 */
package test3;

import javax.swing.*;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.*;

public class Sit1Up1 extends JPanel {
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
	
	public Sit1Up1(int x, int y) {
		// 1번자리의 화면 크기
		nfc_x1 = (int)(x/2*1.3);
		nfc_y1= (int)(y/2*1.3);
		nfc_x_s1 = 0;
		nfc_y_s1 = 0;
		imgSx = (int)(imgSx*1.3);
		imgSy = (int)(imgSy*1.3);
		f = new Font(Font.SERIF, Font.PLAIN, (int)(textsize*1.3));
		text = "테스트용 test 1234 !@#$%";
		
		// 2번 자리의 화면 크기
		nfc_x2 = (int)(x/2*0.7);
		nfc_y2 = (int)(y/2*1.3);
		nfc_x_s2 = (int)(x/2*1.3);
		nfc_y_s2 = 0;
		
		// 3번 자리의 화면 크기
		nfc_x3 = (int)(x/2*1.3);
		nfc_y3 = (int)(y/2*0.7);
		nfc_x_s3 = 0;
		nfc_y_s3 = (int)(y/2*1.3);
		
		// 4번 자리의 화면 크기
		nfc_x4 = (int)(x/2*0.7);
		nfc_y4 = (int)(y/2*0.7);
		nfc_x_s4 = (int)(x/2*1.3);
		nfc_y_s4 = (int)(y/2*1.3);
		
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
		g.drawRect(0, 0, 300, 300);
		g.drawRect(0, 0, 600, 600);
	}
}