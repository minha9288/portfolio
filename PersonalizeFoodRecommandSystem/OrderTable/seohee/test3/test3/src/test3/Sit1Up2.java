/**@author �輭��
 * 1�� �ڸ��� ���� ����� 1�ܰ� ȭ���� ũ�� Ű���� ���
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
		// 1���ڸ��� ȭ�� ũ��
		nfc_x1 = (int)(x/2*1.6);
		nfc_y1= (int)(y/2*1.6);
		nfc_x_s1 = 0;
		nfc_y_s1 = 0;
		
		// 2�� �ڸ��� ȭ�� ũ��
		nfc_x2 = (int)(x/2*0.4);
		nfc_y2 = (int)(y/2*1.6);
		nfc_x_s2 = (int)(x/2*1.6);
		nfc_y_s2 = 0;
		
		// 3�� �ڸ��� ȭ�� ũ��
		nfc_x3 = (int)(x/2*1.6);
		nfc_y3 = (int)(y/2*0.4);
		nfc_x_s3 = 0;
		nfc_y_s3 = (int)(y/2*1.6);
		
		// 4�� �ڸ��� ȭ�� ũ��
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