/**@author 김서희
 * 1번 자리에 앉은 사람이 1단계 화면을 크게 키웠을 경우
 */
package test3;

import javax.swing.*;
import java.awt.*;

public class Sit1Up2 extends JPanel {
	int nfc_x1, nfc_y1;
	int nfc_x_s1, nfc_y_s1;
	int nfc_x2, nfc_y2;
	int nfc_x_s2, nfc_y_s2;
	int nfc_x3, nfc_y3;
	int nfc_x_s3, nfc_y_s3;
	int nfc_x4, nfc_y4;
	int nfc_x_s4, nfc_y_s4;
	
	
	public Sit1Up2(int x, int y) {
		// 1번자리의 화면 크기
		nfc_x1 = (int)(x/2*1.6);
		nfc_y1= (int)(y/2*1.6);
		nfc_x_s1 = 0;
		nfc_y_s1 = 0;
		
		// 2번 자리의 화면 크기
		nfc_x2 = (int)(x/2*0.4);
		nfc_y2 = (int)(y/2*1.6);
		nfc_x_s2 = (int)(x/2*1.6);
		nfc_y_s2 = 0;
		
		// 3번 자리의 화면 크기
		nfc_x3 = (int)(x/2*1.6);
		nfc_y3 = (int)(y/2*0.4);
		nfc_x_s3 = 0;
		nfc_y_s3 = (int)(y/2*1.6);
		
		// 4번 자리의 화면 크기
		nfc_x4 = (int)(x/2*0.4);
		nfc_y4 = (int)(y/2*0.4);
		nfc_x_s4 = (int)(x/2*1.6);
		nfc_y_s4 = (int)(y/2*1.6);
		
		
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